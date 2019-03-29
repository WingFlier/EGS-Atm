package com.egs.atm.models.bankModels;

import com.egs.atm.models.Account;
import com.egs.atm.services.BankService;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.ValidationException;

public class HSBCBank extends IssuerBank
{
	private static volatile HSBCBank instance = new HSBCBank();

	public static synchronized HSBCBank getInstance()
	{
		return instance;
	}

	@Override
	public void addAccount(long pId, Account account)
	{
		super.addAccount(pId, account);
	}

	private HSBCBank()
	{
		super();
	}

	@Override
	public BankService getService()
	{
		return super.getService();
	}

	@Override
	public HashMap<Long, ArrayList<Account>> getAccounts()
	{
		return super.getAccounts();
	}

	@Override
	public void validate() throws ValidationException
	{
		super.validate();
	}
}
