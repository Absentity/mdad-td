package com.me.tiledMapGame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.tiledMapGame.TiledMapGame;

public class Splash implements Screen {

	public ClickListener listener = new ClickListener();
	
	private SpriteBatch spriteBatch;
    private Texture splashTexture;
    private Game myGame;
    
    public Splash(Game g) {
            myGame = g;
    }

    
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(splashTexture, 0, 0);
        spriteBatch.end();
        
        if(Gdx.input.isTouched()){
        	myGame.setScreen(TiledMapGame.M);
        }
        	
        
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		splashTexture = new Texture("img/splash.png");
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
//		myGame.dispose();
	}

}
