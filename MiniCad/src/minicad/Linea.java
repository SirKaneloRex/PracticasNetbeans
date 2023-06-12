package minicad;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LaboratorioU005_11
 */
public class Linea extends Figura {
   Point punto1;
   Point punto2;

   Linea(Point _punto1, Point _punto2, Color _color) {
        punto1 = new Point(_punto1.x,_punto1.y);
        punto2 = new Point(_punto2.x,_punto2.y);        
        color  = _color;
   }

   void dibujar(Graphics2D g2d){
        if (this.color!=null){
            g2d.setColor(color);
        }        
        g2d.drawLine(punto1.x,punto1.y,punto2.x,punto2.y);
   }
}
