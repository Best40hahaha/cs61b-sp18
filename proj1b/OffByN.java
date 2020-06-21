/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：6/21/2020 1:02 PM
 */
public class OffByN implements CharacterComparator {

	private int number;

	public OffByN(int N){
		number = N;
	}

	private static int abs(int value){
		if(value >= 0){
			return value;
		}
		return -value;
	}

	@Override
	public boolean equalChars(char x, char y) {
		return abs(x - y) == this.number;
	}
}
