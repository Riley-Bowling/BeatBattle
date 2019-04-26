package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beatbattle.game.Player;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Track {
    private LinkedList<TrackSection> sections;
    private int xpos;
    private int BPM;
    private Player player;
    private int controlScheme;

    public Track(int x, int s, int c){
        this.xpos = x;
        this.BPM = s;
        this.controlScheme = c;
        sections = new LinkedList<TrackSection>();
        player = new Player();
    }

    public int getXpos() {
        return xpos;
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
    public Player getPlayer() {
        return player;
    }

    public abstract void run(SpriteBatch batch, int bpm, float delta);

}
