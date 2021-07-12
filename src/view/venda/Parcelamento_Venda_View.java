/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.venda;

import control.CaixaController;
import control.Pagamento_Venda_Controller;
import control.Venda_Controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Caixa_Model;
import model.Venda_Model;
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
public class Parcelamento_Venda_View extends javax.swing.JDialog {

    /**
     * Creates new form Frame_Parcelamento
     */
    public Parcelamento_Venda_View(java.awt.Frame parent, boolean modal, Venda_Model vMod) {
        super(parent, modal);
        initComponents();
        jdcPrimeiroVencimento.setDate(new Date());
        pagVenda = new PagamentoVenda_Model();
        pagVenda.setVenda(vMod);
        setCampos();
    }

    public Parcelamento_Venda_View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    PagamentoVenda_Model pagVenda;
    List<RelComprovantePagamento> pagVista;

    public void statusBotoes(boolean status) {
        btnSalvar.setEnabled(status);
    }

    public void setCampos() {
        tfNumOS.setText(String.valueOf(pagVenda.getVenda().getNumVenda()));
        tfValor.setText(String.format("%.2f", pagVenda.getVenda().getValorVenda()));
        tfTotalComDesc.setText(String.format("%.2f", pagVenda.getVenda().getValorVenda()));
        //tfCliente.setText(pagVenda.getVenda().getCliente().getNome());

    }

    public void calculaDescontoPercent() {
        double valDesconto = (Double.parseDouble(tfValor.getText().replace(",", ".")) / 100) * Double.parseDouble(tfDescPorcent.getText().replace(",", "."));
        double desconto = Double.parseDouble(tfValor.getText().replace(",", ".")) - ((Double.parseDouble(tfValor.getText().replace(",", ".")) / 100)
                * Double.parseDouble(tfDescPorcent.getText().replace(",", ".")));

        tfTotalComDesc.setText(String.format("%.2f", desconto));
        tfDescReal.setText(String.format("%.2f", valDesconto));
    }

    public void calculaDescontoReal() {
        double percentDesconto = 100 - (((Double.parseDouble(tfValor.getText().replace(",", ".")) - Double.parseDouble(tfDescReal.getText().replace(",", ".")))
                / Double.parseDouble(tfValor.getText().replace(",", "."))) * 100);
        double desconto = Double.parseDouble(tfValor.getText().replace(",", ".")) - Double.parseDouble(tfDescReal.getText().replace(",", "."));

        tfTotalComDesc.setText(String.format("%.2f", desconto));
        tfDescPorcent.setText(String.format("%.2f", percentDesconto));

    }

    public void atualizarVendaComDesconto() {
        Venda_Model vm = pagVenda.getVenda();
        Venda_Controller vc = new Venda_Controller();

        vm.setPercentDesconto(Double.parseDouble(tfDescPorcent.getText().replace(",", ".")));
        vm.setValorDesconto(Double.parseDouble(tfDescReal.getText().replace(",", ".")));
        vm.setValorComDesconto(Double.parseDouble(tfTotalComDesc.getText().replace(",", ".")));

        vc.atualizaDados(vm);
    }

    public void gerarParcelamento() {

        Date data = jdcPrimeiroVencimento.getDate();
        Date proximoMes = data;
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        double valorParcela = 0;
        int qtdParcelas = Integer.parseInt(tfNumParcelas.getText());

        if ("A Vista".equals(jcbbFormaPagamento.getSelectedItem()) || "Debito".equals(jcbbFormaPagamento.getSelectedItem())) {
            tfNumParcelas.setText("1");
            qtdParcelas = Integer.parseInt(tfNumParcelas.getText());
            valorParcela = Double.parseDouble(tfTotalComDesc.getText().replace(",", ".")) / qtdParcelas;
            jdcPrimeiroVencimento.setDate(new Date());

            DefaultTableModel dtm = (DefaultTableModel) jtDados.getModel();

            while (dtm.getRowCount() > 0) {
                dtm.removeRow(0);
            }

            for (int i = 1; i <= qtdParcelas; i++) {
                dtm.addRow(new Object[]{i, jcbbFormaPagamento.getSelectedItem(), df.format(proximoMes), String.format("%.2f", valorParcela)});

                calendario.add(Calendar.MONTH, 1);
                proximoMes = calendario.getTime();
            }
        } else if ("Credito".equals(jcbbFormaPagamento.getSelectedItem()) || "Promissoria".equals(jcbbFormaPagamento.getSelectedItem())) {
            valorParcela = Double.parseDouble(tfTotalComDesc.getText().replace(",", ".")) / qtdParcelas;

            DefaultTableModel dtm = (DefaultTableModel) jtDados.getModel();

            while (dtm.getRowCount() > 0) {
                dtm.removeRow(0);
            }

            for (int i = 1; i <= qtdParcelas; i++) {
                dtm.addRow(new Object[]{i, jcbbFormaPagamento.getSelectedItem(), df.format(proximoMes), String.format("%.2f", valorParcela)});

                calendario.add(Calendar.MONTH, 1);
                proximoMes = calendario.getTime();
            }

        } else if ("Entrada + Promissoria".equals(jcbbFormaPagamento.getSelectedItem())) {
            valorParcela = (Double.parseDouble(tfTotalComDesc.getText().replace(",", ".")) - Double.parseDouble(tfEntrada.getText().replace(",", "."))) / qtdParcelas;
            double entrada = Double.parseDouble(tfEntrada.getText().replace(",", "."));

            DefaultTableModel dtm = (DefaultTableModel) jtDados.getModel();

            while (dtm.getRowCount() > 0) {
                dtm.removeRow(0);
            }

            if (entrada == 0) {
                JOptionPane.showMessageDialog(null, "O valor da Entrada não pode ser Zero(0)!");
            } else {
                dtm.addRow(new Object[]{1, "Entrada", df.format(new Date()), String.format("%.2f", entrada)});

                for (int i = 2; i <= qtdParcelas + 1; i++) {
                    dtm.addRow(new Object[]{i, "Promissória", df.format(proximoMes), String.format("%.2f", valorParcela)});

                    calendario.add(Calendar.MONTH, 1);
                    proximoMes = calendario.getTime();
                }
            }

        } else if ("Entrada + Credito".equals(jcbbFormaPagamento.getSelectedItem())) {
            valorParcela = (Double.parseDouble(tfTotalComDesc.getText().replace(",", ".")) - Double.parseDouble(tfEntrada.getText().replace(",", "."))) / qtdParcelas;
            double entrada = Double.parseDouble(tfEntrada.getText().replace(",", "."));

            DefaultTableModel dtm = (DefaultTableModel) jtDados.getModel();

            while (dtm.getRowCount() > 0) {
                dtm.removeRow(0);
            }

            if (entrada == 0) {
                JOptionPane.showMessageDialog(null, "O valor da Entrada não pode ser Zero(0)!");
            } else {

                dtm.addRow(new Object[]{1, "Entrada", df.format(new Date()), String.format("%.2f", entrada)});

                for (int i = 2; i <= qtdParcelas + 1; i++) {
                    dtm.addRow(new Object[]{i, "Crédito", df.format(proximoMes), String.format("%.2f", valorParcela)});

                    calendario.add(Calendar.MONTH, 1);
                    proximoMes = calendario.getTime();
                }
            }
        }

//        DefaultTableModel dtm = (DefaultTableModel) jtDados.getModel();
//
//        while (dtm.getRowCount()
//                > 0) {
//            dtm.removeRow(0);
//        }
//
//        for (int i = 1;
//                i <= qtdParcelas;
//                i++) {
//            dtm.addRow(new Object[]{i, jcbbFormaPagamento.getSelectedItem(), df.format(proximoMes), String.format("%.2f", valorParcela)});
//
//            calendario.add(Calendar.MONTH, 1);
//            proximoMes = calendario.getTime();
//        }
    }

    public void emitirComprovantePagamentoAvistaCartao() {

        // emissão do comprovante //
        RelComprovantePagamento rcp = new RelComprovantePagamento();

        pagVista = new ArrayList<>();
        rcp.setCliente(pagVenda.getVenda().getCliente().getNome());
        rcp.setNumParcela(pagVenda.getNumParcela());
        rcp.setOsVenda(pagVenda.getVenda().getNumVenda());
        rcp.setPagamento(pagVenda.getDataPagamento().getDataAbertura());
        rcp.setValorParcela(pagVenda.getValorParcela());
        rcp.setVencimento(new Date());

        pagVista.add(rcp);

        try {
            JasperPrint relatorioPopulado = JasperFillManager.
                    fillReport("src/relatorios/ReciboPagamento.jasper", null, new JRBeanCollectionDataSource(pagVista));
            JasperViewer visualizaRelatorio = new JasperViewer(relatorioPopulado, true);
            JDialog viwer = new JDialog(new javax.swing.JFrame(), "Visualização do Relatório", true);
            viwer.setSize(1024, 768);
            viwer.setLocationRelativeTo(null);
            viwer.getContentPane().add(visualizaRelatorio.getContentPane());
            viwer.setVisible(true);
            dispose();
        } catch (JRException jr) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + jr);
        }
    }

    public Double calcularParcelas(int qtdParcelas) {
        double valorParcela = 0;
        valorParcela = pagVenda.getVenda().getValorVenda() / qtdParcelas;
        return valorParcela;
    }

    public void pagarAVista() {
        Pagamento_Venda_Controller posc = new Pagamento_Venda_Controller();
        Venda_Controller vc = new Venda_Controller();
        Venda_Model vm = new Venda_Model();
        CaixaController cc = new CaixaController();
        Caixa_Model cm = cc.pesquisaCaixa(new Date());
        Date data = jdcPrimeiroVencimento.getDate();
        double valorParcela = Double.parseDouble(tfTotalComDesc.getText().replace(",", "."));

        if (cm == null) {
            JOptionPane.showMessageDialog(null, "Você precisa abrir o caixa para receber este tipo de pagamento!");
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
            pagVenda.setDataVencimento(new Date());
            pagVenda.setTipoPagamento("A Vista");
            pagVenda.setDataPagamento(cm);
            pagVenda.setNumParcela(1);
            pagVenda.setValorParcela(valorParcela);
            posc.gravaDados(pagVenda);
            emitirComprovantePagamentoAvistaCartao();

            //grava dados
            cm.setSaldoEntrada(cm.getSaldoEntrada() + pagVenda.getValorParcela());
            cc.atualizaDados(cm);

            atualizarVendaComDesconto();
            dispose();

        }

    }

    public void pagarEntradaPromissoria(int qtdParcelas) {
        Pagamento_Venda_Controller posc = new Pagamento_Venda_Controller();
        Venda_Controller vc = new Venda_Controller();
        Venda_Model vm = new Venda_Model();
        CaixaController cc = new CaixaController();
        Caixa_Model cm = cc.pesquisaCaixa(new Date());
        Date data = jdcPrimeiroVencimento.getDate();
        Date proximoMes = data;
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        double valorParcela = Double.parseDouble(tfTotalComDesc.getText().replace(",", ".")) - Double.parseDouble(tfEntrada.getText().replace(",", ".")) / qtdParcelas;

        if (Double.parseDouble(tfEntrada.getText().replace(",", ".")) == 0) {
            JOptionPane.showMessageDialog(null, "O valor da Entrada não pode ser Zero(0)!");
        } else {

            if (cm == null) {
                JOptionPane.showMessageDialog(null, "Você precisa abrir o caixa para receber este tipo de pagamento!");
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

                pagVenda.setDataVencimento(new Date());
                pagVenda.setTipoPagamento("A Vista");
                pagVenda.setDataPagamento(cm);
                pagVenda.setNumParcela(1);
                pagVenda.setValorParcela(valorParcela);
                posc.gravaDados(pagVenda);

                cm.setSaldoEntrada(cm.getSaldoEntrada() + pagVenda.getValorParcela());
                cc.atualizaDados(cm);

                for (int i = 2; i <= qtdParcelas; i++) {
                    pagVenda.setDataVencimento(proximoMes);
                    pagVenda.setTipoPagamento("Promissoria");
                    pagVenda.setNumParcela(i);
                    pagVenda.setValorParcela(valorParcela);
                    posc.gravaDados(pagVenda);
                }
                atualizarVendaComDesconto();
                dispose();

            }
        }
    }

    public void pagarEntradaMaisCartao(int qtdParcelas) {
        Pagamento_Venda_Controller posc = new Pagamento_Venda_Controller();
        Venda_Controller vc = new Venda_Controller();
        Venda_Model vm = new Venda_Model();
        CaixaController cc = new CaixaController();
        Caixa_Model cm = cc.pesquisaCaixa(new Date());
        Date data = jdcPrimeiroVencimento.getDate();
        Date proximoMes = data;
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        double valorParcela = Double.parseDouble(tfTotalComDesc.getText().replace(",", ".")) - Double.parseDouble(tfEntrada.getText().replace(",", ".")) / qtdParcelas;

        if (Double.parseDouble(tfEntrada.getText().replace(",", ".")) == 0) {
            JOptionPane.showMessageDialog(null, "O valor da Entrada não pode ser Zero(0)!");
        } else {

            if (cm == null) {
                JOptionPane.showMessageDialog(null, "Você precisa abrir o caixa para receber este tipo de pagamento!");
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

                pagVenda.setDataVencimento(new Date());
                pagVenda.setTipoPagamento("A Vista");
                pagVenda.setDataPagamento(cm);
                pagVenda.setNumParcela(1);
                pagVenda.setValorParcela(valorParcela);
                posc.gravaDados(pagVenda);

                cm.setSaldoEntrada(cm.getSaldoEntrada() + pagVenda.getValorParcela());
                cc.atualizaDados(cm);

                for (int i = 2; i <= qtdParcelas; i++) {
                    pagVenda.setDataVencimento(new Date());
                    pagVenda.setTipoPagamento("Credito");
                    pagVenda.setNumParcela(i);
                    pagVenda.setValorParcela(valorParcela);
                    pagVenda.setDataPagamento(cm);
                    posc.gravaDados(pagVenda);
                }
                atualizarVendaComDesconto();
                dispose();

            }
        }
    }

    public void pagarComPromissoria(int qtdParcelas) {

        Pagamento_Venda_Controller posc = new Pagamento_Venda_Controller();
        Venda_Controller vc = new Venda_Controller();
        Venda_Model vm = new Venda_Model();
        Date data = jdcPrimeiroVencimento.getDate();
        Date proximoMes = data;
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        double valorParcela = (Double.parseDouble(tfTotalComDesc.getText().replace(",", ".")) - Double.parseDouble(tfEntrada.getText().replace(",", "."))) / qtdParcelas;

        for (int i = 1; i <= qtdParcelas; i++) {
            pagVenda.setDataVencimento(proximoMes);
            pagVenda.setTipoPagamento("Promissoria");
            pagVenda.setNumParcela(i);
            pagVenda.setValorParcela(valorParcela);
            calendario.add(Calendar.MONTH, 1);
            proximoMes = calendario.getTime();
            posc.gravaDados(pagVenda);
        }

        atualizarVendaComDesconto();
        dispose();
    }

    public void pagarComCartaoCredito(int qtdParcelas) {

        Pagamento_Venda_Controller posc = new Pagamento_Venda_Controller();
        Venda_Controller vc = new Venda_Controller();
        Venda_Model vm = new Venda_Model();
        CaixaController cc = new CaixaController();
        Caixa_Model cm = cc.pesquisaCaixa(new Date());
        Date data = jdcPrimeiroVencimento.getDate();
        Date proximoMes = data;
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        double valorParcela = Double.parseDouble(tfTotalComDesc.getText().replace(",", ".")) - Double.parseDouble(tfEntrada.getText().replace(",", ".")) / qtdParcelas;

        if (cm == null) {
            JOptionPane.showMessageDialog(null, "Você precisa abrir o caixa para receber este tipo de pagamento!");
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

            for (int i = 1; i <= qtdParcelas; i++) {
                pagVenda.setDataVencimento(new Date());
                pagVenda.setTipoPagamento("Credito");
                pagVenda.setNumParcela(i);
                pagVenda.setValorParcela(valorParcela);
                pagVenda.setDataPagamento(cm);
                posc.gravaDados(pagVenda);
                cm.setSaldoEntrada(cm.getSaldoEntrada() + pagVenda.getValorParcela());
                cc.atualizaDados(cm);
            }

            atualizarVendaComDesconto();
            dispose();

        }

    }

    public void pagarComCartaoDebito() {
        Pagamento_Venda_Controller posc = new Pagamento_Venda_Controller();
        Venda_Controller vc = new Venda_Controller();
        Venda_Model vm = new Venda_Model();
        CaixaController cc = new CaixaController();
        Caixa_Model cm = cc.pesquisaCaixa(new Date());
        Date data = jdcPrimeiroVencimento.getDate();
        double valorParcela = Double.parseDouble(tfTotalComDesc.getText().replace(",", "."));

        if (cm == null) {
            JOptionPane.showMessageDialog(null, "Você precisa abrir o caixa para receber este tipo de pagamento!");
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
            pagVenda.setDataVencimento(new Date());
            pagVenda.setTipoPagamento("Debito");
            pagVenda.setDataPagamento(cm);
            pagVenda.setNumParcela(1);
            pagVenda.setValorParcela(valorParcela);
            posc.gravaDados(pagVenda);
            emitirComprovantePagamentoAvistaCartao();

            //grava dados
            cm.setSaldoEntrada(cm.getSaldoEntrada() + pagVenda.getValorParcela());
            cc.atualizaDados(cm);

            atualizarVendaComDesconto();
            dispose();

        }

    }

    public void atualizaFormaPagamentoVenda(String formaPagamento) {
        pagVenda.getVenda().setTipoPagamento(formaPagamento);
        Venda_Controller vc = new Venda_Controller();

        vc.atualizaDados(pagVenda.getVenda());

    }

//    public void gravarParcelamento(int qtdParcelas) {
//        Pagamento_Venda_Controller posc = new Pagamento_Venda_Controller();
//        Venda_Controller vc = new Venda_Controller();
//        Venda_Model vm = new Venda_Model();
//        CaixaController cc = new CaixaController();
//        Caixa_Model cm = cc.pesquisaCaixa(new Date());
//        Date data = jdcPrimeiroVencimento.getDate();
//        Date proximoMes = data;
//        Calendar calendario = Calendar.getInstance();
//        calendario.setTime(data);
//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        double valorParcela = calcularParcelas(qtdParcelas);
//
//        try {
//
//            if (jcbbFormaPagamento.getSelectedItem() == "À Vista") {
//                if (cm == null) {
//                    JOptionPane.showMessageDialog(null, "Você precisa abrir o caixa para receber este tipo de pagamento!");
//                    AberturaCaixa_View ac = new AberturaCaixa_View(null, true);
//                    ac.setVisible(true);
//                }
//            } else if (jcbbFormaPagamento.getSelectedItem() == "Entrada + Promissoria") {
//                if (cm == null) {
//                    JOptionPane.showMessageDialog(null, "Você precisa abrir o caixa para receber este tipo de pagamento!");
//                    AberturaCaixa_View ac = new AberturaCaixa_View(null, true);
//                    ac.setVisible(true);
//                }
//            } else if (jcbbFormaPagamento.getSelectedItem().equals("Debito") || jcbbFormaPagamento.getSelectedItem().equals("Credito")) {
//                if (cm == null) {
//                    JOptionPane.showMessageDialog(null, "Você precisa abrir o caixa para receber este tipo de pagamento!");
//                    AberturaCaixa_View ac = new AberturaCaixa_View(null, true);
//                    ac.setVisible(true);
//                } else if (cm.isStatus() == false) {
//                    int confirmacao = JOptionPane.showConfirmDialog(rootPane, "O caixa do dia está fechado, deseja efetuar a reabertura?");
//                    if (confirmacao == JOptionPane.YES_OPTION) {
//                        cm.setStatus(true);
//                        cc.atualizaDados(cm);
//                        JOptionPane.showMessageDialog(null, "Reabertura do caixa efetuada!");
//
//                        pagVenda.setDataVencimento(new Date());
//                        pagVenda.setDataPagamento(cm);
//                        pagVenda.setNumParcela(1);
//                        pagVenda.setValorParcela(valorParcela);
//                        posc.gravaDados(pagVenda);
//
//                        emitirComprovantePagamentoAvistaCartao();
//
//                        //grava dados
//                        cm.setSaldoEntrada(cm.getSaldoEntrada() + pagVenda.getValorParcela());
//                        cc.atualizaDados(cm);
//                        dispose();
//                    }
//                } else {
//                    pagVenda.setDataVencimento(new Date());
//                    pagVenda.setDataPagamento(cm);
//                    pagVenda.setNumParcela(1);
//                    pagVenda.setValorParcela(valorParcela);
//                    posc.gravaDados(pagVenda);
//                    emitirComprovantePagamentoAvistaCartao();
//
//                    //grava dados
//                    cm.setSaldoEntrada(cm.getSaldoEntrada() + pagVenda.getValorParcela());
//                    cc.atualizaDados(cm);
//                    dispose();
//                }
//            } else {
//                for (int i = 1; i <= qtdParcelas; i++) {
//                    pagVenda.setDataVencimento(proximoMes);
//                    pagVenda.setNumParcela(i);
//                    pagVenda.setValorParcela(valorParcela);
//                    calendario.add(Calendar.MONTH, 1);
//                    proximoMes = calendario.getTime();
//                    posc.gravaDados(pagVenda);
//                    dispose();
//                }
//            }
//
//            vm = pagVenda.getVenda();
//
//            vm.setPercentDesconto(Double.parseDouble(tfDescPorcent.getText()));
//            vm.setValorDesconto(Double.parseDouble(tfDescReal.getText()));
//            vm.setValorComDesconto(Double.parseDouble(tfTotalComDesc.getText()));
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Ocorreu um erro:\n\n" + e);
//        }
//
//    }
    public static Date calcularData(int dias, Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, dias);
        return calendar.getTime();
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
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jdcPrimeiroVencimento = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        tfNumParcelas = new javax.swing.JTextField();
        btnGerar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tfNumOS = new javax.swing.JTextField();
        jcbbFormaPagamento = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tfValor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfDescPorcent = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfDescReal = new javax.swing.JTextField();
        tfTotalComDesc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfEntrada = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UnicaBoutique - Parcelamento de Venda");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490376769_sign-check.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalvar);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490377443_notification_error.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(btnCancelar);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Forma de Pagamento: ");

        jLabel2.setText("Primeiro Vencimento: ");

        jdcPrimeiroVencimento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcPrimeiroVencimentoPropertyChange(evt);
            }
        });

        jLabel3.setText("Nº Parcelas:");

        tfNumParcelas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfNumParcelas.setText("1");
        tfNumParcelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfNumParcelasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNumParcelasKeyReleased(evt);
            }
        });

        btnGerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490376757_money.png"))); // NOI18N
        btnGerar.setText("Gerar Parcelamento");
        btnGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarActionPerformed(evt);
            }
        });

        jLabel4.setText("Nº da Venda");

        tfNumOS.setEditable(false);
        tfNumOS.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jcbbFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Vista", "Credito", "Debito", "Promissoria", "Entrada + Promissoria", "Entrada + Credito" }));
        jcbbFormaPagamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbbFormaPagamentoItemStateChanged(evt);
            }
        });
        jcbbFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbFormaPagamentoActionPerformed(evt);
            }
        });

        jLabel5.setText("Valor(R$): ");

        tfValor.setEditable(false);
        tfValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfValorActionPerformed(evt);
            }
        });

        jLabel7.setText("Desconto (%):");

        tfDescPorcent.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDescPorcent.setText("0");
        tfDescPorcent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfDescPorcentKeyReleased(evt);
            }
        });

        jLabel8.setText("Desconto(R$):");

        jLabel9.setText("Total c/ Desconto(R$):");

        tfDescReal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDescReal.setText("0");
        tfDescReal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfDescRealKeyReleased(evt);
            }
        });

        tfTotalComDesc.setEditable(false);
        tfTotalComDesc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setText("Entrada(R$):");

        tfEntrada.setEditable(false);
        tfEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfEntrada.setText("0");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1499301375_Calculator.png"))); // NOI18N
        jButton1.setText("Calcular Troco");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbbFormaPagamento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfNumParcelas))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcPrimeiroVencimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGerar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfNumOS, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfEntrada))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfDescPorcent, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(tfDescReal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfTotalComDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(34, 34, 34))
                    .addComponent(tfNumOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDescReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTotalComDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDescPorcent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGerar)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbbFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfNumParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jdcPrimeiroVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Parcela", "Forma de Pagamento", "Vencimento", "Valor(R$)"
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
        if (jdcPrimeiroVencimento.getDate().before(calcularData(-1, new Date()))) {
            JOptionPane.showMessageDialog(null, "O primeiro vencimento não pode ser anterior a data atual!!");
        } else {
            gerarParcelamento();
            statusBotoes(true);
        }

    }//GEN-LAST:event_btnGerarActionPerformed

    private void tfValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfValorActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (jcbbFormaPagamento.getSelectedItem().equals("A Vista")) {
            pagarAVista();
            atualizaFormaPagamentoVenda((String) jcbbFormaPagamento.getSelectedItem());
        } else if (jcbbFormaPagamento.getSelectedItem().equals("Credito")) {
            pagarComCartaoCredito(Integer.parseInt(tfNumParcelas.getText()));
            atualizaFormaPagamentoVenda((String) jcbbFormaPagamento.getSelectedItem());
        } else if (jcbbFormaPagamento.getSelectedItem().equals("Debito")) {
            pagarComCartaoDebito();
            atualizaFormaPagamentoVenda((String) jcbbFormaPagamento.getSelectedItem());
        } else if (jcbbFormaPagamento.getSelectedItem().equals("Promissoria")) {
            pagarComPromissoria(Integer.parseInt(tfNumParcelas.getText()));
            atualizaFormaPagamentoVenda((String) jcbbFormaPagamento.getSelectedItem());
        } else if (jcbbFormaPagamento.getSelectedItem().equals("Entrada + Promissoria")) {
            pagarEntradaPromissoria(Integer.parseInt(tfNumParcelas.getText()));
            atualizaFormaPagamentoVenda((String) jcbbFormaPagamento.getSelectedItem());
        } else if (jcbbFormaPagamento.getSelectedItem().equals("Entrada + Credito")) {
            pagarEntradaMaisCartao(Integer.parseInt(tfNumParcelas.getText()));
            atualizaFormaPagamentoVenda((String) jcbbFormaPagamento.getSelectedItem());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void tfNumParcelasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNumParcelasKeyReleased
        btnSalvar.setEnabled(false);
    }//GEN-LAST:event_tfNumParcelasKeyReleased

    private void jcbbFormaPagamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbbFormaPagamentoItemStateChanged
        if (jcbbFormaPagamento.getSelectedItem() == "A Vista") {
            btnSalvar.setEnabled(true);
            tfEntrada.setEditable(false);
            tfNumParcelas.setText("1");
            gerarParcelamento();
        } else if (jcbbFormaPagamento.getSelectedItem().equals("Debito")) {
            btnSalvar.setEnabled(true);
            tfNumParcelas.setText("1");
            tfEntrada.setEditable(false);
            gerarParcelamento();
        } else if (jcbbFormaPagamento.getSelectedItem().equals("Credito") || jcbbFormaPagamento.getSelectedItem().equals("Promissoria")) {
            btnSalvar.setEnabled(false);
            tfEntrada.setEditable(false);
        } else {
            btnSalvar.setEnabled(false);
            tfEntrada.setEditable(true);
        }
    }//GEN-LAST:event_jcbbFormaPagamentoItemStateChanged

    private void jdcPrimeiroVencimentoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcPrimeiroVencimentoPropertyChange
        btnSalvar.setEnabled(false);
    }//GEN-LAST:event_jdcPrimeiroVencimentoPropertyChange

    private void tfDescPorcentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDescPorcentKeyReleased
        if ("".equals(tfDescPorcent.getText())) {
            tfDescPorcent.setText("0");
        } else {
            calculaDescontoPercent();
        }
    }//GEN-LAST:event_tfDescPorcentKeyReleased

    private void tfDescRealKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDescRealKeyReleased
        if ("".equals(tfDescReal.getText())) {
            tfDescReal.setText("0");
        } else {
            calculaDescontoReal();
        }
    }//GEN-LAST:event_tfDescRealKeyReleased

    private void tfNumParcelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNumParcelasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNumParcelasKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CalculadoraTroco_View ct = new CalculadoraTroco_View(null, true, Double.parseDouble(tfTotalComDesc.getText().replace(",", ".")));
        ct.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jcbbFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbFormaPagamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbbFormaPagamentoActionPerformed

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
            java.util.logging.Logger.getLogger(Parcelamento_Venda_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Parcelamento_Venda_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Parcelamento_Venda_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Parcelamento_Venda_View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Parcelamento_Venda_View dialog = new Parcelamento_Venda_View(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnGerar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbbFormaPagamento;
    private com.toedter.calendar.JDateChooser jdcPrimeiroVencimento;
    private javax.swing.JTable jtDados;
    private javax.swing.JTextField tfDescPorcent;
    private javax.swing.JTextField tfDescReal;
    private javax.swing.JTextField tfEntrada;
    private javax.swing.JTextField tfNumOS;
    private javax.swing.JTextField tfNumParcelas;
    private javax.swing.JTextField tfTotalComDesc;
    private javax.swing.JTextField tfValor;
    // End of variables declaration//GEN-END:variables
}
