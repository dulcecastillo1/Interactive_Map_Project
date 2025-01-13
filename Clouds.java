import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable; 
public class Clouds implements Runnable, Serializable{

    private int x, y; 
    private Screen sc;
    public Clouds(int x, int y, Screen sc){
        this.x = x; 
        this.y = y; 
        this.sc = sc;
    }

    public void drawMe(Graphics g){
        g.setColor(new Color(229, 228, 226)); 
        //left 
        g.fillOval(x-40, y, 80, 30); 
        //middle 
        g.fillOval(x, y-30, 60, 40); 
        //right
        g.fillOval(x+20, y, 80, 30); 

    }


    public void run(){
        //moves left across the screen
        while (true){
            x += 8; 
            if(x > 900){
                x = -100; 
            }
            try{
                Thread.sleep(200); 
            }catch(InterruptedException ex){
                Thread.currentThread().interrupt(); 
            }
            sc.repaint(); 
        }
    }

}
