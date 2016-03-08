
import javax.swing.*;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.Graphics;




class TxtPanel extends JPanel implements MouseListener{
	private JLabel label;
	private String text;
        private int selection;
        private Image bg;
	
        TxtPanel(int ind){ //constructor to call static data 
            this(Reader.text[ind]);
        }
	TxtPanel(String text){//load images and string and mouse listening
                selection=0;
                this.text=text;
		label = new JLabel(text);
		label.setFont(new Font("Arial", Font.PLAIN, 24));
                
                addMouseListener(this);
                bg=new ImageIcon("back.png").getImage();
        }
        
        public void paint(Graphics g){//render string
            super.paint(g);
            g.drawImage(bg, 0, 0, null);
            g.drawString(text, 50, 200);
            repaint();
        }
        


		@Override
        public void mouseClicked(MouseEvent e) { 
            
            //get co-ords
            int x=e.getX();
            int y=e.getY();
            
            //if clicked on back button
            if ((x>280&&x<360)&&(y>400)){
                selection=-1;
            }
            else{
                //clicking anywhere
                selection=1;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) {  }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }


    
    public int getSelection(){
        //accessor for selection
        return selection;
        
    }
}