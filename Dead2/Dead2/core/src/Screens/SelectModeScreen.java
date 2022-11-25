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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stars.game.MarioBros;

public class SelectModeScreen implements Screen {
    private MarioBros game;
    Skin skin;
    Table table;
    Stage stage;
    Texture backGroundImage;
    TextureRegion backGroundTexture;

    TextButton oneVone;
    TextButton oneVComputer;
    OrthographicCamera camera ;
    Texture backImgPic;
    TextureRegion BackRegion;
    Drawable drawable ;
    ImageButton backButton;
    Viewport gamePort;

    public SelectModeScreen(MarioBros game){
        this.game = game;
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        backGroundImage = new Texture("HomeMenu_2.jpg");
        backGroundTexture = new TextureRegion(backGroundImage);
        oneVone = new TextButton("1 V 1",skin,"small");
        oneVComputer  = new TextButton("Computer",skin,"small");
        camera = new OrthographicCamera();
        gamePort = new FitViewport(1280,663,camera);
        camera.setToOrtho(false, gamePort.getWorldWidth(),gamePort.getWorldHeight());
        stage = new Stage(gamePort,game.batch);
        table = new Table();

        backImgPic = new Texture("BackButton_Img.png");
        BackRegion = new TextureRegion(backImgPic);
        drawable = new TextureRegionDrawable(new TextureRegion(BackRegion));
        backButton = new ImageButton(drawable);
        backButton.setPosition(1,gamePort.getWorldHeight()-70);
        backButton.setSize(50,50);

        table.add(oneVone).width(300).spaceBottom(50);
        table.row();
        table.add(oneVComputer).width(300);
        table.setPosition(320,50);
        table.setFillParent(true);
        stage.addActor(table);
        stage.addActor(backButton);
        Gdx.input.setInputProcessor(stage);

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
        game.batch.draw(backGroundTexture, 0,0, gamePort.getWorldWidth(),gamePort.getWorldHeight());
        game.batch.end();
        stage.draw();
        backButton.addListener(new ClickListener(){
            public void clicked(InputEvent event , float x, float y){
                game.ClickSound.play();
                game.setScreen(new HomeMenu(game));
                dispose();
            }
        });
        oneVone.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                game.ClickSound.play();
                game.setScreen(new oneVone_Selection_Screen(game));
                dispose();
            }
        });
        /*oneVComputer.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y){
                game.ClickSound.play();
                dispose();
            }
        });*/
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

