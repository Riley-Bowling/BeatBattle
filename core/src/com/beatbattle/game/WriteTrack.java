package com.beatbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

public class WriteTrack extends Track {

    private float shift = 0;
    //variables for tracking if the three keys have been pressed/released within each row of the track
    private Boolean apres = false;
    private Boolean arele = true;
    private Boolean spres = false;
    private Boolean srele = true;
    private Boolean dpres = false;
    private Boolean drele = true;

    //tracks if metronome has sounded within a row
    private Boolean ticked = false;

    //tracks if a beat combo has been placed within a row
    private Boolean beatlayed = false;

    private Sound kick = Gdx.audio.newSound(Gdx.files.internal("DrumKit/kick.wav"));
    private Sound snare = Gdx.audio.newSound(Gdx.files.internal("DrumKit/snare.wav"));
    private Sound hihat = Gdx.audio.newSound(Gdx.files.internal("DrumKit/hihat.wav"));
    private Sound tick = Gdx.audio.newSound(Gdx.files.internal("DrumKit/tick.wav"));

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

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                if (apres == false && arele == true && beatlayed == false) {
                    super.getSects().get(0).setPatt(1, 0, 0);
                    beatlayed = true;
                    kick.play();
                    apres = true;
                    arele = false;
                }
            }
            else {
                arele = true;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (spres == false && srele == true && beatlayed == false) {
                    super.getSects().get(0).setPatt(0, 1, 0);
                    beatlayed = true;
                    snare.play();
                    spres = true;
                    srele = false;
                }
            }
            else {
                srele = true;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (dpres == false && drele == true && beatlayed == false) {
                    super.getSects().get(0).setPatt(0, 0, 1);
                    beatlayed = true;
                    hihat.play();
                    dpres = true;
                    drele = false;
                }
            }
            else {
                drele = true;
            }

            /*if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.A)) {
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
            }*/

            //metronome
            if ((int) shift == sSprite.getHeight()/2 && ticked == false) {
                tick.play(0.2f);
                ticked = true;
            }

            if (shift > sSprite.getHeight()) {
                loopSection();
                shift = 0;
                apres = false;
                spres = false;
                dpres = false;
                ticked = false;
                beatlayed = false;
            }
        }
    }

    private void loopSection() {
        LinkedList<TrackSection> sects = super.getSects();
        sects.addFirst(sects.get(sects.size() - 1));
        sects.removeLast();
    }

}