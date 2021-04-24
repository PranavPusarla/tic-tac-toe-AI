import java.util.*;

public class AI {
    public static void main (String[]args) {
        Board board = new Board();
        Scanner obj = new Scanner(System.in);
        System.out.println("Would you like to play X or O?");
        String playerIcon = obj.nextLine();
        Player player1 = new Player(playerIcon);
        String AIIcon = "";
        if (playerIcon.equals("X")) {
            AIIcon = "O";
        } else {
            AIIcon = "X";
        }
        Player AI = new Player(AIIcon);
        Player[] playerArray = new Player[2];
        if (playerIcon.equals("X")) {
            playerArray[0] = player1;
            playerArray[1] = AI;
        } else {
            playerArray[1] = player1;
            playerArray[0] = AI;
        }
        System.out.println("Ok, you will be playing " + playerIcon + " and the AI will be playing " + AIIcon +".");
        int currentIndex = 0;
        int turnIndex = 1;
        while (!board.gameFinished()) {
            Player currentPlayer = playerArray[currentIndex];
            if (currentPlayer == player1) {
                System.out.println("Where would you like to mark? Answer in row and then col");
                int row = obj.nextInt();
                int col = obj.nextInt();
                while (!board.board[row-1][col-1].equals(" ")) {
                    System.out.println("Please try a different position. That square is already marked.");
                    row = obj.nextInt();
                    col = obj.nextInt();
                }
                board.markSquare(row, col, currentPlayer.icon);
            } else {
                // AI algorithm
                int bestRow = 0;
                int bestCol = 0;
                int max = Integer.MIN_VALUE;
                int maxDepth = Integer.MAX_VALUE;
                for (int i = 0; i < board.numRows(); i++) {
                    for (int j = 0; j < board.numCols(); j++) {
                        if (board.board[i][j].equals(" ")){
                            Board obj1 = board.copy();
                            obj1.markSquare(i+1, j+1, AIIcon);
                            ValDep ans = minimax(AI, player1, obj1, false, 0);
                            //System.out.print(val + " ");
                            if (max < ans.val) {
                                max = ans.val;
                                bestRow = i+1;
                                bestCol = j+1;
                            } else if (max == ans.val) {
                                if (maxDepth > ans.depth) {
                                    maxDepth = ans.depth;
                                    bestRow = i+1;
                                    bestCol = j+1;
                                }
                            }
                        }
                    }
                }
                //System.out.println(max);
                board.markSquare(bestRow, bestCol, currentPlayer.icon);
            }
            System.out.println("Turn "+turnIndex);
            turnIndex++;
            board.showBoard();
            System.out.println();
            currentIndex = 1 - currentIndex;
        }
        if (board.whoWon(player1.icon) == 1) {
            System.out.println("Congratulations! You have beaten the AI!");
        } else if (board.whoWon(player1.icon) == -1) {
            System.out.println("The AI has won! Better luck next time!");
        } else {
            System.out.println("The game has ended in a draw.");
        }
    }

    public static ValDep minimax(Player currentPlayer, Player otherPlayer, Board obj, boolean max, int depth) {
        if (obj.gameFinished()) {
            return new ValDep(obj.whoWon(currentPlayer.icon), depth);
        }
        String[][] board=  obj.board;
        int val = 0;
        if (max) {
            val = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j].equals(" ")) {
                        Board obj1 = obj.copy();
                        obj1.markSquare(i+1,j+1,currentPlayer.icon);
                        ValDep posval = minimax(currentPlayer, otherPlayer, obj1, false, depth+1);
                        val = Math.max(val, posval.val);
                    }
                }
            }
        } else {
            val = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j].equals(" ")) {
                        Board obj1 = obj.copy();
                        obj1.markSquare(i+1,j+1,otherPlayer.icon);
                        ValDep posval = minimax(currentPlayer, otherPlayer, obj1, true, depth+1);
                        val = Math.min(val, posval.val);
                    }
                }
            }
        }
        return new ValDep(val, depth);
    }


}
