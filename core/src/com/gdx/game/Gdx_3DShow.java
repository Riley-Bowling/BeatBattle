package com.gdx.game;

 

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class Gdx_3DShow extends ApplicationAdapter{

    PerspectiveCamera camera;
    Model model;
    ModelInstance instance;
    ModelBatch modelBatch;
    Environment environment;
    
    CameraInputController cameraController;
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        
        camera = new PerspectiveCamera( 67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        camera.position.set( 10, 10, 10 );
        camera.lookAt( 0, 0, 0 );
        camera.near = 1f;
        camera.far = 300f;
        camera.update();
        
        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox( 5f, 5f, 5f, new Material( ColorAttribute.createDiffuse(Color.GREEN) ), Usage.Position | Usage.Normal );
        instance = new ModelInstance( model );
        modelBatch = new ModelBatch();
        
        environment = new Environment();
        environment.set( new ColorAttribute( ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f ) );
        environment.add( new DirectionalLight().set( 0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f ) );
        
        cameraController = new CameraInputController( camera );
        Gdx.input.setInputProcessor( cameraController );
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        Gdx.gl.glViewport( 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        
        cameraController.update();
        modelBatch.begin( camera );
        //modelBatch.render( instance );
        modelBatch.render( instance, environment );
        modelBatch.end();
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        modelBatch.dispose();
        model.dispose();
        super.dispose();
    }

}
