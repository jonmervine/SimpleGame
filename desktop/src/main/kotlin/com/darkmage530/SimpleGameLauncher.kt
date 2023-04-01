package com.darkmage530

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.darkmage530.simple.game.SimpleGame

fun main(arg: Array<String>) {
    val config = Lwjgl3ApplicationConfiguration()
    config.setForegroundFPS(60)
    config.setTitle("SimpleGame")
    config.setWindowedMode(800, 480)
    config.useVsync(true)
    Lwjgl3Application(SimpleGame(), config)
}