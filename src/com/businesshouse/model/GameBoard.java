package com.businesshouse.model;

import java.util.List;

public class GameBoard {

	private int bankMoney;

	private List<BoardCell> cells;

	public int getBankMoney() {
		return bankMoney;
	}

	public void setBankMoney(int bankMoney) {
		this.bankMoney = bankMoney;
	}

	public List<BoardCell> getCells() {
		return cells;
	}

	public void setCells(List<BoardCell> cells) {
		this.cells = cells;
	}

}
