package com.me.tiledMapGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen implements Screen {

	// TODO: Extend this to launch Play screen instead of setting it every time in TiledMapGame.java 
	public ClickListener listener = new ClickListener();
	private Stage stage = new Stage();
	private TextButton startGameButton;
	private TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
	// TODO: unify assets, get from central location
	private Texture background = new Texture("..\\TiledMapGame-android\\assets\\img\\MDAD-TITLE-v3.png");
	private Sprite b = new Sprite(background);
	private SpriteBatch batch = new SpriteBatch();
	
	public MainMenuScreen(){
		style.font = new BitmapFont();
		style.overFontColor = new Color(.1f,.1f,.1f,1f);
		style.fontColor = new Color(1,1,1,1);
		startGameButton = new TextButton("Start", style);
    
		// label "welcome"       
//		Label welcomeLabel = new Label( "Welcome to Tyrian for Android!", new Skin());   
//		welcomeLabel.setX(( ( width - welcomeLabel.getWidth() ) / 2 ));       
//		welcomeLabel.setY(( currentY + 100 ));      
//		stage.addActor( welcomeLabel );        
		// button "start game"        

		startGameButton.setWidth(32f);
		startGameButton.setHeight(12f);
		startGameButton.setBounds(stage.getWidth() - startGameButton.getWidth() / 2, 280, 32, 12);
		startGameButton.addListener(listener);
		stage.addActor( startGameButton );  
		b.setSize(640, 640);
		Gdx.input.setInputProcessor(stage);	
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		b.draw(batch);
		batch.end();
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		float buttonX = ( stage.getWidth() - startGameButton.getWidth()) / 2;
		float currentY = 280f;   
		
		startGameButton.setX(buttonX);    
		startGameButton.setY(currentY);   
		startGameButton.setWidth(32.0f);  
		startGameButton.setHeight(12.0f);  
     
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
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
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		batch.dispose();
		
	}

}
