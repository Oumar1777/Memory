/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saiko
 */


import java.util.ArrayList;

public class LesPersonnages {
    
    //Attributs
    
    private ArrayList<Personnage> persos;

    //Accesseurs
    
    public ArrayList<Personnage> getPersos() {
        //Retourne la liste des personnages
        return persos;
    }
    
    //Constructeur
    
    public LesPersonnages() {
        //Constructeur par défaut de la liste de personnages
        this.persos = new ArrayList<Personnage>();
    }
       
    //Méthodes
    
    public int getTaille() { 
        //retourne le nombre de personnages de la liste
        return this.persos.size(); 
    }
     
    public int getScore(){
        //Retourne le score du joueur
        int sc = 0;
        for(int i=0; i<getTaille(); i++)
           sc+=getPerso(i).getValeur();
        return sc;
    }
          
    public Personnage getPerso(int i){
        //Retourne le presonnage à la position i
        if (i>=0 && i<this.persos.size())
             return this.persos.get(i);
        else return null;
    }
    
    public LesPersonnages getPersosFamille(String f)
    {
        //Retourne une liste de personnage appartenant toute à la meme famille f
        LesPersonnages lp = new LesPersonnages();
        for(int i=0; i<getTaille(); i++)
            if(getPerso(i).getFamille().equals(f))
                lp.ajoutePerso(getPerso(i));
        return lp;
    }
    public void ajoutePerso(Personnage p)
    {
        //Ajoute un personnage à la liste de personnage
        this.persos.add(p);
    }
    public void ajoutePersos(LesPersonnages lp)
    {
        //Ajoute plusieurs personnages (liste) à la liste de personnages 
        for(int i = 0; i< lp.getTaille(); i++)
            this.ajoutePerso(lp.getPerso(i));
    }
    public void retirePerso(Personnage p)
    { 
        //Retire le personnage p de la liste de personnages
        int i=0; 
        boolean trouve = false;
        while(i<this.getTaille() && !trouve){
          if (getPerso(i).getNom().equals(p.getNom())){
              this.persos.remove(i);  
              trouve = true;
          }
          else i++;
        }      
    }
    
   public LesPersonnages retirePersos(int n)
    {
        //Supprime de la liste les n+1 premiers personnages et renvoie la liste des perso supprimés
        LesPersonnages lcr = new LesPersonnages();
        for (int i=0; i<=n; i++)
        {
            lcr.ajoutePerso(getPerso(0));
            this.persos.remove(0);  
        }
        return lcr;
    } 
    
    public LesPersonnages retirePersosFamille(String f)
    { 
        //Supprime tous les personnages de la famille f et renvoie la liste des personnages supprimés
        LesPersonnages lcr = new LesPersonnages();
        int i = 0;
        while(i<getTaille()){
            if (getPerso(i).getFamille().equals(f)) {
                lcr.ajoutePerso(getPerso(i));
                this.persos.remove(i);  
            }
            else i++;
        }
        return lcr;
    }
    
    public ArrayList<String> getFamilles()
    {
        int i,j;
        boolean b;
        String famille;
        ArrayList<String> liste = new ArrayList<>();
        for(i=0; i<this.getTaille();i++)
        {
            famille = this.getPerso(i).getFamille();
            j=0; b= false;
            while(j<liste.size() && !b)
            {
                if(liste.get(j).equals(famille))
                    b = true;
                else
                    j++;
            }
            if(!b)
                liste.add(famille);
        }
        return liste;
    }
    
    public void retireCartes()
    {   
        //Suppression de tous les personnages
           persos.clear();
    }
    
	@Override
    public String toString() {
        String s = "\n";
        for(int i=0; i<getTaille(); i++)
            s += i+"- "+getPerso(i).toString()+"\n";
       return s;
    }
	
    //Constructeurs avec paramètres
    
    public LesPersonnages(int nc) {
        this.persos = new ArrayList<Personnage>();
        if (nc >= 4){ // 2 familles
            ajoutePerso(new Personnage("communs", "assault-trooper", 10));
            ajoutePerso(new Personnage("communs", "commando", 20));
            ajoutePerso(new Personnage("rares", "absolute-zero", 10));
            ajoutePerso(new Personnage("rares", "arctice-assassin", 20));
        }
        if (nc >= 10){ // 4 familles
            ajoutePerso(new Personnage("communs", "devestrator", 30));
            ajoutePerso(new Personnage("rares", "brawler", 30));
            ajoutePerso(new Personnage("alpins-femmes", "mogul-master", 10));
            ajoutePerso(new Personnage("alpins-femmes", "mogul-master-can", 20));
            ajoutePerso(new Personnage("as-des-pistes", "alpine-ace", 10));
            ajoutePerso(new Personnage("as-des-pistes", "alpine-ace-can", 20));
        }
        if (nc >= 18){ // 6 familles

            ajoutePerso(new Personnage("alpins-femmes", "mogul-master-chn", 30));
            ajoutePerso(new Personnage("as-des-pistes", "alpine-ace-chn", 30));
            ajoutePerso(new Personnage("legendaires", "power-chord", 10));
            ajoutePerso(new Personnage("legendaires", "raptor", 20));
            ajoutePerso(new Personnage("legendaires", "raven", 30));
            ajoutePerso(new Personnage("epiques", "burnout", 10));
            ajoutePerso(new Personnage("epiques", "funk-ops", 20));
            ajoutePerso(new Personnage("epiques", "rex", 30));
        }
        if (nc == 32){ // 6 familles
            ajoutePerso(new Personnage("communs", "dominator", 40));
            ajoutePerso(new Personnage("communs", "highrise-assault-trooper", 50));
            ajoutePerso(new Personnage("communs", "jungle-scout", 60));
            ajoutePerso(new Personnage("communs", "pathfinder", 70));
            ajoutePerso(new Personnage("rares", "brilliant-striker", 40));
            ajoutePerso(new Personnage("rares", "brite-bomber", 50));
            ajoutePerso(new Personnage("rares", "circuit-breaker", 60));
            ajoutePerso(new Personnage("rares", "dazzle", 70));
            ajoutePerso(new Personnage("alpins-femmes", "mogul-master-fra", 40));
            ajoutePerso(new Personnage("alpins-femmes", "mogul-master-gbr", 50));
            ajoutePerso(new Personnage("as-des-pistes", "alpine-ace-fra", 40));
            ajoutePerso(new Personnage("as-des-pistes", "alpine-ace-gbr", 50));
            ajoutePerso(new Personnage("legendaires", "red-knight", 40));
            ajoutePerso(new Personnage("epiques", "shadow-ops", 40));
        }
    }
}
