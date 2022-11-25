package GamePlay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import java.security.PublicKey;

public class Tanks extends Sprite {
    private String name;
    public float radiusOfDamage;
    public Texture tankImage;
    public World world;
    public Body b2body;
    public String path;
    public Tanks(String Name){
        this.name = Name;
    }
    public String getName(){
        return this.name;
    }
}
class Abraham extends Tanks{
    public Abraham(){
        super("Abraham");
        tankImage = new Texture("AbramsTank.png");
    }
}
class Frost extends Tanks{
    public Frost(){
        super("Frost");
        tankImage = new Texture("Frost.png");
    }
}
class Buratino extends Tanks{
    public Buratino(){
        super("Buratino");
        tankImage = new Texture("Buratino.png");
    }
}