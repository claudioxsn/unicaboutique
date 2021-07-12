/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.compra;

import control.Compra_Controller;
import control.Itens_Compra_Controller;
import control.PagamentoCompra_Controller;
import control.Produto_Controller;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Compra_Model;
import model.Fornecedor_Model;
import model.Itens_Compra;
import model.PagamentoCompra_Model;
import model.Produto_Model;

/**
 *
 * @author Claudio Xavier
 */
public final class Nova_Compra_View extends javax.swing.JDialog {

    /**
     * Creates new form Nova_Compra_View
     */
    public Nova_Compra_View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Nova_Compra_View(java.awt.Frame parent, boolean modal, Compra_Model compMod) {
        super(parent, modal);
        initComponents();
        cc = new Compra_Controller();
        cm = compMod;
        preencherTabProdutos();
        preencherCampos();
        verificaTodosBoletosLancados();
        if (cm.getStatusCompra().equals("Cancelado") || cm.getStatusCompra().equals("Finalizada")) {
            statusComponentes(false);
        }

    }

    public Nova_Compra_View(java.awt.Frame parent, boolean modal, Fornecedor_Model fm) {
        super(parent, modal);
        initComponents();
        cc = new Compra_Controller();
        cm = new Compra_Model();
        cm.setFornecedor(fm);
        cm.setDataCompra(new Date());
        cm.setStatusCompra("Em Aberto");
        cm.setNumNota("0");
        cm.setValorCompra(0);
        cc.gravaDados(cm);
        preencherCampos();
    }

    Compra_Controller cc;
    Compra_Model cm;

    public void statusComponentes(boolean status) {
        btnNovo.setEnabled(status);
        btnAdicionar.setEnabled(status);
        btnCancelar.setEnabled(status);
        btnFinalizarPedido.setEnabled(status);
        btnRemover.setEnabled(status);
        btnSalvar.setEnabled(status);
        jcbbStatus.setEnabled(status);
        tfNumNota.setEnabled(status);
    }

    public void atualizaEstoque() {
        Produto_Controller pc = new Produto_Controller();
        Produto_Model pm;
        Itens_Compra_Controller icc = new Itens_Compra_Controller();

        for (Itens_Compra ic : icc.listaItensCompra(cm)) {
            pm = new Produto_Model();
            pm = ic.getProduto();
            pm.setQtdEstoque(pm.getQtdEstoque() + ic.getQuantidadeAtendida());
            pc.atualizaDados(pm);
        }
    }

    public void preencherCampos() {
        tfCod.setText(String.valueOf(cm.getNumCompra()));
        tfFornecedor.setText(cm.getFornecedor().getNomeFantasia());
        tfTotal.setText(String.format("%.2f", cm.getValorCompra()));
        jdcData.setDate(cm.getDataCompra());
        jcbbStatus.addItem(cm.getStatusCompra());
        jcbbStatus.setSelectedItem(cm.getStatusCompra());
        tfNumNota.setText(cm.getNumNota());
    }

    public void preencherTabProdutos() {

        Itens_Compra_Controller icc = new Itens_Compra_Controller();
        List<Itens_Compra> lista = icc.listaItensCompra(cm);
        DefaultTableModel dtm = (DefaultTableModel) jtDados.getModel();

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        for (Itens_Compra icm : lista) {
            dtm.addRow(new Object[]{icm.getProduto().getDescricaoProduto(), icm.getQuantidadeSolicitada(),
                icm.getQuantidadeAtendida(), String.format("%.2f", icm.getValorItem())});
        }

    }

    public void atualizaCompra() {
        cc = new Compra_Controller();
        cm = cc.pesquisaPorNumeroCompra(cm.getNumCompra());
        preencherCampos();
    }

    public void verificaTodosBoletosLancados() {
        PagamentoCompra_Controller pcc = new PagamentoCompra_Controller();
        double valor = 0;
        for (PagamentoCompra_Model pcm : pcc.listaParcelasCompra(cm)) {
            valor = valor + pcm.getValorBoleto();
        }

        if (valor < cm.getValorCompra()) {
            JOptionPane.showMessageDialog(null, "O valor dos boletos lançados não totalizam o valor da compra! Efetue todos os lançamentos!!");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfCod = new javax.swing.JTextField();
        jdcData = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jcbbStatus = new javax.swing.JComboBox<>();
        tfFornecedor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfNumNota = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDados = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnBoletos = new javax.swing.JButton();
        btnFinalizarPedido = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UnicaBoutique - Formulário de Compra");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Cód.:");

        jLabel2.setText("Fornecedor: ");

        jLabel3.setText("Data: ");

        tfCod.setEditable(false);
        tfCod.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jdcData.setEnabled(false);

        jLabel5.setText("Status: ");

        jcbbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em Aberto", "Aguardando Recebimento" }));

        tfFornecedor.setEditable(false);

        jLabel6.setText("Nota Nº: ");

        tfNumNota.setText("0");
        tfNumNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNumNotaActionPerformed(evt);
            }
        });
        tfNumNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNumNotaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCod, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(tfNumNota, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jdcData, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 286, Short.MAX_VALUE))
                    .addComponent(tfFornecedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfNumNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdcData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Qtd Solicitada", "Qtd Atendida", "Valor(R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtDados.getTableHeader().setReorderingAllowed(false);
        jtDados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtDadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtDados);
        if (jtDados.getColumnModel().getColumnCount() > 0) {
            jtDados.getColumnModel().getColumn(0).setResizable(false);
            jtDados.getColumnModel().getColumn(1).setResizable(false);
            jtDados.getColumnModel().getColumn(2).setResizable(false);
            jtDados.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Total(R$): ");

        tfTotal.setEditable(false);
        tfTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addComponent(tfTotal))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490376662_floppy.png"))); // NOI18N
        btnSalvar.setText("Gravar Pedido");
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setPreferredSize(new java.awt.Dimension(115, 59));
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(btnSalvar);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377490_shoppingcart_add.png"))); // NOI18N
        btnNovo.setText("Novo Item");
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setPreferredSize(new java.awt.Dimension(115, 59));
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        jPanel4.add(btnNovo);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490341473_sign-add.png"))); // NOI18N
        btnAdicionar.setText("Adicionar Qtd");
        btnAdicionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdicionar.setPreferredSize(new java.awt.Dimension(115, 59));
        btnAdicionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        jPanel4.add(btnAdicionar);

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_minus_1645995.png"))); // NOI18N
        btnRemover.setText("Remover Item");
        btnRemover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRemover.setPreferredSize(new java.awt.Dimension(115, 59));
        btnRemover.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        jPanel4.add(btnRemover);

        btnBoletos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1499301375_Calculator.png"))); // NOI18N
        btnBoletos.setText("Boletos");
        btnBoletos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBoletos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBoletos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoletosActionPerformed(evt);
            }
        });
        jPanel4.add(btnBoletos);

        btnFinalizarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_Properties_1493285.png"))); // NOI18N
        btnFinalizarPedido.setText("Finalizar");
        btnFinalizarPedido.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFinalizarPedido.setPreferredSize(new java.awt.Dimension(115, 59));
        btnFinalizarPedido.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFinalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarPedidoActionPerformed(evt);
            }
        });
        jPanel4.add(btnFinalizarPedido);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377443_notification_error.png"))); // NOI18N
        btnCancelar.setText("Cancelar Compra");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1483683822_logout.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        jPanel4.add(btnSair);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        Lista_Produtos_Compra_View lpc = new Lista_Produtos_Compra_View(null, true, cm);
        lpc.setVisible(true);
        preencherTabProdutos();
        atualizaCompra();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        int confirmacao2 = JOptionPane.showConfirmDialog(rootPane, "Deseja gravar as alterações efetuadas no pedido? ");
        if (confirmacao2 == JOptionPane.YES_OPTION) {
            cm.setStatusCompra((String) jcbbStatus.getSelectedItem());
            cm.setNumNota(tfNumNota.getText());
            cc.atualizaDados(cm);
            atualizaCompra();
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnFinalizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarPedidoActionPerformed
        // PagamentoCompra_Controller pcc = new PagamentoCompra_Controller();
        // double valor = 0;
        if (!cc.verificaExistenciaQtdAtendida(cm).isEmpty()) {
            JOptionPane.showMessageDialog(null, "A compra não pode ser finalizada pois há itens com quantidade atendida igual a 0(zero)!");
        } else {
            //  for (PagamentoCompra_Model pcm : pcc.listaParcelasCompra(cm)) {
            //     valor = valor + pcm.getValorBoleto();
            //    System.out.println("VALOR BOLETOS = " + valor);
            //   }
            //   if (valor == cm.getValorCompra()) {
            int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja Finalizar a Compra? ");
            if (confirmacao == JOptionPane.YES_OPTION) {
                cm.setStatusCompra("Finalizada");
                cm.setDataEntrega(new Date());
                cm.setNumNota(tfNumNota.getText());
                cc.atualizaDados(cm);
                atualizaEstoque();
                dispose();
                // }
            }/// else {
            //    JOptionPane.showMessageDialog(null, "A compra não pode ser finalizada enquanto o valor dos boletos não totalizarem o valor da compra!");

            // }
        }
    }//GEN-LAST:event_btnFinalizarPedidoActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja sair do formulário de Compra? ");
        if (confirmacao == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja Cancelar a Compra? ");
        if (confirmacao == JOptionPane.YES_OPTION) {
            Itens_Compra_Controller icc = new Itens_Compra_Controller();
            cm.setStatusCompra("Cancelado");
            cc.atualizaDados(cm);
            icc.atualizaItensCompra(cm, 0);
            dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        if (jtDados.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um Item na tabela para excluir!!");
        } else {
            int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja remover este item da compra? ");
            if (confirmacao == JOptionPane.YES_OPTION) {
                Itens_Compra_Controller icc = new Itens_Compra_Controller();
                Itens_Compra icm;
                int linha = jtDados.getSelectedRow();
                List<Itens_Compra> lista = icc.listaItensCompra(cm);
                icm = lista.get(linha);
                icc.excluirDados(icm);
            }
        }
        preencherTabProdutos();
        atualizaCompra();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        if (jtDados.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um Item na tabela para atualizar a quantidade!");
        } else {
            Itens_Compra_Controller icc = new Itens_Compra_Controller();
            Itens_Compra icm;
            int linha = jtDados.getSelectedRow();
            List<Itens_Compra> lista = icc.listaItensCompra(cm);
            icm = lista.get(linha);
            Dados_Item_Compra_View di = new Dados_Item_Compra_View(null, true, icm, "conf");
            di.setVisible(true);
        }
        preencherTabProdutos();
        atualizaCompra();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void tfNumNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNumNotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNumNotaActionPerformed

    private void tfNumNotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNumNotaKeyTyped
        if (caracteres.contains(evt.getKeyChar() + "") || simbolos.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_tfNumNotaKeyTyped

    private void btnBoletosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoletosActionPerformed
        double valor = 0;
        if (cm.getValorCompra() == 0) {
            JOptionPane.showMessageDialog(null, "Boletos não podem ser lançados pois a compra possui valor R$ 0,00!");
        } else {
            PagamentoCompra_Controller pcc = new PagamentoCompra_Controller();
            for (PagamentoCompra_Model pcm : pcc.listaParcelasCompra(cm)) {
                valor = valor + pcm.getValorBoleto();

            }

            if (valor == cm.getValorCompra()) {
                JOptionPane.showMessageDialog(null, "O valor dos boletos já totalizaram o valor da compra!");
            } else {
                double restante = cm.getValorCompra() - valor;
                CadastroBoletoCompra_View cbv = new CadastroBoletoCompra_View(null, true, cm, restante);
                cbv.setVisible(true);
            }
        }
        verificaTodosBoletosLancados();
    }//GEN-LAST:event_btnBoletosActionPerformed

    private void jtDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtDadosMouseClicked
        if (evt.getClickCount() == 1) {
            Itens_Compra_Controller icc = new Itens_Compra_Controller();
            Itens_Compra icm;
            int linha = jtDados.getSelectedRow();
            List<Itens_Compra> lista = icc.listaItensCompra(cm);
            icm = lista.get(linha);
            if (icm.getQuantidadeAtendida() > 0) {
                btnAdicionar.setEnabled(false);
            } else {
                btnAdicionar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jtDadosMouseClicked

    String caracteres = "abcdefghijklmnopqrstuvxzyABCDEFGHIJKLMNOPQRSTUVXZwWY";
    String numeros = "0123456789";
    String simbolos = "!@#$%¨&*()-=+[}{]:></?|\"' ";

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
            java.util.logging.Logger.getLogger(Nova_Compra_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nova_Compra_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nova_Compra_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nova_Compra_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Nova_Compra_View dialog = new Nova_Compra_View(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnBoletos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFinalizarPedido;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbbStatus;
    private com.toedter.calendar.JDateChooser jdcData;
    private javax.swing.JTable jtDados;
    private javax.swing.JTextField tfCod;
    private javax.swing.JTextField tfFornecedor;
    private javax.swing.JTextField tfNumNota;
    private javax.swing.JTextField tfTotal;
    // End of variables declaration//GEN-END:variables
}
