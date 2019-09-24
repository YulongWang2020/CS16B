public class NBody{
	public static double readRadius(String n){
		In in = new In(n);
		in.readInt();
		return in.readDouble();
	}

	public static Body[] readBodies(String n){
		In in = new In(n);
		int number = in.readInt();
		double radius = in.readDouble();
		Body[] data = new Body[number];
		for(int i=0 ; i<number;i++){
		data[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),
			in.readDouble(),in.readDouble(),in.readString());
		}
		return data;
	}

	public static void main(String[] args){
		double time = Double.parseDouble(args[0]);
		double d_t = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] planet = readBodies(filename);
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius,radius);
        StdDraw.picture(0,0, "./images/starfield.jpg");

        for(Body p : planet){
        	p.draw();
        }

        double i_t;

        for(i_t = 0.0 ; i_t < time ; i_t = i_t+ d_t){
        	double[] xforce, yforce;
        	xforce = new double[planet.length];
        	yforce = new double[planet.length];

        	for(int i = 0; i < planet.length; i++){
        		xforce[i] = planet[i].calcNetForceExertedByX(planet);
        		yforce[i] = planet[i].calcNetForceExertedByY(planet);
        	}
        	for(int i = 0; i < planet.length; i++){
        		planet[i].update(d_t,xforce[i],yforce[i]);
        	}
        	
        	StdDraw.picture(0,0, "./images/starfield.jpg");

        	for(Body p : planet){
        	p.draw();
        	}
        	StdDraw.show();
			StdDraw.pause(10);
       
		}
		StdOut.printf("%d\n", planet.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planet.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planet[i].xxPos, planet[i].yyPos, planet[i].xxVel,
            planet[i].yyVel, planet[i].mass, planet[i].imgFileName);   
        }
		

}
}