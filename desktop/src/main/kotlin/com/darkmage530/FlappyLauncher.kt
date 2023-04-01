package com.darkmage530

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.darkmage530.flappy.FlappyDemo
import com.darkmage530.flappy.HEIGHT
import com.darkmage530.flappy.TITLE
import com.darkmage530.flappy.WIDTH


fun main(arg: Array<String>) {
    val config = Lwjgl3ApplicationConfiguration()
    config.setForegroundFPS(60)
    config.setTitle(TITLE)
    config.setWindowedMode(WIDTH.toInt(), HEIGHT.toInt())
    config.useVsync(true)
    Lwjgl3Application(FlappyDemo(), config)
}