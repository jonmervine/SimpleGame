package com.darkmage530.flappy.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3

abstract class State {
    abstract val gsm: GameStateManager

    val camera: OrthographicCamera = OrthographicCamera()
    val mouse: Vector3 = Vector3()

    abstract fun handleInput()
    abstract fun update(dt: Float)
    abstract fun render(batch: SpriteBatch)
    abstract fun dispose()


}