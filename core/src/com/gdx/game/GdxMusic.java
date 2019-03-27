package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class  GdxMusic extends ApplicationAdapter implements ApplicationListener{
    
    Music music;
    Sound sound;
    Stage stage;
    State state;
    
    //The Style objects
    BitmapFont font;
    LabelStyle labelStyle;
    ButtonStyle buttonStyle;
    
    TextButtonStyle textbuttonStyle;
    TextButton textbutton,textbutton2 ;
    Texture texture ,slider_knob;
    Label label; 
    
    enum State{ music_play, music_stop, music_pause };
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        
        music = Gdx.audio.newMusic( Gdx.files.internal( "audio.mp3" ) );
        music.setLooping( true );
        music.setVolume( 0.5f );
        //music.play();
       
        stage = new Stage();
       
        
        Pixmap pixmap = new Pixmap( 1, 1, Format.RGBA8888 );
        pixmap.setColor( Color.DARK_GRAY );
        pixmap.fill();
        texture = new Texture( pixmap );
        Drawable draw1 = new TextureRegionDrawable( new TextureRegion( texture ) );
        
        pixmap.setColor( Color.GRAY );
        pixmap.fill();
        texture = new Texture( pixmap );
        Drawable draw2 = new TextureRegionDrawable( new TextureRegion( texture ) );
        
        font = new BitmapFont();
        labelStyle = new LabelStyle( font, Color.GREEN );
        labelStyle.background = draw1;
        label = new Label( "The GDXGAME Music Player", labelStyle );
        label.setAlignment( Align.right );
        label.setSize(500, 300 );
        label.setBounds( 10, 0, 100, 50 );
        
        buttonStyle = new ButtonStyle( draw1, draw2, null );
        textbuttonStyle = new TextButtonStyle( draw1, draw2, null, font );
        textbuttonStyle.fontColor = Color.CYAN;
        
        textbutton = new TextButton( "Stop", textbuttonStyle );
        textbutton.setBounds( 0, 100, 100, 50 );
        
        textbutton2 = new TextButton( "Play/Pause", textbuttonStyle );
        textbutton2.setBounds( 150, 100, 100, 50 );
        
         final TextButton buttonStop =textbutton;
         TextButton buttonPlay = textbutton2;
        
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
                    System.out.println( "stop" );
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
        
        
        Slider.SliderStyle ss = new Slider.SliderStyle();
        ss.background = new TextureRegionDrawable(new TextureRegion(texture));    
        slider_knob = new Texture(Gdx.files.internal("logo.png"));
        ss.knob = new TextureRegionDrawable(new TextureRegion(slider_knob));
        final Slider slider = new Slider( 0, 100, 1, false,ss);
        slider.addListener(new ChangeListener(){
            public void changed(ChangeEvent event, Actor actor) {
               
                music.setVolume( slider.getValue()/100 );
            }        
        });
        
        slider.setValue( 50 );
        Table table = new Table();
        table.defaults().space(5);

        table.row();
        table.add(label).colspan(4).expandX();
        table.row();
        table.add( slider ).colspan(4).expandX();
        table.row();
        table.add( buttonPlay ).minWidth(100);
        table.add( buttonStop ).minWidth(100);
        table.pad( 10 );
        table.pack();
        table.setBackground( draw1);
       
        stage.addActor( table );
        table.setPosition( stage.getWidth()/2-table.getWidth()/2, stage.getHeight()/2-table.getHeight()/2 );
        Gdx.input.setInputProcessor( stage );
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
        
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