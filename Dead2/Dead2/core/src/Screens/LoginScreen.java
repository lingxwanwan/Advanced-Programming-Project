package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stars.game.MarioBros;
import jdk.internal.loader.AbstractClassLoaderValue;

public class LoginScreen implements Screen {
    private MarioBros game;
    private Texture img ;
    private TextureRegion back;
    private OrthographicCamera camera;
    private Viewport gameport;
    private TextButton Submit;
    private Skin skin;
    private Stage stage;
    private Table table;
    private ImageButton backButton;
    public LoginScreen(MarioBros game){
        this.game = game;

        img  = new Texture("LoginPage.png");
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        table = new Table();

        back = new TextureRegion(img);
        camera = new OrthographicCamera();
        gameport = new FitViewport(1280,663,camera);
        stage = new Stage(gameport,game.batch);
        Submit = new TextButton("SUBMIT",skin,"small");
        table.add(Submit).width(400).height(100);
        table.setPosition(650,170);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        camera.setToOrtho(false, gameport.getWorldWidth(), gameport.getWorldHeight());


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(back,0,0,gameport.getWorldWidth(),gameport.getWorldHeight());
        game.batch.end();
        stage.draw();
        Submit.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.ClickSound.play();
                game.setScreen(new SelectModeScreen(game));
                dispose();
            }
        });


    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
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
        stage.dispose();
    }
}
