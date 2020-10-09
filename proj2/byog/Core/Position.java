package byog.Core;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：7/1/2020 4:03 PM
 */
/*Position is only for the combination of width and height**/
public class Position{
	int widthPosition;
	int heightPosition;
	public Position(int widthPosition, int heightPosition){
		this.widthPosition = widthPosition;
		this.heightPosition = heightPosition;
	}

	public boolean include(Iterable<Position> p){
		for(Position j: p){
			if(this.equals(j)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return widthPosition == position.widthPosition &&
				heightPosition == position.heightPosition;
	}

	@Override
	public int hashCode() {
		return Objects.hash(widthPosition, heightPosition);
	}

	public static List<Integer> widthDiffPoint(Position p1, Position p2){
		List<Integer> w = new ArrayList<>();
		int start = Math.min(p1.widthPosition, p2.widthPosition);
		int end = Math.max(p1.widthPosition, p2.widthPosition);
		for (int i = start; i <= end; i += 1){
			w.add(i);
		}
		return w;
	}

	public static List<Integer> heightDiffPoint(Position p1, Position p2){
		List<Integer> w = new ArrayList<>();
		int start = Math.min(p1.heightPosition, p2.heightPosition);
		int end = Math.max(p1.heightPosition, p2.heightPosition);
		for (int i = start; i <= end; i += 1){
			w.add(i);
		}
		return w;
	}

	/*returns the position around it**/
	public List<Position> around(){
		List<Position> a = new ArrayList<>();
		if(this.widthPosition > 0){
			Position left = new Position(this.widthPosition - 1, this.heightPosition);
			a.add(left);
		}
		if(this.heightPosition > 0){
			Position bottom = new Position(this.widthPosition, this.heightPosition -1);
			a.add(bottom);
		}
		if(this.widthPosition < Game.WIDTH){
			Position right = new Position(this.widthPosition + 1, this.heightPosition);
			a.add(right);
		}
		if(this.heightPosition < Game.HEIGHT){
			Position top = new Position(this.widthPosition, this.heightPosition + 1);
			a.add(top);
		}
		return a;
	}


	/*check if a position within a canvas**/
	public boolean within(int width, int height){
		return (this.widthPosition >= 0) && (this.widthPosition < width) && (this.heightPosition >= 0) && (this.heightPosition < height);
	}

	public Position move(Direction d){
		return new Position(this.widthPosition + d.x, this.heightPosition + d.y);
	}

	public Position moveBack(Direction d){
		return new Position(this.widthPosition - d.x, this.heightPosition - d.y);
	}

	/*define a print method for typing each position**/
	@Override
	public String toString(){
		return ("width position: " + this.widthPosition + " height position: " + this.heightPosition);
	}




	public static void main(String[] args) {
		Position p = new Position(1,1);
		List<Position> a = p.around();
		for(Position i : a){
			System.out.println(i);;
		}
	}
}

