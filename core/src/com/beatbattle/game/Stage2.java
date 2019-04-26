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

        //draw beats
        game.font.draw(game.batch, String.valueOf(track1.getPlayer().getBeats()), 15, 40);
        game.font.draw(game.batch, String.valueOf(track2.getPlayer().getBeats()), viewport.getMinWorldWidth() - 30, 40);

        track1.run(game.batch, 100, delta);
        track2.run(game.batch, 100, delta);

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