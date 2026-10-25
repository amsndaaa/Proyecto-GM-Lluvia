package puppy.code;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// elemento del patrón builder
public class Terremoto extends Comestible{
    private int puntosOtorgados = 30;
    private boolean recuperaVida = false;
    private boolean tieneEfectoResplandor = false;
    public Terremoto(float x, float y, Texture textura){
        super(x, y, textura);
    }
    // Setters que utilizará el Builder para armar el trago
    public void setPuntosOtorgados(int puntos){
        this.puntosOtorgados = puntos;
    }
    public void setRecuperaVida(boolean recuperaVida){
        this.recuperaVida = recuperaVida;
    }
    public void setTieneEfectoResplandor(boolean tieneEfectoResplandor){
        this.tieneEfectoResplandor = tieneEfectoResplandor;
    }
    // Template Method (GM-8)
    @Override
    protected void aplicarEfecto(Tarro tarro){
        tarro.sumarPuntos(puntosOtorgados);
        if (recuperaVida) {
            tarro.sumarVida(); // ¡El trago cura al huaso!
        }
    }
    @Override
    protected void reproducirSonido(){
        GestorAudio.getInstance().reproducirComerCompleto();
    }
    @Override
    public void dibujar(SpriteBatch batch){
        if (tieneEfectoResplandor){
            batch.setColor(Color.WHITE);
            batch.draw(textura, area.x - 5, area.y - 5, 74, 74);
            // efecto de respnlandor/brillante para los terremotos que aparezcan
            batch.setBlendFunction(com.badlogic.gdx.graphics.GL20.GL_SRC_ALPHA, com.badlogic.gdx.graphics.GL20.GL_ONE);
            batch.setColor(1.0f, 0.85f, 0.1f, 0.7f);
            batch.draw(textura, area.x - 5, area.y - 5, 74, 74);
            batch.setBlendFunction(com.badlogic.gdx.graphics.GL20.GL_SRC_ALPHA, com.badlogic.gdx.graphics.GL20.GL_ONE_MINUS_SRC_ALPHA);
            batch.setColor(Color.WHITE);
        }else{
            super.dibujar(batch);
        }
    }
}
