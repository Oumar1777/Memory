/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saiko
 */
public class PlateauJeu {
    
    // Attributs
    
    private int tab[][];// Tableau d'entiers représentant l'état du jeu
    private int nbp; //nombre de personnages sur le plateau qui diminue au cours du jeu
    private int nblig; // nombre de lignes
    private int nbcol; // nombre de colonnes
    
    // Constructeurs
    
    public PlateauJeu( int n )
    {
        // Constructeur avec paramètre
        this.nbp = n;// Recupération du nombre de personnages selon le niveau
        this.nblig = (int)(Math.sqrt(this.nbp*2));// Calcul du nbre de ligne
        this.nbcol = this.nbp*2/this.nblig;//  Calcul du nbre de colonne
        this.tab = new int [this.nblig][this.nbcol];
        initPlateauJeu();
    }
    
    public PlateauJeu()
    {
        // Constructeur par défaut
        this(4);
    }
    
    // Accesseurs

    public int getNbp() {
        return nbp;// Retourne le nbre de personnages
    }

    public int getNblig() {
        return nblig;// Retourne le nbre de lignes
    }

    public int getNbcol() {
        return nbcol;// Retourne le nbre de colonnes
    }
    
    //  Méthodes
    
    public int getCase (int l, int c)
    {
        return this.tab[l][c];// Retourne la valeur à la ligne l et colonne c
    }
    
    public int getNbc()
    {
        return this.nblig * this.nbcol / 2;// Retourne le nombre totale de cartes initiales
    }
    
    public void initPlateauJeu ()
    {
        // Initialisation du plateau
        int k = 0;
        for(int i = 0; i < this.nblig; i++)
            for(int j = 0; j < this.nbcol; j++)
                tab[i][j] = (k++) % this.nbp;
        //Melange du plateau
        melange();
    }
    
    public void melange()
    {
        int l1, c1, l2, c2, x;
        for(int i = 0; i < 1000; i++)
        {
            l1 = (int)(Math.random()*getNblig());
            c1 = (int)(Math.random()*getNbcol());
            l2 = (int)(Math.random()*getNblig());
            c2 = (int)(Math.random()*getNbcol());
            
            x = this.tab[l1][c1];
            this.tab[l1][c1] = this.tab[l2][c2];
            this.tab[l2][c2] = x;
            
        }
    }
    
    public void invalide(int l1, int c1, int l2, int c2)
    {
        // Les cases sont invalides si elles sont gagnés ou bloqués en fin de partie
        this.tab[l1][c1] = -1;// -1 pour dire que cette cellule n'est plus disponible
        this.tab[l2][c2] = -1;// -1 pour dire que cette cellule n'est plus disponible
        this.nbp --;// On décremente le nombre de personnes
        
    }
    
    public boolean estValide (int l, int c)
    {
        return this.tab[l][c] != -1;
    }
    
    public boolean jeuVide()
    {
        return this.nbp == 0;
    }
    
    public void termineJeu()
    {
        for(int i = 0; i < this.nblig; i++)
            for(int j = 0; j < this.nbcol; j++)
                this.tab[i][j] = -1;
        this.nbp = 0;
    }
}
