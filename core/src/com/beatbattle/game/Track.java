package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beatbattle.game.Player;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Track {
    private LinkedList<TrackSection> sections;
    private LinkedList<Sprite> sectionSprites;
    private int xpos;
    private int BPM;
    private Player player;
    private int controlScheme;
    private int counter;

    public Track(int x, int s, LinkedList<TrackSection> t, int c){
        xpos = x;
        BPM = s;
        controlScheme = c;

        sections = t;
        sectionSprites = new LinkedList<Sprite>();
        player = new Player();
    }

    public int getXpos() {
        return xpos;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int c) {
        counter = c;
    }

    public void subtractCounter() {
        if(counter > 0) {
            counter -= 1;
        }
    }

    public float getSpeed() {
        //BPM -> speed math
        return (float) BPM - 20;
    }

    public int getControlScheme() {
        return controlScheme;
    }

    public LinkedList<TrackSection> getSects() {
        return sections;
    }

    public LinkedList<Sprite> getSectSprites() {
        return sectionSprites;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract void run(SpriteBatch batch, int bpm, float delta);

}
