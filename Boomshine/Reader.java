import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

class Reader{
    static int[][] data;
    
    //level text
    static String[] text={"",
        
        "Pop 4/10 particles to win. (Click to continue)", 
        
        "Pop 7/15 particles to win.",
        
        "Introducing the super particle! It produces a larger explosion. Pop 12/18 particles to win. ",
        
        "Pop 20/26 particles to win.",
        
        "Pop 40/50 particles to win.",
        
        "Introducing the recursive particle! It generates a star when exploded. Pop 14/18 particles to win.",
        
        "Pop 30/36 particles to win.",
        
        "Pop 16/20 particles to win.",
        
        "Introducing the bomb particle! It produces multiple explosions. Pop 53/60 particles to win.",
        
        "Pop 24/29 particles to win.",

        "Pop 27/33 particles to win.",
        
        "Introducing the smart particle! It will run to avoid explosions. Pop 25/32 particles to win",
        
        "Pop 25/30 particles to win.",
        
        "Pop 35/42 particles to win.",
        
        "Pop 30/40 particles to win.",
        
        "Pop 70/74 particles to win."
        
        };
    //static ArrayList<String> text;
    
    Reader(){
        try{
            
            data=new int[17][11];
            
            //read level data from file and add to array
            Scanner level=new Scanner(new File("data.txt"));
            for (int i=1; i<=16; i++){
               // currline=level.nextLine();
                for (int j=1; j<=10; j++){
                    data[i][j]=level.nextInt();
                    
                    
                }
            }
            
        }
       
        catch(Exception e){
            System.out.print(e);
            
        }
        
        
        /*
        text.add("Level 1: 10 normal particles\n" +
                    "Pop 4/10 particles to win.\n",
                "");
        */
    }
    
}
