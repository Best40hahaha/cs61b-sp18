package byog.Core;

import java.lang.reflect.GenericArrayType;
import java.util.Arrays;
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
			this.widthPosition = Math.max(widthPosition, 0);
			this.widthPosition = Math.min(widthPosition, Game.WIDTH);

			this.heightPosition = Math.max(heightPosition, 0);
			this.heightPosition = Math.min(heightPosition, Game.HEIGHT);

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
	public List<Position> around(int width, int height){
		List<Position> space = new ArrayList<>();
		int [] paras = {-1, 0, 1};
		for (int i : paras) {
			for (int j : paras) {
				if (i == 0 & j ==0) {
					continue;
				}
				Position p = new Position(this.widthPosition + i, this.heightPosition + j);
				if (p.within(width, height)) {
					space.add(p);
				}
			}
		}

		return space;
	}

	/*returns the position around it**/
	public List<Position> around(int width, int height, String category){

		List<String> categoryList = Arrays.asList("all", "cross");
		assert categoryList.contains(category);
		List<Position> space = new ArrayList<>();
		int [] paras = {-1, 0, 1};
		for (int i : paras) {
			for (int j : paras) {
				if (i == 0 & j ==0) {
					continue;
				}
				if (category.equals("cross") && i*j != 0) {
					continue;
				}
				Position p = new Position(this.widthPosition + i, this.heightPosition + j);
				if (p.within(width, height)) {
					space.add(p);
				}
			}
		}

		return space;
	}

	/*returns the position around it**/
	public List<Position> around(String category){

		int width = Game.WIDTH;
		int height = Game.HEIGHT;
		List<String> categoryList = Arrays.asList("all", "cross");
		assert categoryList.contains(category);
		List<Position> space = new ArrayList<>();
		int [] paras = {-1, 0, 1};
		for (int i : paras) {
			for (int j : paras) {
				if (i == 0 & j ==0) {
					continue;
				}
				if (category.equals("cross") && i*j != 0) {
					continue;
				}
				Position p = new Position(this.widthPosition + i, this.heightPosition + j);
				if (p.within(width, height)) {
					space.add(p);
				}
			}
		}

		return space;
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
		Position p = new Position(1,0);
		List<Position> a = p.around(80,30, "cross");
		for(Position i : a){
			System.out.println(i);;
		}
	}
}

