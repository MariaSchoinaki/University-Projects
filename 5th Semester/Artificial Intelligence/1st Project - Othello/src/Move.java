public class Move
{
    private int row;        // x-cordinate
    private int col;        // y-cordinate
    private int value;      //value of cell defines the pawn color

/* Empty constractor. 
It's used for the initialization of the initial pawn position representation. 
Sets the initial cordinates of each element.*/
    Move()
    {
        this.row = -1;       //x=-1
        this.col = -1;       //y=-1
        this.value = 0;      // empty cell
    }
/* Move constractor. 
It's used for the initialization of the curent pawn position. */
    Move(int row, int col)
    {
        this.row = row;
        this.col = col;
        this.value = -1;
    }
/* Move constractor. 
It's used for the initialization of curent value of the cell. */
    Move(int value)
    {
        this.row = -1;
        this.col = -1;
        this.value = value;
    }
/* Move constractor. 
Here every value changes in order to define how the pawn will appear on the board. */
    Move(int row, int col, int value)
    {
        this.row = row;
        this.col = col;
        this.value = value;
    }
/* get Function. 
returns the x-cordinate of a board element. */
    int getRow()
    {
        return this.row;
    }
/* get Function. 
returns the y-cordinate of a board element. */
    int getCol()
    {
        return this.col;
    }
/* get Function. 
returns the value of a board element. */
    int getValue()
    {
        return this.value;
    }
/* set Function. 
sets the x-cordinate of a board element. */
    void setRow(int row)
    {
        this.row = row;
    }
/* set Function. 
sets the y-cordinate of a board element. */
    void setCol(int col)
    {
        this.col = col;
    }
/* set Function. 
sets the value of a board element. */
    void setValue(int value)
    {
        this.value = value;
    }
}
