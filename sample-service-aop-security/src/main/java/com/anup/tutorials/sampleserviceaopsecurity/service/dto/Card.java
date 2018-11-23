package com.anup.tutorials.sampleserviceaopsecurity.service.dto;

public class Card {
	private String cardNumber;
	private String cardHolderName;
	private String cardExpiry; //MMYY
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCardExpiry() {
		return cardExpiry;
	}
	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}
	@Override
	public String toString() {
		return "Card [cardHolderName=" + cardHolderName + "]";
	}
}
