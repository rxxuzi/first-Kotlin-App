package hexagon

import java.awt.Color
import java.awt.Graphics
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JPanel

class CoreXH1 : JPanel() {
    val corner = 6

    init {
        println("This class is " + this.javaClass.name)
        this.layout = null
        background = Color.black
        val btn1 = JButton("Start")
        btn1.setBounds(10, 700, 100, 30)
        btn1.addActionListener { e: ActionEvent? ->
            isRunning = !isRunning
            if (isRunning) {
                btn1.text = "Stop"
            } else {
                btn1.text = "Start"
            }
        }
        this.add(btn1)
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }

    private fun draw(g: Graphics) {
        g.color = Color.white
        g.color = Color.GREEN
        val rad = 100
        for (i in 0..5) {
            val x = rad * Math.cos(i * Math.PI / 3)
            val y = rad * Math.sin(i * Math.PI / 3)
            drawHexagon(g, x, y)
        }
        g.color = Color.red
        sleep()
    }

    private fun drawHexagon(g: Graphics, dx: Double, dy: Double) {
        g.color = Color.WHITE
        val x = IntArray(corner)
        val y = IntArray(corner)
        for (i in 0 until corner) {
            val sita = 2 * Math.PI
            x[i] = (100 * Math.cos(i * sita / corner + dt) + dx + width / 2).toInt()
            y[i] = (100 * Math.sin(i * sita / corner + dt) + dy + height / 2).toInt()
        }
        g.drawPolygon(x, y, corner)
    }

    private fun sleep() {
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
        if (isRunning) {
            dt += 0.04
        }
        repaint()
    }

    companion object {
        var isRunning = false
        var word = "null"
        private var dt = 0.01
    }
}