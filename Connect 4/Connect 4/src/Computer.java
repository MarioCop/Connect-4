import java.util.Random;

public class Computer extends Random
{
    Computer() {

    } // no action needed

    // returns a random number 0 - 6 to decide which column to place the piece in
    public int nextMove()
    {
        int next = nextInt(7);
        return next;
    }

    // check if the move was valid (redundant)
    public boolean wasInvalidMove(boolean valid)
    {
        if (valid == false)return false;
        else return true;
    }


}
