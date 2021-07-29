import java.awt.*;
import javax.swing.*;


public class Window extends JFrame
{
        ImageIcon icon = new ImageIcon("icon/Connect_4_icon.jpg");
        Board board;
        JButton com = new JButton("Computer");
        JButton p2 = new JButton("2 Players");
        PlayerChoiceListener playerListener = new PlayerChoiceListener(true);
        PlayerChoiceListener comListener = new PlayerChoiceListener(false);
        Controller controller;
        Dimension boardSize;
        LayoutManager layoutManager;

        // Game is launched based on which button clicked from initial window
        Window(boolean separator)
        {
                super("Connect 4");
                board = new Board();
                boardSize = new Dimension(board.width(),board.height());
                controller = new Controller(board.margin(),board.segW(),board.segH(),board, separator);
                setSize(boardSize);
                setLocationRelativeTo(null);
                setResizable(false);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setIconImage(icon.getImage());
                add(board);
                board.addMouseListener(controller);
                setVisible(true);
        }

        // Constructor for when there is no parameter
        // Jframe is with 2 buttons will be made the player can click one to decide tha number of players
        Window()
        {
                super("Set the number of Players");
                setIconImage(icon.getImage());
                setSize(400,135);
                setLocationRelativeTo(null);
                setResizable(false);
                setLayout(layoutManager);
                com.setToolTipText("Play Against The Computer");
                p2.setToolTipText("Play with Another Player");
                com.setLocation(0,0);
                p2.setLocation(200,0);
                com.setSize(200,100);
                p2.setSize(200,100);
                com.addActionListener(playerListener);
                p2.addActionListener(comListener);
                add(p2);
                add(com);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
        }
}
