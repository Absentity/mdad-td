package com.me.tiledMapGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.tiledMapGame.TiledMapGame;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.pathing.ObjectGrid;

public class LevelSelectScreen implements Screen {
	
	public ClickListener goBackListener;
	public ClickListener level1Listener;
	public ClickListener level2Listener;
	public ClickListener level3Listener;
	public ClickListener level4Listener;
	public ClickListener level5Listener;
	private Texture background;
	private Stage stage;
	private Sprite b;
	private SpriteBatch batch;
	private TextButton goBackButton;
	private TextButton.TextButtonStyle style;
	private Skin levelButtons;
	private Button Level1;
	private Button Level2;
	private Button Level3;
	private Button Level4;
	private Button Level5;
	public String Level;
	
	public LevelSelectScreen(){
		//Set up Listeners
		setupSkin();
		createLevelButtons();
		goBackListener = new ClickListener();
		level1Listener= new ClickListener();
		level2Listener= new ClickListener();
		level3Listener= new ClickListener();
		level4Listener= new ClickListener();
		level5Listener= new ClickListener();
		
		//Set up Background
		background = new Texture("img/levelScreenBackground.png");
		
		b = new Sprite(background);
		batch = new SpriteBatch();
		b.setSize(TiledMapGame.screenWidth, TiledMapGame.screenHeight);
		
		//Set up Buttons
		style = new TextButton.TextButtonStyle();
		style.font = new BitmapFont();
		style.fontColor = new Color(1f, 1f, 0, 1f);
		style.overFontColor = new Color(.1f, .1f, .1f, 1f);
		goBackButton = new TextButton("Go Back", style);
		goBackButton.setWidth(32f);
		goBackButton.setHeight(12f);
		goBackButton.addListener(goBackListener);
		goBackButton.setX(40);
		goBackButton.setY(24);
		b.setColor(new Color(100,100,100,.5f));
		Level1.addListener(level1Listener);
		Level2.addListener(level2Listener);
		Level3.addListener(level3Listener);
		Level4.addListener(level4Listener);
		Level5.addListener(level5Listener);
		stage = new Stage();
		stage.addActor(goBackButton);
		stage.addActor(Level1);
		stage.addActor(Level2);
		stage.addActor(Level3);
		stage.addActor(Level4);
		stage.addActor(Level5);
		
		Gdx.input.setInputProcessor(stage);
		
	}
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		b.draw(batch);
		batch.end();
		stage.act(delta);
		stage.draw();
		
	}
	private void createLevelButtons(){
		Level1=new Button(levelButtons.getDrawable("Level1"));
		Level2=new Button(levelButtons.getDrawable("Level2"));
		Level3=new Button(levelButtons.getDrawable("Level3"));
		Level4=new Button(levelButtons.getDrawable("Level4"));
		Level5=new Button(levelButtons.getDrawable("Level5"));
		
		Level1.setBounds(150, 350, 96, 96);
		Level2.setBounds(256, 350, 96, 96);
		Level3.setBounds(150, 244, 96, 96);
		Level4.setBounds(256, 244, 96, 96);
		Level5.setBounds(200, 138, 96, 96);
		//goBackButton.setWidth();
		//goBackButton.setHeight(12f);
		
	}
	private void setupSkin(){
		levelButtons = new Skin();
		levelButtons.add("Level1", new Sprite( new Texture("img/levelselectmap1.png")));
		levelButtons.add("Level2", new Sprite( new Texture("img/levelselectmap2.png")));
		levelButtons.add("Level3", new Sprite( new Texture("img/levelselectmap3.png")));
		levelButtons.add("Level4", new Sprite( new Texture("img/levelselectmap4.png")));
		levelButtons.add("Level5", new Sprite( new Texture("img/levelselectmap5.png")));
		
		
		
	}
	public void setLevel(String l){
		Level=l;
	}
	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
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
		stage.dispose();
		batch.dispose();
	}

}
