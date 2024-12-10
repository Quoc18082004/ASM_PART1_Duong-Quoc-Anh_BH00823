class StudentStack{
    private static class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;

    public StudentStack() {
        this.top = null;
    }

    public void push(Student student) {
        Node newNode = new Node(student);
        newNode.next = top;
        top = newNode;
    }

    public Student pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty.");
        Student popped = top.data;
        top = top.next;
        return popped;
    }


    public Student peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty.");
        return top.data;
    }


    public boolean isEmpty() {
        return top == null;
    }

    public void displayStack() {
        Node current = top;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
