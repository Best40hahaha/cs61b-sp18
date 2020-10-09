package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import javafx.geometry.Pos;

import java.util.List;
import java.util.Random;

/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：7/1/2020 2:30 PM
 */
public class WorldGenerator {
	private TETile[][] world;
	private Random RANDOM;
	private long seed;

	public WorldGenerator(int width, int height, long seed){
		this.world = new TETile[width][height];
		this.seed = seed;
		for (int x = 0; x < width; x += 1) {
			for (int y = 0; y < height; y += 1) {
				world[x][y] = Tileset.NOTHING;
			}
		}
		RANDOM = new Random(seed);
	}

	public long getSeed() {
		return seed;
	}

	public TETile[][] getWorld(){
		return this.world;
	}

	/*given a list of positions, print the space on the canvas*/
	public void build(List<Position> space){
		for(Position p : space){
			this.world[p.widthPosition][p.heightPosition] = Tileset.FLOOR;
		}
	}




	public void randomRoom(int size){
		int x = RandomUtils.uniform(RANDOM, Game.WIDTH);
		int y = RandomUtils.uniform(RANDOM, Game.HEIGHT);
		Position start = new Position(x, y);
		int width = RandomUtils.uniform(RANDOM, size);
		int height = RandomUtils.uniform(RANDOM, size);


	}

	public static void main(String[] args) {
		TERenderer ter = new TERenderer();
		ter.initialize(80, 30);

		WorldGenerator wg = new WorldGenerator(Game.WIDTH, Game.HEIGHT, 10);
		/*Position p1 = new Position(30,29);
		Position p2 = new Position(0, 0);
		Room r = new Room(p1, p2);
		wg.build(r.floor());*/
		RoomGenerator gen = new RoomGenerator(10,100,100);
		List<Room> rooms = gen.genRooms(10);

		for(Room room : rooms){
			List<Position>  space = room.floor();
			wg.build(space);
			}
		ter.renderFrame(wg.getWorld());
	}
}
