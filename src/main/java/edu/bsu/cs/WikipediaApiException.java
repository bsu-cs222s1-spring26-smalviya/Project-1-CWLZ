package edu.bsu.cs;

public class WikipediaApiException extends RuntimeException {

    public WikipediaApiException(String message) {
        super(message);
    }

    public WikipediaApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
