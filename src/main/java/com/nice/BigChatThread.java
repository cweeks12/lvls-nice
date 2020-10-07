package com.nice;

import java.util.List;

import static java.lang.Thread.sleep;

public class BigChatThread extends Thread{

    private List<PlayerMessageThread> messageThreads;
    private float msToCelebrate;

    BigChatThread(List<PlayerMessageThread> messageThreads, float msToCelbrate){
        this.messageThreads = messageThreads;
        this.msToCelebrate = msToCelbrate;
    }

    public void run() {
        for (PlayerMessageThread thread : messageThreads) {
            thread.start();
            try {
                sleep((long) (msToCelebrate / this.messageThreads.size()));
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
