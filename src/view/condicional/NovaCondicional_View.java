/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.condicional;

import relatorios.ComprovanteCondicional;
import control.Produto_Controller;
import control.Condicional_Controller;
import control.Empresa_Controller;
import control.Itens_Condicional_Controller;
import control.Itens_Venda_Controller;
import control.Venda_Controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente_Model;
import model.Itens_Condicional;
import model.Produto_Model;
import model.Condicional_Model;
import model.Empresa_Model;
import model.Funcionario_Model;
import model.Itens_Venda;
import model.Venda_Model;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import view.venda.NovaVenda_View;

/**
 *
 * @author Claudio Xavier
 */
public class NovaCondicional_View extends javax.swing.JDialog {

    /**
     * Creates new form NovaCondicional_View
     */
    public NovaCondicional_View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public NovaCondicional_View(java.awt.Frame parent, boolean modal, Condicional_Model cMod) {
        super(parent, modal);
        initComponents();
        vc = new Condicional_Controller();
        conMod = cMod;
        if ("Finalizada".equals(conMod.getStatusCondicional()) || "Devolvida".equals(conMod.getStatusCondicional())) {
            statusBotoes(false);
        }
        preencherCampos();
        preencherTabProdutos();
    }

    public NovaCondicional_View(java.awt.Frame parent, boolean modal, Cliente_Model cm) {
        super(parent, modal);
        initComponents();
        Funcionario_Model fm = new Funcionario_Model();
        vc = new Condicional_Controller();
        conMod = new Condicional_Model();
        conMod.setCliente(cm);
        conMod.setDataCondicional(new Date());
        conMod.setStatusCondicional("Em Aberto");
        vc.gravaDados(conMod);
        preencherCampos();

    }

    Condicional_Controller vc;
    Condicional_Model conMod;

    public void statusBotoes(boolean status) {
        btnVender.setEnabled(status);
        btnIncluir.setEnabled(status);
        btnExcluir.setEnabled(status);
        btnCancelar.setEnabled(status);
        btnGravar.setEnabled(status);
        btnDevolucao.setEnabled(status);
        btnPesquisarVendedor.setEnabled(status);
    }

    public void preencherCampos() {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        tfCliente.setText(conMod.getCliente().getNome());
        if (conMod.getVendedor() != null) {
            tfVendedor.setText(conMod.getVendedor().getNome());
        }
        tfTotal.setText(String.format("%.2f", conMod.getValorCondicional()));
        tfDataCondicional.setText(df.format(conMod.getDataCondicional()));
        tfNumCondicional.setText(String.valueOf(conMod.getNumCondicional()));
        jdcPrazo.setDate(conMod.getPrazoDevolucao());
        jdcDevolucao.setDate(conMod.getDataDevolucao());
    }

    public void atualizarCondicional() {
        conMod = vc.pesquisaCondicionalObjeto(conMod);
        preencherCampos();
    }

    public void efetuaBaixaEstoque() {
        Produto_Controller pc = new Produto_Controller();
        Itens_Condicional_Controller ivc = new Itens_Condicional_Controller();
        List<Itens_Condicional> lista = ivc.listaItensCondicional(conMod);

        for (Itens_Condicional icMod : lista) {
            Produto_Model pm = new Produto_Model();
            pm = icMod.getProduto();
            pm.setQtdEstoque(pm.getQtdEstoque() - icMod.getQuantidade());
            pc.atualizaDados(pm);
        }
    }

    public void preencherTabProdutos() {

        Itens_Condicional_Controller ivc = new Itens_Condicional_Controller();
        List<Itens_Condicional> lista = ivc.listaItensCondicional(conMod);
        DefaultTableModel dtm = (DefaultTableModel) jtDados.getModel();

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        for (Itens_Condicional iconMod : lista) {
            dtm.addRow(new Object[]{iconMod.getProduto().getDescricaoProduto(), iconMod.getQuantidade(), String.format("%.2f", iconMod.getValorItem())});
        }
    }

    public void venderItensCondicional() {
        Venda_Controller vc = new Venda_Controller();
        Itens_Venda_Controller ivc = new Itens_Venda_Controller();
        Condicional_Controller cc = new Condicional_Controller();
        Venda_Model vm = new Venda_Model();
        vm.setCliente(conMod.getCliente());
        vm.setDataVenda(new Date());
        vm.setStatusVenda("Em Aberto");
        vm.setFuncionario(conMod.getVendedor());
        vc.gravaDados(vm);

        Itens_Condicional_Controller icc = new Itens_Condicional_Controller();

        for (Itens_Condicional iCon : icc.listaItensCondicional(conMod)) {
            Itens_Venda iv = new Itens_Venda();
            iv.setNumItem(iCon.getNumItem());
            iv.setProduto(iCon.getProduto());
            iv.setQuantidade(iCon.getQuantidade());
            iv.setVenda(vm);
            iv.setValorItem(iCon.getValorItem());

            ivc.gravaDados(iv);
        }

        conMod.setDataDevolucao(new Date());
        conMod.setStatusCondicional("Finalizada");
        cc.atualizaDados(conMod);

        NovaVenda_View nvv = new NovaVenda_View(null, true, vm);
        nvv.setVisible(true);

    }

        public void emitirComprovante() {
        Itens_Condicional_Controller icc = new Itens_Condicional_Controller();
        Empresa_Controller ec = new Empresa_Controller();
        Empresa_Model emp;

        ComprovanteCondicional cc;
        List<ComprovanteCondicional> listaComprovante = new ArrayList<>();

        emp = ec.buscarEmpresa();

        for (Itens_Condicional icond : icc.listaItensCondicional(conMod)) {
            cc = new ComprovanteCondicional();
            cc.setLoja(emp.getNomeFantasia());
            cc.setEnderecoLoja(emp.getEndereco() + " - nº" + emp.getNumero() + " - " + emp.getBairro() + " - " + emp.getCidade());
            cc.setTelefone("Tel.: " + emp.getTelefone() + " - " + emp.getCelular());
            cc.setNumCondicional(icond.getCondicional().getNumCondicional());
            cc.setNome(icond.getCondicional().getCliente().getNome());
            cc.setData(icond.getCondicional().getDataCondicional());
            cc.setTelefoneCliente(icond.getCondicional().getCliente().getWhatsApp());
            cc.setEnderecoCliente(icond.getCondicional().getCliente().getRua() + " - " + icond.getCondicional().getCliente().getNumero());
            cc.setCidadeCliente(icond.getCondicional().getCliente().getCidade());
            cc.setItem(icond.getProduto().getDescricaoProduto() + " - " + icond.getProduto().getTamanho());
            cc.setQuantidade(icond.getQuantidade());
            cc.setValor(icond.getValorItem());

            listaComprovante.add(cc);
        }

        try {
            JasperPrint relatorioPopulado = JasperFillManager.
                    fillReport("src/relatorios/ComprovanteCondicional.jasper",
                            null, new JRBeanCollectionDataSource(listaComprovante));

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tfCliente = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfVendedor = new javax.swing.JTextField();
        btnPesquisarVendedor = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDados = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jdcPrazo = new com.toedter.calendar.JDateChooser();
        jdcDevolucao = new com.toedter.calendar.JDateChooser();
        tfDataCondicional = new javax.swing.JTextField();
        tfNumCondicional = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnGravar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnVender = new javax.swing.JButton();
        btnDevolucao = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEmitirNota = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UnicaBoutique - Formulário de Venda");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Cliente: ");

        tfCliente.setEditable(false);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_system-search_118797 (1).png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setEnabled(false);

        jLabel3.setText("Vendedor:");

        tfVendedor.setEditable(false);

        btnPesquisarVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_system-search_118797 (1).png"))); // NOI18N
        btnPesquisarVendedor.setText("Pesquisar");
        btnPesquisarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarVendedorActionPerformed(evt);
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
                        .addComponent(tfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPesquisarVendedor))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(tfVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarVendedor))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jtDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Quantidade", "Valor(R$)", "Status"
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
        jScrollPane1.setViewportView(jtDados);
        if (jtDados.getColumnModel().getColumnCount() > 0) {
            jtDados.getColumnModel().getColumn(0).setResizable(false);
            jtDados.getColumnModel().getColumn(1).setResizable(false);
            jtDados.getColumnModel().getColumn(2).setResizable(false);
            jtDados.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Total(R$): ");

        tfTotal.setEditable(false);
        tfTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfTotal.setForeground(new java.awt.Color(255, 0, 0));
        tfTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Data Condicional:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Condicional Nº: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Prazo de Devolução:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Data de Devolução:");

        jdcDevolucao.setEnabled(false);

        tfDataCondicional.setEditable(false);
        tfDataCondicional.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfDataCondicional.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tfNumCondicional.setEditable(false);
        tfNumCondicional.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfNumCondicional.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdcPrazo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcDevolucao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfNumCondicional, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfTotal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(66, 66, 66))
                            .addComponent(tfDataCondicional, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNumCondicional, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDataCondicional)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdcPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdcDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490079261_floppy.png"))); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGravar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGravar);

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

        btnVender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490376769_sign-check.png"))); // NOI18N
        btnVender.setText("Vender Itens");
        btnVender.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVender.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });
        jPanel4.add(btnVender);

        btnDevolucao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_Undo_1493283.png"))); // NOI18N
        btnDevolucao.setText("Devolução");
        btnDevolucao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDevolucao.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolucaoActionPerformed(evt);
            }
        });
        jPanel4.add(btnDevolucao);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377443_notification_error.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar);

        btnEmitirNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_documents-01_1622837.png"))); // NOI18N
        btnEmitirNota.setText("Imprimir");
        btnEmitirNota.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEmitirNota.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEmitirNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmitirNotaActionPerformed(evt);
            }
        });
        jPanel4.add(btnEmitirNota);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        Lista_Produtos_Condicional_View lpv = new Lista_Produtos_Condicional_View(null, true, conMod);
        lpv.setVisible(true);
        preencherCampos();
        preencherTabProdutos();
        atualizarCondicional();
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (jtDados.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um Item na tabela para excluir!!");
        } else {
            int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja remover este item da Condicional? ");
            if (confirmacao == JOptionPane.YES_OPTION) {
                Itens_Condicional_Controller ivc = new Itens_Condicional_Controller();
                Itens_Condicional iconMod;
                int linha = jtDados.getSelectedRow();
                List<Itens_Condicional> lista = ivc.listaItensCondicional(conMod);
                iconMod = lista.get(linha);
                ivc.excluirDados(iconMod);
            }
        }
        preencherTabProdutos();
        atualizarCondicional();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja Finalizar a Condicional? ");
        if (confirmacao == JOptionPane.YES_OPTION) {
            Itens_Condicional_Controller ivc = new Itens_Condicional_Controller();
            if (ivc.listaItensCondicional(conMod).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não foi possível executar esta ação pois não há itens na condicional!!");
            } else {
                venderItensCondicional();
            }
        }
    }//GEN-LAST:event_btnVenderActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja cancelar a Condicional? ");
        if (confirmacao == JOptionPane.YES_OPTION) {
            conMod.setStatusCondicional("Cancelada");
            vc.atualizaDados(conMod);
            statusBotoes(false);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEmitirNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmitirNotaActionPerformed
        Itens_Condicional_Controller icc = new Itens_Condicional_Controller();
        if (icc.listaItensCondicional(conMod).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não é possivel emitir o comprovante pois a Condicional não possui itens!!");
        } else {
            emitirComprovante();
        }
    }//GEN-LAST:event_btnEmitirNotaActionPerformed

    private void btnDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolucaoActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja efetuar a devolução dos produtos da condicional?");
        if (confirmacao == JOptionPane.YES_OPTION) {
            Condicional_Controller cc = new Condicional_Controller();
            Itens_Condicional_Controller icc = new Itens_Condicional_Controller();
            if (icc.listaItensCondicional(conMod).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não é possível executar esta ação pois a condicional não possui itens!");
            } else {
                conMod.setDataDevolucao(new Date());
                conMod.setStatusCondicional("Devolvida");
                cc.atualizaDados(conMod);
                preencherCampos();
                statusBotoes(false);
            }
        }
    }//GEN-LAST:event_btnDevolucaoActionPerformed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        Condicional_Controller cc = new Condicional_Controller();
        conMod.setStatusCondicional("Aguardando Devolução");
        conMod.setPrazoDevolucao(jdcPrazo.getDate());
        cc.atualizaDados(conMod);
        JOptionPane.showMessageDialog(null, "Os dados da condicional foram salvos!");
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnPesquisarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarVendedorActionPerformed
        Listagem_Funcionario_Condicional_View lf = new Listagem_Funcionario_Condicional_View(null, true, conMod);
        lf.setVisible(true);
        preencherCampos();
    }//GEN-LAST:event_btnPesquisarVendedorActionPerformed

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
            java.util.logging.Logger.getLogger(NovaCondicional_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovaCondicional_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovaCondicional_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovaCondicional_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NovaCondicional_View dialog = new NovaCondicional_View(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnDevolucao;
    private javax.swing.JButton btnEmitirNota;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnPesquisarVendedor;
    private javax.swing.JButton btnVender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdcDevolucao;
    private com.toedter.calendar.JDateChooser jdcPrazo;
    private javax.swing.JTable jtDados;
    private javax.swing.JTextField tfCliente;
    private javax.swing.JTextField tfDataCondicional;
    private javax.swing.JTextField tfNumCondicional;
    private javax.swing.JTextField tfTotal;
    private javax.swing.JTextField tfVendedor;
    // End of variables declaration//GEN-END:variables
}
