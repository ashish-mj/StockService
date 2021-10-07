package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.StockQuote;
import com.example.service.StockService;

@RestController
public class StockController {
	
	
	@Autowired
	StockService service;
	
	@GetMapping("/quotes")
	public List<StockQuote> getAllQuotes(){
		return service.getAllQuotes();
	}
	
	
	@GetMapping("/quotes/{num}")
	public StockQuote getQuote(@PathVariable("num") int num){
		return service.getQuote(num);
	}
	
	@GetMapping("/pieceofcake")
	public String someEasyToComputeInfo() {
		return "zzzzzzzzzzzzz";
	}

}