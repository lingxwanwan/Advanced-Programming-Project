package Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stars.game.MarioBros;

import static com.badlogic.gdx.Gdx.graphics;

public class oneVoneGameScreen implements Screen {
    MarioBros game;

    Texture image ;
    public oneVoneGameScreen(MarioBros game){
        this.game = game;

        image = new Texture("badlogic.jpg");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        graphics.setContinuousRendering(false);
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(image,0,0);
        game.batch.end();
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
