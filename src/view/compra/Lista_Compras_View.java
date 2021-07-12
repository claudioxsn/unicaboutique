/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.compra;

import control.Compra_Controller;
import control.Itens_Compra_Controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Compra_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Lista_Compras_View extends javax.swing.JDialog {

    /**
     * Creates new form Lista_Compras_View
     */
    public Lista_Compras_View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jdcAte.setDate(new Date());
        jdcDe.setDate(new Date());
        popularTabelaCompras(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());
    }

    public void popularTabelaCompras(Date dataIni, Date dataFim, String status) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Compra_Controller cc = new Compra_Controller();
        List<Compra_Model> lista = cc.pesquisaCompraData(dataIni, dataFim, status);
        DefaultTableModel dtm = (DefaultTableModel) jtDados.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        lista.forEach((cm) -> {

            dtm.addRow(new Object[]{cm.getNumCompra(),
                cm.getFornecedor().getNomeFantasia(),
                df.format(cm.getDataCompra()),
                (cm.getDataEntrega() == null ? "" : df.format(cm.getDataEntrega())),
                String.format("%.2f", cm.getValorCompra()), cm.getStatusCompra()});

        });

    }

    public void statusBotoes(boolean status) {
        btnAlterar.setEnabled(status);
        btnCancelar.setEnabled(status);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jdcDe = new com.toedter.calendar.JDateChooser();
        jdcAte = new com.toedter.calendar.JDateChooser();
        btnAtualizar = new javax.swing.JButton();
        jcbbStatus = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UnicaBoutique - Listagem de Pedidos");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("De: ");

        jLabel4.setText("Até: ");

        jdcDe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcDePropertyChange(evt);
            }
        });

        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490391341_reload.png"))); // NOI18N
        btnAtualizar.setText("Atualizar");
        btnAtualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAtualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        jcbbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em Aberto", "Aguardando Recebimento", "Finalizada", "Cancelado" }));
        jcbbStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbbStatusItemStateChanged(evt);
            }
        });

        jLabel1.setText("Status:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcDe, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jdcAte, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAtualizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAtualizar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcAte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº da Compra", "Fornecedor", "Data Pedido", "Data Entrega", "Valor(R$)", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtDados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtDados);
        if (jtDados.getColumnModel().getColumnCount() > 0) {
            jtDados.getColumnModel().getColumn(0).setResizable(false);
            jtDados.getColumnModel().getColumn(1).setResizable(false);
            jtDados.getColumnModel().getColumn(2).setResizable(false);
            jtDados.getColumnModel().getColumn(3).setResizable(false);
            jtDados.getColumnModel().getColumn(4).setResizable(false);
            jtDados.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377448_document_add.png"))); // NOI18N
        btnNovo.setText("Novo Pedido");
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377459_document_edit.png"))); // NOI18N
        btnAlterar.setText("Alterar Pedido");
        btnAlterar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlterar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377455_document_delete.png"))); // NOI18N
        btnCancelar.setText("Cancelar Pedido");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377446_document_search.png"))); // NOI18N
        btnVisualizar.setText("Visualizar Pedido");
        btnVisualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVisualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVisualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNovo)
                        .addComponent(btnAlterar)
                        .addComponent(btnCancelar))
                    .addComponent(btnVisualizar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jdcDePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcDePropertyChange
        popularTabelaCompras(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());       // TODO add your handling code here:
    }//GEN-LAST:event_jdcDePropertyChange

    private void jdcAtePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcAtePropertyChange
        popularTabelaCompras(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());        // TODO add your handling code here:
    }//GEN-LAST:event_jdcAtePropertyChange

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        Listagem_Fornecedor_Compra_View lf = new Listagem_Fornecedor_Compra_View(null, true);
        lf.setVisible(true);
        popularTabelaCompras(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (jtDados.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um pedido na tabela para executar esta ação!");
        } else {
            Compra_Controller cc = new Compra_Controller();
            Compra_Model cm;
            int linha = jtDados.getSelectedRow();
            List<Compra_Model> lista = cc.pesquisaCompraData(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());
            cm = lista.get(linha);
            Nova_Compra_View nc = new Nova_Compra_View(null, true, cm);
            nc.setVisible(true);
        }
        popularTabelaCompras(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        popularTabelaCompras(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());           // TODO add your handling code here:
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (jtDados.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um pedido na tabela para executar esta ação!");
        } else {
            int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja Cancelar este pedido?");
            if (confirmacao == JOptionPane.YES_OPTION) {
                Compra_Controller cc = new Compra_Controller();
                Itens_Compra_Controller icc = new Itens_Compra_Controller();
                Compra_Model cm;
                int linha = jtDados.getSelectedRow();
                List<Compra_Model> lista = cc.pesquisaCompraData(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());
                cm = lista.get(linha);
                cm.setStatusCompra("Cancelado");
                cc.atualizaDados(cm);
                icc.atualizaItensCompra(cm, 0);
            }
        }
        popularTabelaCompras(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jcbbStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbbStatusItemStateChanged
        if (jcbbStatus.getSelectedItem() == "Finalizada" || jcbbStatus.getSelectedItem() == "Cancelado") {
            popularTabelaCompras(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());
            statusBotoes(false);
        } else {
            popularTabelaCompras(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());
            statusBotoes(true);
        }
    }//GEN-LAST:event_jcbbStatusItemStateChanged

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        if (jtDados.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um pedido na tabela para executar esta ação!");
        } else {
            Compra_Controller cc = new Compra_Controller();
            Compra_Model cm;
            int linha = jtDados.getSelectedRow();
            List<Compra_Model> lista = cc.pesquisaCompraData(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());
            cm = lista.get(linha);
            Nova_Compra_View nc = new Nova_Compra_View(null, true, cm);
            nc.setVisible(true);
        }
        popularTabelaCompras(jdcDe.getDate(), jdcAte.getDate(), (String) jcbbStatus.getSelectedItem());
    }//GEN-LAST:event_btnVisualizarActionPerformed

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
            java.util.logging.Logger.getLogger(Lista_Compras_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lista_Compras_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lista_Compras_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lista_Compras_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Lista_Compras_View dialog = new Lista_Compras_View(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbbStatus;
    private com.toedter.calendar.JDateChooser jdcAte;
    private com.toedter.calendar.JDateChooser jdcDe;
    private javax.swing.JTable jtDados;
    // End of variables declaration//GEN-END:variables
}
