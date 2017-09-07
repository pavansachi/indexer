package com.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.model.MockResponse;

/**
 * mock service
 * @author pavansachi
 *
 */
@Service
public class MockService {

	Indexer indexer = null;

	@PostConstruct
	public void init() {

		indexer = new XMLIndexer();

		try {
			indexer.runIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public MockResponse process(final String path, final String request) throws Exception {

		return indexer.getDocument(path, request);
		
	}
}
