/*This class represents the board of the game. It's contained, the basic methods of how to do the minimax algorithm and how a move influences the current board.  */
import java.util.ArrayList;

class Board {

	public static final int W = 1;
    public static final int B = -1;
    public static final int EMPTY = 0;

    private int[][] gameBoard;

    private int lastPlayer;

    private Move lastMove;
	
	private int dimension;
	
/* Empty constractor. 
It's used for the initialization of the game. 
No current move is made here. 
Just the beggining board with the 4 checkers in the middle.*/
	public Board() { 
        this.lastMove = new Move();
        this.lastPlayer = W;                            //black always starts first, so we put as last player white
        this.dimension = 8;                             //standard othello-reversi board
        this.gameBoard = new int[dimension][dimension];

        for(int row = 0; row < this.dimension; row++) {
            for(int col = 0; col < this.dimension; col++) {
                this.gameBoard[row][col] = EMPTY;       //new game starts with empty table except from the middle ones
            }
        }                                               //the middle pawns are placed in a cross pattern as shown below
        this.gameBoard[3][3] = B;                       // ___|___|___|___|___|___|___|___| 
        this.gameBoard[3][4] = W;                       // ___|___|___|_B_|_W_|___|___|___|
        this.gameBoard[4][3] = W;                       // ___|___|___|_W_|_B_|___|___|___|
        this.gameBoard[4][4] = B;                       // ___|___|___|___|___|___|___|___|   
    }
	
/* Copy constractor. 
It's used to make the possible moves a player can made(getChildren(int value)). 
It copies the current board, to not destroy it so to make the first possible move, 
if it's valid, flipping the checkers of the opponent so to be added to the list
with the possible board moves without touching the og board. */ 
    public Board(Board board) {
        this.lastMove = board.lastMove;
        this.lastPlayer = board.lastPlayer;
        this.dimension = board.dimension;
        this.gameBoard = new int[this.dimension][this.dimension];
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0;j < this.dimension; j++) {
                this.gameBoard[i][j] = board.gameBoard[i][j];
            }
        }
    }

/* It's printing the values of our table with a mini animation(boarders, colours etc.). 
It's made just to be eazier for the player to understand the board.*/    
	public void print() {
        for(int row = 0; row < this.dimension; row++) {
            System.out.print("\u001B[42;42m|\u001B[0m");                //
            for(int col = 0; col < this.dimension; col++) {
                switch (this.gameBoard[row][col]) {
                    case W:
                        System.out.print("\u001B[30;47m 2 \u001B[0m");  //code for white square 
                        break; 
                    case B:
                        System.out.print("\u001B[97;40m 1 \u001B[0m");  //code for black square 
                        break;
                    case EMPTY:
                    System.out.print("\u001B[42;42m__|\u001B[0m");      //code for green square
                }
            }
            System.out.println();
        }
    }

/* It's creating a list that contains all the possible boards after, making a possible 
valid move, flipping the right opponents checkers. Used in the minimax algo.*/
	ArrayList<Board> getChildren(int value) {
        ArrayList<Board> children = new ArrayList<>();
        for(int row = 0; row < this.gameBoard.length; row++) {
            for(int col = 0; col < this.gameBoard.length; col++) {
                if(this.isValid(row, col, value)) {                 //if at the specific spot (row,col) the movement is valid
                    Board child = new Board(this);                  //make a new board, copy of the current one
                    child.makeMove(row, col, value);                //make the move on the new board
                    children.add(child);                            //add the new board to the list of children
                }
            }
        }
        return children;
    }

/*It's the heuristic that evaluates each piece of the board*/
	public int evaluate () {
        int scoreB = 0;                                                 //score for the Black player
        int scoreW = 0;                                                 //score for the White player
        
        if(isBlocked(W)) scoreB+=800;                                   //moves that blocks the opponent,
        if(isBlocked(B)) scoreW+=800;                                   //gives mobility to the player

        scoreB+=(availableMoves(W).size())*(-50);                       //the minimum the possible moves of the opponent,
        scoreW+=(availableMoves(B).size())*(-50);                       //the better mobility fore the player

        for(int row = 0; row < this.dimension; row++) {
            for(int col = 0; col < this.dimension; col++) {                
                //edges
                if((row == 0 || row == 7) && (col == 0 || col == 7)){   //edge pieces are the most important
                    if(this.gameBoard[row][col] == B){                  //so we give great value if a player has
                        scoreB += 1000;                                 //claimed a corner
                        if(this.gameBoard[row][col+(int)Math.pow(-1, col)] == B) scoreB += 300; //under, up, left or right of the
                        if(this.gameBoard[row+(int)Math.pow(-1, row)][col] == B) scoreB += 300; //edge piece(corner)
                    }else if(this.gameBoard[row][col] == W){
                        scoreW += 1000;
                        if(this.gameBoard[row][col+(int)Math.pow(-1, col)] == W) scoreW += 300; //chain of claimed pieces
                        if(this.gameBoard[row+(int)Math.pow(-1, row)][col] == W) scoreW += 300; //stability
                    }
                //limits
                }else if(((row == 0 || row == 7) && (col >=1 && col <=6)) || ((row >= 1 && row <= 6) && (col == 0 || col == 7))) {
                    if(this.gameBoard[row][col] == B) scoreB += 100;   //the second most important is the limits of the 
                    if(this.gameBoard[row][col] == W) scoreW += 100;   //board, so we give an average value
                }    
            }
        }
        scoreB+=getScores()[0]*10;                                      //every spot of the board
        scoreW+=getScores()[1]*10;                                      //we value it as ten points

        return scoreB - scoreW;                                         //final value of the board is the difference of of the scores of the two players
    }
	
/*Wether a state is terminal or not. To wit the game is finished, if a player has no 
checkers left in the table to make a move, or if the board is full.          */
	public boolean isTerminal() {
        int sumB = 0;                                   //black checkers in the board
        int sumW = 0;                                   //white checkers in the board 
        int sumE = 0;                                   //empty squares in the board 

        if(isBlocked(B) && isBlocked(W)) return true;   //if no one can make a possible move, the game is over
        
        for (int i = 0; i < this.dimension; i++) {
            for(int j = 0; j < this.dimension; j++) {
                if(this.gameBoard[i][j] == W) {
                    sumW++;                             //increase the number of white pawns
                }else if(this.gameBoard[i][j] == B) {
                    sumB++;                             //increase the number of black pawns
                }else {
                    sumE++;                             //increase the number of empty spots
                }
            }
        }
        if( sumB == 0 || sumW == 0 || (sumE == 0)) {    //if there are no available spots or one of the players
            return true;                                //has no pawns on the board, the game has ended              
        }                                               
        return false;
    }
/*It's checking whether the player with color "value" can make at least one move to 
the board. If he can't he is blocked and loses his turn.*/
    public boolean isBlocked(int value){
        boolean isblocked = true;
        for (int i = 0; i < this.dimension; i++) {
            for(int j = 0; j < this.dimension; j++) {
                if(isValid(i, j, value)) isblocked = false;     //if there is even one valid move then the player 
            }                                                   //is not blocked
        }
        return isblocked;
    }

/*It's changing the board so that the current board must have the current move that 
just had been made. The move must be valid or else a message is printed.      */
    void makeMove(int row, int col, int value) {
        if (isValid(row, col, value)) {
            this.gameBoard[row][col] = value;    //it is valid and the player makes a move in the spot [row,col]
            this.lastMove = new Move(row, col);  //store last players move
            this.lastPlayer = value;             //store last players value
            flipcheckers(row, col, value);
        }else {
            System.out.println("Try again with a valid move such as an empty square that follows the game rules:");
            System.out.println("Vertically, horizontally or diagonally 2 of your checkers enclose 1 or more of the opponent's checkers");
        }
    }

/*It's checking wether a move is valid or not. If it's an empty square that encloses 
1 or more of the opponent's checkers at at least one of the 8 directions.    */
    public boolean isValid(int row, int col, int value) {
        boolean islegal = false;                                        //assume the move is not valid
        int opponent = -1 * value;                                      //opponent is the opposite value of the player
        int x = row;
        int y = col;
    //Checks if the square is empty
        if(inBoarders(row, col) && this.gameBoard[row][col] != EMPTY) { //if the move is out of the board or the spot is taken
            return false;
        }
    //Checks if the move follows the game rules

        //left horizontically
        while(inBoarders(x, y - 1) && this.gameBoard[x][y - 1] == opponent) {
            y--;
            if(inBoarders(x, y - 1) && this.gameBoard[x][y - 1] == EMPTY) break;
            if(inBoarders(x, y - 1) && this.gameBoard[x][y - 1] == value ) {
                islegal = true;
                break;
            }    
        }
        //right horizontically
        y = col;
        while(inBoarders(x, y + 1) && this.gameBoard[x][y + 1] == opponent) {
            y++;
            if(inBoarders(x, y + 1) && this.gameBoard[x][y + 1] == EMPTY) break;
            if(inBoarders(x, y + 1) && this.gameBoard[x][y + 1] == value ) {
                islegal = true;
                break;
            }    
        }
        //up vertically
        y = col;
        while(inBoarders(x - 1, y) && this.gameBoard[x - 1][y] == opponent) {
            x--;
            if(inBoarders(x - 1, y) && this.gameBoard[x - 1][y] == EMPTY) break;
            if(inBoarders(x - 1, y) && this.gameBoard[x - 1][y] == value) {
                islegal = true;
                break;
            }    
        }
        //down vertically
        x = row;
        while(inBoarders(x + 1, y) && this.gameBoard[x + 1][y] == opponent) {
            x++;
            if(inBoarders(x + 1, y) && this.gameBoard[x + 1][y] == EMPTY) break;
            if(inBoarders(x + 1, y) && this.gameBoard[x + 1][y] == value ) {
                islegal = true;
                break;
            }    
        }
        //left up diagonally
        x = row;
        while(inBoarders(x - 1, y - 1) && this.gameBoard[x - 1][y - 1] == opponent) {
            x--;
            y--;
            if(inBoarders(x - 1, y - 1) && this.gameBoard[x - 1][y - 1] == EMPTY) break;
            if(inBoarders(x - 1, y - 1) && this.gameBoard[x - 1][y - 1] == value ) {
                islegal = true;
                break;
            }    
        }
        //right up diagonally
        x = row;
        y = col;
        while(inBoarders(x - 1, y + 1) && this.gameBoard[x - 1][y + 1] == opponent) {
            x--;
            y++;
            if(inBoarders(x - 1, y + 1) && this.gameBoard[x - 1][y + 1] == EMPTY) break;
            if(inBoarders(x - 1, y + 1) && this.gameBoard[x - 1][y + 1] == value ) {
                islegal = true;
                break;
            }    
        }
        //left down diagonally
        x = row;
        y = col;
        while(inBoarders(x + 1, y - 1) && this.gameBoard[x + 1][y - 1] == opponent) {
            x++;
            y--;
            if(inBoarders(x + 1, y - 1) && this.gameBoard[x + 1][y - 1] == EMPTY) break;
            if(inBoarders(x + 1, y - 1) && this.gameBoard[x + 1][y - 1] == value ) {
                islegal = true;
                break;
            }    
        }
        //right down diagonally
        x = row;
        y = col;
        while(inBoarders(x + 1, y + 1) && this.gameBoard[x + 1][y + 1] == opponent) {
            x++;
            y++;
            if(inBoarders(x + 1, y + 1) && this.gameBoard[x + 1][y + 1] == EMPTY) break;
            if(inBoarders(x + 1, y + 1) && this.gameBoard[x + 1][y + 1] == value ) {
                islegal = true;
                break;
            }    
        }
        return islegal;
    }

/*Checks if a move that's trying to be made is inside the 
boarders of the game board (table from 0 to 7 rows and 0 to 7 columns).*/
    Boolean inBoarders(int row, int col) {

        if(row < 0 || row > 7 || col < 0 || col > 7) return false;
        return true;
    }

/*It's changing the board when a valid move so that the enclosing chackers of the 
opponents flip and change value to the value of the player that made the move.  */
    void flipcheckers(int row, int col, int value){
        int opponent = -1 * value;

        //up vertically
        int x = row - 1; //direction is up so it is previous row
        int y = col;    //direction is up so same column

        while (x > 0 && this.gameBoard[x][y] == opponent) {  //while we are still within the table's bounds and the checker belongs to the opponent
            if (this.gameBoard[x - 1][y] == value){          //if the checker is one of our own then we have found the limit where we can flip the checkers
                while(this.gameBoard[x][y] == opponent){     //while there are still checkers of our opponent within our limit
                    this.gameBoard[x][y] = value;            //flip the checker
                    x++;                                     //go to next row
                }
            }
            x--;                                             //go to previous row
        }

        //down vertically
        x = row + 1;  //direction is down so it is next row
        y = col;    //direction is down so same column
        while (x < 7 && this.gameBoard[x][y] == opponent){
            if (this.gameBoard[x+1][y] == value){           //we have found the player's pawn, that is the limit where we can flip
                while(this.gameBoard[x][y] == opponent){    //while there are still pawns within the limit
                    this.gameBoard[x][y] = value;           //flip the pawn
                    x--;                                    //go to previous row
                }
            }
            x++;                                            //go to next row
        }

        //right horizontally
        x = row; //direction is right so it is same row
        y = col + 1; //direction is right so it is next column
        while (y < 7 && this.gameBoard[x][y] == opponent){
            if (this.gameBoard[x][y+1] == value){
                while(this.gameBoard[x][y] == opponent){
                    this.gameBoard[x][y] = value;
                    y--;                                    //go to previous column
                }
            }
            y++;                                            //go to next column
        }

        //left horizontally
        x = row; //direction is left so it is same row
        y = col - 1; //direction is left so it is previous column
        while (y > 0 && this.gameBoard[x][y] == opponent){
            if (this.gameBoard[x][y-1] == value){
                while(this.gameBoard[x][y] == opponent){
                    this.gameBoard[x][y] = value;
                    y++;                                    //go to next column
                }
            }
            y--;                                            //go to previous column
        }

        //left up diagonally
        x = row - 1; //direction is up left so it is previous row
        y = col - 1; //direction is up left so it is previous column
        while(x > 0 && y > 0 && this.gameBoard[x][y] == opponent){
            if (this.gameBoard[x-1][y-1] == value){
                while(this.gameBoard[x][y] == opponent){
                    this.gameBoard[x][y] = value;
                    y++;                                    //go to next column
                    x++;                                    //go to next row
                }
            }
            y--;                                            //go to previous column
            x--;                                            //go to previous row
        }

        //right up diagonally
        x = row - 1; //direction is up right so it is previous row
        y = col + 1; //direction is up right so it is next column
        while(x > 0 && y < 7 && this.gameBoard[x][y] == opponent){
            if (this.gameBoard[x-1][y+1] == value){
                while(x > 0 && y > 0 && this.gameBoard[x][y] == opponent){
                    this.gameBoard[x][y] = value;
                    y--;                                    //go to previous column
                    x++;                                    //go to next row
                }
            }
            y++;                                            //go to next column
            x--;                                            //go to previous row
        }

        //left down diagonally
        x = row + 1; //direction is down left so it is next row
        y = col - 1; //direction is downt so it is previous column
        while( x < 7 && y > 0 && this.gameBoard[x][y] == opponent){
            if (this.gameBoard[x+1][y-1] == value){
                while(x < 7 && y > 0 && this.gameBoard[x][y] == opponent){
                    this.gameBoard[x][y] = value;
                    y++;                                    //go to next column
                    x--;                                    //go to previous row
                }
            }
            y--;                                            //go to previous column
            x++;                                            //go to next row
        }

        //right down horizontally
        x = row + 1; //direction is down right so it is next row
        y = col + 1; //direction is down right so it is next column
        while(x < 7 && y < 7 && this.gameBoard[x][y] == opponent){
            if (this.gameBoard[x+1][y+1] == value){
                while(x < 7 && y < 7 && this.gameBoard[x][y] == opponent){
                    this.gameBoard[x][y] = value;
                    y--;                                    //go to previous column
                    x--;                                    //go to previous row
                }
            }
            y++;                                            //go to previous column
            x++;                                            //go to next row
        }

    }

   
    ArrayList<Move> availableMoves(int color){
        ArrayList<Move> availableMoves = new ArrayList<Move>();

        for(int row = 0; row < this.gameBoard.length; row++) {
            for(int col = 0; col < this.gameBoard.length; col++) {
                if(this.isValid(row, col, color)) {                
                    Move move = new Move(row,col,color);  
                    availableMoves.add(move);
                }
            }
        }

        return availableMoves;
    }

    int[] getScores(){
        int scoreB = 0, scoreW = 0;
        for(int i = 0; i < this.dimension; i++){
            for(int j = 0; j < this.dimension; j++){
                switch (this.gameBoard[i][j]) {
                    case B:
                        scoreB+=1;
                        break;
                    case W:
                        scoreW+=1;
                        break;
                }
            }
        }
        return new int[] {scoreB, scoreW};
    }


	void setGameBoard(int[][] gameBoard) {
        for(int i = 0; i < this.dimension; i++) {
            for(int j = 0; j < this.dimension; j++) {
                this.gameBoard[i][j] = gameBoard[i][j];
            }
        }
    }

    void setLastMove(Move lastMove) {
        this.lastMove.setRow(lastMove.getRow());
        this.lastMove.setCol(lastMove.getCol());
        this.lastMove.setValue(lastMove.getValue());
    }

    void setLastPlayer(int lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    public Move getLastMove() {
        return this.lastMove;
    }

    public int getLastPlayer() {
        return this.lastPlayer;
    }

    public int[][] getGameBoard() {
        return this.gameBoard;
    }

    public int getGameDimension(){
        return this.dimension;
    }
}