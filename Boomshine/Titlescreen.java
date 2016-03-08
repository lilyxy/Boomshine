import java.awt.Color;
import java.awt.Container;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

class Titlescreen extends JPanel implements MouseListener{
    
    private int selection;
    private Container c;
    private Image bg;
    
    
    Titlescreen(){
        selection=0;
        //mouse listening and set up image
        addMouseListener(this);
        bg=new ImageIcon("menu.png").getImage();
        
    }
    //draw the image 
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);
    }
    
    public int getSelected(){
        return selection;
        
    }
    //clear selection;
    public void reset(){
        selection=0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        int x=e.getX();
        int y=e.getY();
        
        if (x>130&&x<495){
            //play game
            if (y>182&&y<238){
                selection=1;
            }
            //level select
            if (y>252&&y<308){
                selection=2;
            }
            //credits
            if (y>322&&y<378){
                selection=3;
            }
        }
        
    }

    
    //abstract method
    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {  }

    @Override
    public void mouseExited(MouseEvent e) {
   
    }
}





