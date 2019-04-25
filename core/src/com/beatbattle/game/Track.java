package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Track {
    private LinkedList<TrackSection> sections;
    private int xpos;
    private int BPM;

    public Track(int x, int s){
        this.xpos = x;
        this.BPM = s;
        sections = new LinkedList<TrackSection>();
    }

    public int getXpos() {
        return xpos;
    }
    public float getSpeed() {
        //BPM -> speed math
        return (float) BPM - 20;
    }

    public LinkedList<TrackSection> getSects() {
        return sections;
    }

    public abstract void run(SpriteBatch batch, int bpm, float delta);

}
