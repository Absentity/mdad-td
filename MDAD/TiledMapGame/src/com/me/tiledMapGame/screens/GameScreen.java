package com.me.tiledMapGame.screens;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.jws.soap.SOAPBinding.Style;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
<<<<<<< HEAD
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
=======
>>>>>>> 4b8a1b63a9b6cebc576f2533b4b8d2a9c902d0d8
import com.me.tiledMapGame.Input;
import com.me.tiledMapGame.Level;
import com.me.tiledMapGame.TiledMapGame;
import com.me.tiledMapGame.entities.Projectile;
import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.entities.TowerType;

public class GameScreen implements Screen {
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	public static OrthographicCamera camera;
	private TiledMapTileLayer layer;
	protected Level level;
	
	Vector2 cVel = new Vector2();
	
	private Input i;
	
	public static ArrayList<Tower> towers = new ArrayList<>();
	ShapeRenderer sr = new ShapeRenderer(); // FOR TESTING
	
	// UI stuff here
	Button twrsButton;
	Button cresButton;
	Skin skin;
	public static Stage stage;
	Table table;
	ScrollPane scrollPane;
	MenuNinePatch nine;
	boolean openTowerMenu = false;
	Table towerTable;
	TextButton closeMenu;
	
	
	float tx, ty; // FOR TESTING CAMERA PANNING BOUNDS
	
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
		
		// Camera panning
		if(Gdx.input.isKeyPressed(Keys.A)){
			cVel.x = -2*16*4;
		}
		if(Gdx.input.isKeyPressed(Keys.W)){
			cVel.y = 2*16*4;
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			cVel.y = -2*16*4;
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			cVel.x = 2*16*4;
		}
		
		tx = camera.position.x;
		ty = camera.position.y;
		
		if(tx + cVel.x*Gdx.graphics.getDeltaTime()-160 > 0 && tx + cVel.x*Gdx.graphics.getDeltaTime()+160 < TiledMapGame.screenWidth){ // Bound x 
			camera.position.x += cVel.x*Gdx.graphics.getDeltaTime();
		}
		if(ty + cVel.y*Gdx.graphics.getDeltaTime()-160 > 0 && ty + cVel.y*Gdx.graphics.getDeltaTime()+160 < TiledMapGame.screenWidth){ // Bound y
			camera.position.y += cVel.y*Gdx.graphics.getDeltaTime();
		}
//		camera.position.x += cVel.x*Gdx.graphics.getDeltaTime();
//		camera.position.y += cVel.y*Gdx.graphics.getDeltaTime();
		
		cVel.x = 0;
		cVel.y = 0;
		
		renderer.setView(camera);
		renderer.render();
		
		// Draw
		renderer.getSpriteBatch().begin(); // FOR TESTING
		
		// Draw towers
		for(Tower t: towers){ // FOR TESTING
			t.update(Gdx.graphics.getDeltaTime()); // FOR TESTING 
			renderer.getSpriteBatch().setColor(1,1,1,t.getAlpha()); // FOR TESTING
			renderer.getSpriteBatch().draw(t.getCurrentFrame(), t.getX(), t.getY()); // FOR TESTING
		} // FOR TESTING
		
		renderer.getSpriteBatch().setColor(1, 1, 1, 1); // FOR TESTING
		renderer.getSpriteBatch().end(); // FOR TESTING
		
		// TESTING: Determine whether or not to draw menu and draw/don't
		if(openTowerMenu){
			towerTable.setVisible(true);
			closeMenu.setVisible(true);
			stage.getSpriteBatch().begin();
			stage.getSpriteBatch().setColor(stage.getSpriteBatch().getColor().r, stage.getSpriteBatch().getColor().g, stage.getSpriteBatch().getColor().b, .5f); // FOR TESTING
			nine.draw(stage.getSpriteBatch(), 0, 0, 120, Gdx.graphics.getHeight()); // FOR TESTING
			stage.getSpriteBatch().end();
		} else {
			towerTable.setVisible(false);
			closeMenu.setVisible(false);
		}
		
		stage.draw();
		
		/* ShapeRenderers have their own SpriteBatch and it does not seem
		 * like they can draw using any other so the range circle moves
		 * independently of the tower when panning. Need to calculate correct
		 * position to draw range circles. Also, zoom messes with the animations!
		 */
		for(Tower t: towers){ // FOR TESTING
			if(!t.isPlaced()){ // FOR TESTING
			sr.begin(ShapeType.Line); // FOR TESTING
			sr.setColor(Color.BLACK); // FOR TESTING
			sr.circle(t.getX()+16, t.getY()+16, 70); // FOR TESTING
			sr.end(); // FOR TESTING
			} // FOR TESTING
		} // FOR TESTING
				
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
//		Gdx.input.setInputProcessor(i);

		skin = new Skin();
		skin.add("up", new Sprite(new Texture("img/buttonUp.png")));
		skin.add("down", new Sprite(new Texture("img/buttonDown.png")));
		skin.add("cresUp", new Sprite(new Texture("img/CresTow1.png")));
		skin.add("cresDown", new Sprite(new Texture("img/CresTow8.png")));
		
		stage = new Stage();
//		bStyle = new ButtonStyle();
		nine = MenuNinePatch.getInstance();
		
<<<<<<< HEAD
		Gdx.input.setInputProcessor(stage);
		
		
		TextButtonStyle twrsButtonStyle = new TextButtonStyle();
		twrsButtonStyle.up = skin.getDrawable("up");
		twrsButtonStyle.down = skin.getDrawable("down");
		
	    twrsButton = new Button(twrsButtonStyle);
	    twrsButton.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        		changeTowerMenuState();
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        }
	    });
	    
	    TextButtonStyle cresButtonStyle = new TextButtonStyle();
	    cresButtonStyle.up = skin.getDrawable("cresUp");
	    cresButtonStyle.down = skin.getDrawable("cresDown");
	    
	    cresButton = new Button(cresButtonStyle);
	    cresButton.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        		System.out.println("Cresent Tower selected");
	        		Gdx.input.setInputProcessor(i);
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        }
	    });
		
	    TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
	    style.font = new BitmapFont();
	    style.fontColor = new Color(0, 0, 200, .8f);
	    style.pressedOffsetX = 2;
	    style.pressedOffsetY = -2;
	    closeMenu = new TextButton("Close", style);
	    closeMenu.addListener(new ClickListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        		
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        			changeTowerMenuState();
	        }
	    });
	    closeMenu.setPosition(75, 5);
	    closeMenu.setVisible(false);
	    
	    table = new Table();
	    table.add(twrsButton);
	    table.setPosition(TiledMapGame.screenWidth-25, 25);
	    
		stage.addActor(table);
		
		towerTable = new Table();
		towerTable.add(cresButton);
		towerTable.setPosition(120/3, TiledMapGame.screenWidth-25); // 120/3 = ninePatch width divided into 3 sections. second column would start at x=80.
		towerTable.setVisible(false);
=======
		t = new Tower(new TowerType(new Texture("img/CresTowTest.png"), 100, 10)); // FOR TESTING
		p = new Projectile(new Texture("img/fireball.png"), new Vector2(200,200)); // FOR TESTING
		p.setPosition(120, 32); // FOR TESTING
>>>>>>> 4b8a1b63a9b6cebc576f2533b4b8d2a9c902d0d8

		stage.addActor(closeMenu);
		stage.addActor(towerTable);
		
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
		for(Tower t: towers){
			t.getTexture().dispose();
			t.dispose();
		}
		sr.dispose();
		stage.getSpriteBatch().dispose();
		stage.dispose();
		skin.dispose();
		
	}
	
	
	/**
	 *  Opens tower menu if it is closed and closes tower menu if it is open.
	 */
	private void changeTowerMenuState(){
		if(openTowerMenu) {
			openTowerMenu = false;
		} else {
			openTowerMenu = true;
		}
	}

}
