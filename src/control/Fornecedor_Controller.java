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
import model.Fornecedor_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Fornecedor_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Fornecedor_Model fm) {
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

    public void atualizaDados(Fornecedor_Model fm) {
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
        Fornecedor_Model fm = em.find(Fornecedor_Model.class, id);
        try {
            em.getTransaction().begin();
            em.remove(fm);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Excluir! \n Erro: " + e);
        }
    }

    public List<Fornecedor_Model> listaTodos() {
        EntityManager em = getEM();

        Query q = em.createQuery("select f from Fornecedor_Model f");

        return q.getResultList();
    }

    public List<Fornecedor_Model> pesquisaNomeFantasia(String pesquisa) {
        EntityManager em = getEM();
        Query q = em.createQuery("select f from Fornecedor_Model f where f.nomeFantasia like :nome").setParameter("nome", "%" + pesquisa + "%");
        return q.getResultList();
    }

    public List<Fornecedor_Model> pesquisaRazaoSocial(String pesquisa) {
        EntityManager em = getEM();
        Query q = em.createQuery("select f from Fornecedor_Model f where f.razaoSocial like :razaoSocial").setParameter("razaoSocial", "%" + pesquisa + "%");
        return q.getResultList();
    }

    public List<Fornecedor_Model> pesquisaPorCnpj(String pesquisa) {
        EntityManager em = getEM();
        Query q = em.createQuery("select f from Fornecedor_Model f where f.cnpj like :cnpj").setParameter("cnpj", "%" + pesquisa + "%");
        return q.getResultList();
    }

    public boolean verificaExistenciaCnpj(String pesquisa) {
        EntityManager em = getEM();
        Query q = em.createQuery("select f from Fornecedor_Model f where f.cnpj like :cnpj").setParameter("cnpj", "%" + pesquisa + "%");
        return (q.getResultList().isEmpty() ? true : false);
    }

    public List<Compra_Model> verificaComprasFornecedor(Fornecedor_Model fm) {
        EntityManager em = getEM();

        Query q = em.createQuery("select cm from Compra_Model cm where cm.fornecedor =:fm").setParameter("fm", fm);

        return q.getResultList();

    }
}
