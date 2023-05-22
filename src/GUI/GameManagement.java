package GUI;

import java.util.Scanner;

public class GameManagement {

    public static GameBoard gameBoard;
    public static boolean finished = false;
    public static boolean whiteTurn = false;
    public static Piece selectedPiece;
    public static Player whitePlayer;
    public static Player blackPlayer;
    public static GameFrame gameFrame;

    public static void start() {
        gameBoard = new GameBoard();
        whitePlayer = new Player(true);
        blackPlayer = new Player(false);
        gameFrame = new GameFrame();
        restart();
    }

    private static void restart() {

        Piece piece;
        piece = new Piece(1, 1, false, false, 'K', 1);
        addPiece(piece);
        piece = new Piece(2, 1, false, false, 'G', 2);
        addPiece(piece);
        piece = new Piece(3, 1, false, false, 'S', 3);
        addPiece(piece);
        piece = new Piece(4, 1, false, false, 'B', 4);
        addPiece(piece);
        piece = new Piece(5, 1, false, false, 'L', 5);
        addPiece(piece);
        piece = new Piece(1, 2, false, false, 'P', 6);
        addPiece(piece);

        piece = new Piece(5, 5, false, false, 'k', 7);
        addPiece(piece);
        piece = new Piece(4, 5, false, false, 'g', 8);
        addPiece(piece);
        piece = new Piece(3, 5, false, false, 's', 9);
        addPiece(piece);
        piece = new Piece(2, 5, false, false, 'b', 10);
        addPiece(piece);
        piece = new Piece(1, 5, false, false, 'l', 11);
        addPiece(piece);
        piece = new Piece(5, 4, false, false, 'p', 12);
        addPiece(piece);
    }

    public static void addPiece(Piece piece) {
        piece.out = false;
        piece.upgraded = false;
        gameBoard.addPiece(piece);
    }

    public static void removePiece(Piece piece) {
        gameBoard.cells[piece.xPosition][piece.yPosition].emptyCell();

        if (piece.isWhite()) piece.xPosition = -2;
        else piece.xPosition = -1;
        piece.yPosition = 0;

        piece.upgraded = false;
        piece.out = true;

        if (piece.isWhite()) {
            blackPlayer.addPiece(piece);
        }
        else {
            whitePlayer.addPiece(piece);
        }
    }

    public static void update() {
        gameFrame.updateFrame();
    }

    public static void setSelectedPiece(Piece piece) {
        selectedPiece = piece;
    }
}
