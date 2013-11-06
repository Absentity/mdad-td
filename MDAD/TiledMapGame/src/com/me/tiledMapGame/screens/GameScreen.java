package com.me.tiledMapGame.screens;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.jws.soap.SOAPBinding.Style;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.me.tiledMapGame.Input;
import com.me.tiledMapGame.Level;
import com.me.tiledMapGame.TiledMapGame;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.entities.EnemyType;
import com.me.tiledMapGame.entities.Projectile;
import com.me.tiledMapGame.entities.ProjectileType;
import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.entities.TowerType;

public class GameScreen implements Screen {
	
	private OrthogonalTiledMapRenderer renderer;
	public static OrthographicCamera camera;
	private TiledMapTileLayer layer;
	protected Level level;
	
	Vector2 cVel = new Vector2();
	
	private int towerChoice = 1;
	
	public static boolean selectionConfirmed = false;
	public static boolean thinking = false;
	public static ArrayList<Tower> towers = new ArrayList<>();
	public static boolean openTowerMenu = false;
	ShapeRenderer sr = new ShapeRenderer(); // FOR TESTING
	
	// UI stuff here
	Button twrsButton;
	Button cresButton;
	Button bombButton;
	Button ampButton;
	Button fireballButton;
	TextButton confirmSelection;
	TextButton closeMenu;
	Skin skin;
	public static Stage stage;
	Table buttonTable;
//	ScrollPane scrollPane;
	MenuNinePatch towerNinePatch;
	MenuNinePatch towerInfoNinePatch;
	Table towerTable;
	Table infoTable;
	TextButton towerNameLabel; // Button without a listener
	TextButton towerDamageLabel; // Button without a listener
	TextButton towerRangeLabel; // Button without a listener
	TextButton peaceTimer; // Button without a listener
	float current = 20;
	
//	Enemy e;
//	Sprite k;
	
	private Input i;

	
	
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
		
		if(tx + cVel.x*Gdx.graphics.getDeltaTime()-(TiledMapGame.screenWidth/2) > 0 && tx + (cVel.x*Gdx.graphics.getDeltaTime()+(TiledMapGame.screenWidth/2)) < TiledMapGame.screenWidth){ // Bound x 
			camera.position.x += cVel.x*Gdx.graphics.getDeltaTime();
		}
		if(ty + cVel.y*Gdx.graphics.getDeltaTime()-(TiledMapGame.screenHeight/2) > 0 && ty + cVel.y*Gdx.graphics.getDeltaTime()+(TiledMapGame.screenHeight/2) < TiledMapGame.screenHeight){ // Bound y
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
		
		// Draw enemy and kingdom
//		e.draw(renderer.getSpriteBatch());
//		k.draw(renderer.getSpriteBatch());
		
		// Draw towers
		for(Tower t: towers){ // FOR TESTING
			t.update(Gdx.graphics.getDeltaTime()); // FOR TESTING
			renderer.getSpriteBatch().setColor(1,1,1,t.getAlpha()); // FOR TESTING
			if(t.getMoved()){
				renderer.getSpriteBatch().draw(t.getCurrentFrame(), t.getX(), t.getY()); // FOR TESTING
			}
		} // FOR TESTING
		
		renderer.getSpriteBatch().setColor(1, 1, 1, 1); // FOR TESTING
		renderer.getSpriteBatch().end(); // FOR TESTING
		
		// TESTING: Determine whether or not to draw menu and draw/don't
		if(openTowerMenu){
			towerTable.setVisible(true);
			closeMenu.setVisible(true);
			
			stage.getSpriteBatch().begin();
			if(thinking) {
				towerNameLabel.setVisible(true);
				towerDamageLabel.setVisible(true);
				towerRangeLabel.setVisible(true);
				stage.getSpriteBatch().setColor(stage.getSpriteBatch().getColor().r, stage.getSpriteBatch().getColor().g, stage.getSpriteBatch().getColor().b, .5f);
				towerInfoNinePatch.draw(stage.getSpriteBatch(), TiledMapGame.screenWidth-110, TiledMapGame.screenHeight-80, 110, 80);
			}
			if(selectionConfirmed){
				selectionConfirmed = false;
				Gdx.input.setInputProcessor(i);
				confirmSelection.setVisible(false);
				towerNameLabel.setVisible(false);
				towerDamageLabel.setVisible(false);
				towerRangeLabel.setVisible(false);
			}
			stage.getSpriteBatch().setColor(stage.getSpriteBatch().getColor().r, stage.getSpriteBatch().getColor().g, stage.getSpriteBatch().getColor().b, .5f);
			towerNinePatch.draw(stage.getSpriteBatch(), 0, 0, 120, Gdx.graphics.getHeight()); 
			stage.getSpriteBatch().end();
			
		} else {
			confirmSelection.setVisible(false);
			towerNameLabel.setVisible(false);
			towerDamageLabel.setVisible(false);
			towerRangeLabel.setVisible(false);
			towerTable.setVisible(false);
			closeMenu.setVisible(false);
		}
		
		stage.draw();
		
		// Timer Stuff
		if(current > 0) {
			peaceTimer.setVisible(true);
			current -= (Gdx.graphics.getDeltaTime());
			current = (float)(Math.floor(current * 1e2) / 1e2);
			peaceTimer.setText( Float.toString(current));
		} else {
			peaceTimer.setVisible(false);
		}
		
		
		/* ShapeRenderers have their own SpriteBatch and it does not seem
		 * like they can draw using any other so the range circle moves
		 * independently of the tower when panning. Need to calculate correct
		 * position to draw range circles. Also, zoom messes with the animations!
		 */
		for(Tower t: towers){ // FOR TESTING
			if(t.getMoved()) {
				if(!t.isPlaced()){ // FOR TESTING
				sr.begin(ShapeType.Line); // FOR TESTING
				sr.setColor(Color.BLACK); // FOR TESTING
				sr.circle(t.getX()+16, t.getY()+16, 70); // FOR TESTING
				sr.end(); // FOR TESTING
				} // FOR TESTING
			}
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
		level = new Level("MDADMap1v1");
		renderer = new OrthogonalTiledMapRenderer(level.getMap());
		
		camera = new OrthographicCamera();
		
		layer = (TiledMapTileLayer) level.getMap().getLayers().get(0);
		
		camera.position.x = layer.getWidth()*(layer.getTileWidth()/2);
		camera.position.y = layer.getHeight()*(layer.getTileHeight()/2);
//		camera.zoom = .75f;
		
		i = new Input(level);

		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		setupSkin();
		createTwrsButton();
	    createInfoBox();
	    setupTowerOptions();
	    setupButtonTable();
		setupTowerTable();
		
		stage.addActor(buttonTable);		
		stage.addActor(closeMenu);
		stage.addActor(confirmSelection);
		stage.addActor(towerTable);
		stage.addActor(infoTable);
		
//		e = new Enemy(new EnemyType(new Texture("img/Skeleton.png"), 100, 5));
//		e.setPosition(100, 100);
//		k = new Sprite(new Texture("img/tempKingdom.png"));
//		k.setPosition(320, 320);
		
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
		renderer.getSpriteBatch().dispose();
//		renderer.dispose(); Leave out until there is another screen to switch to
		for(Tower t: towers){
			t.getTexture().dispose();
			t.dispose();
		}
		sr.dispose();
		stage.getSpriteBatch().dispose();
		//stage.dispose();
		skin.dispose();
		
	}
	
	
	/**
	 *  Opens tower menu if it is closed and closes tower menu if it is open.
	 */
	private void changeTowerMenuState() {
		if(openTowerMenu) {
			openTowerMenu = false;
		} else {
			openTowerMenu = true;
		}
	}
	
	private void setupSkin() {
		
		skin = new Skin();
		skin.add("up", new Sprite(new Texture("img/buttonUp.png")));
		skin.add("down", new Sprite(new Texture("img/buttonDown.png")));
		skin.add("cresUp", new Sprite(new Texture("img/CresTow1.png")));
		skin.add("cresDown", new Sprite(new Texture("img/CresTow8.png")));
		skin.add("bombUp", new Sprite(new Texture("img/bombTowerUp.png")));
		skin.add("bombDown", new Sprite(new Texture("img/bombTowerDown.png")));
		skin.add("ampUp", new Sprite(new Texture("img/amplifyTowerUp.png")));
		skin.add("ampDown", new Sprite(new Texture("img/amplifyTowerDown.png")));
		skin.add("fireballUp", new Sprite(new Texture("img/fireballTowerUp.png")));
		skin.add("fireballDown", new Sprite(new Texture("img/fireballTowerDown.png")));
	}
	
	private void createTwrsButton() {
		
		TextButtonStyle twrsButtonStyle = new TextButtonStyle();
		twrsButtonStyle.up = skin.getDrawable("up");
		twrsButtonStyle.down = skin.getDrawable("down");
		
	    twrsButton = new Button(twrsButtonStyle);
	    twrsButton.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        			if(button == Buttons.RIGHT){
	        				twrsButton.setVisible(false);
	        				Gdx.input.setInputProcessor(i);
	        				return false;
	        			}
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        			changeTowerMenuState();
	        }
	    });
	    
	}
	
	// Peace Timer stuff also here
	private void createInfoBox() {
		
		towerInfoNinePatch = new MenuNinePatch();
		
		infoTable = new Table();
		infoTable.setBounds(TiledMapGame.screenWidth-110, TiledMapGame.screenHeight-80, 110, 80);
		infoTable.pad(5);
		infoTable.top();
		
		TextButton.TextButtonStyle infoStyle = new TextButton.TextButtonStyle();
		infoStyle.font = new BitmapFont();
		infoStyle.fontColor = new Color(255,0,0,.7f);
		
		towerNameLabel = new TextButton("Tower Name", infoStyle);
		towerDamageLabel = new TextButton("Tower Damage", infoStyle);
		towerRangeLabel = new TextButton("Tower Range", infoStyle);
		towerNameLabel.setVisible(false);
		towerDamageLabel.setVisible(false);
		towerRangeLabel.setVisible(false);
		
		infoTable.add(towerNameLabel);
		infoTable.row();
		infoTable.add(towerDamageLabel);
		infoTable.row();
		infoTable.add(towerRangeLabel);
		
		peaceTimer = new TextButton("20",infoStyle);
		peaceTimer.setWidth(20f);
		peaceTimer.setHeight(12f);
		peaceTimer.setPosition((TiledMapGame.screenWidth/2)-10, TiledMapGame.screenHeight-20-5); // offset by 5 for visual appearance
		stage.addActor(peaceTimer);
		
	}
	
	private void setupTowerOptions(){
		
		towerNinePatch = new MenuNinePatch();

		TextButtonStyle cresButtonStyle = new TextButtonStyle();
	    cresButtonStyle.up = skin.getDrawable("cresUp");
	    cresButtonStyle.down = skin.getDrawable("cresDown");
		
		cresButton = new Button(cresButtonStyle);
	    cresButton.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        		System.out.println("Cresent Tower selected");
	        		thinking = true;
	        		confirmSelection.setVisible(true);
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        	towerChoice = 1;
	        	towerNameLabel.setText("Cresent Tower");
	        	towerDamageLabel.setText("Damage: 50");
	        	towerRangeLabel.setText("Range: 70");
	        }
	    });
	    
	    TextButtonStyle bombButtonStyle = new TextButtonStyle();
	    bombButtonStyle.up = skin.getDrawable("bombUp");
	    bombButtonStyle.down = skin.getDrawable("bombDown");
	    
	    bombButton = new Button(bombButtonStyle);
	    bombButton.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        		System.out.println("Bomb Tower selected");
	        		thinking = true;
	        		confirmSelection.setVisible(true);
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        	towerChoice = 2;
	        	towerNameLabel.setText("Bomb Tower");
	        	towerDamageLabel.setText("Damage: 80");
	        	towerRangeLabel.setText("Range: 50");
	        }
	    });
		
	    TextButtonStyle ampButtonStyle = new TextButtonStyle();
	    ampButtonStyle.up = skin.getDrawable("ampUp");
	    ampButtonStyle.down = skin.getDrawable("ampDown");
	    
	    ampButton = new Button(ampButtonStyle);
	    ampButton.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        		System.out.println("Amplifier Tower selected");
	        		thinking = true;
	        		confirmSelection.setVisible(true);
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        	towerChoice = 3;
	        	towerNameLabel.setText("Amplifier Tower");
	        	towerDamageLabel.setText("Damage: 0");
	        	towerRangeLabel.setText("Range: 100");
	        }
	    });
	    
	    TextButtonStyle fireballButtonStyle = new TextButtonStyle();
	    fireballButtonStyle.up = skin.getDrawable("fireballUp");
	    fireballButtonStyle.down = skin.getDrawable("fireballDown");
	    
	    fireballButton = new Button(fireballButtonStyle);
	    fireballButton.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        		System.out.println("Fireball Tower selected");
	        		thinking = true;
	        		confirmSelection.setVisible(true);
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        	towerChoice = 4;
	        	towerNameLabel.setText("Fireball Tower");
	        	towerDamageLabel.setText("Damage: 60");
	        	towerRangeLabel.setText("Range: 90");
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
	    
	    confirmSelection = new TextButton("Confirm", style);
	    confirmSelection.addListener(new ClickListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        		
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        	selectionConfirmed = true;
	        	
	        	switch(towerChoice){
	        		case 1:
	        			towers.add(new Tower(new TowerType(new Texture("img/cresentTower.png"), 100, 70f, 1)));
	        			break;
	        		case 2:
	        			towers.add(new Tower(new TowerType(new Texture("img/bombTower.png"), 100, 70f, 2)));
	        			break;
	        		case 3:
	        			towers.add(new Tower(new TowerType(new Texture("img/amplifyTower.png"), 100, 70f, 3)));
	        			break;
	        		case 4:
	        			towers.add(new Tower(new TowerType(new Texture("img/fireballTower.png"), 100, 70f, 4)));
	        		default:
	        			break;
	        	}
	        	
	        }
	    });
	    confirmSelection.setPosition(10, 5);
	    confirmSelection.setVisible(false);
	}
	
	private void setupTowerTable(){
		
		towerTable = new Table();
		towerTable.setBounds(0, 0, 100, TiledMapGame.screenHeight-10); // offset y by 10 for appearance
		towerTable.pad(0, 20, 0, 0);
		towerTable.top();
		
		towerTable.add(cresButton).spaceRight(24).spaceBottom(5);
		towerTable.add(bombButton).spaceBottom(5);
		towerTable.row();
		
		towerTable.add(ampButton).spaceRight(24);
		towerTable.add(fireballButton).spaceBottom(5);
		towerTable.row();
		
		// Follow this pattern to add two towers per line.
		// towerTable.add(NEW TOWER).spaceRight(24).spaceBottom(5);
		// towerTable.add(NEXT NEW TOWER).spaceBottom(5);
		// towerTable.row();
		
		towerTable.setVisible(false);
	}
	
	private void setupButtonTable() {
		
		buttonTable = new Table();
	    
	    buttonTable.add(twrsButton);
	    buttonTable.setPosition(TiledMapGame.screenWidth-25, 25);
	}
	
	public int getTowerChoice(){
		return towerChoice;
	}
}
