package com.egs.atm.models;

import com.egs.atm.services.AtmService;

public class Atm
{
	private int balance;
	private Card card;

	public Atm(int balance)
	{
		this.balance = balance;
	}

	public AtmService startService()
	{
		return new AtmService(this);
	}

	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int balance)
	{
		this.balance = balance;
	}

	public Card getCard()
	{
		return card;
	}

	public void setCard(Card card)
	{
		this.card = card;
	}

	public void removeCard()
	{
		card = null;
	}
}