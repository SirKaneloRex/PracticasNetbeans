package minicad;

import java.awt.Color;
import java.awt.Graphics2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LaboratorioU005_11
 */
public class Triangulo extends Figura {
    Linea linea1;
    Linea linea2;
    Linea linea3;
            
    Triangulo(Linea l1, Linea l2, Linea l3, Color _color){
        linea1 = l1;
        linea2 = l2;
        linea3 = l3;
        color  = _color;
    }
    
    void dibujar(Graphics2D g2d){
        if (this.color!=null){
            g2d.setColor(color);
        }        
        linea1.dibujar(g2d);
        linea2.dibujar(g2d);
        linea3.dibujar(g2d);
   }
    
}
