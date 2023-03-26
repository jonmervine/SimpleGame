package com.darkmage530.simple.game.kotlin

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.physics.box2d.Box2D
import com.badlogic.gdx.utils.Array

class SimpleGame : Game() {
    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont
    private val disposables: Array<Screen> = Array()

    override fun create() {
        Box2D.init()
        batch = SpriteBatch()
        font = BitmapFont()
        this.setScreen(MainMenu(this))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        this.getScreen().dispose()

        batch.dispose()
        font.dispose()

        disposables.forEach {
            it.dispose()
        }
    }

    fun disposalHook(screen: Screen) = disposables.add(screen)

}