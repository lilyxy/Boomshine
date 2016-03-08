import java.awt.Color;

//smart particle
class MParticle extends Particle{
    Color indc=new Color(255,255,255);
    Color orig;
	
    public MParticle(Color tint, int radius){
        super(tint, radius);
		//colour particle as white
        orig=Color.WHITE;
        this.colour=Color.WHITE;
    }
    public void update(){
        super.update();      
    }
    public int getType(){
        return 5;
    }
    public Color getColour(){
        return Color.WHITE;
    }
	//set angle for particle to move in
    public void setAngle(int x, int y){
        this.angle=Math.atan((x-this.x)/(y-this.y));
    }
}