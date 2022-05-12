/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saiko
 */
public class Bataille extends Action{
    // Attributs
    private Joueur adversaire;
    
    // Accesseurs
    public Joueur getAdversaire() {
        return adversaire;
    }
    
    // Constructeurs
    public Bataille(Joueur jc, Joueur adversaire)
    {
        super("Bataille", jc);
        this.adversaire = adversaire;
    }
    
    // Méthodes
    @Override
    public int execute()
    {
        int res = -1;
        // Si le joueur courant et son adversaire possèdent encore des cartes (taille de leur paquet >0)
        if(this.getJ().getPacket().getTaille() > 0 && this.adversaire.getPacket().getTaille() > 0)
        {
            res = -1; // Initialisation de la valeur du résultat à -1
            // Récupération de la 1ère carte (d’indice 0) du paquet du joueur courant
            Personnage c1 = this.getJ().getPacket().getPerso(0);
            // Récupération de la 1ère carte (d’indice 0) du paquet de l’adversaire 
            Personnage c2 = this.adversaire.getPacket().getPerso(0);
            // Suppression de la carte c1, du paquet du joueur courant
            this.getJ().getPacket().retirePerso(c1);
            // Suppression de la carte c2, du paquet de l’adversaire 
            this.adversaire.getPacket().retirePerso(c2);
            if(c1.getValeur() == c2.getValeur())// Si les valeurs des deux cartes sont identiques
            {
                // Le résultat est affecté à 0 (égalité)
                res = 0;
                // Chacune des cartes est remise à la fin du paquet du joueur auquel elle appartenait
                this.getJ().getPacket().ajoutePerso(c1);
                this.adversaire.getPacket().ajoutePerso(c2);
            } 
            else
            {
                if(c1.getValeur() > c2.getValeur())
                {
                    // Le résultat est affecté à 1
                    res = 1;
                    // Les deux cartes sont ajoutées au joueur courant
                    this.getJ().getPacket().ajoutePerso(c1);
                    this.getJ().getPacket().ajoutePerso(c2);
                }
                else
                {
                    // Le résultat est affecté à 2
                    res = 2;
                    // Les deux cartes sont ajoutées au joueur courant
                    this.adversaire.getPacket().ajoutePerso(c1);
                    this.adversaire.getPacket().ajoutePerso(c2);
                }  
            }
        }
        if(this.getJ().getPacket().getTaille() == 0 || this.adversaire.getPacket().getTaille() == 0)
        {
            String deroule = "";
            deroule += "Bataille entre " + this.getJ().getPseudo() + " et " + this.adversaire.getPseudo() + "\n";
            this.setDeroulement(deroule);
        }
        return res;
    }
}
