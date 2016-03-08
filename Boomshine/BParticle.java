
import java.awt.Color;
import java.util.ArrayList;

//bomb particle
class BParticle extends Particle{
    Color indc=new Color(255,255,255);
    Color orig;
	
    public BParticle(Color tint, int radius){
        super(tint, radius);
        orig=super.colour;
    }
    public void update(){
        super.update();
    }
	
	//flashing colours for bomb particle
	public void changeColor(){
		if (colour.getRed()<200 && colour.getGreen()<200 && colour.getBlue()<200){
			colour=new Color(colour.getRed()+1, colour.getGreen()+1, colour.getBlue()+1);
		}	
	}
    public int getType(){
        return 4;
    }

}