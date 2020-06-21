/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：6/21/2020 1:02 PM
 */
public class OffByN implements CharacterComparator {

	public int number;

	public OffByN(int N){
		number = N;
	}

	public static int abs(int value){
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
