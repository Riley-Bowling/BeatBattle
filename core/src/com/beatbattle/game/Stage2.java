package com.beatbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.beatbattle.game.BeatBattle;

public class Stage2 implements Screen {

    final BeatBattle game;

    OrthographicCamera camera;
    ExtendViewport viewport;
    WriteTrack track;

    public Stage2(final BeatBattle game) {
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 600, camera);
        track = new WriteTrack((int) viewport.getMinWorldWidth()/2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();

        track.create(game.batch);
        track.run(game.batch, 100);

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            BasicBeat beat = new BasicBeat(50,50);
            beat.getSprite().draw(game.batch);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)) {

        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {

        }

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