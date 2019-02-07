public class Driver{
  public static void main(String[] args) {
    QueenBoard a = new QueenBoard(4);
    System.out.println(a);
    System.out.println();
    System.out.println(a.addQueen(2,2));
    System.out.println(a);
  }
}
