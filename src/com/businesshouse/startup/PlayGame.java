package com.businesshouse.startup;

import java.util.List;

import com.businesshouse.enums.BoardCellType;
import com.businesshouse.enums.HotelType;
import com.businesshouse.exception.InsufficientFundsException;
import com.businesshouse.model.BoardCell;
import com.businesshouse.model.GameBoard;
import com.businesshouse.model.Hotel;
import com.businesshouse.model.Player;
import com.businesshouse.service.GameService;
import com.businesshouse.service.impl.GameServiceImpl;

public class PlayGame {
	// Assumptions :
	// player will move in left direction
	// Start point for every player will be same

	static GameService gameService = new GameServiceImpl();

	public static void main(String[] args) {

		GameBoard gameBoard = gameService.setupGameBoard(10, 5000);
		List<BoardCell> cells = gameBoard.getCells();
		List<Player> players = gameService.setupPlayers(3);

		// give 7 turns to each player
		for (int turn = 1; turn <= 7; turn++) {
			for (Player player : players) {
				int currentLocation = player.getCurrentCellLocation();
				int diceNum = gameService.playDice(turn, player.getPlayerId());
				System.out.println(player.getPlayerName() + " is at " + currentLocation + " diceNum : " + diceNum);
				int targetCellIndex = currentLocation + diceNum;

				// because we have total (10) 0-9 cells
				if (targetCellIndex > 9) {
					// for Circular
					targetCellIndex = targetCellIndex - 9;
				}

				System.out.println("Target cell is " + targetCellIndex);
				player.setCurrentCellLocation(targetCellIndex);

				BoardCell targetCell = cells.get(targetCellIndex);
				processCellOperation(player, gameBoard, targetCell);

			}

		}

		// print result
		players.forEach(player -> {
			int assetAmount = getAssetAmount(player.getHotels());
			System.out.println(player.getPlayerName() + " has total money " + player.getAmount()
					+ " and asset of amount " + assetAmount);
		});

	}

	private static int getAssetAmount(List<Hotel> hotels) {
		int amount = 0;
		if (!hotels.isEmpty()) {
			for (Hotel hotel : hotels) {
				amount = amount + hotel.getHotelType().getValue();

			}
		}
		return amount;
	}

	private static void processCellOperation(Player player, GameBoard gameBoard, BoardCell targetCell) {

		switch (targetCell.getCellType()) {
		case J:
			player.setAmount(player.getAmount() - BoardCellType.J.getValue());
			gameService.depositAmoutToBank(gameBoard, BoardCellType.J.getValue());
			break;
		case H:
			// it must have hotelType here
			Hotel hotel = targetCell.getHotel();
			Player hotelOwner = hotel.getHotelOwner();
			HotelType hotelType = hotel.getHotelType();
			if (null != hotelOwner) {
				if (player.getPlayerId() == hotel.getHotelOwner().getPlayerId()) {
					// I am the owner
					upgradeHotel(player, gameBoard, hotel, hotelType);
				} else {
					// someone else is owner
					payRent(player, hotelOwner, hotelType);
				}
			} else {
				// you are free to buy this hotel
				buyHotel(player, gameBoard, hotel, hotelType);
			}

			break;
		case L:
			gameService.withDrawAmountFromBank(gameBoard, BoardCellType.J.getValue());
			player.setAmount(player.getAmount() + BoardCellType.L.getValue());
			break;
		case E:
			/* do nothing here */
			break;
		default:
			break;
		}

	}

	private static void buyHotel(Player player, GameBoard gameBoard, Hotel hotel, HotelType hotelType) {
		if (player.getAmount() >= hotelType.getValue()) {
			gameService.depositAmoutToBank(gameBoard, hotelType.getValue());
			player.setAmount(player.getAmount() - hotelType.getValue());
			// now current player owns it CASE : B
			hotel.setHotelOwner(player);
			player.getHotels().add(hotel);
		} else {
			throw new InsufficientFundsException("You don't have enough money to buy this hotel");
		}
	}

	private static void payRent(Player player, Player hotelOwner, HotelType hotelType) {
		// owned by some other player CASE : D (Pay Rent)
		if (player.getAmount() >= hotelType.getRent()) {
			hotelOwner.setAmount(hotelOwner.getAmount() + hotelType.getRent());
			player.setAmount(player.getAmount() - hotelType.getRent());
		} else {
			throw new InsufficientFundsException("You don't have enough money to pay rent");

		}
	}

	private static void upgradeHotel(Player player, GameBoard gameBoard, Hotel hotel, HotelType hotelType) {
		// owned by current player CASE : C (Landed on my own hotel so upgrade it)
		if (hotelType.PLATINUM != hotel.getHotelType()) {
			HotelType upgradeToType = getNextUpgrade(hotel.getHotelType());
			int deltaToPay = upgradeToType.getValue() - hotel.getHotelType().getValue();
			player.setAmount(player.getAmount() - deltaToPay);
			gameService.depositAmoutToBank(gameBoard, deltaToPay);
		} else {
			System.out.println("Platinum can't be upgraded");
		}
	}

	private static HotelType getNextUpgrade(HotelType hotelType) {
		switch (hotelType) {
		case SILVER:
			return HotelType.GOLD;
		case GOLD:
			return HotelType.PLATINUM;
		default:
			break;
		}
		return null;
	}
}
