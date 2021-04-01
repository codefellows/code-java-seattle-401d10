public class Zipping {
  public static void main(String[] args){
    System.out.println("Lets zip");

    LinkedList<String> students = new LinkedList<>();
    students.append("Seamus");
    students.append("Andy");
    students.append("Cristian");
    students.append("Victor");
    students.append("James");
    for(int i = 0; i < 1000000000; i++){
      students.append("James");

    }
    System.out.println(students);

    LinkedList<String> students2 = new LinkedList<>();
    students2.append("Joseph");
    students2.append("Stephen");
    for(int i = 0; i < 1000000000; i++){
      students2.append("James");
    }
    
    System.out.println(students2);

    // System.out.println(LinkedList.zip2(students, students2));
    // System.out.println(LinkedList.zip2(students2, students));
    // System.out.println(LinkedList.zipRecursive(students, students2));
    System.out.println(LinkedList.zipRecursive(students2, students));
  }
}

class LinkedList <T> {
  Node<T> head;
  Node<T> tail;

  public static <T> LinkedList zipRecursive(LinkedList<T> first, LinkedList<T> second){
    first.head = zipRecursive(first.head, second.head);
    return first;
  }

  private static <T> Node zipRecursive(Node first, Node second){
    // set the first's next to be an entirely zipped chain
    // empty nodes does nothing
    // System.out.println(first.value);
    // System.out.println(second.value);
    if(second == null) return first;
    first.next = zipRecursive(second, first.next);
    return first;
  }

    public static <T> LinkedList zip2(LinkedList<T> first, LinkedList<T> second) {
    Node<T> node1 = first.head;
    Node<T> node2 = second.head;
    while(node1 != null && node2 != null){
      Node<T> node1Next = node1.next;
      Node<T> node2Next = node2.next;
      node1.next = node2; // this exists
      if(node1Next != null) {
        node2.next = node1Next;
      }
      node1 = node1Next;
      node2 = node2Next;
    }

    // Node<T> leftovers = node1 == null ? node2 : node1;
    // node1.next = leftovers;
    return first;
  }

  public static <T> LinkedList zip1(LinkedList<T> first, LinkedList<T> second) {
    LinkedList<T> newList = new LinkedList<>();
    Node<T> node1 = first.head;
    Node<T> node2 = second.head;
    while(node1 != null && node2 != null){
      newList.append(node1.value);
      newList.append(node2.value);
      node1 = node1.next;
      node2 = node2.next;
    }
    Node<T> leftovers = node1 == null ? node2 : node1;
    while(leftovers != null){
      newList.append(leftovers.value);
      leftovers = leftovers.next;
    }
    return newList;
  }

  public void append(T value){
    if(head == null) {
      head = new Node(value, null);
      tail = head;
    } else {
      tail.next = new Node(value, null);
      tail = tail.next;
    }
  }

  @Override
  public String toString(){
    return toString(head);
  }

  private String toString(Node<T> node){
    if(node == null) return "null";
    String newStr = String.format("{%s} -> %s", node.value.toString(), this.toString(node.next));
    return newStr;
  }
}
// 1, 2, 3
class Node <T> {
  T value;
  Node<T> next;

  public Node(T value, Node<T> next){
    this.value = value;
    this.next = next;
  }
}