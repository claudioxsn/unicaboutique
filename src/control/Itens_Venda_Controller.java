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
import model.Itens_Venda;
import model.Produto_Model;
import model.Venda_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Itens_Venda_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Itens_Venda iv) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(iv);
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

    public void atualizaDados(Itens_Venda iv) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.merge(iv);
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

    public void excluirDados(Itens_Venda iv) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("delete from Itens_Venda item where item.numItem = :num "
                    + "and item.venda =:venda").setParameter("num", iv.getNumItem()).setParameter("venda", iv.getVenda());
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + e);
        }
    }

    public List<Itens_Venda> pesquisaUnicoItem(Itens_Venda iv) {
        EntityManager em = getEM();

        Query q = em.createQuery("select iv from Itens_Venda iv where iv.produto = :prod "
                + "and iv.venda = :venda").setParameter("prod", iv.getProduto()).setParameter("venda", iv.getVenda());
        return q.getResultList();
    }

    public Itens_Venda verificaExistenciaItem(Produto_Model pm) {
        EntityManager em = getEM();
        Itens_Venda it;
        Query q = em.createQuery("select iv from Itens_Venda iv where iv.produto = :pm").setParameter("pm", pm);
        try {
            it = (Itens_Venda) q.getSingleResult();
            return it;
        } catch (NoResultException nre) {
            it = null;
            return it;
        }
    }

    public List<Itens_Venda> listaItensVenda(Venda_Model vm) {
        EntityManager em = getEM();

        Query q = em.createQuery("select iv from Itens_Venda iv "
                + "where iv.venda = :venda").setParameter("venda", vm);

        return q.getResultList();
    }

    public List<Itens_Venda> listaItensVendaPeriodo(Date dataInicio, Date dataFim) {
        EntityManager em = getEM();

        Query q = em.createQuery("select iv from Itens_Venda iv where iv.venda.dataVenda between :dataInicio"
                + " and :dataFim").setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim);
        return q.getResultList();
    }

    public void excluirItensDeUmaVenda(Venda_Model vm) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("delete from Itens_Venda item where item.venda =:venda").setParameter("venda", vm);
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + e);
        }
    }

    public void atualizarQtdTodosItensVenda(Venda_Model vm, int qtd) {
        EntityManager em = getEM();

        try {
            em.getTransaction().begin();
            Query q = em.createQuery("Update Itens_Venda iv set iv.quantidade =:qtd where iv.venda =:venda").setParameter("qtd", qtd).setParameter("venda", vm);
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o estoque após o cancelamento da venda!");
        }
    }

}
