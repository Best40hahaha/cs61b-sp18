
public class NBody {
	

	public static double readRadius(String path) {

		In in = new In (path);
		int planetNum = in.readInt();
		return in.readDouble();

	}

	public static Planet [] readPlanets(String path){

		Planet [] allPlanets;
		allPlanets = new Planet [5];

		In in = new In (path);
		int planetNum = in.readInt();
		double radius = in.readDouble();

		for (int i = 0; i < 5; i++){

			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double m = in.readDouble();
			String file = in.readString();
			allPlanets[i] = new Planet(xPos, yPos, xVel, yVel, m, file);
			
		}
		return allPlanets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet [] allPlanets = readPlanets(filename);
		double r = readRadius(filename);
		int planetNum = allPlanets.length;

		String image = "images/starfield.jpg";
		StdDraw.setScale(-r, r);
		StdDraw.picture(0, 0, image);
		for (Planet p : allPlanets){
			p.draw();
		}

		StdDraw.enableDoubleBuffering();
		double time = 0;
		while (time < T){

			double [] xForces = new double [planetNum];
			double [] yForces = new double [planetNum];
			for (int i = 0; i < planetNum; i++){
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
			}
			for (int i = 0; i < planetNum; i++){
				allPlanets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, image);
			for (Planet p : allPlanets){
				p.draw();
			}
			StdDraw.pause(10);
			time = time + dt;
			StdDraw.show();
		}
		StdOut.printf("%d\n", allPlanets.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < allPlanets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
		                  allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
		}

	}
	
}