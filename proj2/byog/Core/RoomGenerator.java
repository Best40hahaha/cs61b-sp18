package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：10/9/2020 11:42 AM
 */
public class RoomGenerator {
	private int fail = 0;
	private long seed = 0;
	private int number = 0;
	private int failBound = 10;
	private List<Room> rooms;


	/*need to be noticed: avgNumber is the mu of the gaussian distribution, number is the exact number of rooms*/
	public RoomGenerator(long seed, int avgNumber, int failBound){
		Random RANDOM = new Random(seed);
		this.number = (int) RandomUtils.gaussian(RANDOM, avgNumber, 2);
		this.failBound = failBound;
		this.seed = seed;
		this.rooms = new ArrayList<>();
	}

	public boolean isValid(Room room){

		// not valid if part of the room outside the canvas
		for(Position p : room.total()){
			if (!p.within(Game.WIDTH, Game.HEIGHT)){
				return false;
			}
		}

		// not valid if the room itself is a line
		HashMap<String, Integer> size = room.sizeValue();
		if((size.get("minHeight") == size.get("maxHeight")) || (size.get("minWidth") == size.get("maxWidth"))){
			return false;
		}

		// not valid if the room overlaps with other rooms which have already generated
		for(Room r : this.rooms){
			if(r.overlap(room)){
				return false;
			}
		}

		return true;
	}

	public Room genRoom(long seed, double sizePara){

		Random RANDOM = new Random(seed);
		int x1 = RandomUtils.uniform(RANDOM, Game.WIDTH - 1);
		int y1 = RandomUtils.uniform(RANDOM, Game.HEIGHT - 1);

		Position p1 = new Position(x1, y1);
		/*hereby, picked gaussian distribution to generate the second position,
		we can adjust the size of the room by sizePara*/
		int x2 = (int) RandomUtils.gaussian(RANDOM, x1, sizePara);
		int y2 = (int) RandomUtils.gaussian(RANDOM, y1, sizePara*0.8);

		Position p2 = new Position(x2, y2);
		Room room = new Room(p1, p2);

		return room;
	}

	public List<Room> genRooms(double sizePara){

		List<Long> series = SeedSeries.series(this.seed, this.number);
		for(long s : series){
			Room room = this.genRoom(s, sizePara);
			if(this.isValid(room)){
				this.rooms.add(room);
			} else{
				this.fail += 1;
				if(this.fail >= this.failBound) break;
			}
		}
		return this.rooms;
	}

	public int getFail() {
		return fail;
	}

	public long getSeed() {
		return seed;
	}

	public int getNumber() {
		return number;
	}

	public int getFailBound() {
		return failBound;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public static void main(String[] args) {
		RoomGenerator gen = new RoomGenerator(10,20,2);
		gen.genRooms(0.8);
		System.out.println(gen.getRooms());

		/*Room r1 = new Room(new Position(0,0), new Position(3,5));
		Room r2 = new Room(new Position(1,3), new Position(5,3));
		Room r3 = new Room(new Position(10,2), new Position(10,5));
		Room r4 = new Room(new Position(0,0), new Position(30,30));
		Room r5 = new Room(new Position(1,1), new Position(28,28));

		System.out.println(gen.isValid(r1));
		System.out.println(gen.isValid(r2));
		System.out.println(gen.isValid(r3));
		System.out.println(gen.isValid(r4));
		System.out.println(gen.isValid(r5));*/

	}
}
