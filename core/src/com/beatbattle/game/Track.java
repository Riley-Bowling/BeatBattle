package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;

public abstract class Track {
    private Texture tex;
    private int xpos;
    private int ypos;

    public Track(int x, int y){
        this.xpos = x;
        this.ypos = y;
    }

}
