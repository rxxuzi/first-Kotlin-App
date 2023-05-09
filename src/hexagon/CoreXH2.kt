package hexagon

import java.awt.Color
import java.awt.Graphics
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JPanel

class CoreXH2 : JPanel() {
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
        g.drawLine(0, height / 2, width, height / 2)
        g.drawLine(width / 2, 0, width / 2, height)
        g.color = Color.GREEN
        drawHexagon(g, 0.0, 0.0)
        var l = (radius * 2).toDouble()
        val nhx = 6 //number of hexagon
        for (i in 0..5) {
            g.color = Color.yellow
            val a = 2 * Math.PI / nhx * i + Math.PI / nhx
            val x = Math.cos(a) * l
            val y = Math.sin(a) * l
            drawHexagon(g, x, y)
        }
        val cx = DoubleArray(6)
        val cy = DoubleArray(6)
        var aveX = 0.0
        var aveY = 0.0
        g.color = Color.red
        l += (radius * 2).toDouble()
        for (i in 0..5) {
            val a = 2 * Math.PI / nhx * i + Math.PI / nhx
            val x = Math.cos(a) * l
            val y = Math.sin(a) * l
            drawHexagon(g, x, y)
            cx[i] = x
            cy[i] = y
        }
        for (i in cx.indices) {
            g.color = Color.BLUE
            if (i != 0) {
                aveX = (cx[i] + cx[i - 1]) / 2
                aveY = (cy[i] + cy[i - 1]) / 2
            } else {
                aveX = (cx[i] + cx[cx.size - 1]) / 2
                aveY = (cy[i] + cy[cx.size - 1]) / 2
            }
            drawHexagon(g, aveX, aveY)
        }
        g.color = Color.red
        sleep()
    }

    private fun drawHexagon(g: Graphics, dx: Double, dy: Double) {
        val x = IntArray(corner)
        val y = IntArray(corner)
        for (i in 0 until corner) {
            val sita = 2 * Math.PI
            x[i] = (radius * Math.cos(i * sita / corner + dt) + dx + width / 2).toInt()
            y[i] = (radius * Math.sin(i * sita / corner + dt) + dy + height / 2).toInt()
        }
        if (g.color === Color.GREEN) {
            g.drawString((x[0] * 10 / radius).toString() + "," + y[0] * 10 / radius, 20, 30)
            g.drawString("Corner : $corner", 20, 50)
            g.color = Color.RED
            g.fillRect(x[0], y[0], 5, 5)
            g.color = Color.GREEN
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
        private var dt = 0.01
        private const val speed = 1
        private const val radius = 75
    }
}