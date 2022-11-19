package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stars.game.MarioBros;
import com.badlogic.gdx.graphics.Camera;



public class MainMenu implements Screen{
    private final MarioBros game;
    private final Texture backgroundImage ;
    private final OrthographicCamera camera;
    private final TextureRegion BackgroundTexture;
    public MainMenu(MarioBros game){
        this.game = game;
        backgroundImage = new Texture("StartingImage.jpg");
        BackgroundTexture = new TextureRegion(backgroundImage, 0, 0,1240,1020);
        camera  = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

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

        game.batch.draw(BackgroundTexture, 0,0, 800, 480);
        game.font.draw(game.batch, "Click anywhere to begin!", 365, 140);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.ClickSound.play();
            game.setScreen(new HomeMenu(game));
            dispose();
        }
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
