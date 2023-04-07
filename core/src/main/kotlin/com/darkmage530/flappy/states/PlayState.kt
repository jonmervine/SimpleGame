package com.darkmage530.flappy.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.darkmage530.flappy.HEIGHT
import com.darkmage530.flappy.WIDTH
import com.darkmage530.flappy.sprites.Bird
import com.darkmage530.flappy.sprites.Tube

data class PlayState(override val gsm: GameStateManager) : State() {
    private val TUBE_SPACING = 125
    private val TUBE_COUNT = 4
    private val GROUND_Y_OFFSET = -50f

    private val bird = Bird(50, 300)
    private val background: Texture = Texture("flappy/bg.png")
    private val ground: Texture = Texture("flappy/ground.png")

    private val groundPos1: Vector2 = Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET)
    private val groundPos2: Vector2 = Vector2((camera.position.x - camera.viewportWidth / 2) + ground.width, GROUND_Y_OFFSET)

    private val tubes: Array<Tube> = Array()

    init {
        camera.setToOrtho(false, WIDTH / 2, HEIGHT / 2)

        for (i in 1..TUBE_COUNT) {
            tubes.add(Tube(i.toFloat() * (TUBE_SPACING + Tube.TUBE_WIDTH)))
        }
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            bird.flap()
        }
    }

    override fun update(dt: Float) {
        handleInput()
        updateGround()
        bird.update(dt)

        camera.position.x = bird.position.x + 80

        for (tube: Tube in tubes) {
            if (camera.position.x - (camera.viewportWidth / 2) > tube.posTopTube.x + tube.topTexture.width) {
                tube.reposition(tube.posTopTube.x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT))
            }

            if (tube.collides(bird.bounds)) {
                gsm.set(PlayState(gsm))
                return
            }
        }

        if (bird.position.y <= ground.height + GROUND_Y_OFFSET) {
            gsm.set(PlayState(gsm))
        }

        camera.update()
    }

    private fun updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.width) {
            groundPos1.add(ground.width * 2f, 0f)
        }
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.width) {
            groundPos2.add(ground.width * 2f, 0f)
        }
    }

    override fun render(batch: SpriteBatch) {
        batch.projectionMatrix = camera.combined
        batch.begin()
        batch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0f)
        batch.draw(ground, groundPos1.x, groundPos1.y)
        batch.draw(ground, groundPos2.x, groundPos2.y)
        batch.draw(bird.getTexture(), bird.position.x, bird.position.y)
        for (tube: Tube in tubes) {
            batch.draw(tube.topTexture, tube.posTopTube.x, tube.posTopTube.y)
            batch.draw(tube.bottomTexture, tube.posBottomTube.x, tube.posBottomTube.y)
        }
        batch.end()
    }

    override fun dispose() {
        background.dispose()
        ground.dispose()
        bird.dispose()
        tubes.forEach { it.dispose() }
        println("Play State disposed")
    }
}