package com.me.tiledMapGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.entities.Player;

public class Play implements Screen {
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	public static OrthographicCamera camera;
	
	private TiledMapTileLayer layer;
	
	private Player p; 
	boolean keyPressed = false;
	boolean kBuilt = false;
	
	Enemy[] enemies = new Enemy[1];
	int a;
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// uncomment to make camera focus on player sprite
//		camera.position.set( p.getX()+(p.getWidth()/2) , p.getY()+(p.getHeight()/2) , 0 );
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
		
		renderer.getSpriteBatch().begin();
		p.draw(renderer.getSpriteBatch());
		for(a=0 ; a<enemies.length ; a++)
			enemies[a].draw(renderer.getSpriteBatch());
		renderer.getSpriteBatch().end();
		
	}

	@Override
	public void resize(int width, int height) {
		// uncomment to make camera focus on player sprite
		camera.viewportWidth = width; // /1.5f;
		camera.viewportHeight = height; // /1.5f;

	}

	@Override
	public void show() {
		
		map = new TmxMapLoader().load("maps/map2.tmx");

		renderer = new OrthogonalTiledMapRenderer(map);

		camera = new OrthographicCamera();

		layer = (TiledMapTileLayer) map.getLayers().get(0);
		camera.position.x = layer.getWidth()*(layer.getTileWidth()/2);
		camera.position.y = layer.getHeight()*(layer.getTileHeight()/2);
		
		// remember to invert y: last cell - cell you want(starting at the top)
		
		p = new Player(new Sprite(new Texture("img/small_blmm.png")), layer);
		p.setPosition(2*layer.getTileWidth(), 2*layer.getTileHeight());

		for(a=0 ; a<enemies.length ; a++){
			enemies[a] = new Enemy(new Sprite(new Texture("img/small_blmme.png")), layer);
			enemies[a].setPosition(0*layer.getTileWidth(), (a+1)*layer.getTileHeight());
		}
		
		Gdx.input.setInputProcessor(p);
		
		
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		p.getTexture().dispose();
		for(a=0 ; a<enemies.length ; a++)
			enemies[a].getTexture().dispose();
	}

}
