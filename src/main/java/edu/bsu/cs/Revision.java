package edu.bsu.cs;

public class Revision {

    private final String username;
    private final String timestamp;

    public Revision(String username, String timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    } // end Revision

    public String getUsername() {
        return username;
    } // end getUsername

    public String getTimestamp() {
        return timestamp;
    } // end getTimestamp
} // close class