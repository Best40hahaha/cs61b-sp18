package byog.lab6;

import byog.Core.Game;
import byog.Core.RandomUtils;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Please enter a seed");
//            return;
//        }
//
//        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, 90);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 90);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //Initialize random number generator
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //Generate random string of letters of length n
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            int index = RandomUtils.uniform(this.rand, CHARACTERS.length);
            sb.append(CHARACTERS[index]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        Font font = new Font("Monaco", Font.BOLD, 90);
        StdDraw.setFont(font);
        StdDraw.text(0.5*this.width, 0.5*this.height, s);
        StdDraw.show();

    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        char [] chAry = letters.toCharArray();
        StdDraw.clear(StdDraw.BLACK);
        for ( char c : chAry) {
            this.drawFrame(String.valueOf(c));
            StdDraw.pause(1000);
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.show();
            StdDraw.pause(500);
        }
        StdDraw.clear(StdDraw.BLACK);
    }

    public void showRound(int round) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.text(0.5*this.width, 0.5*this.height, "ROUND " + round);
        StdDraw.show();
        StdDraw.pause(1000);
    }

    public void gameOver() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.text(0.5*this.width, 0.5*this.height, "GAME OVER");
        StdDraw.show();
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                sb.append(c);
                StdDraw.clear(StdDraw.BLACK);
                StdDraw.setPenColor(StdDraw.ORANGE);
                Font font = new Font("Monaco", Font.BOLD, 90);
                StdDraw.setFont(font);
                StdDraw.text(0.5*this.width, 0.5*this.height, sb.toString());
                StdDraw.show();
                n -= 1;
                if (n == 0){
                    StdDraw.pause(500);
                    return sb.toString();
                }
            }
        }
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        int round = 1;
        //TODO: Establish Game loop
        while (true) {
            this.showRound(round);
            String target = this.generateRandomString(round);
            this.flashSequence(target);
            String answer = solicitNCharsInput(round);
            if (!target.equals(answer)) {
                break;
            }
            round += 1;
        }
        this.gameOver();
    }


}
