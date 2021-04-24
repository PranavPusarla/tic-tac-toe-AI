public class Board {
    String[][] board;
    public Board() {
        board = new String[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = " ";
            }
        }
    }
    public void markSquare(int row, int col, String xo) {
        board[row-1][col-1] = xo;
    }
    public void showBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
                if (j != board.length - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i != board.length - 1) {
                System.out.println("----------");
            }
        }
    }

    public int whoWon(String icon) {
        if (checkColumns(icon) || checkRows(icon) || checkDiagonals(icon)) {
            return 1;
        }
        String otherIcon;
        if (icon.equals("X")) {
            otherIcon = "O";
        } else {
            otherIcon = "X";
        }
        if (checkColumns(otherIcon) || checkRows(otherIcon) || checkDiagonals(otherIcon)) {
            return -1;
        }
        else {
            return 0;
        }
    }

    public boolean gameFinished() {
        if (checkColumns("X") || checkRows("X") || checkDiagonals("X")) {
            return true;
        } else if (checkColumns("O") || checkRows("O") || checkDiagonals("O")) {
            return true;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkColumns() {
        for (int i = 0; i < board.length; i ++) {
            String prev = "";
            boolean colSame = true;
            for (int j = 0; j < board.length; j++) {
                if (j == 0) {
                    prev = board[j][i];
                } else {
                    if (!board[j][i].equals(prev)) {
                        colSame = false;
                    }
                }
            }
            if (colSame) return true;
        }
        return false;
    }

    public boolean checkColumns(String icon) {
        for (int i = 0; i < board.length; i ++) {
            boolean colSame = true;
            for (int j = 0; j < board.length; j++) {
                if (!board[j][i].equals(icon)) {
                    colSame = false;
                }
            }
            if (colSame) return true;
        }
        return false;
    }

    public boolean checkRows() {
        for (int i = 0; i < board.length; i ++) {
            String prev = "";
            boolean rowSame = true;
            for (int j = 0; j < board.length; j++) {
                if (j == 0) {
                    prev = board[i][j];
                } else {
                    if (!board[i][j].equals(prev)) {
                        rowSame = false;
                    }
                }
            }
            if (rowSame) return true;
        }
        return false;
    }

    public boolean checkRows(String icon) {
        for (int i = 0; i < board.length; i ++) {
            boolean rowSame = true;
            for (int j = 0; j < board.length; j++) {
                if (!board[i][j].equals(icon)) {
                    rowSame = false;
                }
            }
            if (rowSame) return true;
        }
        return false;
    }

    public boolean checkDiagonals() {
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            return true;
        }
        if (board[2][0].equals(board[1][1]) && board[2][0].equals(board[0][2])) {
            return true;
        }
        return false;
    }

    public boolean checkDiagonals(String icon) {
        if (board[0][0].equals(icon) && board[1][1].equals(icon) && board[2][2].equals(icon)) {
            return true;
        }
        if (board[2][0].equals(icon) && board[1][1].equals(icon) && board[0][2].equals(icon)) {
            return true;
        }
        return false;
    }

    public int numRows() {
        return board.length;
    }

    public int numCols() {
        return board[0].length;
    }

    public Board copy() {
        Board obj = new Board();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                obj.board[i][j] = board[i][j];
            }
        }
        return obj;
    }
}
