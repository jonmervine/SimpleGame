package com.darkmage530.mario.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.darkmage530.mario.MarioBros
import com.darkmage530.mario.V_HEIGHT
import com.darkmage530.mario.V_WIDTH
import com.darkmage530.mario.scenes.Hud

class PlayScreen(private val game: MarioBros) : Screen{
    private val gameCam: OrthographicCamera = OrthographicCamera()
    private val gamePort: Viewport = FitViewport(V_WIDTH, V_HEIGHT, gameCam)
    private val hud: Hud = Hud(game.batch)

    override fun show() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        game.batch.projectionMatrix = hud.stage.camera.combined
        hud.stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        gamePort.update(width, height)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
    }
}