package com.nice;

import net.runelite.api.Player;

public class PlayerMessageThread extends Thread{

    private Player player;
    private String message;

    PlayerMessageThread(Player player, String message){
        this.player = player;
        this.message = message;
    }

    public void run(){
        this.player.setOverheadText("glow1:shake:" + this.message);
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        this.player.setOverheadText("");
    }
}
