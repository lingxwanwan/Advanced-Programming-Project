package GamePlay;

import com.badlogic.gdx.graphics.Texture;

import java.security.PublicKey;

public class Tanks {
    private String name;
    public float radiusOfDamage;
    public Texture tankImage;
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