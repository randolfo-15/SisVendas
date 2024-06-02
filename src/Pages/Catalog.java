/*****************************************************************************
 *   Catalog
 *  =========
 *  @file  : Catalog.java
 *  @author: Randolfo A Goncalves
 *  @since : 28/05/24
 ****************************************************************************/
package Pages;

import Manager.Graph;
import Manager.Sys;

import javax.swing.*;
import java.awt.*;

public class Catalog extends Sys.Panel {
    //==================================================================================================================
    // Fields
    //==================================================================================================================
    protected final JTabbedPane tabbed = new JTabbedPane();
    protected final int
        DATA = 0,
        NEW  = 1,
        EDIT = 2,
        DEL  = 3;

    protected final Graph[] page =new Graph[]{
            new Graph(),
            new Graph(),
            new Graph(),
            new Graph()
    };
    //==================================================================================================================
    // Build
    //==================================================================================================================
        public Catalog(String name){
            super(name);
            init_tabs();
            plug();
        }

    //==================================================================================================================
    // Initialization
    //==================================================================================================================
        private void init_tabs(){
            tabbed.addTab("Dados",page[DATA]);
            tabbed.addTab("Novo",page[NEW]);
            tabbed.addTab("Editar",page[EDIT]);
            tabbed.addTab("Remover",page[DEL]);
            tabbed.setFont(new Font("Serif",Font.BOLD,16));
            for(var panel : page) panel.setBackground(Sys.frg2);
        }

        private void plug(){ work_space.add(tabbed); }
    //==================================================================================================================
}
