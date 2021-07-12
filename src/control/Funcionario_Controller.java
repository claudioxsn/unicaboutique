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
import model.Funcionario_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Funcionario_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Funcionario_Model fm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(fm);
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

    public void atualizaDados(Funcionario_Model fm) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(fm);
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
        Funcionario_Model fm = em.find(Funcionario_Model.class, id);
        try {
            em.getTransaction().begin();
            em.remove(fm);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Excluir! \n Erro: " + e);
        }
    }

    public List<Funcionario_Model> listarTodos() {
        EntityManager em = getEM();

        Query query = em.createQuery("select fm from Funcionario_Model fm order by fm.nome");
        return query.getResultList();
    }

    public List<Funcionario_Model> pesquisarPorNome(String nomePesquisa) {
        EntityManager em = getEM();
        Query resultado_Pesquisa = em.createQuery("select fm from Funcionario_Model fm "
                + "where fm.nome like :nomePesquisa");
        return resultado_Pesquisa.setParameter("nomePesquisa", "%" + nomePesquisa + "%").getResultList();
    }

    public List<Funcionario_Model> pesquisarPorCpf(String cpfPesquisa) {
        EntityManager em = getEM();
        Query resultado_Pesquisa = em.createQuery("select fm from Funcionario_Model fm "
                + "where fm.cpf like :cpfPesquisa");
        return resultado_Pesquisa.setParameter("cpfPesquisa", "%" + cpfPesquisa + "%").getResultList();
    }

    public boolean verificaExistenciaCPF(String cpfPesquisa) {
        EntityManager em = getEM();
        Query query = (Query) em.createQuery("select fm from Funcionario_Model fm where "
                + "fm.cpf=:cpfPesquisa").setParameter("cpfPesquisa", cpfPesquisa);

        if (query.getResultList().isEmpty()) {

            return true;
        } else {

            return false;
        }
    }

    public List<Funcionario_Model> pesquisaPorCargo(String cargo) {
        EntityManager em = getEM();

        Query q = em.createQuery("select fm from Funcionario_Model fm where fm.cargo = :cargo")
                .setParameter("cargo", cargo);

        return q.getResultList();
    }

    public List<Funcionario_Model> pesquisaMecanicoNome(String nome) {
        EntityManager em = getEM();

        Query q = em.createQuery("select fm from Funcionario_Model fm where fm.nome like :nomePesquisa "
                + "and fm.cargo = 'mecanico'");
        return q.setParameter("nomePesquisa", "%" + nome + "%").getResultList();
    }

}
