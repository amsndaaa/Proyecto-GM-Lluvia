package puppy.code;
import com.badlogic.gdx.graphics.Texture;

// definimos los pasos de construcción del método builder, nos basamos en el ppt
public class GarzonFondero{
    private TerremotoBuilder builder;
    public GarzonFondero(TerremotoBuilder builder){
        this.builder = builder;
    }
    public void setBuilder(TerremotoBuilder builder){
        this.builder = builder;
    }
    // Método que ejecuta la secuencia de pasos ordenada
    public Terremoto prepararTerremoto(float x, float y, Texture textura){
        builder.reset(x, y, textura);
        builder.configurarPuntos();
        builder.activarBrillo();
        builder.activarRecuperacionVida();
        return builder.getResult();
    }
}
