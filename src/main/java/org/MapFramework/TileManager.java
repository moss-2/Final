package org.MapFramework;

import org.example.GamePanel;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class TileManager {

    public Tile[] tiles;
    GamePanel gp;
    public int[][] mapTile;
    int currentMap;
    HashMap<Integer,String> maps;
    public int[][] fireMapTile;

    private Image fire1,fire2;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[10];
        mapTile = new int[gp.maxScreenCol][gp.maxScreenRow];
        fireMapTile = new int[gp.maxScreenCol][gp.maxScreenRow];
        setMaps();
        getTileImage();
        currentMap=1;
        loadMap("res/Maps/Map1.txt");

    }
    private void setMaps(){
        maps = new HashMap<>();
        for(int i=1;i<=2;i++){
            maps.put(i,"res/Maps/Map"+i+".txt");
        }
    }
    private void getTileImage() {
        try {
            tiles[0] = new Tile(false, false, false,0);
            tiles[0].image = ImageIO.read(new FileInputStream("res/Tileset/StoneFloors4.png"));

            tiles[1] = new Tile(true, false, false,0);
            tiles[1].image = ImageIO.read(new FileInputStream("res/Tileset/DarkStoneWall1.png"));

            tiles[2] = new Tile(false, true, false,0);
            tiles[2].image = ImageIO.read(new FileInputStream("res/Tileset/WoodenDoor1.png"));

            tiles[3] = new Tile(false, false, false,0);
            tiles[3].image = ImageIO.read(new FileInputStream("res/Tileset/StoneFloor3.png"));

            tiles[4] = new Tile(false, false, false,0);
            tiles[4].image = ImageIO.read(new FileInputStream("res/Tileset/StoneFloor2.png"));

            tiles[5] = new Tile(false, false, false,0);
            tiles[5].image = ImageIO.read(new FileInputStream("res/Tileset/Grill.png"));

            tiles[6] = new Tile(false, false, false,0);
            tiles[6].image = ImageIO.read(new FileInputStream("res/Tileset/DarkStoneWall3.png"));

            tiles[7] = new Tile(false, false, false,0);
            tiles[7].image = ImageIO.read(new FileInputStream("res/Tileset/DarkStoneWall2.png"));

            tiles[8] = new Tile(false, false, false, 0);
            tiles[8].image = ImageIO.read(new FileInputStream("res/Tileset/StoneBrickFloor.png"));

            //fire1 =  ImageIO.read(new FileInputStream("res/Tileset/fire1.png"));

            //fire2 =  ImageIO.read(new FileInputStream("res/Tileset/fire2.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;

        for(int y=0;y<gp.screenHeight;y = y +gp.tileSize){
            for(int x=0;x<gp.screenWidth;x = x + gp.tileSize){
                g2.drawImage(tiles[mapTile[col][row]].image, x,y, gp.tileSize,gp.tileSize, null);
                switch(tiles[mapTile[col][row]].fireLevel()){ //function to draw fire over tiles.
                    case 0:
                        break;
                    case 1:
                        //g2.drawImage(fire1, x,y, gp.tileSize,gp.tileSize, null);
                        break;
                    case 2:
                        //g2.drawImage(fire2, x,y, gp.tileSize,gp.tileSize, null);
                        break;
                }
                col++;
            }
            row++;
            col=0;
        }
    }
    private void loadMap(String filepath){
        try{
            File file = new File(filepath);
            Scanner reader  = new Scanner(file);

            for(int row=0;row<gp.maxScreenRow;row++){
                String line = reader.nextLine();
                for(int col=0;col<gp.maxScreenCol;col++){
                    String[] numbers = line.split(" ");
                    mapTile[col][row] = Integer.parseInt(numbers[col]);
                }
            }
            reader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void warp(){
        currentMap++;
        if(!maps.containsKey(currentMap)) {
            currentMap = 1;
        }
        loadMap(maps.get(currentMap));

    }
    /**
     * Hopefully I can "finish" this project by adding hazards for the player to avoid overtime.
     * Ideally each room would have a different pattern or rhythm too it and the player would win
     * by reaching the final room and beating some sort of boss. Kind of like the Rhythm game
     * Everhood. (idk I've never played it)
     *
     * in its current state the game simply boots up, and the player character just moves around
     * (press space to dash). The plans i had for an actual playable gamemode were scrapped(for now??).
     * */
    private void updatefireMap(){

    }

    //public
}
