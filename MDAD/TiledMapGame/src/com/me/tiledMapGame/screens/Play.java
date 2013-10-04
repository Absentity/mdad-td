package com.me.tiledMapGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.me.tiledMapGame.entities.Player;

public class Play implements Screen {
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	
	private TiledMapTileLayer layer;
	private TiledMapTileLayer layer1;
	private Cell c;
	
	private Player p; 
	boolean keyPressed = false;
	boolean kBuilt = false;
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.setView(camera);
		renderer.render();
		
		if(Gdx.input.isKeyPressed(Keys.X)){
			keyPressed = true;
			System.out.println("Ready to build.");
		}
		
		if(keyPressed && Gdx.input.isButtonPressed(Buttons.LEFT) ){			
			System.out.println("Placing...");
			c = layer1.getCell(39, 39);
			layer.setCell(Gdx.input.getX()/16, 41-(Gdx.input.getY()/16), c);
			layer.setCell((Gdx.input.getX()/16)+1, 41-(Gdx.input.getY()/16), c);
			layer.setCell(Gdx.input.getX()/16, 41-((Gdx.input.getY()/16)+1), c);
			layer.setCell((Gdx.input.getX()/16)+1, 41-((Gdx.input.getY()/16)+1), c);
			kBuilt = true;
			System.out.println("The kingdom is complete!");
		}	
		
		renderer.getSpriteBatch().begin();
		p.draw(renderer.getSpriteBatch());
		renderer.getSpriteBatch().end();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
		

	}

	@Override
	public void show() {
		
		map = new TmxMapLoader().load("maps/map2.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		camera = new OrthographicCamera();
		
		layer = (TiledMapTileLayer) map.getLayers().get(0);
		layer1 = (TiledMapTileLayer) map.getLayers().get(1);
		camera.position.x = layer.getWidth()*layer.getTileWidth()/2;
		camera.position.y = layer.getHeight()*(layer.getTileHeight()/2);
		
		// remember to invert y: last cell - cell you want(starting at the top)
		
		p = new Player(new Sprite(new Texture("img/blmm.png")));
		
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
	}

}
