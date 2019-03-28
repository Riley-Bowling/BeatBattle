package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;

public class BasicBeat extends Beat {
    Texture tex = new Texture("beat.png");

    public BasicBeat(int x, int y) {
        super (x, y);
    }

    public Texture getTex() {
        return tex;
    }
}
