package com.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.model.Document;
import com.parse.XMLSaxParser;

public class XMLIndexer implements Indexer {

	public XMLIndexer(String rootDir) {
		this.rootDir = rootDir;
	}

	Properties props = new Properties();

	XMLSaxParser parser = new XMLSaxParser();

	List<Document> documents = new ArrayList<>();

	String rootDir;
	
	@Override
	public void runIndex() throws FileNotFoundException, IOException {

		Resource resource = new ClassPathResource(rootDir + "/mapping.properties");
		
		props.load(new FileInputStream(resource.getFile()));

		System.out.println(props);
		
		Resource resource2 = new ClassPathResource(rootDir);
		File fileDir = resource2.getFile();

		FilenameFilter filter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("xml");
			}
		};

		for (File file: fileDir.listFiles(filter)) {

			Document doc = new Document(file.getName());

			parser.parse(new FileInputStream(file), doc);

			documents.add(doc);
		}
	}
	
	public Document getIndexDoc (String doc) {

		Document document = new Document();

		parser.parse(new ByteArrayInputStream(doc.getBytes()), document);

		return document;
	}

	public String getDocument (Document searchDoc) throws IOException {

		Document retDoc = null;

		for (Document doc: documents) {

			if (searchDoc.compareTo(doc) == 0) {

				retDoc = doc;
				break;
			}
		}

		if (retDoc != null) {
			
			Resource resource = new ClassPathResource(rootDir + "/responses/" + props.getProperty(retDoc.getName()));
			
			List<String> lines = Files.readAllLines(Paths.get(resource.getURI())); 

			String response = lines.stream().collect(Collectors.joining(","));

			return response;
		}

		return null;
	}

}
