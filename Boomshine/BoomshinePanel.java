
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer.*;

class BoomshinePanel extends JPanel implements ActionListener, MouseListener{ 
    Container c;
    Graphics g;
	
    private ArrayList<Boom> booms;
    private ArrayList<Particle> dots;
    private ArrayList<SParticle> sDots; 
    private ArrayList<RParticle> rDots;
    private ArrayList<BParticle> bDots;
    private ArrayList<MParticle> mDots;
    private ArrayList<Particle> dotscopy;
    private Reader r=new Reader();
	
    private Timer btime;
    private Color tint, invert;
    private int clicks=1, target, xsum, ysum, total, delay=-1, selection=0;
    private boolean fade=false;
    
    public BoomshinePanel(){}
	
    public BoomshinePanel(int numdots, int numsuper, int numrecur, int numbomb, int numsmart, int speed, int target, Color tint){
        //list of particles
		sDots = new ArrayList<SParticle>();
		rDots = new ArrayList<RParticle>();
		bDots = new ArrayList<BParticle>();
		mDots = new ArrayList<MParticle>();
		
        dots=new ArrayList<Particle>();
        booms=new ArrayList<Boom>();
        
        
		//initialize particles
        for (int i=0; i<numdots; i++)					
			dots.add(new Particle(tint,5));
		for (int i=0; i<numsuper; i++)
			sDots.add(new SParticle(tint,8));
		for (int i=0; i<numrecur; i++)
			rDots.add(new RParticle(tint,8));
		for (int i=0; i<numbomb; i++)
			bDots.add(new BParticle(tint,8));
		for (int i=0; i<numsmart; i++)
			mDots.add(new MParticle(tint,8));
                
        total=numdots+numsuper+numrecur+numbomb+numsmart;
		
        addMouseListener(this);
        btime=new Timer(10, this);
        btime.start();
        this.tint=tint;
        invert=new Color(40, 40, 40);

        this.target=target;
    }
    
    public BoomshinePanel(int ind){
        this(Reader.data[ind][1],Reader.data[ind][2],Reader.data[ind][3],Reader.data[ind][4],
             Reader.data[ind][5],Reader.data[ind][6],Reader.data[ind][7],
             new Color(Reader.data[ind][8],Reader.data[ind][9],Reader.data[ind][10]));
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(invert);
        g.fillRect(0,0,640,480);
        g.setColor(tint);
        g.drawString(""+getBooms()+"/"+target, 20, 35);
		
		//normal particle
        for (Particle p: dots){
            g.setColor(p.getColour());
            g.fillOval(p.getX()-p.getRadius(), p.getY()-p.getRadius(), p.getRadius()*2, p.getRadius()*2);
        }
		//super particle
        for (SParticle sP: sDots){
            g.setColor(sP.getColour());
            g.fillOval(sP.getX()-sP.getRadius(), sP.getY()-sP.getRadius(), sP.getRadius()*2, sP.getRadius()*2);
        }		
		//recursion particle
        for (RParticle rP: rDots){
            g.setColor(rP.getColour());
            g.drawOval(rP.getX()-rP.getRadius(), rP.getY()-rP.getRadius(), rP.getRadius()*2, rP.getRadius()*2);
			g.fillOval(rP.getX()-4, rP.getY()-4, 8, 8);
        }			
		//bomb particle
        for (BParticle bP: bDots){
            g.setColor(bP.getColour());
            g.fillOval(bP.getX()-bP.getRadius(), bP.getY()-bP.getRadius(), bP.getRadius()*2, bP.getRadius()*2);
        }	
		//smart particle
        for (MParticle mP: mDots){
            g.setColor(mP.getColour());
            g.fillOval(mP.getX()-mP.getRadius(), mP.getY()-mP.getRadius(), mP.getRadius()*2, mP.getRadius()*2);
        }
		//explosion
        for (Boom b: booms){
            g.setColor(b.getColour());
            g.fillOval(b.getX()-b.getRadius(),b.getY()-b.getRadius(),b.getRadius()*2, b.getRadius()*2);
        }
    }
	
    public void checkColl(){
		//check collisions between particles and explosions
		
        ArrayList<Boom> toAdd=new ArrayList();
        ArrayList <Particle> toRem=new ArrayList();
		ArrayList <SParticle> toRemS=new ArrayList();
		ArrayList <RParticle> toRemR=new ArrayList();
		ArrayList <BParticle> toRemB=new ArrayList();
		ArrayList <MParticle> toRemM=new ArrayList();
		
        for (Boom b: booms){
            for (Particle p: dots){
                int xdelta=b.getX()-p.getX();
                int ydelta=b.getY()-p.getY();
                int radiisum=b.getRadius()+p.getRadius();
                if (Math.pow(xdelta*xdelta+ydelta*ydelta,0.5)<radiisum){
                    
                    toAdd.add(new Boom(p.getX(),p.getY(), 40,p.getColour()));
                    toRem.add(p);
                }
            }
			
            for (SParticle sP: sDots){
                int xdelta=b.getX()-sP.getX();
                int ydelta=b.getY()-sP.getY();
                int radiisum=b.getRadius()+sP.getRadius();
                if (Math.pow(xdelta*xdelta+ydelta*ydelta,0.5)<radiisum){
                    
                    toAdd.add(new Boom(sP.getX(),sP.getY(),65,sP.getColour()));
                    toRemS.add(sP);
                }
            }			
			
            for (RParticle rP: rDots){
                int xdelta=b.getX()-rP.getX();
                int ydelta=b.getY()-rP.getY();
                int radiisum=b.getRadius()+rP.getRadius();
                if (Math.pow(xdelta*xdelta+ydelta*ydelta,0.5)<radiisum){
                    
                    toAdd.add(new Boom(rP.getX(),rP.getY(),30,rP.getColour()));
					
					//generate recursive explosion
					recursiveBoom(rP.getX(), rP.getY(), 35, toAdd, 1, rP.getColour());
					recursiveBoom(rP.getX(), rP.getY(), 35, toAdd, 2, rP.getColour());
					recursiveBoom(rP.getX(), rP.getY(), 35, toAdd, 3, rP.getColour());
					recursiveBoom(rP.getX(), rP.getY(), 35, toAdd, 4, rP.getColour());
					
                    toRemR.add(rP);
                }
            }

            for (BParticle bP: bDots){
                int xdelta=b.getX()-bP.getX();
                int ydelta=b.getY()-bP.getY();
                int radiisum=b.getRadius()+bP.getRadius();
                if (Math.pow(xdelta*xdelta+ydelta*ydelta,0.5)<radiisum){
                    
                    toAdd.add(new Boom(bP.getX(),bP.getY(),40,bP.getColour()));
					
					//generate super explosion
					superBoom(40, bP.getX(),bP.getY(), toAdd, bP.getColour());
                    toRemB.add(bP);
                }
            }				
			
            for (MParticle mP: mDots){
                int xdelta=b.getX()-mP.getX();
                int ydelta=b.getY()-mP.getY();
                int radiisum=b.getRadius()+mP.getRadius();
                if (Math.pow(xdelta*xdelta+ydelta*ydelta,0.5)<radiisum){
         
                    toAdd.add(new Boom(mP.getX(), mP.getY(), 40, mP.getColour()));
                    toRemM.add(mP);
                }
            }			
			
        }
		
		//add explosions
        for (Boom b: toAdd){
            booms.add(b);
        }
		
		//delete destroyed particles 
        for (Particle p: toRem){
            dots.remove(p);
        }
        for (SParticle sP: toRemS){
            sDots.remove(sP);
        }
        for (RParticle rP: toRemR){
            rDots.remove(rP);
        }
        for (BParticle bP: toRemB){
            bDots.remove(bP);
	}
	for (MParticle mP: toRemM){
            mDots.remove(mP);
	}        
    }
	
    
    public void toExplode(Particle hit, ArrayList<Particle> outside){
        ArrayList<Particle> toRemove=new <Particle> ArrayList();
        for (Particle p: outside){
            int delx=p.getX()-hit.getX();
            int dely=p.getY()-hit.getY();
            if (Math.pow(delx*delx+dely*dely, 0.5)<10){
                toRemove.add(p);
            }
        }
        
        for (Particle p: toRemove){
            outside.remove(p);
        }
        for (Particle p: toRemove){
            toExplode(p, outside);
        }
    }
            
	//generate explosion for bomb particle  
	public void superBoom(int size, int x, int y, ArrayList toAdd, Color tint){
		for (int i=0; i<12; i++){
			double angle = 0;
			
			int distance;
			if (i < 6)
				distance = size;
			else
				distance = (int)(size*1.7);
			
			angle = i*(Math.PI/3);
			int newX = (int)(x + distance*Math.cos(angle));
			int newY = (int)(y + distance*Math.sin(angle));
			
			//generate random colours for explosions 
			int red=(int)((Math.random()*256+tint.getRed()*2)/3);
			int green=(int)((Math.random()*256+tint.getGreen()*2)/3);
			int blue=(int)((Math.random()*256+tint.getBlue()*2)/3);
			Color colour=new Color(red, green, blue);	
			
			//generate explosion
			if (i < 6)
				toAdd.add(new Boom(newX,newY,30, colour));
			else 
				toAdd.add(new Boom(newX,newY,20, colour));
		}
	}
	
	//generate explosion for recursive particle
	public void recursiveBoom(int x, int y, int size, ArrayList toAdd, int quadrant, Color tint){
		if (size <= 10)
			return;
		
		//generate random colors for explosions 
		int red=(int)((Math.random()*256+tint.getRed()*2)/3);
                int green=(int)((Math.random()*256+tint.getGreen()*2)/3);
            	int blue=(int)((Math.random()*256+tint.getBlue()*2)/3);
                Color colour=new Color(red, green, blue);
		
		//generate stream of explosions heading each of 4 directions
		if (quadrant == 1){
			toAdd.add(new Boom(x+size, y-size,size-5, colour ));
			recursiveBoom(x+size, y-size,size-5, toAdd,1, tint);
		}
		else if (quadrant == 2){
			toAdd.add(new Boom(x-size, y-size,size-5, colour ));
			recursiveBoom(x-size, y-size,size-5, toAdd, 2, tint);
		}
		else if (quadrant == 3){
			toAdd.add(new Boom(x-size, y+size,size-5, colour ));
			recursiveBoom(x-size, y+size,size-5, toAdd, 3, tint);
		}		
		else{
			toAdd.add(new Boom(x+size, y+size,size-5, colour ));
			recursiveBoom(x+size, y+size,size-5, toAdd, 4, tint);
		}		
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Particle p: dots){
            p.update();
        }
        for (SParticle sP: sDots){
            sP.update();
        }
        for (RParticle rP: rDots){
            rP.update();
        }
        for (BParticle bP: bDots){
            bP.update();
        }
        for (MParticle mP: mDots){
            mP.update();
        }
        for (Boom b: booms){
            b.update();
        }
        checkColl();
        this.repaint();
        if (outcome()==1){
            fade=true;
        }
        if (fade){
            if (invert.getRed()<220&&invert.getGreen()<220&&invert.getBlue()<220){
                invert=new Color(invert.getRed()+1, invert.getGreen()+1, invert.getBlue()+1);
            }
            else{
                fade=false;
                selection=1;
                delay=-1;
                
            }
        }
        else{
            if (outcome()==2 && delay==-1){
                delay=100;
                
            }
            if (delay>0){
                delay-=1;
            }
            if (delay==0){
                selection=-1;
            }
        }
        //total=sDots.size()+rDots.size()+bDots.size()+mDots.size()+dots.size();
    }

    @Override
    public void mouseClicked(MouseEvent e) { 
        if (clicks>0){
            clicks-=1;
            Boom click=new Boom(e.getX(), e.getY(), 40, tint);
            booms.add(click);
            
        
            if (!mDots.isEmpty()){
                dotscopy=new ArrayList();
                for (Particle p: dots){
                    dotscopy.add(p);
                }
                Particle temp=new Particle();
                temp.setX(e.getX());
                temp.setY(e.getY());
                
                toExplode(temp,dotscopy);
                
                xsum=0;
                ysum=0;
                for (Particle p: dotscopy){
                    xsum+=p.getX();
                    ysum+=p.getY();
                }
                xsum/=dotscopy.size();
                ysum/=dotscopy.size();
                
                for (MParticle m: mDots){
                    m.setAngle(xsum, ysum);
                }
            }
        }
        /*else{
            if (getBooms()<target){
                delay=100;
            }
        }*/
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {  }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
    
    public int outcome(){
        int outcome=1;
        /*for (Boom b: booms){
            if (b.getRadius()>0){
                outcome=0;
                break;
            }
        }*/
        
        if (getBooms()<target){
            outcome=0;
            if (clicks==0){
                for (Boom b: booms){
                    if (b.getRadius()>0){
                        outcome=3;
                        break;
                    }
                }
                if (outcome!=3){
                    outcome=2;
                }
            }
            
        }
        
        return outcome;
    }
    public int getSelection(){
        
        return selection;
    }
    public int getBooms(){
        //return booms.size()-clicks;
        
        return total-(sDots.size()+rDots.size()+bDots.size()+mDots.size()+dots.size());
        
    }
    public int getTarget(){
        return target;
    }
}
