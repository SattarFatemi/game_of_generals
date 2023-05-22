package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GameFrame extends JFrame {

    public static final int WIDTH_OF_FRAME = 800;
    public static final int HEIGHT_OF_FRAME = 630;

    private Container pane;
    private JLabel background;
    private JPanel board;
    private JLabel selectedPieceLabel;
    private JLabel turnLabel;
    public OutPiecesColumn whiteOutPieceHolder;
    public OutPiecesColumn blackOutPieceHolder;
    private JPanel winingPopup;
    private JLabel winingText;

    public GameFrame() {

        // settings
        setSize(new Dimension(WIDTH_OF_FRAME, HEIGHT_OF_FRAME));
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponents();

        alignComponents();

        setVisible(true);
        revalidate();
        repaint();
    }

    private void initComponents() {

        pane = new JPanel();
        setContentPane(pane);

        winingPopup = new JPanel();

        ImageIcon img = new ImageIcon("src/Assets/board.png");
        background = new JLabel("", img, JLabel.LEFT);

        board = new JPanel();

        selectedPieceLabel = new JLabel("-");
        turnLabel = new JLabel("Black");

        whiteOutPieceHolder = new OutPiecesColumn(GameManagement.whitePlayer);
        blackOutPieceHolder = new OutPiecesColumn(GameManagement.blackPlayer);
    }

    private void alignComponents() {

        pane.setLayout(null);

        background.setBounds(0, -5, 800, 600);

        board.setBounds(150, 69, 500, 500);
        board.setOpaque(false);
        board.setLayout(new GridBagLayout());

        pane.add(winingPopup);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {

                Cell cell = GameManagement.gameBoard.cells[i][j];

                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.gridx = i-1;
                gbc.gridy = 4-(j-1);

                gbc.ipadx = 100;
                gbc.ipady = 100;

                board.add(cell, gbc);
            }
        }

        selectedPieceLabel.setBounds(615, 6, 50, 50);
        selectedPieceLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        selectedPieceLabel.setForeground(Color.WHITE);
        pane.add(selectedPieceLabel);

        turnLabel.setBounds(260, 6, 100, 50);
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        turnLabel.setForeground(Color.WHITE);
        pane.add(turnLabel);

        whiteOutPieceHolder.setBounds(25, 35, 100, 500);
        pane.add(whiteOutPieceHolder);

        blackOutPieceHolder.setBounds(675, 35, 100, 500);
        pane.add(blackOutPieceHolder);

        pane.add(board);
        pane.add(background);
    }

    public void updateFrame() {

        if (GameManagement.selectedPiece != null) selectedPieceLabel.setText(GameManagement.selectedPiece.type + "");
        else selectedPieceLabel.setText("-");

        if (GameManagement.whiteTurn) turnLabel.setText("White");
        else turnLabel.setText("Black");

        whiteOutPieceHolder.reloadColumn();
        blackOutPieceHolder.reloadColumn();

        GameManagement.gameBoard.updateBoard();
    }

    public void gameOver(Player winner) {

        System.out.println("ON MY WAY...");

        //settings
        winingPopup.setLayout(new FlowLayout());
        winingPopup.setOpaque(true);
        winingPopup.setBackground(winner.isWhite ? Color.WHITE : Color.BLACK);
        winingPopup.setBounds(0, 0, 800, 600);

        winingText = new JLabel(winner.isWhite ? "WHITE WINS :)" : "BLACK WINS :)");
        winingText.setFont(new Font("Arial", Font.PLAIN, 32));
        winingText.setForeground(!winner.isWhite ? Color.WHITE : Color.BLACK);
        winingText.setBounds(0, 330, 800, 100);

        winingPopup.add(winingText);

        pane.revalidate();
        pane.repaint();
    }
}
