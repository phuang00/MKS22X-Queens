import java.util.*;
public class QueenBoard{
  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
  }

  public boolean addQueen(int r, int c){
    if (board[r][c] != 0) return false;
    board[r][c] = -1;
    for (int y = 1; y < board.length - c; y++){
      board[r][c + y] += 1;
      if (r + y < board.length) board[r + y][c + y] += 1;
      if (r - y >= 0) board[r - y][c + y] += 1;
    }
    return true;
  }

  private boolean removeQueen(int r, int c){
    return true;
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String ans = "";
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board.length; j++){
        if (board[i][j] == -1) ans += "Q";
        else ans += "_";
        if (j == board.length - 1 && i != board.length - 1) ans += "\n";
        else ans += " ";
      }
    }
    return ans;
  }


  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    return true;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    return -1;
  }

  public static void main(String[] args) {
    QueenBoard a = new QueenBoard(4);
    a.addQueen(3,0);
    for (int i = 0; i < a.board.length; i++){
      System.out.println(Arrays.toString(a.board[i]));
    }
  }
}
