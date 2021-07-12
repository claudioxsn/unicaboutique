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
import model.Caixa_Model;
import model.Cliente_Model;
import model.Venda_Model;
import model.PagamentoVenda_Model;

/**
 *
 * @author Claudio Xavier
 */
public class Pagamento_Venda_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(PagamentoVenda_Model pvm) {
        EntityManager em = getEM();
        try {

            em.getTransaction().begin();
            em.persist(pvm);
            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar as parcelas" + e);
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void atualizaDados(PagamentoVenda_Model pvm) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(pvm);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar a parcela!" + e);
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

    }

    public boolean verificaExistenciaDeParcelasDaVenda(Venda_Model vm) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT p FROM PagamentoVenda_Model p where p.venda = :vm").setParameter("vm", vm);

        if (q.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public List<PagamentoVenda_Model> listarParcelasVencidas(Cliente_Model cm, Date vencimento) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT p from PagamentoVenda_Model p where p.venda.cliente =:cm and p.dataPagamento = null and p.dataVencimento < :vencimento order by p.dataVencimento")
                .setParameter("cm", cm).setParameter("vencimento", vencimento);

        return q.getResultList();
    }

    public List<PagamentoVenda_Model> listarDebitoVendaCliente(Cliente_Model cm) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT p from PagamentoVenda_Model p where p.venda.cliente =:cm and p.dataPagamento = null order by p.dataVencimento").setParameter("cm", cm);

        return q.getResultList();
    }

    public List<PagamentoVenda_Model> listarParcelasPagasCliente(Cliente_Model cm) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT p from PagamentoVenda_Model p where p.venda.cliente =:cm and p.dataPagamento != null order by p.dataPagamento").setParameter("cm", cm);

        return q.getResultList();
    }

    public List<PagamentoVenda_Model> emitirRecibo(Cliente_Model cm, Caixa_Model cx) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT p from PagamentoVenda_Model p where p.venda.cliente =:cm and p.dataPagamento =:caixa").setParameter("cm", cm).setParameter("caixa", cx);

        return q.getResultList();
    }

    public List<PagamentoVenda_Model> listaParcelasPeriodo(Date ini, Date fim) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT p from PagamentoVenda_Model p where p.dataPagamento IS NULL and p.dataVencimento "
                + "BETWEEN :ini and :fim").setParameter("ini", ini).setParameter("fim", fim);
        
        return q.getResultList();
    }
}
