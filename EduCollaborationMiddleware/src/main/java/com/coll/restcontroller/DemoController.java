package com.coll.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping("/demo")
	public ResponseEntity<String> showDemo() {
		return new ResponseEntity<String>("Simple Demo", HttpStatus.OK);
	}
}
