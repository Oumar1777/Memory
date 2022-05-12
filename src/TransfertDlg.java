/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
/**
 *
 * @author saiko
 */
public class TransfertDlg extends javax.swing.JDialog {
    
    // Attributs
    private LesJoueurs lj; // Collection des joueurs, pour initialiser la liste déroulante avec les pseudos des joueurs     
    private int indj; // Indice joueur courant     
    private Transfert tc; // Permettra d'effectuer le transfert des personnages d'un joueur à l'autre    
    private boolean ok; // Indicateur pour savoir le transfert a bien été effectué.     
    private int indjs; // Indice du joueur sélectionné dans la liste déroulante     
    private String fs; // Famille du personnage sélectionné en cliquant sur un des personnages du joueur sélectionné
    
    // Constructeur
    public TransfertDlg(Frame parent, boolean modal, LesJoueurs lj, int indj)
    {
        super(parent, modal); // Appel du constructeur de la classe ancêtre
        initComponents(); // Création de l'interface par l'EDI
        this.lj = lj; // Récupération de la liste des joueurs
        this.indj = indj; // Récupération de l'indice du joueur courant
        this.tc=null; // Initialisation du transfert
        this.ok = false; // Initialisation du contrôle de transfert
        this.fs = null; // Initialisation de la famille sélectionnée
        this.indjs = 0; // Le premier joueur est sélectionné par défaut
        this.Message.setText("Le joueur "+lj.getJoueur(indj).getPseudo()+" a obtenu une famille complète"); //Message 
        initCombo(); // méthode pour remplir la liste déroulante 
        this.Infos.setText("Personnages de "+lj.getJoueur(indj).getPseudo()+" : \n"+lj.getJoueur(indj).getPacket());
        this.Transfert.setEnabled(false);// Désactiver le bouton transfert jusqu'à la sélection des personnages à transferer
    }
    
    // Accesseurs
    public boolean getOk() {
        return ok;
    }
    public Transfert getTc() {
        return this.tc;
    }
    public int getIndj(){
        return this.indj;
    }
    // Méthodes
    public void initCombo()
    {
        // Cette méthode permet de remplir la JComboBox avec le nom des joueurs
        for(int i = 0; i < this.lj.getNbJoueurs(); i++)
            this.ComboJoueurs.addItem(this.lj.getJoueur(i).getPseudo());
    }
    public void initPanneau()
    {
        this.PanneauG.removeAll(); // Suppression de tous les composants du panneau : PanneauG
        this.repaint(); // Ré-affichage de la boite de dialogue
        // Récupération du paquet du joueur sélectionné
        LesPersonnages lcs = this.lj.getJoueur(this.indjs).getPacket();
        int t = lcs.getTaille(); // Récupération du nombre de personnages dans le paquet
        int n = 1+(t-1)/4; // Détermination du nombre de colonne
        // Definition de la disposition
        this.PanneauG.setLayout(new GridLayout(4, n));
        this.PanneauD.setLayout(new GridLayout(4, n)); 
        //this.PanneauD.setLayout(new GridLayout(4, n)); 
        for(int i = 0; i < t; i++)
        {
            JButton bt = new JButton();
            bt.setName(lcs.getPerso(i).getFamille());// La famille du personnage est défini comme nom 
            // Abonnement à un écouteur
            bt.addActionListener(
                new java.awt.event.ActionListener()
                {
                      @Override
                      public void actionPerformed(ActionEvent evt)
                    {
                        //methode éxécuté quand on clique sur le bouton
                        boutonActionPerformed(evt);
                    }  
                }
            );
            this.PanneauG.add(bt);
        } 
        this.pack();
    }
    public void affichePanneau()
    {
        // Méthode permettant d'afficher les images des personnages sur les boutons
        int t = this.lj.getJoueur(indjs).getPacket().getTaille();
        for (int i = 0; i < t; i++)
        {
            JButton jb = (JButton)this.PanneauG.getComponent(i);
            Personnage p = this.lj.getJoueur(this.indjs).getPacket().getPerso(i);
            Image img = p.getPhoto().getScaledInstance(jb.getWidth(), jb.getHeight(), Image.SCALE_DEFAULT);
            jb.setIcon(new ImageIcon(img));
        }
    }
    private void boutonActionPerformed(ActionEvent evt)     
    {       
        // Recupération du paquet du joueur sélectionné
        LesPersonnages lp = this.lj.getJoueur(indjs).getPacket();
        // Recupération du nombre de personnage
        int t = lp.getTaille();
        // Recupération du bouton cliqué
        JButton bt = (JButton) evt.getSource();
        // Recupération du nom du personnage
        fs = bt.getName(); // la proprité Name, contient ici le nom du personnage affiché sur le bouton
        for(int i = 0; i < t; i++) 
        {
            //Récuperation des boutons
            JButton b = (JButton)(PanneauG.getComponent(i));
            if (b.getName().equals(fs))
                // Si le bouton à le même name que le bouton cliqué
                b.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 0, 0)));
                // La bordure du bouton devient roouge
            else
                // Si c'est pas le même name pas de bordure
                b.setBorder(null);
        }
        // Récupération de tous les personnages de la même famille que le bouton sélectionné
        LesPersonnages lps = lp.getPersosFamille(fs);
        // Affichage du message dans Infos
        Infos.setText("Vous pouvez récupérer "+lps.getTaille()+" personnages : \n"+lps);
        // Transfert possible
        this.Transfert.setEnabled(true);
    }
    public void creePanneau(JPanel jp, LesPersonnages lc)
    {
        jp.removeAll(); // Suppression de tous les composants du panneau
        for(int i = 0; i < lc.getTaille(); i++)
            jp.add(new JButton());
        this.pack(); // à appeler si ajout ou suppression de composants 
    }
    public void dessinePanneau(JPanel jp, LesPersonnages lc)
    {
        // Méthode permettant d'afficher les images des personnages sur les boutons
        for (int i = 0; i < lc.getTaille(); i++)
        {
            JButton jb = (JButton)jp.getComponent(i);
            Image img = lc.getPerso(i).getPhoto().getScaledInstance(jb.getWidth(), jb.getHeight(), Image.SCALE_DEFAULT);
            jb.setIcon(new ImageIcon(img));
        }
    }         
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauG = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Message = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ComboJoueurs = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Infos = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        Transfert = new javax.swing.JButton();
        Fermer = new javax.swing.JButton();
        PanneauD = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 3));

        PanneauG.setLayout(new java.awt.GridLayout(1, 1));
        getContentPane().add(PanneauG);

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jPanel2.setLayout(new java.awt.GridLayout(4, 1));

        Message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(Message);
        jPanel2.add(jLabel2);
        jPanel2.add(jLabel3);

        ComboJoueurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboJoueursActionPerformed(evt);
            }
        });
        jPanel2.add(ComboJoueurs);

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        Infos.setColumns(20);
        Infos.setRows(5);
        jScrollPane1.setViewportView(Infos);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        Transfert.setText("Transfert");
        Transfert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransfertActionPerformed(evt);
            }
        });
        jPanel4.add(Transfert);

        Fermer.setText("Fermer");
        Fermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FermerActionPerformed(evt);
            }
        });
        jPanel4.add(Fermer);

        jPanel3.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1);

        PanneauD.setLayout(new java.awt.GridLayout(1, 1));
        getContentPane().add(PanneauD);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboJoueursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboJoueursActionPerformed
        // Gestionnaire de la sélection dans la ComboBox
        this.indjs = ComboJoueurs.getSelectedIndex(); // Récupération de l'indice sélectionné 
        if (indjs != -1) // Si un joueur a été selectionné
        {
            if (this.indjs == this.indj) // Si le joueur sélectionné est le joueur courant
            {             
                // Si le joueur sélectionné est le joueur courant
                Infos.setText("Sélectionnez un joueur différent du joueur courant !"); // On affiche le message
                PanneauG.removeAll(); // Suppression de tous les composants du panneau : PanneauG
                PanneauG.repaint(); // Ré-affichage du panneau
            }             
            else
            { 
                // Si le joueur sélectionné n'est pas le joueur courant
                Infos.setText("\nJoueur sélectionné: "+lj.getJoueur(indjs).toString());
                initPanneau(); // Appel de la méthode initPanneau()
                affichePanneau(); // Appel de la méthode affichePanneau()
            }
        }   
    }//GEN-LAST:event_ComboJoueursActionPerformed

    private void FermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FermerActionPerformed
        // Gestionnaire du clic sur le bouton Fermer
        this.setVisible(false);//Fermer la fenêtre
        this.dispose();//Libérer l'espace mémoire
    }//GEN-LAST:event_FermerActionPerformed

    private void TransfertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransfertActionPerformed
        // Gestionnaire du clic sur Transfert
        if(!this.fs.equals("") && this.ComboJoueurs.getSelectedIndex() != -1)
        {
            // Création d’une instance pour l’attribut de type « Transfert »
            tc = new Transfert(this.lj.getJoueur(indj),this.lj.getJoueur(this.ComboJoueurs.getSelectedIndex()),this.fs);
            // Appel de la méthode « execute() » sur cette instance 
            if(tc.execute() > 0)
            {
                // Récupération des personnages transférés 
                LesPersonnages lpt = tc.getCartesTranferees();
                // Récupération des personnages du paquet du joueur sélectionné (donc après l’action de transfert)
                LesPersonnages lpj = this.lj.getJoueur(this.ComboJoueurs.getSelectedIndex()).getPacket();
                // Appel d’une méthode nommée « creePanneau » pour le panneau de droite
                creePanneau(this.PanneauD, lpt);
                // Appel d’une méthode nommée « creePanneau » pour le panneau de gauche
                creePanneau(this.PanneauG, lpj);
                // Appel d’une méthode « dessinePanneau » pour le panneau de droite
                dessinePanneau(this.PanneauD, lpt); 
                // Appel d’une méthode « dessinePanneau » pour le panneau de gauche
                dessinePanneau(this.PanneauG, lpj);
                // Affectation de la valeur « true » au booléen « ok » 
                this.ok = true;
                // Invalidation du bouton de transfert 
                this.Transfert.setEnabled(false);
                // Description
                this.tc.setDeroulement(lj.getJoueur(indj).getPseudo()+" a pris les cartes de la famille "+fs+" à "+lj.getJoueur(indjs).getPseudo()+"\n");
                this.Infos.append(tc.getDeroulement());
            }
            else
                this.Infos.setText("Il est nécessaire de sélectionner un joueur qui a au moins une carte");
        }
    }//GEN-LAST:event_TransfertActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboJoueurs;
    private javax.swing.JButton Fermer;
    private javax.swing.JTextArea Infos;
    private javax.swing.JLabel Message;
    private javax.swing.JPanel PanneauD;
    private javax.swing.JPanel PanneauG;
    private javax.swing.JButton Transfert;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
