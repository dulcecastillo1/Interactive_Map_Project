import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.Serializable; 

public class Fish implements Runnable, Serializable{

    private int x, y; 
    private Screen sc;
    private BufferedImage fishImage; 
    public Fish(int x, int y, Screen sc){
        this.x = x; 
        this.y = y; 
        this.sc = sc;
        int choose = (int)(Math.random()*3+1);
        if(choose == 1){
            try{
                fishImage = ImageIO.read(new File("purpleFish.png"));
            }catch (IOException e) {} 
        }
        else if(choose == 2){
            try{
                fishImage = ImageIO.read(new File("blueFish.png"));
            }catch (IOException e) {} 
        }
        else if(choose == 3){
            try{
                fishImage = ImageIO.read(new File("yellowFish.png"));
            }catch (IOException e) {} 
        }
    }

    public void drawMe(Graphics g){
        g.drawImage(fishImage, x, y, null); 
    }

    


    //change to move one square at a time (8x8 square); 
    public void run(){
        //moves left across the screen
        while (true){
            x += 8; 
            int choice = (int)(Math.random()*2+1); 
            if(choice == 1){
                y -= 8; 
            }
            else if(choice == 2){
                y += 8; 
            }
            if(y > 400){
                y = 328; 
            }
            if(y < (328)){
                y = 328; 
            }
            if(x > (800)){
                x = 628; 
            }
            try{
                Thread.sleep(400); 
            }catch(InterruptedException ex){
                Thread.currentThread().interrupt(); 
            }
            sc.repaint(); 
        }
    }

}
