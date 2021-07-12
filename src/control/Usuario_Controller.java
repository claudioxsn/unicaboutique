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
import model.Funcionario_Model;
import model.Usuario_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Usuario_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Usuario_Model um) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(um);
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

    public void atualizaDados(Usuario_Model um) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.merge(um);
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
        Usuario_Model um = em.find(Usuario_Model.class, id);
        try {
            em.getTransaction().begin();
            em.remove(um);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Excluir! \n Erro: " + e);
        }
    }

    public List<Usuario_Model> verificaExistenciaUsuario(String nome) {

        EntityManager em = getEM();

        Query q = em.createQuery("SELECT u from Usuario_Model u where u.usuario =:nome").setParameter("nome", nome);

        return q.getResultList();
    }

    public Usuario_Model retornaUsuarioFuncionario(Funcionario_Model fm) {

        EntityManager em = getEM();

        try {
            Usuario_Model usr = (Usuario_Model) em.createQuery("SELECT usr from Usuario_Model usr where usr.funcionario =:fun")
                    .setParameter("fun", fm).getSingleResult();

            return usr;
        } catch (NoResultException nre) {
        }
        return null;
    }

    public boolean verificaSeNomeUsuarioPertenceFuncionario(Usuario_Model usr) {

        EntityManager em = getEM();

        try {
            Usuario_Model us = (Usuario_Model) em.createQuery("SELECT us from Usuario_Model us where us.usuario =:usuario "
                    + "and us.funcionario =:fun").setParameter("usuario", usr.getUsuario())
                    .setParameter("fun", usr.getFuncionario()).getSingleResult();
            JOptionPane.showMessageDialog(null, us);
            return true;
        } catch (NoResultException nre) {
            JOptionPane.showMessageDialog(null, nre);
            return false;
        }

    }

    public Usuario_Model autentica(String usuario, String senha) {
        EntityManager em = getEM();

        try {
            Usuario_Model usr = (Usuario_Model) em.createQuery("SELECT u from Usuario_Model u where u.usuario =:usr and u.senha =:senha")
                    .setParameter("usr", usuario).setParameter("senha", senha).getSingleResult();
            return usr;
        } catch (NoResultException nre) {
            return null; 
        }

    }
}
