package com.gdx.game;
 

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
 
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GdxAction extends ApplicationAdapter{

    Stage stage;
    Image img;
    Texture texture;
    Action totalAction;
    Action exitAction;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        stage = new Stage();
        texture = new Texture( Gdx.files.internal( "badlogic.jpg" ) );
        img = new Image( texture );
        img.setSize( texture.getWidth(), texture.getHeight() );
        //img.setPosition( stage.getWidth()/2-img.getWidth()/2, stage.getHeight()/2-img.getHeight()/2 );
        img.setOrigin( img.getWidth()/2, img.getHeight()/2 );
        stage.addActor( img );
        
        MoveByAction action1 = Actions.moveBy( stage.getWidth()/2-img.getWidth()/2, stage.getHeight()/2-img.getHeight()/2, 2 );
        ScaleByAction action2 = Actions.scaleBy( 2, 2, 2 );
        RotateByAction action3 = Actions.rotateBy( -360, 2 );
        
        RotateToAction action4 = Actions.rotateTo( 0, 2 );
        ScaleToAction action5 = Actions.scaleTo( 1, 1, 2 );
        MoveToAction action6 = Actions.moveTo( 0, 0, 2 );        
        
        totalAction = Actions.forever( Actions.sequence( Actions.sequence(action1, action2, action3), Actions.sequence(action4, action5, action6) ) );
        img.addAction( totalAction );
        
        
        AlphaAction action7 = Actions.fadeOut( 1 );
        AlphaAction action8 = Actions.fadeIn( 1 );
        Action action9 = new Action(){
            @Override
            public boolean act(float delta) {
                // TODO Auto-generated method stub
                stage.getRoot().removeActor( this.getActor() );
                return false;
            }            
        };
        exitAction = Actions.sequence( action7, action8, action9 );
        
        img.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                img.clearActions();
                img.addAction( exitAction );
                return true;
            }        
        });
        
        
        Gdx.input.setInputProcessor( stage );
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
        
        stage.act();
        stage.draw();        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
        super.dispose();
    }

}