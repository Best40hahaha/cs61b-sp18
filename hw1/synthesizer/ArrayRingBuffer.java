// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator{
        private int ptr;
        private int size;
        public BufferIterator(){
            ptr = first;
            size = 0;
        }

        public boolean hasNext(){
            return (size != fillCount);
        }
        public T next(){
            if(!hasNext()){
                throw new IndexOutOfBoundsException("there is no next item in this queue.");
            }
            T returnItem =  rb[ptr];
            ptr = bufferNum(ptr - 1);
            size += 1;
            return returnItem;
        }
    }
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;
    private BufferIterator iter;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.fillCount = 0;
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.rb = (T[])new Object[capacity];
        this.iter = new BufferIterator();
    }

    /*helper method, define the calculation way of this class**/
    private int bufferNum(int num){
        if(num >= 0){
            return (num % this.capacity);
        }else{
            return this.capacity - ((-num) % this.capacity);
        }
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if(this.fillCount == this.capacity){
            throw new RuntimeException("Ring Buffer Overflow");
        }

        if(this.fillCount != 0){
            this.last = bufferNum(this.last - 1);
        }
        rb[this.last] = x;
        this.fillCount += 1;
        return;

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if(this.fillCount == 0){
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T temp = rb[this.first];
        rb[first] = null;
        this.first = bufferNum(this.first - 1);
        this.fillCount -= 1;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if(this.fillCount == 0){
            throw new RuntimeException("Buffer Ring is empty");
        }
        return rb[this.first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.


}
