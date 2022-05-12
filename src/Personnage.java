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
public class Personnage {
    
    //Attributs
    
    private String famille, nom;
    private Image photo;
    private int valeur;

    //Constructeurs
    
    public Personnage()
    {
        this.famille = "anonyme";
        this.nom = "anonyme";
        this.valeur = 0;
        this.photo = new ImageIcon(getClass().getResource("/img/anonyme.png")).getImage();
    }
    
    public Personnage(String famille, String nom, int valeur)
    {
        this.famille = famille;
        this.nom = nom;
        this.valeur = valeur;
        this.photo = new ImageIcon(getClass().getResource("/img/"+this.nom+".jpg")).getImage();
    }
    
    //Accesseurs
    
    public String getFamille()
    {
        return this.famille;
    }
    public void setFamille( String famille )
    {
        this.famille = famille;
    }
    public String getNom()
    {
        return this.nom;
    }
    public void setNom( String nom )
    {
        this.nom = nom;
    }
    public int getValeur()
    {
        return this.valeur;
    }
    public void setValeur( int x )
    {
        this.valeur = x;
    }
    public Image getPhoto()
    {
        return this.photo;
    }
    public void setPhoto( Image ph )
    {
        this.photo = ph;
    }
    public void setImgBouton(JButton jb)
    {
        Image img = photo.getScaledInstance(jb.getWidth(), jb.getHeight(), Image.SCALE_SMOOTH);
        jb.setIcon(new ImageIcon(img));
    }
    
    //Méthodes
    
    @Override
    public String toString()
    {
        String s = "";
        s+= this.nom + " de la famille " + this.famille + ";";
        s+=" Valeur : "+this.valeur;
        return s;
    }
    
    public boolean equals (Personnage autre)
    {
        return this.famille.equals(autre.famille) && this.nom.equals(autre.nom);
    }
}
