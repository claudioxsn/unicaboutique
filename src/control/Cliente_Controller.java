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
import model.Cliente_Model;
import model.Venda_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Cliente_Controller {
      public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Cliente_Model cm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(cm);
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

    public void atualizaDados(Cliente_Model cm) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(cm);
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

    public void excluirDados (Integer id) {
        EntityManager em = getEM();
        Cliente_Model cm =  em.find(Cliente_Model.class, id);
        try {
            em.getTransaction().begin();
            em.remove(cm);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Excluir! \n Erro: " + e);
        }
    }

    public List<Cliente_Model> listarTodos() {
        EntityManager em = getEM();

        Query query = em.createQuery("select cm from Cliente_Model cm order by cm.nome");
        return query.getResultList();
    }

    public List<Cliente_Model> pesquisarPorNome(String nomePesquisa) {
        EntityManager em = getEM();
        Query resultado_Pesquisa = em.createQuery("select cm from Cliente_Model cm "
                + "where cm.nome like :nomePesquisa");
        return resultado_Pesquisa.setParameter("nomePesquisa", "%" + nomePesquisa + "%").getResultList();
    }
    
    public List<Cliente_Model> pesquisarPorCpf(String cpfPesquisa) {
        EntityManager em = getEM();
        Query resultado_Pesquisa = em.createQuery("select cm from Cliente_Model cm "
                + "where cm.cpf like :cpfPesquisa");
        return resultado_Pesquisa.setParameter("cpfPesquisa", "%" + cpfPesquisa + "%").getResultList();
    }
    
    public boolean verificaExistenciaCPF(String cpfPesquisa) {
        EntityManager em = getEM();
        Query query = (Query) em.createQuery("select cm from Cliente_Model cm where "
                + "cm.cpf=:cpfPesquisa").setParameter("cpfPesquisa", cpfPesquisa);

        if (query.getResultList().isEmpty()) {

            return true;
        } else {

            return false;
        }
    }
    
    public List<Venda_Model> verificaPresencaClienteVenda(Cliente_Model cliente){
        EntityManager em = getEM(); 
        
        Query q = em.createQuery("select vm from Venda_Model vm where vm.cliente = :cliente").setParameter("cliente", cliente);
        
        return q.getResultList();
    }
    
}
