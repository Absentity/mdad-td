package com.me.tiledMapGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.tiledMapGame.TiledMapGame;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.entities.EnemyType;

public class CreditsScreen implements Screen {

	private SpriteBatch sb;
	private Texture t = new Texture("img/Credits.png");
	private MainMenuScreen M;
	private Enemy pic = new Enemy(TiledMapGame.enemyTypeLibrary.get("Wyvern"), true);
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.begin();
		sb.draw(t, 0, 0);
		pic.update(Gdx.graphics.getDeltaTime());
		if(!pic.getCurrentFrame().isFlipX()) {
			pic.getCurrentFrame().flip(true, false);
		}
		sb.draw(pic.getCurrentFrame(), 430, 450-32);
		sb.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
		sb = new SpriteBatch();
		
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
		sb.dispose();
	}
	
	
	
}
