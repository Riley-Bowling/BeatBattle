package com.beatbattle.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MyGdxGame  extends ApplicationAdapter{
    
    Music music;
    Sound sound;
    
    Skin skin;
    Stage stage;
    State state;
    
    enum State{ music_play, music_stop, music_pause };
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        
        music = Gdx.audio.newMusic( Gdx.files.internal( "audio/audio.mp3" ) );  // directory name and audio name 
        music.setLooping( true );  // default , allow loop 
        music.setVolume( 0.5f );
        //music.play();
    
        stage = new Stage();
        skin = new Skin( Gdx.files.internal( "skin/uiskin.json" ) );      //create a new stage 
        final TextButton buttonStop = new TextButton( "Stop", skin );     // a button to control the music play.
        TextButton buttonPlay = new TextButton( "Play/Pause", skin );
        
        state = State.music_stop;
        buttonStop.setDisabled( true );  
        buttonStop.addListener(new InputListener(){  
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
                // TODO Auto-generated method stub
                return true;
            }
            public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
                // TODO Auto-generated method stub
                if( state != State.music_stop ){
                    music.stop();
                    state = State.music_stop;
                    buttonStop.setDisabled( true );
                    System.out.println( "stop" );    // print something to monitor the process.
                }
            }            
        });
        
        buttonPlay.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
                // TODO Auto-generated method stub
                return true;
            }
            public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
                // TODO Auto-generated method stub
                if( state == State.music_play ){
                    music.pause();
                    state = State.music_pause;
                    System.out.println( "pause" );
                }
                else{
                    music.play();
                    state = State.music_play;
                    buttonStop.setDisabled( false );    
                    System.out.println( "play" );
                }                            
                //(state==State.music_play)? music.pause(): music.play();
            }            
        });    
        
        // slider to control the process of the music    
        final Slider slider = new Slider( 0, 100, 1, false, skin );
        slider.addListener(new ChangeListener(){
            public void changed(ChangeEvent event, Actor actor) {
                // TODO Auto-generated method stub
                music.setVolume( slider.getValue()/100 );
            }        
        });
        
        slider.setValue( 50 );
        Table table = new Table();
        table.defaults().space(5);
		// set a container here 
        table.row();
        table.add( new Label( "Music Play", skin )  ).colspan(2).expandX();
        table.row();
        table.add( slider ).colspan(2).expandX();
        table.row();
        table.add( buttonPlay ).minWidth(100);
        table.add( buttonStop ).minWidth(100);
        table.pad( 10 );
        table.pack();
        table.setBackground( skin.newDrawable( "white", Color.PINK ) );

        stage.addActor( table );
        table.setPosition( stage.getWidth()/2-table.getWidth()/2, stage.getHeight()/2-table.getHeight()/2 );
		//Input 
        Gdx.input.setInputProcessor( stage );
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        music.dispose();
        super.dispose();
    }

}