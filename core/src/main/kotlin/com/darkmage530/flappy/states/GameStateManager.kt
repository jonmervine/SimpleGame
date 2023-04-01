package com.darkmage530.flappy.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.ArrayDeque

class GameStateManager {
    private val states: ArrayDeque<State> = ArrayDeque()

    fun push(state: State) {
        states.push(state)
    }

    fun pop() {
        states.pop().dispose()
    }

    fun set(state: State) {
        states.pop().dispose()
        states.push(state)
    }

    fun update(dt: Float) {
        states.peek().update(dt)
    }

    fun render(batch: SpriteBatch) {
        states.peek().render(batch)
    }
}