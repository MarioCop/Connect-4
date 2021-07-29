
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Controller manages the logic and mouse clicks for the connect 4 game
public class Controller extends MouseAdapter
{

    private int margin, segW, segH, column, row, pieceX, pieceY;
    private int controller[][];
    private boolean color , turn, p2Disabled,winner;
    Board board;
    Computer computer;

    //Constructor initializes all variable and fills ever slot in the 2d array with -1
    Controller(int margin, int segW, int segH, Board board,boolean p2Disabled)
    {
        this.margin = margin;
        this.segW = segW;
        this.segH = segH;
        this.board = board;
        this.p2Disabled = p2Disabled;
        pieceX = 0;
        pieceY = 0;
        controller = new int[7][6];
        color = false;
        turn = false;
        winner = false;
        computer = new Computer();
        for(int col = 0; col < 7; col++)
        {
            for(int r = 0; r < 6; r++)
            {
                controller[col][r] = -1;
            }
        }
        System.out.println("Red's Turn:");
    }


    // If the mouse click is outside the connect 4 board then nothing is done
    // If the the click is inside the board then the the x coordinate is used to calculate the column the click was in
    // The move function is given the column as a parameter
    // If player 2 is disabled the computers move is performed immediately if the last move did not result in a win
    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {
        if (mouseEvent.getX() < margin || mouseEvent.getY() < margin)
            return;
        if (mouseEvent.getX() > segW*8 - margin || mouseEvent.getY() > segH*7 - margin)
            return;
        ClickToColumn(mouseEvent.getX());
        if(!winner)move(column);
        if(!winner && p2Disabled)computerMove();
    }

    // takes the x coordinate of the click and returns the column number of the the column that was clicked
    private void ClickToColumn(int x)
    {
        column = (x - margin)/segW;
        if ( column == 7){column = 6;}
    }

    // Will move conduct a move for the computer  if the move was not valid the move will be repeated until a valid move is made
    private void computerMove()
    {
        do move(computer.nextMove());
        while (computer.wasInvalidMove(turn));
    }

    // The function will check if the column that was clicked has an open space available
    // If the column does not have an open space the move is not preformed and all flags are left unchanged
    // A the place piece function is called and the board is updated
    // The Game state is check to see if a winner is decided
    private void move(int column)
    {
        if (!color)
        {
            for (int r = 0; r < 6; r++)
            {
               if(controller[column][r] != 0 && controller[column][r] != 1)
               {
                   controller[column][r] = 0;
                   System.out.println(column +", " + r);
                   turn = true;
                   row = r;
                   pieceLocation(column,row);
                   break;
               }
            }
            if (turn)
            {
                checkGameState(row,column);
                if (winner)
                {
                    System.out.println("Red is the Winner: ");
                    board.printWinner("RED");
                }
                else
                {
                    color = true;
                    System.out.println("Yellow's Turn:");
                }
            }

        }
        else
        {
            for (int r = 0; r < 6; r++)
            {
                if(controller[column][r] != 0 && controller[column][r] != 1)
                {
                    controller[column][r] = 1;
                    System.out.println(column +", " + r);
                    turn = false;
                    row = r;
                    pieceLocation(column,row);
                    break;
                }
            }
            if (!turn)
            {
                checkGameState(row,column);
                if (winner)
                {
                    System.out.println("Yellow is the Winner: ");
                    board.printWinner("YELLOW");
                }
                else
                {
                    color = false;
                    System.out.println("Red's Turn:");
                }
            }
        }
    }

    //given the column and row the x and y coordinates are calculated for where to place the piece image
    private void pieceLocation(int column,int row)
    {
        pieceX = margin + segW * column;
        pieceY = margin + segH * (5 - row);
        placePiece(pieceX,pieceY,color);
    }

    // call board placePiece function and places the corresponding colored piece
    private void placePiece(int x,int y,boolean color)
    {
        board.placePiece(x,y,color);
    }

    // check the game state horizontally vertically and diagonally
    private void checkGameState(int row, int column)
    {
        verticalCheck(column);
        horizontalCheck(row);
        diagonalCheck1(row,column);
        diagonalCheck2(row,column);
    }

    // logic for the vertical check
    // The entire column of the placed piece is checked and if four matching pieces are there winner flag is triggered
    private void verticalCheck(int column)
    {
        int matching = 0;
        for(int row = 0; row < 5;row++)
        {
            if (controller[column][row] == controller[column][row + 1] && controller[column][row] != -1)
            {
                matching++;
            }
            else {matching = 0;}
            if (matching == 3)
            {
                winner = true;
                break;
            }
        }
    }

    // logic for horizontal check
    // The entire row of the placed piece is checked then the winner flag is triggered
    private void horizontalCheck(int row)
    {
        int matching = 0;
        for (int column = 0; column < 6; column++)
        {
           if (controller[column][row] == controller[column + 1][row] && controller[column][row] != -1)
           {
               matching++;
           }
           else{matching = 0;}
           if (matching == 3)
           {
               winner = true;
               break;
           }
        }
    }

    // The first half of the diagonal check
    private void diagonalCheck1(int row, int column)
    {
        int matching = 0;
        int tempCol = column,tempRow = row;
        while (tempCol > 0 && tempRow > 0)
        {
            if (controller[tempCol][tempRow] == controller[tempCol - 1][tempRow - 1] && controller[tempCol][tempRow] != -1)
            {
                tempCol--;
                tempRow--;
                matching++;
                if (matching == 3)
                {
                    winner = true;
                    break;
                }
            }
            else break;
        }
        tempCol = column; tempRow = row;

        while (tempCol < 6 && tempRow < 5)
        {
            if (controller[tempCol][tempRow] == controller[tempCol + 1][tempRow + 1] && controller[tempCol][tempRow] != -1)
            {
                tempCol++;
                tempRow++;
                matching++;

                if (matching == 3)
                {
                    winner = true;
                    break;
                }
            }
            else break;
        }
    }

    //second half of the diagonal check
    private void diagonalCheck2(int row,int column)
    {
        int matching = 0;
        int tempCol = column,tempRow = row;
        while (tempCol > 0 && tempRow < 5)
        {
            if (controller[tempCol][tempRow] == controller[tempCol - 1][tempRow + 1] && controller[tempCol][tempRow] != -1)
            {
                tempCol--;
                tempRow++;
                matching++;
                if (matching == 3)
                {
                    winner = true;
                    break;
                }
            }
            else break;
        }
        tempCol = column; tempRow = row;

        while (tempCol < 6 && tempRow > 0)
        {
            if (controller[tempCol][tempRow] == controller[tempCol + 1][tempRow - 1] && controller[tempCol][tempRow] != -1)
            {
                tempCol++;
                tempRow--;
                matching++;

                if (matching == 3)
                {
                    winner = true;
                    break;
                }
            }
            else break;
        }
    }
}
