public interface Deque<T> {
	public void addFirst(T value);
	public void addLast(T value);
	public boolean isEmpty();
	public int size();
	public void printDeque();
	public T removeFirst();
	public T removeLast();
	public T get(int index);

}
