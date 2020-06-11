import static org.junit.Assert.*;

import org.junit.Test;
/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：6/11/2020 3:02 PM
 */
public class testFlik {
	@Test
	public void testIsSameNumber(){
		int a = 1;
		int b = 1;
		int c = 1000;
		int d = 1000;
		int e = 900;
		assertTrue("when number equals to 1",Flik.isSameNumber(a,b));
		assertTrue("when number equals to 1000", Flik.isSameNumber(c,d));
		assertFalse("when number equals to 900 and 1000",Flik.isSameNumber(d,e));

	}
}
