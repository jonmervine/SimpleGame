package com.darkmage530

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.darkmage530.breakout.BREAKOUT_HEIGHT
import com.darkmage530.breakout.BREAKOUT_TITLE
import com.darkmage530.breakout.BREAKOUT_WIDTH
import com.darkmage530.breakout.BreakoutDemo

fun main(arg: Array<String>) {
    val config = Lwjgl3ApplicationConfiguration()
    config.setForegroundFPS(60)
    config.setTitle(BREAKOUT_TITLE)
    config.setWindowedMode(BREAKOUT_WIDTH.toInt(), BREAKOUT_HEIGHT.toInt())
    config.useVsync(true)
    Lwjgl3Application(BreakoutDemo(), config)
}