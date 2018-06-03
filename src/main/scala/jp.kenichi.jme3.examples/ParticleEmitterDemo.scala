package jp.kenichi.jme3.examples

import com.jme3.scene.Geometry
import com.jme3.material.Material
import com.jme3.effect.{ParticleEmitter, ParticleMesh}
import com.jme3.math.{ColorRGBA, Vector3f}
import com.jme3.app.SimpleApplication
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler

class ParticleEmitterDemo extends SimpleApplication {
	import ParticleEmitterDemo.log

	override def simpleInitApp {
		val geom = new ParticleEmitter("pe", ParticleMesh.Type.Point, 100)
		geom.setParticlesPerSec(100) // 0 means only initial emission
		geom.getParticleInfluencer.setInitialVelocity(new Vector3f(0.3f, 0.3f, 0.3f)) // faster
		geom.getParticleInfluencer.setVelocityVariation(1) // 360 degree
		geom.setGravity(new Vector3f(0, 0, 0))
		geom.setEndColor(ColorRGBA.Red)
		geom.setLowLife(0.50f)
		geom.setHighLife(0.75f)
		geom.emitAllParticles
		val mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md")
		geom.setMaterial(mat)
		rootNode.attachChild(geom)

		// custom adjustments
		flyCam.setDragToRotate(true) // unlock the mouse from the window
		flyCam.setMoveSpeed(5) // move faster (WSAD)
	}
}

object ParticleEmitterDemo extends App {
	// replace the root logger of java.util.logging with SLF4JBridgeHandler
	SLF4JBridgeHandler.removeHandlersForRootLogger
	SLF4JBridgeHandler.install

	val log = LoggerFactory.getLogger(classOf[ParticleEmitterDemo])

	val jmeApp = new ParticleEmitterDemo
	jmeApp.start
}
