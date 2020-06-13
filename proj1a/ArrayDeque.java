/**
 * @author ：FlyingRedPig
 * @description：Create an ArrayList class
 * @date ：6/9/2020 11:35 AM
 */
public class ArrayDeque<T> {
	private int size;
	private int nextFirst;
	private int nextLast;
	private T[] items;
	private static double increaseCo = 2;
	private static double decreaseCo = 0.5;
	private static double usageLimit = 0.25;

	public ArrayDeque() {
		size = 0;
		nextFirst = 4;
		nextLast = 5;
		items = (T[]) new Object[8];
	}

	/*helper method, create a new calculation way for this class*/
	private int aListNumber(int x){
		if(x>=0){
			return x % items.length;
		}else{
			return items.length - ((-x) % items.length);
		}
	}

	/*helper method, change the order of this array, start from the zero index*/
	private T[] stdOrder(){
		int firstPosition = aListNumber(nextFirst+1);
		T[] newitems = (T[]) new Object[items.length];
		// copy first half of the list
		System.arraycopy(items, firstPosition, newitems, 0, items.length-firstPosition);
		// copy second half of the list
		System.arraycopy(items,0, newitems, items.length-firstPosition, firstPosition);
		return newitems;
	}

	/*helper method, judge if the usage of list below our limitation(0.25 in this case)*/
	private boolean isBelowUsage(){
		double dSize = this.size;
		double dLength = this.items.length;
		if(dLength==0){
			return false;
		}
		return (dSize/dLength) < usageLimit;
	}


	/*resize the list when necessary, coefficient refers to how many times we resize the list*/
	private void reSize(double coefficient){
		T[] stdOrderitems = stdOrder();
		this.items = (T[])new Object[(int) (items.length*coefficient)];
		System.arraycopy(stdOrderitems, 0 , this.items, 0, this.size);
		this.nextLast = this.size;
		this.nextFirst = aListNumber(-1);
	}

	/*check if the list is empty*/
	public boolean isEmpty(){
		return this.size == 0;
	}

	/*return the size of the list*/
	public int size(){
		return this.size;
	}

	/*print the array element one by one*/
	public void printDeque(){
		int index = aListNumber(this.nextFirst + 1);
		while (index != this.nextLast){
			System.out.print(items[index] + " ");
			index = aListNumber(index + 1);
		}
		System.out.println("");
	}


	/* Add value to the beginning of the list */
	public void addFirst(T value) {
		size += 1;
		items[this.nextFirst] = value;
		nextFirst = aListNumber(nextFirst - 1);
		if(this.size == this.items.length){
			reSize(increaseCo);
		}
	}

	/* Add value to the last of the list */
	public void addLast(T value) {
		size += 1;
		items[this.nextLast] = value;
		nextLast = aListNumber(nextLast + 1);
		if(this.size == this.items.length){
			reSize(increaseCo);
		}
	}

	/*Romove the T from the very beginning of the list*/
	public T removeFirst(){
		if(size > 0){
			size -= 1;
		}else{
			size = 0;
		}
		nextFirst = aListNumber(nextFirst+1);
		T originFirst = items[nextFirst];
		items[nextFirst] = null;
		if(isBelowUsage() && this.items.length>=16){
			reSize(0.5);
		}
		return originFirst;
	}

	/*Romove the T from the very end of the list*/
	public T removeLast(){
		if(size > 0){
			size -= 1;
		}else{
			size = 0;
		}
		nextLast = aListNumber(nextLast-1);
		T originLast = items[nextLast];
		items[nextLast] = null;
		if(isBelowUsage() && this.items.length>=16){
			reSize(0.5);
		}
		return originLast;
	}

	/*returns the value of corresponding index in our list*/
	public T get(int index){
		if(index>this.size() || index<0){
			return null;
		}
		int first = aListNumber(this.nextFirst+1);
		int realIndex = aListNumber(index+ first);
		return this.items[realIndex];
	}

}
