package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public abstract class Track {
    private ArrayList<TrackSection> sections;
    private int xpos;

    public Track(int x){
        this.xpos = x;
        sections = new ArrayList<TrackSection>();
    }

    public int getXpos() {
        return xpos;
    }

    public ArrayList<TrackSection> getSects() {
        return sections;
    }

    public abstract void create(SpriteBatch batch);
    public abstract void run(SpriteBatch batch, int bpm);

}
