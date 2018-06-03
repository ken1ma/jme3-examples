package jp.kenichi.jme3.examples

import com.jme3.scene.Geometry
import com.jme3.scene.shape.StripBox
import com.jme3.material.Material
import com.jme3.material.RenderState.BlendMode
import com.jme3.renderer.queue.RenderQueue.Bucket
import com.jme3.post.FilterPostProcessor
import com.jme3.post.filters.BloomFilter
import com.jme3.math.{ColorRGBA, FastMath}
import com.jme3.app.SimpleApplication
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler

class TransparentBoard extends SimpleApplication {
	import TransparentBoard.log

	override def simpleInitApp {
		// grow
		val fpp = new FilterPostProcessor(assetManager)
		val bloom = new BloomFilter(BloomFilter.GlowMode.Objects)
		fpp.addFilter(bloom)
		viewPort.addProcessor(fpp)

		val box = new StripBox(1, 1, 0.05f)
		val geom = new Geometry("Box", box)
		val mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
		mat.setColor("Color"    , new ColorRGBA(0.50f, 0.50f, 0.50f, 0.50f)) // gray
		mat.setColor("GlowColor", new ColorRGBA(0.80f, 0.80f, 0.80f, 0.80f))

		// transparent
		mat.getAdditionalRenderState.setBlendMode(BlendMode.Alpha)
		geom.setQueueBucket(Bucket.Transparent)

		geom.setMaterial(mat)
		geom.setLocalRotation(geom.getLocalRotation.fromAngles(Array(0f, -FastMath.PI / 2f * 7f / 8f, 0f)))
		rootNode.attachChild(geom)

		{
			val box = new StripBox(1, 1, 1)
			val geom = new Geometry("Box", box)
			val mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
			mat.setColor("Color", ColorRGBA.Green)
			geom.setMaterial(mat)
			geom.setLocalTranslation(0, 0, -10)
			rootNode.attachChild(geom)
		}

		flyCam.setDragToRotate(true) // unlock the mouse from the window
		flyCam.setMoveSpeed(5) // move faster
	}
}

object TransparentBoard extends App {
	// replace the root logger of java.util.logging with SLF4JBridgeHandler
	SLF4JBridgeHandler.removeHandlersForRootLogger
	SLF4JBridgeHandler.install

	val log = LoggerFactory.getLogger(classOf[TransparentBoard])

	val jmeApp = new TransparentBoard
	jmeApp.start
}
