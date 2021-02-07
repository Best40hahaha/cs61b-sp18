package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javafx.geometry.Pos;

/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：7/1/2020 2:30 PM
 */
public class WorldGenerator {

  private TETile[][] world;
  private Random RANDOM;
  private long seed;

  public WorldGenerator(long seed) {
    int width = Game.WIDTH;
    int height = Game.HEIGHT;
    this.world = new TETile[width][height];
    this.seed = seed;
    for (int x = 0; x < width; x += 1) {
      for (int y = 0; y < height; y += 1) {
        world[x][y] = Tileset.NOTHING;
      }
    }
    RANDOM = new Random(seed);
  }

  public static void main(String[] args) {
  	WorldGenerator w = new WorldGenerator(28);
    TERenderer ter = new TERenderer();
    ter.initialize(80,30);
    // initialize tiles

    w.generate();

    ter.renderFrame(w.getWorld());



//    Room r1 = new Room(new Position(4, 5), new Position(8, 10));
//    Room r2 = new Room(new Position(11, 12), new Position(13, 15));
//    Hallway h = HallwayGenerator.genHallway(r1, r2, 100);
//    w.build(h.floorSpace(), "floor");

  }


  public void build(Position p, TETile t) {
    this.world[p.widthPosition][p.heightPosition] = t;
  }

  /*given a list of positions, print the space on the canvas*/
  public void build(Collection<Position> space, TETile t) {
    for (Position p : space) {
      this.world[p.widthPosition][p.heightPosition] = t;
    }
  }


  /* return the rooms position for this seed */
  public List<Room> rooms() {
    RoomGenerator gen = new RoomGenerator(this.seed, 200, 50);
    return gen.genRooms(10);
  }

  public List<Hallway> hallways() {

    //random pick one room to start
    List<Room> rooms = this.rooms();
    List<Room> connected = new ArrayList<>();
    connected.add(rooms.get(0));
    rooms.remove(0);
    List<Hallway> hallways = new ArrayList<>();
    //start loop
    while (rooms.size() != 0) {
      Integer[] index = Room.nearRoomIndex(rooms, connected);
      Room r1 = rooms.get(index[0]);
      Room r2 = connected.get(index[1]);
      Hallway h = HallwayGenerator.genHallway(r1, r2, this.seed);
      hallways.add(h);
      connected.add(r1);
      rooms.remove(r1);
    }
    return hallways;
  }

  /* return the wall of given space */
  public List<Position> wall() {
    List<Position> wallSpace = new ArrayList<>();
    List<Position> space = new ArrayList<>();
    for (Room r : this.rooms()) {
      space.addAll(r.floor());
    }
    for (Hallway h : this.hallways()) {
      space.addAll(h.floorSpace());
    }
    for (Position p : space) {
      List<Position> aroundSpace = p.around(Game.WIDTH, Game.HEIGHT);
      for (Position a : aroundSpace) {
        if (!(space.contains(a))){
          wallSpace.add(a);
        }
      }
    }
    return wallSpace;
  }

  /* return the locked door */
  public Position door(long seed) {
    List<Position> wallSpace = this.wall();
    int index = RandomUtils.uniform(new Random(seed), wallSpace.size());
    if (verifyDoor(wallSpace.get(index))) {
      return wallSpace.get(index);
    } else {
      return door(seed + 1);
    }
  }

  private boolean verifyDoor(Position door) {
    List<Room> rooms = this.rooms();
    for (Room r : rooms) {
      for (Position d : door.around("cross")) {
        if (r.floor().contains(d)) {
          return true;
        }
      }
    }
    return false;
  }

  public void show() {
    TERenderer ter = new TERenderer();
    ter.initialize(Game.WIDTH, Game.HEIGHT);
    ter.renderFrame(this.getWorld());
  }

  public TETile[][] getWorld() {
    return world;
  }

  public Random getRANDOM() {
    return RANDOM;
  }

  public long getSeed() {
    return seed;
  }

  public void generate() {
    List<Room> rooms = this.rooms();
    List<Hallway> hallways = this.hallways();
    List<Position> wall = this.wall();
    Position door = this.door(this.seed);
    for (Room r: rooms) {
      this.build(r.floor(), Tileset.FLOOR);
    }
    for (Hallway h : hallways) {
      this.build(h.floorSpace(), Tileset.FLOOR);
    }
    this.build(wall, Tileset.WALL);
    this.build(door, Tileset.LOCKED_DOOR);
  }


//  public void generate() {
//    HashSet<Position> map = new HashSet<>();
//    // generate rooms
//    RoomGenerator gen = new RoomGenerator(this.seed, 100, 100);
//    List<Room> rooms = gen.genRooms(10);
//    // generate hallways
//    List<Room> connected = new ArrayList<>();
//		for (Room room : rooms) {
//      map.addAll(room.floor());
//		}
//    //random pick one room to start
//    connected.add(rooms.get(0));
//    rooms.remove(0);
//    List<Hallway> hallways = new ArrayList<>();
//    //start loop
//    while (rooms.size() != 0) {
//      Integer[] index = Room.nearRoomIndex(rooms, connected);
//      Room r1 = rooms.get(index[0]);
//      Room r2 = connected.get(index[1]);
//      Hallway h = HallwayGenerator.genHallway(r1, r2, this.seed);
//      hallways.add(h);
//      connected.add(r1);
//      rooms.remove(r1);
//    }
//    //build
//    for (Hallway hallway : hallways) {
//      map.addAll(hallway.floorSpace());
//    }
//    List<Position> x = this.wall(map);
//    this.build(map, "floor");
//    List<Position> wall = this.wall(map);
//    this.build(wall, "wall");
//  }
}
