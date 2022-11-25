package Screens;

import GamePlay.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stars.game.MarioBros;
import sun.jvm.hotspot.utilities.BitMap;

import java.lang.reflect.Type;

import static com.badlogic.gdx.Gdx.graphics;

public class oneVone_Selection_Screen implements Screen {

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
    Boolean flagChoose;
    int chooseCount;

    TextButton ChooseButton ;

    OrthographicCamera camera;
    private Viewport gamePort;
    private Table table;
    public oneVone_Selection_Screen(MarioBros game){
        this.game = game;
        tankIndex = 0;
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        camera = new OrthographicCamera();
        gamePort = new FitViewport(1280,663,camera);
        camera.setToOrtho(false, gamePort.getWorldWidth(),gamePort.getWorldHeight());
        stage = new Stage(gamePort, game.batch);
        flag = false;
        flagBack = false;
        flagChoose = false;
        chooseCount = 0;
        table = new Table();

        BackgroundImg = new Texture("HomeMenu_2.jpg");
        BackgroundImgTexture = new TextureRegion(BackgroundImg);

        backImgPic = new Texture("BackButton_Img.png");
        BackRegion = new TextureRegion(backImgPic);
        drawable = new TextureRegionDrawable(new TextureRegion(BackRegion));
        backButton = new ImageButton(drawable);
        backButton.setPosition(1,gamePort.getWorldHeight()-70);
        backButton.setSize(50,50);

        changeTankPic = new Texture("ChangeTankImage.png");
        changeTankRegion = new TextureRegion(changeTankPic);
        drawableChangeTank = new TextureRegionDrawable(new TextureRegion(changeTankRegion));
        changeTank = new ImageButton(drawableChangeTank);
        /*changeTank.setPosition(720,270);
        changeTank.setSize(50,50);*/


        backTankPic = new Texture("backTankImage.png");
        backTankRegion = new TextureRegion(backTankPic);
        drawablebackTank = new TextureRegionDrawable(new TextureRegion(backTankRegion));
        backTank = new ImageButton(drawablebackTank);
        //backTank.setPosition(470,270);
        //backTank.setSize(50,50);



        ChooseButton = new TextButton("CHOOSE",skin,"small");
        ChooseButton.setWidth(300);
        ChooseButton.setHeight(50);
        //ChooseButton.setPosition(470,150);

        table.add(backTank).width(50).height(50).spaceRight(280).spaceBottom(80);
        table.add(changeTank).width(50).height(50).space(0,0,60,0);

        table.row();
        table.add();
        table.row();
        table.add(ChooseButton).width(300).spaceTop(70);

        table.setPosition(350,30);
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
        ScreenUtils.clear(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.batch.draw(BackgroundImgTexture,0,0,gamePort.getWorldWidth(),gamePort.getWorldHeight());
        game.font.draw(game.batch, "Select tank for Player1 and Player2", 850, 200);
        int imx = 850;
        int imy = 300;
        if (flag){
            try{
                game.batch.draw(game.tanks.getTanksList().get(++tankIndex).tankImage,imx,imy,imy,imy);
                flag = false;
            }
            catch (Exception e){
                game.batch.draw(game.tanks.getTanksList().get(--tankIndex).tankImage,imx,imy,imy,imy);
                flag = false;
            }
        }
        else if (flagBack){
            try{
                game.batch.draw(game.tanks.getTanksList().get(--tankIndex).tankImage,imx,imy,imy,imy);

                flagBack = false;
            }
            catch (Exception e){
                game.batch.draw(game.tanks.getTanksList().get(++tankIndex).tankImage,imx,imy,imy,imy);
                flagBack = false;
            }
        }
        else{
            game.batch.draw(game.tanks.getTanksList().get(tankIndex).tankImage,imx,imy,imy,imy);
            flag = false;
            flagBack = false;
        }

        game.batch.end();
        stage.draw();

        if (flagChoose){
            Sprite  sprite;

            sprite = new Sprite(game.tanks.getTanksList().get(tankIndex).tankImage);
            sprite.setSize(50,50);
            sprite.setPosition(500,600);
            if (++chooseCount==1){
                game.FirstPlayer = new Player(game.tanks.getTanksList().get(tankIndex));
            }
            else if (chooseCount==2){
                game.SecondPlayer = new Player(game.tanks.getTanksList().get(tankIndex));
                game.setScreen(new oneVoneGameScreen(game));
            }
            flagChoose = false;
        }

        handleinput(delta);
    }
    void handleinput(float del){
        backButton.addListener(new ClickListener(){
            public void clicked(InputEvent event , float x, float y){
                game.ClickSound.play();
                game.setScreen(new SelectModeScreen(game));
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
        ChooseButton.addListener(new ClickListener(){
            public void clicked (InputEvent event ,float x,float y){
                game.ClickSound.play();
                flagChoose = true;
            }
        });

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
