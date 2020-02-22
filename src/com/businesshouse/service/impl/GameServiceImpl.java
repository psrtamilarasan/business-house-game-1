package com.businesshouse.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.businesshouse.enums.BoardCellType;
import com.businesshouse.enums.HotelType;
import com.businesshouse.model.BoardCell;
import com.businesshouse.model.GameBoard;
import com.businesshouse.model.Hotel;
import com.businesshouse.model.Player;
import com.businesshouse.service.GameService;

public class GameServiceImpl implements GameService {

	@Override
	public GameBoard setupGameBoard(int numberOfCells, int initialMoney) {
		GameBoard gameBoard = new GameBoard();
		gameBoard.setBankMoney(initialMoney);

		List<BoardCell> cells = new ArrayList<>();
		cells.add(new BoardCell(BoardCellType.J));
		cells.add(new BoardCell(BoardCellType.H, new Hotel(1, "aHotel", HotelType.SILVER)));
		cells.add(new BoardCell(BoardCellType.L));
		cells.add(new BoardCell(BoardCellType.H, new Hotel(2, "bHotel", HotelType.SILVER)));
		cells.add(new BoardCell(BoardCellType.E));
		cells.add(new BoardCell(BoardCellType.L));
		cells.add(new BoardCell(BoardCellType.H, new Hotel(3, "cHotel", HotelType.SILVER)));
		cells.add(new BoardCell(BoardCellType.L));
		cells.add(new BoardCell(BoardCellType.H, new Hotel(4, "dHotel", HotelType.SILVER)));
		cells.add(new BoardCell(BoardCellType.J));
		gameBoard.setCells(cells);
		return gameBoard;
	}

	@Override
	public List<Player> setupPlayers(int numberOfPlayer) {
		List<Player> players = new ArrayList<>();

		players.add(new Player("player-1", 1, 1000, 0, new ArrayList<>()));
		players.add(new Player("player-2", 2, 1000, 0, new ArrayList<>()));
		players.add(new Player("player-3", 3, 1000, 0, new ArrayList<>()));

		return players;
	}

	@Override
	public int playDice(int turn, int playerId) {

		// temp code just to accept given input and play
		Map<String, Integer> tempMap = new HashMap<>();

		tempMap.put("1_1", 2);
		tempMap.put("1_2", 2);
		tempMap.put("1_3", 1);

		tempMap.put("2_1", 4);
		tempMap.put("2_2", 4);
		tempMap.put("2_3", 2);

		tempMap.put("3_1", 4);
		tempMap.put("3_2", 4);
		tempMap.put("3_3", 2);

		tempMap.put("4_1", 2);
		tempMap.put("4_2", 2);
		tempMap.put("4_3", 1);

		tempMap.put("5_1", 4);
		tempMap.put("5_2", 4);
		tempMap.put("5_3", 2);

		tempMap.put("6_1", 4);
		tempMap.put("6_2", 4);
		tempMap.put("6_3", 2);

		tempMap.put("7_1", 2);
		tempMap.put("7_2", 2);
		tempMap.put("7_3", 1);

		String str = "" + turn + "_" + playerId;
		return tempMap.get(str);
	}

	@Override
	public void depositAmoutToBank(GameBoard gameBoard, int amountToDeposit) {
		gameBoard.setBankMoney(gameBoard.getBankMoney() + amountToDeposit);

	}

	@Override
	public void withDrawAmountFromBank(GameBoard gameBoard, int amountToWithdraw) {
		gameBoard.setBankMoney(gameBoard.getBankMoney() - amountToWithdraw);
	}

}
