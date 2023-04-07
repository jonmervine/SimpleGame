package com.darkmage530.mario.sprites

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.World
import com.darkmage530.mario.PPM

class Mario(val world: World) : Sprite() {
    lateinit var b2body: Body
    init {
        defineMario()
    }

    fun defineMario() {
        val bdef: BodyDef = BodyDef()
        bdef.position.set(32f / PPM, 32f / PPM)
        bdef.type = BodyDef.BodyType.DynamicBody
        b2body = world.createBody(bdef)

        val fdef: FixtureDef = FixtureDef()
        val shape: CircleShape = CircleShape()
        shape.radius = 5f / PPM

        fdef.shape = shape
        b2body.createFixture(fdef)
    }
}