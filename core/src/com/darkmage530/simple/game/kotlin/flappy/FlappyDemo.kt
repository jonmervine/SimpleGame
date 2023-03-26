package com.darkmage530.simple.game.kotlin.flappy

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.darkmage530.simple.game.kotlin.flappy.states.GameStateManager
import com.darkmage530.simple.game.kotlin.flappy.states.MenuState

const val WIDTH = 480f
const val HEIGHT = 800f
const val TITLE = "Flappy Bird"

class FlappyDemo : ApplicationAdapter() {
    private lateinit var gsm: GameStateManager
    private lateinit var batch: SpriteBatch

    private lateinit var music: Music

    override fun create() {
        batch = SpriteBatch()
        gsm = GameStateManager()
        music = Gdx.audio.newMusic(Gdx.files.internal("flappy/music.mp3"))
        music.isLooping = true
        music.volume = 0.1f
        music.play()
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        gsm.push(MenuState(gsm))
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gsm.update(Gdx.graphics.deltaTime)
        gsm.render(batch)
    }

    override fun dispose() {
        super.dispose()
        music.dispose()
        batch.dispose()
        println("Flappy Demo disposed")
    }
}