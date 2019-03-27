package com.beatbattle.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture beat_img;
	Texture track_img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		beat_img = new Texture("beat.png");
		track_img = new Texture("track.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(track_img, 50, 50,500,150);
		batch.draw(beat_img, 80, 80,80,80);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		beat_img.dispose();
		track_img.dispose();
	}
}
