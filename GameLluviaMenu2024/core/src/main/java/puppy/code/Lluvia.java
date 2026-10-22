package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	private Array<Rectangle> rainDropsPos;
	private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Texture texturaCompleto;
    private Texture texturaArepa;
    private Sound dropSound;
    private Music rainMusic;

    // atributos agregados por la incorporación del patrón Strategy
    private CaidaStrategy estrategiaActual;
    private CaidaStrategy caidaRecta;
    private CaidaStrategy caidaZigzag;

    // para manejo del viento
    private float tiempoGlobal = 0f;
    private float tiempoParaViento = 10f;
    private float duracionViento = 6f;
    private boolean hayViento = false;

	public Lluvia(Texture texturaCompleto, Texture texturaArepa, Sound ss, Music mm) {
		this.texturaCompleto = texturaCompleto;
		this.texturaArepa = texturaArepa;
        this.dropSound = ss;
        this.rainMusic = mm;

        this.caidaRecta = new CaidaRecta();
        this.caidaZigzag = new CaidaZigZag();
        this.estrategiaActual = caidaRecta;

	}

	public void crear() {
		rainDropsPos = new Array<Rectangle>();
		rainDropsType = new Array<Integer>();
		crearGotaDeLluvia();
	      // start the playback of the background music immediately
	      rainMusic.setLooping(true);
	      rainMusic.play();
	}

	private void crearGotaDeLluvia() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        rainDropsPos.add(raindrop);
        // 35% de probabilidad spawneo de arepas y 65% de completos
        if (MathUtils.random(1, 100) <= 35){
            rainDropsType.add(1);
        }else{
            rainDropsType.add(2);
        }
        lastDropTime = TimeUtils.nanoTime();

    }

    public boolean actualizarMovimiento(Tarro tarro) {
        float delta = Gdx.graphics.getDeltaTime();
        tiempoGlobal += delta;
        // Alternar viento cada cierto tiempo
        actualizarViento(delta);
        // Crear nuevos elementos cada 0.35 segundos
        if (TimeUtils.nanoTime() - lastDropTime > 350000000) {
            crearGotaDeLluvia();
        }
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            // Uso del patrón Strategy
            estrategiaActual.mover(raindrop, delta, tiempoGlobal);
            // Si cae al suelo
            if (raindrop.y + 64 < 0) {
                rainDropsPos.removeIndex(i);
                rainDropsType.removeIndex(i);
                continue;
            }
            // Colisión con el tarro
            if (raindrop.overlaps(tarro.getArea())) {
                if (rainDropsType.get(i) == 1) { // Arepa
                    tarro.dañar();
                    if (tarro.getVidas() <= 0) {
                        return false; // Game Over
                    }
                } else { // Completo
                    tarro.sumarPuntos(10);
                    dropSound.play();
                }
                rainDropsPos.removeIndex(i);
                rainDropsType.removeIndex(i);
            }
        }
        return true;
    }
    private void actualizarViento(float delta){
        if (!hayViento) {
            tiempoParaViento -= delta;
            if (tiempoParaViento <= 0){
                setViento(true);
                duracionViento = 6f;
            }
        } else {
            duracionViento -= delta;
            if (duracionViento <= 0){
                setViento(false);
                tiempoParaViento = MathUtils.random(8f, 14f);
            }
        }
   }

   public void setViento(boolean activar){
        this.hayViento = activar;
        if (activar){
            this.estrategiaActual = caidaZigzag;
        }else{
            this.estrategiaActual = caidaRecta;
        }
   }

    public boolean isHayViento(){
        return hayViento;
    }

   public void actualizarDibujoLluvia(SpriteBatch batch) {

	  for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle raindrop = rainDropsPos.get(i);
		  if(rainDropsType.get(i)==1) // gota dañina
	         batch.draw(texturaArepa, raindrop.x, raindrop.y);
		  else
			 batch.draw(texturaCompleto, raindrop.x, raindrop.y);
	   }
   }
   public void destruir() {
      dropSound.dispose();
      rainMusic.dispose();
   }
   public void pausar() {
	  rainMusic.stop();
   }
   public void continuar() {
	  rainMusic.play();
   }

}
