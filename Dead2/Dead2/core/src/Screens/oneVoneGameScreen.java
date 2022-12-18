package Screens;

import GamePlay.Weapon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stars.game.MarioBros;

public class oneVoneGameScreen implements Screen {
    private final MarioBros game;
    private final  TmxMapLoader mapLoader;
    private final TiledMap map;
    private final OrthographicCamera camera;
    private final OrthogonalTiledMapRenderer renderer;
    private final Viewport gamePort;
    private final Stage stage;
    private final World world;
    private final Box2DDebugRenderer b2dr;
    private final Texture PauseImage;
    private final Body bodyFirstPlayerTank;
    private final Body bodySecondPlayerTank;
    private Body body;
    private final Sprite sprite1;
    private final Sprite sprite2;
    private final TextButton FireButton;
    private final Skin skin;
    private final Table table;
    private   Boolean flagRight1 = true;
    private  Boolean flagLeft1 = false;
    private  Boolean flagFire = false;
    private Boolean FirstPlayerControlFlag = true;
    private Boolean SecondPlayerContrlFlag = false;
    public static Boolean WeaponGroundHit = false;
    private static Body weapon ;
    private static Fixture weaponfixture;

    public oneVoneGameScreen(MarioBros game){
        this.game = game;
        camera = game.factory.getOrthographicCamera();
        gamePort = game.factory.getFitViewport(camera);
        stage = game.factory.getStage((FitViewport) gamePort,game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("World.tmx");
        skin = game.factory.getSkin("skin/glassy-ui.json");

        FireButton = new TextButton("FIRE",skin,"small");
        table = game.factory.getTable();
        table.add(FireButton).width(200).height(100);
        table.setPosition(1100,300);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        PauseImage = game.factory.getTexture("pause.png");
        sprite1 = game.factory.getSprite(game.FirstPlayer.getTanks().tankImage);
        sprite2 = game.factory.getSprite(game.SecondPlayer.getTanks().tankImage);
        renderer = new OrthogonalTiledMapRenderer(map);
        camera.setToOrtho(false,gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2);
        camera.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        world = game.factory.getWorld();
        b2dr = game.factory.getBox2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        fdef.friction = 0.5f;

        bodyFirstPlayerTank = game.factory.getWorldBody("Tank",world,300,350);
        bodySecondPlayerTank = game.factory.getWorldBody("Tank",world,900,450);

        for(MapObject object : map.getLayers().get(1).getObjects()){

            Shape shape = getRectangle((RectangleMapObject)object);
            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            fdef.shape = shape;
            fdef.restitution = 0;
            Fixture fixture = body.createFixture(fdef);
            fixture.setUserData("Ground");
        }
        world.setContactListener(new WorldContactListener());
    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) ,
                (rectangle.y + rectangle.height * 0.5f ) );
        polygon.setAsBox(rectangle.width * 0.5f , rectangle.height * 0.5f , size, 0.0f);
        return polygon;
    }



    @Override
    public void show() {

    }

    public void update(float dt){
        world.step(1/60f,6,2);
        camera.update();
        renderer.setView(camera);
    }

    private  void createBody(){
        BodyDef bdefn = new BodyDef();
        bdefn.type = BodyDef.BodyType.DynamicBody;
        bdefn.position.set(bodyFirstPlayerTank.getPosition().x,bodyFirstPlayerTank.getPosition().y);
        weapon  = world.createBody(bdefn);
    }
    public void handleinput(float delta){
        CircleShape rect = new CircleShape();
        if (flagFire){
            //weapon   = game.factory.getWorldBody("Weapon",world,0,0);
            game.ClickSound.play();
            createBody();
            FixtureDef fd = new FixtureDef();
            fd.density = 5;
            fd.restitution = 0;

            //rect = new CircleShape();
            rect.setRadius(1);
            fd.shape = rect;
            weaponfixture= weapon.createFixture(fd);
            weaponfixture.setUserData("weapon");

            weapon.applyLinearImpulse(new Vector2(1000f,1000f),weapon.getWorldCenter(),true);
            flagFire = false;
        }
        if (weapon !=null && (weapon.getLinearVelocity().y==0)){
            System.out.println("true");
            weapon.destroyFixture(weapon.getFixtureList().get(0));
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && bodyFirstPlayerTank.getLinearVelocity().x<=10){
            System.out.println("hi");
            bodyFirstPlayerTank.applyLinearImpulse(new Vector2(25000f,0),bodyFirstPlayerTank.getWorldCenter(),true);
            if (!flagRight1){
                sprite1.flip(true,false);
                flagRight1 = true;
                flagLeft1 = false;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && bodyFirstPlayerTank.getLinearVelocity().x>=-10){
            bodyFirstPlayerTank.applyLinearImpulse(new Vector2(-25000f,0),bodyFirstPlayerTank.getWorldCenter(),true);
            if (!flagLeft1){
                sprite1.flip(true,false);
                flagRight1 = false;
                flagLeft1 = true;
            }
        }
        FireButton.addListener(new ClickListener(){
            public void clicked(InputEvent event , float x, float y){
                flagFire = true;
            }
        });
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        renderer.render();

        handleinput(delta);
        game.batch.begin();
        game.batch.draw(sprite1, game.FirstPlayer.update(bodyFirstPlayerTank).get(0), game.FirstPlayer.update(bodyFirstPlayerTank).get(1), 50, 50);
        game.batch.draw(sprite2,game.SecondPlayer.update(bodySecondPlayerTank).get(0),game.SecondPlayer.update(bodySecondPlayerTank).get(1), 50, 50);
        game.batch.draw(PauseImage, gamePort.getWorldWidth() - 70, gamePort.getWorldHeight() - 70, 50, 50);
        game.batch.end();
        b2dr.render(world, camera.combined);
        update(delta);
        if (WeaponGroundHit){
            WeaponGroundHit = false;
            WeaponGroundHit();
        }
        stage.draw();
    }
    private static void WeaponGroundHit(){
        try{
            weapon.destroyFixture(weaponfixture);
            System.out.println("Destroyed");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

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
        map.dispose();
        world.dispose();
        game.FirstPlayer.getTexture().dispose();
    }
}
