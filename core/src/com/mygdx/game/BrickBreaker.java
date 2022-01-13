package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BrickBreaker extends ApplicationAdapter {
    SpriteBatch batch;
    Viewport viewPort;
//TODO: cambiar nombres de variables dif visual-logico
    //pelotitaVisual-pelotitaLogica
    Texture bola;//imagen
    Texture jugador;
    private OrthographicCamera camera;
    private Rectangle pelotita;
    private Rectangle player;
    public int dx = 10;
    public int dy = 10;
    float w;
    float h;
    public BitmapFont font;

    @Override
    public void create() {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.update();
        camera.setToOrtho(false, w, h);
        font = new BitmapFont();
        viewPort = new FillViewport(1280, 800, camera);

        pelotita = new Rectangle(); //logica
        player = new Rectangle();
        player.width = 180;
        player.height = 32;
        player.x = w / 2;
        player.y = 0 + player.height / 2;
        pelotita.width = 64;
        pelotita.height = 64;
        pelotita.x = w / 2 - pelotita.width / 2;
        pelotita.y = h / 2;

        batch = new SpriteBatch();//coleccion de recursos que van a ser dibujados, conecta logica con visual


        bola = new Texture("Bola.png");
        jugador = new Texture("Paddle.png");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ostrich-sans/ostrich-regular.ttf"));
        font = createFont(generator, 40);
        generator.dispose();
    }

    private BitmapFont createFont(FreeTypeFontGenerator generator, float dp) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        int fontSize = (int) (dp * Gdx.graphics.getDensity());
        parameter.size = fontSize;

        return generator.generateFont(parameter);
    }


    @Override
    //render dibuja
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        //empieza dibujo
        batch.begin();
        batch.draw(bola, pelotita.x, pelotita.y);
        batch.draw(jugador, player.x, player.y, player.width, player.height);
        moveBall();

        font.draw(batch, "Coord: X = "+(int)pelotita.x+" Y = "+(int)pelotita.y+" \n"+dx+""+dy, 0, h-40);

        batch.end();//terminar dibujo

        //Acá se detecta el toque de la pantalla
        //Oto comentario
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x > w / 2) {
                player.x += 10;
            } else {
                player.x -= 10;
            }
        }
        if (player.overlaps(pelotita)) {
            dy = -dy;
        }


    }

    @Override
    public void dispose() {
        batch.dispose();
        bola.dispose();
        jugador.dispose();
        font.dispose();
    }

    public void moveBall() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        int bounds = 64;
        pelotita.x = pelotita.x + dx; //lo visual se va actualizando con la logica
        pelotita.y = pelotita.y + dy;

        if (pelotita.x + bounds >= w) {
            dx = -dx;
            pelotita.x = w - bounds;
        } else if (pelotita.x <= 0) {
            dx = -dx;
            pelotita.x = 0;
        }
        if (pelotita.y + bounds >= h) {
            dy = -dy;
            pelotita.y = h - bounds;
        } else if (pelotita.y <= 0) {
            dy = -dy;
            pelotita.y = 0;
        }
    }
}
