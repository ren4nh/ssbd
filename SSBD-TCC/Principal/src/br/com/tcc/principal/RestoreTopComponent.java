/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.principal;

import br.com.tcc.bean.Conexao;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//br.com.tcc.principal//Restore//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "RestoreTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "br.com.tcc.principal.RestoreTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_RestoreAction",
        preferredID = "RestoreTopComponent")
@Messages({
    "CTL_RestoreAction=Restore",
    "CTL_RestoreTopComponent=Restaurar",
    "HINT_RestoreTopComponent=This is a Restore window"
})
public final class RestoreTopComponent extends TopComponent {

    public RestoreTopComponent() {
        initComponents();
        setName(Bundle.CTL_RestoreTopComponent());
        setToolTipText(Bundle.HINT_RestoreTopComponent());
        loadCombo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbBase = new javax.swing.JComboBox();
        btRestore = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(RestoreTopComponent.class, "RestoreTopComponent.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btRestore, org.openide.util.NbBundle.getMessage(RestoreTopComponent.class, "RestoreTopComponent.btRestore.text")); // NOI18N
        btRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRestoreActionPerformed(evt);
            }
        });

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbBase, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btRestore)
                        .addGap(0, 122, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRestore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRestoreActionPerformed
        if (cmbBase.getSelectedItem() != null) {
            Conexao c = (Conexao) cmbBase.getSelectedItem();
             JFileChooser j = new JFileChooser();
//                j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            j.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    String fileName = f.getName();
                    return f.isDirectory() || fileName.toLowerCase().endsWith(".exe");
                }

                @Override
                public String getDescription() {
                    return "*.exe";
                }
            });
            j.setDialogTitle("Selecione o arquivo pg_restore");
            int resp = j.showOpenDialog(j);
            if (resp != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File salvo = j.getSelectedFile();
            if (!salvo.getName().equalsIgnoreCase("pg_restore.exe")) {
                JOptionPane.showMessageDialog(null, "Deverá ser selecionado o arquivo pg_restore");
                return;
            }
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {
                    String fileName = f.getName();
                    return f.isDirectory() || fileName.toLowerCase().endsWith(".backup");
                }

                @Override
                public String getDescription() {
                    return "*.backup";
                }
            });
            jfc.setDialogTitle("Selecione o arquivo de backup");
            resp = jfc.showSaveDialog(jfc);
            if (resp != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File arquivo = jfc.getSelectedFile();
            final List<String> comandos = new ArrayList<String>();
            comandos.add(salvo.getAbsolutePath()); //testado no windows xp       
            comandos.add("-i");
            comandos.add("-h");
            comandos.add(c.getServidor());    //ou   comandos.add("192.168.0.1");   
            comandos.add("-p");
            comandos.add(c.getPorta());
            comandos.add("-U");
            comandos.add(c.getUser());
            comandos.add("-d");
            comandos.add(c.getBase());
            comandos.add("-v");
            comandos.add(arquivo.getAbsolutePath());   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.    
            ProcessBuilder pb = new ProcessBuilder(comandos);
            pb.environment().put("PGPASSWORD", c.getSenha());     //Somente coloque sua senha           
            try {
                final Process process = pb.start();
                final BufferedReader r = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));
                String line = r.readLine();
                while (line != null) {
                    System.err.println(line);
                    txtArea.append(line + "\n");
                    line = r.readLine();
                }
                r.close();

                process.waitFor();
                process.destroy();
                JOptionPane.showMessageDialog(null, "Restaurado com sucesso !");
                this.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

        }
    }//GEN-LAST:event_btRestoreActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRestore;
    private javax.swing.JComboBox cmbBase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    private void loadCombo() {
        List<Conexao> lista = AbaConexaoTopComponent.lista;
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbBase.getModel();
        for (Conexao conexao : lista) {
            dcm.addElement(conexao);
        }

    }
}