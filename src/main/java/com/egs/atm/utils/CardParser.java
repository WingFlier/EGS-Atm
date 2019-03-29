package com.egs.atm.utils;

import com.egs.atm.enums.CardBrand;
import com.egs.atm.enums.Currency;
import com.egs.atm.enums.IssuerBankFactory;
import com.egs.atm.models.Card;
import com.egs.atm.models.Person;
import com.egs.atm.models.bankModels.IssuerBank;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CardParser implements Parser<List<Person>>
{
	private static Document document;

	@Override
	public List<Person> parse() throws ValidationException, ParseException, ParserConfigurationException, IOException, SAXException
	{
		List<Person> personList = new ArrayList<>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		DocumentBuilder builder = null;

		builder = factory.newDocumentBuilder();
		document = builder.parse(new File("cards.xml"));
		document.getDocumentElement().normalize();

		NodeList persons = document.getElementsByTagName("person");

		for (int j = 0; j < persons.getLength(); j++)
		{
			Node personNode = persons.item(j);
			Element person = (Element) personNode;
			int personId = Integer.parseInt(person.getElementsByTagName("personid")
					.item(0).getTextContent());
			String personName = person.getElementsByTagName("personname")
					.item(0).getTextContent();
			Person person1 = new Person(personId,personName);
			List<Card> cardList = new ArrayList<>();


			NodeList cards = person.getElementsByTagName("card");
			for (int i = 0; i < cards.getLength(); i++)
			{
				Node item = cards.item(i);
				if (item.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) item;
					long pid = Long.valueOf(element.getElementsByTagName("personid")
							.item(0).getTextContent());
					String name = element.getElementsByTagName("cardnumber")
							.item(0).getTextContent();
					CardBrand cardBrand = CardBrand.valueOf(element.getElementsByTagName("cardbrand")
							.item(0).getTextContent());
					Currency currency = Currency.valueOf(element.getElementsByTagName("currency")
							.item(0).getTextContent());
					IssuerBank issuerBank = IssuerBankFactory.valueOf(element.getElementsByTagName("issuerbank")
							.item(0).getTextContent()).getIssuerBank();
					String cardHolder = element.getElementsByTagName("cardholder")
							.item(0).getTextContent();
					String expireDate = element.getElementsByTagName("expiredate")
							.item(0).getTextContent();
//LocalDate of, time
//									Instant.from(DateTimeFormatter.ofPattern("dd/mm/yy").parse("25/12/21"))
//				LocalDate parse = LocalDate.parse(expiredate, DateTimeFormatter.ofPattern("dd/MM/yy"));
//				Instant testInstant = LocalDateTime.of(parse, LocalTime.now()).toInstant(ZoneOffset.MIN);
					Instant expireDateFinal = new SimpleDateFormat("dd/MM/yy").parse(expireDate).toInstant();

					Card build = new Card.CardBuilder()
							.setBank(issuerBank).setPid(pid)
							.setCardNumber(name).setExpireDate(expireDateFinal)
							.setBrand(cardBrand).setCurrencyType(currency)
							.setCardHolder(cardHolder).build();

					cardList.add(build);
				}
			}
			person1.setCardList(cardList);
			personList.add(person1);
		}


		return personList;
	}
}