package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TrackSection {
    int[] bPatt;
    private Texture tex;
    // 3 = both
    private int track;
    private int count;

    public TrackSection(int a, int b, int c, int t, int co) {
        bPatt = new int[3];
        bPatt[0] = a;
        bPatt[1] = b;
        bPatt[2] = c;
        track = t;
        count = co;
        setTex(bPatt);
    }

    private void setTex(int[] b){
        if (b[0] == 0 && b[1] == 0 && b[2] == 0)
            tex = new Texture("track.png");
        if (b[0] == 1 && b[1] == 0 && b[2] == 0)
            tex = new Texture("track-xoo.png");
        if (b[0] == 0 && b[1] == 1 && b[2] == 0)
            tex = new Texture("track-oxo.png");
        if (b[0] == 0 && b[1] == 0 && b[2] == 1)
            tex = new Texture("track-oox.png");
        if (b[0] == 1 && b[1] == 0 && b[2] == 1)
            tex = new Texture("track-xox.png");
        if (b[0] == 0 && b[1] == 1 && b[2] == 1)
            tex = new Texture("track-oxx.png");
        if (b[0] == 1 && b[1] == 1 && b[2] == 0)
            tex = new Texture("track-xxo.png");
    }

    public void setPatt(int a, int b, int c) {
        bPatt[0] = a;
        bPatt[1] = b;
        bPatt[2] = c;
        setTex(bPatt);
    }

    public Texture getTex() {
        return tex;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int t) {
        track = t;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int t) {
        count = t;
    }

    public void subtractCount() {
        count -= 1;
    }
}