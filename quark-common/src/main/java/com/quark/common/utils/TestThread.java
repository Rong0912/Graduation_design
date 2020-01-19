package com.quark.common.utils;

import static com.quark.common.utils.NoticeSingleton.getData;

public class TestThread extends Thread {

    public void run(){

        while (true){

            try {
                getData("1111");
                sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) {
        TestThread testThread=new TestThread();
        testThread.start();
    }


}
