/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.funcionario;

import control.Funcionario_Controller;
import control.Usuario_Controller;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Funcionario_Model;
import model.Usuario_Model;
import util.ValidaCPF;
import view.usuario.CadastroUsuario_View;

/**
 *
 * @author Claudio Xavier
 */
public class Cadastro_Funcionario_View extends javax.swing.JDialog {

    /**
     * Creates new form Cadastro_Cliente_View
     */
    public Cadastro_Funcionario_View(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Cadastro_Funcionario_View(java.awt.Frame parent, boolean modal, Funcionario_Model funMod) {
        super(parent, modal);
        initComponents();
        btnAcesso.setEnabled(true);
        fun = funMod;
        preencherCampos(funMod);
    }

    Funcionario_Model fun;
    String caracteres = "abcdefghijklmnopqrstuvxzABCDEFGHIJKLMNOPQRSTUVXZwW";
    String numeros = "0123456789";
    String simbolos = "!@#$%¨&*()-=+[}{]:.,></?|\"'";

    public Funcionario_Model capturaCampos() {
        Funcionario_Model fm = new Funcionario_Model();
        if (fun != null) {
            fm.setId(fun.getId());
        }

//        if (rbAtendente.isSelected() == true) {
//            fm.setCargo("Atendente");
//        } else if (rbGerente.isSelected() == true) {
//            fm.setCargo("Gerente");
//        } else if (rbMecanico.isSelected() == true) {
//            fm.setCargo("Mecanico");
//        }
        fm.setNome(tfNome.getText().toUpperCase());
        fm.setDataNascimento(jdcDataNasc.getDate());
        fm.setSexo((String) jcbbSexo.getSelectedItem());
        fm.setCpf(ffCPF.getText());
        fm.setRg(tfRg.getText());
        fm.setRua(tfRua.getText().toUpperCase());
        fm.setNumero(tfNumero.getText());
        fm.setBairro(tfBairro.getText().toUpperCase());
        fm.setComplemento(tfComplemento.getText().toUpperCase());
        fm.setCidade(tfCidade.getText().toUpperCase());
        fm.setEstado((String) jcbbEstado.getSelectedItem());
        fm.setCep(ffCep.getText());
        fm.setTelefoneResidencial(ffTelResidencial.getText());
        fm.setTelefoneComercial(ffTelComercial.getText());
        fm.setCelular(ffCelular2.getText());
        fm.setWhatsApp(ffWhatsapp.getText());
        fm.setObservacoes(taObservacoes.getText());

        return fm;
    }

    public void preencherCampos(Funcionario_Model fm) {
        tfNome.setText(fm.getNome());
        jdcDataNasc.setDate(fm.getDataNascimento());
        jcbbSexo.setSelectedItem(fm.getSexo());
        ffCPF.setText(fm.getCpf());
        tfRg.setText(fm.getRg());
        tfRua.setText(fm.getRua());
        tfNumero.setText(fm.getNumero());
        tfBairro.setText(fm.getBairro());
        tfComplemento.setText(fm.getComplemento());
        tfCidade.setText(fm.getCidade());
        jcbbEstado.setSelectedItem(fm.getEstado());
        ffCep.setText(fm.getCep());
        ffTelResidencial.setText(fm.getTelefoneResidencial());
        ffTelComercial.setText(fm.getTelefoneComercial());
        ffCelular2.setText(fm.getCelular());
        ffWhatsapp.setText(fm.getWhatsApp());
        taObservacoes.setText(fm.getObservacoes());
//        if ("Atendente".equals(fm.getCargo())) {
//            rbAtendente.setSelected(true);
//        } else if ("Gerente".equals(fm.getCargo())) {
//            rbGerente.setSelected(true);
//        } else if ("Mecanico".equals(fm.getCargo())) {
//            rbMecanico.setSelected(true);
//        }
    }

    public boolean validaCampos() {
        if ("".equals(tfNome.getText())) {
            JOptionPane.showMessageDialog(null, "O campo Nome não pode ser Vazio!");
            tfNome.grabFocus();
            return false;
        } else if (jdcDataNasc.getDate() == null) {
            JOptionPane.showMessageDialog(null, "O campo data de nascimento não pode ser vazio!");
            jdcDataNasc.grabFocus();
            return false;
        } else if (jdcDataNasc.getDate().after(new Date())) {
            JOptionPane.showMessageDialog(null, "A data de nascimento não pode ser no futuro!");
            return false;
        } else if ("   .   .   -  ".equals(ffCPF.getText())) {
            JOptionPane.showMessageDialog(null, "Campo CPF não pode ser vazio!");
            ffCPF.grabFocus();
            return false;
        } else if ("".equals(tfRua.getText())) {
            JOptionPane.showMessageDialog(null, "O campo Rua não pode ser Vazio!");
            tfRua.grabFocus();
            return false;
        } else if ("".equals(tfNumero.getText())) {
            JOptionPane.showMessageDialog(null, "O campo Número não pode ser Vazio!");
            tfNumero.grabFocus();
            return false;
        } else if ("".equals(tfBairro.getText())) {
            JOptionPane.showMessageDialog(null, "O campo Bairro não pode ser Vazio!");
            tfBairro.grabFocus();
            return false;
        } else if ("".equals(tfCidade.getText())) {
            JOptionPane.showMessageDialog(null, "O campo Cidade não pode ser Vazio!");
            tfCidade.grabFocus();
            return false;
        } else if ("(  )     -    ".equals(ffWhatsapp.getText())) {
            JOptionPane.showMessageDialog(null, "O campo Celular(WhatsApp) não pode ser vazio!");
            ffWhatsapp.grabFocus();
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgCargo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnAcesso = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfRua = new javax.swing.JTextField();
        tfNumero = new javax.swing.JTextField();
        tfBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfComplemento = new javax.swing.JTextField();
        tfCidade = new javax.swing.JTextField();
        jcbbEstado = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        ffCep = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ffTelResidencial = new javax.swing.JFormattedTextField();
        ffTelComercial = new javax.swing.JFormattedTextField();
        ffWhatsapp = new javax.swing.JFormattedTextField();
        ffCelular2 = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taObservacoes = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ffCPF = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        tfRg = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jdcDataNasc = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jcbbSexo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UnicaBoutique - Formulário de Cadastro de Funcionários");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1490376662_floppy.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setToolTipText("Salva os Dados Contidos no Formulário");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalvar);

        btnAcesso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1483683374_Manager.png"))); // NOI18N
        btnAcesso.setText("Acesso ao Sistema");
        btnAcesso.setEnabled(false);
        btnAcesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcessoActionPerformed(evt);
            }
        });
        jPanel1.add(btnAcesso);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1484791039_file_search.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setToolTipText("Retorna para o formulário de pesquisa de Funcionários");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(btnPesquisar);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/1483683822_logout.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setToolTipText("Sair do formulário de cadastro");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        jPanel1.add(btnSair);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("Rua(*): ");

        jLabel9.setText("Número(*): ");

        jLabel10.setText("Bairro(*): ");

        tfRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfRuaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfRuaKeyTyped(evt);
            }
        });

        tfNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNumeroKeyTyped(evt);
            }
        });

        tfBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBairroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfBairroKeyTyped(evt);
            }
        });

        jLabel11.setText("Complemento: ");

        jLabel12.setText("Cidade(*): ");

        jLabel13.setText("Estado: ");

        tfComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfComplementoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfComplementoKeyTyped(evt);
            }
        });

        tfCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCidadeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCidadeKeyTyped(evt);
            }
        });

        jcbbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SP", "MS" }));

        jLabel14.setText("CEP:");

        try {
            ffCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel15.setText("Telefone Residencial: ");

        jLabel16.setText("Telefone Comercial: ");

        jLabel17.setText("Celular (WhatsApp)(*): ");

        jLabel18.setText("Celular 2 (opcional):");

        try {
            ffTelResidencial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            ffTelComercial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            ffWhatsapp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            ffCelular2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addComponent(jLabel12)
                            .addComponent(tfCidade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10)
                                .addComponent(tfBairro)
                                .addComponent(jcbbEstado, 0, 168, Short.MAX_VALUE))
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel14))
                                .addGap(0, 59, Short.MAX_VALUE))
                            .addComponent(tfComplemento)
                            .addComponent(ffCep, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ffTelResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ffTelComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ffWhatsapp, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(0, 34, Short.MAX_VALUE))
                            .addComponent(ffCelular2))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ffCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ffTelResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ffTelComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ffWhatsapp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ffCelular2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Endereço e Contato", jPanel3);

        taObservacoes.setColumns(20);
        taObservacoes.setRows(5);
        taObservacoes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                taObservacoesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(taObservacoes);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Observações", jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nome(*): ");

        tfNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNomeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNomeKeyTyped(evt);
            }
        });

        jLabel4.setText("CPF(*): ");

        try {
            ffCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ffCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ffCPFKeyReleased(evt);
            }
        });

        jLabel5.setText("RG:");

        jLabel2.setText("Data de Nascimento(*): ");

        jdcDataNasc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcDataNascPropertyChange(evt);
            }
        });

        jLabel3.setText("Sexo:");

        jcbbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(ffCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(tfRg))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdcDataNasc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jcbbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(tfNome))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ffCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Funcionario_Controller cc = new Funcionario_Controller();
        Funcionario_Model fm = capturaCampos();
        ValidaCPF val = new ValidaCPF();

        if (val.isCPF(ffCPF.getText().replace(".", "").replace("-", "")) == false) {
            JOptionPane.showMessageDialog(null, "CPF Inválido!");
        } else {
            if (validaCampos() == true) {
                if (fm.getId() != null) {
                    cc.atualizaDados(fm);
                    dispose();
                } else if (cc.verificaExistenciaCPF(fm.getCpf()) == false) {
                    JOptionPane.showMessageDialog(null, "Este CPF Já Existe no Sistema!");
                } else {
                    cc.gravaDados(fm);
                    dispose();
                }
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja ir ao formulário de pesquisa? ");
        if (confirmacao == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Deseja sair do Formulário de Cadastro/Edição de Funcionários? ");
        if (confirmacao == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void taObservacoesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taObservacoesKeyReleased
        taObservacoes.setText(taObservacoes.getText().toUpperCase());
        taObservacoes.setLineWrap(true);
    }//GEN-LAST:event_taObservacoesKeyReleased

    private void tfNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomeKeyTyped
        if (numeros.contains(evt.getKeyChar() + "") || simbolos.contains(evt.getKeyChar() + "")) {
            evt.consume();
        } else if (tfNome.getText().length() > 60) {

        }

    }//GEN-LAST:event_tfNomeKeyTyped

    private void tfRuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfRuaKeyTyped
        if (numeros.contains(evt.getKeyChar() + "") || simbolos.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_tfRuaKeyTyped

    private void tfComplementoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfComplementoKeyTyped
        if (numeros.contains(evt.getKeyChar() + "") || simbolos.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_tfComplementoKeyTyped

    private void tfCidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCidadeKeyTyped
        if (numeros.contains(evt.getKeyChar() + "") || simbolos.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_tfCidadeKeyTyped

    private void tfNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNumeroKeyTyped
        if (caracteres.contains(evt.getKeyChar() + "") || simbolos.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_tfNumeroKeyTyped

    private void tfBairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBairroKeyTyped
        if (numeros.contains(evt.getKeyChar() + "") || simbolos.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_tfBairroKeyTyped

    private void jdcDataNascPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcDataNascPropertyChange

    }//GEN-LAST:event_jdcDataNascPropertyChange

    private void tfNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomeKeyReleased
        tfNome.setText(tfNome.getText().toUpperCase());
    }//GEN-LAST:event_tfNomeKeyReleased

    private void tfRuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfRuaKeyReleased
        tfRua.setText(tfRua.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_tfRuaKeyReleased

    private void tfBairroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBairroKeyReleased
        tfBairro.setText(tfBairro.getText().toUpperCase());
    }//GEN-LAST:event_tfBairroKeyReleased

    private void tfComplementoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfComplementoKeyReleased
        tfComplemento.setText(tfComplemento.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_tfComplementoKeyReleased

    private void tfCidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCidadeKeyReleased
        tfCidade.setText(tfCidade.getText().toUpperCase());       // TODO add your handling code here:
    }//GEN-LAST:event_tfCidadeKeyReleased

    private void ffCPFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ffCPFKeyReleased
        Funcionario_Controller fc = new Funcionario_Controller();
        if (fc.verificaExistenciaCPF(ffCPF.getText()) == false) {
            JOptionPane.showMessageDialog(null, "CPF já cadastrado no sistema!");
            if (fun == null) {
                ffCPF.setText("   .   .   -  ");
                ffCPF.grabFocus();
            } else {
                ffCPF.setText(fun.getCpf());
            }
        }
    }//GEN-LAST:event_ffCPFKeyReleased

    private void btnAcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcessoActionPerformed
        Usuario_Controller usc = new Usuario_Controller();
        Usuario_Model usr = usc.retornaUsuarioFuncionario(fun);
        if (usr != null) {
            CadastroUsuario_View cadUser = new CadastroUsuario_View(null, true, usr);
            cadUser.setVisible(true);
        } else {
            CadastroUsuario_View cadUser = new CadastroUsuario_View(null, true, fun);
            cadUser.setVisible(true);
        }
    }//GEN-LAST:event_btnAcessoActionPerformed

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
            java.util.logging.Logger.getLogger(Cadastro_Funcionario_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastro_Funcionario_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastro_Funcionario_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastro_Funcionario_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cadastro_Funcionario_View dialog = new Cadastro_Funcionario_View(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup btgCargo;
    private javax.swing.JButton btnAcesso;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JFormattedTextField ffCPF;
    private javax.swing.JFormattedTextField ffCelular2;
    private javax.swing.JFormattedTextField ffCep;
    private javax.swing.JFormattedTextField ffTelComercial;
    private javax.swing.JFormattedTextField ffTelResidencial;
    private javax.swing.JFormattedTextField ffWhatsapp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcbbEstado;
    private javax.swing.JComboBox<String> jcbbSexo;
    private com.toedter.calendar.JDateChooser jdcDataNasc;
    private javax.swing.JTextArea taObservacoes;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfComplemento;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNumero;
    private javax.swing.JTextField tfRg;
    private javax.swing.JTextField tfRua;
    // End of variables declaration//GEN-END:variables
}
