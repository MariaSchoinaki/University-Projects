import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        int length = 0;
        boolean validInput = false;
        Move moveP, moveAI;

        while (!validInput) {
            System.out.print("Choose difficalty level 1-5: ");

            if (in.hasNextInt()) {
                length = in.nextInt();

                if (length >= 1 && length <= 5) {
                    validInput = true;
                } else {
                    System.out.println("\nDifficalty level out of range.\nPlease type a number between 1-5");
                }
            } else {
                in.next();
                System.out.println("\nType error. Please type a valid integer between 1-5");
            }
        }
        int turn = 0;
        validInput = false;

        while (!validInput) {
            System.out.print("\nChoose if you want to play first or second.\nType 1 to play first or 2 to play second: ");
            if (in.hasNextInt()) {
                turn = in.nextInt();
            if(turn == 1 || turn == 2){
                validInput = true;
            } else{
                System.out.println("\nTurn number out of range.\nPlease type a number between 1 and 2");
            }
        } else{
            in.next();
            System.out.println("\nType error. Please type a valid integer between 1 and 2");
        }
    }

        Player AI = ((turn==2) ? new Player(length, Board.B): new Player(length,Board.W));

        
        Board board = new Board();
        board.print();

        while(!board.isTerminal()){
            switch(board.getLastPlayer()){
                case Board.B:
                    if(turn==2) {
                        System.out.println("Its your turn!");
                        if(board.isBlocked(Board.W)){
                            System.out.println("You can't play. It's AI turn again");
                            board.setLastPlayer(Board.W);
                            break;
                        }else{
                           System.out.println("Please select the spot you want to play. Insert first row and then column."); 
                            int r = in.nextInt(); int c = in.nextInt();
                            moveP = new Move(r,c, Board.W);
                            System.out.println("Row: "+ moveP.getRow()+ "   Col: " + moveP.getCol()  );
                            board.makeMove(moveP.getRow(), moveP.getCol(), Board.W);
                        }
                    }else{
                        if(board.isBlocked(Board.W)){
                            System.out.println("AI is blocked! Its your turn.");
                            board.setLastPlayer(Board.W);
                            break;
                        }else{
                            moveAI = AI.MiniMax(board);
                            System.out.println("Row: "+ moveAI.getRow()+ "   Col: " + moveAI.getCol()  );
                            board.makeMove(moveAI.getRow(), moveAI.getCol(), Board.W);
                        }
                    }
                    break;
                case Board.W:
                    if(turn==1){
                        if(board.isBlocked(Board.B)){
                            System.out.println("You are blocked! It's AI's turn now.");
                            board.setLastPlayer(Board.B);
                            break;
                        }else{
                            System.out.println("Its your turn!");
                            System.out.println("Please select the spot you want to play. Insert first row and then column.");
                            int r = in.nextInt(); int c = in.nextInt();
                            moveP = new Move(r,c, Board.B);
                            board.makeMove(moveP.getRow(), moveP.getCol(), Board.B);
                        }
                    }else{
                        if(board.isBlocked(Board.B)){
                            System.out.println("AI is blocked! It's your turn.");
                            board.setLastPlayer(Board.B);
                            break;
                        }else{
                            moveAI = AI.MiniMax(board);
                            System.out.println("Row: "+ moveAI.getRow()+ "   Col: " + moveAI.getCol()  );
                            board.makeMove(moveAI.getRow(), moveAI.getCol(), Board.B);
                        }
                    }
                    break;
            }
            board.print();
            System.out.println("Press enter to continue");
            in.nextLine();
        }
    }
}
