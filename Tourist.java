import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.Serializable; 

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Tourist implements Serializable{
    private int x, y; 
    private int gridRow, gridCol; 
    private BufferedImage touristIcon; 
    private BufferedImage touristUp; 
    private BufferedImage touristDown; 
    private BufferedImage touristLeft; 
    private BufferedImage touristRight; 

    public Tourist(int x, int y){
        this.x = x; 
        this.y = y; 
        gridCol = x/8; 
        gridRow = y/8; 

        try{
            touristIcon = ImageIO.read(new File("touristIcon.png"));
        }catch (IOException e) {} 

        try{
            touristUp = ImageIO.read(new File("touristUp.png"));
        }catch (IOException e) {} 

        try{
            touristDown = ImageIO.read(new File("touristDown.png"));
        }catch (IOException e) {} 

        try{
            touristLeft = ImageIO.read(new File("touristLeft.png"));
        }catch (IOException e) {} 

        try{
            touristRight = ImageIO.read(new File("touristRight.png"));
        }catch (IOException e) {} 
    }

    public void drawTourist(Graphics g){
        g.drawImage(touristIcon, x, y-4, null);  
    }

    public void drawTouristUp(Graphics g){
        g.drawImage(touristUp, x, y-4, null);
    }

    public void drawTouristDown(Graphics g){
        g.drawImage(touristDown, x, y-4, null); 
    }

    public void drawTouristLeft(Graphics g){
        g.drawImage(touristLeft, x, y-4, null); 
    }

    public void drawTouristRight(Graphics g){
        g.drawImage(touristRight, x, y-4, null); 
    }

    public void changeX(int change){
        this.x = x+(change); 
    }
    public void changeY(int change){
        this.y = y+(change); 
    }

    public void errorSound(){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("error.wav").getAbsoluteFile()));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        } 
    }

    
    public void landmark1Sound(){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("bells.wav").getAbsoluteFile()));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        } 
    }

    public void landmark2Sound(){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("birdClap.wav").getAbsoluteFile()));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        } 
    }

    public void landmark3Sound(){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("chimes.wav").getAbsoluteFile()));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        } 
    }

    public void landmark4Sound(){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("pop.wav").getAbsoluteFile()));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        } 
    }

    


    public void moveLeft(MyHashTable map){
        int leftRow = gridRow-1; 
        Location location = new Location(leftRow, gridCol); 
        DLList<MapObject> square = map.get(location); 
        if(square.get(square.size() - 1).canWalkOn() == true){
            gridRow = leftRow; 
            changeX(-8); 
            
            
            if(square.get(square.size()-1).surrounding1() == true){
                landmark1Sound(); 
            }
            if(square.get(square.size()-1).surrounding2() == true){
                landmark2Sound(); 
            }
            if(square.get(square.size()-1).surrounding3() == true){
                landmark3Sound(); 
            }
            if(square.get(square.size()-1).surrounding4() == true){
                landmark4Sound(); 
            }
            
        }
        else{
            errorSound();
        }
    }

    public void moveRight(MyHashTable map){
        int rightRow = gridRow+1; 
        Location location = new Location(rightRow, gridCol); 
        DLList<MapObject> square = map.get(location); 
        if(square.get(square.size() - 1).canWalkOn() == true){
            gridRow = rightRow; 
            changeX(8); 
            
            if(square.get(square.size()-1).surrounding1() == true){
                landmark1Sound(); 
            }
            if(square.get(square.size()-1).surrounding2() == true){
                landmark2Sound(); 
            }
            if(square.get(square.size()-1).surrounding3() == true){
                landmark3Sound(); 
            }
            if(square.get(square.size()-1).surrounding4() == true){
                landmark4Sound(); 
            }
            
        }
        else{
            errorSound();
        }
    }

    public void moveUp(MyHashTable map){
        int upCol = gridCol-1; 
        Location location = new Location(gridRow, upCol); 
        DLList<MapObject> square = map.get(location); 
        if(square.get(square.size() - 1).canWalkOn() == true){
            gridCol = upCol; 
            changeY(-8); 
            
            if(square.get(square.size()-1).surrounding1() == true){
                landmark1Sound(); 
            }
            if(square.get(square.size()-1).surrounding2() == true){
                landmark2Sound(); 
            }
            if(square.get(square.size()-1).surrounding3() == true){
                landmark3Sound(); 
            }
            if(square.get(square.size()-1).surrounding4() == true){
                landmark4Sound(); 
            }
            
        }
        else{
            errorSound();
        }
    }

    public void moveDown(MyHashTable map){
        int downCol = gridCol+1; 
        Location location = new Location(gridRow, downCol); 
        DLList<MapObject> square = map.get(location); 
        if(square.get(square.size() - 1).canWalkOn() == true){
            gridCol = downCol; 
            changeY(8); 
            
            if(square.get(square.size()-1).surrounding1() == true){
                landmark1Sound(); 
            }
            if(square.get(square.size()-1).surrounding2() == true){
                landmark2Sound(); 
            }
            if(square.get(square.size()-1).surrounding3() == true){
                landmark3Sound(); 
            }
            if(square.get(square.size()-1).surrounding4() == true){
                landmark4Sound(); 
            }
            
        }
        else{
            errorSound();
        }
    }

    //return the row/col location of the tourist 
    public int getCol(){
        return gridCol; 
    }

    public int getRow(){
        return gridRow; 
    }
    
}
