package com.darkmage530.simple.game.kotlin.flappy.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

data class Bird(val x: Int, val y: Int) {
    private val GRAVITY = -15f
    private val MOVEMENT = 100f

    val position: Vector3 = Vector3(x.toFloat(), y.toFloat(), 0f)

    private val velocity: Vector3 = Vector3(0f, 0f, 0f)

    private val animationTexture: Texture = Texture("flappy/birdanimation.png")
    private val birdAnimation: Animation = Animation(animationTexture, 3, 0.5f)
    val bounds: Rectangle = Rectangle(x.toFloat(), y.toFloat(), animationTexture.width / 3f, animationTexture.height.toFloat())

    private val flap: Sound = Gdx.audio.newSound(Gdx.files.internal("flappy/sfx_wing.ogg"))

    fun update(dt: Float) {
        birdAnimation.update(dt)
        if (position.y > 0)
            velocity.add(0f, GRAVITY, 0f)
        velocity.scl(dt)

        position.add(MOVEMENT * dt, velocity.y, 0f)

        if (position.y < 0) {
            position.y = 0f
        }

        velocity.scl(1/dt)
        bounds.setPosition(position.x, position.y)
    }

    fun flap() {
        flap.play(0.4f)
        velocity.y = 250f
    }

    fun getTexture(): TextureRegion {
        return birdAnimation.getFrame()
    }

    fun dispose() {
        animationTexture.dispose()
        flap.dispose()
    }
}