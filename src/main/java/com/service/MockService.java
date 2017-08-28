package com.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.model.Document;
import com.model.MockResponse;

@Service
public class MockService {

	Indexer indexer = null;

	@PostConstruct
	public void init() {

		// read the mapping json
		
		indexer = new XMLIndexer();
		
		try {
			indexer.runIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public MockResponse process(String request) throws IOException {

		Document searchDoc = indexer.getIndexDoc(request);

		System.out.println(searchDoc);
		
		return indexer.getDocument(searchDoc);

	}
}
