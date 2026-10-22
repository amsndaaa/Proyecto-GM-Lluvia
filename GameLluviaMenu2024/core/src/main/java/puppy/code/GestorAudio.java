package puppy.code;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

// gestor único de audio para cumplir con diseño Singleton
public class GestorAudio{
    // instancia estática
    private static GestorAudio instancia;
    // atributos de los distintos audios presnets en el juego
    private Music musicaFondo;
    private Sound sonidoComerCompleto;
    private Sound sonidoHerido;

    private GestorAudio(){
        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        sonidoComerCompleto = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        sonidoHerido = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        musicaFondo.setLooping(true);
    }
    // método para acceder en todo el proyecto a esa única instancia
    public static GestorAudio getInstance(){
        if (instancia == null){
            instancia = new GestorAudio();
        }
        return instancia;
    }
    // distintos métodos a continuación para el control de audio

    public void reproducirMusica(){
        if (!musicaFondo.isPlaying()) {
            musicaFondo.play();
        }
    }
    public void pausarMusica(){
        if (musicaFondo.isPlaying()) {
            musicaFondo.pause();
        }
    }
    public void detenerMusica(){
        musicaFondo.stop();
    }
    // métodos a continuación para los disintos efectos de audio
    public void reproducirComerCompleto(){
        sonidoComerCompleto.play();
    }
    public void reproducirHerido() {
        sonidoHerido.play();
    }


    public void destruir() {
        musicaFondo.dispose();
        sonidoComerCompleto.dispose();
        sonidoHerido.dispose();
        instancia = null;
    }
}
