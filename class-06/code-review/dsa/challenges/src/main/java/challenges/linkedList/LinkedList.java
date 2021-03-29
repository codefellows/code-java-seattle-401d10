package challenges.linkedList;



public class LinkedList {
//    It knows about the head
    Node head; // == null

//    public LinkedList(){
//
//    }

    public void insert(int value){
        this.head = new Node(value, this.head);
    }

    public boolean includesWithALoop(int valueWeAreSearchingFor){
//         assuming LL is {9} -> {7} -> {5}
        Node current = this.head; // {value: 9}
        while(current != null) {
            if(current.value == valueWeAreSearchingFor) return true;
            current = current.next;
        }
        return false;
    }

    public boolean includesRecursive(int valueWeAreSearchingFor){
        return includesRecursive(this.head, valueWeAreSearchingFor);
    }

    private boolean includesRecursive(Node current, int valueWeAreSearchingFor){
//        assuming LL is {9} -> {7} -> {5} -> null
//        base case : we arrived at null : return false
//        small bit of work: node of not null : check for match: return true
//        recursion: call the function on current.next, value
        if(current == null) return false;
        if(current.value == valueWeAreSearchingFor) return true;
        return includesRecursive(current.next, valueWeAreSearchingFor);
    }
}

class Node {
    Node next;
    int value;

    public Node(int valuePotato){
        this.value = valuePotato;
    }

    public Node(int valuePotato, Node next){
        this.value = valuePotato;
        this.next = next;
    }
}

