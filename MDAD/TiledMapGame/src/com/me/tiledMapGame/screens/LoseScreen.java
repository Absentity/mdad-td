package com.me.tiledMapGame.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.tiledMapGame.TiledMapGame;

public class LoseScreen implements Screen {

	public ClickListener listener = new ClickListener();
	private TextButton backToMain;
	private SpriteBatch spriteBatch;
    private Texture splashTexture;
    private Stage stage;
	private TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
    
    public LoseScreen() {
		style.font = new BitmapFont();
		style.overFontColor = new Color(0f,.1f,.1f,1f);
		style.fontColor = new Color(1,1,1,1);
		stage = new Stage();
		backToMain = new TextButton("Continue", style); 
		backToMain.setBounds((stage.getWidth()/2) - 32, 300, 32, 12);
		backToMain.addListener(listener);
		stage.addActor(backToMain);
		
		Gdx.input.setInputProcessor(stage);

    }

    
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(splashTexture, 0, 0);
        spriteBatch.end();
        stage.act(delta);
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		splashTexture = new Texture("img/looseScreen.png");
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
		spriteBatch.dispose();
		splashTexture.dispose();
		stage.dispose();
//		myGame.dispose();
	}

}
