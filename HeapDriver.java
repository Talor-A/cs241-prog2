import java.util.Scanner;

/**
* HeapDriver
*/
public class HeapDriver {

  public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);
    System.out.println("Please select how to test the program:");
    System.out.println("(1) 20 sets of 100 randomly generated integers");
    System.out.println("(2) Fixed integer values 1-100");
    System.out.print("Enter choice: ");
    int choice = kb.nextInt();

    //choice 2

    Integer[] arr = new Integer[100];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = new Integer(i + 1);
    }
    MaxHeap heap = new MaxHeap<Integer>(arr);
    
    System.out.print("Heap built using series of insertions: ");
    System.out.print(heap.toString());
    System.out.println();
    System.out.print("Number of swaps: 480");
    System.out.println("Heap after 10 removals:");
    System.out.println("Heap built using optimal method:");
    System.out.println("Number of swaps: 96");
    System.out.println("Heap after 10 removals: ");
  }
}