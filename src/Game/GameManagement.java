package Game;

import java.util.Scanner;

public class GameManagement {

    public static GameBoard gameBoard;
    public static boolean finished = false;
    public static boolean whiteTurn = true;
    public static Player whitePlayer;
    public static Player blackPlayer;

    public static void start() {
        gameBoard = new GameBoard();
        whitePlayer = new Player(true);
        blackPlayer = new Player(false);
        restart();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            whiteTurn = !whiteTurn;

            //System.out.println(whiteTurn ? "white true" : "black turn");

            Piece selectedPiece;
            char c = scanner.next().charAt(0);

            if (c == '0') {
                //FINISHED
                break;
            }

            int fromX = scanner.nextInt();
            int fromY = (fromX % 10);
            fromX = (fromX / 10);

            int toX = scanner.nextInt();
            int toY = (toX % 10);
            toX = (toX / 10);

            //Checked: System.out.println(c + " " + fromX + "," + fromY + " " + toX + "," + toY);

            if (!gameBoard.inside(toX, toY)) {
                printGameboard();
                whiteTurn = !whiteTurn;
                continue;
            }

            // bekhad az biroon biare
            if (fromX == 0 && fromY == 0) {

                if (gameBoard.cells[toX][toY] == null) {

                    if (whiteTurn) {
                        selectedPiece = whitePlayer.removePiece(c);
                        if (selectedPiece != null) {
                            selectedPiece.xPosition = toX;
                            selectedPiece.yPosition = toY;
                            addPiece(selectedPiece);
                            continue;
                        }
                    }
                    else {
                        selectedPiece = blackPlayer.removePiece(c);
                        if (selectedPiece != null) {
                            selectedPiece.xPosition = toX;
                            selectedPiece.yPosition = toY;
                            addPiece(selectedPiece);
                            continue;
                        }
                    }
                }

                printGameboard();
                whiteTurn = !whiteTurn;
                continue;
            }

            if (!gameBoard.inside(fromX, fromY)) {
                printGameboard();
                whiteTurn = !whiteTurn;
                continue;
            }

            if (gameBoard.cells[fromX][fromY] != null) {

                // type ok hast ya na
                if (gameBoard.cells[fromX][fromY].type != c) {
                    printGameboard();
                    whiteTurn = !whiteTurn;
                    continue;
                }

                selectedPiece = gameBoard.cells[fromX][fromY];

                //TODO
                //System.out.println("SELECTED: " + selectedPiece.type);

                // nobat ok hast ya na
                if (selectedPiece.isWhite() ^ whiteTurn) {
                    //TODO
                    //System.out.println("IT'S NOT YOUR TURN!");
                    printGameboard();
                    whiteTurn = !whiteTurn;
                    continue;
                }

                if (!selectedPiece.transfer(selectedPiece, toX, toY, gameBoard)) whiteTurn = !whiteTurn;
            }
            else {
                printGameboard();
                whiteTurn = !whiteTurn;
                continue;
            }

            printGameboard();
            if (finished) System.out.println(whiteTurn ? "white wins!" : "black wins!");
        }
    }

    private static void printGameboard() {

        //TODO
        /*for (int j = 5; j >= 1; j--) {
            for (int i = 1; i <= 5; i++) {
                if (gameBoard.cells[i][j] == null) System.out.print('-');
                else System.out.print(gameBoard.cells[i][j].type);
            }
            System.out.println();
        }*/

        for (int j = 1; j <= 5; j++) {
            for (int i = 1; i <= 5; i++) {
                if (gameBoard.cells[i][j] == null) System.out.print('-');
                else System.out.print(gameBoard.cells[i][j].type);
            }
        }
        System.out.println();

        for (int i = 0; i < blackPlayer.pieces.size(); i++) {
            System.out.print(blackPlayer.pieces.get(i).type);
        }
        System.out.println();
        for (int i = 0; i < whitePlayer.pieces.size(); i++) {
            System.out.print(whitePlayer.pieces.get(i).type);
        }
        System.out.println();

    }

    private static void restart() {

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                gameBoard.cells[i][j] = null;
            }
        }

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
        //TODO
        //System.out.println(piece.type + " ADDED!");
    }

    public static void removePiece(Piece piece) {
        gameBoard.cells[piece.xPosition][piece.yPosition] = null;
        piece.xPosition = 0;
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
}
