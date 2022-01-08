package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BrickBreaker extends ApplicationAdapter {
	SpriteBatch batch;
	Viewport viewPort;

	Texture img;
	private OrthographicCamera camera;
	private Rectangle bucket;
	public int dx = 10;
	public int dy = 10;

	@Override
	public void create () {

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.position.set(0,0,0);
		camera.update();
		camera.setToOrtho(false, w, h);

		viewPort = new FillViewport(1280, 800, camera);

		bucket = new Rectangle();
		bucket.x = w/2 - 64/2;
		bucket.y = h/2;
		bucket.width = 64;
		bucket.height = 64;
		batch = new SpriteBatch();


		img = new Texture("Bola.png");

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, bucket.x, bucket.y);

		moveBall();

		batch.end();



	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
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

//		if ((bucket.x < 0 || bucket.x > w - bounds)) {
//			dx *= -100;
//			if (bucket.x < 0) {
//				bucket.x = 0;
//			} else if (bucket.x > w - bounds) {
//				bucket.x = w - bounds;
//			}
//		}
//		if (bucket.y < 0 || bucket.y > h - bounds) {
//			dy *= -1;
//			if (bucket.y < 0) {
//				bucket.y = 0;
//			} else if (bucket.y > h - bounds) {
//				bucket.y = h - bounds;
//			}
//		}
//		bucket.x += dx;
//		bucket.y += dy;
	}
}
