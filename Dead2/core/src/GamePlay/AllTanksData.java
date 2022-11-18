package GamePlay;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;


public class AllTanksData{
    private ArrayList<Tanks> TanksList ;
    private Tanks Abraham ;
    private Tanks Frost;
    public AllTanksData(){
        TanksList  = new ArrayList<>();
        Abraham = new Abraham() ;
        Frost = new Frost();
        TanksList.add(Abraham);
        TanksList.add(Frost);
    }
    public ArrayList<Tanks> getTanksList(){
        return TanksList;
    }
}

