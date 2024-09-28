package Entities;

public class Player {
    private String club;
    private String name;
    private String jerseyNo;
    private String playingPosition;

    //constructor
    public Player(String club, String name, String jerseyNo, String playingPosition) {
        this.club = club;
        this.name = name;
        this.jerseyNo = jerseyNo;
        this.playingPosition = playingPosition;
    }

    //setter
    public void setClub(String club) {
        this.club = club;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayingPosition(String playingPosition) {
        this.playingPosition = playingPosition;
    }

    public void setJerseyNo(String sid) {
        this.jerseyNo = sid;
    }

    //getter
    public String getClub() {
        return club;
    }

    public String getName() {
        return name;
    }

    public String getPlayingPosition() {
        return playingPosition;
    }

    public String getJerseyNo() {
        return jerseyNo;
    }

    public void show() {
        System.out.println("Sl: " + club);
        System.out.println("Name: " + name);
        System.out.println("Jersey no: " + jerseyNo);
        System.out.println("Playing position: " + playingPosition);
    }

    public String getFileWriteFormat() {
        return club + ";" + name + ";" + jerseyNo + ";" + playingPosition + "\n";
    }
}
