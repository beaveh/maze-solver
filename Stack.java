public class Stack {
    Node top; 
    
    public void push(int p) {
        Node n = new Node(p);
        n.setNext(top);
        top = n;
    }
    public Integer peek() {
        if (top == null) return null;
        return top.get();
    }
    public Integer pop() {
        if (top == null) return null;
        Node oldtop = top;
        top = top.getNext();
        oldtop.setNext(null);
        return oldtop.get();
    }
}