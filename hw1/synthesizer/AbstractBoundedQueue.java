package synthesizer;

/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：6/26/2020 10:28 PM
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
	protected int fillCount;
	protected int capacity;

	public int capacity() {
		return this.capacity;
	}

	public int fillCount() {
		return this.fillCount;
	}

	public abstract T peek();
	public abstract T dequeue();
	public abstract void enqueue(T x);
}
