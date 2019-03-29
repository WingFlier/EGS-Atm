package com.egs.atm.models;

import java.util.ArrayList;
import java.util.List;

public class Person
{
	private int id = 1;
	private String name;
	private List<Card> cardList;

	public Person(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public void setCardList(List<Card> cardList)
	{
		this.cardList = cardList;
	}

	public void addCard(Card card)
	{
		cardList.add(card);
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public List<Card> getCardList()
	{
		return cardList;
	}
}
