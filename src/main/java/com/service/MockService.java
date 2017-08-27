package com.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.model.Document;

@Service
public class MockService {

	Indexer indexer = null;

	@PostConstruct
	public void init() {

		indexer = new XMLIndexer("docs");
		try {
			indexer.runIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String process(String request) throws IOException {

		Document searchDoc = indexer.getIndexDoc(request);

		System.out.println(searchDoc);
		
		return indexer.getDocument(searchDoc);

	}
}
