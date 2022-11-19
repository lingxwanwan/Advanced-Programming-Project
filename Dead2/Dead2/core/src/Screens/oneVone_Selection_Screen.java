package Screens;

import GamePlay.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stars.game.MarioBros;
import sun.jvm.hotspot.utilities.BitMap;

import static com.badlogic.gdx.Gdx.graphics;

public class oneVone_Selection_Screen implements Screen {

    MarioBros game ;
    int tankIndex ;
    Skin skin ;

    Label.LabelStyle labelStyle;
    Label popUpMessage;
    BitmapFont myFont;

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

    public oneVone_Selection_Screen(MarioBros game){
        this.game = game;
        tankIndex = 0;
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        //labelStyle = new Label.LabelStyle();
        //myFont = new BitmapFont(Gdx.files.internal("skin/font-big.fnt"));
        ////labelStyle.font = myFont;
        //labelStyle.fontColor = Color.WHITE;

        /*popUpMessage = new Label("SELECT TANK FOR FIRST PLAYER",labelStyle);
        popUpMessage.setSize(50,50);
        popUpMessage.setPosition(0,50);
        popUpMessage.setAlignment(Align.bottomRight);
        stage.addActor(popUpMessage);*/


        flag = false;
        flagBack = false;
        flagChoose = false;
        chooseCount = 0;


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

        ChooseButton = new TextButton("CHOOSE",skin,"small");
        ChooseButton.setWidth(300);
        ChooseButton.setHeight(50);
        ChooseButton.setPosition(470,150);

        stage.addActor(backButton);
        stage.addActor(changeTank);
        stage.addActor(backTank);
        stage.addActor(ChooseButton);
        stage.addActor(backButton);
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
        if (flagChoose){
            if (++chooseCount==1){
                game.FirstPlayer = new Player(game.tanks.getTanksList().get(tankIndex));
            }
            else if (chooseCount==2){
                game.SecondPlayer = new Player(game.tanks.getTanksList().get(tankIndex));
                game.setScreen(new oneVoneGameScreen(game));
            }
            System.out.println(chooseCount);
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
