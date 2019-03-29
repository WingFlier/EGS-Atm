package com.egs.atm.services;

import com.egs.atm.models.Account;
import com.egs.atm.models.bankModels.IssuerBank;
import com.egs.atm.models.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class BankService
{
	IssuerBank bank;

	public BankService(IssuerBank bank)
	{
		this.bank = bank;
	}


	public synchronized boolean cashOut(Card card, int cashOutAmount) throws Exception
	{
		Account account = findAccount(card);
		checkCardBalance(cashOutAmount, account);
		account.setBalance(account.getBalance() - cashOutAmount);
		return true;
	}

	private void checkCardBalance(int cashOutAmount, Account account) throws Exception
	{
		if (account.getBalance() < cashOutAmount)
			throw new Exception("not enough money on card");
	}


	private Account findAccount(Card card)
	{
		HashMap<Long, ArrayList<Account>> accounts = bank.getAccounts();
		ArrayList<Account> personAccList = accounts.get(card.getPid());
		return getAccountByCard(personAccList, card);
	}

	private Account getAccountByCard(ArrayList<Account> pAccList, Card card)
	{

		Account acc = pAccList.stream().filter(account -> account.getCard().equals(card)).findAny().orElse(null);
		return acc;
	}

	/*account.getCardList().stream().filter(card1 -> {
				card1.equals(card)
			}*/
}
