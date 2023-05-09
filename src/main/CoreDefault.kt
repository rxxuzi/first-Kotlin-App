package main

import java.awt.Color
import java.awt.Graphics
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JPanel

class CoreDefault : JPanel() {
    init {
        println("This class is " + this.javaClass.name)
        this.layout = null
        background = Color.black
        val btn1 = JButton("Start")
        btn1.setBounds(10, 700, 100, 30)
        btn1.addActionListener { _: ActionEvent? ->
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
        sleep()
    }

    private fun sleep() {
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
        repaint()
    }

    companion object {
        var isRunning = false
    }
}