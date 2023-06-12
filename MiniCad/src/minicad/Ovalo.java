/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
/**
 *
 * @author LaboratorioU005_11
 */
public class Ovalo extends Figura {
   Point punto1;
   Point punto2;

   Ovalo(Point _punto1, Point _punto2, Color _color) {
        punto1 = new Point(_punto1.x,_punto1.y);
        punto2 = new Point(_punto2.x,_punto2.y);
        color  = _color;
   }
   
   
   void dibujar(Graphics2D g2d){
        if (this.color!=null){
            g2d.setColor(color);
        }        
        
        g2d.drawOval(punto1.x,punto1.y,Math.abs(this.punto2.x-this.punto1.x),Math.abs(this.punto2.y-this.punto1.y));
   }   
    
}
