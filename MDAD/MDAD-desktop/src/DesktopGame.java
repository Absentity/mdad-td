import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopGame {
	
	public final static int screenWidth = 700;
	public final static int screenHeight = 600;
	
	public static void main (String[] args) {
		new LwjglApplication(new Game(), "MDAD", screenWidth, screenHeight, false);

	}

}
