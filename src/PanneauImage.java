/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author saiko
 */
public class PanneauImage extends JPanel{
    // Attribut
        private Image img;
    
    // Construction
        public PanneauImage() {
            super();
            this.img=null;
        }
        public PanneauImage(Image im) { 
            super();
            this.img=im;
        }
        
    // Accesseurs
        public Image getImage () { 
            return this.img;
        }
        public void setImage (Image im) {
            this.img=im;
            this.repaint();
        }
    
    // Méthodes    
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (img != null) {
                g.drawImage(img,0,0, this.getWidth(), this.getHeight(),this);
            }
        }
}
