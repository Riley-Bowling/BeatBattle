package com.beatbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.beatbattle.game.BeatBattle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Color;


public class Stage2 implements Screen {

    final BeatBattle game;

    OrthographicCamera camera;
    ExtendViewport viewport;
    WriteTrack track1, track2;
    BasicBeat beat;

    //UI elements
    Sprite border, border2;
    Texture background;
    LabelStyle trackerStyle;

    public Stage2(final BeatBattle game) {
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 600, camera);
        viewport.apply();
        track1 = new WriteTrack((int) viewport.getMinWorldWidth()/4, 100, 1);
        track2 = new WriteTrack((int) (viewport.getMinWorldWidth() - viewport.getMinWorldWidth()/4), 100, 2);
        background = new Texture(Gdx.files.internal("bg.jpg"));

        border = new Sprite(new Texture(Gdx.files.internal("border.png")));
        border.setSize(330,110);
        border.setPosition(track1.getXpos() - 150 - 15, 60);
        game.font.getData().setScale(2,2);

        border2 = new Sprite(new Texture(Gdx.files.internal("border2.png")));
        border2.setSize(330,110);
        border2.setPosition(track2.getXpos() - 150 - 15, 60);
        game.font.getData().setScale(2,2);;
    }

    @Override
    public void render(float delta) {
        //update game
        delta = Math.max(1/30f, delta);

        //draw
        camera.update();
        Gdx.gl.glClearColor(0.78f, 0.77f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.batch.draw(background,0,0);

        //draw counter
        game.font.draw(game.batch, String.valueOf(track1.getCounter()), viewport.getMinWorldWidth()/2 - 12, 120);

        //draw beat trackers
        game.font.draw(game.batch, String.valueOf(track1.getPlayer().getBeats()), 15, 40);
        game.font.draw(game.batch, String.valueOf(track2.getPlayer().getBeats()), viewport.getMinWorldWidth() - 30, 40);

        //draw life trackers
        game.font.draw(game.batch, String.valueOf(track1.getPlayer().getHealth()), 15, viewport.getMinWorldHeight() - 20);
        game.font.draw(game.batch, String.valueOf(track2.getPlayer().getHealth()), viewport.getMinWorldWidth() - 30, viewport.getMinWorldHeight() - 20);

        track1.run(game.batch, 100, delta);
        track2.run(game.batch, 100, delta);

        border.draw(game.batch);
        border2.draw(game.batch);

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        game.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {
        //music
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}