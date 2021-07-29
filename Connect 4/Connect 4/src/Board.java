import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.net.URL;

// The Board Class is used to manage the visuals within the connect 4 game
public class Board extends Canvas
{
    private static final int margin = 50,height = 800, width = 900,segW = width/8,segH = height/8; // board dimensions
    boolean UserChoice; //
    BufferedImage redPiece = getImage("/image/red_piece.png"); //red piece image
    BufferedImage yellowPiece = getImage("/image/yellow_piece.png"); // yellow piece image
    BufferedImage boardTemplate=getImage("/image/Connect_4_Board.png"); // board template image
    Color color = new Color (0,200,255); // board color (under board image)
    Graphics2D graphics2D;

    Board() { } // no action needed in constructor

    // automatically invoked casts graphics to graphics2d and calls draw function
    @Override
    public void paint(Graphics graphics)
    {
        graphics2D = (Graphics2D) graphics;
        draw(graphics2D);
    }

    // sets background and draws a frame board that is underneath board image
    private void draw(Graphics2D graphics2D)
    {
        setBackground(Color.black);
        graphics2D.setColor(color);
        for (int col = 0; col < 8;col++)
        {
            graphics2D.draw(new Line2D.Float(margin+segW*col,margin,margin+segW*col,margin+segH*6));
        }
        for (int row = 0; row < 7; row++)
        {
            graphics2D.draw(new Line2D.Float(margin,margin+segH*row,segW*7+margin,margin+segH*row));
        }
            graphics2D.drawImage(boardTemplate,margin,margin,segW*7,segH*6,this);
    }

    //takes x and y as well as a boolean to place a piece of proper color at the correct coordinate
    public void placePiece(int x,int y, boolean color)
    {
        Graphics G = getGraphics();
        if (!color)
        {
            G.drawImage(redPiece, x, y, segW, segH, this);
        }
        else
        {
            G.drawImage(yellowPiece,x,y,segW,segH,this);
        }
    }

    // when a winner is decided text is printed in the respective players color
    public void printWinner(String winner)
    {
        Graphics g = getGraphics();
        if(winner == "RED") g.setColor(Color.RED);
        else g.setColor(Color.YELLOW);
        g.setFont(new Font("serif", Font.ITALIC, 25));
        g.drawString(winner + " is the Winner",50,40);
    }

    // given a string path as a parameter an image will be stored if found
    private BufferedImage getImage(String path)
    {
        BufferedImage tempHolder = null;
        try
        {
            tempHolder= ImageIO.read(getClass().getResourceAsStream(path));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
     return tempHolder;
    }

    // getter functions to get all variables
    public int height() {return height;}
    public int width() {return width;}
    public int margin() {return margin;}
    public int segW() {return segW;}
    public int segH() {return segH;}
}
