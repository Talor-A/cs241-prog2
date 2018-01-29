import java.util.Scanner;
import java.util.Random;

/**
* HeapDriver
*/
public class HeapDriver {

  public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);
    System.out.println("Please select how to test the program:");
    System.out.println("(1) 20 sets of 100 randomly generated integers");
    System.out.println("(2) Fixed integer values 1-100");
    boolean done = false;
    while (!done) {
      System.out.print("Enter choice: ");
      int choice = kb.nextInt();

      switch (choice) {
      case 1:

        int totalSwapsSeries = 0;
        int totalSwapsOptimal = 0;

        for (int trial = 0; trial < 20; trial++) {
          Integer[] arr1 = new Integer[100];
          for (int i = 0; i < arr1.length; i++) {
            arr1[i] = new Integer(i + 1);
          }
          arr1 = RandomizeArray(arr1);
          MaxHeap heap1 = new MaxHeap<Integer>(arr1.length);
          for (Integer i : arr1) {
            heap1.add(i);
          }

          totalSwapsSeries += heap1.getNumSwaps();

          Integer[] arr2 = new Integer[100];
          for (int i = 0; i < arr2.length; i++) {
            arr2[i] = new Integer(i + 1);
          }
          MaxHeap heap2 = new MaxHeap<Integer>(RandomizeArray(arr2));

          totalSwapsOptimal += heap2.getNumSwaps();
        }

        System.out.println("Average swaps for series of insertions: " + totalSwapsSeries / 20);
        System.out.println("Average swaps for optimal method: " + totalSwapsOptimal / 20);
        break;

      case 2:
        //choice 2

        Integer[] arr1 = new Integer[100];
        for (int i = 0; i < arr1.length; i++) {
          arr1[i] = new Integer(i + 1);
        }
        MaxHeap heap1 = new MaxHeap<Integer>(arr1.length);
        for (Integer i : arr1) {
          heap1.add(i);
        }
        Integer[] arr2 = new Integer[100];
        for (int i = 0; i < arr2.length; i++) {
          arr2[i] = new Integer(i + 1);
        }
        MaxHeap heap2 = new MaxHeap<Integer>(arr2);

        System.out.println("Heap built using series of insertions: " + heap1.toString());
        System.out.println("Number of swaps: " + heap1.getNumSwaps());
        for (int i = 0; i < 10; i++) {
          heap1.removeMax();
        }
        System.out.println("Heap after 10 removals: " + heap1.toString());

        System.out.println("Heap built using optimal method: " + heap2.toString());
        System.out.println("Number of swaps: " + heap2.getNumSwaps());
        for (int i = 0; i < 10; i++) {
          heap2.removeMax();
        }
        System.out.println("Heap after 10 removals: " + heap2.toString());
        break;

      default:
        System.out.println("exiting.");
        done = true;
        break;
      }

    }
  }

  public static Integer[] RandomizeArray(Integer[] array) {
    Random rgen = new Random(); // Random number generator
    for (int i = 0; i < array.length; i++) {
      int randomPosition = rgen.nextInt(array.length);
      int temp = array[i];
      array[i] = array[randomPosition];
      array[randomPosition] = temp;
    }
    return array;
  }
}