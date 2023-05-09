package main

import hexagon.CoreXH2
import java.awt.CardLayout
import javax.swing.JFrame
import javax.swing.JPanel

class Main {
    var cl: CardLayout? = null
    var cardPanel: JPanel? = null

    internal inner class objects {
        var x = 0
    }
//    main

    companion object {
        const val SCREEN_WIDTH = 800
        const val SCREEN_HEIGHT = 800
        var e = ArrayList<Any>()
        @JvmStatic
        fun main(args: Array<String>) {
            val frame = JFrame("FEELS")
            //        frame.setTitle("circular motion");
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT)
            frame.isVisible = true
            frame.isResizable = false
            frame.setLocation(400, 50)
            frame.add(CoreXH2())
        }
    }
}