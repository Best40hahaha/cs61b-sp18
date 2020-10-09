package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import javafx.geometry.Pos;

import java.util.*;

/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：7/1/2020 9:08 PM
 */
public class Room {
	private Position position1;
	private Position position2;
	private TETile floor;

	public Room(Position p1, Position p2, TETile floor){
		this.position1 = p1;
		this.position2 = p2;
		this.floor = floor;
	}

	public Room(Position p1, Position p2){
		this.position1 = p1;
		this.position2 = p2;
		this.floor = Tileset.FLOOR;
	}

	public HashMap<String, Integer> sizeValue(){
		Position p1 = this.getPosition1();
		Position p2 = this.getPosition2();
		HashMap<String, Integer> value = new HashMap<>();

		/*generate the min and max width / height from p1, p2*/
		value.put("minWidth", Math.min(p1.widthPosition, p2.widthPosition));
		value.put("maxWidth", Math.max(p1.widthPosition, p2.widthPosition));
		value.put("minHeight", Math.min(p1.heightPosition, p2.heightPosition));
		value.put("maxHeight", Math.max(p1.heightPosition, p2.heightPosition));

		return value;
	}

	/*from two points generate a four points vertexes*/
	public List<Position> vertexes() {

		HashMap<String, Integer> value = this.sizeValue();

		/*the vertexes should be sorted, from left-down position, and sort it anti-clockwise*/
		List<Position> vertexPoints = new ArrayList<>();
		vertexPoints.add(new Position(value.get("minWidth"), value.get("minHeight")));
		vertexPoints.add(new Position(value.get("maxWidth"), value.get("minHeight")));
		vertexPoints.add(new Position(value.get("maxWidth"), value.get("maxHeight")));
		vertexPoints.add(new Position(value.get("minWidth"), value.get("maxHeight")));

		return vertexPoints;
	}


	/*Return the floor position[] of the room**/
	public List<Position> floor(){
		List<Position> floorSpace = new ArrayList<>();
		HashMap<String, Integer> size = this.sizeValue();
		for(int i = size.get("minWidth"); i <= size.get("maxWidth"); i++){
			for(int j = size.get("minHeight"); j <= size.get("maxHeight"); j++){
				floorSpace.add(new Position(i,j));
			}
		}

		return floorSpace;
	}

	/*this border is the wall around the floor space*/
	public List<Position> border(){
		List<Position> borderSpace = new ArrayList<>();
		HashMap<String, Integer> size = this.sizeValue();

		for(int i = size.get("minWidth") - 1; i <= size.get("maxWidth") + 1; i++){
			borderSpace.add(new Position(i, size.get("minHeight")));
			borderSpace.add(new Position(i, size.get("maxHeight")));
		}

		for(int i = size.get("minHeight") - 1; i < size.get("maxHeight") + 1; i++){
			borderSpace.add((new Position(size.get("minWidth"), i)));
			borderSpace.add((new Position(size.get("maxWidth"), i)));
		}

		return borderSpace;

	}


	public List<Position> total(){
		List<Position> totalSpace = new ArrayList<>();
		HashMap<String, Integer> size = this.sizeValue();
		for(int i = size.get("minWidth") - 1; i <= size.get("maxWidth") + 1; i++){
			for(int j = size.get("minHeight") - 1; j <= size.get("maxHeight") + 1; j++){
				totalSpace.add(new Position(i,j));
			}
		}

		return totalSpace;
	}


	public static Room genRoom(long seed, double sizePara){

		Random RANDOM = new Random(seed);
		int x1 = RandomUtils.uniform(RANDOM, Game.WIDTH - 1);
		int y1 = RandomUtils.uniform(RANDOM, Game.HEIGHT - 1);

		if(x1 < 1){
			x1 = 1;
		}

		if(y1 < 1){
			y1 = 1;
		}

		Position p1 = new Position(x1, y1);
		/*hereby, picked gaussian distribution to generate the second position, we can adjust the size of the room by sizePara*/
		int x2 = (int) RandomUtils.gaussian(RANDOM, x1, sizePara);
		int y2 = (int) RandomUtils.gaussian(RANDOM, y1, sizePara*0.8);


		if(x2 < 1){
			x2 = 1;
		}

		if(y2 < 1){
			y2 = 1;
		}

		if(x2 > Game.WIDTH - 2){
			x2 = Game.WIDTH - 2;
		}

		if(y2 > Game.HEIGHT - 2){
			y2 = Game.HEIGHT - 2;
		}

		Position p2 = new Position(x2, y2);
		Room room = new Room(p1, p2);

		return room;
	}

	public static List<Room> genRooms(long seed, double sizePara, int number){
		List<Long> series = SeedSeries.series(seed, number);
		List<Room> rooms = new ArrayList<>();
		for(long s : series){
			Random RANDOM = new Random(s);
			rooms.add(genRoom(s, sizePara));
		}
		return rooms;
	}

	/*to judge if this room is overlap with another room (by total space)
	* overlap: true
	* separate: false
	* */
	public boolean overlap(Room room){
		for(Position p1 : this.border()){
			for(Position p2 : room.border()){
				if(p1.equals(p2)){
					return true;
				}
			}
		}
		return false;
	}


	public Position getPosition1() {
		return position1;
	}

	public Position getPosition2() {
		return position2;
	}

	public TETile getFloor() {
		return floor;
	}

	// 由于定义长和宽可能会超出画面，所以我想了一个新办法，可以确定两个点，而两个点可以唯一确定一个长方形
	public static void main(String[] args) {
		Room r = new Room(new Position(0,0), new Position(5,7));
		Room r2 = new Room(new Position(12,12), new Position(10,10));
		System.out.println(r.overlap(r2));
	}

}
