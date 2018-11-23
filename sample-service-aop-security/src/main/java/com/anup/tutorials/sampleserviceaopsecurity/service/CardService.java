package com.anup.tutorials.sampleserviceaopsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anup.tutorials.sampleserviceaopsecurity.dao.CardDao;
import com.anup.tutorials.sampleserviceaopsecurity.dao.entity.CardEntity;
import com.anup.tutorials.sampleserviceaopsecurity.service.dto.Card;

@RestController
public class CardService {

	@Autowired
	CardDao cardDaoImpl;
	
	@RequestMapping(method=RequestMethod.POST, consumes= {"application/json"}, path="/card")
	public void createCard(@RequestBody Card card) {
		CardEntity cardEntity = new CardEntity();
		cardEntity.setCardHolderName(card.getCardHolderName());
		cardEntity.setCardNumber(card.getCardNumber());
		cardEntity.setExpiry(card.getCardExpiry());
		
		cardDaoImpl.saveCard(cardEntity);
		System.out.println("After save trying to print Card Number: " + cardEntity.getCardNumber());
	}
}
