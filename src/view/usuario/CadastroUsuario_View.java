/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.usuario;

import control.NivelAcesso_Controller;
import control.Usuario_Controller;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Funcionario_Model;
import model.NivelAcesso_Model;
import model.Usuario_Model;

/**
 *
 * @author claudio
 */
public class CadastroUsuario_View extends javax.swing.JDialog {

    /**
     * Creates new form CadastroUsuario_View
     */
    public CadastroUsuario_View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public CadastroUsuario_View(java.awt.Frame parent, boolean modal, Funcionario_Model func) {
        super(parent, modal);
        initComponents();
        tfUsuario.setEditable(true);
        usuario = new Usuario_Model();
        usuario.setFuncionario(func);
    }

    public CadastroUsuario_View(java.awt.Frame parent, boolean modal, Usuario_Model usr) {
        super(parent, modal);
        initComponents();
        usuario = usr;
        NivelAcesso_Controller nc = new NivelAcesso_Controller();

        preencherCampos(usuario, nc.verificaNiveisUsuario(usr));
    }

    Usuario_Model usuario;

    public void preencherCampos(Usuario_Model usr, List<NivelAcesso_Model> listaNivel) {

        tfUsuario.setText(usr.getUsuario());
        tfSenha.setText(usr.getSenha());

        if (usr.isAtivo()) {
            ckLiberarUtilizacao.setSelected(true);
        }

        if (!listaNivel.isEmpty()) {
            for (NivelAcesso_Model nv : listaNivel) {

                if (nv.getPermissao().equals("Caixa")) {
                    ckCaixa.setSelected(true);
                }

                if (nv.getPermissao().equals("Clientes")) {
                    ckClientes.setSelected(true);
                }

                if (nv.getPermissao().equals("Compras")) {
                    ckCompras.setSelected(true);
                }

                if (nv.getPermissao().equals("Condicional")) {
                    ckCondicionais.setSelected(true);
                }

                if (nv.getPermissao().equals("Fornecedor")) {
                    ckFornecedores.setSelected(true);
                }

                if (nv.getPermissao().equals("Funcionario")) {
                    ckFuncionarios.setSelected(true);
                }

                if (nv.getPermissao().equals("Pagar")) {
                    ckPagar.setSelected(true);
                }

                if (nv.getPermissao().equals("Produtos")) {
                    ckProdutos.setSelected(true);
                }

                if (nv.getPermissao().equals("Receber")) {
                    ckReceber.setSelected(true);
                }

                if (nv.getPermissao().equals("Vendas")) {
                    ckVendas.setSelected(true);
                }
            }
        }
    }

    public List<NivelAcesso_Model> capturaNivelNaoSelecionado(Usuario_Model usr) {

        List<NivelAcesso_Model> lista = new ArrayList<>();

        if (ckCaixa.isSelected()) {
        } else {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Caixa");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckClientes.isSelected()) {
        } else {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Clientes");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckCompras.isSelected()) {
        } else {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Compras");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckCondicionais.isSelected()) {
        } else {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Condicional");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckFornecedores.isSelected()) {
        } else {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Fornecedor");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckFuncionarios.isSelected()) {
        } else {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Funcionario");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckPagar.isSelected()) {
        } else {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Pagar");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckProdutos.isSelected()) {
        } else {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Produtos");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckReceber.isSelected()) {
        } else {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Receber");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckVendas.isSelected()) {
        } else {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Vendas");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        return lista;

    }

    public List<NivelAcesso_Model> capturaNivelSelecionado(Usuario_Model usr) {
        List<NivelAcesso_Model> lista = new ArrayList<>();

        if (ckCaixa.isSelected()) {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Caixa");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckClientes.isSelected()) {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Clientes");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckCompras.isSelected()) {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Compras");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckCondicionais.isSelected()) {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Condicional");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckFornecedores.isSelected()) {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Fornecedor");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckFuncionarios.isSelected()) {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Funcionario");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckPagar.isSelected()) {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Pagar");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckProdutos.isSelected()) {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Produtos");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckReceber.isSelected()) {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Receber");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        if (ckVendas.isSelected()) {
            NivelAcesso_Model nv = new NivelAcesso_Model();
            nv.setPermissao("Vendas");
            nv.setUsuario(usr);
            lista.add(nv);
        }

        return lista;

    }

    public Usuario_Model capturarCamposUser() {

        Usuario_Model usr = new Usuario_Model();
        if (usuario != null) {
            usr = usuario;
        }

        usr.setFuncionario(usuario.getFuncionario());
        usr.setUsuario(tfUsuario.getText());
        usr.setSenha(tfSenha.getText());
        if (ckLiberarUtilizacao.isSelected()) {
            usr.setAtivo(true);
        } else {
            usr.setAtivo(false);
        }

        return usr;
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
        tfUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfSenha = new javax.swing.JPasswordField();
        ckLiberarUtilizacao = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        ckVendas = new javax.swing.JCheckBox();
        ckCompras = new javax.swing.JCheckBox();
        ckCondicionais = new javax.swing.JCheckBox();
        ckCaixa = new javax.swing.JCheckBox();
        ckClientes = new javax.swing.JCheckBox();
        ckFuncionarios = new javax.swing.JCheckBox();
        ckFornecedores = new javax.swing.JCheckBox();
        ckPagar = new javax.swing.JCheckBox();
        ckReceber = new javax.swing.JCheckBox();
        ckProdutos = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UnicaBoutique - Cadastro de Usuário");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Usuário:");

        tfUsuario.setEditable(false);

        jLabel2.setText("Senha:");

        ckLiberarUtilizacao.setText("Liberar Utilização do Sistema");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfUsuario)
                    .addComponent(tfSenha)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(ckLiberarUtilizacao))
                        .addGap(0, 87, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckLiberarUtilizacao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Permissões"));

        ckVendas.setText("Vendas");

        ckCompras.setText("Compras");

        ckCondicionais.setText("Condicionais");

        ckCaixa.setText("Caixa");

        ckClientes.setText("Clientes");

        ckFuncionarios.setText("Funcionários");

        ckFornecedores.setText("Fornecedores");

        ckPagar.setText("Contas a Pagar");

        ckReceber.setText("Contas a Receber");

        ckProdutos.setText("Produtos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ckProdutos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ckPagar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ckCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ckVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ckClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ckCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(ckReceber))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckFuncionarios)
                            .addComponent(ckFornecedores)
                            .addComponent(ckCondicionais, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckVendas)
                    .addComponent(ckCompras)
                    .addComponent(ckCondicionais))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckCaixa)
                    .addComponent(ckClientes)
                    .addComponent(ckFuncionarios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckFornecedores)
                    .addComponent(ckReceber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckPagar)
                    .addComponent(ckProdutos)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490376769_sign-check.png"))); // NOI18N
        jButton1.setText("Gravar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377443_notification_error.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            Usuario_Model usr = new Usuario_Model();
            Usuario_Controller usc = new Usuario_Controller();
            NivelAcesso_Controller nvc = new NivelAcesso_Controller();
            List<Usuario_Model> listaExistenciaUser = usc.verificaExistenciaUsuario(tfUsuario.getText());
            usr = capturarCamposUser();
            Usuario_Model usAux = new Usuario_Model();

            if (!listaExistenciaUser.isEmpty()) {
                for (Usuario_Model us : listaExistenciaUser) {
                    usAux = us;
                }

                if (usr.getUsuario().equals(usAux.getUsuario()) && usr.getFuncionario().equals(usAux.getFuncionario())) {
                    usc.atualizaDados(usr);
                } else {
                    JOptionPane.showMessageDialog(null, "Já existe um usuário com esse nome!!");
                }
            } else {
                usc.gravaDados(usr);
            }

            // insere selecionados caso não estejam cadastrados no banco
            if (!capturaNivelSelecionado(usr).isEmpty()) {
                for (NivelAcesso_Model nv : capturaNivelSelecionado(usr)) {
                    if (nvc.pesquisaNivelAcesso(nv) == false) {
                        nvc.gravaDados(nv);
                    }
                }
            }

            // remove selecionados caso estejam cadastrados no banco
            if (!capturaNivelNaoSelecionado(usr).isEmpty()) {
                for (NivelAcesso_Model nv : capturaNivelNaoSelecionado(usr)) {
                    if (nvc.pesquisaNivelAcesso(nv) == true) {
                        nvc.excluirDados(nv);
                    }
                }
            }
            dispose();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar: \n" + e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroUsuario_View dialog = new CadastroUsuario_View(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ckCaixa;
    private javax.swing.JCheckBox ckClientes;
    private javax.swing.JCheckBox ckCompras;
    private javax.swing.JCheckBox ckCondicionais;
    private javax.swing.JCheckBox ckFornecedores;
    private javax.swing.JCheckBox ckFuncionarios;
    private javax.swing.JCheckBox ckLiberarUtilizacao;
    private javax.swing.JCheckBox ckPagar;
    private javax.swing.JCheckBox ckProdutos;
    private javax.swing.JCheckBox ckReceber;
    private javax.swing.JCheckBox ckVendas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField tfSenha;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}
