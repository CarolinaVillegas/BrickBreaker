package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BrickBreaker extends ApplicationAdapter {
    SpriteBatch batch;
    Viewport viewPort;

    Texture bola;
    Texture jugador;
    private OrthographicCamera camera;
    private Rectangle bucket;
    private Rectangle player;
    public int dx = 10;
    public int dy = 10;
    float w;
    float h;

    @Override
    public void create() {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.update();
        camera.setToOrtho(false, w, h);

        viewPort = new FillViewport(1280, 800, camera);

        bucket = new Rectangle();
        player = new Rectangle();
        player.width = 180;
        player.height = 32;
        player.x = w/2;
        player.y = 0 + player.height/2;
        bucket.width = 64;
        bucket.height = 64;
        bucket.x = w / 2 - bucket.width / 2;
        bucket.y = h / 2;

        batch = new SpriteBatch();


        bola = new Texture("Bola.png");
        jugador = new Texture("Paddle.png");

    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bola, bucket.x, bucket.y);
        batch.draw(jugador, player.x, player.y, player.width, player.height);
        moveBall();

        batch.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if(touchPos.x > w/2){
                player.x += 10;
            } else{
                player.x -= 10;
            }
        }

        if(player.overlaps(bucket)){
            dy = -dy;
        }


    }

    @Override
    public void dispose() {
        batch.dispose();
        bola.dispose();
        jugador.dispose();
    }

    public void moveBall() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        int bounds = 64;
        bucket.x = bucket.x + dx;
        bucket.y = bucket.y + dy;

        if (bucket.x + bounds >= w) {
            dx = -dx;
            bucket.x = w - bounds;
        } else if (bucket.x <= 0) {
            dx = -dx;
            bucket.x = 0;
        }
        if (bucket.y + bounds >= h) {
            dy = -dy;
            bucket.y = h - bounds;
        } else if (bucket.y <= 0) {
            dy = -dy;
            bucket.y = 0;
        }
    }
}
