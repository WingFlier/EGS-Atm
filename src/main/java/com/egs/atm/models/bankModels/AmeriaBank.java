package com.egs.atm.models.bankModels;

import com.egs.atm.models.Account;
import com.egs.atm.services.BankService;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.ValidationException;

public class AmeriaBank extends IssuerBank
{
	private static volatile AmeriaBank instance = new AmeriaBank();

	public static synchronized AmeriaBank getInstance()
	{
		return instance;
	}

	@Override
	public void addAccount(long pId, Account account)
	{
		super.addAccount(pId, account);
	}

	private AmeriaBank()
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
