package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;



public class PausaScreen implements Screen {

	private final GameLluviaMenu game;
	private GameScreen juego;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
    private Texture fondoPausa;

	public PausaScreen (final GameLluviaMenu game, GameScreen juego) {
		this.game = game;
        this.juego = juego;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
        fondoPausa = new Texture(Gdx.files.internal("fondo_dieciochero.png"));
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 1.0f, 0.5f);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
        batch.setColor(0.4f, 0.4f, 0.4f, 0.85f);
        batch.draw(fondoPausa, 0, 0, 800, 480);
        batch.setColor(Color.WHITE);
		font.draw(batch, "Juego en Pausa ", 100, 150);
		font.draw(batch, "Presiona ESC o toca en cualquier lado para continuar !!!", 100, 100);
		batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            font.getData().setScale(1.0f);
            game.setScreen(juego);
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
        fondoPausa.dispose();

	}

}

