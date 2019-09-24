public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Body(double xp, double yP, double xV, double yV, double m,String img){
		xxPos = xp;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}
	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;

	}
	public double calcDistance(Body p){
		return  Math.sqrt(Math.pow((xxPos-p.xxPos),2)+Math.pow((yyPos-p.yyPos),2));
	}

	public double calcForceExertedBy(Body p){
		double G = 6.67e-11;
		return (G*mass*p.mass)/Math.pow(calcDistance(p),2) ;

	}

	public double calcForceExertedByX(Body p){

		return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
	}
	public double calcForceExertedByY(Body p){

		return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
	}

	public double calcNetForceExertedByX(Body[] lists){
		double sum = 0;
		for(Body element : lists){
			if(!this.equals(element)){
			sum = calcForceExertedByX(element)+sum;
			}
		}
		return sum; 
	}
	public double calcNetForceExertedByY(Body[] lists){
		double sum = 0;
		for(Body element : lists){
			if(!this.equals(element)){
			sum = calcForceExertedByY(element)+sum;
			}
		}
		return sum; 
	}
	public void update(double dt, double xF, double yF){
		double accX = xF/mass;	
		double accY = yF/mass;
		xxVel = xxVel + accX*dt;
		yyVel = yyVel + accY*dt;
		xxPos = xxPos + xxVel*dt;
		yyPos = yyPos + yyVel*dt;
	}
	public void draw(){
		System.out.println(imgFileName);
		StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
	}
}