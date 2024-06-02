package Pages;

import Manager.Graph;
import Manager.Sys;

import javax.swing.*;
import java.awt.*;

public class Info extends Sys.Panel {
    private final String body_00 ="\n" +
            "<br>Projeto dedicado para  disciplinas de LP e POO, focado em desenvolver um e-commerce." +
            "<br>SisVendas – (Sistema de vendas) pretende simular o atendimento de um caixa de supermecado"+
            "<br>suportando o cadastro de  produtos como  games, peças de computador, " +
            "<br>camisas de futebol, maquiagens,roupas, calçados, cosméticos, etc." +
            "<br>O sistema é desenvolvido com Java Desktop, versão 17, utilizando a biblioteca Swing," +
            "<br>e Mysql como banco de dados.\n";

    JPanel panel = new Graph("bkg2.jpg");
    public Info(){
        super("Informações");
        init();
    }

    public void init(){
        JLabel info = Sys.make_text("",20, Color.BLACK);
        info.setText(
                Sys._html+
                    "<div align='center'>"+
                        "<br><br> <br><br><br> <h1> SisVendas </h1>"+
                        "<p style='text-align:justify'>"+body_00+"</p>"+
                    "</div>"+
                Sys.html_
        );
        panel.add(info);
        work_space.add(panel);
    }
}
