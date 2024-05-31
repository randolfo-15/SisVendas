/*****************************************************************************
 *   Graph
 *  =======
 *  @file  : Graph.java
 *  @author: Randolfo A Goncalves
 *  @since : 30/05/24
 ****************************************************************************/
package Manager;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Graph extends javax.swing.JPanel {
    private ImageIcon img;

    public Graph() {
        img = new ImageIcon();
    }

    public Graph(String path) {
        img = new ImageIcon("src/imagens/"+path);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void setImg(ImageIcon img) {
        this.img = img;

    }
}