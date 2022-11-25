package GamePlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player extends Sprite {
    private Tanks tanks;
    private Vector2 velocity;

    private float speed = 100;
    private float gravity = 9.8f;
    private World world;
    private Sprite sprite;

    public Player(Tanks tanks){
        this.tanks = new Tanks(tanks.getName());
    }
    public void update (float delta){
        velocity.y -=gravity*delta;

        if (velocity.y>speed)
            velocity.y = speed;
        else if (velocity.y<speed)
            velocity.y = -speed;
        setX(getX()+velocity.x*delta);
        setY(getY()+velocity.y*delta);
    }

    public Tanks getTanks(){
        return tanks;
    }
}
