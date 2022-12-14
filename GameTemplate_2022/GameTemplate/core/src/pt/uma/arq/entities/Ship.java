package pt.uma.arq.entities;

//todo imports
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;
import java.awt.*;

public abstract class Ship {
    ////////////////////////////////
    //todo attributes
    ////////////////////////////////

    protected Animator animator;
    protected int x, y;
    protected Rectangle boundingBox;
    protected SpriteBatch batch;

    protected int firePower;
    protected int health;
    protected Boolean collided;

    ////////////////////////////////
    //todo methods
    ////////////////////////////////

    //todo shoot
    public abstract Laser shoot();


    //todo render
    public void render(){
        animator.render(x,y);
    }




    ////////////////////////////////
    //todo getter and setter
    ////////////////////////////////

    //todo setHealth
    public void setHealth(int health) {
        this.health = health;
    }

    //todo getHealth
    public int getHealth() {
        return health;
    }

    //todo setLocation
    public void setLocation(int x, int y){
        this.x=x;
        this.y=y;
        this.boundingBox.setLocation(x,y);
    }
}
