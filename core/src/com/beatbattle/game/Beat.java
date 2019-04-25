package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Beat {
    private Sprite sprite;
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

    public void setSprite(Texture tex) {
        sprite = new Sprite(tex);
        sprite.setX(xpos);
        sprite.setY(ypos);
    }

    public Sprite getSprite() {
        return sprite;
    }


}
