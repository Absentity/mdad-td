package com.me.tiledMapGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.me.tiledMapGame.TiledMapGame;
import com.me.tiledMapGame.entities.AnimationEntity;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.entities.Entity;
import com.me.tiledMapGame.entities.Projectile;
import com.me.tiledMapGame.entities.Structure;
import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.entities.Unit;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.pathing.PathFinder;

public class GameScreen implements Screen, InputProcessor {
	
	public Level level;
	public boolean lost = false;
	public static OrthographicCamera camera;
	public static boolean selectionConfirmed = false;
	public static boolean thinking = false;
	public static boolean openTowerMenu = false;
	public static boolean openUnitMenu = false;
	public int x;
	public int y;
	private String entityChoice;
	private int unitChoice = 1;
	
	public static InputMultiplexer im;
	private OrthogonalTiledMapRenderer renderer;
	private TiledMapTileLayer layer;
	
	boolean usingTower = false;
	boolean usingUnit = false;
	
	private boolean isPaused = false;

	
	ShapeRenderer sr = new ShapeRenderer(); // FOR TESTING
	
	// UI stuff here
	Button twrsButton;
	Button unitsButton;
	Button cresButton;
	Button bombButton;
	Button ampButton;
	Button fireballButton;
	Button farmButton;
	
	Button mageButton;
	
	TextButton confirmSelection;
	TextButton closeMenu;
	Skin skin;
	public static Stage stage;
	Table buttonTable;
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
	TextButton priceLabel; // Button without a listener
	TextButton peaceTimer; // Button without a listener
	MenuNinePatch uSMenu;
	Table uSTable;
	TextButton upgrade;
	TextButton sell;
	MenuNinePatch box;
	
	Button displayButton;
	private Button pauseButton; 
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

	private TextButton goldCounter;

	private TextButton xpCounter;
	
	/**
	 * 
	 * @param level
	 */
	public GameScreen(Level l) {
		
		this.level = l;
		int hold=level.leveln;
		if(hold==1){
			x=((int)(475/32));
			y=((int)(450/32));
		}
		if(hold==2){
			x=((int)(475/32));
			y=((int)(450/32));
		}
		if(hold==3){
			x=((int)(450/32));
			y=((int)(150/32));
		}
		if(hold==4){
			x=((int)(1));
			y=((int)(450/32));
		}
		if(hold==5){
			x=((int)(475/32));
			y=((int)(450/32));
		}
		if(level == null){
			this.level = new Level("MDADMap1v1");
			
			//Create Kingdom if making new level
			ObjectGrid.towerList().add(new Tower(TiledMapGame.towerTypeLibrary.get("Portal"))); //TODO Change to Kingdom
			ObjectGrid.towerList().get(0).setPosition(level.castleX*32, level.castleY*32);
		}
		
		
		renderer = new OrthogonalTiledMapRenderer(level.getMap());
		stage = new Stage();
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
		
		// I WILL FIND YOU
		sr.begin(ShapeType.Line);
		sr.setColor(Color.YELLOW);
		Rectangle r;
		for (Tower t : ObjectGrid.towerList()) {
			r = t.getBoundingRectangle();
			sr.rect(r.x, r.y, r.width, r.height);
		}
		for (Unit u : ObjectGrid.unitList()) {
			r = u.getBoundingRectangle();
			sr.rect(r.x, r.y, r.width/4, r.height/3); //TODO set bounds on units! Important for collision
		}
		sr.setColor(Color.RED);
		for (Projectile p : ObjectGrid.projectileList()) {
			r = p.getBoundingRectangle();
			sr.rect(r.x, r.y, r.width, r.height);
		}
		sr.setColor(Color.CYAN);
		for (AnimationEntity ae : ObjectGrid.animations) {
			r = ae.getBoundingRectangle();
			sr.rect(r.x, r.y, r.width/5, r.height/4);
		}
		sr.end();
		
		// Draw
		renderer.getSpriteBatch().begin(); // FOR TESTING
		
		drawGoldXPBox(); // draw gold and exp box
		
		if (showHealth) {
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
			for (Unit u : ObjectGrid.unitList()) {
				if (u.getHealth() < 100) {
					font.draw(renderer.getSpriteBatch(), u.showHealth(), u.getX(), u.getY());
				}
			}
		}
		

		// Draw towers
		for (Tower t: ObjectGrid.towerList()) { // FOR TESTING
			t.update(Gdx.graphics.getDeltaTime()); // FOR TESTING
			renderer.getSpriteBatch().setColor(1,1,1,t.getAlpha()); // FOR TESTING
			
			if (t.towerType == 0) {
				renderer.getSpriteBatch().draw(t.getCurrentFrame(), t.getX(), t.getY()); // FOR TESTING
			} else if (t.getMoved()){
				renderer.getSpriteBatch().draw(t.getCurrentFrame(), t.getX(), t.getY()); // FOR TESTING
			}

		}
		
		// Draw towers
		for (Structure s: ObjectGrid.structureList()) { // FOR TESTING
			s.update(Gdx.graphics.getDeltaTime()); // FOR TESTING
			renderer.getSpriteBatch().setColor(1,1,1,s.getAlpha()); // FOR TESTING
			
			renderer.getSpriteBatch().draw(s.getCurrentFrame(), s.getX(), s.getY()); // FOR TESTING
		}
		
		for (Structure s : ObjectGrid.structureList()) {
			s.update(Gdx.graphics.getDeltaTime());
			renderer.getSpriteBatch().draw(s.getCurrentFrame(), s.getX(), s.getY());
		}
		
		for (Unit u : ObjectGrid.unitList()) {
			u.update(Gdx.graphics.getDeltaTime()); // Might not need this, as Unit is MobileEntity
			renderer.getSpriteBatch().setColor(1,1,1,1);
			renderer.getSpriteBatch().draw(u.getCurrentFrame(), u.getX(), u.getY());
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
		
		for (Tower t: ObjectGrid.towerList()) { // FOR TESTING
			if(t.isSelected() && t.getTowerType() != 0) {
				drawUpgradeOrSell(t);
				upgrade.setVisible(true);
				sell.setVisible(true);
			}
		}
		
		for (AnimationEntity ae : ObjectGrid.animations) {
			ae.update(Gdx.graphics.getDeltaTime());
			// Use this to test epicenter alignment
//			renderer.getSpriteBatch().setColor(Color.GREEN);
//			renderer.getSpriteBatch().draw(ae.getCurrentFrame(), ae.getX(), ae.getY());
			renderer.getSpriteBatch().setColor(ae.getColor());
			renderer.getSpriteBatch().draw(ae.getCurrentFrame(), ae.getX(), ae.getY(), ae.getWidth()/2, ae.getHeight()/2,
					ae.getWidth(), ae.getHeight(), ae.getScaleX(), ae.getScaleY(), ae.getRotation());
		}
		renderer.getSpriteBatch().setColor(1,1,1,1);
		
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
				priceLabel.setVisible(true);
				stage.getSpriteBatch().setColor(stage.getSpriteBatch().getColor().r, stage.getSpriteBatch().getColor().g, stage.getSpriteBatch().getColor().b, .5f);
				infoNinePatch.draw(stage.getSpriteBatch(), TiledMapGame.screenWidth-110, TiledMapGame.screenHeight-100, 110, 100);
			}
			if(selectionConfirmed){
				selectionConfirmed = false;
				
//				if (usingTower) {
//					Gdx.input.setInputProcessor(i);
//				}
				
				confirmSelection.setVisible(false);
				nameLabel.setVisible(false);
				damageLabel.setVisible(false);
				rangeLabel.setVisible(false);
				priceLabel.setVisible(false);
			}
			
			stage.getSpriteBatch().setColor(stage.getSpriteBatch().getColor().r, stage.getSpriteBatch().getColor().g, stage.getSpriteBatch().getColor().b, .5f);
			towerNinePatch.draw(stage.getSpriteBatch(), 0, (3*Gdx.graphics.getHeight()/4)-20, 120, (Gdx.graphics.getHeight()/4)+20); 
			
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
				priceLabel.setVisible(true);
				stage.getSpriteBatch().setColor(stage.getSpriteBatch().getColor().r, stage.getSpriteBatch().getColor().g, stage.getSpriteBatch().getColor().b, .5f);
				infoNinePatch.draw(stage.getSpriteBatch(), TiledMapGame.screenWidth-110, TiledMapGame.screenHeight-100, 110, 100);
			}
			unitNinePatch.draw(stage.getSpriteBatch(), 0, 3*Gdx.graphics.getHeight()/4, 120, Gdx.graphics.getHeight()/4);
			
			stage.getSpriteBatch().end();
			
			
		} else {
			confirmSelection.setVisible(false);
			nameLabel.setVisible(false);
			damageLabel.setVisible(false);
			rangeLabel.setVisible(false);
			priceLabel.setVisible(false);
			towerTable.setVisible(false);
			unitTable.setVisible(false);
			closeMenu.setVisible(false);
		}
		
		stage.draw();
		
		// Timer Stuff
		
			peaceTimer.setVisible(false);
			
			
			
			
			
			
			
			PathFinder.find_path(ObjectGrid.gridLayer(0).getGrid(),x, y);
			
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
						case 3:
							level.generateEnemy("Wyvern");
							System.out.println("Ka KAAA!!!!!!!");
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
			if(level.getObjectGrid().enemies.size() == 0) {
//				level.won = true;
				System.out.println("You Won!");
			}
		}

		// Show resources
		goldCounter.setText("Gold: " + Level.getResource("Gold"));
		xpCounter.setText("XP: " + Level.getResource("XP"));
		
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
		
		// Remove diposed objects
		for (Entity e : ObjectGrid.disposeList) {
			if (e instanceof Enemy) {
				ObjectGrid.enemies.remove(e);
			} else if (e instanceof Projectile) {
				ObjectGrid.projectiles.remove(e);
			} else if (e instanceof Tower) {
				if(((Tower) e).isSelected()) {
					upgrade.setVisible(false);
					sell.setVisible(false);
				}
				ObjectGrid.gridLayer(0).getGrid()[(int) (e.getY()/32)][(int) (e.getX()/32)].is_buildable = true;
				ObjectGrid.gridLayer(0).getGrid()[(int) (e.getY()/32)][(int) (e.getX()/32)].is_passable = true;
				ObjectGrid.towers.remove(e);
			} else if (e instanceof Unit) {
				ObjectGrid.unitList().remove(e);
			} else if (e instanceof AnimationEntity) {
				ObjectGrid.animations.remove(e);
			} else if (e instanceof Structure) {
				if(((Structure) e).isSelected()) {
					upgrade.setVisible(false);
					sell.setVisible(false);
				}
				ObjectGrid.structures.remove(e);
			}
			e = null;
		}
		ObjectGrid.disposeList.clear();
		
		/*Check if the Player Lost*/
		if(ObjectGrid.towers.isEmpty() || ObjectGrid.towers.get(0).getTowerType() != 0){
			System.out.println("You are not doing so good!");
			this.lost = true;
		}
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
		
		//stage = new Stage();
		
		im = new InputMultiplexer(stage, this);
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
		createUpgradeOrSell();
		createGoldXPBox();
		
		stage.addActor(buttonTable);		
		stage.addActor(closeMenu);
		stage.addActor(confirmSelection);
		stage.addActor(towerTable);
		stage.addActor(infoTable);
		stage.addActor(unitTable);
		stage.addActor(uSTable);
		
		current = level.getTimeBetWaves();
	}

	

	@Override
	public void hide() {
		//dispose();
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
		//renderer.getSpriteBatch().dispose();
		//renderer.dispose(); Leave out until there is another screen to switch to
		for(Tower t: ObjectGrid.towers){
		    t.getTexture().dispose();
			t.dispose();
		}
		for(Enemy E: ObjectGrid.enemyList()){
//			E.getTexture().dispose();
			E.dispose();
		}
		for(AnimationEntity A: ObjectGrid.animations){
			A.dispose();
		}
		//im.removeProcessor(stage);
		//sr.dispose();
		//stage.getSpriteBatch().dispose();
		//stage.dispose();
		//skin.dispose();
		//font.dispose();
		
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
		// Main UI
		skin.add("up", new Sprite(new Texture("img/buttonUp.png")));
		skin.add("down", new Sprite(new Texture("img/buttonDown.png")));
		skin.add("uUp", new Sprite(new Texture("img/uButtonUp.png")));
		skin.add("uDown", new Sprite(new Texture("img/uButtonDown.png")));
		skin.add("pauseUp", new Sprite(new Texture("img/uButtonUp.png")));
		skin.add("pauseDown", new Sprite(new Texture("img/uButtonDown.png")));
		// Towers
		skin.add("cresUp", new Sprite(new Texture("img/CresTow1.png")));
		skin.add("cresDown", new Sprite(new Texture("img/CresTow8.png")));
		skin.add("bombUp", new Sprite(new Texture("img/bombTowerUp.png")));
		skin.add("bombDown", new Sprite(new Texture("img/bombTowerDown.png")));
		skin.add("ampUp", new Sprite(new Texture("img/amplifyTowerUp.png")));
		skin.add("ampDown", new Sprite(new Texture("img/amplifyTowerDown.png")));
		skin.add("fireballUp", new Sprite(new Texture("img/fireballTowerUp.png")));
		skin.add("fireballDown", new Sprite(new Texture("img/fireballTowerDown.png")));
		// Structures
		skin.add("farmUp", new Sprite(new Texture("img/buttonStructureFarmUpDown.png")));
		skin.add("farmDown", new Sprite(new Texture("img/buttonStructureFarmUpDown.png")));
		// Units
		skin.add("mageUp", new Sprite(new Texture("img/mageUp.png")));
		skin.add("mageDown", new Sprite(new Texture("img/mageDown.png")));
		// ?
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
	    
	    TextButtonStyle pauseButtonStyle = new TextButtonStyle();
	    pauseButtonStyle.up = skin.getDrawable("pauseUp");
	    pauseButtonStyle.down = skin.getDrawable("pauseDown");
	    
	    pauseButton = new Button(pauseButtonStyle);
	    pauseButton.addListener(new InputListener() {
	    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	    		return true;
	    	}
	    	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//	    		isPaused = true;
	    		Music m = TiledMapGame.musicLibrary.get("worldOne");
	    		if (m.getVolume() == 0f) {
	    			m.setVolume(.8f);
	    		} else {
	    			m.setVolume(0f);
	    		}
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
		infoStyle.fontColor = new Color(255,215,0,.7f);
		
		nameLabel = new TextButton("Tower Name", infoStyle);
		damageLabel = new TextButton("Tower Damage", infoStyle);
		rangeLabel = new TextButton("Tower Range", infoStyle);
		priceLabel = new TextButton("Tower Price", infoStyle);
		nameLabel.setVisible(false);
		damageLabel.setVisible(false);
		rangeLabel.setVisible(false);
		priceLabel.setVisible(false);
		
		infoTable.add(nameLabel);
		infoTable.row();
		infoTable.add(damageLabel);
		infoTable.row();
		infoTable.add(rangeLabel);
		infoTable.row();
		infoTable.add(priceLabel);
		
		peaceTimer = new TextButton("20",infoStyle);
		peaceTimer.setWidth(20f);
		peaceTimer.setHeight(12f);
		peaceTimer.setPosition((TiledMapGame.screenWidth/2)-10, TiledMapGame.screenHeight-20-5); // offset by 5 for visual appearance
		stage.addActor(peaceTimer);
		
		goldCounter = new TextButton("20", infoStyle);
		goldCounter.setWidth(20f);
		goldCounter.setHeight(12f);
		goldCounter.setPosition(75, 20+16+8+8);
		stage.addActor(goldCounter);
		goldCounter.setVisible(true);
		
		xpCounter = new TextButton("20", infoStyle);
		xpCounter.setWidth(20f);
		xpCounter.setHeight(12f);
		xpCounter.setPosition(75, 16+8);
		stage.addActor(xpCounter);
		xpCounter.setVisible(true);
		
	}		
		
	
	private void createGoldXPBox() {
		
		box = new MenuNinePatch();
		
	}
	
	private void drawGoldXPBox() {
		
		renderer.getSpriteBatch().setColor(renderer.getSpriteBatch().getColor().r, renderer.getSpriteBatch().getColor().g, renderer.getSpriteBatch().getColor().b, .7f);
		box.draw(renderer.getSpriteBatch(), 45, 19, 85, 50);
		renderer.getSpriteBatch().setColor(renderer.getSpriteBatch().getColor().r, renderer.getSpriteBatch().getColor().g, renderer.getSpriteBatch().getColor().b, 1);
		
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
	        	entityChoice = "Crescent";
	        	usingTower = true;
	        	nameLabel.setText("Crescent Tower");
	        	damageLabel.setText("Damage: 50");
	        	rangeLabel.setText("Range: 90");
	        	priceLabel.setText("Cost: 70G");
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
	        	entityChoice = "Bomb";
	        	usingTower = true;
	        	nameLabel.setText("Bomb Tower");
	        	damageLabel.setText("Damage: 80");
	        	rangeLabel.setText("Range: 50");
	        	priceLabel.setText("Cost: 80G");
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
	        	entityChoice = "Amplify";
	        	usingTower = true;
	        	nameLabel.setText("Amplifier Tower");
	        	damageLabel.setText("Damage: 0");
	        	rangeLabel.setText("Range: 100");
	        	priceLabel.setText("Cost: 60G");
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
	        	entityChoice = "Fireball";
	        	usingTower = true;
	        	nameLabel.setText("Fireball Tower");
	        	damageLabel.setText("Damage: 60");
	        	rangeLabel.setText("Range: 90");
	        	priceLabel.setText("Cost: 30G");
	        }
	    });
	    
	    TextButtonStyle farmButtonStyle = new TextButtonStyle();
	    farmButtonStyle.up = skin.getDrawable("farmUp");
	    farmButtonStyle.down = skin.getDrawable("farmDown");
	    
	    farmButton = new Button(farmButtonStyle);
	    farmButton.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        		System.out.println("Farm Resource selected");
	        		thinking = true;
	        		confirmSelection.setVisible(true);
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        	usingUnit = false;
	        	entityChoice = "Farm";
	        	usingTower = true;
	        	nameLabel.setText("Farm Resource");
	        	damageLabel.setText("Earning: 20");
	        	rangeLabel.setText("Freq: 3s");
	        	priceLabel.setText("Cost: 20G");
	        }
	    });
	}
	
	private void setupUnitOptions() {
		
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
	        	entityChoice = "Mage";
	        	nameLabel.setText("Mage");
	        	damageLabel.setText("Damage: 20");
	        	rangeLabel.setText("Range: 90");
	        	priceLabel.setText("Price: 10G");
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
	    closeMenu.setPosition(75, 384-15); // 3/4 of 512
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
	        	
        		Entity entityToBuild;
        		if ("Farm".equals(entityChoice)) {
        			entityToBuild = TiledMapGame.structureTypeLibrary.get("Farm").createInstance();
        		} else if ("Mage".equals(entityChoice)) {
        			entityToBuild = TiledMapGame.unitTypeLibrary.get("Mage").createInstance();
        		} else {
        			entityToBuild = TiledMapGame.towerTypeLibrary.get(entityChoice).createInstance();
        		}
        		
        		// Kinda hacky, doesn't account for cost type.....
        		final int price = entityToBuild.getStat("Cost");
        		if (Level.getResource("Gold") >= price) {
        			Level.gainResource("Gold", -price);
        			
        			if (entityToBuild instanceof Tower) {
        				usingUnit = false;
        				ObjectGrid.towers.add((Tower) entityToBuild);
		        		level.lastBuilt = entityToBuild;
		        		Gdx.input.setInputProcessor(i);
        			} else if (entityToBuild instanceof Structure) {
        				usingUnit = false;
        				ObjectGrid.structures.add((Structure) entityToBuild);
		        		level.lastBuilt = entityToBuild;
		        		Gdx.input.setInputProcessor(i);
        			} else if (entityToBuild instanceof Unit) {
        				usingTower = false;
        				// Place next to kingdom instead of enable build mode
        				entityToBuild.setPosition(320-16,320);
        				ObjectGrid.units.add((Unit) entityToBuild);
	    			}
        		}
	        	
	        }
	    });
	    confirmSelection.setPosition(10, 384-15);
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
		
		 towerTable.add(farmButton).spaceRight(24).spaceBottom(5).spaceTop(5);
//		 towerTable.add(NEXT NEW TOWER).spaceBottom(5);
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
		
		buttonTable.add(pauseButton).pad(15);
	    buttonTable.add(displayButton).pad(15);
	    buttonTable.add(unitsButton).pad(15);
	    buttonTable.add(twrsButton).pad(15);   
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
		level.generateEnemy("Wight"); // TODO take this out for final design
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenY = TiledMapGame.screenHeight - screenY;
		
		for(Tower t : ObjectGrid.towerList()) {
			t.setSelected(false);
			upgrade.setVisible(false);
			sell.setVisible(false);
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY = TiledMapGame.screenHeight - screenY;
//		System.out.println("[GameScreen] Clicked " + screenX + " " + screenY);
		
		
		// Move
		for(Unit u : ObjectGrid.unitList()) {
			if(u.isSelected()) {
				u.setDestination(screenX, screenY);
				u.setSelected(false);
			}
		}
		
		// Select
		for (Tower t : ObjectGrid.towerList()) {
			if (t.getBoundingRectangle().contains(screenX, screenY)) {
				System.out.println("[GameScreen] Clicked tower " + t);
				
				t.setSelected(true);
				
				return true;
			} else {
				t.setSelected(false);
			}
		}
		
		// Select
		for(Unit u : ObjectGrid.unitList()) {
			if(u.getBoundingRectangle().contains(screenX, screenY)) {
				System.out.println("[GameScreen] Clicked unit " + u);
				
				u.setSelected(true);
				
				return true;
				
			 } else {
				 u.setSelected(false);
			 }
			
			
		}
		// Following Aaron's idea
		level.generateEnemy("Skeleton"); // TODO take this out for final design
		return false;
	}
	
	private void updateUorS(Entity e) {
		
		upgrade.setText("Uprade (" + (int)(((Tower)e).getPrice()*1.5) + "G)");
		sell.setText("Sell (" + ((Tower)e).getPrice()/2 + "G)");
		
	}
	
	private void createUpgradeOrSell(){
		
		uSMenu = new MenuNinePatch();
		uSTable = new Table();
		
		uSTable.setBounds(0 , 0, 125, 50);
		uSTable.pad(5);
		uSTable.top();
		
		TextButton.TextButtonStyle uSStyle = new TextButton.TextButtonStyle();
		uSStyle.font = new BitmapFont();
		uSStyle.fontColor = new Color(0,255,255,.7f);
		uSStyle.pressedOffsetX = 2;
	    uSStyle.pressedOffsetY = -2;
	    
		upgrade = new TextButton("Upgrade (Price)", uSStyle);
		upgrade.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        			
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        		System.out.println("Powering up!");
	        }
	    });
		
		sell = new TextButton("Sell (Price)", uSStyle);
		sell.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {
	        			
	            return true;
	        }

	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	        		System.out.println("Bye. D:");
	        		for(Tower t: ObjectGrid.towerList()) {
	        			if(t.isSelected()) {
	        				ObjectGrid.towerList().remove(t);
	        				upgrade.setVisible(false);
	        				sell.setVisible(false);
	        				Level.gainResource("Gold", t.getPrice()/2);
	        				break;
	        			}
	        		}
	        }
	    });
		
		upgrade.setVisible(false);
		sell.setVisible(false);
		
		uSTable.add(upgrade);
		uSTable.row();
		uSTable.add(sell);
		
	}
	
//	private String getInfo(Tower t) {
//		return Integer.toString(t.getPrice());
//	}
	
	private void drawUpgradeOrSell(Tower tower){
		
		updateUorS(tower);
		
		upgrade.setPosition(tower.getX()+40, tower.getY()+70);
		sell.setPosition(tower.getX()+40, tower.getY()+45);
		
		uSMenu.draw(renderer.getSpriteBatch(), tower.getX()+35 , tower.getY()+40, 125, 55);
		upgrade.draw(renderer.getSpriteBatch(), .8f);
		sell.draw(renderer.getSpriteBatch(), .8f);
		
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}


}
