package Algorithm;

public class MinMax {
    public int[] computerTurn(int[][] board) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j] == 0) {
                    //System.out.println("Checking at "+i+" "+j);
                    board[i][j] = -1;
                    int score = miniMax(board,0, false);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                    board[i][j] = 0;
                }
            }
        }
        return bestMove;
    }
    private int miniMax(int[][] board,int depth,boolean flag)
    {
        int result=checkWinner(board);
        if(result!=0)
        {
            if(result==-1)
            {
                return 10;
            }
            else if(result==2)
            {
                return 0;
            }
            else
            {
                return -10;
            }
        }
        int bestScore = Integer.MIN_VALUE, i, j;
        if (flag) {

            for ( i = 0; i < 3; i++) {
                for ( j = 0; j < 3; j++) {
                    // Is the spot available?
                    if (board[i][j] == 0) {
                        board[i][j] = -1;
                        int score = miniMax(board,depth + 1, false);
                        board[i][j] = 0;
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {

            for ( i = 0; i < 3; i++) {
                for ( j = 0; j < 3; j++) {
                    // Is the spot available?
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                        int score = miniMax(board,depth + 1, true);
                        board[i][j] = 0;
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }

    }

    //Checks winning condition in the internal Board
    private int checkWinner(int[][] board) {

        int winner = 1;


        for (int i = 0; i < 3; i++) {
            if (equals3(board[i][0], board[i][1], board[i][2])) {
                winner = board[i][0];
            }
        }


        for (int i = 0; i < 3; i++) {
            if (equals3(board[0][i], board[1][i], board[2][i])) {
                winner = board[0][i];
            }
        }

        // Diagonal
        if (equals3(board[0][0], board[1][1], board[2][2])) {
            winner = board[0][0];
        }
        if (equals3(board[2][0], board[1][1], board[0][2])) {
            winner = board[2][0];
        }
        //Find If place remains in board
        int openSpots = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    openSpots++;
                }
            }
        }

        if (winner == 0 && openSpots == 0) {
            return 2;
        } else {
            return winner;
        }
    }


    private boolean equals3(int a, int b, int c) {
        return a == b && b == c && a != 0;
    }
}