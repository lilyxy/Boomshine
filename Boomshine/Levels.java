import java.awt.Color;
import java.awt.Container;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

class Levels extends JPanel implements MouseListener{
    
    private int selection;
    private Container c;
    private Image bg;
    
    //constructor and setting up image
    Levels(){
        selection=0;
        addMouseListener(this);
        bg=new ImageIcon("levelselect.png").getImage();
        
    }
    
    public void paintComponent(Graphics g){//render image
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);
    }
    
    public int getSelected(){
        return selection;
    }
    
    public void reset(){//clear selection
        selection=0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        selection=0;
        //handle clicking in the grid of levels
        for (int i=0; i<4; i++){//columns
            for (int j=0; j<4; j++){//rows
                int lx=i*152+20;
                int ly=j*75+100;
                int rx=lx+140;
                int ry=ly+65;
                if ((x>lx&&x<rx)&&(y>ly&&y<ry)){
                    selection=j*4+i+1;//assign selection
                }
                       
            }
        }
        if ((x>280&&x<360)&&(y>400)){
            selection=-1;//back button
        }
       
    }

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