package com.beatbattle.game;

import com.badlogic.gdx.Gdx;
 
import com.badlogic.gdx.Screen;
 
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
 
import com.badlogic.gdx.graphics.Texture;
 
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
 
 
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.beatbattle.game.BeatBattle;
 

public class Stage2 implements Screen {

    final BeatBattle game;

    OrthographicCamera camera;
    ExtendViewport viewport;
    WriteTrack track;
    BasicBeat beat;
    
    //variables for the background
    Texture texture;
    SpriteBatch batch;
    TextureRegion region;
    
  

    public Stage2(final BeatBattle game) {
        this.game = game;
        

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 600, camera);
        viewport.apply();
        track = new WriteTrack((int) viewport.getMinWorldWidth()/2);
        beat = new BasicBeat(50,50);
        
        texture = new Texture( Gdx.files.internal("bg.jpg") );
        batch = new SpriteBatch();
        region = new TextureRegion( texture );
        
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
       // game.batch.draw( texture, 50, 50 );
        game.batch.draw(texture,0,0);

        track.run(game.batch, 100, delta);
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