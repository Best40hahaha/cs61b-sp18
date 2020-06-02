
public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double gConstant = 6.67e-11;
	
	// Constructor
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;

	}

	public double calcDistance(Planet p){
		return Math.sqrt((p.xxPos - this.xxPos)*(p.xxPos - this.xxPos) + (p.yyPos - this.yyPos)*(p.yyPos - this.yyPos));
	}

	public double calcForceExertedBy(Planet p){
		
		return (gConstant * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));  
	}

	public double calcForceExertedByX(Planet p){

		double totalForce = this.calcForceExertedBy(p);
		return totalForce * (p.xxPos - this.xxPos) / this.calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){

		double totalForce = this.calcForceExertedBy(p);
		return totalForce * (p.yyPos - this.yyPos) / this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet [] allPlanets){

		double force = 0;
		for (Planet p : allPlanets){
			if(!this.equals(p)){
				force = force + this.calcForceExertedByX(p);
			}
			
		}
		return force;

	}

	public double calcNetForceExertedByY(Planet [] allPlanets){

		double force = 0;
		for (Planet p : allPlanets){
			if (!this.equals(p)){ 
				// you can not calculate the force with yourself because the distance will be divided
				force = force + this.calcForceExertedByY(p);	
			}
			
		}
		return force;

	}

	public void update(double time, double xForce, double yForce){

		double accerlationX = xForce / this.mass; 
		double accerlationY = yForce / this.mass;

		this.xxVel = this.xxVel + accerlationX * time;
		this.yyVel = this.yyVel + accerlationY * time;

		this.xxPos = this.xxPos + this.xxVel * time;
		this.yyPos = this.yyPos + this.yyVel * time;

	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}

	
}