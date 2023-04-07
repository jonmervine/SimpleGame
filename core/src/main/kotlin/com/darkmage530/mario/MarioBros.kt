package com.darkmage530.mario

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.darkmage530.mario.screens.PlayScreen

const val V_WIDTH = 400f
const val V_HEIGHT = 208f

class MarioBros : Game() {
    lateinit var batch: SpriteBatch


    override fun create() {
        batch = SpriteBatch()

        setScreen(PlayScreen(this))
    }

    override fun render() {
        super.render()
    }
}