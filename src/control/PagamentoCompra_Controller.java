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
import model.Compra_Model;
import model.PagamentoCompra_Model;

/**
 *
 * @author Claudio Xavier
 */
public class PagamentoCompra_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(PagamentoCompra_Model pcm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(pcm);
            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar as parcelas" + e);
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void atualizaDados(PagamentoCompra_Model pcm) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(pcm);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar a parcela!" + e);
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

    }

    public List<PagamentoCompra_Model> listaParcelasCompra(Compra_Model cm) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT p from PagamentoCompra_Model p where p.compra =:cm").setParameter("cm", cm);

        return q.getResultList();
    }

    public List<PagamentoCompra_Model> listaParcelasPagasPeriodo(Date dataIni, Date dataFim) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT p from PagamentoCompra_Model p where p.dataPagamento.dataAbertura"
                + " BETWEEN :ini and :fim").setParameter("ini", dataIni).setParameter("fim", dataFim);

        return q.getResultList();
    }

    public List<PagamentoCompra_Model> listaParcelasNaoPagasPeriodo(Date dataIni, Date dataFim) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT p from PagamentoCompra_Model p where p.dataPagamento = null and p.dataVencimento BETWEEN :dataIni and :dataFim")
                .setParameter("dataIni", dataIni).setParameter("dataFim", dataFim);

        return q.getResultList();
    }

    public List<PagamentoCompra_Model> verificaExistenciaBoleto(String num, Compra_Model cm) {
        EntityManager em = getEM();

        Query q = em.createQuery("select p from PagamentoCompra_Model p where p.numBoleto = :num and p.compra =:compra").setParameter("num", num).setParameter("compra", cm);

        return q.getResultList();
    }
}
