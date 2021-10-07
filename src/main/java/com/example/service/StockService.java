package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.domain.StockQuote;

@Service
public class StockService {

	@Value("${delay.per.stock}")
	int delayPerStock;
	
	@Value("${number.of.stocks}")
	int numberOfStocks;
	
	List<StockQuote> stockList = new ArrayList<>();
	
	Random r = new Random();
	
	@PostConstruct
	public void init() {
		
		for(int i = 1;i <= numberOfStocks; i++) {
			int randomWhole = r.nextInt(1000);
			float randomFraction = ((float) r.nextInt(100))/10; 
			StockQuote s = new StockQuote("Stock"+i, (randomWhole+randomFraction));
			stockList.add(s);
		}
	}
	
	public List<StockQuote> getAllQuotes(){
		List<StockQuote> l = new ArrayList<>();
		for(int i=0;i<numberOfStocks;i++) {
			l.add(getQuote(i));
		}
		
		return l;
	}
	
	
	public StockQuote getQuote(int i) {
		StockQuote s = stockList.get(i);
		randomPriceChange(s);
		sleep(delayPerStock);
		return s;
	}


	private void randomPriceChange(StockQuote s) {
		float deltaChange = ((r.nextFloat() * 5 )/100)*s.getPrice();
		if(r.nextBoolean()) {
			s.setPrice(s.getPrice()+deltaChange);
		}else {
			s.setPrice(s.getPrice() - deltaChange);
		}
		
	}


	private void sleep(int delayPerStock2) {
		try {
			Thread.sleep(delayPerStock2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
