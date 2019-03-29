package com.egs.atm.models;

import com.egs.atm.enums.CardBrand;
import com.egs.atm.enums.Currency;
import com.egs.atm.models.bankModels.AmeriaBank;
import com.egs.atm.models.bankModels.EvocaBank;
import com.egs.atm.models.bankModels.HSBCBank;
import com.egs.atm.models.bankModels.IssuerBank;
import com.egs.atm.utils.IValidate;

import java.time.Instant;
import java.util.Objects;

import javax.xml.bind.ValidationException;

public class Card implements IValidate
{
	//TODO change person to pId Long /
	// brand to enum /
	// issuer bank should be string
	// date to Instant /
	// brand to enum CardBrand /
	// currency to enum Currency /
	// card is immutable
	// user Builder pattern for this class /

	//TODO abstract or interface parser for different type of parsers CardParser

	//TODO Parser<T> class
	// T parse() method
	// use this as functional interface

	//TODO create Ivalidate interface with validate method
	// implement it in Card Bank, Person
	// validating through all fields,
	// in Card pId with length, cardNum if null, the others as well

	private final long pid;
	private final IssuerBank bank;
	private final String cardNumber;
	private final Instant expireDate;
	private final CardBrand brand;
	private final Currency currencyType;
	private final String cardHolder;

	public Card(CardBuilder builder) throws ValidationException
	{
		this.bank = builder.bank;
		this.pid = builder.pid;
		this.cardNumber = builder.cardNumber;
		this.expireDate = builder.expireDate;
		this.brand = builder.brand;
		this.currencyType = builder.currencyType;
		this.cardHolder = builder.cardHolder;
		validate();
	}

	public String getCardHolder()
	{
		return cardHolder;
	}

	public String getCardNumber()
	{
		return cardNumber;
	}

	public Instant getExpireDate()
	{
		return expireDate;
	}

	public IssuerBank getBank()
	{
		return bank;
	}

	public long getPid()
	{
		return pid;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return pid == card.pid &&
				Objects.equals(bank, card.bank) &&
				Objects.equals(cardNumber, card.cardNumber) &&
				Objects.equals(expireDate, card.expireDate) &&
				brand == card.brand &&
				currencyType == card.currencyType &&
				Objects.equals(cardHolder, card.cardHolder);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(pid, bank, cardNumber, expireDate, brand, currencyType, cardHolder);
	}

	@Override
	public void validate() throws ValidationException
	{
		if (Objects.isNull(bank) || pid == 0 || Objects.isNull(cardNumber)
				|| Objects.isNull(expireDate) || Objects.isNull(brand)
				|| Objects.isNull(currencyType) || Objects.isNull(cardHolder))
		{
			throw new ValidationException("validation error");
		}

		if (bank instanceof AmeriaBank)
		{
			if (cardNumber.length() != 6 || !cardNumber.startsWith("5"))
				throw new ValidationException("Ameria bank card number should start with 5 and have 6 numbers");
		} else if (bank instanceof HSBCBank)
		{
			if (cardNumber.length() != 8 || !cardNumber.startsWith("7"))
				throw new ValidationException("Hsbc bank card number should start with 7 and have 8 numbers");
		} else if (bank instanceof EvocaBank)
		{
			if (cardNumber.length() != 10 || !cardNumber.startsWith("9"))
				throw new ValidationException("Ameria bank card number should start with 9 and have 10 numbers");
		}
	}

	public static class CardBuilder
	{
		private IssuerBank bank;
		private long pid;
		private String cardNumber;
		private Instant expireDate;
		private CardBrand brand;
		private Currency currencyType;
		private String cardHolder;

		public CardBuilder()
		{
		}

		public Card build() throws ValidationException
		{
			return new Card(this);
		}

		public CardBuilder setBank(IssuerBank bank)
		{
			this.bank = bank;
			return this;
		}

		public CardBuilder setPid(long pid)
		{
			this.pid = pid;
			return this;
		}

		public CardBuilder setCardNumber(String cardNumber)
		{
			this.cardNumber = cardNumber;
			return this;
		}

		public CardBuilder setExpireDate(Instant expireDate)
		{
			this.expireDate = expireDate;
			return this;
		}

		public CardBuilder setBrand(CardBrand brand)
		{
			this.brand = brand;
			return this;
		}

		public CardBuilder setCurrencyType(Currency currencyType)
		{
			this.currencyType = currencyType;
			return this;
		}

		public CardBuilder setCardHolder(String cardHolder)
		{
			this.cardHolder = cardHolder;
			return this;
		}
	}
}
