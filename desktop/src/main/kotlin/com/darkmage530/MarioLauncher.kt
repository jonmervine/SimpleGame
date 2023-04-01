package com.darkmage530

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

fun main(arg: Array<String>) {
    val config = Lwjgl3ApplicationConfiguration()
    config.setForegroundFPS(60)
//    config.setTitle(TITLE)
//    config.setWindowedMode(WIDTH.toInt(), HEIGHT.toInt())
    config.useVsync(true)
//    Lwjgl3Application(MarioDemo(), config)
}