package com.egs.atm.models.bankModels;

import com.egs.atm.enums.IssuerBankFactory;
import com.egs.atm.models.Account;
import com.egs.atm.services.BankService;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.ValidationException;

public class EvocaBank extends IssuerBank
{
	private static volatile EvocaBank instance = new EvocaBank();

	public static synchronized EvocaBank getInstance()
	{
		return instance;
	}

	@Override
	public void addAccount(long pId, Account account)
	{
		super.addAccount(pId, account);
	}

	private EvocaBank()
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
