package com.anup.tutorials.sampleserviceaopsecurity.dao.impl;

import org.springframework.stereotype.Component;

import com.anup.tutorials.sampleserviceaopsecurity.dao.CardDao;
import com.anup.tutorials.sampleserviceaopsecurity.dao.entity.CardEntity;

@Component
public class CardDaoImpl implements CardDao {

	@Override
	public void saveCard(CardEntity cardEntity) {
		System.out.println("Card Entity: " + cardEntity);
	}

}
