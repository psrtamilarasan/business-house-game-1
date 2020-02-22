package com.businesshouse.model;

import java.util.List;

public class Player {

	private String playerName;
	private int amount;
	private int playerId;
	private int currentCellLocation;

	private List<Hotel> hotels;

	public Player(String playerName, int playerId, int amount, int currentCellLocation, List<Hotel> hotels) {
		super();
		this.playerName = playerName;
		this.amount = amount;
		this.currentCellLocation = currentCellLocation;
		this.hotels = hotels;
		this.playerId = playerId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public int getCurrentCellLocation() {
		return currentCellLocation;
	}

	public void setCurrentCellLocation(int currentCellLocation) {
		this.currentCellLocation = currentCellLocation;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
}
