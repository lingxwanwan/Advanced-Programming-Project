package Screens;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stars.game.MarioBros;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.math.Rectangle;
import com.sun.tools.javac.comp.Enter;
public class HomeMenu implements  Screen {
    private MarioBros game;
    private TextureAtlas atlas;
    private Texture backgroundImage;
    private final TextureRegion BackgroundTexture;
    private OrthographicCamera camera;
    private Stage stage;
    private Skin skin;
    private Table table;
    private Texture tankImage;
    private TextButton buttonPlay,buttonExit;
    private Label heading;



    public HomeMenu(MarioBros game){
        this.game = game;
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        stage = new Stage(new ScreenViewport());
        table = new Table();

        buttonPlay = new TextButton("LOGIN",skin,"small");
        buttonExit = new TextButton("EXIT",skin,"small");
        //buttonPlay.se
        table.add(buttonPlay).width(150).spaceBottom(50);
        table.row();
        table.add(buttonExit).width(150);
        table.setPosition(220,50);
        table.setFillParent(true);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        backgroundImage = new Texture("image1.jpg");
        BackgroundTexture = new TextureRegion(backgroundImage,0, 0,800,449);
        tankImage = new Texture("AbramsTank.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 449);

    }
    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0,0 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(BackgroundTexture, 0,0, 800, 449);
        game.batch.draw(tankImage,90,40,300,250);
        game.batch.end();
        stage.draw();
        buttonExit.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                game.ClickSound.play();
                Gdx.app.exit();
            }
        });
        buttonPlay.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y){
                game.ClickSound.play();
                game.setScreen(new SelectModeScreen(game));
                dispose();
            }
        });
    }

    @Override
    public void resize(int width, int height) {

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
