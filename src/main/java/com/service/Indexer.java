package com.service;

import java.util.List;
import java.util.Map;

import com.model.Document;
import com.model.MappingResource;
import com.model.MockResponse;

/**
 * indexer interface to index any type of file
 * @author pavansachi
 *
 */
public interface Indexer {

	/**
	 * run index on a source of files
	 * @throws Exception
	 */
	public void runIndex() throws Exception;
	
	/**
	 * runs index on a single file
	 * @param resource
	 * @throws Exception
	 */
	public void runFileIndex(String resource) throws Exception;
	
	/**
	 * mappings
	 * @return
	 * @throws Exception
	 */
	public Map<String , List<MappingResource>> getMappings() throws Exception;
	
	/**
	 * get a document for a string based input 
	 * @param doc
	 * @return
	 */
	public Document getIndexDoc (String doc);
	
	/**
	 * search the document in index
	 * @param searchDoc
	 * @return
	 * @throws Exception
	 */
	public MockResponse getDocument (Document searchDoc) throws Exception;
	
	/**
	 * search document based on a path and input data
	 * @param path
	 * @param sourceRequest
	 * @return
	 * @throws Exception
	 */
	public MockResponse getDocument (final String path, final String sourceRequest) throws Exception;
}
