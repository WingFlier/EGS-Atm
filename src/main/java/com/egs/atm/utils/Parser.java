package com.egs.atm.utils;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

import javax.xml.bind.ValidationException;
import javax.xml.parsers.ParserConfigurationException;

public interface Parser<T extends Iterable>
{
	T parse() throws ValidationException, ParseException, ParserConfigurationException, IOException, SAXException;
}
