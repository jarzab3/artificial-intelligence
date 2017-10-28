package com.company;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class drawing extends JPanel
{
    public drawing()
    {
        JFrame f = new JFrame();

        JFrame frame = new JFrame();

        f.setSize(400, 420);

        f.add(new Main());

        f.setVisible(true);

    }

    public void paint(Graphics g)
    {
        super.paint(g);
        //Draws the line
        g.drawOval(0,0, 100, 100);
        //draws filled circle
        g.setColor(Color.red);
        g.fillOval(0,0,100, 100);
    }
}