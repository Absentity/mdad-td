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
import com.me.tiledMapGame.TiledMapGame;

public class MainMenuScreen implements Screen {

	// TODO: Extend this to launch Play screen instead of setting it every time in TiledMapGame.java 
	public ClickListener levelSelectListener = new ClickListener();
	public ClickListener levelLoadListener = new ClickListener();
	public ClickListener creditsMenuListener = new ClickListener();
	
	private Stage stage = new Stage();
	private TextButton startGameButton;
	private TextButton loadGameButton;
	private TextButton creditsButton;
	private TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
	// TODO: unify assets, get from central location
	private Texture background = new Texture("img/title512x512.png");
	private Sprite b = new Sprite(background);
	private SpriteBatch batch = new SpriteBatch();

	public static boolean done = false; // FOR TESTING
	
	public MainMenuScreen(){
		style.font = new BitmapFont();
		style.overFontColor = new Color(.1f,.1f,.1f,1f);
		style.fontColor = new Color(1,1,1,1);
		startGameButton = new TextButton("Start", style);    
		loadGameButton = new TextButton("Load", style);
		creditsButton = new TextButton("Credits", style);
				
		startGameButton.setWidth(32f);
		startGameButton.setHeight(12f);
		startGameButton.setBounds((stage.getWidth() - startGameButton.getWidth() - 48)/2, 240, 32, 12);
		startGameButton.addListener(levelSelectListener);
		startGameButton.setX((stage.getWidth()- startGameButton.getWidth() - 48)/2);
		startGameButton.setY(240);
		
		loadGameButton.setWidth(32f);
		loadGameButton.setHeight(12f);
		loadGameButton.addListener(levelLoadListener);
		loadGameButton.setX((stage.getWidth()- loadGameButton.getWidth() - 48)/2);
		loadGameButton.setY(200);
		
		creditsButton.setWidth(32f);
		creditsButton.setHeight(12f);
		creditsButton.addListener(creditsMenuListener);
		creditsButton.setX((stage.getWidth()- creditsButton.getWidth() - 48)/2);
		creditsButton.setY(160);
		
		stage.addActor( startGameButton );  
		stage.addActor( loadGameButton );
		stage.addActor( creditsButton );
		b.setSize(TiledMapGame.screenWidth, TiledMapGame.screenHeight);
		Gdx.input.setInputProcessor(stage);
	}
	
	/**
	 * Load level select screen 
	 */
	private void startNewLevel() {
	}
	
	/**
	 * Load level from saved game
	 */
	private void loadSavedGame() {
		// new GameScreen(Level.load());
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
