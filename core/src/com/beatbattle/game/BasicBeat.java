package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;

public class BasicBeat extends Beat {

    public BasicBeat(int x, int y) {
        super(x, y);
        super.setSprite(new Texture("beat.png"));
    }
}
