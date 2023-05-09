package line

import java.awt.Color
import java.awt.Graphics
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JPanel

class CoreXL1 : JPanel() {
    init {
        println("This class is " + this.javaClass.name)
        println("x^2 + y^2 = " + 2)
        this.layout = null
        background = Color.black
        val btn1 = JButton("Start")
        btn1.setBounds(10, 700, 100, 30)
        btn1.addActionListener { e: ActionEvent? ->
            isRunning++
            if (isRunning % 3 == 0) {
                btn1.text = "Stop"
            } else if (isRunning % 3 == 1) {
                btn1.text = "Pause"
            } else {
                btn1.text = "Reverse"
            }
        }
        this.add(btn1)
    }

    fun f(x: Double): Double {
        val a = height.toDouble() / unitSize * 2 //振幅
        return Math.sin(x / 100 + t) * a
    }

    fun ff(x: Double): Double {
        val a = height.toDouble() / unitSize * 2 //指数
        return Math.cos(x / 100 + t) * a
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }

    private fun draw(g: Graphics) {
        val rad = 10
        drawGrid(g)
        val hx = width / 2
        val hy = height / 2
        val unitX = width / unitSize
        val unitY = height / unitSize
        //sine wave
        g.color = Color.BLUE
        for (x in 0 until width) {
            g.drawOval(x, (f((x - hx).toDouble()) + hy).toInt(), 1, 1)
        }
        var x = width / 2
        g.fillOval(x - rad, f((x - hx).toDouble()).toInt() + height / 2 - rad, rad * 2, rad * 2)
        g.drawString("sin(t) : " + f((x - hx).toDouble()) / unitY, 10, 20)

        //cosine wave
        for (y in 0 until height) {
            g.color = Color.RED
            g.drawOval((ff((y - hy).toDouble()) + hx).toInt(), y, 1, 1)
        }
        var y = height / 2
        g.fillOval(ff((y - hy).toDouble()).toInt() + width / 2 - rad, y - rad, rad * 2, rad * 2)
        g.drawString("cos(t) : " + ff((y - hy).toDouble()) / unitX, 10, 40)
        val vx = f((x - hx).toDouble()) / unitY
        val vy = ff((y - hy).toDouble()) / unitX
        g.color = Color.YELLOW
        g.drawString("tan(t) : " + vx / vy, 10, 60)
        x = (Math.cos(t) * unitX * 2).toInt() + hx
        y = (Math.sin(t) * unitY * 2).toInt() + hy
        g.fillOval(x - rad, y - rad, rad * 2, rad * 2)
        g.drawOval(hx - unitX * 2, hy - unitY * 2, unitX * 4, unitY * 4)

        //x軸を底辺とした三角形
        val x_x = intArrayOf(hx, x, x)
        val x_y = intArrayOf(hy, y, hy)
        g.drawPolygon(x_x, x_y, 3)
        //y軸を底辺とした三角形
        val y_x = intArrayOf(hx, hx, x)
        val y_y = intArrayOf(hy, y, y)
        g.drawPolygon(y_x, y_y, 3)
        g.color = Color.WHITE
        g.drawString("VECTOR : [" + vx.toInt() + "." + Math.abs(vx * 10 % 10).toInt() + " , " + vy.toInt() + "." + Math.abs(vy * 10 % 10).toInt() + "]", 10, 80)
        sleep()
    }

    private fun drawGrid(g: Graphics) {
        //draw grid by unitSize
        for (i in 0 until unitSize) {
            g.drawLine(i * width / unitSize, 0, i * width / unitSize, height)
            g.drawLine(0, i * height / unitSize, width, i * height / unitSize)
        }
        //draw Center Line
        g.color = Color.WHITE
        g.drawLine(width / 2, 0, width / 2, height)
        g.drawLine(0, height / 2, width, height / 2)
    }

    private fun sleep() {
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
        if (isRunning % 3 == 1) {
            t += 0.12
        } else if (isRunning % 3 == 2) {
            t -= 0.05
        }
        repaint()
    }

    companion object {
        var isRunning = 0
        const val unitSize = 10
        var t = 0.0
        const val s = Math.PI //初期位相
    }
}