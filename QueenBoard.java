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
    return helpS(0, 0, new int[board.length], new ArrayList<String>());
  }

  private static String convert(int[] queens){
    String ans = "";
    for (int i = 0; i < queens.length; i++){
      ans += queens[i];
    }
    return ans;
  }

  private boolean helpS(int start, int index, int[] queens, ArrayList<String> count){
    if (index < start) return false;
    if (index >= queens.length) {
      String a = convert(queens);
      if (!count.contains(a)) count.add(a);
      return true;
    }
    if (queens[index] < queens.length && addQueen(queens[index], index)) return helpS(start, index + 1, queens, count);
    else{
      if (queens[index] >= board.length - 1) {
        queens[index] = 0;
        if (index == start) {
          board = new int[board.length][board.length];
          return false;
        }
        removeQueen(queens[index - 1], index - 1);
        queens[index - 1]++;
        return helpS(start, index - 1, queens, count);
      }
      else{
        queens[index]++;
        return helpS(start, index, queens, count);
      }
    }
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
    int[] queens = new int[board.length];
    int[] testy = new int[board.length];
    ArrayList<String> sols = new ArrayList<>();
    if (helpS(0,0, queens, sols)){
      for (int index = 0; index < queens.length - 1; index++){
        for (int i = 0; i < index; i++){
          testy[i] = queens[i];
        }
        for (int i = index; i < queens.length; i++){
          testy[i] = queens[i] + 1;
          helpS(index, index, testy, sols);
        }
      }
      return sols.size();
    }
    else return 0;
    /*
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
    return count;*/
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
