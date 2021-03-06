import java.awt.Image;

public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

   
   /** The First Planet Constructor. */
	public Planet (double xP, double yP, double xV,
			       double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

    
    /** The Second Planet Constructor. */
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
	    this.yyPos = p.yyPos;
	    this.xxVel = p.xxVel;
	    this.yyVel = p.yyVel;
	    this.mass = p.mass;
	    this.imgFileName = p.imgFileName;
	}

   
   /** Calculated the distance between two planets. */
    public double calcDistance(Planet p) {
    	double dx = p.xxPos - this.xxPos;
    	double dy = p.yyPos - this.yyPos;
		double r = Math.sqrt((dx * dx) + (dy * dy));  
		return r;  
	}   

   
   /** Return the total force. */
    public double calcForceExertedBy(Planet p) {
    	double G = 6.67e-11;
    	double r = calcDistance(p);
    	double f = (G * this.mass * p.mass) / (r * r);
    	return f;
    } 

    /** Return the force exerted in the X directions. */
    public double calcForceExertedByX(Planet p) {
    	double dx = p.xxPos - this.xxPos;
    	double r = calcDistance(p);
    	double f = calcForceExertedBy(p);
    	double fX = f * dx / r;
    	return fX;
    }

    /** Force in Y directions. */
    public double calcForceExertedByY(Planet p) {
    	double dy = p.yyPos - this.yyPos;
    	double r = calcDistance(p);
    	double f = calcForceExertedBy(p);
    	double fY = f * dy / r;
    	return fY;
    }
    

    /** NetForce from X/Y directions. */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        int len = allPlanets.length;
        double NetForceX = 0;

        for (int i = 0; i < len; i ++) {
            if (allPlanets[i] != this) {
                NetForceX += calcForceExertedByX(allPlanets[i]);
            } else {
                i = i + 1;
            }
        }
        return NetForceX;
    }


    public double calcNetForceExertedByY(Planet[] allPlanets) {
        int len = allPlanets.length;
        double NetForceY = 0;

        for (int i = 0; i < len; i ++) {
            if (allPlanets[i] != this) {
                NetForceY += calcForceExertedByY(allPlanets[i]);
            } else {
                i = i + 1;
            }
        }
        return NetForceY;
    }

    /** 'void' no return. 
      *  Velocity and Postion Update from Force. */
    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;

        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }


    /* Draw a planet by itself. */
    public void draw() {
        String fileName = "./images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, fileName);
    }

} /* END OF CLASS */





