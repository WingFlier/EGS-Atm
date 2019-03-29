package com.egs.atm.enums;

import com.egs.atm.models.bankModels.AmeriaBank;
import com.egs.atm.models.bankModels.IssuerBank;
import com.egs.atm.models.bankModels.EvocaBank;
import com.egs.atm.models.bankModels.HSBCBank;

public enum IssuerBankFactory
{
	AMERIA
			{
				@Override
				public IssuerBank getIssuerBank()
				{
					return AmeriaBank.getInstance();
				}
			},
	HSBC
			{
				@Override
				public IssuerBank getIssuerBank()
				{
					return HSBCBank.getInstance();
				}
			},
	EVOCA
			{
				@Override
				public IssuerBank getIssuerBank()
				{
					return EvocaBank.getInstance();
				}
			};

	public abstract IssuerBank getIssuerBank();
}
