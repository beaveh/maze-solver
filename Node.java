/**
*Class representing a Node. Each node has an integer value and can be linked to another Node.
*/
public class Node
{
    int value;
    Node next;
    

    public Node(int v) {
        value = v;
    }

    public int get() {
        return value;
    }
    public void set(int v) {
        value = v;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node n) { //setNext does not set value by itself - it's a pointer
        next = n;
    }
}