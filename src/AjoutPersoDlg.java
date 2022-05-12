
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saiko
 */
public class AjoutPersoDlg extends javax.swing.JDialog {

    /**
     * Creates new form AjoutPersoDlg
     */
    private Personnage p;//Le personnage à saisir 
    private boolean ok;
    
    //Accesseurs
    public boolean getOK()
    {
        return this.ok;
    }
    public Personnage getPerso()
    {
        return this.p;
    }
    
    public AjoutPersoDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);//appel du constructeur de la classe ancêtre 
        initComponents();//Interface
        MPhoto.setText("Attention: Un fichier jpg avec le nom du personnage doit exister dans le répertoire src/img/ du projet");
        p = null;
        ok = false;//Initialisation
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
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        Famille = new javax.swing.JTextField();
        Nom = new javax.swing.JTextField();
        Valeur = new javax.swing.JTextField();
        MPhoto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        Annuler = new javax.swing.JButton();
        Valider = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Saisie d'un nouveau personnage");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setLayout(new java.awt.GridLayout(4, 1));

        jLabel2.setText("Famille ?");
        jPanel4.add(jLabel2);

        jLabel3.setText("Nom ?");
        jPanel4.add(jLabel3);

        jLabel4.setText("Valeur ?");
        jPanel4.add(jLabel4);

        jLabel5.setText("Photo :");
        jPanel4.add(jLabel5);

        jPanel2.add(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(4, 1));
        jPanel5.add(Famille);
        jPanel5.add(Nom);
        jPanel5.add(Valeur);

        MPhoto.setEditable(false);
        jPanel5.add(MPhoto);

        jPanel2.add(jPanel5);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        Annuler.setText("Annuler");
        Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerActionPerformed(evt);
            }
        });
        jPanel3.add(Annuler);

        Valider.setText("Valider");
        Valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValiderActionPerformed(evt);
            }
        });
        jPanel3.add(Valider);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnulerActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_AnnulerActionPerformed

    private void ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValiderActionPerformed
        // TODO add your handling code here:
        String fam = Famille.getText();
        String n = Nom.getText();
        int v = Integer.parseInt(Valeur.getText());
        MPhoto.setText("");
        String fich = new File("").getAbsolutePath()+"/src/img/"+n+".jpg";
        File f = new File(fich);
        if(!f.isFile())//Test sur l'existance de la photo n.jpg dans le repertoire 
        {
            //si non on affiche un message d'erreur
            MPhoto.setText("ATTENTIION !! Le fichier "+fich+" n'existe pas");
        }
        else
        {
            //si oui on crée le personnage
            this.p = new Personnage (fam, n, v);
            this.ok = true;
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_ValiderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annuler;
    private javax.swing.JTextField Famille;
    private javax.swing.JTextField MPhoto;
    private javax.swing.JTextField Nom;
    private javax.swing.JTextField Valeur;
    private javax.swing.JButton Valider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
