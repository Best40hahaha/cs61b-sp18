/**
 * @author ：FlyingRedPig
 * @description：Create an ArrayList class
 * @date ：6/9/2020 11:35 AM
 */
public class ArrayDeque<T> {
	private int size;
	private int nextFirst;
	private int nextLast;
	private T[] Ts;
	private static double increaseCo = 2;
	private static double decreaseCo = 0.5;
	private static double usageLimit = 0.25;

	public ArrayDeque() {
		size = 0;
		nextFirst = 4;
		nextLast = 5;
		Ts = (T[]) new Object[8];
	}

	/*helper method, create a new calculation way for this class*/
	private int aListNumber(int x){
		if(x>=0){
			return x % Ts.length;
		}else{
			return Ts.length - ((-x) % Ts.length);
		}
	}

	/*helper method, change the order of this array, start from the zero index*/
	private T[] stdOrder(){
		int firstPosition = aListNumber(nextFirst+1);
		T[] newTs = (T[]) new Object[Ts.length];
		// copy first half of the list
		System.arraycopy(Ts, firstPosition, newTs, 0, Ts.length-firstPosition);
		// copy second half of the list
		System.arraycopy(Ts,0, newTs, Ts.length-firstPosition, firstPosition);
		return newTs;
	}

	/*helper method, judge if the usage of list below our limitation(0.25 in this case)*/
	private boolean isBelowUsage(){
		double dSize = this.size;
		double dLength = this.Ts.length;
		return (dSize/dLength) < usageLimit;
	}


	/*resize the list when necessary, coefficient refers to how many times we resize the list*/
	private void reSize(double coefficient){
		T[] stdOrderTs = stdOrder();
		this.Ts = (T[])new Object[(int) (Ts.length*coefficient)];
		System.arraycopy(stdOrderTs, 0 , this.Ts, 0, this.size);
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
			System.out.print(Ts[index] + " ");
			index = aListNumber(index + 1);
		}
		System.out.println("");
	}


	/* Add value to the beginning of the list */
	public void addFirst(T value) {
		size += 1;
		Ts[this.nextFirst] = value;
		nextFirst = aListNumber(nextFirst - 1);
		if(this.size == this.Ts.length){
			reSize(increaseCo);
		}
	}

	/* Add value to the last of the list */
	public void addLast(T value) {
		size += 1;
		Ts[this.nextLast] = value;
		nextLast = aListNumber(nextLast + 1);
		if(this.size == this.Ts.length){
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
		T originFirst = Ts[nextFirst];
		Ts[nextFirst] = null;
		if(isBelowUsage()){
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
		nextLast -= 1;
		T originLast = Ts[nextLast];
		Ts[nextLast] = null;
		if(isBelowUsage()){
			reSize(0.5);
		}
		return originLast;
	}

	/*returns the value of corresponding index in our list*/
	public T get(int index){
		int first = aListNumber(this.nextFirst+1);
		int realIndex = aListNumber(index+ first);
		return this.Ts[realIndex];
	}

	public static void main(String[] args) {

	}

}
