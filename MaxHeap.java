
/**
 * MaxHeap
 */
public class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T> {

  private T[] heap;
  private int lastIndex;
  private static final int DEFAULT_CAPACITY = 25;
  private static final int MAX_CAPACITY = 10000;
  private int numSwaps;

  public MaxHeap() {
    this(DEFAULT_CAPACITY);
  }

  public MaxHeap(int size) {
    if (size < DEFAULT_CAPACITY) {
      size = DEFAULT_CAPACITY;
    } else if (size > MAX_CAPACITY) {
      size = MAX_CAPACITY;
    }
    @SuppressWarnings("unchecked")
    T[] tempHeap = (T[]) new Comparable[size + 1];
    heap = tempHeap;
    lastIndex = 0;
    numSwaps = 0;
  }

  public MaxHeap(T[] entries) {
    this(entries.length);
    for (int index = 0; index < entries.length; index++) {
      heap[index + 1] = entries[index];
    }
    lastIndex = entries.length;
    for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--) {
      reheap(rootIndex);
    }
  }

  public void add(T newEntry) {
    int newIndex = lastIndex + 1;
    int parentIndex = newIndex / 2;
    while (parentIndex > 0 && newEntry.compareTo(heap[parentIndex]) > 0) {
      heap[newIndex] = heap[parentIndex];
      newIndex = parentIndex;
      parentIndex = parentIndex / 2;
      numSwaps++;
    }
    heap[newIndex] = newEntry;
    lastIndex++;
    ensureCapacity();
  }

  public void ensureCapacity() {
    if (lastIndex >= heap.length) {
      @SuppressWarnings("unchecked")
      T[] tempHeap = (T[]) new Comparable[2 * heap.length];
      for (int i = 0; i <= lastIndex; i++) {
        tempHeap[i] = heap[i];
      }
      heap = tempHeap;
    }
  }

  public T removeMax() {
    if (isEmpty()){
      System.out.println("empty!!");
      return null;
    }

    T root = heap[1];
    heap[1] = heap[lastIndex];
    lastIndex--;
    numSwaps++;
    reheap(1);

    return root;
  }

  public T getMax() {
    return isEmpty() ? null : heap[1];
  }

  public boolean isEmpty() {
    return lastIndex < 1;
  }

  public int getSize() {
    return lastIndex;
  }

  public void clear() {
    while (lastIndex > -1) {
      heap[lastIndex] = null;
      lastIndex--;
    }
    lastIndex = 0;
  }

  private void reheap(int rootIndex) {
    boolean done = false;
    T orphan = heap[rootIndex];
    int leftChildIndex = 2 * rootIndex;
    while (!done && (leftChildIndex <= lastIndex)) {
      int largerChildIndex = leftChildIndex;
      int rightChildIndex = leftChildIndex + 1;
      if ((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0) {
        largerChildIndex = rightChildIndex;
      }
      if (orphan.compareTo(heap[largerChildIndex]) < 0) {
        heap[rootIndex] = heap[largerChildIndex];
        numSwaps++;
        rootIndex = largerChildIndex;
        leftChildIndex = 2 * rootIndex;
      } else {
        done = true;
      }
      heap[rootIndex] = orphan;

    }
  }

  private static <T extends Comparable<? super T>> void reheap(T[] heap, int rootIndex, int lastIndex) {
    boolean done = false;
    T orphan = heap[rootIndex];
    int leftChildIndex = 2 * rootIndex + 1;
    while (!done && (leftChildIndex <= rootIndex)) {
      int largerChildIndex = leftChildIndex;
      int rightChildIndex = leftChildIndex + 1;
      if ((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0) {
        largerChildIndex = rightChildIndex;
      }
      if (orphan.compareTo(heap[largerChildIndex]) < 0) {
        heap[rootIndex] = heap[largerChildIndex];
        rootIndex = largerChildIndex;
        leftChildIndex = 2 * rootIndex + 1;
      } else {
        done = true;
      }
      heap[rootIndex] = orphan;
    }
  }

  public String toString() {
    String str = "";
    for (int i = 1; i < heap.length; i++) {
      str += heap[i];
      str += ",";
    }
    return str;
  }
  public int getNumSwaps() {
    return numSwaps;
  }
}