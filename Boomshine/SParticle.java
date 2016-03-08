
import java.awt.Color;
import java.util.ArrayList;

class SParticle extends Particle{
    Color indc=new Color(255,255,255);
    Color orig;
	
    public SParticle(Color tint, int radius){
        super(tint, radius);
        orig=super.colour;
    }
    public void update(){
        super.update();      
    }
    public int getType(){
        return 2;
    }

}
