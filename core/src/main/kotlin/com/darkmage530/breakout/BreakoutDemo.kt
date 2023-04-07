package com.darkmage530.breakout

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.darkmage530.mario.screens.PlayScreen

const val BREAKOUT_TITLE = "Breakout Demo"
const val BREAKOUT_WIDTH = 800f
const val BREAKOUT_HEIGHT = 480f

class BreakoutDemo : Game() {

    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()

//        setScreen(PlayScreen(this))
    }

    override fun render() {
        super.render()
    }
}