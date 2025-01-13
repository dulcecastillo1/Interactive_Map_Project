import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image; 

public class MapObject{
    
    private String name; 
    private Color blue; 
    private Color green; 
    private Color sand; 
    private Color brown;
    private Color treeGreen; 
    private boolean canWalkOn; 
    private boolean isSurrounding1; 
    private boolean isSurrounding2;
    private boolean isSurrounding3;
    private boolean isSurrounding4;
    private boolean isAdjacent1; 
    private boolean isAdjacent2; 
    private boolean isAdjacent3;
    private boolean isAdjacent4;  
    private BufferedImage image1; 
    private BufferedImage image2; 
    private BufferedImage image3; 
    private BufferedImage image4; 
    public MapObject(String name){
        this.name = name; 
        blue = new Color(167, 199, 231);
        green = new Color(79, 121, 66); 
        sand = new Color(194, 178, 128); 
        brown = new Color(92, 64, 51); 
        //test Color 
        treeGreen = new Color(180, 196, 36); 
        canWalkOn = false; 
        isSurrounding1 = false;
        isSurrounding2 = false; 
        isSurrounding3 = false; 
        isSurrounding4 = false; 
        isAdjacent1 = false; 
        isAdjacent2 = false; 
        isAdjacent3 = false;
        isAdjacent4 = false;

        try{
			image1 = ImageIO.read(new File("landmark1.png"));
		} catch (IOException e) {
            System.out.println("Landmark 1 not found");
        } 

        try{
			image2 = ImageIO.read(new File("landmark2.png"));
		} catch (IOException e) {
            System.out.println("Landmark 2 not found");
        } 

        try{
			image3 = ImageIO.read(new File("landmark3.png"));
		} catch (IOException e) {
            System.out.println("Landmark 3 not found");
        } 

        
        try{
			image4 = ImageIO.read(new File("landmark4.png"));
		} catch (IOException e) {
            System.out.println("Landmark 4 not found");
        }
    



    }

    public void drawMe(Graphics g, int x, int y){
        if(name.equals("water")){
            g.setColor(blue); 
            g.fillRect(x, y, 8, 8); 
            canWalkOn = false;
            
        }
        else if(name.equals("grass")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8);
            canWalkOn = true; 
        }
        else if(name.equals("road")){
            g.setColor(Color.BLACK); 
            g.fillRect(x, y, 8, 8); 

            g.setColor(Color.YELLOW); 
            g.fillRect(x+2, y+3, 3, 2); 
            canWalkOn = false; 
        }
        else if(name.equals("sand")){
            g.setColor(sand); 
            g.fillRect(x, y, 8, 8); 
            canWalkOn = true; 
        }

        //landmark1 
        else if(name.equals("landmark1")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8); 
            g.drawImage(image1, x-72, y-72, null); 
            canWalkOn = true; 
        }
        else if(name.equals("surrounding1")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8); 
            canWalkOn = true; 
            isSurrounding1 = true; 
        }
        else if(name.equals("adjacent1")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8); 
            canWalkOn = false; 
            isAdjacent1 = true; 
        }

        //landmark2 
        else if(name.equals("landmark2")){
            g.setColor(blue); 
            g.fillRect(x, y, 8, 8); 
            g.drawImage(image2, x-72, y-72, null); 
            canWalkOn = true;  
        }
        else if(name.equals("surrounding2")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8); 
            canWalkOn = true; 
            isSurrounding2 = true; 
        }
        else if(name.equals("adjacent2")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8); 
            canWalkOn = false; 
            isAdjacent2 = true; 
        }

        //landmark3
         else if(name.equals("landmark3")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8); 
            g.drawImage(image3, x-72, y-72, null); 
            canWalkOn = true; 
        }
        else if(name.equals("surrounding3")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8); 
            canWalkOn = true; 
            isSurrounding3 = true; 
        }
        else if(name.equals("adjacent3")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8); 
            canWalkOn = false; 
            isAdjacent3 = true; 
        }

        //landmark4
         else if(name.equals("landmark4")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8); 
            g.drawImage(image4, x-72, y-72, null); 
            canWalkOn = true; 
        }
        else if(name.equals("surrounding4")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8); 
            canWalkOn = true; 
            isSurrounding4 = true; 
        }
        else if(name.equals("adjacent4")){
            g.setColor(green); 
            g.fillRect(x, y, 8, 8); 
            canWalkOn = false; 
            isAdjacent4 = true; 
        }

        //mountain
        else if(name.equals("mountain")){
            canWalkOn = false; 

            //create 2 arrays to hold the 3 vertices (x,y)
            int [] xArray = new int[3]; 
            int [] yArray = new int[3]; 

            //Set up coordinates of the vertices 
            xArray[0] = x+1; 
            xArray[1] = x+4; 
            xArray[2] = x+7;
            yArray[0] = y+7; 
            yArray[1] = y; 
            yArray[2] = y+7; 

            //Set color to draw the polygon 
            g.setColor(brown); 
            //Use fillPolygon to fill the triangle 
            g.fillPolygon(xArray, yArray, 3); 
        }

        //house 
        else if(name.equals("house")){
            canWalkOn = false; 

            g.setColor(sand); 
            g.fillRect(x+1, y+2, 6, 5); 

            //create 2 arrays to hold the 3 vertices (x,y)
            int [] xArray = new int[3]; 
            int [] yArray = new int[3]; 

            //Set up coordinates of the vertices 
            xArray[0] = x; 
            xArray[1] = x+4; 
            xArray[2] = x+8;
            yArray[0] = y+3; 
            yArray[1] = y; 
            yArray[2] = y+3; 

            //Set color to draw the polygon 
            g.setColor(brown); 
            //Use fillPolygon to fill the triangle 
            g.fillPolygon(xArray, yArray, 3); 

        }

        //tree
        else if(name.equals("tree")){
            g.setColor(brown); 
            g.fillRect(x+3, y, 3, 8); 
            canWalkOn = false; 

            g.setColor(treeGreen); 
            g.fillOval(x+3, y, 3, 3); 
            g.fillOval(x, y+2, 3, 3);
            g.fillOval(x+5, y+2, 3, 3);

        }

    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public String getName(){
        return name; 
    }

    public boolean canWalkOn(){
        return canWalkOn; 
    }

    public boolean surrounding1(){
        return isSurrounding1; 
    }

    public boolean surrounding2(){
        return isSurrounding2; 
    }

    public boolean surrounding3(){
        return isSurrounding3; 
    }

    public boolean surrounding4(){
        return isSurrounding4; 
    }
}
