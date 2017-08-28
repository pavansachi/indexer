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
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Document;
import com.model.MappingResource;
import com.model.MockResponse;
import com.parse.XMLSaxParser;

public class XMLIndexer implements Indexer {

	public XMLIndexer() {
	}

	public XMLIndexer(String rootDir) {
		this.rootDir = rootDir;
	}

	XMLSaxParser parser = new XMLSaxParser();

	List<Document> documents = new ArrayList<>();
	
	List<MappingResource> list = null;

	String rootDir;

	@Override
	public void runFileIndex(String directory) throws FileNotFoundException, IOException {

		Resource resource = new ClassPathResource(directory);
		File file = resource.getFile();

		Document doc = new Document(file.getName());

		parser.parse(new FileInputStream(file), doc);

		documents.add(doc);
	}

	@Override
	public void runDirectoryIndex(String directory) throws FileNotFoundException, IOException {

		Resource resource = new ClassPathResource(directory);
		File fileDir = resource.getFile();

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

	public MockResponse getDocument (Document searchDoc) throws IOException {

		Document retDoc = null;

		for (Document doc: documents) {

			if (searchDoc.compareTo(doc) == 0) {

				retDoc = doc;
				break;
			}
		}

		if (retDoc != null) {

			final String docName = retDoc.getName();
			
			MappingResource mRes = list.stream().filter(r -> {
				
				MappingResource res = r;
				
				if (res.getRequest().getResource().equals(docName)) {
					
					return true;
				}
				return false;
			}).findFirst().get();
			
			Resource resource = new ClassPathResource("docs/responses/" + mRes.getResponse().getResource());

			List<String> lines = Files.readAllLines(Paths.get(resource.getURI())); 

			String response = lines.stream().collect(Collectors.joining(""));

			return new MockResponse(response, mRes.getResponse().getStatus());
		}

		return null;
	}

	@Override
	public void runIndex() throws Exception {

		Resource resource = new ClassPathResource("docs/mapping.json");

		ObjectMapper mapper = new ObjectMapper();
		try {
			list = mapper.readValue(resource.getInputStream(), 
					new TypeReference<List<MappingResource>>() {
			});

			for (MappingResource res: list) {

				try {
					runFileIndex("docs/requests/" + res.getRequest().getResource());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
