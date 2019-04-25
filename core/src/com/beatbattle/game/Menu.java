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
    BitmapFont font;
    LabelStyle labelStyle;
    ButtonStyle buttonStyle;
    Texture texture;
    
    TextButtonStyle textbuttonStyle;
    TextButton textbutton,textbutton2;
    TextButton BPM_Button,Volume_Button,Metronome_Button;
    Label label; 
    
    // change the background color
    private MyActor logoActor;
    private Texture logoTexture;
    
    public Menu(final BeatBattle game) {
    	
    	this.game = game;
    	stage = new Stage();
    	 
    	logoTexture = new Texture(Gdx.files.internal("bgm2.jpg"));
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
         labelStyle = new LabelStyle( font, Color.GREEN );
         //set the label background color as white
         labelStyle.background = draw2;
         //label:The title for the game
         label = new Label( "Beat Battle", labelStyle );
         label.setAlignment( Align.center );
         label.setSize(600, 400 );
         
         int button_width = 150;
         int button_height = 50;
         int button_location_X = 250;
         label.setBounds(button_location_X, 400, button_width, 50 );
         
         buttonStyle = new ButtonStyle( draw1, draw2, null );
         textbuttonStyle = new TextButtonStyle( draw1, draw2, null, font );
         textbuttonStyle.fontColor = Color.CYAN;
         
         textbutton = new TextButton( "2 Player Versus", textbuttonStyle );
         textbutton.setBounds(button_location_X, 300, button_width, button_height );
         
         textbutton2 = new TextButton( "Settings", textbuttonStyle );
         textbutton2.setBounds(button_location_X, 200, button_width, button_height );
         
         BPM_Button = new TextButton("1.BPM Setting",textbuttonStyle);
         BPM_Button.setBounds(button_location_X, 150, button_width, button_height);
         
         Volume_Button = new TextButton("2.Volume Setting",textbuttonStyle);
         Volume_Button.setBounds(button_location_X, 100, button_width, button_height);
         
         Metronome_Button = new TextButton("3.Metronome Setting",textbuttonStyle);
         Metronome_Button.setBounds(button_location_X, 50, button_width, button_height);
         
         
          final TextButton buttonPlayer =textbutton;
          TextButton buttonSettings = textbutton2;
         
     
         buttonPlayer.setDisabled( true );
         buttonPlayer.addListener(new InputListener(){
        	// do something here
         	 public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
                //Currently,when click on the button , the screen switch to the game interface
         		 
         		 game.setScreen(new Stage2(game));
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
         
         buttonSettings.addListener(new InputListener(){
             public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
                
             	// do something here
            
             	System.out.println("Button Setting ");
                 return true;
             }
             public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
             	// do something here
             	System.out.println("Set something");
             }            
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
         stage.addActor(textbutton);
         stage.addActor(textbutton2);
         stage.addActor( BPM_Button);
         stage.addActor( Volume_Button);
         stage.addActor( Metronome_Button);
         
          
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
