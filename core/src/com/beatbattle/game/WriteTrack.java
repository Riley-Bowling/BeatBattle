package com.beatbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

public class WriteTrack extends Track {

    private float shift = 0;
    private Sound kick = Gdx.audio.newSound(Gdx.files.internal("DrumKit/kick.wav"));
    private Sound snare = Gdx.audio.newSound(Gdx.files.internal("DrumKit/snare.wav"));
    private Sound hihat = Gdx.audio.newSound(Gdx.files.internal("DrumKit/hihat.wav"));

    public WriteTrack(int x){
        super(x);
        for (int i = 0; i < 10; i++) {
            super.getSects().add(new TrackSection(0, 0, 0));
        }
    }

    public void run(SpriteBatch batch, int bpm, float delta) {
        for (int i = super.getSects().size() - 1; i >= 0; i--) {
            shift += (5f * delta);
            Sprite sSprite = super.getSects().get(i).getSprite();
            sSprite.setSize(300,60);
            sSprite.setPosition(super.getXpos() - sSprite.getWidth()/2, i * sSprite.getHeight());
            sSprite.translateY(shift);
            sSprite.draw(batch);

            if (shift > sSprite.getHeight()) {
                loopSection();
                shift = 0;

                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    super.getSects().get(0).setPatt(1, 0, 0);
                    kick.play();
                }
                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    super.getSects().get(0).setPatt(0, 1, 0);
                    snare.play();
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    super.getSects().get(0).setPatt(0, 0, 1);
                    hihat.play();
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.A)) {
                    super.getSects().get(0).setPatt(1, 0, 1);
                    kick.play();
                    hihat.play();
                }
                if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.S)) {
                    super.getSects().get(0).setPatt(1, 1, 0);
                    kick.play();
                    snare.play();
                }
                if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)) {
                    super.getSects().get(0).setPatt(0, 1, 1);
                    snare.play();
                    hihat.play();
                }
            }
        }
    }

    private void loopSection() {
        LinkedList<TrackSection> sects = super.getSects();
        sects.addFirst(sects.get(sects.size() - 1));
        sects.removeLast();

    }

}