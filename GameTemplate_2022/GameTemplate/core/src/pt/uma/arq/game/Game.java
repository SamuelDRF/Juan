package pt.uma.arq.game;

//todo imports
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.entities.Fleet;
import pt.uma.arq.entities.Laser;
import pt.uma.arq.entities.PlayerShip;

import java.lang.constant.Constable;
import java.util.*;

import com.badlogic.gdx.Input;
import pt.uma.arq.entities.Ship;

public class Game extends ApplicationAdapter {
    //todo atributes
    private SpriteBatch batch;
    private BackgroundManagement backgroundManagement;
    private BitmapFont font;
    private PlayerShip playerShip;
    private ArrayList<Laser> laserArray;

    //Nos-----------------------------------------
    private Fleet fleet;
    private ArrayList<Laser> laserArray2;
    private int points=-1;
    private int language=0;
    private int score;
    private int loose=0;
    private int dificulty=0;
    private Timer timer;
    private int random;
    private int memory;

    //todo create
    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(600, 800);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("gamefont.fnt"),
                Gdx.files.internal("gamefont.png"), false);
        backgroundManagement = new BackgroundManagement(batch);
        playerShip= new PlayerShip(batch);

        //Nosotros-----------------------------------------
        laserArray = new ArrayList<>();
        laserArray2 = new ArrayList<>();
        fleet = new Fleet(batch);

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                random=(int) (Math.random() * Fleet.getArrayListShipSize());
            }
        };
        timer=new Timer("disparo de los panas");
        timer.schedule(tt,500,500);

    }

    //todo Render
    @Override
    public void render() {
        score=fleet.getScore();
        score-=playerShip.getScore();
        if (playerShip.getScore()>=50){
            loose=1;
        }


        //Calendar(enemy shoot)


        //IDK
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        //verificar se o fleet esta vazio ou se o jogador perdeu
        if (fleet.isEmpy()|| loose==1){
            backgroundManagement.render();
            if (language==0){
                if (loose==1)
                    font.draw(batch,"GAME OVER",Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()/2+50);
                else
                    font.draw(batch,"YOU WIN!!!",Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()/2+50);
                font.draw(batch,"score: "+score,Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()/2);
            }
            else if (language==1){
                if (loose==1)
                    font.draw(batch,"FIN DEL JUEGO",Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()/2+50);
                else
                    font.draw(batch,"GANASTE!!!",Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()/2+50);
                font.draw(batch,"puntuacion: "+score,Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()/2);
            }
            else if (language==2){
                if (loose==1)
                    font.draw(batch,"FIM DO JOGO",Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()/2+50);
                else
                    font.draw(batch,"GANHOU!!!",Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()/2+50);
                font.draw(batch,"pontuacao: "+score,Gdx.graphics.getWidth()/2-50,Gdx.graphics.getHeight()/2);
            }
        }
        else {
            //O jogo em funcionamento
            backgroundManagement.render();
            playerShip.hadleInput(points);

            //menu de inicio
            if (points < 0) {
                if (language==0){
                    font.draw(batch,"Use 'A' and 'D' to move slow",10,Gdx.graphics.getHeight()/2+50);
                    font.draw(batch,"Use '<' and '>' to move fast",10,Gdx.graphics.getHeight()/2);
                    font.draw(batch,"Use 'Space' to shoot",10,Gdx.graphics.getHeight()/2-50);
                    font.draw(batch,"PRESS 'Space' TO START",160,Gdx.graphics.getHeight()/2-150);
                    font.draw(batch,"press 'L' to change language",10,25);
                    font.draw(batch,"press 'D' to change difficulty",10,65);
                    switch (dificulty){
                        case 0:
                            font.draw(batch,"Easy",270,Gdx.graphics.getHeight()-20);
                            break;
                        case 1:
                            font.draw(batch,"Normal",270,Gdx.graphics.getHeight()-20);
                            break;
                        case 2:
                            font.draw(batch,"Hard",270,Gdx.graphics.getHeight()-20);
                            break;
                        case 3:
                            font.draw(batch,"Legend",270,Gdx.graphics.getHeight()-20);
                            break;
                    }
                }
                else if (language==1){
                    font.draw(batch,"Usa 'A' y 'D' para moverte lento",10,Gdx.graphics.getHeight()/2+50);
                    font.draw(batch,"Usa '<' y '>' para moverte rapido",10,Gdx.graphics.getHeight()/2);
                    font.draw(batch,"Usa 'Espacio' para disparar",10,Gdx.graphics.getHeight()/2-50);
                    font.draw(batch,"PRECIONA 'Espacio' PARA EMPEZAR",80,Gdx.graphics.getHeight()/2-150);
                    font.draw(batch,"presiona 'L' para cambiar el idioma",10,25);
                    font.draw(batch,"presiona 'D' para cambiar la dificultad",10,65);
                    switch (dificulty){
                        case 0:
                            font.draw(batch,"Facil",270,Gdx.graphics.getHeight()-20);
                            break;
                        case 1:
                            font.draw(batch,"Normal",270,Gdx.graphics.getHeight()-20);
                            break;
                        case 2:
                            font.draw(batch,"Dificil",270,Gdx.graphics.getHeight()-20);
                            break;
                        case 3:
                            font.draw(batch,"Leyenda",270,Gdx.graphics.getHeight()-20);
                            break;
                    }
                }
                else if(language==2){
                    font.draw(batch,"Use 'A' e 'D' para mover-se devagar",10,Gdx.graphics.getHeight()/2+50);
                    font.draw(batch,"Use '<' e '>' para mover-se rapido",10,Gdx.graphics.getHeight()/2);
                    font.draw(batch,"Use 'Espaco' para atirar",10,Gdx.graphics.getHeight()/2-50);
                    font.draw(batch,"PRESSIONE 'Espaco' PARA COMECAR",80,Gdx.graphics.getHeight()/2-150);
                    font.draw(batch,"pressione 'L' para mudar de idioma",10,25);
                    font.draw(batch,"pressione 'D' para mudar de dificultade",10,65);
                    switch (dificulty){
                        case 0:
                            font.draw(batch,"Facil",270,Gdx.graphics.getHeight()-20);
                            break;
                        case 1:
                            font.draw(batch,"Normal",270,Gdx.graphics.getHeight()-20);
                            break;
                        case 2:
                            font.draw(batch,"Dificil",270,Gdx.graphics.getHeight()-20);
                            break;
                        case 3:
                            font.draw(batch,"Lenda",270,Gdx.graphics.getHeight()-20);
                            break;
                    }

                }
            }
            else
                playerShip.render();

            //Informação para o jugador
            if (language==0){
                font.draw(batch,"Score: "+score,10,Gdx.graphics.getHeight()-20);
                font.draw(batch,"Lives: "+(5-playerShip.getScore()/10),500,Gdx.graphics.getHeight()-20);
            }
            else if (language==1){
                font.draw(batch,"Puntuacion: "+score,10,Gdx.graphics.getHeight()-20);
                font.draw(batch,"Vidas: "+(5-playerShip.getScore()/10),490,Gdx.graphics.getHeight()-20);
            }
            else if(language==2){
                font.draw(batch,"Pontuacao: "+score,10,Gdx.graphics.getHeight()-20);
                font.draw(batch,"Vidas: "+(5-playerShip.getScore()/10),490,Gdx.graphics.getHeight()-20);
            }

            lasers(random);

           //collide playership
            playerShip.checkCollided2(laserArray2,points);
            Laser.update2(laserArray2);
            for (Laser laser : laserArray2) {
                laser.render();
            }

            //collide fleet
            fleet.checkCollided(laserArray,points);
            Laser.update(laserArray);
            for (Laser laser : laserArray) {
                laser.render();
            }

            //inputs
            gameHandleInputs();
            fleet.render();
        }
        batch.end();
    }

    //todo dispose
    @Override
    public void dispose() {
        batch.dispose();
    }

    //todo gameHandleInputs
    public void gameHandleInputs() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {//escolher idioma
            if (points==-1){
                language++;
                if (language>2)
                    language=0;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {//escolher dificultade
            if (points==-1){
                dificulty++;
                if (dificulty>3)
                    dificulty=0;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (points==-1){//começar jogo
                points=0;
            }
            else {//shoot do playership
                Laser laser = playerShip.shoot();
                laser.create();
                laserArray.add(laser);
            }
        }
    }

    //todo lasers
    public void lasers(int randomNum){
        if (randomNum!=memory && dificulty==0){
            memory=randomNum;
            Laser laser2 = fleet.randomPosition(randomNum);
            laser2.create();
            laserArray2.add(laser2);
        }
        else if (randomNum!=memory && dificulty==1){
            memory=randomNum;

            Laser laser2 = fleet.randomPosition(randomNum);
            laser2.create();
            laserArray2.add(laser2);
            laserArray2.add(laser2);
        }
        else if (randomNum!=memory && dificulty==2){
            memory=randomNum;

            Laser laser2 = fleet.randomPosition(randomNum);
            laser2.create();
            laserArray2.add(laser2);
            laserArray2.add(laser2);
            laserArray2.add(laser2);
        }
        else if (randomNum!=memory && dificulty==3){
            memory=randomNum;

            Laser laser2 = fleet.randomPosition(randomNum);
            laser2.create();
            laserArray2.add(laser2);
            laserArray2.add(laser2);
            laserArray2.add(laser2);
            laserArray2.add(laser2);
        }


    }

}