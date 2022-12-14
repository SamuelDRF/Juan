package pt.uma.arq.entities;

//todo import
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;
import pt.uma.arq.game.Game;
import com.badlogic.gdx.Gdx;
import java.awt.*;
import java.util.ArrayList;

public class Laser {
    ////////////////////////////////
    //todo atributes
    ////////////////////////////////
    private Rectangle boundingBox;
    private  int x;
    private int y;
    private Animator animator;
    private int firePower;

    ////////////////////////////////
    //todo constructor
    ////////////////////////////////

    //todo Laser
    public Laser(SpriteBatch batch, int x, int y, int firePower){
        //animator------------------------------------------------------------------------------------------------------
        animator=new Animator(batch,"laser-bolts.png",2,2);
        //position------------------------------------------------------------------------------------------------------
        this.x=x;
        this.y=y;
        //stats---------------------------------------------------------------------------------------------------------
        this.firePower = firePower;
        //box-----------------------------------------------------------------------------------------------------------
        boundingBox = new Rectangle(x,y,animator.getWidth(),animator.getHeight());
    }
    ////////////////////////////////
    //todo methods
    ////////////////////////////////

    //todo create
    public  void create(){
        animator.create();
    }

    //todo render
    public void render(){
        animator.render(x,y);
    }

    //todo update
    //este update apaga os lasers que sao disparados pelo jogador
    public static void update(ArrayList<Laser> laserArray) {
        for (Laser laser : laserArray) {
            if (laser.getY() > 800) {
                laserArray.remove(laser);
                break;
            }
            laser.setY(laser.getY() + 5);

        }
    }

    //todo update2
    //este update apaga os lasers que sao disparados pelas naves enemigas
    public static void update2(ArrayList<Laser> laserArray) {
        for (Laser laser : laserArray) {
            if (laser.getY() < -20) {
                laserArray.remove(laser);
                break;
            }
            laser.setY(laser.getY() - 5);

        }
    }

    ////////////////////////////////
    //todo getters and setters
    ////////////////////////////////

    //todo getX
    public int getX() {
        return x;
    }

    //todo getY
    public int getY() {
        return y;
    }

    //todo setY
    public void setY(int y) {
        this.y = y;
    }

    //todo getFirePower
    public int getFirePower() {
        return firePower;
    }
}
