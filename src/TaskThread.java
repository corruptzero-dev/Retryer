package ru.corruptzero;

public class TaskThread extends Thread {
    Query clazz;
    boolean hasError = false;

    public TaskThread(Query clazz) {
        this.clazz = clazz;
    }

    @Override
    public void run() {
        try {
            clazz.request();
        } catch (Exception e){
            hasError=true;
        }
    }
}
