package com.egs.atm.models;

import com.egs.atm.models.bankModels.IssuerBank;
import com.egs.atm.utils.IValidate;

import java.util.Objects;

import javax.xml.bind.ValidationException;


public class Account implements IValidate
{
	private long holderId;
	private Card card;
	private int balance;
	private String cardNumber;
	private IssuerBank bank;

	public Account(long holderId, int balance, IssuerBank bank, String cardNumber) throws ValidationException
	{
		this.holderId = holderId;
		this.balance = balance;
		this.bank = bank;
		this.cardNumber = cardNumber;
		validate();
	}

	public String getCardNumber()
	{
		return cardNumber;
	}

	public void setCardNumber(String cardNumber)
	{
		this.cardNumber = cardNumber;
	}

	public long getHolderId()
	{
		return holderId;
	}

	public IssuerBank getBank()
	{
		return bank;
	}

	public Card getCard()
	{
		return card;
	}

	public void setCard(Card card)
	{
		this.card = card;
	}


	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int balance)
	{
		this.balance = balance;
	}

	@Override
	public void validate() throws ValidationException
	{
		if (Objects.isNull(bank))
		{
			throw new ValidationException("validation error");
		}
	}
}
