package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class GameOverScreen implements Screen {
	private final GameLluviaMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
    private Texture fondoFin;

	public GameOverScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
        fondoFin = new Texture(Gdx.files.internal("fondo_dieciochero.png"));
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
        batch.setColor(0.6f, 0.4f, 0.4f, 0.85f);
        batch.draw(fondoFin, 0, 0, 800, 480);
        batch.setColor(Color.WHITE);
        font.getData().setScale(2.3f);
        font.draw(batch, "¡CERRARON LAS FONDAS!", 180, 390);
        font.getData().setScale(1.3f);
        font.draw(batch, "¡El huaso comió muchas arepas!", 190, 300);
        font.draw(batch, "Mejor récord: " + game.getHigherScore() + " pts", 220, 230);
        font.getData().setScale(1.25f);
        font.draw(batch, "Toca en cualquier lado para reiniciar.", 160, 110);
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
        fondoFin.dispose();

	}

}
