package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JPanel {

    public static final int WIDTH_OF_CELL = 100;
    public static final int HEIGHT_OF_CELL = 100;
    public Piece innerPiece;
    private JLabel pieceImage;
    private JButton button;

    public int x;
    public int y;

    Cell(int x, int y) {

        // settings
        Dimension dimension = new Dimension(WIDTH_OF_CELL, HEIGHT_OF_CELL);
        setSize(dimension);
        setPreferredSize(dimension);
        setLayout(null);
        setOpaque(false);

        this.x = x;
        this.y = y;

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        initComponents();
    }

    public void initComponents() {
        button = new JButton();
        button.setBounds(0, 0, 100, 100);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (GameManagement.selectedPiece == null) {
                    if (innerPiece == null || (innerPiece.isWhite() ^ GameManagement.whiteTurn)) {
                        System.out.println("null / not your turn");
                    }
                    else {
                        GameManagement.setSelectedPiece(innerPiece);
                    }
                }
                else {
                    if (x < 0) {

                        if (innerPiece == null) {
                            System.out.println("OUT!");
                        }
                        else {
                            GameManagement.setSelectedPiece(innerPiece);
                        }

                    }
                    else if (innerPiece == null) {

                        if (GameManagement.selectedPiece.out) {
                            GameManagement.selectedPiece.move(GameManagement.selectedPiece, x, y, GameManagement.gameBoard);
                        }
                        else {
                            GameManagement.selectedPiece.transfer(GameManagement.selectedPiece, x, y, GameManagement.gameBoard);
                        }

                    }
                    else if (innerPiece.isWhite() ^ GameManagement.whiteTurn) {
                        GameManagement.selectedPiece.transfer(GameManagement.selectedPiece, x, y, GameManagement.gameBoard);
                    }
                    else {
                        GameManagement.setSelectedPiece(innerPiece);
                    }
                }

                GameManagement.update();
            }
        });

        button.revalidate();
        button.repaint();
        add(button);

        setVisible(true);
        revalidate();
        repaint();
    }

    public Piece getInnerPiece() {
        return innerPiece;
    }

    public void setPiece(Piece piece) {
        emptyCell();
        innerPiece = piece;
        reloadCell();
    }

    public void emptyCell() {
        innerPiece = null;
        reloadCell();
    }

    public void reloadCell() {

        if (pieceImage != null) remove(pieceImage);

        if (innerPiece == null) {
            ImageIcon img = new ImageIcon("src/Assets/empty.png");
            pieceImage = new JLabel("", img, JLabel.CENTER);
            pieceImage.setBounds(0, 0, 0, 0);
            add(pieceImage);
        }
        else {
            ImageIcon img = new ImageIcon(innerPiece.imageSource);
            pieceImage = new JLabel("", img, JLabel.CENTER);
            pieceImage.setBounds(0, 0, 100, 100);
            add(pieceImage);
        }

        setVisible(true);
        revalidate();
        repaint();
    }
}
