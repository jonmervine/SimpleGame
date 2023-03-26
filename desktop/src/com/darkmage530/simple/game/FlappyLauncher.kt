package com.darkmage530.simple.game

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.darkmage530.simple.game.kotlin.flappy.FlappyDemo
import com.darkmage530.simple.game.kotlin.flappy.HEIGHT
import com.darkmage530.simple.game.kotlin.flappy.TITLE
import com.darkmage530.simple.game.kotlin.flappy.WIDTH


fun main(arg: Array<String>) {
    val config = Lwjgl3ApplicationConfiguration()
    config.setForegroundFPS(60)
    config.setTitle(TITLE)
    config.setWindowedMode(WIDTH.toInt(), HEIGHT.toInt())
    config.useVsync(true)
    Lwjgl3Application(FlappyDemo(), config)
}