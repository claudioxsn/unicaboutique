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
import model.Itens_Compra;
import model.Itens_Venda;
import model.Produto_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Produto_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Produto_Model pm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(pm);
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

    public void atualizaDados(Produto_Model pm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.merge(pm);
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
        Produto_Model pm = em.find(Produto_Model.class, id);
        try {
            em.getTransaction().begin();
            em.remove(pm);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Excluir! \n Erro: " + e);
        }
    }

    public List<Produto_Model> listarTodos() {
        EntityManager em = getEM();

        Query query = em.createQuery("select pm from Produto_Model pm order by pm.descricaoProduto");
        return query.getResultList();
    }

    public List<Produto_Model> pesquisarPorNome(String nomePesquisa) {
        EntityManager em = getEM();
        Query resultado_Pesquisa = em.createQuery("select pm from Produto_Model pm "
                + "where pm.descricaoProduto like :nomePesquisa");
        return resultado_Pesquisa.setParameter("nomePesquisa", "%" + nomePesquisa + "%").getResultList();
    }

    public List<Produto_Model> pesquisarObjeto(Produto_Model pmod) {
        EntityManager em = getEM();
        Query resultado_Pesquisa = em.createQuery("select pm from Produto_Model pm "
                + "where pm = :pmod");
        return resultado_Pesquisa.setParameter("pmod", pmod).getResultList();
    }

    public List<Produto_Model> pesquisarPorNomeEstoquePositivo(String nomePesquisa) {
        EntityManager em = getEM();
        Query resultado_Pesquisa = em.createQuery("select pm from Produto_Model pm "
                + "where pm.qtdEstoque > 0 and pm.descricaoProduto like :nomePesquisa");
        return resultado_Pesquisa.setParameter("nomePesquisa", "%" + nomePesquisa + "%").getResultList();
    }

    public List<Produto_Model> listaEstoqueAbaixoMinimo() {
        EntityManager em = getEM();
        Query resultado_Pesquisa = em.createQuery("select pm from Produto_Model pm where pm.qtdEstoque < pm.qtdMinima");
        return resultado_Pesquisa.getResultList();
    }

    public List<Produto_Model> listaEstoqueAbaixoDe(int qtd) {
        EntityManager em = getEM();
        Query resultado_Pesquisa = em.createQuery("select pm from Produto_Model pm where pm.qtdEstoque < :qtd").setParameter("qtd", qtd);
        return resultado_Pesquisa.getResultList();
    }

    public List<Itens_Venda> verificaPresencaProdutoVenda(Produto_Model pm) {
        EntityManager em = getEM();
        Query q = em.createQuery("select iv from Itens_Venda iv where iv.produto = :prod").setParameter("prod", pm);
        return q.getResultList();
    }

    public List<Itens_Compra> verificaPresencaProdutoCompra(Produto_Model pm) {
        EntityManager em = getEM();
        Query q = em.createQuery("select ic from Itens_Compra ic where ic.produto =:prod").setParameter("prod", pm);
        return q.getResultList();
    }

    public List<Produto_Model> verificaExistenciaCodBarra(String codbar) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT p from Produto_Model p where p.codBar = :codbar").setParameter("codbar", codbar);

        return q.getResultList();
    }

    public Produto_Model pesquisaCodBarr(String codbar) {
        EntityManager em = getEM();

        try {
            Produto_Model p = (Produto_Model) em.createQuery("SELECT p from Produto_Model p where "
                    + "p.codBar =:codbar").setParameter("codbar", codbar).getSingleResult();

            return p;
        } catch (NoResultException nre) {
            return null;
        }
        //JOptionPane.showMessageDialog(null, "Produto não encontrado!");

    }
}
