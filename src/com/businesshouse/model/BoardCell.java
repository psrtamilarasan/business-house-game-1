package com.businesshouse.model;

import com.businesshouse.enums.BoardCellType;

public class BoardCell {
	private BoardCellType cellType;
	private Hotel hotel;

	public BoardCell(BoardCellType cellType, Hotel hotel) {
		super();
		this.cellType = cellType;
		this.hotel = hotel;
	}

	public BoardCell(BoardCellType cellType) {
		super();
		this.cellType = cellType;
	}

	public BoardCellType getCellType() {
		return cellType;
	}

	public void setCellType(BoardCellType cellType) {
		this.cellType = cellType;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}
