package puppy.code;
import com.badlogic.gdx.math.Rectangle;
public class CaidaZigZag implements CaidaStrategy{
    private float velocidadY = 260f;
    private float balanceo = 180f;
    private float frecuencia = 4f;
    @Override
    public void mover(Rectangle posicion, float delta, float tiempo){
        posicion.y -= velocidadY * delta;
        posicion.x += (float) Math.sin(tiempo * frecuencia) * balanceo * delta;

        if (posicion.x < 0) posicion.x = 0;
        if (posicion.x > 800 - posicion.width) posicion.x = 800 - posicion.width;
    }
}
