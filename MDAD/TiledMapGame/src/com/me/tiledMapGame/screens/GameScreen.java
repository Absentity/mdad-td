package com.me.tiledMapGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.me.tiledMapGame.Input;
import com.me.tiledMapGame.Level;
import com.me.tiledMapGame.entities.Projectile;
import com.me.tiledMapGame.entities.Tower;

public class GameScreen implements Screen {
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	public static OrthographicCamera camera;
	private TiledMapTileLayer layer;
	protected Level level;
	
	private Input i;
	
	
	Tower t; // FOR TESTING
	Projectile p; // FOR TESTING
	ShapeRenderer sr = new ShapeRenderer(); // FOR TESTING
	
	// UI stuff here
	
//	private Player p; 
//	boolean keyPressed = false;
//	boolean kBuilt = false;
//	
//	Enemy[] enemies = new Enemy[1];
//	int a;
	
	// TODO: move these and associated lines when there is a more appropriate place
//	public static boolean touched;
//	public static float alpha;
	
	// projectile
//	Sprite fireball;
	
	/**
	 * 
	 * @param level
	 */
	public GameScreen(Level level) {
		this.level = level;
	}
	
	/**
	 * Render level.render() and all sprites. Use one SpriteBatch
	 */
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
		
		renderer.getSpriteBatch().begin(); // FOR TESTING
		t.update(Gdx.graphics.getDeltaTime()); // FOR TESTING
		renderer.getSpriteBatch().draw(t.getCurrentFrame(), i.getX()-16, i.getY()-16); // FOR TESTING
		p.draw(renderer.getSpriteBatch()); // FOR TESTING
//		spriteArtist.begin();
//		p.draw(spriteArtist);
//		for(a=0 ; a<enemies.length ; a++){
//			if(enemies[a].getHealth() > 0)
//					enemies[a].draw(spriteArtist);
//		}
//		if(touched){
//			if(down){
//				spriteArtist.setColor(spriteArtist.getColor().r, spriteArtist.getColor().g, spriteArtist.getColor().b, alpha);
//				fireball.setPosition(Player.touchX+10, Player.touchY+10);
//			}
//			else if(up){
//				spriteArtist.setColor(spriteArtist.getColor().r, spriteArtist.getColor().g, spriteArtist.getColor().b, alpha);
//			}
//			spriteArtist.draw(currentFrame, Player.touchX, Player.touchY, 32, 32);
//			fireball.draw(spriteArtist);
//		}
//		
//		spriteArtist.end();
		renderer.getSpriteBatch().end();
		
		sr.begin(ShapeType.Line); // FOR TESTING
		sr.setColor(Color.BLACK); // FOR TESTING
		sr.circle(i.getX(), i.getY(), 70); // FOR TESTING
		sr.end(); // FOR TESTING
		
	}

	@Override
	public void resize(int width, int height) {

		camera.viewportWidth = width;
		camera.viewportHeight = height;

	}

	@Override
	public void show() {
		
		// TODO: Load level
		level = new Level("map_32x32");
		renderer = new OrthogonalTiledMapRenderer(level.getMap());
		
		camera = new OrthographicCamera();
		
		layer = (TiledMapTileLayer) level.getMap().getLayers().get(0);
		
		camera.position.x = layer.getWidth()*(layer.getTileWidth()/2);
		camera.position.y = layer.getHeight()*(layer.getTileHeight()/2);
		
		i = new Input();
		Gdx.input.setInputProcessor(i);
		
		t = new Tower(new Sprite(new Texture("img/CresTowTest.png"))); // FOR TESTING
		p = new Projectile(new Sprite(new Texture("img/fireball.png")), 200, 200); // FOR TESTING
		p.setPosition(120, 32); // FOR TESTING

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
		renderer.getSpriteBatch().dispose();
//		renderer.dispose(); Leave out until there is another screen to switch to
//		p.getTexture().dispose();
//		for(a=0 ; a<enemies.length ; a++)
//			enemies[a].getTexture().dispose();
//		spriteArtist.dispose();
//		cresSheet.dispose();
		
	}

}
