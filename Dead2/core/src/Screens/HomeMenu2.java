package Screens;

import com.badlogic.gdx.ApplicationListener;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stars.game.MarioBros;
import com.sun.org.apache.xerces.internal.xinclude.XIncludeTextReader;

import static com.badlogic.gdx.Gdx.graphics;

public class HomeMenu2 implements Screen {

    MarioBros game ;
    int tankIndex ;
    Skin skin ;

    Texture BackgroundImg ;
    Stage stage;
    TextureRegion BackgroundImgTexture;
    Texture backImgPic;
    TextureRegion BackRegion;
    Drawable drawable ;
    ImageButton backButton;

    Texture changeTankPic;
    Drawable drawableChangeTank;
    TextureRegion changeTankRegion;
    ImageButton changeTank;

    Texture backTankPic;
    Drawable drawablebackTank;
    TextureRegion backTankRegion;
    ImageButton backTank;
    Boolean flag;
    Boolean flagBack;

    TextButton ChooseButton ;

    OrthographicCamera camera;

    public HomeMenu2(MarioBros game){
        this.game = game;
        tankIndex = 0;
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        flag = false;
        flagBack = false;
        stage = new Stage();
        BackgroundImg = new Texture("HomeMenu_2.jpg");
        BackgroundImgTexture = new TextureRegion(BackgroundImg,0,0,800,449);

        backImgPic = new Texture("BackButton_Img.png");
        BackRegion = new TextureRegion(backImgPic);
        drawable = new TextureRegionDrawable(new TextureRegion(BackRegion));
        backButton = new ImageButton(drawable);
        backButton.setPosition(1,380);
        backButton.setSize(50,50);

        changeTankPic = new Texture("ChangeTankImage.png");
        changeTankRegion = new TextureRegion(changeTankPic);
        drawableChangeTank = new TextureRegionDrawable(new TextureRegion(changeTankRegion));
        changeTank = new ImageButton(drawableChangeTank);
        changeTank.setPosition(720,270);
        changeTank.setSize(50,50);

        backTankPic = new Texture("backTankImage.png");
        backTankRegion = new TextureRegion(backTankPic);
        drawablebackTank = new TextureRegionDrawable(new TextureRegion(backTankRegion));
        backTank = new ImageButton(drawablebackTank);
        backTank.setPosition(470,270);
        backTank.setSize(50,50);

        ChooseButton = new TextButton("CHOOSE",skin,"default");
        ChooseButton.setWidth(300);
        ChooseButton.setHeight(120);
        ChooseButton.setPosition(470,80);

        stage.addActor(backButton);
        stage.addActor(changeTank);
        stage.addActor(backTank);
        stage.addActor(ChooseButton);
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 449);
        graphics.setContinuousRendering(false);


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        graphics.setContinuousRendering(false);
        ScreenUtils.clear(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(BackgroundImgTexture,0,0,800,449);

        if (flag){
            try{
                game.batch.draw(game.tanks.getTanksList().get(++tankIndex).tankImage,520,200,200,200);
                flag = false;
            }
            catch (Exception e){
                game.batch.draw(game.tanks.getTanksList().get(--tankIndex).tankImage,520,200,200,200);
                flag = false;
            }
        }
        else if (flagBack){
            try{
                game.batch.draw(game.tanks.getTanksList().get(--tankIndex).tankImage,520,200,200,200);
                flagBack = false;
            }
            catch (Exception e){
                game.batch.draw(game.tanks.getTanksList().get(++tankIndex).tankImage,520,200,200,200);
                flagBack = false;
            }
        }
        else{
            game.batch.draw(game.tanks.getTanksList().get(tankIndex).tankImage,520,200,200,200);
            flag = false;
            flagBack = false;
        }
        game.batch.end();
        stage.draw();
        handleinput(delta);
    }
    void handleinput(float del){
        backButton.addListener(new ClickListener(){
            public void clicked(InputEvent event ,float x, float y){
                game.ClickSound.play();
                game.setScreen(new HomeMenu(game));
                dispose();
            }
        });
        changeTank.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event ,float x,float y){
                game.ClickSound.play();
                flag  = true;

                //Gdx.graphics.requestRendering();
            }
        });
        backTank.addListener(new ClickListener(){
            public void clicked(InputEvent event ,float x,float y){
                game.ClickSound.play();
                flagBack  = true;
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
