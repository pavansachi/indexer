//package com;
//
//import com.model.Document;
//import com.service.Indexer;
//import com.service.XMLIndexer;
//
//public class IndexerTest {
//
//	public static void main(String[] args) throws Exception {
//
//		Indexer indexer = new XMLIndexer("data/docs");
//		indexer.runIndex();
//
//		//		String xmlReq = "<?xml version=\"1.0\"?>" + 
//		//		"<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://www.w3.org/2001/12/soap-envelope\" SOAP-ENV:encodingStyle=\"http://www.w3.org/2001/12/soap-encoding\" >" +
//		//			"<SOAP-ENV:Body xmlns:m=\"http://www.xyz.org/quotations\" >" +
//		//				"<m:GetQuotation>" +
//		//					"<m:QuotationsName>MicroSoft</m:QuotationsName>" +
//		//				"</m:GetQuotation>" +
//		//			"</SOAP-ENV:Body>" +
//		//		"</SOAP-ENV:Envelope>";
//
//		String xmlReq = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +  
//				"<soap:Envelope "+
//				"  xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"" + 
//				" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" + 
//				" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"+
//				" <soap:Body>" +
//				" <GetCustomerInfo xmlns=\"http://tempUri.org/\">"+
//				" <CustomerID>1</CustomerID>"+ 
//				" <OutputParam /> "+
//				" </GetCustomerInfo> "+
//				" </soap:Body>" +
//				" </soap:Envelope>";
//
//		Document searchDoc = indexer.getIndexDoc(xmlReq);
//
//		System.out.println(indexer.getDocument(searchDoc));
//	}
//}
