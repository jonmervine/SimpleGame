package com.darkmage530.flappy.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import java.util.*

data class Tube(val x: Float) {
    companion object {
        val TUBE_WIDTH = 52
    }

    private val FLUCTUATION = 130
    private val TUBE_GAP = 100
    private val LOWEST_OPENING = 120

    val topTexture: Texture = Texture("flappy/toptube.png")
    val bottomTexture: Texture = Texture("flappy/bottomtube.png")

    private val rand: Random = Random()
    val posTopTube: Vector2 = Vector2(x, (rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING).toFloat())
    val posBottomTube: Vector2 = Vector2(x, posTopTube.y - TUBE_GAP - bottomTexture.height)
    private val boundsTop: Rectangle = Rectangle(posTopTube.x, posTopTube.y, topTexture.width.toFloat(), topTexture.height.toFloat())
    private val boundsBottom: Rectangle = Rectangle(posBottomTube.x, posBottomTube.y, bottomTexture.width.toFloat(), bottomTexture.height.toFloat())

    fun reposition(x: Float) {
        posTopTube.set(x, (rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING).toFloat())
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTexture.height)
        boundsTop.setPosition(posTopTube.x, posTopTube.y)
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y)
    }

    fun collides(player: Rectangle): Boolean {
        return player.overlaps(boundsBottom) || player.overlaps(boundsTop)
    }

    fun dispose() {
        topTexture.dispose()
        bottomTexture.dispose()
    }
}