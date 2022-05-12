
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saiko
 */
public class LesJoueurs {
    
    //Attribut
    
    private ArrayList<Joueur> lstJ;
    
    public ArrayList<Joueur> getJoueurs() {
        //Retourne la liste des personnages
        return lstJ;
    }
    
    //Constructeurs
    
    public LesJoueurs() {
        //Constructeur par défaut de la liste de personnages
        this.lstJ = new ArrayList<Joueur>();
    }
    
    //Méthodes
    
    public Joueur getJoueur (int i)
    {
        //Retourne le joueur à la position i
        if (i>=0 && i<this.lstJ.size())
            return this.lstJ.get(i);
        else 
        //s'il n'y a pas de joueurs à l'indice i retourne null
            return null;
    }
    
    public int getIndiceJoueur (Joueur j)
    {
        //Retourne l'indice du joueur j
        return lstJ.indexOf(j);
    }
    
    public int getNbJoueurs()
    {
        //Retourne le nombre de joueurs
        return this.lstJ.size();
    }
    
    public void ajouteJoueur(Joueur j)
    {
        //Permet d'ajouter le joueur j donné en paramètre dans la liste
        this.lstJ.add(j);
    }
    
    public Joueur rechJoueur(String p)
    {
        //Récupère le pseudo d'un joueur et vérifie s'il est dans la liste 
        for(int i=0; i<this.lstJ.size(); i++)
        {
            if(this.lstJ.get(i).getPseudo().equals(p))
                return this.lstJ.get(i);
            //Si le joueur existe il est retourné
        }
        return null;//sinon retourne null
    }
    
    public void supprimeJoueur(Joueur j)
    {
        //Retire le joueur j de la liste de joueurs
        int i=0; 
        boolean trouve = false;
        while(i<this.lstJ.size()&& !trouve){
          if (getJoueur(i).getPseudo().equals(j.getPseudo())){
              this.lstJ.remove(i);  
              trouve = true;
          }
          else i++;
        }      
    }
    
   	@Override
    public String toString() 
    {
        String s = "";
        for(int i=0; i<this.lstJ.size(); i++)
            s += i+"- "+lstJ.get(i).toString();
        return s;
    }
    public LesJoueurs getGagnants()     
    {   
        //Determine le ou les gagnants et les renvoie
        int max=getJoueur(0).getScore();
        for(int i=1; i<getNbJoueurs();i++)
            if (getJoueur(i).getScore()>max)
                max=this.getJoueur(i).getScore(); 
 
        LesJoueurs lst=new LesJoueurs();
        for(int i=0; i<getNbJoueurs();i++)
            if (getJoueur(i).getScore()==max)
                lst.ajouteJoueur(getJoueur(i));
        return lst;
    } 
    public String getPseudos()
    {
        String pseudos = "";
        for(int i = 0; i < getNbJoueurs(); i++)
            pseudos += "- " + getJoueur(i).getPseudo() + "\n";
        return pseudos;
    }
}
