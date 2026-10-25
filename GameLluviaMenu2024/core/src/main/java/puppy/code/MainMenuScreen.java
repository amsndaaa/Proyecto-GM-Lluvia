package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;


public class MainMenuScreen implements Screen {

	final GameLluviaMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
    private Texture fondoMenu;

	public MainMenuScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
        fondoMenu = new Texture(Gdx.files.internal("fondo_dieciochero.png"));
        GestorAudio.getInstance().reproducirMusica();

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
        batch.draw(fondoMenu, 0, 0, 800, 480);
        font.getData().setScale(2.2f);
        font.draw(batch, "¡EL HUASO CON BAJÓN!", 180, 410);
        font.getData().setScale(1.15f);
        font.draw(batch, "Controles: Puedes moverte con flechas/A-D", 140, 300);
        font.draw(batch, "Por cada completo que te comas son (+10 puntos), cuidado con las arepas, ya que perderás (-1 vida)", 100, 250);
        font.draw(batch, "Cuando haya viento tómate el terremoto que caerá, ya que da (1 vida + 50 puntos)", 80, 200);
        font.getData().setScale(1.3f);
        font.draw(batch, "Toca en cualquier lugar para comenzar!", 140, 100);
		batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
        fondoMenu.dispose();

	}

}
