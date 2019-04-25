package com.beatbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

public class WriteTrack extends Track {

    private float shift = 0;
    //variables for tracking if the three keys have been pressed/released within each section of the track

    private Boolean apres = false;
    private Boolean arele = true;
    private Boolean spres = false;
    private Boolean srele = true;
    private Boolean dpres = false;
    private Boolean drele = true;

    //tracks if metronome has sounded within a section
    private Boolean ticked = false;

    //tracks if a beat combo has been placed within a section
    private Boolean beatlayed = false;

    private Sound kick = Gdx.audio.newSound(Gdx.files.internal("DrumKit/kick.wav"));
    private Sound snare = Gdx.audio.newSound(Gdx.files.internal("DrumKit/snare.wav"));
    private Sound hihat = Gdx.audio.newSound(Gdx.files.internal("DrumKit/hihat.wav"));
    private Sound tick = Gdx.audio.newSound(Gdx.files.internal("DrumKit/tick.wav"));

    public WriteTrack(int x, int BPM){
        super(x, BPM);
        for (int i = 0; i < 16; i++) {
            super.getSects().add(new TrackSection(0, 0, 0));
            Sprite sSprite = super.getSects().get(i).getSprite();
            sSprite.setSize(300,60);
            sSprite.setPosition(super.getXpos() - sSprite.getWidth()/2, i * sSprite.getHeight() - 60);
        }
    }

    public void run(SpriteBatch batch, int bpm, float delta) {
        shift += (super.getSpeed() * delta);
        for (int i = super.getSects().size() - 1; i >= 0; i--) {
            Sprite sSprite = super.getSects().get(i).getSprite();
            sSprite.translateY(super.getSpeed() * delta);
            sSprite.draw(batch);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (apres == false && arele == true && beatlayed == false) {
                super.getSects().get(1).setPatt(1, 0, 0);
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
                super.getSects().get(1).setPatt(0, 1, 0);
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
                super.getSects().get(1).setPatt(0, 0, 1);
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
        if (shift > super.getSects().get(0).getSprite().getHeight()/2 && ticked == false) {
            tick.play(0.2f);
            ticked = true;
        }

        if (shift > super.getSects().get(0).getSprite().getHeight()) {
            loopSection();
            shift = 0;
            apres = false;
            spres = false;
            dpres = false;
            ticked = false;
            beatlayed = false;
        }
        /*For Error Checking
        System.out.println((int) shift);
        System.out.println(super.getSects().get(0).getSprite().getHeight());*/
    }

    private void loopSection() {
        //loop list
        LinkedList<TrackSection> sects = super.getSects();
        sects.addFirst(sects.get(sects.size() - 1));
        sects.removeLast();

        //loop graphics
        for (int i = super.getSects().size() - 1; i >= 0; i--) {
            Sprite sSprite = super.getSects().get(i).getSprite();
            sSprite.setPosition(super.getXpos() - sSprite.getWidth() / 2, i * sSprite.getHeight() - 60);
        }
    }

}