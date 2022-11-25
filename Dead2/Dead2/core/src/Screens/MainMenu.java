package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stars.game.MarioBros;
import com.badlogic.gdx.graphics.Camera;



public class MainMenu implements Screen{
    private final MarioBros game;
    private final Texture backgroundImage ;
    private final OrthographicCamera camera;
    private final TextureRegion BackgroundTexture;
    private Viewport gameport;

    public MainMenu(MarioBros game){
        this.game = game;

        backgroundImage = new Texture("StartingImage.jpg");
        camera  = new OrthographicCamera();
        BackgroundTexture = new TextureRegion(backgroundImage);
        gameport = new FitViewport(1280,663,camera);

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

        game.batch.draw(BackgroundTexture, 0,0,gameport.getWorldWidth(),gameport.getWorldHeight());
        game.font.draw(game.batch, "Click anywhere to begin!", 615, 440);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.ClickSound.play();
            game.setScreen(new HomeMenu(game));
            dispose();
        }
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
