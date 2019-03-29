package com.beatbattle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TrackSection {
    int bInfo[];
    private Sprite sprite;
    private Texture tex = new Texture("track.png");

    public TrackSection(int a, int b, int c) {
        bInfo = new int[]{a, b, c};
        sprite = new Sprite(tex);
    }

    public Sprite getSprite() {
        return sprite;
    }
}