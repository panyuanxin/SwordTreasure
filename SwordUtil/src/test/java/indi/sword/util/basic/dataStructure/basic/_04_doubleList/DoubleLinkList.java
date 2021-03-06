package indi.sword.util.basic.dataStructure.basic._04_doubleList;

/*
 * 双向链表
 */
public class DoubleLinkList {
    //头结点
    private Node first;
    //尾结点
    private Node last;

    public DoubleLinkList() {
        first = null;
        last = null;
    }

    public void insertFirst(long value) {
        Node node = new Node(value);
        if (isEmpty()) {
            last = node;
        } else {
            first.previous = node;
        }
        node.next = first;
        first = node;
    }

    public void insertLast(long value) {
        Node node = new Node(value);
        if (isEmpty()) {
            first = node;
        } else {
            last.next = node;
            node.previous = last;
        }
        last = node;
    }

    public Node deleteFirst() {
        Node tmp = first;
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = tmp.next;
        return tmp;
    }

    public Node deleteLast() {
        Node tmp = last;
        if (first.next == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return last;
    }

    /**
     * 显示方法
     */
    public void display() {
        Node current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 查找方法
     */
    public Node find(long value) {
        Node current = first;
        while (current.data != value) {
            if (current.next == null) {
                return null;
            }
            current = current.next;
        }
        return current;
    }

    /**
     * 删除方法，根据数据域来进行删除
     */
    public Node delete(long value) {
        Node current = first;
        while (current.data != value) {
            if (current.next == null) {
                return null;
            }
            current = current.next;
        }

        if (current == first) {
            first = first.next;
        } else {
            current.previous.next = current.next;
        }
        return current;

    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return (first == null);
    }


    public void deleteByValue(long value) {
        if (isEmpty()) {
            return;
        } else if (first == last && first.data == value) {
            first = null;
            last = null;
        }else{
            Node current = first;

            while(current != null){
                if(current.data == value){
                    if(current == first){
                        first = first.next;
                        first.next.previous = null;
                    }else if(current == last){
                        last.previous.next = null;
                        last = last.previous;
                    }
                    else {
                        current.previous.next = current.next;
                        current.next.previous = current.previous;
                    }
                }
                current = current.next;
            }



        }
    }
}
