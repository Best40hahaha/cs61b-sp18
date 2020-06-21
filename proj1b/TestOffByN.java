import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：6/21/2020 4:08 PM
 */
public class TestOffByN {
	static CharacterComparator offByN = new OffByN(5);

	@Test
	public void testEqualChars(){
		assertTrue(offByN.equalChars('a','f'));
		assertTrue(offByN.equalChars('f','a'));
		assertFalse(offByN.equalChars('h','f'));
	}
}
