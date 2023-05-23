package org.entity;

import org.MapFramework.TileManager;
import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The animations are still a little wonky -- I didn't prepare all the animation states i needed
 * So the character oddly switches between some states. */

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    TileManager tileM;

    public int health; //later
    public int iFrames; //later

    private int remainingDash;
    private int dashCooldown;//temp
    private int dashSpeed;
    private int defaultSpeed=5;

    private int animationState;

    private BufferedImage dash1,dash2,dash3,dash4;

    //public int[] abilityCooldowns; vars for storing cooldown data
    // public String[] abilities; might change data structure later

    //public Inventory inventory; class for storing stuff -> later

    public Player(GamePanel gp, KeyHandler keyH, TileManager tileM){
        this.gp = gp;
        this.keyH = keyH;
        this.tileM = tileM;
        setDefaults();
        getPlayerImage();

        solidArea = new Rectangle(8,16,32,32);

    }
    public void setDefaults(int x, int y,int speed){
        this.x=x;
        this.y=y;
        this.speed=speed;
    }
    public void setDefaults(){
        this.x=100;
        this.y=100;
        this.speed=defaultSpeed;
        direction = "down";
        this.iFrames=0; //number of invincibility frames
        this.dashCooldown = 0;
        this.remainingDash=0;
        //stamina system? later.
        this.animationState = 0;
        dashSpeed=2*defaultSpeed;
    }
    public void update(){
        if(remainingDash==0) {
            speed = defaultSpeed;
            if(keyH.upPressed){
                direction="up";
                if(gp.collisionChecker.checkTile(this)==0){
                    y-=speed;
                }
            }
            else if(keyH.downPressed){
                direction="down";
                if(gp.collisionChecker.checkTile(this)==0){
                    y+=speed;
                }

            }
            else if(keyH.rightPressed){
                direction="right";
                if(gp.collisionChecker.checkTile(this)==0){
                    x+=speed;
                }
            }
            else if(keyH.leftPressed){
                direction="left";
                if(gp.collisionChecker.checkTile(this)==0){
                    x-=speed;
                }

            }
            if(keyH.spacePressed && dashCooldown==0){
                dashCooldown=300; // 5 seconds
                remainingDash=30; //frames!
                iFrames = 30;
            }
            if(dashCooldown>0){
                dashCooldown--;
            }
        }
        else {
            speed = dashSpeed;
            if (gp.collisionChecker.checkTile(this) == 0) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "right" -> x += speed;
                    case "left" -> x -= speed;
                }
            }
            remainingDash--;
            dashCooldown--;
            iFrames--;
        }
        animationState++;
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int slowState = animationState/40;
        if(remainingDash==0){
            if(direction.equals("down")) {
                if(slowState%2==0)
                    image = down1;
                else
                    image = down1; // don't currently have secondary animation states
            }
            if(direction.equals("up")) {
                if(slowState%2==0)
                    image = up1;
                else
                    image = up2;
            }
            if(direction.equals("right")) {
                if(slowState%2==0)
                    image = right1;
                else
                    image = right2;
            }
            if(direction.equals("left")) {
                if(slowState%2==0)
                    image = left1;
                else
                    image = left2;
            }
        }
        else{
            if(animationState%4==0){
                image = dash1;
            }
            if(animationState%4==1){
                image = dash2;
            }
            if(animationState%4==2){
                image = dash3;
            }
            if(animationState%4==3){
                image = dash4;
            }
        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(new FileInputStream("res/player/up1.png"));
            up2 = ImageIO.read(new FileInputStream("res/player/up2.png"));
            down1 = ImageIO.read(new FileInputStream("res/player/down1.png"));
            right1 = ImageIO.read(new FileInputStream("res/player/right1.png"));
            right2 = ImageIO.read(new FileInputStream("res/player/right2.png"));
            left1 = ImageIO.read(new FileInputStream("res/player/left1.png"));
            left2 = ImageIO.read(new FileInputStream("res/player/left2.png"));
            this.dash1 = ImageIO.read(new FileInputStream("res/player/dash1.png"));
            this.dash2 = ImageIO.read(new FileInputStream("res/player/dash2.png"));
            this.dash3 = ImageIO.read(new FileInputStream("res/player/dash3.png"));
            this.dash4 = ImageIO.read(new FileInputStream("res/player/dash4.png"));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
