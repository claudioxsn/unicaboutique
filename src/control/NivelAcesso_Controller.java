/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.Itens_Venda;
import model.NivelAcesso_Model;
import model.Usuario_Model;

/**
 *
 * @author Claudio Xavier
 */
public class NivelAcesso_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(NivelAcesso_Model nm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(nm);
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

    public void atualizaDados(NivelAcesso_Model nm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.merge(nm);
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

    public void excluirDados(NivelAcesso_Model nv) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("delete from NivelAcesso_Model nv where nv =:nivel").setParameter("nivel", nv);
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + e);
        }
    }

    public List<NivelAcesso_Model> verificaNiveisUsuario(Usuario_Model usr) {

        EntityManager em = getEM();

        Query q = em.createQuery("SELECT nv from NivelAcesso_Model nv where nv.usuario =:usr").setParameter("usr", usr);

        return q.getResultList();
    }

    public boolean pesquisaNivelAcesso(NivelAcesso_Model nv) {
        EntityManager em = getEM();

        try {
            NivelAcesso_Model nvAc = (NivelAcesso_Model) em.createQuery("SELECT nv from NivelAcesso_Model nv where nv = :nivel").setParameter("nivel", nv).getSingleResult();
            return true;
        } catch (NoResultException nre) {
            return false;
        }

    }

}
