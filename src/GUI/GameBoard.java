package GUI;

public class GameBoard {

    public Cell[][] cells = new Cell[6][6];

    public GameBoard() {
        init();
    }

    public void init() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void addPiece(Piece piece) {
        cells[piece.xPosition][piece.yPosition].setPiece(piece);
    }

    public boolean inside(int x, int y) {
        if (x >= 1 && x <= 5 && y >= 1 && y <= 5) return true;
        return false;
    }

    public void updateBoard() {

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                cells[i][j].reloadCell();
            }
        }

    }
}
