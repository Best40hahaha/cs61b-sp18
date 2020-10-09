package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：7/1/2020 10:20 PM
 */
public class Hallway {
	/*all of the hallway is height changed first**/
	private Position startPos;
	private Position endPos;
	private Position infection;
	private Direction direction;


	public Hallway(Position start, Position end){
		this.startPos = start;
		this.endPos = end;
		this.direction = new Direction(start, end);
		if(start.widthPosition == end.widthPosition || start.heightPosition == end.heightPosition){
			infection = null;
		}else {
			this.infection = new Position(this.startPos.widthPosition, this.endPos.heightPosition);
		}

	}

	public Position getStartPos() {
		return this.startPos;
	}

	public Position getEndPos() {
		return this.endPos;
	}

	public Direction getDirection(){
		return this.direction;
	}


	private List<Position> way(Position start, Position end){
		List<Position> w = new ArrayList<>();
		Position current = start;
		w.add(start);
		while(!current.equals(end)){
			while(!(current.heightPosition == end.heightPosition)){
				Direction d = new Direction(0, this.direction.y);
				current = current.move(d);
				w.add(current);
			}
			while(!(current.widthPosition == end.widthPosition)){
				Direction d = new Direction(this.direction.x, 0);
				current = current.move(d);
				w.add(current);
			}
		}
		return w;
	}

	/*return the floor space this hallway occupied**/
	public List<Position> floorSpace(){
		return way(this.startPos, this.endPos);
	}

	private List<Direction> momentum(List<Position> track){
		List<Direction> d = new ArrayList<>();
		for(int i = 1; i < track.size(); i += 1){
			Direction mo = new Direction(track.get(i - 1), track.get(i));
			d.add(mo);
		}
		return d;
	}

	/*return the total space this hallway occupied**/
	public List<Position> totalSpace(){
		List<Position> t = this.floorSpace();
		t.addAll(this.wallSpace());
		return t;
	}

	/*return the wall space this hallway occupied**/
	public List<Position> wallSpace(){
		List<Position> w = new ArrayList<>();
		List<Position> f = this.floorSpace();
		List<Direction> mo = this.momentum(floorSpace());
		Position nearStart;
		Position nearEnd;
		if(mo.size() == 0){
			nearStart = this.startPos;
			nearEnd = this.endPos;
		}else{
			nearStart = this.startPos.moveBack(mo.get(0));
			nearEnd = this.endPos.move(mo.get(mo.size() - 1));
		}

		for(Position i : f){
			for(Position j : i.around()){
				if(!(j.include(w) || j.include(f) || j.equals(nearStart) || j.equals(nearEnd))){
					w.add(j);
				}
			}
		}
		if(this.infection == null){
			return w;
		}
		// make the infection part more juicy
		List<Position> angle = new ArrayList<>();
		angle.add(new Position(this.infection.widthPosition - 1, this.infection.heightPosition + 1));
		angle.add(new Position(this.infection.widthPosition - 1, this.infection.heightPosition - 1));
		angle.add(new Position(this.infection.widthPosition + 1, this.infection.heightPosition + 1));
		angle.add(new Position(this.infection.widthPosition + 1, this.infection.heightPosition - 1));
		for(Position i : angle){
			if((!(i.include(w) || i.include(f))) && i.within(Game.WIDTH, Game.HEIGHT)){
				w.add(i);
			}
		}
		return w;
	}

	public static void main(String[] args) {
		Position start = new Position(10,5);
		Position end = new Position(5,10);
		Hallway h = new Hallway(start, end);

		for(Position i : h.floorSpace()){
			System.out.println(i);
		}
	}
}
