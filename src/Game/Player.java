package Game;

import java.util.ArrayList;

class Player {

    public ArrayList<Piece> pieces = new ArrayList<>();
    public boolean isWhite = false;

    public Player(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public void addPiece(Piece piece) {
        if (this.isWhite) {
            piece.type = (char)(piece.type + ('A' - 'a'));
        }
        else {
            piece.type = (char)(piece.type - ('A' - 'a'));
        }
        pieces.add(piece);
    }

    public Piece removePiece(char type) {
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).type == type) {
                Piece thePiece = pieces.get(i);
                pieces.remove(i);
                return thePiece;
            }
        }

        return null;
    }
}
