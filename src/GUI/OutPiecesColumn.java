package GUI;

import javax.swing.*;
import java.awt.*;

public class OutPiecesColumn extends JPanel {

    public Player player;
    public Cell[] cells = new Cell[5];

    public OutPiecesColumn(Player player) {

        // settings
        Dimension dimension = new Dimension(100, 500);
        setSize(dimension);
        setPreferredSize(dimension);
        setLayout(null);
        setOpaque(false);

        this.player = player;

        for (int i = 0; i < 5; i++) {
            cells[i] = new Cell(player.isWhite ? -1 : -2, 0);
            cells[i].setBounds(0, (4 - i) * 100, 100, 100);
            add(cells[i]);
        }

        reloadColumn();
    }

    public void reloadColumn() {

        for (int i = 0; i < 5; i++) {

            if (i < player.pieces.size()) {
                cells[i].setPiece(player.pieces.get(i));
            }
            else {
                cells[i].emptyCell();
            }

            cells[i].reloadCell();
        }
    }
}
