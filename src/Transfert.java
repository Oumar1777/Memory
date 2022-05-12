/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saiko
 */
public class Transfert extends Action {
    // Attributs
        private Joueur cible;
        private String fp = "";
        private LesPersonnages cartesTranferees;
        
    // Accesseurs
        public Joueur getJoueurCible() {
            return this.cible;
        }
        public LesPersonnages getCartesTranferees() {
            return this.cartesTranferees;
        }
        
    // Constructeurs
        public Transfert(Joueur j, Joueur cible, String famille)
        {
            super("Transfert", j);
            this.cible = cible;
            this.fp = famille;
            this.cartesTranferees = new LesPersonnages();
        }
    
    // Méthodes
        @Override
        public int execute()
        {
            int res = 0;
            if(!this.fp.equals(""))
            {
                this.cartesTranferees = this.cible.getPacket().retirePersosFamille(fp);
                for(int i = 0; i < this.cartesTranferees.getTaille(); i++)
                    this.getJ().ajoutePersoPaquet(this.cartesTranferees.getPerso(i));   
                // ou this.getJ().getPacket().ajoutePersos(cartesTranferees);
                res = this.cartesTranferees.getTaille();
            }
            return res;
        }
}
