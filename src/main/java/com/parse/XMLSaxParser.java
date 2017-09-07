package com.parse;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.model.Document;

/**
 * Sax parser for xml
 * @author pavansachi
 *
 */
public class XMLSaxParser {

	public void parse(InputStream stream, Document doc) {

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				String name;

				public void startElement(String uri, String localName,String qName,
						Attributes attributes) throws SAXException {

					name = qName;

				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {


				}

				public void characters(char ch[], int start, int length) throws SAXException {

					if (!name.contains("Envelope") && 
							!name.contains("Body")) {

						String s = new String(ch, start, length);

						if (s != null && !s.trim().equals("")) {
							doc.add(name, new String(ch, start, length));
						}

					}

				}

			};

			saxParser.parse(stream, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
