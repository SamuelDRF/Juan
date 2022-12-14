package pt.uma.arq.entities;

//todo imports
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;
import java.awt.*;

public class MediumShip extends Ship{

    //todo MediumShip
    public MediumShip(SpriteBatch batch, int x, int y){
        //animator------------------------------------------------------------------------------------------------------
        this.animator= new Animator(batch, "enemy-medium.png",2,1);
        animator.create();
        this.batch= batch;
        //position------------------------------------------------------------------------------------------------------
        this.x = x;
        this.y = y;
        //box-----------------------------------------------------------------------------------------------------------
        this.boundingBox = new Rectangle(x-10,y,animator.getWidth(),animator.getHeight());
        this.collided=false;
        //stats---------------------------------------------------------------------------------------------------------
        this.firePower=20;
        this.health = 60;
    }

    //todo shoot
    @Override
    public Laser shoot() {
        Laser laser = new Laser(batch, x, y - 20, firePower);
        return laser;
    }
}
