/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.principal;

import br.com.tcc.bean.Conexao;
import br.com.tcc.bean.Tabela;
import br.com.tcc.dao.TabelaDAO;
import br.com.tcc.principal.node.ConexaoChildren;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//br.com.tcc.principal//principal//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "principalTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "br.com.tcc.principal.principalTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_principalAction",
        preferredID = "principalTopComponent")
@Messages({
    "CTL_principalAction=principal",
    "CTL_principalTopComponent=principal Window",
    "HINT_principalTopComponent=This is a principal window"
})
public final class principalTopComponent extends TopComponent implements ExplorerManager.Provider{

    
    
    private transient ExplorerManager em = new ExplorerManager();
    
    public principalTopComponent() {
        initComponents();
        setName(Bundle.CTL_principalTopComponent());
        setToolTipText(Bundle.HINT_principalTopComponent());
        criarArvore();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jsPane = new BeanTreeView();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(628, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jsPane;
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
    
    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }
    
    public static void novaConexao() {
        TelaConexao c = new TelaConexao();
        c.setVisible(true);
    }
    
    public void criarArvore(){
         TabelaDAO tabDAO = new TabelaDAO();
         List<Tabela> listaTab = new ArrayList<Tabela>();
         List<Conexao> listaCon = new ArrayList<Conexao>();
         Conexao c = new Conexao();
         c.setNome("Teste");
         for (Tabela tabela1 : tabDAO.listaTabelas()) {
            Tabela t = new Tabela();
            t.setNome(tabela1.getNome());
            t.setListaColuna(tabDAO.listaColunas(tabela1));
            listaTab.add(t);
        }
         c.setListaTabelas(listaTab);
         Conexao con = new Conexao();
         con.setNome("Teste 2");
         con.setListaTabelas(listaTab);
         listaCon.add(c);
         listaCon.add(con);
         em.setRootContext(new RootNode(new ConexaoChildren(listaCon)));
         em.getRootContext().setDisplayName("Conexões");
         
    }
}
