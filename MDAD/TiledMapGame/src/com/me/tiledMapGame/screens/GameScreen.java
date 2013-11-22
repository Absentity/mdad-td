package com.me.tiledMapGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.tiledMapGame.Input;
import com.me.tiledMapGame.Level;
import com.me.tiledMapGame.LevelSaver;
import com.me.tiledMapGame.TiledMapGame;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.entities.Entity;
import com.me.tiledMapGame.entities.Projectile;
import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.entities.Unit;
import com.me.tiledMapGame.entities.UnitType;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.pathing.PathFinder;

public class GameScreen implements Screen, InputProcessor {
	
	protected Level level;
	
	public static OrthographicCamera camera;
	public static boolean selectionConfirmed = false;
	public static boolean thinking = false;
	public static boolean openTowerMenu = false;
	public static boolean openUnitMenu = false;
	
	private int towerChoice = 1;
	private int unitChoice = 1;
	
	public static InputMultiplexer im;
	private OrthogonalTiledMapRenderer renderer;
	private TiledMapTileLayer layer;
	
	boolean usingTower = false;
	boolean usingUnit = false;

	
	ShapeRenderer sr = new ShapeRenderer(); // FOR TESTING
	
	// UI stuff here
	Button twrsButton;
	Button unitsButton;
	Button cresButton;
	Button bombButton;
	Button ampButton;
	Button fireballButton;
	
	Button mageButton;
	
	TextButton confirmSelection;
	TextButton closeMenu;
	Skin skin;
	public static Stage stage;
	Table buttonTable;
//	ScrollPane scrollPane;
	MenuNinePatch towerNinePatch;
	MenuNinePatch infoNinePatch;
	MenuNinePatch unitNinePatch;
	MenuNinePatch unitInfoNinePatch;
	Table towerTable;
	Table infoTable;
	Table unitTable;
	Table unitInfoTable;
	TextButton nameLabel; // Button without a listener
	TextButton damageLabel; // Button without a listener
	TextButton rangeLabel; // Button without a listener
	TextButton peaceTimer; // Button without a listener

	Button displayButton;
	boolean showHealth = false;
	BitmapFont font;
	
	// Wave Information
	float current = 5; // Peace Timer
	float wave = 1; // Current Wave
	float tbw = 0; // Time Between Waves
	float tbs = 0; // Time Between Spawns
	float eCounter = 0; // Enemy Counter
	
	// For Towers
	boolean created;
	
	private Input i;
	
	float tx, ty; // FOR TESTING CAMERA PANNING BOUNDS
	
	/**
	 * 
	 * @param level
	 */
	public GameScreen(Level l) {
		this.level = l;
		if(level == null){
			this.level = new Level("MDADMap1v1");
			
			//Create Kingdom if making new level
			ObjectGrid.towerList().add(new Tower(TiledMapGame.towerTypeLibrary.get("Portal"))); //TODO Change to Kingdom
			ObjectGrid.towerList().get(0).setPosition(level.castleX*32, level.castleY*32);
		}
		
		renderer = new OrthogonalTiledMapRenderer(level.getMap());
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
		
		
		// Draw
		renderer.getSpriteBatch().begin(); // FOR TESTING
		
		
		if(showHealth) {
			for(Enemy e: ObjectGrid.enemyList()) {
				if(e.getX()-10 >= 0 && e.getY()+35 <= 512) { // bound upper left
					font.draw(renderer.getSpriteBatch(), e.showHealth(), e.getX()-10, e.getY()+30);
				} else if(e.getX()+10 <= 512 && e.getY()-35 >= 0) { // bound lower right
					font.draw(renderer.getSpriteBatch(), e.showHealth(), e.getX()+10, e.getY()-30);
				}
			}
			for (Tower t : ObjectGrid.towerList()) {
				if (t.getHealth() < 100) {
					font.draw(renderer.getSpriteBatch(), t.showHealth(), t.getX(), t.getY());
				}
			}
		}
		
	
		// Draw towers
		for(Tower t: ObjectGrid.towerList()){ // FOR TESTING
			t.update(Gdx.graphics.getDeltaTime()); // FOR TESTING
			renderer.getSpriteBatch().setColor(1,1,1,t.getAlpha()); // FOR TESTING
			/////// Huh???? how are towers still rendering?
			if(t.towerType == 0) {
				renderer.getSpriteBatch().draw(t.getCurrentFrame(), t.getX(), t.getY()); // FOR TESTING
			} else if(t.getMoved()){
				renderer.getSpriteBatch().draw(t.getCurrentFrame(), t.getX(), t.getY()); // FOR TESTING
			}
			
		} // FOR TESTING
		
		for(Unit u: ObjectGrid.unitList()){
			u.update(Gdx.graphics.getDeltaTime());
			renderer.getSpriteBatch().setColor(1,1,1,1);
			renderer.getSpriteBatch().draw(u.getCurrentFrame(), 398, 398);
		}
		
		renderer.getSpriteBatch().setColor(1, 1, 1, 1);
		
		for (Enemy e : ObjectGrid.enemyList()) {
			e.update(Gdx.graphics.getDeltaTime());
			if (e.getHealth() >= 0) {
				e.draw(renderer.getSpriteBatch());
			}
		}
		
		for (Projectile p : ObjectGrid.projectileList()) {
			p.draw(renderer.getSpriteBatch());
		}
		
		renderer.getSpriteBatch().end();
		
		// Determine whether or not to draw menu and draw/don't
		if(openTowerMenu){
			towerTable.setVisible(true);
			closeMenu.setVisible(true);
			
			stage.getSpriteBatch().begin();
			if(thinking) {
				nameLabel.setVisible(true);
				damageLabel.setVisible(true);
				rangeLabel.setVisible(true);
				stage.getSpriteBatch().setColor(stage.getSpriteBatch().getColor().r, stage.getSpriteBatch().getColor().g, stage.getSpriteBatch().getColor().b, .5f);
				infoNinePatch.draw(stage.getSpriteBatch(), TiledMapGame.screenWidth-110, TiledMapGame.screenHeight-80, 110, 80);
			}
			if(selectionConfirmed){
				selectionConfirmed = false;
				
				if (usingTower) {
					Gdx.input.setInputProcessor(i);
				}
				
				confirmSelection.setVisible(false);
				nameLabel.setVisible(false);
				damageLabel.setVisible(false);
				rangeLabel.setVisible(false);
			}
			
			stage.getSpriteBatch().setColor(stage.getSpriteBatch().getColor().r, stage.getSpriteBatch().getColor().g, stage.getSpriteBatch().getColor().b, .5f);
			towerNinePatch.draw(stage.getSpriteBatch(), 0, Gdx.graphics.getHeight()/2, 120, Gdx.graphics.getHeight()/2); 
			
			stage.getSpriteBatch().end();
			
		} else if(openUnitMenu){
			unitTable.setVisible(true);
			closeMenu.setVisible(true);
			
			stage.getSpriteBatch().begin();
			stage.getSpriteBatch().setColor(stage.getSpriteBatch().getColor().r, stage.getSpriteBatch().getColor().g, stage.getSpriteBatch().getColor().b, .5f);
			
			if(thinking){
				nameLabel.setVisible(true);
				damageLabel.setVisible(true);
				rangeLabel.setVisible(true);
				stage.getSpriteBatch().setColor(stage.getSpriteBatch().getColor().r, stage.getSpriteBatch().getColor().g, stage.getSpriteBatch().getColor().b, .5f);
				infoNinePatch.draw(stage.getSpriteBatch(), TiledMapGame.screenWidth-110, TiledMapGame.screenHeight-80, 110, 80);
			}
			unitNinePatch.draw(stage.getSpriteBatch(), 0, 0, 120, Gdx.graphics.getHeight());
			
			stage.getSpriteBatch().end();
			
			
		} else {
			confirmSelection.setVisible(false);
			nameLabel.setVisible(false);
			damageLabel.setVisible(false);
			rangeLabel.setVisible(false);
			towerTable.setVisible(false);
			unitTable.setVisible(false);
			closeMenu.setVisible(false);
		}
		
		stage.draw();
		
		// Timer Stuff
		
			peaceTimer.setVisible(false);
			PathFinder.find_path(ObjectGrid.gridLayer(0).getGrid(),10, 10);
			
		// Spawn enemies based on the text file(it is read in Level.java)
		if(wave <= level.getTotalWaves()){
			if(tbw <= 0){
				if(tbs <= 0){
					tbs = level.getTimeBetSpawns(); // reset enemy spawn timer(within wave)
					switch(level.getEnemyTypes((int)(wave-1))){
						case 1:
							level.generateEnemy("Skeleton"); // create a skeleton
							break;
						case 2:
							level.generateEnemy("Wight");
							break;
						default:
							break;
					}
					eCounter++; // add to the counter
					if(eCounter == level.getEnemiesPerWave()){ // if true, wave has ended
						tbw = level.getTimeBetWaves(); // reset wave timer
						eCounter = 0; // reset enemy counter
						wave++; // advance to next wave
					}
				} else {
					tbs -= Gdx.graphics.getDeltaTime(); // count down time since last enemy spawn
				}
			} else {
				tbw -= Gdx.graphics.getDeltaTime(); // count down time since start of last wave
				peaceTimer.setVisible(true);
				current = tbw;
				current = (float)(Math.floor(current * 1e2) / 1e2);
				if(current > 0) {
					peaceTimer.setText( Float.toString(current));
				}
			}
		} else {
			// done
		}

		
		// Show tower range
		for (Tower t: ObjectGrid.towerList()) {
			if (t.getMoved()) {
				if (!t.isPlaced()) {
					sr.begin(ShapeType.Line);
					sr.setColor(Color.BLACK);
					sr.circle(t.getX()+16, t.getY()+16, t.getRange());
					sr.end();
				}
			}
		}
		
		// I WILL FIND YOU
		Rectangle r;
		for (Tower t : ObjectGrid.towerList()) {
			r = t.getBoundingRectangle();
			sr.begin(ShapeType.Line);
			sr.setColor(Color.YELLOW);
			sr.rect(r.x, r.y, r.width, r.height);
			sr.end();
		}
		
		// Remove diposed objects
		for (Entity e : ObjectGrid.disposeList) {
			if (e instanceof Enemy) {
				ObjectGrid.enemies.remove(e);
			}
			if (e instanceof Projectile) {
				ObjectGrid.projectiles.remove(e);
			}
			if (e instanceof Tower) {
				ObjectGrid.towers.remove(e);
			}
			e = null;
		}
		ObjectGrid.disposeList.clear();
	}

	@Override
	public void resize(int width, int height) {

		camera.viewportWidth = width;
		camera.viewportHeight = height;

	}

	@Override
	public void show() {
		tbw = level.getTimeBetWaves();
		tbs = level.getTimeBetSpawns();
		
		camera = new OrthographicCamera();
		
		layer = (TiledMapTileLayer) level.getMap().getLayers().get(0);
		
		camera.position.x = layer.getWidth()*(layer.getTileWidth()/2);
		camera.position.y = layer.getHeight()*(layer.getTileHeight()/2);
		
		font = new BitmapFont();
		font.scale(-.2f);
		font.setColor(.1f, .1f, .1f, 1f);
		
		i = new Input(level);
		
		stage = new Stage();
		
		im = new InputMultiplexer(stage, this);
//		Gdx.input.setInputProcessor(this);
//		Gdx.input.setInputProcessor(stage);
		Gdx.input.setInputProcessor(im);
		
		setupSkin();
		createUIButtons();
	    createInfoBox();
	    cancelConfirm();
	    setupButtonTable();
	    setupTowerOptions();
	    setupTowerTable();
		setupUnitOptions();		
		setupUnitTable();
		
		stage.addActor(buttonTable);		
		stage.addActor(closeMenu);
		stage.addActor(confirmSelection);
		stage.addActor(towerTable);
		stage.addActor(infoTable);
		stage.addActor(unitTable);
		
		current = level.getTimeBetWaves();
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
		//TEST SAVE
		LevelSaver save = new LevelSaver("testSaveFile.txt");
		save.saveLevel(level);
		renderer.getSpriteBatch().dispose();
//		renderer.dispose(); Leave out until there is another screen to switch to
		for(Tower t: ObjectGrid.towers){
			t.getTexture().dispose();
			t.dispose();
		}
		sr.dispose();
		stage.getSpriteBatch().dispose();
		skin.dispose();
		font.dispose();
		
	}
	
	
	/**
	 *  Opens tower menu if it is closed and closes tower menu if it is open.
	 */
	private void changeTowerMenuState() {
		if(openTowerMenu) {
			openTowerMenu = false;
		} else {
			openUnitMenu = false; // close the other menu
			unitTable.setVisible(false); // close the other menu
			openTowerMenu = true;
		}
	}
	
	private void changeUnitMenuState() {
		if(openUnitMenu) {
			openUnitMenu = false;
		} else {
			openTowerMenu = false; // close the other menu
			towerTable.setVisible(false); // close the other menu
			openUnitMenu = true;
		}
	}
	
	private void setupSkin() {
		
		skin = new Skin();
		skin.add("up", new Sprite(new Texture("img/buttonUp.png")));
		skin.add("down", new Sprite(new Texture("img/buttonDown.png")));
		skin.add("uUp", new Sprite(new Texture("img/uButtonUp.png")));
		skin.add("uDown", new Sprite(new Texture("img/uButtonDown.png")));
		skin.add("cresUp", new Sprite(new Texture("img/CresTow1.png")));
		skin.add("cresDown", new Sprite(new Texture("img/CresTow8.png")));
		skin.add("bombUp", new Sprite(new Texture("img/bombTowerUp.png")));
		skin.add("bombDown", new Sprite(new Texture("img/bombTowerDown.png")));
		skin.add("ampUp", new Sprite(new Texture("img/amplifyTowerUp.png")));
		skin.add("ampDown", new Sprite(new Texture("img/amplifyTowerDown.png")));
		skin.add("fireballUp", new Sprite(new Texture("img/fireballTowerUp.png")));
		skin.add("fireballDown", new Sprite(new Texture("img/fireballTowerDown.png")));
		
		skin.add("mageUp", new Sprite(new Texture("img/mageUp.png")));
		skin.add("mageDown", new Sprite(new Texture("img/mageDown.png")));
		
		skin.add("enemyDisplay", new Sprite(new Texture("img/enemyDisplay.png")));
	}
	
	private void createUIButtons() {
		
		TextButtonStyle twrsButtonStyle = new TextButtonStyle();
		twrsButtonStyle.up = skin.getDrawable("up");
		twrsButtonStyle.down = skin.getDrawable("down");
		
	    twrsButton = new Button(twrsButtonStyle);
	    twrsButton.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        			changeTowerMenuState();
	        }
	    });
	    
	    TextButtonStyle unitsButtonStyle = new TextButtonStyle();
		unitsButtonStyle.up = skin.getDrawable("uUp");
		unitsButtonStyle.down = skin.getDrawable("uDown");
		
	    unitsButton = new Button(unitsButtonStyle);
	    unitsButton.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        			changeUnitMenuState();
	        }	        
	    });
	    
	    TextButtonStyle displayButtonStyle = new TextButtonStyle();
		displayButtonStyle.up = skin.getDrawable("enemyDisplay");
		displayButtonStyle.down = skin.getDrawable("enemyDisplay");
		
	    displayButton = new Button(displayButtonStyle);
	    displayButton.addListener(new InputListener() {
	    	
	    	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	    		
	    		if(!showHealth) {
	    			if(!ObjectGrid.enemyList().isEmpty()){
	    				showHealth = true;
	    			}
	    		} else {
	    			showHealth = false;
	    		}
	    		
	    		return true;
	     	}
	     
	     	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	     	
	     	}

	    	
	    });
	    
	}
	
	// Peace Timer stuff also here
	private void createInfoBox() {
		
		infoNinePatch = new MenuNinePatch();
		
		infoTable = new Table();
		infoTable.setBounds(TiledMapGame.screenWidth-110, TiledMapGame.screenHeight-80, 110, 80);
		infoTable.pad(5);
		infoTable.top();
		
		TextButton.TextButtonStyle infoStyle = new TextButton.TextButtonStyle();
		infoStyle.font = new BitmapFont();
		infoStyle.fontColor = new Color(255,0,0,.7f);
		
		nameLabel = new TextButton("Tower Name", infoStyle);
		damageLabel = new TextButton("Tower Damage", infoStyle);
		rangeLabel = new TextButton("Tower Range", infoStyle);
		nameLabel.setVisible(false);
		damageLabel.setVisible(false);
		rangeLabel.setVisible(false);
		
		infoTable.add(nameLabel);
		infoTable.row();
		infoTable.add(damageLabel);
		infoTable.row();
		infoTable.add(rangeLabel);
		
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
	        		System.out.println("Crescent Tower selected");
	        		thinking = true;
	        		confirmSelection.setVisible(true);
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        	usingUnit = false;
	        	towerChoice = 1;
	        	usingTower = true;
	        	nameLabel.setText("Crescent Tower");
	        	damageLabel.setText("Damage: 50");
	        	rangeLabel.setText("Range: 90");
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
	        	usingUnit = false;
	        	towerChoice = 2;
	        	usingTower = true;
	        	nameLabel.setText("Bomb Tower");
	        	damageLabel.setText("Damage: 80");
	        	rangeLabel.setText("Range: 50");
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
	        	usingUnit = false;
	        	towerChoice = 3;
	        	usingTower = true;
	        	nameLabel.setText("Amplifier Tower");
	        	damageLabel.setText("Damage: 0");
	        	rangeLabel.setText("Range: 100");
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
	        	usingUnit = false;
	        	towerChoice = 4;
	        	usingTower = true;
	        	nameLabel.setText("Fireball Tower");
	        	damageLabel.setText("Damage: 60");
	        	rangeLabel.setText("Range: 90");
	        }
	    });
	}
	
	private void setupUnitOptions(){
		
		unitNinePatch = new MenuNinePatch();
		
		TextButtonStyle mageButtonStyle = new TextButtonStyle();
	    mageButtonStyle.up = skin.getDrawable("mageUp");
	    mageButtonStyle.down = skin.getDrawable("mageDown");
	    
	    mageButton = new Button(mageButtonStyle);
	    mageButton.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
//	        		System.out.println("Mage selected");
	        		thinking = true;
	        		confirmSelection.setVisible(true);
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        	usingTower = false;
	        	openTowerMenu = false;
	        	usingUnit = true;
	        	unitChoice = 1;
	        	nameLabel.setText("Mage");
	        	damageLabel.setText("Damage: 20");
	        	rangeLabel.setText("Range: 90");
	        }
	    });
		
	}
	
	private void cancelConfirm(){
	    // Close and confirm buttons
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
	        		if(openTowerMenu) {
	        			changeTowerMenuState();
	        		} 
	        		if(openUnitMenu) {
	        			changeUnitMenuState();
	        		}
	        		
	        }
	    });
	    closeMenu.setPosition(75, 5+256);
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
	        	
	        	if(usingTower) {
	        		usingUnit = false;
	        		
		        	switch(towerChoice){
		        		case 1:
		        			ObjectGrid.towers.add(TiledMapGame.towerTypeLibrary.get("Crescent").createInstance());
		        			break;
		        		case 2:
		        			ObjectGrid.towers.add(TiledMapGame.towerTypeLibrary.get("Bomb").createInstance());
		        			break;
		        		case 3:
		        			ObjectGrid.towers.add(TiledMapGame.towerTypeLibrary.get("Amplify").createInstance());
		        			break;
		        		case 4:
		        			ObjectGrid.towers.add(TiledMapGame.towerTypeLibrary.get("Fireball").createInstance());
		        		default:
		        			break;
		        	}
	        	}
	        	else if(usingUnit) {
	        		usingTower = false;
	        		
		        	switch(unitChoice){
		        		case 1:
		        			ObjectGrid.units.add(new Unit(new UnitType(new Texture("img/mage.png"), 100, 2f, 1)));
		        			break;
		        		default:
		        			break;
		        	}
	        	}
	        	
	        }
	    });
	    confirmSelection.setPosition(10, 5+256);
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
	
	private void setupUnitTable(){
		
		unitTable = new Table();
		unitTable.setBounds(0, 0, 100, TiledMapGame.screenHeight-10); // offset y by 10 for appearance
		unitTable.pad(0, 20, 0, 0);
		unitTable.top();
		
		unitTable.add(mageButton).spaceRight(24).spaceBottom(5);
//		unitTable.add(another unit button).spaceBottom(5);
		unitTable.row();
		
		unitTable.setVisible(false);
		
		
	}
	
	private void setupButtonTable() {
		
		buttonTable = new Table();
		
		buttonTable.setPosition(TiledMapGame.screenWidth-150, 50);
		
	    buttonTable.add(displayButton).pad(15);
	    buttonTable.add(unitsButton).pad(15);
	    buttonTable.add(twrsButton).pad(15);
	    
	    
	}
	
	public int getTowerChoice(){
		return towerChoice;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY = TiledMapGame.screenHeight - screenY;
//		System.out.println("[GameScreen] Clicked " + screenX + " " + screenY);
		for (Tower t : ObjectGrid.towerList()) {
			if (t.getBoundingRectangle().contains(screenX, screenY)) {
//				System.out.println("[GameScreen] Clicked tower " + t);
				return true;
			}
		}
		// Following Aaron's idea
		level.generateEnemy("Skeleton");
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
//		System.out.println("[GameScreen] " + screenX + " " + screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
