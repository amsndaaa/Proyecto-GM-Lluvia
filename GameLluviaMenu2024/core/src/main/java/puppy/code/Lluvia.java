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
    // implementaci´on de lista que alamacena completos y arepas
    private Array<Comestible> comestibles;
    private long lastDropTime;
    private Texture texturaCompleto;
    private Texture texturaArepa;
    private Texture texturaTerremoto;

    // atributos agregados por la incorporación del patrón Strategy
    private CaidaStrategy estrategiaActual;
    private CaidaStrategy caidaRecta;
    private CaidaStrategy caidaZigzag;

    // para manejo del viento
    private float tiempoGlobal = 0f;
    private float tiempoParaViento = 10f;
    private float duracionViento = 6f;
    private boolean hayViento = false;
    private boolean doradoLanzadoEnEstaRafaga = false;
    // atributos para implmnetar en patrón builder
    private TerremotoBuilder terremotoBuilder;
    private GarzonFondero garzonFondero;

    //acá hicimos cambios no recibiendo los sonidos por parámetro, sino que delegando a GestorAudio()
	public Lluvia(Texture texturaCompleto, Texture texturaArepa, Texture texturaTerremoto) {
		this.texturaCompleto = texturaCompleto;
		this.texturaArepa = texturaArepa;
        this.texturaTerremoto = texturaTerremoto;

        this.caidaRecta = new CaidaRecta();
        this.caidaZigzag = new CaidaZigZag();
        this.estrategiaActual = caidaRecta;
        this.terremotoBuilder = new TerremotoFonderoBuilder();
        this.garzonFondero = new GarzonFondero(terremotoBuilder);

	}

	public void crear() {
        comestibles = new Array<Comestible>();
		crearGotaDeLluvia();
	      // start the playback of the background music immediately (cambiaddo para ahora utilizar Singleton)
          GestorAudio.getInstance().reproducirMusica();
	}

	private void crearGotaDeLluvia() {
        float xAzar = MathUtils.random(0, 800 - 64);
        float yFijo = 480;

        // generar elemento dorado (completo especieal) si hay viento y aún no se ha generado
        if(hayViento && !doradoLanzadoEnEstaRafaga){
            doradoLanzadoEnEstaRafaga = true;
            Terremoto trago = garzonFondero.prepararTerremoto(xAzar, yFijo, texturaTerremoto);
            comestibles.add(trago);
            // 35% de probabilidad spawneo de arepas y 65% de completos
        }else if(MathUtils.random(1, 100) <= 35){
                comestibles.add(new Arepa(xAzar, yFijo, texturaArepa));
        }else{
            comestibles.add(new Completo(xAzar, yFijo, texturaCompleto));
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
        for (int i = 0; i < comestibles.size; i++) {
            Comestible item = comestibles.get(i);
            // Uso del patrón Strategy
            estrategiaActual.mover(item.getArea(), delta, tiempoGlobal);
            // Si cae al suelo
            if (item.getArea().y + 64 < 0) {
                comestibles.removeIndex(i);
                continue;
            }
            // Colisión con el tarro
            if (item.getArea().overlaps(tarro.getArea())) {
                // llamada al template method
                item.procesarColision(tarro);
                if (tarro.getVidas() <= 0) {
                    return false; // Game Over
                }
                comestibles.removeIndex(i);
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
            this.doradoLanzadoEnEstaRafaga = false;
            GestorAudio.getInstance().reproducirViento();
        }else{
            this.estrategiaActual = caidaRecta;
            GestorAudio.getInstance().detenerViento();
        }
   }

    public boolean isHayViento(){
        return hayViento;
    }

   public void actualizarDibujoLluvia(SpriteBatch batch) {

	  for (int i=0; i < comestibles.size; i++ ) {
          comestibles.get(i).dibujar(batch);
	   }
   }
   public void destruir(){
      // ahora esto lo dejamos delegado al GestorAudio
   }
   public void pausar(){
        GestorAudio.getInstance().pausarMusica();
   }
   public void continuar(){
       GestorAudio.getInstance().reproducirMusica();
   }

}
