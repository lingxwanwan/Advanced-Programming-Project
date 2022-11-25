package GamePlay;

import com.badlogic.gdx.graphics.Texture;

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
}

