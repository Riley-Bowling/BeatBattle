package com.beatbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

public class WriteTrack extends Track {

    private float shift = 0;
    //variables for tracking if the three keys have been pressed/released within each section of the track

    private Boolean apres, spres, dpres;
    private Boolean arele, srele, drele;
    private Boolean flip;

    //tracks if metronome has sounded within a section
    private Boolean ticked = false;

    //tracks if a beat combo has been placed within a section
    private Boolean beatlayed = false;

    private Sound kick = Gdx.audio.newSound(Gdx.files.internal("DrumKit/kick.wav"));
    private Sound snare = Gdx.audio.newSound(Gdx.files.internal("DrumKit/snare.wav"));
    private Sound hihat = Gdx.audio.newSound(Gdx.files.internal("DrumKit/hihat.wav"));
    private Sound tick = Gdx.audio.newSound(Gdx.files.internal("DrumKit/tick.wav"));
    private Sound NO = Gdx.audio.newSound(Gdx.files.internal("DrumKit/NO.wav"));

    private Texture track = new Texture("track.png");
    public WriteTrack(int x, int BPM, LinkedList<TrackSection> t, int c){
        super(x, BPM, t, c);
        //beats per loop = 12
        super.setCounter(12);
        apres = false;
        spres = false;
        dpres = false;

        arele = true;
        srele = true;
        drele = true;

        flip = true;

        for (int i = 0; i < 12; i++) {
            if (super.getControlScheme() == 1) {
                super.getSects().add(new TrackSection(0, 0, 0, 3, -1));
            }

            Sprite sSprite = new Sprite(super.getSects().get(i).getTex());
            super.getSectSprites().add(sSprite);

            sSprite.setSize(400,80);
            sSprite.setPosition(super.getXpos() - sSprite.getWidth()/2, i * sSprite.getHeight() - 80);
        }
    }

    public void run(SpriteBatch batch, int bpm, float delta) {
        shift += (super.getSpeed() * delta);
        for (int i = super.getSects().size() - 1; i >= 0; i--) {
            Sprite sSprite = super.getSectSprites().get(i);
            if (super.getSects().get(i).getTrack() == super.getControlScheme() || super.getSects().get(i).getTrack() == 3) {
                sSprite.setTexture(super.getSects().get(i).getTex());
            }
            else {
                sSprite.setTexture(track);
            }
            sSprite.translateY(super.getSpeed() * delta);
            sSprite.draw(batch);
        }

        if (super.getControlScheme() == 1) CheckAKeyPresses();
        if (super.getControlScheme() == 2) CheckLKeyPresses();

        //metronome
        if (shift > super.getSectSprites().get(0).getHeight()/2 && ticked == false) {
            tick.play(0.1f);
            ticked = true;
        }

        if (shift > super.getSectSprites().get(0).getHeight()) {


            //if nothing was pressed and sect is empty lose health
            if (((apres == true|| spres == true || dpres == true) != true) && super.getSects().get(4).isEmpty() == false &&
                    (super.getSects().get(4).getTrack() == super.getControlScheme() || super.getSects().get(4).getTrack() == 3)) {
                super.getPlayer().subtractHealth();
                NO.play(0.5f);
            }

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
        //loop BeatPattern

        if (super.getControlScheme() == 2) {

            LinkedList<TrackSection> temp = super.getSects();
            temp.addFirst(temp.get(temp.size() - 1));
            temp.removeLast();
        }

        for (int i = super.getSects().size() - 1; i >= 0; i--) {

            if (super.getSects().get(i).getCount() > 0){
                super.getSects().get(i).subtractCount();
            }
            else {
                super.getSects().get(i).setTrack(3);
            }

            Sprite sSprite = super.getSectSprites().get(i);
            sSprite.setPosition(super.getXpos() - sSprite.getWidth() / 2, i * sSprite.getHeight() - 80);
        }
        if (super.getCounter() == 1) {
            super.setCounter(super.getSects().size());
        }
        else {
            super.subtractCounter();
        }
    }

    private void CheckAKeyPresses (){
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (apres == false && arele == true && beatlayed == false) {
                TrackSection sect = super.getSects().get(4);
                //if a beat hasn't been layed
                if (sect.isEmpty() && super.getPlayer().getBeats() > 0) {
                    sect.setPatt(1, 0, 0);
                    sect.setTrack(super.getControlScheme());
                    sect.setCount(super.getSects().size());
                    beatlayed = true;
                    kick.play();
                    super.getPlayer().subtractBeat();
                }
                else {
                    //if you didn't hit the right beat
                    if (!(sect.getPatt()[0] == 1 && sect.getPatt()[1] == 0 && sect.getPatt()[2] == 0)) {
                        super.getPlayer().subtractHealth();
                        NO.play(0.5f);
                    }
                    else {
                        kick.play();
                    }
                }
                apres = true;
                arele = false;
            }
        } else {
            arele = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (spres == false && srele == true && beatlayed == false) {
                TrackSection sect = super.getSects().get(4);
                //if a beat hasn't been layed
                if (sect.isEmpty() && super.getPlayer().getBeats() > 0) {
                    sect.setPatt(0, 1, 0);
                    sect.setTrack(super.getControlScheme());
                    sect.setCount(super.getSects().size());
                    beatlayed = true;
                    snare.play();
                    super.getPlayer().subtractBeat();
                } else {
                    //if you didn't hit the right beat
                    if (!(sect.getPatt()[0] == 0 && sect.getPatt()[1] == 1 && sect.getPatt()[2] == 0)) {
                        super.getPlayer().subtractHealth();
                        NO.play(0.5f);
                    }
                    else {
                        snare.play();
                    }
                }
                spres = true;
                srele = false;
            } else {
                srele = true;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (dpres == false && drele == true && beatlayed == false) {
                TrackSection sect = super.getSects().get(4);
                //if a beat hasn't been layed
                if (sect.isEmpty() && super.getPlayer().getBeats() > 0) {
                    sect.setPatt(0, 0, 1);
                    sect.setTrack(super.getControlScheme());
                    sect.setCount(super.getSects().size());
                    beatlayed = true;
                    hihat.play();
                    super.getPlayer().subtractBeat();
                } else {
                    //if you didn't hit the right beat
                    if (!(sect.getPatt()[0] == 0 && sect.getPatt()[1] == 0 && sect.getPatt()[2] == 1)) {
                        super.getPlayer().subtractHealth();
                        NO.play(0.5f);
                    }
                    else {
                        hihat.play();
                    }
                }
                dpres = true;
                drele = false;
            } else {
                drele = true;
            }
        }
    }


    private void CheckLKeyPresses() {
        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            if (apres == false && arele == true && beatlayed == false) {
                TrackSection sect = super.getSects().get(4);
                //if a beat hasn't been layed
                if (sect.isEmpty() && super.getPlayer().getBeats() > 0) {
                    sect.setPatt(1, 0, 0);
                    sect.setTrack(super.getControlScheme());
                    sect.setCount(super.getSects().size());
                    beatlayed = true;
                    kick.play();
                    super.getPlayer().subtractBeat();
                } else {
                    //if you didn't hit the right beat
                    if (!(sect.getPatt()[0] == 1 && sect.getPatt()[1] == 0 && sect.getPatt()[2] == 0)) {
                        super.getPlayer().subtractHealth();
                        NO.play(0.5f);
                    }
                    else {
                        kick.play();
                    }
                }
                apres = true;
                arele = false;
            } else {
                arele = true;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SEMICOLON)) {
            if (spres == false && srele == true && beatlayed == false) {
                TrackSection sect = super.getSects().get(4);
                //if a beat hasn't been layed
                if (sect.isEmpty() && super.getPlayer().getBeats() > 0) {
                    sect.setPatt(0, 1, 0);
                    sect.setTrack(super.getControlScheme());
                    sect.setCount(super.getSects().size());
                    beatlayed = true;
                    snare.play();
                    super.getPlayer().subtractBeat();
                } else {
                    //if you didn't hit the right beat
                    if (!(sect.getPatt()[0] == 0 && sect.getPatt()[1] == 1 && sect.getPatt()[2] == 0)) {
                        super.getPlayer().subtractHealth();
                        NO.play(0.5f);
                    }
                    else {
                        snare.play();
                    }
                }
                spres = true;
                srele = false;
            } else {
                srele = true;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.APOSTROPHE)) {
            if (dpres == false && drele == true && beatlayed == false) {
                TrackSection sect = super.getSects().get(4);
                //if a beat hasn't been layed
                if (sect.isEmpty() && super.getPlayer().getBeats() > 0) {
                    sect.setPatt(0, 0, 1);
                    sect.setTrack(super.getControlScheme());
                    sect.setCount(super.getSects().size());
                    beatlayed = true;
                    hihat.play();
                    super.getPlayer().subtractBeat();
                }
                else {
                    //if you didn't hit the right beat
                    if (!(sect.getPatt()[0] == 0 && sect.getPatt()[1] == 0 && sect.getPatt()[2] == 1)) {
                        super.getPlayer().subtractHealth();
                        NO.play(0.5f);
                    }
                    else {
                        hihat.play();
                    }
                }
                dpres = true;
                drele = false;
        } else {
            drele = true;
        }

    }

    }
}