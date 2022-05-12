/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saiko
 */
public abstract class Action {
    // Attributs
    private Joueur j;  // joueur à l'origine de l'action
    private String descriptif; // nature de l'action 
    private String deroulement; // description de ce qui s’est passé durant l’action 
    
    // Accesseurs
    public Joueur getJ() {
        return j;
    }
    public void setJ(Joueur j) {
        this.j = j;
    }
    public String getDescriptif() {
        return descriptif;
    }
    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }
    public String getDeroulement() {
        return deroulement;
    }
    public void setDeroulement(String deroulement) {
        this.deroulement = deroulement;
    }
    
    // Constructeurs
    public Action (String descriptif, Joueur joueur)
    {
        this.descriptif = descriptif;
        this.j = joueur;
        this.deroulement = "";
    }
    // Méthodes
    public abstract int execute();
    public String toString() {
        return "Action effectuée par "+j.getPseudo()+ " : " +descriptif+"\n"+this.deroulement+"\n";
    }
}
