package com.service;

import java.io.IOException;

import com.model.Document;
import com.model.MockResponse;

public interface Indexer {

	public void runIndex() throws Exception;
	public void runFileIndex(String resource) throws Exception;
	public void runDirectoryIndex(String resource) throws Exception;
	
	public Document getIndexDoc (String doc);
	
	public MockResponse getDocument (Document searchDoc) throws IOException;
}
