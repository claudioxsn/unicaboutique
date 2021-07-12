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
import model.Itens_Condicional;
import model.Produto_Model;
import model.Condicional_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Itens_Condicional_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Itens_Condicional iv) {
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

    public void atualizaDados(Itens_Condicional iv) {
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

    public void excluirDados(Itens_Condicional iv) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("delete from Itens_Condicional item where item.numItem = :num "
                    + "and item.Condicional =:Condicional").setParameter("num", iv.getNumItem()).setParameter("Condicional", iv.getCondicional());
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + e);
        }
    }

    public List<Itens_Condicional> pesquisaUnicoItem(Itens_Condicional iv) {
        EntityManager em = getEM();

        Query q = em.createQuery("select iv from Itens_Condicional iv where iv.produto = :prod "
                + "and iv.Condicional = :Condicional").setParameter("prod", iv.getProduto()).setParameter("Condicional", iv.getCondicional());
        return q.getResultList();
    }

    public Itens_Condicional verificaExistenciaItem(Produto_Model pm) {
        EntityManager em = getEM();
        Itens_Condicional it;
        Query q = em.createQuery("select iv from Itens_Condicional iv where iv.produto = :pm").setParameter("pm", pm);
        try {
            it = (Itens_Condicional) q.getSingleResult();
            return it;
        } catch (NoResultException nre) {
            it = null;
            return it;
        }
    }

    public List<Itens_Condicional> listaItensCondicional(Condicional_Model vm) {
        EntityManager em = getEM();

        Query q = em.createQuery("select iv from Itens_Condicional iv "
                + "where iv.Condicional = :Condicional").setParameter("Condicional", vm);

        return q.getResultList();
    }

    public List<Itens_Condicional> listaItensCondicionalPeriodo(Date dataInicio, Date dataFim) {
        EntityManager em = getEM();

        Query q = em.createQuery("select iv from Itens_Condicional iv where iv.Condicional.dataCondicional between :dataInicio"
                + " and :dataFim").setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim);
        return q.getResultList();
    }

    public void excluirItensDeUmaCondicional(Condicional_Model vm) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("delete from Itens_Condicional item where item.Condicional =:Condicional").setParameter("Condicional", vm);
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + e);
        }
    }

    public void atualizarQtdTodosItensCondicional(Condicional_Model vm, int qtd) {
        EntityManager em = getEM();

        try {
            em.getTransaction().begin();
            Query q = em.createQuery("Update Itens_Condicional iv set iv.quantidade =:qtd where iv.Condicional =:Condicional").setParameter("qtd", qtd).setParameter("Condicional", vm);
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o estoque após o cancelamento da Condicional!");
        }
    }

}
