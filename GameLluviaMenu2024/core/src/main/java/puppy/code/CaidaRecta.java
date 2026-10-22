package puppy.code;
import com.badlogic.gdx.math.Rectangle;
public class CaidaRecta implements CaidaStrategy{
    private float velocidad = 300f;
    @Override
    public void mover(Rectangle posicion, float delta, float tiempo){
        posicion.y -= velocidad * delta;
    }
}
