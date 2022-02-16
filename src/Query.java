package ru.corruptzero;

import java.util.Random;

public record Query(int id) implements Retryable {

    @Override
    public void request() throws Exception {
        Random random = new Random();
        boolean isSuccessful = random.nextBoolean();
        if (!isSuccessful) {
            throw new Exception("self error");
        }
        System.out.println("waiting...");
        Thread.sleep(100000L);
    }
}
