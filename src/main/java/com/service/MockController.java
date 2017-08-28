package com.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.MockResponse;

@Controller
public class MockController {

	@Autowired
	private MockService service;

	@RequestMapping(value="test", method=RequestMethod.GET)
	@ResponseBody
	public String works(){

		return "works";
	}

	@RequestMapping(value="mock", method=RequestMethod.POST, consumes="application/xml",
			produces="application/xml")
	@ResponseBody
	public ResponseEntity<String> mock(@RequestBody String request){

		try {
			
			MockResponse response = service.process(request);
			return new ResponseEntity<String>(response.getData(), HttpStatus.valueOf(response.getStatus()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
