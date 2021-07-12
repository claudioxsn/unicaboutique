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
import model.Funcionario_Model;
import model.Venda_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Venda_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Venda_Model vm) {
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

    public void atualizaDados(Venda_Model vm) {
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
        Venda_Model vm = em.find(Venda_Model.class, id);
        try {
            em.getTransaction().begin();
            em.remove(vm);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Excluir! \n Erro: " + e);
        }
    }

    public List<Venda_Model> pesquisaVendaDataStatus(Date dataIni, Date dataFim, String status) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT vm FROM Venda_Model vm where vm.statusVenda =:status and vm.dataVenda BETWEEN :dataIni and :dataFim")
                .setParameter("status", status).setParameter("dataIni", dataIni).setParameter("dataFim", dataFim);
        return q.getResultList();
    }

    public Venda_Model pesquisaVendaObjeto(Venda_Model vm) {
        EntityManager em = getEM();

        Venda_Model venda = (Venda_Model) em.createQuery("SELECT vm FROM Venda_Model vm WHERE vm =:venda").setParameter("venda", vm).getSingleResult();
        return venda;
    }

    public List<Venda_Model> pesquisaVendaFuncionario(Funcionario_Model fm) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT vm FROM Venda_Model vm WHERE vm.funcionario =:func").setParameter("func", fm);

        return q.getResultList();
    }

}
