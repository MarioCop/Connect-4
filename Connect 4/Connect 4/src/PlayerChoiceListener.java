import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerChoiceListener implements ActionListener {

    boolean p2Disabled;
    Window connect4;

    // On button click determined on which button is clicked a game will be launched with either a player 2 or computer
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (p2Disabled)
        {
            connect4 = new Window(true);
        }
        else
        {
            connect4 = new Window(false);
        }
    }

    // constructor initializes p2Disabled boolean flag which will be passed to Window class
    PlayerChoiceListener(boolean p2Disabled)
    {
        this.p2Disabled = p2Disabled;
    }
}
