package byog.Core;

import java.awt.*;

/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：7/3/2020 2:49 PM
 */
public class Direction {
	int x;
	int y;
	public Direction(int x, int y){
		this.x = x;
		this.y = y;
		this.re();
	}

	public Direction(Position start, Position end){
		this.x = end.widthPosition - start.widthPosition;
		this.y = end.heightPosition - start.heightPosition;
		this.re();
	}

	/*x shadow of the direction**/
	public Direction xShadow(){
		return new Direction(this.x, 0);
	}

	/*y shadow of the direction**/
	public Direction yShadow(){
		return new Direction(0, this.y);
	}

	private void re(){
		if(this.x != 0){
			this.x = this.x / Math.abs(this.x);
		}
		if(this.y != 0){
			this.y = this.y / Math.abs(this.y);
		}
	}
}
