/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contasdiversas;

import control.CaixaController;
import control.Contas_Diversas_Controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Caixa_Model;
import model.Contas_Diversas_Model;
import view.caixa.AberturaCaixa_View;

/**
 *
 * @author Claudio Xavier
 */
public final class Lista_Contas_Diversas_View extends javax.swing.JDialog {

    /**
     * Creates new form Form_Contas_Pagar
     */
    public Lista_Contas_Diversas_View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jdcPagarDe.setDate(new Date());
        jdcPagarAte.setDate(new Date());
        jdcPagasDe.setDate(new Date());
        jdcPagasAte.setDate(new Date());
        popularTabelaContasPagarComPesquisa(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
        popularTabelaContasPagas(tfPesquisaPagas.getText(), jdcPagasDe.getDate(), jdcPagasAte.getDate());
    }

    /* ------------------------------------------------- CONTAS A PAGAR ------------------------------------------------------------------*/
    public void editarContaPagar(int linha) {
        Contas_Diversas_Controller cdc = new Contas_Diversas_Controller();
        Contas_Diversas_Model cdm;

        List<Contas_Diversas_Model> lista = cdc.contasPagarPorVencimentoNomeFantasia(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
        cdm = lista.get(linha);

        Cadastro_ContasDiversas_View cadContas = new Cadastro_ContasDiversas_View(null, true, cdm);
        cadContas.setVisible(true);

    }

    public void popularTabelaContasPagarComPesquisa(String pesquisa, Date dataIni, Date dataFim) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Contas_Diversas_Controller cdc = new Contas_Diversas_Controller();
        List<Contas_Diversas_Model> lista = cdc.contasPagarPorVencimentoNomeFantasia(pesquisa, dataIni, dataFim);

        DefaultTableModel dtm = (DefaultTableModel) jtDadosContasPagar.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        for (Contas_Diversas_Model cdm : lista) {
            dtm.addRow(new Object[]{cdm.getNumDocumento(), cdm.getDescricao(), cdm.getFornecedor(),
                df.format(cdm.getDataVencimento()), String.format("%.2f", cdm.getValorDocumento())});
        }
    }

    public void calcularValoresVencer(String pesquisa, Date dataIni, Date dataFim) {
        double valor = 0;
        Contas_Diversas_Controller cdc = new Contas_Diversas_Controller();
        List<Contas_Diversas_Model> lista = cdc.contasPagarPorVencimentoNomeFantasia(pesquisa, dataIni, dataFim);

        for (Contas_Diversas_Model cdm : lista) {

            valor += cdm.getValorDocumento();

        }
        tfTotalVencer.setText(String.format("%.2f", valor));
    }

    /* public void calcularValoresPagos(String pesquisa, Date dataIni, Date dataFim) {
        double valor = 0;
        Contas_Diversas_Controller cdc = new Contas_Diversas_Controller();
        List<Contas_Diversas_Model> lista = cdc.contasPagasPorPagamentoNomeFantasia(pesquisa, dataIni, dataFim);

        valor = lista.stream().map((cdm) -> {
            System.out.println(cdm.getValorDocumento());
            return cdm;
        }).map((cdm) -> cdm.getValorDocumento()).reduce(valor, (accumulator, _item) -> accumulator + _item);
        tfTotalPago.setText(String.valueOf(valor));
    }
     */
    public void chamadaDeMetodos() {
        popularTabelaContasPagarComPesquisa(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
        popularTabelaContasPagas(tfPesquisaPagas.getText(), jdcPagasDe.getDate(), jdcPagasAte.getDate());
        calcularValoresVencer(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
    }

    public void popularTabelaContasPagar() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Contas_Diversas_Controller cdc = new Contas_Diversas_Controller();
        List<Contas_Diversas_Model> lista = cdc.listarTodas();

        DefaultTableModel dtm = (DefaultTableModel) jtDadosContasPagar.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        for (Contas_Diversas_Model cdm : lista) {
            dtm.addRow(new Object[]{cdm.getNumDocumento(), cdm.getDescricao(), cdm.getFornecedor(),
                df.format(cdm.getDataVencimento()), String.format("%.2f", cdm.getValorDocumento())});
        }
    }

    public void pagarConta(int linha[], Caixa_Model cm) {
        Contas_Diversas_Controller cdc = new Contas_Diversas_Controller();
        CaixaController cc = new CaixaController();
        Contas_Diversas_Model cdm;

        List<Contas_Diversas_Model> lista = cdc.contasPagarPorVencimentoNomeFantasia(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
        if (verificaExistenciaSaldo() == true) {
            for (int i = 0; i < linha.length; i++) {
                cdm = lista.get(linha[i]);
                cdm.setDataPagamento(cm);
                cm.setSaldoSaida(cm.getSaldoSaida() + cdm.getValorDocumento());
                cdc.atualizaDados(cdm);
                cc.atualizaDados(cm);
            }
        }
    }

    /*------------------------------------  CONTAS A PAGAR -------------------------------------------------------------------*/
    public void popularTabelaContasPagas(String pesquisa, Date dataInicio, Date dataFim) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Contas_Diversas_Controller cdc = new Contas_Diversas_Controller();
        List<Contas_Diversas_Model> lista = cdc.contasPagasPorPagamentoNomeFantasia(pesquisa, dataInicio, dataFim);

        DefaultTableModel dtm = (DefaultTableModel) jtDadosContasPagas.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        for (Contas_Diversas_Model cdm : lista) {
            dtm.addRow(new Object[]{cdm.getNumDocumento(), cdm.getDescricao(), cdm.getFornecedor(),
                df.format(cdm.getDataVencimento()), df.format(cdm.getDataPagamento().getDataAbertura()), String.format("%.2f", cdm.getValorDocumento())});
        }
    }

    public boolean verificaExistenciaSaldo() {
        double valor = 0;
        int pagComp[] = jtDadosContasPagar.getSelectedRows();
        Contas_Diversas_Controller cdc = new Contas_Diversas_Controller();
        List<Contas_Diversas_Model> pagConta = cdc.contasPagarPorVencimentoNomeFantasia(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
        CaixaController cc = new CaixaController();
        Caixa_Model cm = cc.pesquisaCaixa(new Date());

        for (int i = 0; i < pagComp.length; i++) {
            valor = valor + pagConta.get(pagComp[i]).getValorDocumento();
        }

        double saldoCaixa = (cm.getSaldoAbertura() + cm.getSaldoEntrada()) - cm.getSaldoSaida();
        if (saldoCaixa < valor) {
            JOptionPane.showMessageDialog(null, "Não há saldo em caixa para efetuar o pagamento");
            return false;
        } else {
            return true;
        }
    }

    public void calculaTotalSelecionado() {
        double valor = 0;
        double valorPeriodo = 0;
        int pagCont[] = jtDadosContasPagar.getSelectedRows();
        Contas_Diversas_Controller cdc = new Contas_Diversas_Controller();
        List<Contas_Diversas_Model> pagConta = cdc.contasPagarPorVencimentoNomeFantasia(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());

        for (int i = 0; i < pagCont.length; i++) {
            valor = valor + pagConta.get(pagCont[i]).getValorDocumento();
        }
        tfTotalPago.setText(String.format("%.2f", valor));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jdcPagarDe = new com.toedter.calendar.JDateChooser();
        jdcPagarAte = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tfPesquisa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtDadosContasPagar = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnNovoLancamento = new javax.swing.JButton();
        btnAlterarLancamento = new javax.swing.JButton();
        btnExcluirLancamento = new javax.swing.JButton();
        btnPagarConta = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfTotalVencer = new javax.swing.JTextField();
        tfTotalPago = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        tfPesquisaPagas = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jdcPagasDe = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jdcPagasAte = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtDadosContasPagas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UnicaBoutique - Formulário de Despesas (Pagas/à Pagar)");
        setResizable(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Entre Datas de Vencimento"));

        jLabel13.setText("Até:");

        jLabel12.setText("De:");

        jdcPagarDe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcPagarDePropertyChange(evt);
            }
        });

        jdcPagarAte.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcPagarAtePropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jdcPagarDe, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcPagarAte, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcPagarDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcPagarAte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Fornecedor"));

        jLabel10.setText("Pesquisar:");

        tfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisaKeyReleased(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_system-search_118797 (1).png"))); // NOI18N
        jButton1.setText("Pesquisar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtDadosContasPagar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº da Nota", "Descrição", "Fornecedor", "Vencimento", "Valor(R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtDadosContasPagar.setToolTipText("");
        jtDadosContasPagar.getTableHeader().setReorderingAllowed(false);
        jtDadosContasPagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtDadosContasPagarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtDadosContasPagar);
        if (jtDadosContasPagar.getColumnModel().getColumnCount() > 0) {
            jtDadosContasPagar.getColumnModel().getColumn(0).setResizable(false);
            jtDadosContasPagar.getColumnModel().getColumn(1).setResizable(false);
            jtDadosContasPagar.getColumnModel().getColumn(2).setResizable(false);
            jtDadosContasPagar.getColumnModel().getColumn(3).setResizable(false);
            jtDadosContasPagar.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        btnNovoLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377448_document_add.png"))); // NOI18N
        btnNovoLancamento.setText("Novo");
        btnNovoLancamento.setToolTipText("Nova Despesa");
        btnNovoLancamento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovoLancamento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovoLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoLancamentoActionPerformed(evt);
            }
        });
        jPanel7.add(btnNovoLancamento);

        btnAlterarLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377459_document_edit.png"))); // NOI18N
        btnAlterarLancamento.setText("Alterar");
        btnAlterarLancamento.setToolTipText("Alterar Despesa");
        btnAlterarLancamento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlterarLancamento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAlterarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarLancamentoActionPerformed(evt);
            }
        });
        jPanel7.add(btnAlterarLancamento);

        btnExcluirLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377455_document_delete.png"))); // NOI18N
        btnExcluirLancamento.setText("Excluir");
        btnExcluirLancamento.setToolTipText("Excluir Despesa");
        btnExcluirLancamento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluirLancamento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluirLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirLancamentoActionPerformed(evt);
            }
        });
        jPanel7.add(btnExcluirLancamento);

        btnPagarConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490376757_money.png"))); // NOI18N
        btnPagarConta.setText("Pagar Conta");
        btnPagarConta.setToolTipText("Pagar Despesa");
        btnPagarConta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPagarConta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPagarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarContaActionPerformed(evt);
            }
        });
        jPanel7.add(btnPagarConta);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Total do Período:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Total Selecionado:");

        tfTotalVencer.setEditable(false);
        tfTotalVencer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfTotalVencer.setForeground(new java.awt.Color(255, 0, 0));
        tfTotalVencer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTotalVencer.setText("0,00");
        tfTotalVencer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTotalVencerActionPerformed(evt);
            }
        });

        tfTotalPago.setEditable(false);
        tfTotalPago.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfTotalPago.setForeground(new java.awt.Color(0, 0, 255));
        tfTotalPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTotalPago.setText("0,00");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tfTotalVencer, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(tfTotalPago))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTotalVencer)
                    .addComponent(tfTotalPago))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Contas a Pagar", jPanel3);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome Fantasia"));

        jLabel14.setText("Pesquisar:");

        tfPesquisaPagas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisaPagasKeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_system-search_118797 (1).png"))); // NOI18N
        jButton2.setText("Pesquisar");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(tfPesquisaPagas, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPesquisaPagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Entre Datas de Pagamento"));

        jLabel15.setText("De:");

        jdcPagasDe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcPagasDePropertyChange(evt);
            }
        });

        jLabel16.setText("Até:");

        jdcPagasAte.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcPagasAtePropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jdcPagasDe, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcPagasAte, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdcPagasDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcPagasAte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtDadosContasPagas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Documento", "Descrição", "Fornecedor", "Vencimento", "Pagamento", "Valor(R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtDadosContasPagas.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtDadosContasPagas);
        if (jtDadosContasPagas.getColumnModel().getColumnCount() > 0) {
            jtDadosContasPagas.getColumnModel().getColumn(0).setResizable(false);
            jtDadosContasPagas.getColumnModel().getColumn(1).setResizable(false);
            jtDadosContasPagas.getColumnModel().getColumn(2).setResizable(false);
            jtDadosContasPagas.getColumnModel().getColumn(3).setResizable(false);
            jtDadosContasPagas.getColumnModel().getColumn(4).setResizable(false);
            jtDadosContasPagas.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Contas Pagas", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoLancamentoActionPerformed
        Cadastro_ContasDiversas_View ccd = new Cadastro_ContasDiversas_View(null, true);
        ccd.setVisible(true);
        popularTabelaContasPagarComPesquisa(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
        calcularValoresVencer(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
    }//GEN-LAST:event_btnNovoLancamentoActionPerformed

    private void btnAlterarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarLancamentoActionPerformed
        if (jtDadosContasPagar.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(rootPane, "Selecione uma conta na Tabela!");
        } else {
            int linha = jtDadosContasPagar.getSelectedRow();
            editarContaPagar(linha);
        }
        chamadaDeMetodos();
    }//GEN-LAST:event_btnAlterarLancamentoActionPerformed

    private void btnExcluirLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirLancamentoActionPerformed
        if (jtDadosContasPagar.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao excluir!\nSelecione um item na tabela de contas a pagar para excluir.");
        } else {
            int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja excluir o cadastro? ");
            if (confirmacao == JOptionPane.YES_OPTION) {
                Contas_Diversas_Controller cdc = new Contas_Diversas_Controller();
                Contas_Diversas_Model cdm;
                int linha = jtDadosContasPagar.getSelectedRow();
                List<Contas_Diversas_Model> lista = cdc.listarTodas();
                cdm = lista.get(linha);
                cdc.excluirDados(cdm.getId());
            }
        }
        chamadaDeMetodos();
    }//GEN-LAST:event_btnExcluirLancamentoActionPerformed

    private void btnPagarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarContaActionPerformed
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
        } else if (jtDadosContasPagar.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(rootPane, "Selecione uma linha da tabela  de contas a pagar para efetuar a baixa.");
        } else {
            int linha[] = jtDadosContasPagar.getSelectedRows();
            pagarConta(linha, cc.pesquisaCaixa(new Date()));
        }
        chamadaDeMetodos();
    }//GEN-LAST:event_btnPagarContaActionPerformed

    private void tfPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaKeyReleased
        chamadaDeMetodos();
    }//GEN-LAST:event_tfPesquisaKeyReleased

    private void jdcPagarDePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcPagarDePropertyChange
        popularTabelaContasPagarComPesquisa(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
        calcularValoresVencer(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
    }//GEN-LAST:event_jdcPagarDePropertyChange

    private void jdcPagarAtePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcPagarAtePropertyChange
        popularTabelaContasPagarComPesquisa(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
        calcularValoresVencer(tfPesquisa.getText(), jdcPagarDe.getDate(), jdcPagarAte.getDate());
    }//GEN-LAST:event_jdcPagarAtePropertyChange

    private void jdcPagasDePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcPagasDePropertyChange
        popularTabelaContasPagas(tfPesquisaPagas.getText(), jdcPagasDe.getDate(), jdcPagasAte.getDate());
    }//GEN-LAST:event_jdcPagasDePropertyChange

    private void tfPesquisaPagasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaPagasKeyReleased
        popularTabelaContasPagas(tfPesquisaPagas.getText(), jdcPagasDe.getDate(), jdcPagasAte.getDate());
    }//GEN-LAST:event_tfPesquisaPagasKeyReleased

    private void jdcPagasAtePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcPagasAtePropertyChange
        popularTabelaContasPagas(tfPesquisaPagas.getText(), jdcPagasDe.getDate(), jdcPagasAte.getDate());
    }//GEN-LAST:event_jdcPagasAtePropertyChange

    private void tfTotalVencerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTotalVencerActionPerformed

    }//GEN-LAST:event_tfTotalVencerActionPerformed

    private void jtDadosContasPagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtDadosContasPagarMouseClicked
        if (evt.getClickCount() == 1) {
            calculaTotalSelecionado();
        }// TODO add your handling code here:
    }//GEN-LAST:event_jtDadosContasPagarMouseClicked

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
            java.util.logging.Logger.getLogger(Lista_Contas_Diversas_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lista_Contas_Diversas_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lista_Contas_Diversas_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lista_Contas_Diversas_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Lista_Contas_Diversas_View dialog = new Lista_Contas_Diversas_View(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAlterarLancamento;
    private javax.swing.JButton btnExcluirLancamento;
    private javax.swing.JButton btnNovoLancamento;
    private javax.swing.JButton btnPagarConta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcPagarAte;
    private com.toedter.calendar.JDateChooser jdcPagarDe;
    private com.toedter.calendar.JDateChooser jdcPagasAte;
    private com.toedter.calendar.JDateChooser jdcPagasDe;
    private javax.swing.JTable jtDadosContasPagar;
    private javax.swing.JTable jtDadosContasPagas;
    private javax.swing.JTextField tfPesquisa;
    private javax.swing.JTextField tfPesquisaPagas;
    private javax.swing.JTextField tfTotalPago;
    private javax.swing.JTextField tfTotalVencer;
    // End of variables declaration//GEN-END:variables
}
