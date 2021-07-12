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
import model.Compra_Model;
import model.Itens_Compra;
import model.Produto_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Itens_Compra_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Itens_Compra item) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(item);
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

    public void atualizaDados(Itens_Compra item) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.merge(item);
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

    public void excluirDados(Itens_Compra it) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("delete from Itens_Compra item where item.numItem = :num "
                    + "and item.compra =:compra").setParameter("num", it.getNumItem()).setParameter("compra", it.getCompra());
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + e);
        }
    }

    public void atualizaItensCompra(Compra_Model cm, int qtd) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("update Itens_Compra c set c.quantidadeAtendida = :qtd where c.compra =:compra").setParameter("qtd", qtd)
                    .setParameter("compra", cm);
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + e);
        }
    }

    public List<Itens_Compra> pesquisaUnicoItem(Itens_Compra item) {
        EntityManager em = getEM();

        Query q = em.createQuery("select it from Itens_Compra it where it.produto = :prod "
                + "and it.compra = :compra").setParameter("prod", item.getProduto()).setParameter("compra", item.getCompra());
        return q.getResultList();
    }

    public Itens_Compra verificaExistenciaItem(Produto_Model pm) {
        EntityManager em = getEM();
        Itens_Compra it;
        Query q = em.createQuery("select it from Itens_Compra it where it.produto = :pm").setParameter("pm", pm);
        try {
            it = (Itens_Compra) q.getSingleResult();
            return it;
        } catch (NoResultException nre) {
            it = null;
            return it;
        }
    }

    public List<Itens_Compra> listaItensCompra(Compra_Model cm) {
        EntityManager em = getEM();

        Query q = em.createQuery("select it from Itens_Compra it "
                + "where it.compra = :compra").setParameter("compra", cm);

        return q.getResultList();
    }

    public List<Itens_Compra> listaItensCompraPeriodo(Date dataInicio, Date dataFim) {
        EntityManager em = getEM();

        Query q = em.createQuery("select it from Itens_Compra it where IT.compra.dataCompra between :dataInicio"
                + " and :dataFim").setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim);
        return q.getResultList();
    }

}
