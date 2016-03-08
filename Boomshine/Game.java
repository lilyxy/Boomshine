//Boomshine
//Fangwei Chang and Lily Wang
//11/06/15
//A game about particles and chain reactions

import java.awt.Container;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

class Game extends JFrame implements MouseListener{
    
    //init var
    private int screen;
    private int selection;
    private int level;
    private CardLayout cards;
    private Levels levelselect;
    private Titlescreen title;
    private Credits creds;
    private TxtPanel currtext;
    private BoomshinePanel currlevel;

    Container c;
    
    Reader r=new Reader();//set up class for the 
    
    public static void main(String[] args){
        Game bgame=new Game();//set up frame for users
        bgame.setSize(640, 480);
        bgame.setVisible(true);
        
    }
    
    Game(){
        c=getContentPane();
	cards=new CardLayout();//set up layout
	c.setLayout(cards);
        
        level=0;//set up levels
        
        //set up components of cardlayout
        title=new Titlescreen();
        title.addMouseListener(this);
        
        levelselect=new Levels();
        levelselect.addMouseListener(this);
        
        creds=new Credits();
        creds.addMouseListener(this);
        
        currtext=new TxtPanel("");//textpanels
        currtext.addMouseListener(this);

        currlevel=new BoomshinePanel(1);
        currlevel.addMouseListener(this);
        
        //add to frame
        c.add(title, "Titlescreen");
        c.add(levelselect, "Level Select");
        c.add(creds, "Credits");
        c.add(currtext, "Text");
        c.add(currlevel, "GameLevel");
        
        
        screen=1;//begin with titlescreen
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (screen==1){   //titlescreen
            selection=title.getSelected();//get input
            title.reset();
            if (selection==1){//selected START
                
                screen=5;//go to game screen
                level=1;//set level
                currlevel=new BoomshinePanel(1);
                currlevel.addMouseListener(this);
                currlevel.setVisible(true);
                c.add(currlevel, "GameLevel");//change currlevel and currtext panels for level
                currtext=new TxtPanel(1);
                currtext.addMouseListener(this);
                currtext.setVisible(true);
                c.add(currtext, "Text");
                cards.show(c, "Text");//switch to text
                
            }
            if (selection==2){//select level select
                screen=2;
                cards.show(c, "Level Select");//switch cards
            }
            if (selection==3){//select credits
                screen=3;
                cards.show(c, "Credits");//switch cards
            }
        }
        else if (screen==2){
            selection=levelselect.getSelected();
            if (selection==-1){//to go back
                screen=1;
                cards.show(c, "Titlescreen");
            }
            else if (selection==0){//no selection
            }
            else{//selected a level
                screen=4;
                level=selection;
                currlevel=new BoomshinePanel(level);
                currlevel.addMouseListener(this);
                currlevel.setVisible(true);//create level
                c.add(currlevel, "GameLevel");
                cards.show(c, "GameLevel");
                
                //go to specified level
            }
        }
        else if (screen==3){//if on credits
            selection=creds.getSelected();
            if (selection==-1){//back button
                screen=1;
                cards.show(c, "Titlescreen");//switch card
            }
        }
        else if (screen==4){//game level
            selection=currlevel.getSelection();//check condition
            if (selection==1){//won level
                if (level!=16){//if not last level
                    screen=5;
                    level+=1;//next level
                    
                    currlevel=new BoomshinePanel(level);
                    currlevel.addMouseListener(this);
                    currlevel.setVisible(true);
                    c.add(currlevel, "GameLevel");//set up next level
                    
                    currtext=new TxtPanel(level);
                    currtext.addMouseListener(this);
                    currtext.setVisible(true);
                    c.add(currtext, "Text");
                    
                    cards.show(c, "Text");//swtich
                }
                else{//if it's the last level
                    screen=1;
                    cards.show(c, "Titlescreen");//return to titlescreen
                }
            }
            if (selection==-1){//game lost
                screen=5;//restart and reset level
                
                currlevel=new BoomshinePanel(level);
                currlevel.addMouseListener(this);
                currlevel.setVisible(true);
                c.add(currlevel, "GameLevel");
                
                currtext=new TxtPanel(level);
                currtext.addMouseListener(this);
                currtext.setVisible(true);
                c.add(currtext, "Text");
                               
                cards.show(c, "Text");//go to instructions first
            }
        }
        else{//text panel
            selection=currtext.getSelection();
            if (selection==1){//clicked somewhere
                screen=4;
                cards.show(c, "GameLevel"); //go to game                        
            }
            if (selection==-1){//back button
                screen=1;
                cards.show(c, "Titlescreen");
            }
        } 
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    
}