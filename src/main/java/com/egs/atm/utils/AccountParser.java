package com.egs.atm.utils;

import com.egs.atm.enums.IssuerBankFactory;
import com.egs.atm.models.Account;
import com.egs.atm.models.bankModels.IssuerBank;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class AccountParser implements Parser<List<Account>>
{
	private static Document document;

	@Override
	public List<Account> parse()
	{
		List<Account> accList = new ArrayList<>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		DocumentBuilder builder = null;
		try
		{
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File("accounts.xml"));
			document.getDocumentElement().normalize();
			NodeList cards = document.getElementsByTagName("account");

			for (int i = 0; i < cards.getLength(); i++)
			{
				Node item = cards.item(i);
				if (item.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) item;
					long pid = Long.valueOf(element.getElementsByTagName("holderid")
							.item(0).getTextContent());
					int balance = Integer.valueOf(element.getElementsByTagName("balance")
							.item(0).getTextContent());
					IssuerBank issuerbank = IssuerBankFactory.valueOf(element.getElementsByTagName("issuerbank")
							.item(0).getTextContent()).getIssuerBank();
					String cardnumber = element.getElementsByTagName("cardnumber")
							.item(0).getTextContent();
					accList.add(new Account(pid, balance, issuerbank, cardnumber));
				}
			}
			return accList;
		} catch (ParserConfigurationException | SAXException | IOException | ValidationException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}