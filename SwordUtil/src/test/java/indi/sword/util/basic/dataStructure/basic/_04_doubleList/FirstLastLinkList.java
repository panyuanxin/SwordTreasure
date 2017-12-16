package indi.sword.util.basic.dataStructure.basic._04_doubleList;

/*
 * 双端链表
 */
public class FirstLastLinkList {
	//头结点
	private Node first;
	//尾结点
	private Node last;

	public FirstLastLinkList() {
		first = null;
		last = null;
	}

	public void insertFirst(long value) {
		Node node = new Node(value);
		if(isEmpty()) {
			last = node;
		}
		node.next = first;
		first = node;
	}

	public void insertLast(long value) {
		Node node = new Node(value);
		if(isEmpty()) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
	}

	public Node deleteFirst() {
		Node tmp = first;
		if(first.next == null) {
			last = null;
		}
		first = tmp.next;
		return tmp;
	}

	/**
	 * 显示方法
	 */
	public void display() {
		Node current = first;
		while(current != null) {
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
		while(current.data != value) {
			if(current.next == null) {
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
		Node previous = first;
		while(current.data != value) {
			if(current.next == null) {
				return null;
			}
			previous = current;
			current = current.next;
		}

		if(current == first) {
			first = first.next;
		} else {
			previous.next = current.next;
		}
		return current;

	}

	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return (first == null);
	}


	public Node deleteLast(){
		if(isEmpty()){
			return null;
		}
		Node current = first;
		if(first == last){
			first = null;
			last = null;
			return current;
		}else{
			Node previous = current;
			while(current.next != null){
				previous = current;
				current = current.next;
			}
			previous.next = null;
			last = previous;
			return current;
		}
	}


	public void deleteByValue(long value){
		if(isEmpty()){
			return ;
		}else if(first == last){
			if(first.data == value){
				first = null;
				last = null;
			}
		}else {
			Node current = first;
			Node previous = first;
			while(current != null){
				if(current.data == value){
					previous.next = current.next;
					if(current == first){
						first = first.next;
						previous = previous.next;
					}
				}else{
					previous = current;
				}
				current = current.next;
			}
		}
	}
}
