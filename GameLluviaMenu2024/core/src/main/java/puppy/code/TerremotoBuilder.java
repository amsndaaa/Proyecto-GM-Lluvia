package puppy.code;
import com.badlogic.gdx.graphics.Texture;

// interfaz para hacer el builder junto a lo necesario para crear terremoto, nos basamos en el ppt
public interface TerremotoBuilder{
    void reset(float x, float y, Texture textura);
    void configurarPuntos();
    void activarBrillo();
    void activarRecuperacionVida();
    Terremoto getResult();
}
