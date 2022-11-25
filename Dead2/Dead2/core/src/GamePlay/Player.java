package GamePlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

interface  PlayerInterface {
    public Tanks getTanks();
    public Tanks setTanks();

    public void decHealth(float value);
    public void setHealth(float health);
    public float getHealth();

    public Boolean getWiningStatus();
    public void setWiningStatus(Boolean value);



}
public class Player extends Sprite implements PlayerInterface {
    private Tanks tanks;
    private float health;
    private Boolean WiningStatus;
    public Player(Tanks tanks){
        this.tanks = tanks;
    }

    public Tanks getTanks(){
        return tanks;
    }

    @Override
    public Tanks setTanks() {
        return null;
    }

    @Override
    public void decHealth(float value) {

    }

    @Override
    public void setHealth(float health) {

    }

    @Override
    public float getHealth() {
        return 0;
    }

    @Override
    public Boolean getWiningStatus() {
        return null;
    }

    @Override
    public void setWiningStatus(Boolean value) {

    }
}
