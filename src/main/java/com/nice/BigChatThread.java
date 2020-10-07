package com.nice;

import java.util.List;

import static java.lang.Thread.sleep;

public class BigChatThread extends Thread{

    private List<PlayerMessageThread> messageThreads;

    BigChatThread(List<PlayerMessageThread> messageThreads){
        this.messageThreads = messageThreads;
    }

    public void run() {
        for (PlayerMessageThread thread : messageThreads) {
            thread.start();
            try {
                sleep(50);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
