package puppy.code;
import com.badlogic.gdx.graphics.Texture;

public class Completo extends Comestible{
    public Completo(float x, float y, Texture textura){
        super(x, y, textura);
    }
    @Override
    protected void aplicarEfecto(Tarro tarro){
        tarro.sumarPuntos(10);
    }
    @Override
    protected void reproducirSonido(){
        GestorAudio.getInstance().reproducirComerCompleto();
    }
}
