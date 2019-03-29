package com.egs.atm.models.bankModels;

import com.egs.atm.enums.CardBrand;
import com.egs.atm.models.Account;
import com.egs.atm.services.BankService;
import com.egs.atm.utils.IValidate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.xml.bind.ValidationException;

public abstract class IssuerBank implements IValidate
{
	BankService service;
	private HashMap<Long, ArrayList<Account>> accounts;
	CardBrand brand;
	//TODO every bank has to have Cardbrands enum
	// also here
	// validating what is same like accounts, enum here
	// for everything else like below comment every bank has it's own validator


	//TODO
	// Ameria - cardNumber length - 6, starts  with - 5
	// Hsbc- cardNumber length - 8, starts  with - 7
	// Evoca- cardNumber length - 10, starts  with - 9

	//TODO
	// banks should be singleton choose which one to use


	public void addAccount(long pId, Account account)
	{
		ArrayList<Account> acc = accounts.get(pId);
		if (acc == null)
		{
			acc = new ArrayList<>();
		}
		acc.add(account);
		this.accounts.put(pId, acc);
	}

	public IssuerBank()
	{
		service = new BankService(this);
		accounts = new HashMap<>();
	}

	public BankService getService()
	{
		return service;
	}

	public HashMap<Long, ArrayList<Account>> getAccounts()
	{
		return accounts;
	}

	@Override
	public void validate() throws ValidationException
	{
		if (Objects.isNull(accounts) || Objects.isNull(brand))
			throw new ValidationException("validation exception");
	}
}