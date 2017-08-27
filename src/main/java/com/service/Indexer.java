package com.service;

import java.io.IOException;

import com.model.Document;

public interface Indexer {

	public void runIndex() throws Exception;
	
	public Document getIndexDoc (String doc);
	
	public String getDocument (Document searchDoc) throws IOException;
}
