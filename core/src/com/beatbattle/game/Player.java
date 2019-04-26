


public class Player {

    private int health, beats;

    public Player(int b) {
        //default health
        this.health = 3;
        this.beats = beats;
    }

    public int getHealth() { return health; }
    public void setHealth(int h) { health = h; }

    public int getBeats() { return beats; }
    public void setBeats(int b) { beats = b; }
    public void subtractBeat() { beats -= 1;}
}