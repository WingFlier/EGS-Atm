package com.egs.atm;
//82826-90730

import com.egs.atm.models.Account;
import com.egs.atm.models.Atm;
import com.egs.atm.models.Card;
import com.egs.atm.models.Person;
import com.egs.atm.services.AtmService;
import com.egs.atm.utils.AccountParser;
import com.egs.atm.utils.CardParser;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main
{

	public static void main(String[] args)
	{
		try
		{
			List<Person> people = new CardParser().parse();
			List<Account> accounts = new AccountParser().parse();
			linkCardsToAccounts(people, accounts);
			addAccountsToBanks(accounts);

			Atm atm = new Atm(150000);
			AtmService atmService = atm.startService();

			ExecutorService executor = Executors.newFixedThreadPool(5);

			for (int i = 0; i < 5000; i++)
			{

				Thread thread = new Thread(() -> {
					try
					{
						Random random = new Random();
						Person person = people.get(random.nextInt(people.size()));
						int rand = (random.nextInt(10000) + 1000) / 1000 * 1000;
						Card card = person.getCardList().get(random.nextInt(person.getCardList().size()));
						System.out.println(card.getCardHolder() + "  " + atmService.putCard(card, rand));
					} catch (Exception e)
					{
						System.err.println(e.getMessage());
						System.exit(-1);
					}
				});
				executor.execute(thread);
			}
			executor.shutdown();
		} catch (Exception e)
		{
			System.err.println(e.getMessage());
			System.exit(-1);
		}
	}

	private static void addAccountsToBanks(List<Account> accounts)
	{
		accounts.forEach(account -> account.getBank().addAccount(account.getHolderId(), account));
	}

	private static void linkCardsToAccounts(List<Person> people, List<Account> accounts)
	{
		people.forEach(person ->
				person.getCardList().stream().filter(card -> accounts.stream().anyMatch(account ->
				{
					if (account.getHolderId() == card.getPid()
							&& account.getCardNumber().equals(card.getCardNumber()))
					{
						account.setCard(card);
						return true;
					}
					return false;
				})).count());
		System.out.println();
	}
}