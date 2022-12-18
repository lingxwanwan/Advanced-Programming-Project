package GamePlay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.nio.file.Watchable;

interface WeaponInter{
    public String getName();
    public void setName();

    public float getradiusOfImpact();
    public void setradiusOfImpact();

    public int getLevel();
    public void setLevel();

    public int getMaxLevel();
    public void setMaxLevel();

    public Texture getweaponImage();
    public void setweaponImage();

    public float getdamage();
    public void setdamage();
    public void fire();
}

public class Weapon implements  WeaponInter{
    private String Name;
    private float radiusOfImpact;
    private int Level;
    private int MaxLevel;
    private Texture weaponImage;
    private float damage;

    public Weapon(String Name,float radiusOfImpact,int level,int Maxlevel) {
        this.Name = Name;
        this.radiusOfImpact = radiusOfImpact;
        this.Level = level;
        this.MaxLevel = Maxlevel;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName() {

    }

    @Override
    public float getradiusOfImpact() {
        return 0;
    }

    @Override
    public void setradiusOfImpact() {

    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public void setLevel() {

    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public void setMaxLevel() {

    }

    @Override
    public Texture getweaponImage() {
        return null;
    }

    @Override
    public void setweaponImage() {

    }

    @Override
    public float getdamage() {
        return 0;
    }

    @Override
    public void setdamage() {

    }
    @Override
    public void fire(){

    }
    private static class ProjectileMotion{
        public  float gravity;
        public Vector2 InitialVelocity;
        public Vector2 CurrentLocation;

        public float getCurrentXPosition(float time) {
            return CurrentLocation.x + InitialVelocity.x*time;
        }
        public float getCurrentYPosition(float time) {
            return (float)0.5*gravity*time*time+InitialVelocity.y*time;
        }
    }
    private  static class Control{
        public float power = 50f;
        public float angle = 0f;

    }
    private static class Trajectory extends Actor {
        public  Control control;
        public  ProjectileMotion projectileMotion;
        public Sprite trajectoryImage;
        public float trajectoryPointCount = 30;
        public float timeSeparation = 1f;
        public Trajectory (Control control,float gravity,Sprite TrajectoryImage){
            this.control = control;
            this.trajectoryImage  = TrajectoryImage;
            this.projectileMotion = new ProjectileMotion();
            this.projectileMotion.gravity = gravity;
        }
        @Override
        public void act(float delta){
            super.act(delta);
            projectileMotion.InitialVelocity.set(control.power,0f);
            projectileMotion.InitialVelocity.rotate(control.angle);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            //super.draw(batch, parentAlpha);
            float t = 0f;
            float width = this.getWidth();
            float height = this.getHeight();

            float timeSepara  = this.timeSeparation;
            for (int i = 0;i <trajectoryPointCount; i++) {
                float x = this.getX() + projectileMotion.getCurrentXPosition(t);
                float y = this.getY() + projectileMotion.getCurrentYPosition(t);

                batch.draw(trajectoryImage, x, y, width, height);

                t += timeSeparation;
            }
        }


        @Override
        public Actor hit(float x, float y, boolean touchable) {
            return null;
        }

    }
}

