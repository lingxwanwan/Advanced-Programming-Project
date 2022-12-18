package Box2dObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Factory {
    private static Boolean FactoryObject = false;
    private Factory(){};
    public static Factory getInstance(){
        if (FactoryObject){
            return null;
        }
        else{
            FactoryObject = true;
            return new Factory();
        }
    }
    public Table getTable(){
        return new Table();
    }
    public Skin getSkin(String Path){
        return new Skin(Gdx.files.internal(Path));
    }
    public OrthographicCamera getOrthographicCamera(){
        return new OrthographicCamera();
    }
    public Stage getStage(FitViewport gamport, SpriteBatch batch){
        return new Stage(gamport,batch);
    }
    public Texture getTexture(String Path){
        return new Texture(Path);
    }
    public Sprite getSprite(Texture texture){
        return new Sprite(texture);
    }
    public FitViewport getFitViewport(OrthographicCamera camera){
        return new FitViewport(1280,663,camera);
    }
    public World getWorld(){
        return new World(new Vector2(0,-10),false);
    }
    public Box2DDebugRenderer getBox2DDebugRenderer(){
        return new Box2DDebugRenderer();
    }
    public BodyDef getBodyDef(){
        return new BodyDef();
    }
    public FixtureDef getFixtureDef(){
        return new FixtureDef();
    }
    public Body getWorldBody(String Name, World world,float x,float y){
        Body body = null;
        if(Name.equals("Tank")){
            BodyDef bd = getBodyDef();
            bd.type = BodyDef.BodyType.DynamicBody;
            bd.position.set(x,y);
            body = world.createBody(bd);

            FixtureDef fd = getFixtureDef();

            CircleShape circle = new CircleShape();
            circle.setRadius(10);
            fd.shape = circle;
            fd.friction= 5f;
            fd.restitution = 0;
            fd.density = 10f;
            Fixture fixture = body.createFixture(fd);
            fixture.setUserData("Tank");
        }

        return body;
    }
}
