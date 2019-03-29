package com.beatbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.LinkedList;

public class WriteTrack extends Track {

    float shift = 0;

    public WriteTrack(int x){
        super(x);
        for (int i = 0; i < 10; i++) {
            super.getSects().add(new TrackSection(0, 0, 0));
        }
    }

    public void run(SpriteBatch batch, int bpm, float delta) {
        for (int i = super.getSects().size() - 1; i >= 1; i--) {
            shift += (5f * delta);
            Sprite sSprite = super.getSects().get(i).getSprite();
            sSprite.setSize(300,60);
            sSprite.setPosition(super.getXpos() - sSprite.getWidth()/2, i * sSprite.getHeight());
            sSprite.translateY(shift);
            sSprite.draw(batch);
            if (shift > sSprite.getHeight()) {
                loopSection();
                shift = 0;
            }
        }
    }

    private void loopSection() {
        LinkedList<TrackSection> sects = super.getSects();
        sects.addFirst(sects.get(sects.size() - 1));
        sects.removeLast();

    }

}
