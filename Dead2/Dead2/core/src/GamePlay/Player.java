package GamePlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

interface  PlayerInterface {
    public Tanks getTanks();
    public Tanks setTanks(Tanks tanks);

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
    public Tanks setTanks(Tanks tanks) {
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
    public ArrayList<Float> update(Body body){
        ArrayList<Float> arr = new ArrayList<>();
        arr.add(body.getPosition().x-50/2);
        arr.add(body.getPosition().y-20);
        return arr;
    }
}
