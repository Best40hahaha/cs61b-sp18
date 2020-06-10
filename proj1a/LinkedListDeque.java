/**
 * @author ：FlyingRedPig
 * @description：create a double linked list deque
 * @date ：6/7/2020 10:20 PM
 */
public class LinkedListDeque<T> {

	// build a naked linked list first, then wear clothes on it.
	private class NakedLList{
		public T value;
		public NakedLList frontPointer;
		public NakedLList backPointer;

		public NakedLList(T v, NakedLList p1, NakedLList p2){
			this.value = v;
			this.backPointer = p1;
			this.frontPointer = p2;
		}
	}

	private NakedLList sentinel = new NakedLList(null, null, null);
	private int size;

	//constructor
	public LinkedListDeque(){
		this.sentinel.backPointer = this.sentinel;
		this.sentinel.frontPointer = this.sentinel;
		this.size = 0;
	}


	/* add a new item at the first of the list*/
	public void addFirst(T v){

		NakedLList item = new NakedLList(v, sentinel, sentinel.frontPointer);
		sentinel.frontPointer = item;
		item.frontPointer.backPointer = item;
		size += 1;
	}

	/* add a new item at the last of the list*/
	public void addLast(T v){

		NakedLList item = new NakedLList(v, sentinel.backPointer, sentinel);
		sentinel.backPointer = item;
		item.backPointer.frontPointer = item;
		size += 1;

	}

	/*Return a boolean show if the list is empty*/
	public boolean isEmpty(){
		return this.size == 0;
	}

	/*Return an int show the size(or say length) of the list*/
	public int size(){
		return this.size;
	}

	/*Print the elements within the list one by one from the start to the end*/
	public void printDeque() {
		NakedLList recursiveMachine = sentinel.frontPointer;
		while (recursiveMachine != sentinel) {
			System.out.print(recursiveMachine.value + " ");
			recursiveMachine = recursiveMachine.frontPointer;
		}
		System.out.println("");
	}

	/*remove the first item from the list*/
	public T removeFirst(){
		if (!isEmpty()){
			this.size -= 1;
		}
		NakedLList originFirst = sentinel.frontPointer;
		sentinel.frontPointer = originFirst.frontPointer;
		originFirst.frontPointer.backPointer = sentinel;
		return originFirst.value;
	}

	/*remove the last item from the list*/
	public T removeLast(){
		if(!isEmpty()){
			this.size -= 1;
		}
		NakedLList originLast = sentinel.backPointer;
		sentinel.backPointer = originLast.backPointer;
		originLast.backPointer.frontPointer = sentinel;
		return originLast.value;
	}

	/*return the value by index, if such index doesn't exist, return null
	* this method adopts iteration way*/
	public T get(int index){
		if ((index < 0) || (index >= this.size)){
			return null;
		}
		NakedLList searchPointer = sentinel.frontPointer;
		while (index != 0){
			searchPointer = searchPointer.frontPointer;
			index -= 1;
		}
		return searchPointer.value;
	}

	/*helper method for getRecursive*/
	private NakedLList getRecursiveList(int index){
		if ((index < 0) || (index >= this.size)){
			return null;
		}
		NakedLList searchPointer = sentinel.frontPointer;
		if (index == 0){
			return searchPointer;
		}
		return getRecursiveList(index -1).frontPointer;
	}

	/*return the value by index, if such index doesn't exist, return null
	 * this method adopts recursive way*/
	public T getRecursive(int index){
		if (this.getRecursiveList(index) == null){
			return null;
		}
		return this.getRecursiveList(index).value;
	}

}
