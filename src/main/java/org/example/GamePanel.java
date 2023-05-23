package org.example;

import org.MapFramework.TileManager;
import org.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel implements Runnable{
    // Display settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 4;
    public final int tileSize = originalTileSize*scale;

    public final int maxScreenCol = 16;
    public final int maxScreenRow  = 12;

    public final int screenWidth  = tileSize*maxScreenCol;
    public final int screenHeight = tileSize*maxScreenRow;

    public boolean roomClear; //check to determine if a room is complete

    //FPS
    int FPS=60;


    KeyHandler keyH = new KeyHandler();
    MouseHandler mouse = new MouseHandler();
    Thread gameThread;
    TileManager tileM  = new TileManager(this);
    Player player = new Player(this, keyH, tileM);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    StartScreen menu = new StartScreen(this,keyH,mouse); //work in progress startscreen method


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });

    }
    public void startup(){
        //menu.draw();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double ns = 1000000000 / (double)FPS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(gameThread != null){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                update();
                repaint();
                frames++;
                updates++;
                delta--;
            }

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
                updates = 0;
            }
        }
    }
    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);



        g2.dispose();
    }

    /*
    private static void getBestSize(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        canvasWidth = screenSize.width;
        canvasHeight = screenSize.height;

        gameWidth = screenSize.width;
        gameHeight = screenSize.height;
    }
    */
}
