/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.compra;

import control.CaixaController;
import control.PagamentoCompra_Controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Caixa_Model;
import model.PagamentoCompra_Model;
import view.caixa.AberturaCaixa_View;

/**
 *
 * @author Claudio Xavier
 */
public class DebitosCompra_View extends javax.swing.JDialog {

    /**
     * Creates new form DebitosCliente_View
     */
    public DebitosCompra_View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jdcAte.setDate(new Date());
        jdcDe.setDate(new Date());
        rbPagar.setSelected(true);
        popularTabelaDebitosCompra();
    }

    public void popularTabelaDebitosCompra() {
        PagamentoCompra_Controller pcc = new PagamentoCompra_Controller();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        List<PagamentoCompra_Model> pagCompraLista = pcc.listaParcelasNaoPagasPeriodo(jdcDe.getDate(), jdcAte.getDate());
        DefaultTableModel dtm = (DefaultTableModel) jtDadosPagos.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        for (PagamentoCompra_Model pcm : pagCompraLista) {
            dtm.addRow(new Object[]{
                pcm.getCompra().getNumCompra(), pcm.getCompra().getNumNota(), pcm.getCompra().getFornecedor().getNomeFantasia(),
                pcm.getNumBoleto(), df.format(pcm.getDataVencimento()),
                "", String.format("%.2f", pcm.getValorBoleto())
            });
        }
    }

    public void popularTabelaPagos() {
        PagamentoCompra_Controller pcc = new PagamentoCompra_Controller();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        List<PagamentoCompra_Model> pagCompraLista = pcc.listaParcelasPagasPeriodo(jdcDe.getDate(), jdcAte.getDate());
        DefaultTableModel dtm = (DefaultTableModel) jtDadosPagos.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        for (PagamentoCompra_Model pcm : pagCompraLista) {
            dtm.addRow(new Object[]{
                pcm.getCompra().getNumCompra(), pcm.getCompra().getNumNota(), pcm.getCompra().getFornecedor().getNomeFantasia(),
                pcm.getNumBoleto(), df.format(pcm.getDataVencimento()), df.format(pcm.getDataPagamento().getDataAbertura()), String.format("%.2f", pcm.getValorBoleto())
            });
        }
    }

    public void pagarParcelas() {
        int parComp[] = jtDadosPagos.getSelectedRows();
        PagamentoCompra_Controller pcc = new PagamentoCompra_Controller();
        CaixaController cc = new CaixaController();
        Caixa_Model cm = cc.pesquisaCaixa(new Date());
        List<PagamentoCompra_Model> pagCompraLista = pcc.listaParcelasNaoPagasPeriodo(jdcDe.getDate(), jdcAte.getDate());
        PagamentoCompra_Model pagComMod;

        if (verificaExistenciaSaldo() == true) {
            for (int i = 0; i < parComp.length; i++) {
                pagComMod = pagCompraLista.get(parComp[i]);
                pagComMod.setDataPagamento(cm);
                pcc.atualizaDados(pagComMod);
                cm.setSaldoSaida(cm.getSaldoSaida() + pagComMod.getValorBoleto());
                cc.atualizaDados(cm);
            }
        }
    }

    public void calculaTotal() {
        double valor = 0;
        double valorPeriodo = 0;
        int parComp[] = jtDadosPagos.getSelectedRows();
        PagamentoCompra_Controller pcc = new PagamentoCompra_Controller();
        List<PagamentoCompra_Model> pagCompraLista = pcc.listaParcelasNaoPagasPeriodo(jdcDe.getDate(), jdcAte.getDate());

        for (int i = 0; i < parComp.length; i++) {
            valor = valor + pagCompraLista.get(parComp[i]).getValorBoleto();
        }
        tfTotal.setText(String.format("%.2f", valor));

        for (PagamentoCompra_Model pcm : pagCompraLista) {
            valorPeriodo = valorPeriodo + pcm.getValorBoleto();
        }
        tfTotalPeriodo.setText(String.format("%.2f", valorPeriodo));
    }

    public boolean verificaExistenciaSaldo() {
        double valor = 0;
        int parComp[] = jtDadosPagos.getSelectedRows();
        PagamentoCompra_Controller pcc = new PagamentoCompra_Controller();
        List<PagamentoCompra_Model> pagCompraLista = pcc.listaParcelasNaoPagasPeriodo(jdcDe.getDate(), jdcAte.getDate());
        CaixaController cc = new CaixaController();
        Caixa_Model cm = cc.pesquisaCaixa(new Date());

        for (int i = 0; i < parComp.length; i++) {
            valor = valor + pagCompraLista.get(parComp[i]).getValorBoleto();
        }

        double saldoCaixa = (cm.getSaldoAbertura() + cm.getSaldoEntrada()) - cm.getSaldoSaida();
        if (saldoCaixa < valor) {
            JOptionPane.showMessageDialog(null, "Não há saldo em caixa para efetuar o pagamento");
            return false;
        } else {
            return true;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        btnPagar = new javax.swing.JButton();
        btnCancelarPagamento = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfTotalPeriodo = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jdcDe = new com.toedter.calendar.JDateChooser();
        jdcAte = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtDadosPagos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        rbPagos = new javax.swing.JRadioButton();
        rbPagar = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UnicaBoutique - Débitos de Compras");
        setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490376757_money.png"))); // NOI18N
        btnPagar.setText("Efetuar Pagamento");
        btnPagar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPagar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        btnCancelarPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377455_document_delete.png"))); // NOI18N
        btnCancelarPagamento.setText("Cancelar Pagamento");
        btnCancelarPagamento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelarPagamento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPagamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarPagamento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelarPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Total Selecionado: ");

        tfTotal.setEditable(false);
        tfTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setText("Total do Período:");

        tfTotalPeriodo.setEditable(false);
        tfTotalPeriodo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfTotalPeriodo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTotalPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTotal)
                    .addComponent(tfTotalPeriodo))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jLabel2.setText("De:");

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

        jLabel3.setText("Até: ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jdcDe, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jdcAte, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(53, 53, 53))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcAte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtDadosPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Compra", "Nº Nota", "Fornecedor", "Nº Boleto", "Data Vencimento", "Data Pagamento", "Valor(R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtDadosPagos.setToolTipText("");
        jtDadosPagos.getTableHeader().setReorderingAllowed(false);
        jtDadosPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtDadosPagosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtDadosPagos);
        if (jtDadosPagos.getColumnModel().getColumnCount() > 0) {
            jtDadosPagos.getColumnModel().getColumn(0).setResizable(false);
            jtDadosPagos.getColumnModel().getColumn(1).setResizable(false);
            jtDadosPagos.getColumnModel().getColumn(2).setResizable(false);
            jtDadosPagos.getColumnModel().getColumn(3).setResizable(false);
            jtDadosPagos.getColumnModel().getColumn(4).setResizable(false);
            jtDadosPagos.getColumnModel().getColumn(5).setResizable(false);
            jtDadosPagos.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(rbPagos);
        rbPagos.setText("Pagos");
        rbPagos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbPagosItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rbPagar);
        rbPagar.setText("A Pagar");
        rbPagar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbPagarItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(rbPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(rbPagos)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(rbPagos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbPagar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        if (jtDadosPagos.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione as parcelas que serão pagas!!");
        } else {
            CaixaController cc = new CaixaController();
            Caixa_Model cm = cc.pesquisaCaixa(new Date());
            if (cm == null) {
                JOptionPane.showMessageDialog(null, "Você precisa abrir o caixa para executar esta ação!");
                AberturaCaixa_View ac = new AberturaCaixa_View(null, true);
                ac.setVisible(true);
            } else if (cm.isStatus() == false) {
                int confirmacao = JOptionPane.showConfirmDialog(rootPane, "O caixa do dia está fechado, deseja efetuar a reabertura?");
                if (confirmacao == JOptionPane.YES_OPTION) {
                    cm.setStatus(true);
                    cc.atualizaDados(cm);
                    JOptionPane.showMessageDialog(null, "Reabertura do caixa efetuada!");
                }
            } else {
                pagarParcelas();
            }
        }

        popularTabelaDebitosCompra();
    }//GEN-LAST:event_btnPagarActionPerformed

    private void jtDadosPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtDadosPagosMouseClicked
        if (rbPagar.isSelected()) {
            calculaTotal();
        }
    }//GEN-LAST:event_jtDadosPagosMouseClicked

    private void jdcDePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcDePropertyChange
        if (rbPagar.isSelected()) {
            popularTabelaDebitosCompra();
        } else {
            popularTabelaPagos();
        }
        calculaTotal();
    }//GEN-LAST:event_jdcDePropertyChange

    private void jdcAtePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcAtePropertyChange
        if (rbPagar.isSelected()) {
            popularTabelaDebitosCompra();
        } else {
            popularTabelaPagos();
        }
        calculaTotal();
    }//GEN-LAST:event_jdcAtePropertyChange

    private void btnCancelarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPagamentoActionPerformed
        PagamentoCompra_Controller pcc = new PagamentoCompra_Controller();
        CaixaController cc = new CaixaController();
        Caixa_Model cm = cc.pesquisaCaixa(new Date());

        List<PagamentoCompra_Model> pagCompraLista = pcc.listaParcelasPagasPeriodo(jdcDe.getDate(), jdcAte.getDate());
        PagamentoCompra_Model pagCompraMod = pagCompraLista.get(jtDadosPagos.getSelectedRow());
        if (pagCompraMod.getDataPagamento().getDataAbertura().before(cm.getDataAbertura())) {
            JOptionPane.showMessageDialog(null, "Não é possivel cancelar pagamento de dias anteriores!");
        } else {
            pagCompraMod.setDataPagamento(null);
            pcc.atualizaDados(pagCompraMod);
            cm.setSaldoSaida(cm.getSaldoSaida() - pagCompraMod.getValorBoleto());
            cc.atualizaDados(cm);
        }

    }//GEN-LAST:event_btnCancelarPagamentoActionPerformed

    private void rbPagosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbPagosItemStateChanged
        popularTabelaPagos();
        btnPagar.setEnabled(false);
        btnCancelarPagamento.setEnabled(true);
    }//GEN-LAST:event_rbPagosItemStateChanged

    private void rbPagarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbPagarItemStateChanged
        popularTabelaDebitosCompra();
        btnCancelarPagamento.setEnabled(false);
        btnPagar.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_rbPagarItemStateChanged

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
            java.util.logging.Logger.getLogger(DebitosCompra_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DebitosCompra_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DebitosCompra_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DebitosCompra_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DebitosCompra_View dialog = new DebitosCompra_View(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelarPagamento;
    private javax.swing.JButton btnPagar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcAte;
    private com.toedter.calendar.JDateChooser jdcDe;
    private javax.swing.JTable jtDadosPagos;
    private javax.swing.JRadioButton rbPagar;
    private javax.swing.JRadioButton rbPagos;
    private javax.swing.JTextField tfTotal;
    private javax.swing.JTextField tfTotalPeriodo;
    // End of variables declaration//GEN-END:variables
}
