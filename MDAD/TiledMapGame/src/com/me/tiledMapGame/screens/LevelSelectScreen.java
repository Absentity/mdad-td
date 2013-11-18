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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.tiledMapGame.TiledMapGame;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.pathing.ObjectGrid;

public class LevelSelectScreen implements Screen {
	
	public ClickListener goBackListener;
	private Texture background;
	private Stage stage;
	private Sprite b;
	private SpriteBatch batch;
	private TextButton goBackButton;
	private TextButton.TextButtonStyle style;
	
	public LevelSelectScreen(){
		//Set up Listeners
		goBackListener = new ClickListener();
		
		//Set up Background
		background = new Texture("img/levelScreenBackground.png");
		b = new Sprite(background);
		batch = new SpriteBatch();
		b.setSize(TiledMapGame.screenWidth, TiledMapGame.screenHeight);
		
		//Set up Buttons
		style = new TextButton.TextButtonStyle();
		style.font = new BitmapFont();
		style.fontColor = new Color(.8f, .9f, 0, 1f);
		style.overFontColor = new Color(.1f, .1f, .1f, 1f);
		goBackButton = new TextButton("Go Back", style);
		goBackButton.setWidth(32f);
		goBackButton.setHeight(12f);
		goBackButton.addListener(goBackListener);
		goBackButton.setX(40);
		goBackButton.setY(24);
		
		//Set up Stage
		stage = new Stage();
		stage.addActor(goBackButton);
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
