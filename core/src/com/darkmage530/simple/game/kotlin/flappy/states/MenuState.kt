package com.darkmage530.simple.game.kotlin.flappy.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.darkmage530.simple.game.kotlin.flappy.HEIGHT
import com.darkmage530.simple.game.kotlin.flappy.WIDTH

data class MenuState(override val gsm: GameStateManager) : State() {
    private val background: Texture = Texture("flappy/bg.png")
    private val playButton: Texture = Texture("flappy/playBtn.png")

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(PlayState(gsm))
        }
    }

    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(batch: SpriteBatch) {
        batch.begin()
        batch.draw(background, 0f, 0f, WIDTH, HEIGHT)
        batch.draw(playButton,
            (WIDTH / 2) - (playButton.width / 2),
            HEIGHT / 2
        )
        batch.end()
    }

    override fun dispose() {
        background.dispose()
        playButton.dispose()
        println("Menu State disposed")
    }
}