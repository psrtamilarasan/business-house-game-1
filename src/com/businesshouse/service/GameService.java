package com.businesshouse.service;

import java.util.List;

import com.businesshouse.model.GameBoard;
import com.businesshouse.model.Player;

public interface GameService {

	public GameBoard setupGameBoard(int numberOfCells, int initialMoney);

	public List<Player> setupPlayers(int numberOfPlayer);

	public int playDice(int turn, int playerId);

	public void depositAmoutToBank(GameBoard gameBoard, int amountToDeposit);

	public void withDrawAmountFromBank(GameBoard gameBoard, int amountToWithdraw);


}
