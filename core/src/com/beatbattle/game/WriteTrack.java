package com.beatbattle.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WriteTrack extends Track {
    public WriteTrack(int x){
        super(x);
        for (int i = 0; i < 8; i++) {
            super.getSects().add(new TrackSection(0, 0, 0));
        }
    }

    public void create(SpriteBatch batch) {
        //batch.draw();
        //draw each track in a vertical stack and then begin shifting them up according to a bpm
        for (int j = 0; j < 1; j++){
            for (int i = 0; i < super.getSects().size(); i++) {
                Sprite sSprite = super.getSects().get(i).getSprite();
                sSprite.setPosition(super.getXpos() - sSprite.getWidth()/2, i * sSprite.getHeight() + j);
                sSprite.setSize(300,60);
                sSprite.draw(batch);
            }
        }
    }

    public void run(SpriteBatch batch, int bpm) {

    }
}
