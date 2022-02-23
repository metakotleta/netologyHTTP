package com.netology.HW12;

public class Main {
    public static void main(String[] args) {
        IOnDoneListener listener = (x) -> System.out.println(x);
        IOnErrorListener errorListener = (x) -> System.out.println(x);
        Worker worker = new Worker(listener,errorListener);
        worker.start();
    }
}
