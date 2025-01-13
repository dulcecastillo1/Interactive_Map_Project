import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.Serializable; 

public class Boat implements Runnable, Serializable{

    private int x, y; 
    private Screen sc;
    private Color blue; 
    private Color brown; 
    private Color tan; 
    public Boat(int x, int y, Screen sc){
        this.x = x; 
        this.y = y; 
        this.sc = sc;
        blue = new Color(167, 199, 231);
        brown = new Color(92, 64, 51);
        tan = new Color(194, 178, 128); 
    }

    public void drawMe(Graphics g){
        g.setColor(brown); 
        g.fillRect(x+3, y-3, 2, 9);

        g.setColor(tan); 
        g.fillArc(x, y-1, 11, 13, 180, 180); 

        //create 2 arrays to hold the 3 vertices (x,y)
        int [] xArray = new int[3]; 
        int [] yArray = new int[3]; 

        //Set up coordinates of the vertices 
        xArray[0] = x+5; 
        xArray[1] = x+9; 
        xArray[2] = x+5;
        yArray[0] = y-3; 
        yArray[1] = y; 
        yArray[2] = y+3; 

        //Set color to draw the polygon 
        g.setColor(Color.YELLOW); 
        //Use fillPolygon to fill the triangle 
        g.fillPolygon(xArray, yArray, 3); 

    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
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
            if(y > 800){
                y = 600; 
            }
            if(y < (600)){
                y = 600; 
            }
            if(x > (24*8)){
                x = 0; 
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
