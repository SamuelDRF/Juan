package pt.uma.arq.entities;

//todo imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;
import java.awt.*;
import java.util.ArrayList;

public class PlayerShip extends Ship{
    ////////////////////////////////
    //todo attributes
    ////////////////////////////////
    public int score=0;

    ////////////////////////////////
    //todo constructor
    ////////////////////////////////

    //todo PlayerShip
    public PlayerShip(SpriteBatch batch){
        //animator------------------------------------------------------------------------------------------------------
        this.animator= new Animator(batch, "ship.png",5,2);
        animator.create();
        this.batch= batch;
        //position------------------------------------------------------------------------------------------------------
        this.x = 575 / 2;
        this.y = 30;
        //box-----------------------------------------------------------------------------------------------------------
        boundingBox = new Rectangle(x,y,animator.getWidth(),animator.getHeight());
        //stats---------------------------------------------------------------------------------------------------------
        this.firePower=25;
        this.health=50;
    }

    ////////////////////////////////
    //todo methods
    ////////////////////////////////

    //todo hadleInput
    public void  hadleInput(int points){
        //normal speed
        if (points>=0){
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                if (x > 0){
                    x-=5;
                }
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                if (x < 575) {
                    x+=5;
                }
            }
            //slow speed
            else if (Gdx.input.isKeyPressed(Input.Keys.A)){
                if (x > 0) {
                    x-=2;
                }
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.D)){
                if (x < 575) {
                    x+=2;
                }
            }
            setLocation(x,y);
        }
    }

    //todo shoot
    @Override
    public Laser shoot() {
        Laser laser = new Laser(batch, x, y + 20, firePower);
        return laser;
    }

    //todo checkCollided2
    //este checkCollided funciona so para os laser disparados pelas naves enemigasque batem no jogador
    public Ship checkCollided2(ArrayList<Laser> laserArray,int points) {

        for (Laser laser : laserArray) {
            if (boundingBox.contains(laser.getX()+10, laser.getY())) {
                if (laser.getY() < 400) {
                    laserArray.remove(laser);
                    setHealth(getHealth() - laser.getFirePower());
                    this.score+=points+10;
                    break;
                }
            }
        }
        return null;
    }

    //todo getScore
    public int getScore() {
        return score;
    }
}
