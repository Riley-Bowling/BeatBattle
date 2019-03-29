package com.beatbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WriteTrack extends Track {

    float shift = 0;

    public WriteTrack(int x){
        super(x);
        for (int i = 0; i < 8; i++) {
            super.getSects().add(new TrackSection(0, 0, 0));
        }
    }

    public void run(SpriteBatch batch, int bpm, float delta) {
        for (int i = 0; i < super.getSects().size(); i++) {
            shift += (5f * delta);
            Sprite sSprite = super.getSects().get(i).getSprite();
            sSprite.setPosition(super.getXpos() - sSprite.getWidth()/2, i * sSprite.getHeight());
            sSprite.setSize(300,60);
            sSprite.translateY(shift);
            sSprite.draw(batch);
        }
    }
}
