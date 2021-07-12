/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cliente;

import control.CaixaController;
import control.Pagamento_Venda_Controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Caixa_Model;
import model.Cliente_Model;
import model.PagamentoVenda_Model;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import relatorios.RelComprovantePagamento;
import view.caixa.AberturaCaixa_View;

/**
 *
 * @author Claudio Xavier
 */
public class DebitosCliente_View extends javax.swing.JDialog {

    /**
     * Creates new form DebitosCliente_View
     */
    public DebitosCliente_View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public DebitosCliente_View(java.awt.Frame parent, boolean modal, Cliente_Model cm) {
        super(parent, modal);
        initComponents();
        popularTabelaDebitosVenda(cm);
        cli = cm;
        calculaVencidas();
        preencherCampos();
        rbVencer.setSelected(true);
    }

    Cliente_Model cli;
    List<PagamentoVenda_Model> auxVenda;

    public void preencherCampos() {
        tfCliente.setText(cli.getNome());
        tfCpf.setText(cli.getCpf());
       // tfEndereco.setText(cli.getRua() + " - " + cli.getNumero() + " - " + cli.getComplemento());
    }

    public void popularTabelaDebitosVenda(Cliente_Model cm) {
        Pagamento_Venda_Controller posc = new Pagamento_Venda_Controller();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        List<PagamentoVenda_Model> pagVLista = posc.listarDebitoVendaCliente(cm);
        DefaultTableModel dtm = (DefaultTableModel) jtDadosVenda.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        for (PagamentoVenda_Model posv : pagVLista) {
            String pgto = "";
            if (posv.getDataPagamento() == null) {
                pgto = "";
            }
            dtm.addRow(new Object[]{
                posv.getVenda().getNumVenda(), posv.getNumParcela(), df.format(posv.getDataVencimento()), pgto, String.format("%.2f", posv.getValorParcela())
            });
        }
    }

    public void popularTabelaPagasVenda(Cliente_Model cm) {
        Pagamento_Venda_Controller posc = new Pagamento_Venda_Controller();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        List<PagamentoVenda_Model> pagVLista = posc.listarParcelasPagasCliente(cm);
        DefaultTableModel dtm = (DefaultTableModel) jtDadosVenda.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        for (PagamentoVenda_Model posv : pagVLista) {
            dtm.addRow(new Object[]{
                posv.getVenda().getNumVenda(), posv.getNumParcela(), df.format(posv.getDataVencimento()), df.format(posv.getDataPagamento().getDataAbertura()), String.format("%.2f", posv.getValorParcela())
            });
        }
    }

    public void pagarParcelasVenda(Caixa_Model caixaMod) {
        int parcVenda[] = jtDadosVenda.getSelectedRows();
        Pagamento_Venda_Controller posc = new Pagamento_Venda_Controller();
        CaixaController cc = new CaixaController();
        List<PagamentoVenda_Model> pagVLista = posc.listarDebitoVendaCliente(cli);
        auxVenda = new ArrayList<>();
        PagamentoVenda_Model pagVmod;
        for (int i = 0; i < parcVenda.length; i++) {
            pagVmod = pagVLista.get(parcVenda[i]);
            pagVmod.setDataPagamento(caixaMod);
            posc.atualizaDados(pagVmod);
            auxVenda.add(pagVmod);
            caixaMod.setSaldoEntrada(caixaMod.getSaldoEntrada() + pagVmod.getValorParcela());
            cc.atualizaDados(caixaMod);
        }
    }

    public void calculaVencidas() {
        double valor = 0;
        Pagamento_Venda_Controller pvc = new Pagamento_Venda_Controller();

        for (PagamentoVenda_Model pvm : pvc.listarParcelasVencidas(cli, new Date())) {
            valor = valor + pvm.getValorParcela();
        }

        tfVencido.setText(String.format("%.2f", valor));
    }

    public void emitirComprovantePagamento() {

        CaixaController cc = new CaixaController();
        Caixa_Model caixa = cc.pesquisaCaixa(new Date());
        Pagamento_Venda_Controller pvc = new Pagamento_Venda_Controller();
        List<RelComprovantePagamento> comprovante = new ArrayList<>();

        for (PagamentoVenda_Model pvm : auxVenda) {
            RelComprovantePagamento rcp = new RelComprovantePagamento();
            rcp.setCliente(pvm.getVenda().getCliente().getNome());
            rcp.setNumParcela(pvm.getNumParcela());
            rcp.setOsVenda(pvm.getVenda().getNumVenda());
            rcp.setPagamento(pvm.getDataPagamento().getDataAbertura());
            rcp.setValorParcela(pvm.getValorParcela());
            rcp.setVencimento(pvm.getDataVencimento());
            comprovante.add(rcp);
        }

        try {
            JasperPrint relatorioPopulado = JasperFillManager.
                    fillReport("src/relatorios/ReciboPagamento.jasper", null, new JRBeanCollectionDataSource(comprovante));
            JasperViewer visualizaRelatorio = new JasperViewer(relatorioPopulado, true);
            JDialog viwer = new JDialog(new javax.swing.JFrame(), "Visualização do Relatório", true);
            viwer.setSize(1024, 768);
            viwer.setLocationRelativeTo(null);
            viwer.getContentPane().add(visualizaRelatorio.getContentPane());
            viwer.setVisible(true);
        } catch (JRException jr) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + jr);
        }
    }

    public void calculaTotal() {
        if (rbVencer.isSelected()) {

            double valor = 0;
            int parcVenda[] = jtDadosVenda.getSelectedRows();
            Pagamento_Venda_Controller pvc = new Pagamento_Venda_Controller();
            List<PagamentoVenda_Model> pagVLista = pvc.listarDebitoVendaCliente(cli);

            for (int i = 0; i < parcVenda.length; i++) {
                valor = valor + pagVLista.get(parcVenda[i]).getValorParcela();
            }

            tfTotal.setText(String.format("%.2f", valor));
        } else {
            tfTotal.setText("0,00");
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
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfVencido = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtDadosVenda = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rbVencer = new javax.swing.JRadioButton();
        rbPaga = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfCliente = new javax.swing.JTextField();
        tfCpf = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UnicaBoutique - Débitos do Cliente");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPagar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPagar)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Total Selecionado: ");

        tfTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("Total Vencido:");

        tfVencido.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfVencido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfVencido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfVencidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfVencido, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTotal)
                    .addComponent(tfVencido))
                .addContainerGap())
        );

        jtDadosVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Venda", "Nº Parcela ", "Vencimento", "Pagamento", "Valor(R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtDadosVenda.setToolTipText("");
        jtDadosVenda.getTableHeader().setReorderingAllowed(false);
        jtDadosVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtDadosVendaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtDadosVenda);
        if (jtDadosVenda.getColumnModel().getColumnCount() > 0) {
            jtDadosVenda.getColumnModel().getColumn(0).setResizable(false);
            jtDadosVenda.getColumnModel().getColumn(1).setResizable(false);
            jtDadosVenda.getColumnModel().getColumn(2).setResizable(false);
            jtDadosVenda.getColumnModel().getColumn(3).setResizable(false);
            jtDadosVenda.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(rbVencer);
        rbVencer.setText("Em Aberto");
        rbVencer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbVencerItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rbPaga);
        rbPaga.setText("Pagas");
        rbPaga.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbPagaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbPaga)
                    .addComponent(rbVencer))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(rbVencer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbPaga)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Cliente: ");

        jLabel4.setText("CPF:");

        tfCliente.setEditable(false);

        tfCpf.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCpf)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        if (jtDadosVenda.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione as parcelas que serão pagas!!");
        } else {
            CaixaController cc = new CaixaController();
            if (cc.pesquisaCaixa(new Date()) == null) {
                JOptionPane.showMessageDialog(null, "Você precisa abrir o caixa para executar esta ação!");
                AberturaCaixa_View ac = new AberturaCaixa_View(null, true);
                ac.setVisible(true);
            } else if (cc.pesquisaCaixa(new Date()).isStatus() == false) {
                int confirmacao = JOptionPane.showConfirmDialog(rootPane, "O caixa do dia está fechado, deseja efetuar a reabertura?");
                if (confirmacao == JOptionPane.YES_OPTION) {
                    Caixa_Model cm = cc.pesquisaCaixa(new Date());
                    cm.setStatus(true);
                    cc.atualizaDados(cm);

                    pagarParcelasVenda(cm);
                    emitirComprovantePagamento();
                }
            } else {
                Caixa_Model caixa = cc.pesquisaCaixa(new Date());
                pagarParcelasVenda(caixa);
                emitirComprovantePagamento();
            }
        }
        popularTabelaDebitosVenda(cli);
        calculaTotal();
        calculaVencidas();
    }//GEN-LAST:event_btnPagarActionPerformed

    private void jtDadosVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtDadosVendaMouseClicked
        calculaTotal();
//        if (evt.getClickCount() == 2) {
//            Pagamento_Venda_Controller posc = new Pagamento_Venda_Controller();
//            List<PagamentoVenda_Model> pagVLista = posc.listarDebitoVendaCliente(cli);
//            PagamentoVenda_Model pagVmod = pagVLista.get(jtDadosVenda.getSelectedRow());
//            AlterarParcelaVenda_View apv = new AlterarParcelaVenda_View(null, true, pagVmod);
//            apv.setVisible(true);
//        }
    }//GEN-LAST:event_jtDadosVendaMouseClicked

    private void rbVencerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbVencerItemStateChanged
        popularTabelaDebitosVenda(cli);
        btnPagar.setEnabled(true);

    }//GEN-LAST:event_rbVencerItemStateChanged

    private void rbPagaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbPagaItemStateChanged
        popularTabelaPagasVenda(cli);
        btnPagar.setEnabled(false);

    }//GEN-LAST:event_rbPagaItemStateChanged

    private void tfVencidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfVencidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfVencidoActionPerformed

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
            java.util.logging.Logger.getLogger(DebitosCliente_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DebitosCliente_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DebitosCliente_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DebitosCliente_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DebitosCliente_View dialog = new DebitosCliente_View(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnPagar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtDadosVenda;
    private javax.swing.JRadioButton rbPaga;
    private javax.swing.JRadioButton rbVencer;
    private javax.swing.JTextField tfCliente;
    private javax.swing.JTextField tfCpf;
    private javax.swing.JTextField tfTotal;
    private javax.swing.JTextField tfVencido;
    // End of variables declaration//GEN-END:variables
}
