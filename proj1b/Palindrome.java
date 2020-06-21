import static java.lang.Character.getType;

/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：6/20/2020 10:40 AM
 */
public class Palindrome {
	public Deque<Character> wordToDeque(String word){
		Deque<Character> d = new ArrayDeque<Character>();
		for(int i = 0; i < word.length(); i++){
			Character a = word.charAt(i);
			d.addLast(a);
		}
		return d;
	}

	public String sub(String word){
		return word.substring(1, word.length() - 1);
	}

	public boolean isPalindrome(String word){
		if(word.length() == 0 || word.length() == 1){
			return true;
		}
		if(word.charAt(0) != word.charAt(word.length() - 1)){
			return false;
		}
		return isPalindrome(sub(word));

	}


	public boolean isPalindrome(String word, CharacterComparator cc){

		if(word.length() == 0 || word.length() == 1){
			return true;
		}
		if(!cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))){
			return false;
		}
		return isPalindrome(sub(word), cc);
	}

	public static void main(String[] args) {
		Palindrome p = new Palindrome();
		Deque<Character> d = p.wordToDeque("word");
		System.out.println(p.isPalindrome(""));
		System.out.println('b'-'a');
	}
}
