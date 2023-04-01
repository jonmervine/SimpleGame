package com.darkmage530.flappy.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class Animation(texture: Texture, private val frameCount: Int, cycleTime: Float ) {
    private val frames: Array<TextureRegion> = Array()
    private val maxFrameTime: Float = cycleTime / frameCount
    private var currentFrameTime: Float = 0f
    private var frame: Int = 0
    private val region: TextureRegion = TextureRegion(texture)

    private val frameWidth = region.regionWidth / frameCount

    init {
        for (i in 0..frameCount) {
            frames.add(TextureRegion(region, i * frameWidth, 0, frameWidth, region.regionHeight))
        }
    }

    fun update(dt: Float) {
        currentFrameTime += dt
        if(currentFrameTime > maxFrameTime) {
            frame++
            currentFrameTime = 0f
        }
        if (frame >= frameCount) {
            frame = 0
        }
    }

    fun getFrame(): TextureRegion {
        return frames.get(frame)
    }
}