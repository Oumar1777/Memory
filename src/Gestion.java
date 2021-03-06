
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
public class Gestion extends javax.swing.JFrame {

    private LesPersonnages lespersos;
    public Gestion(java.awt.Frame parent, boolean modal)  {
        initComponents();
        lespersos = new LesPersonnages(10);//Constructeur avec paramètre ajoutant 10 personnages
        initListeFamilles();//Remplir la JComboBox avec la liste des noms des familles
        Edition.setText("");//Vide la zone Edition
    }

    public void initListeFamilles()
    {
        ArrayList <String> lstFamille = lespersos.getFamilles();
        for(int i=0; i<lstFamille.size(); i++)
            this.ListeFamilles.addItem(lstFamille.get(i));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Edition = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Afficher = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Numero = new javax.swing.JTextField();
        Chercher = new javax.swing.JButton();
        ListeFamilles = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        BPhoto = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Ajout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Gestion des personnages");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.GridLayout(1, 1));

        Edition.setColumns(20);
        Edition.setRows(5);
        jScrollPane1.setViewportView(Edition);

        jPanel2.add(jScrollPane1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout(2, 1));

        jPanel4.setLayout(new java.awt.GridLayout(5, 1));

        Afficher.setText("Afficher");
        Afficher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AfficherActionPerformed(evt);
            }
        });
        jPanel4.add(Afficher);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Numéro cherché ?");
        jPanel4.add(jLabel2);
        jPanel4.add(Numero);

        Chercher.setText("Chercher");
        Chercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChercherActionPerformed(evt);
            }
        });
        jPanel4.add(Chercher);

        ListeFamilles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListeFamillesActionPerformed(evt);
            }
        });
        jPanel4.add(ListeFamilles);

        jPanel3.add(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(1, 1));
        jPanel5.add(BPhoto);

        jPanel3.add(jPanel5);

        getContentPane().add(jPanel3, java.awt.BorderLayout.EAST);

        jMenu1.setText("Gestion");

        Ajout.setText("Ajout");
        Ajout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutActionPerformed(evt);
            }
        });
        jMenu1.add(Ajout);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AfficherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AfficherActionPerformed
        // TODO add your handling code here:
        Edition.setText(this.lespersos.toString());
    }//GEN-LAST:event_AfficherActionPerformed

    private void ChercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChercherActionPerformed
        // TODO add your handling code here:
        //Récupération du texte dans la JTextField Numero
        String s = Numero.getText();
        if(!s.equals(""))//si chaîne non vide
        {
            int num = Integer.parseInt(s);
            Personnage p = this.lespersos.getPerso(num);
            if(p!= null)
            {
                Edition.setText(p.toString());
                p.setImgBouton(BPhoto);
            }
            else
                Edition.setText("Numéro Incorrect !!!");
        }
        else
            Edition.setText("Tapez un numéro !!!");
    }//GEN-LAST:event_ChercherActionPerformed

    private void ListeFamillesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListeFamillesActionPerformed
        //Récupération du nom de la famille
        String f = ListeFamilles.getSelectedItem().toString();
        //On récupère les perosnnages de cette famille
        LesPersonnages famille = this.lespersos.getPersosFamille(f);
        Edition.setText(famille.toString());
        BPhoto.setIcon(null);
    }//GEN-LAST:event_ListeFamillesActionPerformed

    private void AjoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutActionPerformed
        // TODO add your handling code here:
        AjoutPersoDlg diag = new AjoutPersoDlg (this, true);//true pour bloquante
        diag.setVisible(true);//Affiche la boite
        if(diag.getOK())
            this.lespersos.ajoutePerso(diag.getPerso());
        Edition.setText("");
        
    }//GEN-LAST:event_AjoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Afficher;
    private javax.swing.JMenuItem Ajout;
    private javax.swing.JButton BPhoto;
    private javax.swing.JButton Chercher;
    private javax.swing.JTextArea Edition;
    private javax.swing.JComboBox<String> ListeFamilles;
    private javax.swing.JTextField Numero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
