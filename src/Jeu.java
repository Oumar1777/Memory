/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saiko
 */
public class Jeu {
    // Attributs
    private LesPersonnages lesPers; // Les personnages utilisés pour les cartes
    private PlateauJeu monP; // Le plateau de jeu 
    private LesJoueurs lesJ; // Les joueurs qui ont été choisis ou ajoutés pour la partie en cours
    private int indC; // L’indice du joueur courant
    private Action act; // L’action que va réaliser le joueur (selon les cartes du joueur qui est en train de jouer).
    
    // Constructeurs
    public Jeu(LesPersonnages lp, LesJoueurs lj, int nbc){
        this.lesPers = lp; // Récupération de la liste des personnages
        this.monP = new PlateauJeu(nbc); // Création du plateau en fonction du niveau du jeu nbc
        this.lesJ = lj; // Récupération de la liste des joueurs
        this.act = null; // Initialisation des actions
        this.indC = 0; // Sélection du premier joueur
    }  
    
    // Accesseurs
    public LesPersonnages getLesPers() {
        return lesPers;
    }
    public PlateauJeu getMonP() {
        return monP;
    }
    public LesJoueurs getLesJ() {
        return lesJ;
    }
    public int getIndC() {
        return indC;
    }
    public void setIndC(int ind) {
        this.indC = ind;
    }
    public Action getAct() {
        return act;
    }
    public int getIndSuivant(int j){ 
        return (j+1)%lesJ.getNbJoueurs(); 
    } 
    public Joueur getJoueurCourant(){
        return lesJ.getJoueur(indC);
    }  
    public Joueur getJoueurSuivant(int j){
        return lesJ.getJoueur(getIndSuivant(j)); 
    }
    
    // Méthodes
    public boolean finJeu() {
        // Returne true si la partie est fini
        return monP.jeuVide(); // En d'autres termes si le plateau est vide (toutes les cases invalides)
    } 
    public int nbPers() {
        // Retourne le nombre total des personnages en possession des autres joueurs autre que le joueur actuel
        int n = 0; // Initialisation à 0
        for(int i = 0; i < lesJ.getNbJoueurs(); i++) // parcours la liste des joueurs
            if (i != this.indC) // s'il ne s'agit pas du joueur actuel
                n += lesJ.getJoueur(i).getNbCartes(); // on compte le nombre de carte qu'il possède
        return n; // Retourne le nombre après compte 
    }
    public int traiterTour(Joueur jo, int s) {
        int bonus = -1; // Initialisation de la valeur du bonus à -1 
        // Récupération du personnage gagné nommé « pers » 
        Personnage pers = this.lesPers.getPerso(s);
        // Ajout de ce personnage dans le paquet du joueur courant
        this.lesJ.getJoueur(this.indC).ajoutePersoPaquet(pers);
        // Récupération de la famille nommée « f » du personnage gagné « pers » 
        String f = pers.getFamille();
        // Récupération du nombre de personnages de cette famille nommé « npf » dans le jeu (pour l’ensemble des personnages)
        int npf = this.lesPers.getPersosFamille(f).getTaille();
        // Récupération du nombre de personnages de cette famille nommé « nbj » dans le paquet du joueur courant
        int nbj = this.lesJ.getJoueur(this.indC).getPacket().getPersosFamille(f).getTaille();
        
        if(nbj == npf) // Si le joueur a une famille complète 
        {
            if(this.lesJ.getJoueur(this.indC).getFamilleP().equals(f)) // Si cette famille est la famille préférée du joueur
            {
                bonus = 0; // Valeur de bonus à 0
                this.monP.termineJeu(); // Terminer le jeu
            }
            else
            {
                if(nbPers() != 0) // Si les autres joueurs ont des cartes 
                    if(f.equals("rares") || f.equals("communs")) // Si la famille gagnée est « rares » ou « communs » 
                        bonus = 1; // Valeur de bonus à 1
                    else
                        if(f.equals("legendaires") || f.equals("epiques")) // Si la famille gagnée est « legendaires » ou «epiques »
                            bonus = 2; // Valeur de bonus à 2
                        else
                            bonus = 3; // Valeur de bonus à 3
            }
        }
        return bonus;
    }
}
