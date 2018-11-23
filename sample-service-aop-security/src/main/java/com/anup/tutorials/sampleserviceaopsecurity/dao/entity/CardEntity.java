package com.anup.tutorials.sampleserviceaopsecurity.dao.entity;

import com.anup.tutorials.sampleserviceaopsecurity.security.EnableSecure;

public class CardEntity {
	private String cardNumber;
	private String cardHolderName;
	private String expiry;//MMYY
	
	@EnableSecure
	public String getCardNumber() {
		return cardNumber;
	}
	
	@EnableSecure
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	@Override
	public String toString() {
		return "CardEntity [cardNumber=" + cardNumber + ", cardHolderName=" + cardHolderName + ", expiry=" + expiry
				+ "]";
	}
}
