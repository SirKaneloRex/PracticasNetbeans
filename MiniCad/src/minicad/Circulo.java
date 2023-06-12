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
public class Circulo extends Figura {
   Point centro;
   int   radio;

   Circulo(Point _centro, int _radio, Color _color) {
        centro = new Point(_centro.x,_centro.y);
        radio  = _radio;                
        color  = _color;
   }
   
   
   void dibujar(Graphics2D g2d){
        if (this.color!=null){
            g2d.setColor(color);
        }        
        g2d.drawOval(centro.x,centro.y,radio,radio);
   }   
    
}
