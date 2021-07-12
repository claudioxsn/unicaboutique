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
import model.Compra_Model;
import model.Itens_Compra;

/**
 *
 * @author Claudio Xavier
 */
public class Compra_Controller {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnicaBoutiquePU");
        return emf.createEntityManager();
    }

    public void gravaDados(Compra_Model cm) {
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

    public void atualizaDados(Compra_Model cm) {
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

    public void excluirDados(Integer id) {
        EntityManager em = getEM();
        Compra_Model cm = em.find(Compra_Model.class, id);
        try {
            em.getTransaction().begin();
            em.remove(cm);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Excluir! \n Erro: " + e);
        }
    }

    public List<Compra_Model> pesquisaCompraData(Date dataIni, Date dataFim, String status) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT CM FROM Compra_Model cm where cm.statusCompra =:status and cm.dataCompra BETWEEN :dataIni and :dataFim")
                .setParameter("status", status).setParameter("dataIni", dataIni).setParameter("dataFim", dataFim);
        return q.getResultList();
    }

    public Compra_Model pesquisaPorNumeroCompra(int num) {
        EntityManager em = getEM();
        Compra_Model cm = (Compra_Model) em.createQuery("SELECT CM FROM Compra_Model cm WHERE CM.numCompra =:numCompra")
                .setParameter("numCompra", num).getSingleResult();
        return cm;
    }

    public List<Itens_Compra> verificaExistenciaQtdAtendida(Compra_Model cm) {
        EntityManager em = getEM();

        Query q = em.createQuery("SELECT it from Itens_Compra it where it.compra =:compra and it.quantidadeAtendida = 0").setParameter("compra", cm);

        return q.getResultList();
    }

    public List<Compra_Model> verificaExistenciaNota(String nota) {
        EntityManager em = getEM();

        Query q = em.createQuery("select cm from Compra_Model cm where cm.numNota = :nota").setParameter("nota", nota);

        return q.getResultList();
    }
}
