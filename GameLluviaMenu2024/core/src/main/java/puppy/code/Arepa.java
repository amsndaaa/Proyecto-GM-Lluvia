package puppy.code;
import com.badlogic.gdx.graphics.Texture;

public class Arepa extends Comestible{
    public Arepa(float x, float y, Texture textura){
        super(x, y, textura);
    }
    @Override
    protected void aplicarEfecto(Tarro tarro){
        tarro.dañar();
    }
    @Override
    protected void reproducirSonido(){
        GestorAudio.getInstance().reproducirHerido();
    }
}
