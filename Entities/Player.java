package Entities;

public class Player {
    private String sport;
    private String name;
    private String jerseyNo;
    private String playingPosition;

    public Player(String sport, String name, String jerseyNo, String playingPosition) {
        this.sport = sport;
        this.name = name;
        this.jerseyNo = jerseyNo;
        this.playingPosition = playingPosition;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJerseyNo() {
        return jerseyNo;
    }

    public void setJerseyNo(String jerseyNo) {
        this.jerseyNo = jerseyNo;
    }

    public String getPlayingPosition() {
        return playingPosition;
    }

    public void setPlayingPosition(String playingPosition) {
        this.playingPosition = playingPosition;
    }

    @Override
    public String toString() {
        return sport + "," + name + "," + jerseyNo + "," + playingPosition;
    }

    public void show() {
        System.out.println("Sport: " + sport);
        System.out.println("Name: " + name);
        System.out.println("Jersey no: " + jerseyNo);
        System.out.println("Playing position: " + playingPosition);
    }
}
