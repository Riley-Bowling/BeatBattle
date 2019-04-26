package com.beatbattle.game;


public class Player {

    private int health, beats;

    public Player() {
        //default health
        health = 8;
        //default beats
        beats = 10;
    }

    public int getHealth() { return health; }
    public void setHealth(int h) { health = h; }
    public void subtractHealth() { health -= 1;}

    public int getBeats() { return beats; }
    public void setBeats(int b) { beats = b; }
    public void subtractBeat() { beats -= 1;}
}