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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.Caixa_Model;

/**
 *
 * @author Claudio Xavier
 */
public class CaixaController {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Caixa_Model cm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(cm);
            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao abrir o Caixa" + e);
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void atualizaDados(Caixa_Model cm) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(cm);
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

    public boolean verificaCaixaAberto(Date data) {
        EntityManager em = getEM();

        Query q = em.createQuery("select cx from Caixa_Model cx where cx.dataAbertura = :data")
                .setParameter("data", data);

        if (q.getResultList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Caixa_Model pesquisaCaixa(Date data) {
        EntityManager em = getEM();

        try {
            Caixa_Model cx = (Caixa_Model) em.createQuery("select cx from Caixa_Model cx where cx.dataAbertura = :data")
                    .setParameter("data", data).getSingleResult();
            return cx;
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Caixa_Model> imprimeCaixaSelecionado(Caixa_Model cm) {
        EntityManager em = getEM();

        try {
            Query cx = em.createQuery("select cx from Caixa_Model cx where cx =:cm")
                    .setParameter("cm", cm);
            return cx.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Caixa_Model> relatorioCaixaPeriodo(Date dataIni, Date dataFim) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT c from Caixa_Model c where c.dataAbertura BETWEEN :ini and :fim")
                .setParameter("ini", dataIni).setParameter("fim", dataFim);

        return q.getResultList();
    }

    public List<Caixa_Model> listaCaixaDataStatus(boolean status, Date dataIni, Date dataFim) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT c from Caixa_Model c where c.status =:status and c.dataAbertura BETWEEN :ini and :fim").setParameter("status", status)
                .setParameter("ini", dataIni).setParameter("fim", dataFim);

        return q.getResultList();
    }

    public List<Caixa_Model> listarTodos() {
        EntityManager em = getEM();

        Query q = (Query) em.createQuery("select cx from Caixa_Model cx");
        return q.getResultList();
    }

    public List<Caixa_Model> verificaExistenciaCaixaComStatusAberto() {
        EntityManager em = getEM();

        Query q = em.createQuery("select c from Caixa_Model c where c.status = true and c.dataAbertura <> :data").setParameter("data", new Date());

        return q.getResultList();
    }
}
