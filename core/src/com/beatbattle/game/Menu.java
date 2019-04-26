package com.beatbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class Menu implements Screen{

    final BeatBattle game;
    Stage stage;
    //varibales for the button

    //The Style objects
    BitmapFont font, font2;
    LabelStyle labelStyle;
    ButtonStyle buttonStyle;
    Texture texture;

    TextButtonStyle textbuttonStyle;
    TextButton textbutton,textbutton2;
    TextButton BPM_Button,Volume_Button,Metronome_Button;
    Label label, label2;

    // change the background color
    private MyActor logoActor;
    private Texture logoTexture;

    public Menu(final BeatBattle game) {

        this.game = game;
        stage = new Stage();

        logoTexture = new Texture(Gdx.files.internal("bg.jpg"));
        logoActor = new MyActor(new TextureRegion(logoTexture));
        logoActor.setPosition( stage.getWidth() / 2 - logoActor.getWidth() / 2, stage.getHeight() / 2 - logoActor.getHeight() / 2 );

        // draw1 , dark_gray  color;
        // draw2 , white  color

        Pixmap pixmap = new Pixmap( 3, 3, Format.RGBA8888 );
        // you can change the background color here of every button
        pixmap.setColor( Color.DARK_GRAY );
        pixmap.fill();
        texture = new Texture( pixmap );
        Drawable draw1 = new TextureRegionDrawable( new TextureRegion( texture ) );
        pixmap.setColor( Color.GRAY );
        pixmap.fill();
        texture = new Texture( pixmap );
        Drawable draw2 = new TextureRegionDrawable( new TextureRegion( texture ) );

        font = new BitmapFont();
        font2 = new BitmapFont();
        font2.getData().setScale(1,1);
        labelStyle = new LabelStyle(font, Color.LIME);
        //set the label background color as white
        //label:The title for the game
        label = new Label( "Beat Battle", labelStyle);
        label.setFontScale(3,3);
        label.setAlignment( Align.center );

        int button_width = 150;
        int button_height = 50;
        int button_location_X = 250;
        label.setBounds(button_location_X, 400, button_width, 50 );

        buttonStyle = new ButtonStyle( draw1, draw2, null );
        textbuttonStyle = new TextButtonStyle( draw1, draw2, null, font2 );
        textbuttonStyle.fontColor = Color.CYAN;

        textbutton = new TextButton( "2 Player Versus", textbuttonStyle );
        textbutton.setBounds(button_location_X, 300, button_width, button_height );

        label2 = new Label( "Settings", labelStyle);
        label2.setAlignment( Align.center );
        label2.setColor(Color.CYAN);
        label2.setFontScale(2,2);
        label2.setBounds(button_location_X, 200, button_width, 50 );



        BPM_Button = new TextButton("BPM",textbuttonStyle);
        BPM_Button.setBounds(button_location_X, 150, button_width, button_height);

        Volume_Button = new TextButton("Volume",textbuttonStyle);
        Volume_Button.setBounds(button_location_X, 100, button_width, button_height);

        Metronome_Button = new TextButton("Metronome",textbuttonStyle);
        Metronome_Button.setBounds(button_location_X, 50, button_width, button_height);


        final TextButton buttonPlayer = textbutton;
        TextButton buttonSettings = textbutton2;


        buttonPlayer.setDisabled( true );
        buttonPlayer.addListener(new InputListener(){
            // do something here
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
                //Currently,when click on the button , the screen switch to the game interface

                game.setScreen(new TwoPVersus(game));
                System.out.println("Game started now !");
                return true;
            }

            // This method touchUp() has no need to exist.
         	/*
              public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
              	// do something here
              	System.out.println("2 Player");
              }
            */
        });

        BPM_Button.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {

                // do something here
                // you can do it like this: game.setScreen(new Stage2(game));
                // Jump to another screen to finish the setting.
                System.out.println("1.BPM_Button Setting ");
                return true;
            }
        });

        Metronome_Button.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
                // TODO Auto-generated method stub
                // do something here
                System.out.println("2.Metronome_Button ");
                return true;
            }

        });

        Volume_Button.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
                // TODO Auto-generated method stub
                // do something here
                System.out.println("3.Volume_Button ");
                return true;
            }

        });

        // Place the actors on the stage.
        stage.addActor(logoActor);

        stage.addActor(label);
        stage.addActor(label2);
        stage.addActor(textbutton);
        stage.addActor(BPM_Button);
        stage.addActor(Volume_Button);
        stage.addActor(Metronome_Button);


        // table.setPosition( stage.getWidth()/2-table.getWidth()/2, stage.getHeight()/2-table.getHeight()/2 );
        Gdx.input.setInputProcessor( stage );

    }


    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub

        Gdx.gl.glClearColor(0.75F, 1, 0.98F, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}