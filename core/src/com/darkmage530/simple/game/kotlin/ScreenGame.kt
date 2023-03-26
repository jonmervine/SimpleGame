package com.darkmage530.simple.game.kotlin

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.Array

class ScreenGame(private val game: SimpleGame) : Screen {
    private var dropImage: Texture
    private var bucketImage: Texture
    private var dropSound: Sound
    private var rainMusic: Music

    private var camera: OrthographicCamera

    private var bucket: Rectangle

    private var touchPos: Vector3

    private var raindrops: Array<Rectangle>
    private var lastDropTime: Long = 0L
    private var dropsGathered: Int = 0
    private var highScore: Int = 0

    init {
        game.disposalHook(this)

        // load the images for the droplet & bucket, 64x64 pixels each
        dropImage = Texture(Gdx.files.internal("droplet.png"))
        bucketImage = Texture(Gdx.files.internal("bucket.png"))

        // load the drop sound effect and the rain background music
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"))
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"))
        rainMusic.isLooping = true

        // create the camera and the SpriteBatch
        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 480f)

        // create a Rectangle to logically represent the bucket
        bucket = Rectangle()
        bucket.x = 800f/2f - 64f/2f  // center the bucket horizontally
        bucket.y = 20f               // bottom left bucket corner is 20px above
        //    bottom screen edge
        bucket.width = 64f
        bucket.height = 64f

        // create the touchPos to store mouse click position
        touchPos = Vector3()

        // create the raindrops array and spawn the first raindrop
        raindrops = Array()
        spawnRaindrop()
    }

    private fun spawnRaindrop() {
        val raindrop = Rectangle()
        raindrop.x = MathUtils.random(0f, 800f-64f)
        raindrop.y = 480f
        raindrop.width = 64f
        raindrop.height = 64f
        raindrops.add(raindrop)
        lastDropTime = TimeUtils.nanoTime()
    }

    override fun render(delta: Float) {
        // clear the screen with a dark blue color. The arguments to clear
        //    are the RGB and alpha component in the range [0,1] of the color to
        //    be used to clear the screen.
        ScreenUtils.clear(0f, 0f, 0.2f, 1f)

        // generally good practice to update the camera's matrices once per frame
        camera.update()

        // tell the SpriteBatch to render in the coordinate system specified by the
        //    camera.
        game.batch.projectionMatrix = camera.combined

        // begin a new batch and draw the bucket and all drops
        game.batch.begin()
        game.font.draw(game.batch, "Drops Collected: $dropsGathered", 0f, 480f)
        game.font.draw(game.batch, "HighScore: $highScore", 200f, 480f)
        game.batch.draw(bucketImage, bucket.x, bucket.y,
            bucket.width, bucket.height)
        for (raindrop in raindrops) {
            game.batch.draw(dropImage, raindrop.x, raindrop.y)
        }
        game.batch.end()

        // process user input
        if (Gdx.input.isTouched) {
            touchPos.set(Gdx.input.x.toFloat(),
                Gdx.input.y.toFloat(),
                0f)
            camera.unproject(touchPos)
            bucket.x = touchPos.x - 64f/2f
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            // getDeltaTime returns the time passed between the last and the current
            //    frame in seconds
            bucket.x -= 300 * Gdx.graphics.deltaTime
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            bucket.x += 300 * Gdx.graphics.deltaTime
        }

        // make sure the bucket stays within the screen bounds
        if (bucket.x < 0f)
            bucket.x = 0f
        if (bucket.x > 800f-64f)
            bucket.x = 800f-64f

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1_000_000_000L)
            spawnRaindrop()

        // move the raindrops, remove any that are beneath the bottom edge of the
        //    screen or that hit the bucket.  In the latter case, play back a sound
        //    effect also
        val iter = raindrops.iterator()
        while (iter.hasNext()) {
            val raindrop = iter.next()
            raindrop.y -= 200 * Gdx.graphics.deltaTime
            if (raindrop.y + 64 < 0) {
                if (dropsGathered > highScore)
                    highScore = dropsGathered
                dropsGathered = 0
                iter.remove()
            }
            if (raindrop.overlaps(bucket)) {
                dropsGathered++
                dropSound.play()
                iter.remove()
            }
        }
    }

    override fun show() {
        rainMusic.play()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        dropImage.dispose()
        bucketImage.dispose()
        dropSound.dispose()
        rainMusic.dispose()
    }
}