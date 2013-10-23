package com.me.tiledMapGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	
	// TODO: move these and associated lines when there is a more appropriate place
	public static Music music;
	public static boolean touched;
	public static float alpha;
	
	// Sprite animation
	SpriteBatch spriteAnimator;
	Animation cresAnima;
    Texture cresSheet;
    TextureRegion[] cresFrames;
    public static TextureRegion currentFrame;
	float stateTime;
	public static boolean down, up;
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
		
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = cresAnima.getKeyFrame(stateTime, true);
		
		renderer.getSpriteBatch().begin();
		spriteAnimator.begin();
		p.draw(spriteAnimator);
		for(a=0 ; a<enemies.length ; a++)
			enemies[0].draw(spriteAnimator);
		if(touched){
			
			if(down){
				spriteAnimator.setColor(spriteAnimator.getColor().r, spriteAnimator.getColor().g, spriteAnimator.getColor().b, alpha);
			}
			else if(up){
				spriteAnimator.setColor(spriteAnimator.getColor().r, spriteAnimator.getColor().g, spriteAnimator.getColor().b, alpha);
			}
			spriteAnimator.draw(currentFrame, Player.touchX, Player.touchY, 32, 32);
			
		}spriteAnimator.end();
		renderer.getSpriteBatch().end();
		
	}

	@Override
	public void resize(int width, int height) {

		camera.viewportWidth = width;
		camera.viewportHeight = height;

	}

	@Override
	public void show() {
		
		map = new TmxMapLoader().load("maps/map_32x32.tmx");

		renderer = new OrthogonalTiledMapRenderer(map);

		camera = new OrthographicCamera();
		
		layer = (TiledMapTileLayer) map.getLayers().get(0);
		camera.position.x = layer.getWidth()*(layer.getTileWidth()/2);
		camera.position.y = layer.getHeight()*(layer.getTileHeight()/2);
		
		p = new Player(new Sprite(new Texture("img/small_blmm.png")), layer);

		for(a=0 ; a<enemies.length ; a++){
			enemies[a] = new Enemy(new Sprite(new Texture("img/Wyvern2.png")), layer);
			enemies[a].setPosition(0*layer.getTileWidth(), (a+1)*layer.getTileHeight());
		}
		
		Gdx.input.setInputProcessor(p);
		
		// music code - change directory to sounds/song name.mp3 to change song
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Facade.mp3"));
		music.setLooping(true);
		music.setVolume(.5f);
		music.play();
		
		// animated sprite
		cresSheet = new Texture("img/CresTowTest.png");
		TextureRegion[][] tempTexReg = TextureRegion.split(cresSheet, cresSheet.getWidth()/4, cresSheet.getHeight()/3);
		cresFrames = new TextureRegion[4 * 3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                        cresFrames[index++] = tempTexReg[i][j];
                }
        }
        
        cresAnima = new Animation(.05f, cresFrames);
        stateTime = 0f;
        
        spriteAnimator = new SpriteBatch();
        
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
		renderer.dispose();
		p.getTexture().dispose();
		for(a=0 ; a<enemies.length ; a++)
			enemies[a].getTexture().dispose();
		spriteAnimator.dispose();
		cresSheet.dispose();
		
	}

}
