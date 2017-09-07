package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.MockResponse;
import com.model.SearchRequest;
import com.model.SearchResponse;

/**
 * mock controller to expose the endpoints
 * @author pavansachi
 *
 */
@Controller
public class MockController {

	@Autowired
	private MockService service;

	@RequestMapping(value="test", method=RequestMethod.GET)
	@ResponseBody
	public String works(){

		return "works";
	}

	@RequestMapping(value="/{base}", method=RequestMethod.POST, consumes="application/xml",
			produces="application/xml")
	@ResponseBody
	public ResponseEntity<String> mock(@RequestBody String request, @PathVariable("base") String basePath){

		try {
			
			MockResponse response = service.process(basePath, request);
			return new ResponseEntity<String>(response.getData(), HttpStatus.valueOf(response.getStatus()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value="structured-search", method=RequestMethod.POST, consumes="application/json",
			produces="application/json")
	@ResponseBody
	public ResponseEntity<SearchResponse> search(@RequestBody SearchRequest search){

		return null;
	}

}
