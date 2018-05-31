lazy val root = (project in file("."))
	.settings(
		name         := "jme3-examples",
		scalaVersion := "2.12.6",
		scalacOptions ++= Seq("-feature", "-deprecation"),
		version      := "0.1.0-SNAPSHOT",
		libraryDependencies ++= Seq(
			// https://wiki.jmonkeyengine.org/jme3/maven.html
			"org.jmonkeyengine" % "jme3-core"    % "3.2.1-stable",
			"org.jmonkeyengine" % "jme3-desktop" % "3.2.1-stable",
			"org.jmonkeyengine" % "jme3-lwjgl"   % "3.2.1-stable",
			"org.jmonkeyengine" % "jme3-blender" % "3.2.1-stable",
			//"org.jmonkeyengine" % "jme3-effects" % "3.2.1-stable",

			"org.slf4j" % "jul-to-slf4j" % "1.7.25", // jme3 uses java.util.logging
			"org.slf4j" % "slf4j-api"    % "1.7.25",
			"ch.qos.logback" % "logback-classic" % "1.2.3" % Runtime,
			"org.scalatest" %% "scalatest" % "3.0.5" % Test,
		),
		fork := true, // avoid error: (jME3 Main) java.lang.UnsatisfiedLinkError: Native Library /Users/kenichi/jme3-examples/libopenal.dylib already loaded in another classloader
	)
