package puppy.code;
import com.badlogic.gdx.graphics.Texture;

// implememtación de builder del terremoto, nos basamos en el ppt
public class TerremotoFonderoBuilder implements TerremotoBuilder{
    private Terremoto producto;
    @Override
    public void reset(float x, float y, Texture textura){
        this.producto = new Terremoto(x, y, textura);
    }
    @Override
    public void configurarPuntos(){
        producto.setPuntosOtorgados(50);
    }
    @Override
    public void activarBrillo(){
        producto.setTieneEfectoResplandor(true);
    }
    @Override
    public void activarRecuperacionVida(){
        producto.setRecuperaVida(true);
    }
    @Override
    public Terremoto getResult(){
        return this.producto;
    }
}
