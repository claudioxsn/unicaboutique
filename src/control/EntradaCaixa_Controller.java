/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.Caixa_Model;
import model.EntradaCaixa_Model;

/**
 *
 * @author Claudio Xavier
 */
public class EntradaCaixa_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(EntradaCaixa_Model ecm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(ecm);
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

    public void atualizaDados(EntradaCaixa_Model ecm) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(ecm);
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

    public void excluirDados(EntradaCaixa_Model ecm) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("delete from EntradaCaixa_Model e where e =:entrada").setParameter("entrada", ecm);
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + e);
        }
    }

    public List<EntradaCaixa_Model> listarEntradasCaixa(Caixa_Model cm) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT ec from EntradaCaixa_Model ec where ec.caixa = :cm").setParameter("cm", cm);

        return q.getResultList();
    }
}
