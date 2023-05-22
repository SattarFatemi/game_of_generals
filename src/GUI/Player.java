package GUI;

import java.util.ArrayList;

public class Player {

    public ArrayList<Piece> pieces = new ArrayList<>();
    public boolean isWhite = false;

    public Player(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public void addPiece(Piece piece) {

        if (this.isWhite) {
            piece.type = (char)(piece.type + ('A' - 'a'));

            piece.imageSource = piece.imageSource.substring(0, piece.imageSource.length() - 7) + "w" + piece.imageSource.substring(piece.imageSource.length() - 6);
            System.out.println(piece.imageSource);
        }
        else {
            piece.type = (char)(piece.type - ('A' - 'a'));

            piece.imageSource = piece.imageSource.substring(0, piece.imageSource.length() - 7) + "b" + piece.imageSource.substring(piece.imageSource.length() - 6);
            System.out.println(piece.imageSource);
        }

        pieces.add(piece);

        GameManagement.update();
    }

    public boolean removePiece(Piece piece) {
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).id == piece.id) {
                pieces.remove(i);
                return true;
            }
        }

        return false;
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
