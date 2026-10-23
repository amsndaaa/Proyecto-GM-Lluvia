package puppy.code;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


// clase abstracta para los distintos ítems que caen en el juego
public abstract class Comestible{
    protected Rectangle area;
    protected Texture textura;

    public Comestible(float x, float y, Texture textura){
        this.area = new Rectangle();
        this.area.x = x;
        this.area.y = y;
        this.area.width = 64;
        this.area.height = 64;
        this.textura = textura;
    }

    // template method con lo que ocurre al chocar con el huaso
    public final void procesarColision(Tarro tarro) {
        aplicarEfecto(tarro);
        reproducirSonido();
    }
    // métodos abstractos para las subclases de completo y arepa
    protected abstract void aplicarEfecto(Tarro tarro);
    protected abstract void reproducirSonido();

    // otros métodos
    public void dibujar(SpriteBatch batch){
        batch.draw(textura, area.x, area.y);
    }
    public Rectangle getArea(){
        return area;
    }
}
