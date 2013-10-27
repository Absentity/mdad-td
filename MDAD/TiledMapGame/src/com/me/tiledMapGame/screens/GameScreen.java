package com.me.tiledMapGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.me.tiledMapGame.Level;

public class GameScreen implements Screen {
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	public static OrthographicCamera camera;
	
	private TiledMapTileLayer layer;
	
	protected Level level;
	
//	private Player p; 
//	boolean keyPressed = false;
//	boolean kBuilt = false;
//	
//	Enemy[] enemies = new Enemy[1];
//	int a;
	
	// TODO: move these and associated lines when there is a more appropriate place
//	public static boolean touched;
//	public static float alpha;
	
	// Sprite animation
//	SpriteBatch spriteArtist;
//	Animation cresAnima;
//    Texture cresSheet;
//    TextureRegion[] cresFrames;
//    public static TextureRegion currentFrame;
//	float stateTime;
//	public static boolean down, up;
	
	// projectile
//	Sprite fireball;
	
	/**
	 * 
	 * @param level
	 */
	public GameScreen(Level level) {
		this.level = level;
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
		
//		stateTime += Gdx.graphics.getDeltaTime();
//		currentFrame = cresAnima.getKeyFrame(stateTime, true);
//		
//		renderer.getSpriteBatch().begin();
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
//		//////////////////////
//		if(touched && up){
//		fireball.rotate(15);
//		if(enemies[0].getX() > fireball.getX())
//			fireball.setPosition(fireball.getX()+1, fireball.getY());
//		else if(enemies[0].getX() < fireball.getX())
//			fireball.setPosition(fireball.getX()-1, fireball.getY());
//		if(enemies[0].getY() > fireball.getY())
//			fireball.setPosition(fireball.getX(), fireball.getY()+1);
//		else if(enemies[0].getY() < fireball.getY())
//			fireball.setPosition(fireball.getX(), fireball.getY()-1);
////		fireball.draw(spriteArtist);
//		
//		if( (fireball.getX()-enemies[0].getX()) <= 4 && (fireball.getX()-enemies[0].getX()) >= 0 && (fireball.getY()-enemies[0].getY()) <= 4 && (fireball.getY()-enemies[0].getY()) >= 0 )
//			enemies[0].setHealth(enemies[0].getHealth()/2);
//		}
//		//////////////////////
//		
//		spriteArtist.end();
//		renderer.getSpriteBatch().end();
		
	}

	@Override
	public void resize(int width, int height) {

		camera.viewportWidth = width;
		camera.viewportHeight = height;

	}

	@Override
	public void show() {
		
		// TODO: Load level
		map = new TmxMapLoader().load("maps/map_32x32.tmx");

		renderer = new OrthogonalTiledMapRenderer(map);

		camera = new OrthographicCamera();
		
		layer = (TiledMapTileLayer) map.getLayers().get(0);
		camera.position.x = layer.getWidth()*(layer.getTileWidth()/2);
		camera.position.y = layer.getHeight()*(layer.getTileHeight()/2);
		
//		p = new Player(new Sprite(new Texture("img/small_blmm.png")), layer);
//
//		for(a=0 ; a<enemies.length ; a++){
//			enemies[a] = new Enemy(new Sprite(new Texture("img/Wyvern2.png")), layer);
//			enemies[a].setPosition(0*layer.getTileWidth(), (a+1)*layer.getTileHeight());
//		}
//		
//		Gdx.input.setInputProcessor(p);
//		
//		
//		// animated sprite
//		cresSheet = new Texture("img/CresTowTest.png");
//		TextureRegion[][] tempTexReg = TextureRegion.split(cresSheet, cresSheet.getWidth()/4, cresSheet.getHeight()/3);
//		cresFrames = new TextureRegion[4 * 3];
//        int index = 0;
//        for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 4; j++) {
//                        cresFrames[index++] = tempTexReg[i][j];
//                }
//        }
//        
//        cresAnima = new Animation(.05f, cresFrames);
//        stateTime = 0f;
//        
//        spriteArtist = new SpriteBatch();
//        
//        fireball = new Sprite(new Texture("img/fireball.png"));
//        fireball.setPosition(160, 160);
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
