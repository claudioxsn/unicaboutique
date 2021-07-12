/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.venda;

import control.Itens_Venda_Controller;
import control.Pagamento_Venda_Controller;
import control.Produto_Controller;
import control.Venda_Controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente_Model;
import model.Itens_Venda;
import model.Produto_Model;
import model.Venda_Model;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Claudio Xavier
 */
public class NovaVenda_View extends javax.swing.JDialog {

    /**
     * Creates new form NovaVenda_View
     */
    public NovaVenda_View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tfCodbar.grabFocus();
    }

    public NovaVenda_View(java.awt.Frame parent, boolean modal, Venda_Model vMod) {
        super(parent, modal);
        initComponents();
        Pagamento_Venda_Controller pvc = new Pagamento_Venda_Controller();
        vc = new Venda_Controller();
        vm = vMod;
        if ("Finalizada".equals(vm.getStatusVenda())) {
            statusBotoes(false);
        }
        preencherCampos();
        preencherTabProdutos();
    }

    public NovaVenda_View(java.awt.Frame parent, boolean modal, Cliente_Model cm) {
        super(parent, modal);
        initComponents();
        vc = new Venda_Controller();
        vm = new Venda_Model();
        vm.setCliente(cm);
        vm.setDataVenda(new Date());
        vm.setStatusVenda("Em Aberto");
        vc.gravaDados(vm);
        preencherCampos();

    }

    Venda_Controller vc;
    Venda_Model vm;

    public void emitirComprovante() {
        Itens_Venda_Controller ivc = new Itens_Venda_Controller();

        try {
            JasperPrint relatorioPopulado = JasperFillManager.
                    fillReport("src/relatorios/CumpomVenda.jasper",
                            null, new JRBeanCollectionDataSource(ivc.listaItensVenda(vm)));

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

    public void statusBotoes(boolean status) {
        btnFinalizar.setEnabled(status);
        btnIncluir.setEnabled(status);
        btnExcluir.setEnabled(status);
        btnCancelar.setEnabled(status);
        btnPesquisarFuncionario.setEnabled(status);

    }

    public void preencherCampos() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        tfCliente.setText(vm.getCliente().getNome());
        tfTotal.setText(String.format("%.2f", vm.getValorVenda()));
        tfDataVenda.setText(df.format(vm.getDataVenda()));
        tfNumVenda.setText(String.valueOf(vm.getNumVenda()));
        tfDescontoPercent.setText(String.format("%.2f", vm.getPercentDesconto()));
        tfDescontoReal.setText(String.format("%.2f", vm.getValorDesconto()));
        tfTotalDesc.setText(String.format("%.2f", vm.getValorComDesconto()));

        if (vm.getFuncionario() != null) {
            tfFuncionario.setText(vm.getFuncionario().getNome());
        }
    }

    public void atualizarVenda() {
        vm = vc.pesquisaVendaObjeto(vm);
        preencherCampos();
    }

    public void efetuaBaixaEstoque() {
        Produto_Controller pc = new Produto_Controller();
        Itens_Venda_Controller ivc = new Itens_Venda_Controller();
        List<Itens_Venda> lista = ivc.listaItensVenda(vm);

        for (Itens_Venda ivMod : lista) {
            Produto_Model pm = new Produto_Model();
            pm = ivMod.getProduto();
            pm.setQtdEstoque(pm.getQtdEstoque() - ivMod.getQuantidade());
            pc.atualizaDados(pm);
        }
    }

    public void preencherTabProdutos() {

        Itens_Venda_Controller ivc = new Itens_Venda_Controller();
        List<Itens_Venda> lista = ivc.listaItensVenda(vm);
        DefaultTableModel dtm = (DefaultTableModel) jtDados.getModel();

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        for (Itens_Venda ivm : lista) {
            dtm.addRow(new Object[]{ivm.getProduto().getDescricaoProduto(), ivm.getQuantidade(), String.format("%.2f", ivm.getValorItem())});
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

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        tfNumVenda = new javax.swing.JTextField();
        tfDataVenda = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfDescontoPercent = new javax.swing.JTextField();
        tfDescontoReal = new javax.swing.JTextField();
        tfTotalDesc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tfCliente = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfFuncionario = new javax.swing.JTextField();
        btnPesquisarFuncionario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDados = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnIncluir = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnEmitirNota = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        tfCodbar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UnicaBoutique - Formulário de Venda");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Venda Nº: ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Data Venda:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Total(R$): ");

        tfTotal.setEditable(false);
        tfTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfTotal.setForeground(new java.awt.Color(255, 0, 0));
        tfTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tfNumVenda.setEditable(false);
        tfNumVenda.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfNumVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tfDataVenda.setEditable(false);
        tfDataVenda.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfDataVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Desconto(%):");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Desconto(R$): ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Total Com Desconto(R$):");

        tfDescontoPercent.setEditable(false);
        tfDescontoPercent.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfDescontoPercent.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tfDescontoReal.setEditable(false);
        tfDescontoReal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfDescontoReal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tfTotalDesc.setEditable(false);
        tfTotalDesc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfTotalDesc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTotalDesc)
                    .addComponent(tfNumVenda, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfDataVenda)
                    .addComponent(tfTotal)
                    .addComponent(tfDescontoPercent)
                    .addComponent(tfDescontoReal)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1))
                        .addGap(0, 88, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNumVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDescontoPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDescontoReal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTotalDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Cliente: ");

        tfCliente.setEditable(false);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_system-search_118797 (1).png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setEnabled(false);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel3.setText("Vendedor:");

        tfFuncionario.setEditable(false);

        btnPesquisarFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_system-search_118797 (1).png"))); // NOI18N
        btnPesquisarFuncionario.setText("Pesquisar");
        btnPesquisarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarFuncionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfFuncionario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarFuncionario))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar)
                    .addComponent(tfFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarFuncionario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Quantidade", "Valor(R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490341473_sign-add.png"))); // NOI18N
        btnIncluir.setText("Incluir Produto");
        btnIncluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIncluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });
        jPanel4.add(btnIncluir);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377455_document_delete.png"))); // NOI18N
        btnExcluir.setText("Excluir Produto");
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(btnExcluir);

        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490376769_sign-check.png"))); // NOI18N
        btnFinalizar.setText("Finalizar Venda");
        btnFinalizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFinalizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });
        jPanel4.add(btnFinalizar);

        btnEmitirNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_documents-01_1622837.png"))); // NOI18N
        btnEmitirNota.setText("Emitir Comprovante");
        btnEmitirNota.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEmitirNota.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEmitirNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmitirNotaActionPerformed(evt);
            }
        });
        jPanel4.add(btnEmitirNota);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377443_notification_error.png"))); // NOI18N
        btnCancelar.setText("Cancelar Venda");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Código de Barras"));

        tfCodbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCodbarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfCodbar)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfCodbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        Lista_Produtos_Venda_View lpv = new Lista_Produtos_Venda_View(null, true, vm);
        lpv.setVisible(true);
        preencherCampos();
        preencherTabProdutos();
        atualizarVenda();
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (jtDados.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um Item na tabela para excluir!!");
        } else {
            int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja remover este item da Venda? ");
            if (confirmacao == JOptionPane.YES_OPTION) {
                Itens_Venda_Controller ivc = new Itens_Venda_Controller();
                Itens_Venda ivm;
                int linha = jtDados.getSelectedRow();
                List<Itens_Venda> lista = ivc.listaItensVenda(vm);
                ivm = lista.get(linha);
                ivc.excluirDados(ivm);
            }
        }
        preencherTabProdutos();
        atualizarVenda();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja Finalizar a Venda? ");
        if (confirmacao == JOptionPane.YES_OPTION) {
            Itens_Venda_Controller ivc = new Itens_Venda_Controller();
            if (ivc.listaItensVenda(vm).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Esta venda não pode ser finalizada por não possuir itens!");
            } else {
                atualizarVenda();
                Parcelamento_Venda_View pvv = new Parcelamento_Venda_View(null, true, vm);
                pvv.setVisible(true);
                Pagamento_Venda_Controller pvc = new Pagamento_Venda_Controller();
                if (pvc.verificaExistenciaDeParcelasDaVenda(vm) == true) {
                    vm.setStatusVenda("Finalizada");
                    vc.atualizaDados(vm);
                    efetuaBaixaEstoque();
                    statusBotoes(false);
                    emitirComprovante();
                }
            }
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja cancelar a Venda? ");
        if (confirmacao == JOptionPane.YES_OPTION) {
            Itens_Venda_Controller ivc = new Itens_Venda_Controller();
            if (ivc.listaItensVenda(vm).isEmpty()) {
                vm.setStatusVenda("Cancelada");
                vc.atualizaDados(vm);
            } else {
                // ivc.atualizarQtdTodosItensVenda(vm, 0);
                vm.setStatusVenda("Cancelada");
                vc.atualizaDados(vm);
                statusBotoes(false);
            }
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEmitirNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmitirNotaActionPerformed

        if (!"Finalizada".equals(vm.getStatusVenda())) {
            JOptionPane.showMessageDialog(null, "Não é possivel emitir o comprovante pois a venda não foi finalizada!!");
        } else {
            emitirComprovante();
        }
    }//GEN-LAST:event_btnEmitirNotaActionPerformed

    private void tfCodbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodbarActionPerformed
        Itens_Venda iv = new Itens_Venda();
        Produto_Controller pc = new Produto_Controller();
        Produto_Model pm = pc.pesquisaCodBarr(tfCodbar.getText());
        if (pm != null) {
            iv.setVenda(vm);
            iv.setProduto(pm);
            iv.setValorItem(pm.getValorVenda());

            if (pm.getQtdEstoque() < 1) {
                JOptionPane.showMessageDialog(null, "Não há exemplares do produto em estoque!");
            } else {
                Dados_Item_Venda_View dados = new Dados_Item_Venda_View(null, true, iv);
                dados.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
        }
        tfCodbar.setText("");
    }//GEN-LAST:event_tfCodbarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnPesquisarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarFuncionarioActionPerformed
        Listagem_Funcionario_Venda_View lf = new Listagem_Funcionario_Venda_View(null, true, vm);
        lf.setVisible(true);
        preencherCampos();
    }//GEN-LAST:event_btnPesquisarFuncionarioActionPerformed

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
            java.util.logging.Logger.getLogger(NovaVenda_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovaVenda_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovaVenda_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovaVenda_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NovaVenda_View dialog = new NovaVenda_View(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEmitirNota;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnPesquisarFuncionario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jtDados;
    private javax.swing.JTextField tfCliente;
    private javax.swing.JTextField tfCodbar;
    private javax.swing.JTextField tfDataVenda;
    private javax.swing.JTextField tfDescontoPercent;
    private javax.swing.JTextField tfDescontoReal;
    private javax.swing.JTextField tfFuncionario;
    private javax.swing.JTextField tfNumVenda;
    private javax.swing.JTextField tfTotal;
    private javax.swing.JTextField tfTotalDesc;
    // End of variables declaration//GEN-END:variables
}
