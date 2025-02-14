import java.util.ArrayList;
import java.util.Random;

class Player 
{
	private int maxDepth;                //player's choise
    private int playerLetter;            //B or W
	
	public Player() {}
    //constructor
    public Player(int maxDepth, int playerLetter)
    {
        this.maxDepth = maxDepth;
        this.playerLetter = playerLetter;
    }
	
	public Move MiniMax(Board board) {
        if (this.playerLetter == Board.B){
            //If the Black plays then it wants to maximize the heuristics value
            return max(new Board(board), 0);
        }else {
            //If the White plays then it wants to minimize the heuristics value
            return min(new Board(board), 0);
        }
    }
	
	public Move max(Board board, int depth) {
        Random r = new Random();

        if(board.isTerminal() || (depth == this.maxDepth)){ //if the board is on terminal state, or the depth is maximized
            return new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
        }
        
        ArrayList<Board> children = board.getChildren(Board.B); //tree form
        Move maxMove = new Move(Integer.MIN_VALUE);             //first smallest integer, so with the first evaluate move
                                                                //maxMove will have the value of the first possible move
        for(Board child: children){                             //for every possible move that can be maked in the current board
            Move move = min(child, depth + 1);                  //change level(tree) with the min method, so to get the upper evaluation
            
            if(move.getValue() >= maxMove.getValue()){          //if the current move has bigger(or even) value than the temporary max move
                if((move.getValue()) == maxMove.getValue()){    //if they have the same value
                    if(r.nextInt(2) == 0){                //randomly
                        maxMove.setRow(child.getLastMove().getRow());//change the row, col, value of the max move, 
                        maxMove.setCol(child.getLastMove().getCol());//to the row, col, value of the current possible move(child)
                        maxMove.setValue(move.getValue());
                    }                                           //if the if clause is false(random), then the maxMove stays the same
                }else{
                    maxMove.setRow(child.getLastMove().getRow());//change the row, col, value of the max move,
                    maxMove.setCol(child.getLastMove().getCol());//to the row, col, value of the current possible move(child)
                    maxMove.setValue(move.getValue());
                }
            }
        }
        return maxMove;
    }
	
	public Move min(Board board, int depth) {
        Random r = new Random();
        if(board.isTerminal() || (depth == this.maxDepth)){ //if the board is on terminal state, or the depth is maximized
            return new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
        }

        ArrayList<Board> children = board.getChildren(Board.W); //tree form
        Move minMove = new Move(Integer.MAX_VALUE);             //first largest integer, so with the first evaluate move
                                                                //minMove will have the value of the first possible move
        for(Board child: children){                             //for every possible move that can be maked in the current board
            Move move = max(child, depth + 1);                  //change level(tree) with the max method, so to get the upper evaluation

            if(move.getValue() <= minMove.getValue()){          //if the current move has smaller(or even) value than the temporary min move
                if((move.getValue()) == minMove.getValue()){    //if they have the same value
                    if(r.nextInt(2) == 0){                //randomly
                        minMove.setRow(child.getLastMove().getRow());//change the row, col, value of the min move,
                        minMove.setCol(child.getLastMove().getCol());//to the row, col, value of the current possible move(child)
                        minMove.setValue(move.getValue());
                    }                                            //if the if clause is false(random), then the minMove stays the same
                }
                else{
                    minMove.setRow(child.getLastMove().getRow());//change the row, col, value of the min move,
                    minMove.setCol(child.getLastMove().getCol());//to the row, col, value of the current possible move(child)
                    minMove.setValue(move.getValue());
                }
            }
        }
        return minMove;
    }

    public void setMaxDepth(int x){ this.maxDepth = x;}
    
    public void setPlayerLetter(int letter){this.playerLetter = letter;}
}
