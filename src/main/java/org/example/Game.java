package org.example;

import javax.swing.*;

/**
 * This project framework refered to this handy youtube tutorial series:
 * https://www.youtube.com/playlist?list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq
 * I used this setup for the timestep method in gamepanel:
 * https://gafferongames.com/post/fix_your_timestep/
 * The plan at the moment is to generate sprites, backgrounds, tilesets, music, etc using AI tools
 * but for the meanwhile I'm some assets from free sets I found online:
 * https://gif-superretroworld.itch.io/interior-pack
 * https://bakudas.itch.io/generic-rpg-pack
 * https://rekkimaru.itch.io/dungeon-rpg-tileset
 *
 * The plan(if i continue this project) is to replace all these assets, but for now I'm using them as placeholders
 * */
public class Game {
    public static void main(String[] args){
            Game game = new Game();
            game.run();
    }
    public Game(){

    }
    private void run(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        gamePanel.startGameThread();
    }
}

