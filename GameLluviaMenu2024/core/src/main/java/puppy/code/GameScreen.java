package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private Tarro tarro;
	private Lluvia lluvia;

    // agregación de variedad de fondos
    private Texture fondoNormal;
    private Texture fondoViento;

    private Texture texturaCompleto;
    private Texture texturaArepa;
    private Texture texturaTerremoto;


	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.font.getData().setScale(1.2f, 1.2f);

        // carga de los distintos fondos
        fondoNormal = new Texture(Gdx.files.internal("fondo_dieciochero.png"));
        fondoViento = new Texture(Gdx.files.internal("fondo_volantines.png"));

		  // load the images for the droplet and the bucket, 64x64 pixels each
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
		  tarro = new Tarro(new Texture(Gdx.files.internal("bucket.png")),hurtSound);

	      // load the drop sound effect and the rain background "music"
         Texture texturaCompleto = new Texture(Gdx.files.internal("drop.png"));
         Texture texturaArepa = new Texture(Gdx.files.internal("dropBad.png"));
         Texture texturaTerremoto = new Texture(Gdx.files.internal("terremoto.png"));

         lluvia = new Lluvia(texturaCompleto, texturaArepa, texturaTerremoto);

	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();
	      // creacion del tarro
	      tarro.crear();

	      // creacion de la lluvia
	      lluvia.crear();
	}

	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

        // cambio de fondo cuando haya viento
        if (lluvia.isHayViento()){
            batch.draw(fondoViento, 0, 0, 800, 480);
            font.draw(batch, "¡MUCHO VIENTO!", 220, 440);
        }else{
            batch.draw(fondoNormal, 0, 0, 800, 480);
        }


		//dibujar textos
		font.draw(batch, "Completos: " + tarro.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
		font.draw(batch, "Récord : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);

		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();
			// caida de la lluvia
	       if (!lluvia.actualizarMovimiento(tarro)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<tarro.getPuntos())
	    		  game.setHigherScore(tarro.getPuntos());
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}

		tarro.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
	  lluvia.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		lluvia.pausar();
		game.setScreen(new PausaScreen(game, this));
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
      tarro.destruir();
      lluvia.destruir();
      fondoNormal.dispose();
      fondoViento.dispose();
      texturaCompleto.dispose();
      texturaArepa.dispose();
      texturaTerremoto.dispose();
      GestorAudio.getInstance().destruir();

	}

}
