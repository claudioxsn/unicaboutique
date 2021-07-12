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
import model.Compra_Model;
import model.Empresa_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Empresa_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Empresa_Model emp) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(emp);
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

    public void atualizaDados(Empresa_Model emp) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(emp);
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
        Empresa_Model emp = em.find(Empresa_Model.class, id);
        try {
            em.getTransaction().begin();
            em.remove(emp);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Excluir! \n Erro: " + e);
        }
    }

    public Empresa_Model buscarEmpresa() {
        EntityManager em = getEM();

        try {
            Empresa_Model emp = (Empresa_Model) em.createQuery("SELECT e from Empresa_Model e where e.id = '1'").getSingleResult();
            return emp;
        } catch (Exception e) {
            return null;
        }
    }
}
