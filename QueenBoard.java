import java.util.*;
public class QueenBoard{
  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
  }

  private boolean addQueen(int r, int c){
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
    if (board[r][c] != -1) return false;
    board[r][c] = 0;
    for (int y = 1; y < board.length - c; y++){
      board[r][c + y] -= 1;
      if (r + y < board.length) board[r + y][c + y] -= 1;
      if (r - y >= 0) board[r - y][c + y] -= 1;
    }
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
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board.length; j++){
        if (board[i][j] != 0) throw new IllegalStateException();
      }
    }
    return this.hSolve(0, 0, new int[board.length]);
  }

  private boolean hSolve(int y, int xinc, int[] queens){
    for (int j = 0 + y; j < board.length; j++){
      for (int i = 0; i < board.length; i++){
        if (j == board.length - 1 && addQueen(i,j)) return true;
        if (j == y) {
          i += xinc;
        }
        if (i < board.length && addQueen(i,j)){
          queens[j] = i;
          j++;
          i = -1;
        }
        else if (i >= board.length - 1){
          if (j == 0){
              board = new int[queens.length][queens.length];
              return false;
          }
          removeQueen(queens[j - 1], j - 1);
          return hSolve(j - 1, queens[j - 1] + 1, queens);
        }
      }
    }
    board = new int[queens.length][queens.length];
    return false;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board.length; j++){
        if (board[i][j] != 0) throw new IllegalStateException();
      }
    }
    int count = 0;
    boolean first = true;
    int[] prev = new int[board.length];
    int[] queens = new int[board.length];
    for (int i = 0; i < board.length; i++){
      if (first){
        if (hSolve(0, i, prev)){
          count++;
          first = false;
          board = new int[board.length][board.length];
        }
        else prev = new int[board.length];
      }
      else {
        if (hSolve(0 , i, queens)){
          boolean same = true;
          for (int x = 0; x < board.length; x++){
            if (prev[x] != queens[x]) same = false;
          }
          if (!same){
            prev = queens;
            queens = new int[board.length];
            count++;
          }
          board = new int[board.length][board.length];
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    QueenBoard a = new QueenBoard(4);
    a.addQueen(0,0);
    for (int i = 0; i < a.board.length; i++){
      System.out.println(Arrays.toString(a.board[i]));
    }
    a.addQueen(2,2);
    System.out.println();
    for (int i = 0; i < a.board.length; i++){
      System.out.println(Arrays.toString(a.board[i]));
    }

    a.removeQueen(2,2);
    System.out.println();
    for (int i = 0; i < a.board.length; i++){
      System.out.println(Arrays.toString(a.board[i]));
    }

  }
}
