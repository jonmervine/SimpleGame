package com.darkmage530.mario.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.darkmage530.mario.MarioBros
import com.darkmage530.mario.PPM
import com.darkmage530.mario.V_HEIGHT
import com.darkmage530.mario.V_WIDTH
import com.darkmage530.mario.scenes.Hud
import com.darkmage530.mario.sprites.Mario

class PlayScreen(private val game: MarioBros) : Screen{
    private val gameCam: OrthographicCamera = OrthographicCamera()
    private val gamePort: Viewport = FitViewport(V_WIDTH / PPM, V_HEIGHT / PPM, gameCam)
    private val hud: Hud = Hud(game.batch)

    private val mapLoader: TmxMapLoader = TmxMapLoader()
    private val map: TiledMap = mapLoader.load("mario/level1.tmx")
    private val renderer: OrthogonalTiledMapRenderer = OrthogonalTiledMapRenderer(map, 1 / PPM)

    // Box2d variables
    private val world: World = World(Vector2(0f, -10f), true)
    private val b2dr: Box2DDebugRenderer = Box2DDebugRenderer()
    private val player: Mario = Mario(world)

    private val bodyDef: BodyDef= BodyDef()
    private val shape: PolygonShape = PolygonShape()
    private val fixtureDef: FixtureDef = FixtureDef()
    private lateinit var body: Body

    init {
        gameCam.position.set(gamePort.worldWidth / 2f, gamePort.worldHeight / 2f, 0f)

        // ground
        for (mapObject: MapObject in map.layers.get(2).objects.getByType(RectangleMapObject::class.java)) {
            val rect: Rectangle = (mapObject as RectangleMapObject).rectangle

            bodyDef.type = BodyDef.BodyType.StaticBody
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2f) / PPM, (rect.getY() + rect.getHeight() / 2f) / PPM)

            body = world.createBody(bodyDef)
            shape.setAsBox((rect.width / 2f) / PPM, (rect.getHeight() / 2f) / PPM)
            fixtureDef.shape = shape
            body.createFixture(fixtureDef)
        }

        // pipe
        for (mapObject: MapObject in map.layers.get(3).objects.getByType(RectangleMapObject::class.java)) {
            val rect: Rectangle = (mapObject as RectangleMapObject).rectangle

            bodyDef.type = BodyDef.BodyType.StaticBody
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2f) / PPM, (rect.getY() + rect.getHeight() / 2f) / PPM)

            body = world.createBody(bodyDef)
            shape.setAsBox((rect.width / 2f) / PPM, (rect.getHeight() / 2f) / PPM)
            fixtureDef.shape = shape
            body.createFixture(fixtureDef)
        }

        //bricks
        for (mapObject: MapObject in map.layers.get(5).objects.getByType(RectangleMapObject::class.java)) {
            val rect: Rectangle = (mapObject as RectangleMapObject).rectangle

            bodyDef.type = BodyDef.BodyType.StaticBody
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2f) / PPM, (rect.getY() + rect.getHeight() / 2f) / PPM)

            body = world.createBody(bodyDef)
            shape.setAsBox((rect.width / 2f) / PPM, (rect.getHeight() / 2f) / PPM)
            fixtureDef.shape = shape
            body.createFixture(fixtureDef)
        }

        //coins
        for (mapObject: MapObject in map.layers.get(4).objects.getByType(RectangleMapObject::class.java)) {
            val rect: Rectangle = (mapObject as RectangleMapObject).rectangle

            bodyDef.type = BodyDef.BodyType.StaticBody
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2f) / PPM, (rect.getY() + rect.getHeight() / 2f) / PPM)

            body = world.createBody(bodyDef)
            shape.setAsBox((rect.width / 2f) / PPM, (rect.getHeight() / 2f) / PPM)
            fixtureDef.shape = shape
            body.createFixture(fixtureDef)
        }
    }

    override fun show() {
    }

    fun handleInput(dt: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.b2body.applyLinearImpulse(Vector2(0f, 4f), player.b2body.worldCenter, true)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.linearVelocity.x <= 2) {
            player.b2body.applyLinearImpulse(Vector2(0.1f, 0f), player.b2body.worldCenter, true)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.linearVelocity.x >= -2) {
            player.b2body.applyLinearImpulse(Vector2(-0.1f, 0f), player.b2body.worldCenter, true)
        }
    }

    fun update(dt: Float) {
        handleInput(dt)

        world.step(1/60f,6, 2)
        gameCam.position.x = player.b2body.position.x
        gameCam.update()

        renderer.setView(gameCam)
    }

    override fun render(delta: Float) {
        update(delta)

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        renderer.render()

        //renderer our box2dd
        b2dr.render(world, gameCam.combined)

        game.batch.projectionMatrix = hud.stage.camera.combined
        hud.stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        gamePort.update(width, height)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
    }
}