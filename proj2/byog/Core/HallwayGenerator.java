package byog.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：10/10/2020 8:26 PM
 */
public class HallwayGenerator {

  private long seed;
  /*hallways should be generated after rooms generation*/
  private List<Room> rooms;

  public HallwayGenerator(long seed, List<Room> rooms) {
    this.seed = seed;
    this.rooms = rooms;
  }

  /* do a verify for hallways */
  private static boolean verify(Room r1, Room r2, Hallway h) {
    List<Position> hallwaySpace = h.floorSpace();
    //取并集
    //取墙体，意思是hallway不能成为room的墙体，只可以有两个点与墙体重合，作为连接点
    List<Position> roomWall = new ArrayList<>(r1.wall());
    roomWall.removeAll(r2.wall());
    roomWall.addAll(r2.wall());
    roomWall.retainAll(hallwaySpace);
    return roomWall.size() <= 2;
  }

  public static Hallway genHallway(Room r1, Room r2, long seed) {

    List<Position> border1 = r1.border();
    List<Position> border2 = r2.border();

    List<Long> twoSeeds = SeedSeries.series(seed, 2);
    long seed1 = twoSeeds.get(0);
    long seed2 = twoSeeds.get(1);

    Random RANDOM1 = new Random(seed1);
    int index1 = RandomUtils.uniform(RANDOM1, border1.size());

    Random RANDOM2 = new Random(seed2);
    int index2 = RandomUtils.uniform(RANDOM2, border2.size());

    Position p1 = border1.get(index1);
    Position p2 = border2.get(index2);

    Hallway h = new Hallway(r1, r2, p1, p2);
//     judge if this hallway is valid
    if (HallwayGenerator.verify(r1, r2, h)) {
      return h;
    } else {
    	return genHallway(r1, r2, seed + 1);
		}


  }

//	public List<Hallway> genHallways() {
//		List<Room> rooms = this.rooms;
//		ArrayList<Room> connectedRooms = new ArrayList<Room>();
//
//	}
//

  public static void main(String[] args) {
    Room r1 = new Room(new Position(4, 5), new Position(8, 10));
    Room r2 = new Room(new Position(11, 12), new Position(13, 15));
    Hallway h = genHallway(r1, r2, 100);
		WorldGenerator w = new WorldGenerator(600);


  }


}
