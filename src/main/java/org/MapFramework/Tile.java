package org.MapFramework;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;
    boolean solid = false;
    boolean warp = false;
    int fire = 0;
    boolean eventTrigger=false; //catch all placeholder

    public Tile(boolean solid, boolean warp, boolean Trigger, int fire){
        this.eventTrigger = Trigger;
        this.warp = warp;
        this.solid = solid;
        this.fire = fire;
    }

    public void setSolid(boolean t){
        solid = t;
    }
    public boolean isSolid(){
        return solid;
    }
    public boolean isWarp() {
        return warp;
    }
    public boolean isEventTrigger(){
        return eventTrigger;
    }


    public int fireLevel(){
        return fire;
    }
}
