import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    //Uncomment this class once you've created your Palindrome class.
    @Test
    public void testIsPalindrome(){
        String t1 = "noon";
        String t2 = "Noon";
        String t3 = "";
        String t4 = "r";
        assertTrue(palindrome.isPalindrome(t1));
        assertTrue(palindrome.isPalindrome("abcba"));
        assertFalse(palindrome.isPalindrome(t2));
        assertTrue(palindrome.isPalindrome(t3));
        assertTrue(palindrome.isPalindrome(t4));

    }

    @Test
    public void testIsPalinndrome2(){
        CharacterComparator cc = new OffByOne();
        String t1 = "a";
        String t2 = "flake";
        String t3 = "apple";
        assertTrue(palindrome.isPalindrome(t1, cc));
        assertTrue(palindrome.isPalindrome(t2, cc));
        assertFalse(palindrome.isPalindrome(t3, cc));
    }


}
