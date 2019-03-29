package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Track {
    private LinkedList<TrackSection> sections;
    private int xpos;

    public Track(int x){
        this.xpos = x;
        sections = new LinkedList<TrackSection>();
    }

    public int getXpos() {
        return xpos;
    }

    public LinkedList<TrackSection> getSects() {
        return sections;
    }

    public abstract void run(SpriteBatch batch, int bpm, float delta);

}
