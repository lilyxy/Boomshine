import java.awt.Color;
import java.awt.Container;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

class Credits extends JPanel implements MouseListener{
    
    private int selection;
    private Container c;
    private Image bg;
    
    
    Credits(){
        //init mouse listener and image
        selection=0;
        addMouseListener(this);
        bg=new ImageIcon("credits.png").getImage();
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //draw image in panel
        g.drawImage(bg, 0, 0, null);
    }
    
    public int getSelected(){
        return selection;
    }
    
    public void reset(){
        selection=0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        
        //if clicked back
        if ((x>280&&x<360)&&(y>400)){
            selection=-1;  
          
        }
        //click anywhere else
        else{
            selection=0;
        }
       
    }

    //abstract methods
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