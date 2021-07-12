/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.Contas_Diversas_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Contas_Diversas_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Contas_Diversas_Model cdm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(cdm);
            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Gravar os Dados!" + e);
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void atualizaDados(Contas_Diversas_Model cdm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.merge(cdm);
            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar os Dados!" + e);
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

    }

    public void excluirDados(Integer id) {
        EntityManager em = getEM();
        Contas_Diversas_Model cdm = em.find(Contas_Diversas_Model.class, id);
        try {
            em.getTransaction().begin();
            em.remove(cdm);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Excluir! \n Erro: " + e);
        }
    }

    public List<Contas_Diversas_Model> listarTodas() {
        Query lista;

        EntityManager em = getEM();
        lista = em.createQuery("select c from Contas_Diversas_Model c");
        return lista.getResultList();

    }

    public List<Contas_Diversas_Model> contasPagarPorVencimentoNomeFantasia(String pesquisa, Date dataIni, Date dataFim) {
        Query lista;

        EntityManager em = getEM();
        lista = em.createQuery("select c from Contas_Diversas_Model c where c.dataPagamento = null and c.fornecedor like :nomeFantasia and c.dataVencimento "
                + "BETWEEN :dataIni and :dataFim").setParameter("nomeFantasia", "%" + pesquisa + "%")
                .setParameter("dataIni", dataIni).setParameter("dataFim", dataFim);
        return lista.getResultList();
    }

    public List<Contas_Diversas_Model> contasPagasPorPagamentoNomeFantasia(String pesquisa, Date dataIni, Date dataFim) {
        Query lista;

        EntityManager em = getEM();
        lista = em.createQuery("select c from Contas_Diversas_Model c where c.fornecedor like :nomeFantasia and "
                + " c.dataPagamento.dataAbertura "
                + "BETWEEN :dataIni and :dataFim").setParameter("nomeFantasia", "%" + pesquisa + "%")
                .setParameter("dataIni", dataIni).setParameter("dataFim", dataFim);
        return lista.getResultList();
    }

    public List<Contas_Diversas_Model> contasPagarPorVencimento(Date dataIni, Date dataFim) {
        Query lista;

        EntityManager em = getEM();
        lista = em.createQuery("select c from Contas_Diversas_Model c where c.dataPagamento IS NULL and c.dataVencimento "
                + "BETWEEN :dataIni and :dataFim").setParameter("dataIni", dataIni).setParameter("dataFim", dataFim);
        return lista.getResultList();
    }

    public List<Contas_Diversas_Model> contasPagasPorVencimento(Date dataIni, Date dataFim) {
        Query lista;

        EntityManager em = getEM();
        lista = em.createQuery("select c from Contas_Diversas_Model c where c.dataPagamento.dataAbertura "
                + "BETWEEN :dataIni and :dataFim").setParameter("dataIni", dataIni).setParameter("dataFim", dataFim);
        return lista.getResultList();
    }
}
