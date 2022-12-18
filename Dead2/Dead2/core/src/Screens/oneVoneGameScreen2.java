package Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stars.game.MarioBros;

public class oneVoneGameScreen2 extends ApplicationAdapter implements Screen  {
    private MarioBros game;
    private Body body;
    private World world;
    private float angle;
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr = new Box2DDebugRenderer();
    private Viewport gameport;
    private Texture BackgroundImg ;
    private Body body1;
    public oneVoneGameScreen2(MarioBros game) {

        this.game = game;


        world = new World(new Vector2(0,-20),false);
        camera = new OrthographicCamera();

        gameport = new FitViewport(1280,663,camera);
        camera.setToOrtho(false,gameport.getWorldWidth(),gameport.getWorldHeight());

        BackgroundImg = new Texture(Gdx.files.internal("Ground.jpg"));
        //BackGroundRegion = new TextureRegion(BackGroundRegion);




        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("World.json"));

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(0,0);
        Body bodyimp =world.createBody(bd);

        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.2f;
        //fd.restitution = ;

        loader.attachFixture(bodyimp,"Image1",fd,1f);

        BodyDef bdef1 = new BodyDef();
        bdef1.position.set(300,500);
        bdef1.type = BodyDef.BodyType.DynamicBody;
        body1 = world.createBody(bdef1);

        FixtureDef fd1 = new FixtureDef();
        CircleShape circle = new CircleShape();
        circle.setRadius(5);

        fd1.shape = circle;
        fd.density = 1;
        fd.friction = 0.5f;
        //fd.restitution = 10;

        body1.createFixture(fd1);


    }



    @Override
    public void show() {

    }

    public void handleinput(float delta){
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && body1.getLinearVelocity().x<=2){
            body1.applyLinearImpulse(new Vector2(100f,0),body1.getWorldCenter(),true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && body1.getLinearVelocity().x>=-2){
            body1.applyLinearImpulse(new Vector2(-100f,0),body1.getWorldCenter(),true);
        }
    }

    @Override
    public void render(float delta) {


        ScreenUtils.clear(1, 0, 0,0 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        world.step(1/60f,6,2);
        camera.update();
        handleinput( delta);

        game.batch.begin();
        game.batch.draw(BackgroundImg,0,0,gameport.getWorldWidth(),gameport.getWorldHeight());
        game.batch.draw(game.FirstPlayer.getTanks().tankImage,game.FirstPlayer.update(body1).get(0),game.FirstPlayer.update(body1).get(1),50,50);
        game.batch.end();
        b2dr.render(world,camera.combined);



        /*game.batch.begin();
        game.batch.draw(groundImage,0,0,gameport.getWorldWidth(),gameport.getWorldHeight());
        game.batch.end();*/





    }
    @Override
    public void create(){
        //physicsBodies = new PhysicsShapeCache("physics.xml");
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
