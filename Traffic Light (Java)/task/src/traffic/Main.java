package traffic;

public class Main {
  public static void main(String[] args){
    displayMenu();
  }

  private static void displayMenu() {
    System.out.println("Welcome to the traffic management system!");
    System.out.println("Menu:\n" +
            "1. Add\n" +
            "2. Delete\n" +
            "3. System\n" +
            "0. Quit");
  }
}
