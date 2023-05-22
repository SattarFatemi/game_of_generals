package Game;

public class GameBoard {

    public Piece[][] cells = new Piece[6][6];

    public void addPiece(Piece piece) {
        cells[piece.xPosition][piece.yPosition] = piece;
    }

    public boolean inside(int x, int y) {
        if (x >= 1 && x <= 5 && y >= 1 && y <= 5) return true;
        return false;
    }
}
