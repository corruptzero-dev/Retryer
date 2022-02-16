package ru.corruptzero;

public interface Retryable {
     void request() throws Exception;
}
