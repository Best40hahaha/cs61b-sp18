/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：6/20/2020 10:25 PM
 */
public class OffByOne implements CharacterComparator{

	public static int abs(int value){
		if(value >= 0){
			return value;
		}
		return -value;
	}

	@Override
	public boolean equalChars(char x, char y){
		return abs(x - y) == 1;
	}

}
