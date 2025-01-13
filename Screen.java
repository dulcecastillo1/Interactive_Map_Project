import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font; 

import javax.swing.JTextField;
import javax.swing.JTextArea; 
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyListener;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image; 
import java.net.URL;
import java.io.*;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Screen extends JPanel implements ActionListener, MouseListener, KeyListener{ 
    //travel map 
    MyHashTable<Location, MapObject> travelMap;

    //tourist
    Tourist tourist; 

    //ints 
    int touristStartX, touristStartY; 

    //textArea

    //textFields 


    //buttons 
    JButton saveButton; 

    //booleans 
    boolean instructionsScreen; 
    boolean removeInstructions; 
    boolean showLandmark1;
    boolean showLandmark2; 
    boolean showLandmark3; 
    boolean showLandmark4;
    boolean drawTouristImage; 
    boolean touristUp; 
    boolean touristDown; 
    boolean touristLeft; 
    boolean touristRight; 
    boolean touristStanding; 

    //string 
    String fileName; 
    String cloud1FileName; 
    String cloud2FileName;

    String boat1FileName; 
    String boat2FileName; 
    String boat3FileName; 
    String boat4FileName; 

    String fish1FileName;
    String fish2FileName;
    String fish3FileName;
    String fish4FileName;
    String fish5FileName;
    String fish6FileName;
    String fish7FileName;
    String fish8FileName;
    String fish9FileName;
    String fish10FileName;



    //font 
    Font font; 

    Clouds cloud1; 
    Clouds cloud2; 

    DLList<Boat> boats; 
    DLList<Thread> boatThreads; 

    DLList<Fish> fish; 
    DLList<Thread> fishThreads; 

    Boat boat1; 
    Boat boat2; 
    Boat boat3; 
    Boat boat4; 

    Fish fish1; 
    Fish fish2; 
    Fish fish3; 
    Fish fish4; 
    Fish fish5; 
    Fish fish6; 
    Fish fish7; 
    Fish fish8; 
    Fish fish9; 
    Fish fish10;

    Thread t1; 
    Thread t2; 

    //images - for every landmark 
    BufferedImage landmark1Image; 
    BufferedImage landmark2Image;
    BufferedImage landmark3Image; 
    BufferedImage landmark4Image; 


	public Screen(){
        //hashMap 
        travelMap = new MyHashTable<Location, MapObject>(); 

        //booleans 
        instructionsScreen = true; 
        showLandmark1 = false; 
        showLandmark2 = false; 
        drawTouristImage = true; 
        touristUp = false; 
        touristDown = false; 
        touristLeft = false; 
        touristRight = false; 
        touristStanding = true; 

        //images 
        try{
			landmark1Image = ImageIO.read(new File("Metropolitan Cathedral.jpg"));
		} catch (IOException e) {
            System.out.println("File not found");
        } 

        try{
			landmark2Image = ImageIO.read(new File("chichenitza.jpeg"));
		} catch (IOException e) {
            System.out.println("File not found");
        } 

        try{
			landmark3Image = ImageIO.read(new File("FridaKhaloMuseum.jpg"));
		} catch (IOException e) {
            System.out.println("File not found");
        } 

        try{
			landmark4Image = ImageIO.read(new File("monument.png"));
		} catch (IOException e) {
            System.out.println("File not found");
        } 

        

        setLayout(null); 


        //buttons 
        saveButton = new JButton("Save"); 
        saveButton.setBounds(700, 30, 60, 30); 


        cloud1 = new Clouds(100, 80, this); 
        cloud2 = new Clouds(400, 80, this); 

        t1 = new Thread(cloud1); 
        t1.start(); 

        t2 = new Thread(cloud2); 
        t2.start(); 

        boats = new DLList<Boat>(); 
        int yRange = 800 - 456 +1;
        boat1 = new Boat((int)(Math.random()*47*8), (int)(Math.random()*yRange +(456)), this);
        boat2 = new Boat((int)(Math.random()*47*8), (int)(Math.random()*yRange +(456)), this);
        boat3 = new Boat((int)(Math.random()*47*8), (int)(Math.random()*yRange +(456)), this);
        boat4 = new Boat((int)(Math.random()*47*8), (int)(Math.random()*yRange +(456)), this);

        boats.add(boat1); 
        boats.add(boat2); 
        boats.add(boat3); 
        boats.add(boat4); 

        boatThreads = new DLList<Thread>(); 
        for(int i = 0; i < 4; i++){
            boatThreads.add(new Thread(boats.get(i))); 
            boatThreads.get(i).start(); 
        }

        fish = new DLList<Fish>(); 
        int yRangeFish = (50*8) - (41*8) +1; 
        int xRangeFish = 800 - 624 +1; 
        fish1 = new Fish(752, 335, this);
        fish2 = new Fish(777, 365, this);
        fish3 = new Fish(744, 328, this);
        fish4 = new Fish(762, 400, this);
        fish5 = new Fish(723, 333, this);
        fish6 = new Fish(741, 350, this);
        fish7 = new Fish(700, 404, this);
        fish8 = new Fish(679, 398, this);
        fish9 = new Fish(624, 348, this);
        fish10 = new Fish(624, 328, this);
        fish.add(fish1);
        fish.add(fish2);
        fish.add(fish3);
        fish.add(fish4);
        fish.add(fish5);
        fish.add(fish6);
        fish.add(fish7);
        fish.add(fish8);
        fish.add(fish9);
        fish.add(fish10);

        fishThreads = new DLList<Thread>(); 
        for(int i = 0; i < 10; i++){
            fishThreads.add(new Thread(fish.get(i))); 
            fishThreads.get(i).start(); 
        }




        //music 
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("backgroundMusic.wav").getAbsoluteFile()));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        } 
    

        //add textFields and buttons 

        //scan text file to create the map 
        try {
            Scanner scan = new Scanner(new FileReader("MapExportFile.txt"));             
            int row=0; //Each line in your text file is a row
            while (scan.hasNextLine()){
                //Reads in one line at a time.
                String line = scan.nextLine();

                //if there's a space between each number in your text file
                String[] numberArray = line.split(" ");  
                for(int col=0;col<numberArray .length;col++){  //Each number in the line is a column
                    Location key = new Location(col,row);
                    String value = numberArray[col];
                    //Based upon the value, create your mapObject
                    //consider changing numbers to letters - not having to use double digits
                    if(value.equals("3")){
                        MapObject mapObj = new MapObject("water"); 
                        travelMap.put(key, mapObj); 
                    }
                    else if(value.equals("5")){
                        MapObject mapObj = new MapObject("grass");
                        travelMap.put(key, mapObj); 
                    }
                    else if(value.equals("7")){
                        MapObject mapObj = new MapObject("road"); 
                        travelMap.put(key, mapObj); 
                    }
                    else if(value.equals("1")){
                        MapObject mapObj = new MapObject("sand");
                        travelMap.put(key, mapObj); 
                    }

                    //landmark 1
                    else if(value.equals("0")){
                        MapObject mapObj = new MapObject("landmark1"); 
                        travelMap.put(key, mapObj); 
                    
                    }
                    else if(value.equals("4")){
                        MapObject mapObj = new MapObject("surrounding1"); 
                        travelMap.put(key, mapObj);
                    }
                    else if(value.equals("6")){
                        MapObject mapObj = new MapObject("adjacent1"); 
                        travelMap.put(key, mapObj); 
                    }

                    //landmark 2
                    else if(value.equals("2")){
                        MapObject mapObj = new MapObject("landmark2"); 
                        travelMap.put(key, mapObj); 
                    }
                    else if(value.equals("9")){
                        MapObject mapObj = new MapObject("surrounding2"); 
                        travelMap.put(key, mapObj);
                    }
                    else if(value.equals("8")){
                        MapObject mapObj = new MapObject("adjacent2"); 
                        travelMap.put(key, mapObj); 
                    }

                    //landmark 3
                    else if(value.equals("10")){
                        MapObject mapObj = new MapObject("landmark3"); 
                        travelMap.put(key, mapObj);
                    }
                    else if(value.equals("11")){
                        MapObject mapObj = new MapObject("surrounding3"); 
                        travelMap.put(key, mapObj);
                    }
                    else if(value.equals("12")){
                        MapObject mapObj = new MapObject("adjacent3"); 
                        travelMap.put(key, mapObj); 
                    }

                    //landmark4
                    else if(value.equals("14")){
                        MapObject mapObj = new MapObject("landmark4"); 
                        travelMap.put(key, mapObj);
                    }
                    else if(value.equals("16")){
                        MapObject mapObj = new MapObject("adjacent4"); 
                        travelMap.put(key, mapObj);
                    }
                    else if(value.equals("15")){
                        MapObject mapObj = new MapObject("surrounding4"); 
                        travelMap.put(key, mapObj); 
                    }

                    //mountain 
                    else if(value.equals("13")){
                        MapObject mapObj = new MapObject("grass"); 
                        travelMap.put(key, mapObj);

                        MapObject mapObj2 = new MapObject("mountain"); 
                        travelMap.put(key, mapObj2); 
                    }

                    //house 
                    else if(value.equals("17")){
                        MapObject mapObj = new MapObject("grass"); 
                        travelMap.put(key, mapObj);

                        MapObject mapObj2 = new MapObject("house"); 
                        travelMap.put(key, mapObj2); 
                    }

                    //tree
                    else if(value.equals("18")){
                        MapObject mapObj = new MapObject("grass"); 
                        travelMap.put(key, mapObj);

                        MapObject mapObj2 = new MapObject("tree"); 
                        travelMap.put(key, mapObj2); 
                    }
                }
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        tourist = new Tourist(200, 200); 

        fileName = "travelerLocation.dat";

        
        try {
            //Load the data file to read in
            FileInputStream fis = new FileInputStream(fileName);


            //Create a data stream to read in
            ObjectInputStream in = new ObjectInputStream(fis);


            //Read in the object from file
            //Casting to generics to produce unchecked type warning
            tourist = (Tourist) in .readObject();


            //Close all your data stream
            fis.close(); 
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Using default list");
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        




        //add interfaces
        addKeyListener(this);
        addMouseListener(this); 
        this.setFocusable(true);


        //add actionListener 
        add(saveButton); 
        saveButton.addActionListener(this); 

        
    }
	

	//set the size of the JPanel container
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(800,800);
	}
	
	@Override
	public void paintComponent(Graphics g){
        
        drawGrid(g); 

        
        if(removeInstructions == false){
            drawInstructions(g); 
        }

        if(showLandmark1 == true){
            drawLandmark(g, 1);
        }

        if(showLandmark2 == true){
            drawLandmark(g, 2);
        }

        if(showLandmark3 == true){
            drawLandmark(g, 3); 
        }

        if(showLandmark4 == true){
            drawLandmark(g, 4); 
        }

        if(drawTouristImage == true){
            if(touristUp == true){
                tourist.drawTouristUp(g);  
            }
            else if(touristDown == true){
                tourist.drawTouristDown(g); 
            }
            else if(touristLeft == true){
                tourist.drawTouristLeft(g); 
            }
            else if(touristRight == true){
                tourist.drawTouristRight(g); 
            }
            else if(touristStanding == true){
                tourist.drawTourist(g); 
            }
        }

        cloud1.drawMe(g); 
        cloud2.drawMe(g);

        
        for(int i = 0; i < 4; i++){
            boats.get(i).drawMe(g); 
        }
        
        for(int i = 0; i < 10; i++){
            fish.get(i).drawMe(g); 
        }
        

        
        
        
		
	}

    public void drawGrid(Graphics g){
        for(int r = 0; r < 100; r++){
            for(int c = 0; c < 100; c++){
                Location keyTemp = new Location(r, c); 
                DLList<MapObject> mapObjectsList = travelMap.get( keyTemp );
                for(int i = 0; i < mapObjectsList.size(); i++){
                    mapObjectsList.get(i).drawMe(g, r*8, c*8);
                    
                }
            }
        }
    }


    public void displayLandmark(int r, int c){
        Location keyTemp = new Location(r, c); 
        DLList<MapObject> mapObjectsList = travelMap.get( keyTemp );
        for(int i = 0; i <mapObjectsList.size() ; i++){
            if(mapObjectsList.get(i).surrounding1() == true){
                showLandmark1 = true; 
                i = mapObjectsList.size()+1; 
            }
            else if(mapObjectsList.get(i).surrounding2() == true){
                showLandmark2 = true; 
                i = mapObjectsList.size()+1; 
            }
            else if(mapObjectsList.get(i).surrounding3() == true){
                showLandmark3 = true; 
                i = mapObjectsList.size()+1; 
            }
            else if(mapObjectsList.get(i).surrounding4() == true){
                showLandmark4 = true; 
                i = mapObjectsList.size()+1; 
            }
            else{
                showLandmark1 = false;   
                showLandmark2 = false; 
                showLandmark3 = false; 
                showLandmark4 = false; 
            }
                
        }
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public void drawLandmark(Graphics g, int landmark){
        if(landmark == 1){
            g.setColor(Color.WHITE);
            //change size of display pop up
            g.fillRect(50, 50, 350, 280); 
            g.setColor(Color.BLACK); 
            g.drawString("Metropolitan Cathedral", 60, 70);
            g.drawString("The Metropolitan Cathedral is located in", 60, 230);
            g.drawString("the center of Mexico City. The cathedral", 60, 245);  
            g.drawString("was built in sections from 1573 to 1813.", 60, 260);
            g.drawString("It includes Gothic, Baroque, Churrigueresque,", 60, 275);
            g.drawString("Neoclassical architectual styles.", 60, 290); 
            g.drawImage(resizeImage(landmark1Image, 180, 130), 70, 80, null);

            
        }
        else if(landmark == 2){
            g.setColor(Color.WHITE);
            g.fillRect(50, 50, 350, 290); 
            g.setColor(Color.BLACK); 
            g.drawString("Chichen Itza", 60, 70);
            g.drawString("The Chichen Itza is located in Yucatan, Mexico.", 60, 230); 
            g.drawString("It is one of the 7 wonders of the word.", 60, 245); 
            g.drawString("When clapping in front of the Chichen Itza, ", 60, 260); 
            g.drawString("the sound travels back to you, mimicking the", 60, 275); 
            g.drawString("sound of a bird. The sound playing is an ", 60, 290); 
            g.drawString("example of this.", 60, 305); 
            g.drawImage(resizeImage(landmark2Image, 180, 130), 70, 80, null); 
        }
        else if(landmark == 3){
            g.setColor(Color.WHITE);
            g.fillRect(50, 50, 390, 250); 
            g.setColor(Color.BLACK); 
            g.drawString("Frida Khalo Museum", 60, 70);
            g.drawString("The Frida Khalo Museum features not only famous artwork,", 60, 230);
            g.drawString("but aspects of Frida Khalo's life.", 60, 245);
            g.drawString("This museum is surrounded by green and vibrant scenery.", 60, 260); 
            g.drawImage(resizeImage(landmark3Image, 180, 130), 70, 80, null);
        }
        else if(landmark == 4){
            g.setColor(Color.WHITE);
            g.fillRect(250, 450, 435, 230); 
            g.setColor(Color.BLACK); 
            g.drawString("Angel of Independence", 260, 470);
            g.drawString("This monument was first built in 1910.", 430, 540); 
            g.drawString("The Angel of Independence is a tribute", 430, 555);
            g.drawString("to Mexico's victory over Spain in its", 430, 570);
            g.drawString("War of Independence.", 430, 585);
            g.drawImage(resizeImage(landmark4Image, 150, 185), 270, 480, null);
        }
    }



    public void drawInstructions(Graphics g){
        //draw the instructions 
        g.setColor(Color.WHITE); 
        g.fillRect(260, 200, 280, 300); 
        g.setColor(Color.BLACK); 
        g.drawString("Instructions: ", 360, 230); 
        g.drawString("Travel to the different", 324, 250);
        g.drawString("landmarks across the map.", 309, 270); 
        g.drawString("Move your traveler using", 316, 290); 
        g.drawString("your arrow keys.", 340, 310); 
        g.drawString("Be wary of obstacles", 327, 330); 
        g.drawString("along the way!", 346, 350); 
        g.drawString("Click anywhere to continue", 310, 410); 
        
    }

    

	
	//action to be performed when a button is clicked
	public void actionPerformed(ActionEvent e){
        if(e.getSource() == saveButton){
            //tourist location save
            try {
                //Create a data file to write
                FileOutputStream fos = new FileOutputStream(fileName);


                //Create a stream to write
                ObjectOutputStream out = new ObjectOutputStream(fos);


                //Write object from stream
                out.writeObject(tourist);

                //Close all your data stream
                fos.close();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        repaint(); 
	}

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX(); 
        int mouseY = e.getY(); 
        if(instructionsScreen == true){
            if(mouseX >= 0 && mouseX <= 800 && mouseY >= 0 && mouseY <= 800){
                removeInstructions = true; 
                instructionsScreen = false; 
            }
        }

        repaint(); 
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e){
        //move tourist + check if landmark should be displayed 
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            tourist.moveLeft(travelMap);  
            touristLeft = true; 
            touristUp = false;
            touristDown = false; 
            touristRight = false; 
            touristStanding = false; 
            displayLandmark(tourist.getRow(), tourist.getCol()); 
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            tourist.moveRight(travelMap);  
            touristRight = true; 
            touristUp = false;
            touristDown = false; 
            touristLeft = false; 
            touristStanding = false; 
            displayLandmark(tourist.getRow(), tourist.getCol());
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            tourist.moveUp(travelMap); 
            touristUp = true; 
            touristDown = false; 
            touristLeft = false; 
            touristRight = false; 
            touristStanding = false; 
            displayLandmark(tourist.getRow(), tourist.getCol());
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            tourist.moveDown(travelMap); 
            touristDown = true; 
            touristUp = false;
            touristLeft = false; 
            touristRight = false; 
            touristStanding = false;  
            displayLandmark(tourist.getRow(), tourist.getCol());
        }

        repaint(); 
    }

    @Override
    public void keyReleased(KeyEvent e){
        touristUp = false;
        touristDown = false; 
        touristLeft = false; 
        touristRight = false; 
        touristStanding = true; 
    }

    @Override 
    public void keyTyped(KeyEvent e){
        
    }






}
