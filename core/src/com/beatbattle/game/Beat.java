package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;

public abstract class Beat {
    private Texture tex;
    private int xpos;
    private int ypos;

    public Beat(int x, int y){
        this.xpos = x;
        this.ypos = y;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }


}
