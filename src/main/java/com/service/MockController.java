package com.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String mock(@RequestBody String request){

		try {
			return service.process(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "No match"; // fault
	}

}
