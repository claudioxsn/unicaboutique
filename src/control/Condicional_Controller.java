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
import model.Condicional_Model;
import model.Funcionario_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Condicional_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Condicional_Model vm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(vm);
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

    public void atualizaDados(Condicional_Model vm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.merge(vm);
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
        Condicional_Model vm = em.find(Condicional_Model.class, id);
        try {
            em.getTransaction().begin();
            em.remove(vm);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Excluir! \n Erro: " + e);
        }
    }

    public List<Condicional_Model> pesquisaCondicionalDataStatus(Date dataIni, Date dataFim, String status) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT vm FROM Condicional_Model vm where vm.statusCondicional =:status and vm.dataCondicional BETWEEN :dataIni and :dataFim")
                .setParameter("status", status).setParameter("dataIni", dataIni).setParameter("dataFim", dataFim);
        return q.getResultList();
    }

    public Condicional_Model pesquisaCondicionalObjeto(Condicional_Model vm) {
        EntityManager em = getEM();

        Condicional_Model Condicional = (Condicional_Model) em.createQuery("SELECT vm FROM Condicional_Model vm WHERE vm =:Condicional").setParameter("Condicional", vm).getSingleResult();
        return Condicional;
    }

    public List<Condicional_Model> pesquisaCondicionaisFuncionario(Funcionario_Model fm) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT cm from Condicional_Model cm where cm.vendedor = :func").setParameter("func", fm);

        return q.getResultList();
    }
}
