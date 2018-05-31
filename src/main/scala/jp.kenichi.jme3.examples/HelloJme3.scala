package jp.kenichi.jme3.examples

import com.jme3.scene.Geometry
import com.jme3.scene.shape.Box
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.app.SimpleApplication
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler

/** HelloJME3 in the tutorial */
class HelloJME3 extends SimpleApplication {
	import HelloJME3.log

	override def simpleInitApp {
		log.debug("simpleInitApp")

		val box = new Box(1, 1, 1)
		val geom = new Geometry("Box", box)
		val mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
		mat.setColor("Color", ColorRGBA.Blue)
		geom.setMaterial(mat)
		rootNode.attachChild(geom)

		// custom adjustments
		flyCam.setDragToRotate(true) // unlock the mouse from the window
		flyCam.setMoveSpeed(5) // move faster (WSAD)
	}
}

object HelloJME3 extends App {
	// replace the root logger of java.util.logging with SLF4JBridgeHandler
	SLF4JBridgeHandler.removeHandlersForRootLogger
	SLF4JBridgeHandler.install

	val log = LoggerFactory.getLogger(classOf[HelloJME3])

	val jmeApp = new HelloJME3
	jmeApp.start
}
