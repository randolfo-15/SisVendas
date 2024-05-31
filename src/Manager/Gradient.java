/*****************************************************************************
 *   Gradient
 *  =========
 *  @file  : Gradient.java
 *  @author: Randolfo A Goncalves
 *  @since : 30/05/24
 ****************************************************************************/
package Manager;

import javax.swing.*;
import java.awt.*;


public class Gradient extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        Color color1 = Color.BLACK;
        Color color2 = Color.WHITE;

        GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}