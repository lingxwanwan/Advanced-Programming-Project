package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stars.game.MarioBros;

import static com.badlogic.gdx.Gdx.graphics;
import static com.badlogic.gdx.utils.JsonValue.ValueType.object;

public class oneVoneGameScreen implements Screen {
    private  MarioBros game;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    private Viewport gamePort;
    private Stage stage;
    private World world;
    private Box2DDebugRenderer b2dr;

    public oneVoneGameScreen(MarioBros game){
        this.game = game;
        camera = new OrthographicCamera();
        gamePort = new FitViewport(1280,663,camera);
        stage = new Stage(gamePort,game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("World.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);
        camera.setToOrtho(false,gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2);
        camera.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        world = new World(new Vector2(0,0),true);
        b2dr = new Box2DDebugRenderer();
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        Body body;
        for(MapObject object : map.getLayers().get(1).getObjects()){

            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject)object).getRectangle();
                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+ rect.getHeight()/2);
                body = world.createBody(bdef);
                shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
                fdef.shape= shape;
                body.createFixture(fdef);

            }
            else if (object instanceof PolygonMapObject) {
                //shape = getPolygon((PolygonMapObject)object);
            }
            else if (object instanceof PolylineMapObject) {
                //shape = getPolyline((PolylineMapObject)object);
            }
            else if (object instanceof CircleMapObject) {
                //shape = getCircle((CircleMapObject)object);
            }
            else {
                continue;
            }
        }




        /*try{



            for (MapObject object : map.getLayers().get(2).getObjects()){

                PolygonShape polygon = new PolygonShape();
                float[] vertices = ((PolygonMapObject) object).getPolygon().getTransformedVertices();
                float[] worldvertices = new float[vertices.length];
                for (int i=0;i<vertices.length;++i){
                    worldvertices[i] = vertices[i]/100;
                }
                polygon.set(worldvertices);
                bdef.type = BodyDef.BodyType.StaticBody;
                body = world.createBody(bdef);
                fdef.shape = polygon;
                body.createFixture(polygon,1);
                polygon.dispose();

                PolygonShape polygon = new PolygonShape();
                float[] vertices = ((PolygonMapObject)object).getPolygon().getTransformedVertices();

                float[] worldVertices = new float[vertices.length];

                for (int i = 0; i < vertices.length; ++i) {
                    System.out.println(vertices[i]);
                    worldVertices[i] = vertices[i] / 100;

                }

                polygon.set(worldVertices);
                bdef.type = BodyDef.BodyType.StaticBody;
                body = world.createBody(bdef);
                fdef.shape = polygon;
                body.createFixture(fdef);


            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }*/






    }


    private static CircleShape getCircle(CircleMapObject circleObject) {
        System.out.println('e');
        Circle circle = circleObject.getCircle();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circle.radius / 100);
        circleShape.setPosition(new Vector2(circle.x / 100, circle.y / 100));
        return circleShape;
    }

    private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
        System.out.println('l');
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            System.out.println(vertices[i]);
            worldVertices[i] = vertices[i] /100;
        }

        polygon.set(worldVertices);
        return polygon;
    }

    private static ChainShape getPolyline(PolylineMapObject polylineObject) {
        System.out.println('o');
        float[] vertices = polylineObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];
        for (int i = 0; i < vertices.length / 2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2] / 100;
            worldVertices[i].y = vertices[i * 2 + 1] / 100;
        }

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }


    @Override
    public void show() {

    }

    public void update(float dt){

        world.step(1/60f,6,2);
        camera.update();
        renderer.setView(camera);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        renderer.render();
        game.batch.begin();
        //game.batch.draw(game.FirstPlayer.getTanks().tankImage,500,300,50,50);
        //game.batch.draw(game.SecondPlayer.getTanks().tankImage,800,300,50,50);
        game.batch.end();
        stage.draw();
        b2dr.render(world,camera.combined);
        update(delta);
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
