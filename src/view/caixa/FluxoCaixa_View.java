/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.caixa;

import control.CaixaController;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Caixa_Model;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Claudio Xavier
 */
public class FluxoCaixa_View extends javax.swing.JDialog {

    /**
     * Creates new form FluxoCaixa_View
     */
    public FluxoCaixa_View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jdcAte.setDate(new Date());
        jdcDe.setDate(new Date());
        preencherTabela();

    }

    Caixa_Model cm;

    public void preencherTabela() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        CaixaController cc = new CaixaController();
        boolean status;

        List<Caixa_Model> lista = cc.listaCaixaDataStatus(("Aberto" == jcbbStatus.getSelectedItem() ? true : false), jdcDe.getDate(), jdcAte.getDate());
        DefaultTableModel dtm = (DefaultTableModel) jtDados.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        lista.forEach((cm) -> {
            dtm.addRow(new Object[]{df.format(cm.getDataAbertura()), String.format("%.2f", cm.getSaldoAbertura()),
                String.format("%.2f", cm.getSaldoEntrada()), String.format("%.2f", cm.getSaldoSaida()), String.format("%.2f", (cm.getSaldoAbertura() + cm.getSaldoEntrada()) - cm.getSaldoSaida()), (true == cm.isStatus() ? "Aberto" : "Fechado")});
        });
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
        btnAbrirCaixa = new javax.swing.JButton();
        btnFecharCaixa = new javax.swing.JButton();
        btnNovaEntrada = new javax.swing.JButton();
        btnNovaSaida = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcbbStatus = new javax.swing.JComboBox<>();
        jdcDe = new com.toedter.calendar.JDateChooser();
        jdcAte = new com.toedter.calendar.JDateChooser();
        btnAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UnicaBoutique - Movimento de Caixa");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAbrirCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_14_Payment_1871430.png"))); // NOI18N
        btnAbrirCaixa.setText("Abrir Caixa");
        btnAbrirCaixa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrirCaixa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrirCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCaixaActionPerformed(evt);
            }
        });

        btnFecharCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377452_notification_done.png"))); // NOI18N
        btnFecharCaixa.setText("Fechar Caixa");
        btnFecharCaixa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFecharCaixa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFecharCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharCaixaActionPerformed(evt);
            }
        });

        btnNovaEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490376757_money.png"))); // NOI18N
        btnNovaEntrada.setText("Entrada");
        btnNovaEntrada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovaEntrada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaEntradaActionPerformed(evt);
            }
        });

        btnNovaSaida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_05_Wallet_1871422.png"))); // NOI18N
        btnNovaSaida.setText("Saída");
        btnNovaSaida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovaSaida.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovaSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaSaidaActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1483683822_logout.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1491932936_Print.png"))); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAbrirCaixa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFecharCaixa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNovaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNovaSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnNovaEntrada)
                        .addComponent(btnNovaSaida, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSair)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAbrirCaixa)
                            .addComponent(btnFecharCaixa))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Saldo de Abertura(R$)", "Entradas(R$)", "Saídas(R$)", "Saldo em Caixa(R$)", "Status"
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

        jLabel1.setText("Status");

        jLabel2.setText("De:");

        jLabel3.setText("Até: ");

        jcbbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aberto", "Fechado" }));
        jcbbStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbbStatusItemStateChanged(evt);
            }
        });

        jdcDe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcDePropertyChange(evt);
            }
        });

        jdcAte.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcAtePropertyChange(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jcbbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcDe, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jdcAte, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAtualizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcAte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jcbbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnAtualizar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCaixaActionPerformed
        CaixaController cc = new CaixaController();
        if (cc.verificaExistenciaCaixaComStatusAberto().isEmpty()) {
            cm = cc.pesquisaCaixa(new Date());
            if (cm == null) {
                AberturaCaixa_View ac = new AberturaCaixa_View(null, true);
                ac.setVisible(true);
                preencherTabela();
            } else if (cm.isStatus() == false) {
                int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Já existe um caixa nesta data, deseja efetuar a reabertura?");
                if (confirmacao == JOptionPane.YES_OPTION) {
                    cm.setStatus(true);
                    cc.atualizaDados(cm);
                    JOptionPane.showMessageDialog(null, "Reabertura do caixa efetuada!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "O caixa do dia já está aberto!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Há um ou mais caixas abertos no sistema, efetue o fechamento do caixa para efetuar a abertura do caixa do dia!");
        }

        preencherTabela();
    }//GEN-LAST:event_btnAbrirCaixaActionPerformed

    private void btnNovaEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaEntradaActionPerformed
        CaixaController cc = new CaixaController();
        Caixa_Model caMod = cc.pesquisaCaixa(new Date());
        if (caMod == null) {
            JOptionPane.showMessageDialog(null, "O caixa do dia deve estar aberto para que esta ação seja executada!");
        } else {
            ListagemEntradas_View lev = new ListagemEntradas_View(null, true, caMod);
            lev.setVisible(true);
            preencherTabela();
        }
    }//GEN-LAST:event_btnNovaEntradaActionPerformed

    private void btnNovaSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaSaidaActionPerformed
        CaixaController cc = new CaixaController();
        Caixa_Model caMod = cc.pesquisaCaixa(new Date());
        if (caMod == null) {
            JOptionPane.showMessageDialog(null, "O caixa do dia deve estar aberto para que esta ação seja executada!");
        } else {
            ListagemSaidas_View lsv = new ListagemSaidas_View(null, true, caMod);
            lsv.setVisible(true);
        }
        preencherTabela();
    }//GEN-LAST:event_btnNovaSaidaActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnFecharCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharCaixaActionPerformed
        if (jtDados.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um caixa na tabela!");
        } else {
            int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja efetuar o fechamento do Caixa? ");
            if (confirmacao == JOptionPane.YES_OPTION) {
                int linha = jtDados.getSelectedRow();
                CaixaController cc = new CaixaController();
                List<Caixa_Model> lista = cc.listaCaixaDataStatus(("Aberto" == jcbbStatus.getSelectedItem() ? true : false), jdcDe.getDate(), jdcAte.getDate());
                cm = lista.get(linha);
                cm.setStatus(false);
                cc.atualizaDados(cm);

                try {
                    JasperPrint relatorioPopulado = JasperFillManager.
                            fillReport("src/relatorios/FechamentoCaixa.jasper",
                                    null, new JRBeanCollectionDataSource(cc.imprimeCaixaSelecionado(cm)));

                    JasperViewer visualizaRelatorio = new JasperViewer(relatorioPopulado, true);
                    JDialog viwer = new JDialog(new javax.swing.JFrame(), "Visualização do Relatório", true);
                    viwer.setSize(1024, 768);
                    viwer.setLocationRelativeTo(null);
                    viwer.getContentPane().add(visualizaRelatorio.getContentPane());
                    viwer.setVisible(true);

                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
        preencherTabela();
    }//GEN-LAST:event_btnFecharCaixaActionPerformed

    private void jcbbStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbbStatusItemStateChanged
        if (jcbbStatus.getSelectedItem() == "Aberto") {
            btnFecharCaixa.setEnabled(true);
        } else {
            btnFecharCaixa.setEnabled(false);
        }
        preencherTabela();
    }//GEN-LAST:event_jcbbStatusItemStateChanged

    private void jdcDePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcDePropertyChange
        preencherTabela();
    }//GEN-LAST:event_jdcDePropertyChange

    private void jdcAtePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcAtePropertyChange
        preencherTabela();
    }//GEN-LAST:event_jdcAtePropertyChange

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        preencherTabela();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jtDados.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um caixa para executar esta ação");
        } else {
            int linha = jtDados.getSelectedRow();
            CaixaController cc = new CaixaController();

            List<Caixa_Model> lista = cc.listaCaixaDataStatus(("Aberto" == jcbbStatus.getSelectedItem() ? true : false), jdcDe.getDate(), jdcAte.getDate());
            Caixa_Model caMod = lista.get(linha);

            try {
                JasperPrint relatorioPopulado = JasperFillManager.
                        fillReport("src/relatorios/FechamentoCaixa.jasper",
                                null, new JRBeanCollectionDataSource((Collection<?>) cc.imprimeCaixaSelecionado(caMod)));

                JasperViewer visualizaRelatorio = new JasperViewer(relatorioPopulado, true);
                JDialog viwer = new JDialog(new javax.swing.JFrame(), "Visualização do Relatório", true);
                viwer.setSize(1024, 768);
                viwer.setLocationRelativeTo(null);
                viwer.getContentPane().add(visualizaRelatorio.getContentPane());
                viwer.setVisible(true);

            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FluxoCaixa_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FluxoCaixa_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FluxoCaixa_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FluxoCaixa_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FluxoCaixa_View dialog = new FluxoCaixa_View(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAbrirCaixa;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnFecharCaixa;
    private javax.swing.JButton btnNovaEntrada;
    private javax.swing.JButton btnNovaSaida;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbbStatus;
    private com.toedter.calendar.JDateChooser jdcAte;
    private com.toedter.calendar.JDateChooser jdcDe;
    private javax.swing.JTable jtDados;
    // End of variables declaration//GEN-END:variables
}