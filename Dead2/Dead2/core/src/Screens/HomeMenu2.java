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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stars.game.MarioBros;

public class HomeMenu2 implements Screen {

    MarioBros game ;
    Texture BackgroundImg ;
    Stage stage;
    TextureRegion BackgroundImgTexture;
    Texture backImgPic;
    TextureRegion BackRegion;
    Drawable drawable ;
    ImageButton backButton;

    OrthographicCamera camera;
    public HomeMenu2(MarioBros game){
        this.game = game;
        stage = new Stage();
        BackgroundImg = new Texture("HomeMenu_2.jpg");
        BackgroundImgTexture = new TextureRegion(BackgroundImg,0,0,800,449);
        backImgPic = new Texture("BackButton_Img.png");
        BackRegion = new TextureRegion(backImgPic);
        drawable = new TextureRegionDrawable(new TextureRegion(BackRegion));
        backButton = new ImageButton(drawable);
        backButton.setPosition(0,400);
        backButton.setSize(50,50);
        stage.addActor(backButton);
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 449);
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
        game.batch.draw(BackgroundImgTexture,0,0,800,449);
        game.batch.end();
        stage.draw();
        backButton.addListener(new ClickListener(){
            public void clicked(InputEvent event ,float x, float y){
                game.setScreen(new HomeMenu(game));
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

    }
}
