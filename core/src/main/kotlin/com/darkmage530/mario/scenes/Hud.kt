package com.darkmage530.mario.scenes

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.darkmage530.mario.V_HEIGHT
import com.darkmage530.mario.V_WIDTH

class Hud(batch: SpriteBatch) {
    private val viewport: Viewport = FitViewport(V_WIDTH, V_HEIGHT, OrthographicCamera())
    val stage: Stage = Stage(viewport, batch)

    private val worldTimer: Int = 300
    private val timeCount: Float = 0f
    private val score: Int = 0

    private val countdownLabel: Label = Label(String.format("%03d", worldTimer), Label.LabelStyle(BitmapFont(), Color.WHITE))
    private val scoreLabel: Label = Label(String.format("%06d", score), Label.LabelStyle(BitmapFont(), Color.WHITE))
    private val timeLabel: Label = Label("TIME", Label.LabelStyle(BitmapFont(), Color.WHITE))
    private val levelLabel: Label = Label("1-1", Label.LabelStyle(BitmapFont(), Color.WHITE))
    private val worldLabel: Label = Label("WORLD", Label.LabelStyle(BitmapFont(), Color.WHITE))
    private val marioLabel: Label = Label("MARIO", Label.LabelStyle(BitmapFont(), Color.WHITE))

    init {
        val table = Table()
        table.top()
        table.setFillParent(true)

        table.add(marioLabel).expandX().padTop(10f)
        table.add(worldLabel).expandX().padTop(10f)
        table.add(timeLabel).expandX().padTop(10f)
        table.row()
        table.add(scoreLabel).expandX()
        table.add(levelLabel).expandX()
        table.add(countdownLabel).expandX()

        stage.addActor(table)
    }
}