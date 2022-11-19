package GamePlay;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;


public class AllTanksData{
    private ArrayList<Tanks> TanksList ;
    private Tanks Abraham ;
    private Tanks Frost;
    private Tanks Buratino;
    public AllTanksData(){
        TanksList  = new ArrayList<>();
        Abraham = new Abraham() ;
        Frost = new Frost();
        Buratino = new Buratino();
        TanksList.add(Abraham);
        TanksList.add(Frost);
        TanksList.add(Buratino);
    }
    public ArrayList<Tanks> getTanksList(){
        return TanksList;
    }
}

