import static org.junit.Assert.*;
import org.junit.Test;
/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：6/21/2020 11:17 PM
 */
public class TestArrayDequeGold {

	@Test
	public void testArrayDeque(){
		StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
		ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<Integer>();
		int testNumber = 500;
		ArrayDequeSolution<String> record = new ArrayDequeSolution<String>();

		for (int i = 0; i < testNumber; i += 1) {
			double numberBetweenZeroAndOne = StdRandom.uniform();

			if (numberBetweenZeroAndOne < 0.25) {
				sad1.addLast(i);
				sad2.addLast(i);
				record.addFirst("addLast(" + i +")");
			} else if (numberBetweenZeroAndOne < 0.5) {
				sad1.addFirst(i);
				sad2.addFirst(i);
				record.addFirst("addFirst(" + i +")");
			} else if (numberBetweenZeroAndOne <0.75) {
				try{
					int x = sad1.removeFirst();
					int y = sad2.removeFirst();
					record.addFirst("removeFirst()");
					assertEquals("\n" + record.get(2) + "\n" + record.get(1) + "\n" + record.get(0), x, y);
				} catch (Exception e){
					continue;
				}

			} else if (numberBetweenZeroAndOne < 1){
				try{
					int x = sad1.removeLast();
					int y = sad2.removeLast();
					record.addFirst("removeLast()");

					assertEquals("\n" + record.get(2) + "\n" + record.get(1) + "\n" + record.get(0), x, y);
				} catch (Exception e){
					continue;
				}

			}
	}
	}
}
