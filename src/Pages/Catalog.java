/*****************************************************************************
 *   Catalog
 *  =========
 *  @file  : Catalog.java
 *  @author: Randolfo A Goncalves
 *  @since : 28/05/24
 ****************************************************************************/
package Pages;

import Manager.Sys;

import javax.swing.*;
import java.awt.*;

public class Catalog extends Sys.Panel {
    //==================================================================================================================
    // Fields
    //==================================================================================================================
    private final JTabbedPane tabbed = new JTabbedPane();
    private final int
        DATA = 0,
        NEW  = 1,
        EDIT = 2,
        DEL  = 3;

    private final JPanel[] panel =new JPanel[]{
            new JPanel(),
            new JPanel(),
            new JPanel(),
            new JPanel()
    };
    //==================================================================================================================
    // Build
    //==================================================================================================================
        Catalog(String name){
            super(name);
            init_tabs();
            plug();
        }

    //==================================================================================================================
    // Initialization
    //==================================================================================================================
        private void init_tabs(){
            tabbed.addTab("Dados",panel[DATA]);
            tabbed.addTab("Novo",panel[NEW]);
            tabbed.addTab("Editar",panel[EDIT]);
            tabbed.addTab("Remover",panel[DEL]);
        }

        private void plug(){ work_space.add(tabbed); }
    //==================================================================================================================
}