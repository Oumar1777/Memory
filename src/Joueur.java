/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
/**
 *
 * @author saiko
 */

public class Joueur {
    
    //Attributs
    
    private String pseudo, famillePrefere;
    private LesPersonnages packet = new LesPersonnages();
    private ImageIcon photo;
    
    //Constructeurs
    
    public Joueur ()
    {
        this.setPseudo("");
        this.setFamilleP("");
        photo = new ImageIcon();
    }
    public Joueur (String pseudo, String famille)
    {
        this.setPseudo(pseudo);
        this.setFamilleP(famille);
        photo = new ImageIcon();
    }
    
    //Accesseurs
    
    public void setPseudo (String pseudo)
    {
        this.pseudo = pseudo;
    }
    public String getPseudo ()
    {
        return this.pseudo;
    }
    public void setFamilleP(String famille)
    {
        this.famillePrefere = famille;
    }
    public String getFamilleP()
    {
        return this.famillePrefere;
    }
    public void setPhoto(ImageIcon ph)
    {
        this.photo = ph;
    }
    public ImageIcon getPhoto()
    {
        return this.photo;
    }
    public void setPacket(LesPersonnages list)
    {
        this.packet = list;
    }
    public LesPersonnages getPacket()
    {
        return this.packet;
    }
    
    //Méthodes
    
    public void ajoutePersoPaquet(Personnage p) 
    {
        this.packet.ajoutePerso(p);
    }  
    public void initPaquetTest()
    {
        ajoutePersoPaquet(new Personnage("communs", "assault-trooper", 10));
        ajoutePersoPaquet(new Personnage("communs", "commando", 20));
        ajoutePersoPaquet(new Personnage("rares", "absolute-zero", 10));
    }
    public int getNbCartes()
    {
        return this.packet.getTaille();
    }
    public int getScore()
    {
        int score = 0;
        for (int i=0; i<this.packet.getTaille(); i++)
            score+= this.packet.getPerso(i).getValeur();
        return score;
    }
    @Override
    public String toString()
    {
        String s = "";
        s+="\n Pseudo : "+this.pseudo;
        s+="\n Famile Préférée : "+this.famillePrefere;
        s+="\n Packet : "+this.packet.toString();
        s+=" Score : "+this.getScore()+"\n";
        return s;
    }
}
