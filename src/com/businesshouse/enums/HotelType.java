package com.businesshouse.enums;

public enum HotelType {

	SILVER(200, 50), GOLD(300, 150), PLATINUM(500, 300);
	int value;
	int rent;

	private HotelType(int value, int rent) {
		this.value = value;
		this.rent = rent;
	}

	public int getValue() {
		return value;
	}

	public int getRent() {
		return rent;
	}
}
