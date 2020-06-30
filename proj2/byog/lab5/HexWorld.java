package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
	private static final int WIDTH = 50;
	private static final int HEIGHT = 35;
	private static final long SEED = 2873123;
	private static final Random RANDOM = new Random(SEED);


	/*add a hexagon with certain size on a specific position**/
	public static void addHexagon(TETile[][] world, int size, Position p, TETile t){
		int heightPosition = p.heightPosition;
		int widthPosition = p.widthPosition;
		int[] shape = shape(size);
		int[] startPoint = startPoint(size);
		for(int y = 0; y < shape.length; y += 1) {
			int height = y + heightPosition;
			for (int x = 0; x < shape[y]; x += 1) {
				int width = x + widthPosition + startPoint[y];
				world[width][height] = t;
			}
		}
	}

	/*helper method for creating a single hexagon**/
	public static int[] startPoint(int size){
		int[] s = new int[size * 2];
		for(int i = 0; i < size; i += 1){
			s[i] = -i;
			s[size * 2 - 1 - i] = s[i];
		}
		return s;
	}

	/*helper method for creating a single hexagon**/
	public static int[] shape(int size){
		int[] shape = new int[size * 2];
		for(int i = 0; i < size; i += 1){
			shape[i] = size + i * 2;
			shape[size * 2 - 1 - i] = shape[i];
		}
		return shape;
	}

	/**helper method for counting hexagons*/
	public static int[] hexagonNum(){
		int[] n = {1, 2, 3, 2, 3, 2, 3, 2, 1};
		return n;
	}

	public static Position[] hexagonStart(Position startPoint){
		int startWidth = startPoint.widthPosition;
		int startHeight = startPoint.heightPosition;
		Position[] p = new Position[19];
		int[] number = hexagonNum();
		int layer = 0;
		int index = 0;
		for(int y = 0; y < 27; y += 3){
			int height = startHeight + y;
			switch (number[layer]){
				case 1:
					p[index] = new Position(startWidth, height);
					index += 1;
					break;
				case 2:
					p[index] = new Position(startWidth - 5, height);
					index += 1;
					p[index] = new Position(startWidth + 5, height);
					index += 1;
					break;
				case 3:
					p[index] = new Position(startWidth - 10, height);
					index += 1;
					p[index] = new Position(startWidth, height);
					index += 1;
					p[index] = new Position(startWidth + 10, height);
					index += 1;
					break;
			}
			layer += 1;
		}
		return p;
	}

	public static TETile randomTile(){
		int tileNum = RANDOM.nextInt(5);
		switch (tileNum) {
			case 0:
				return Tileset.WALL;
			case 1:
				return Tileset.FLOWER;
			case 2:
				return Tileset.MOUNTAIN;
			case 3:
				return Tileset.GRASS;
			case 4:
				return Tileset.LOCKED_DOOR;
			default: return Tileset.SAND;
		}
	}

	public static void construct(TETile[][] world, Position p){
		Position[] s = hexagonStart(p);
		for(Position i: s){
			addHexagon(world, 3, i, randomTile());
		}
	}



	public static void main(String[] args) {
		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		/**Initialize the world*/
		TETile[][] world = new TETile[WIDTH][HEIGHT];
		for (int x = 0; x < WIDTH; x += 1) {
			for (int y = 0; y < HEIGHT; y += 1) {
				world[x][y] = Tileset.NOTHING;
			}
		}

		Position p = new Position(20,0);
		HexWorld.construct(world, p);

		//HexWorld.addHexagon(world,3, p , Tileset.GRASS);

		ter.renderFrame(world);

	}


}
