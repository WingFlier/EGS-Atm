package com.egs.atm.services;

import com.egs.atm.models.Atm;
import com.egs.atm.models.Card;
import com.egs.atm.models.bankModels.IssuerBank;

import java.time.Instant;

public class AtmService
{
	private Atm atm;

	public AtmService(Atm atm)
	{
		this.atm = atm;
	}

	public synchronized String putCard(Card card, int amount) throws Exception
	{
		checkAtmBalance(amount);
		atm.setCard(card);
		validateCardDate();


		//get the bank of the current card
		IssuerBank bank = atm.getCard().getBank();
		if (bankRequest(bank, amount))
		{
			atm.setBalance(atm.getBalance() - amount);
			atm.removeCard();
			return "Take your amount: " + amount;
		}
		return "something's wrong";
	}

	private boolean bankRequest(IssuerBank bank, int cashOutAmount) throws Exception
	{
		BankService service = bank.getService();
		return service.cashOut(atm.getCard(), cashOutAmount);
	}

	private void validateCardDate() throws Exception
	{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Instant expireDate = atm.getCard().getExpireDate();
		Instant currentDate = Instant.now();
		if (currentDate.compareTo(expireDate) > 0)
			throw new Exception("card expired");
	}

	private void checkAtmBalance(int amount) throws Exception
	{
		//if its amd we cannot give less than 1000
		if (atm.getBalance() < 1000 || atm.getBalance() < amount)
			throw new Exception("not enough money in atm");
	}
}
