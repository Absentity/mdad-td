import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Game implements ApplicationListener {
	
	Stage stage;
	ShapeRenderer sr;
	
	public void create() {
		
		sr = new ShapeRenderer();
		stage = new Stage();
		
//		stage.addListener(new InputListener() {
//	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//				
//	        	System.out.println("touchDown at " + x + "," + y );
//	        	return true;
//	        }
//	 
//	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//	        	
//	        	System.out.println("touchUp called");
//	        	sr.begin(ShapeType.Filled);
//	        	sr.setColor(Color.ORANGE);
//	        	sr.circle(x, y, 5);
//	        	sr.end();
//	        		
//	        }
//		});
		
	}

	public void resize(int width, int height) {
		
	}

	public void render() {
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		Gdx.gl.glClearColor(0, .2f, 0, 0);
		
	}

	public void pause() {
		
	}

	public void resume() {
		
	}

	public void dispose() {
		
		stage.dispose();
		sr.dispose();
		
	}

}
