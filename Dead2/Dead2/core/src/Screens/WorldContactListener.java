package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

import static Screens.oneVoneGameScreen.WeaponGroundHit;

public class WorldContactListener implements ContactListener{
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData()=="weapon" || fixB.getUserData()=="weapon"){
            Fixture weapon = fixA.getUserData()=="weapon"? fixA:fixB;
            Fixture object = weapon == fixA? fixB:fixA;

            if (object!=null && weapon!=null && object.getUserData()=="Ground"){
                try{
                   oneVoneGameScreen.WeaponGroundHit = true;
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }


            }
        }
    }


    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
