/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Claudio Xavier
 */

@Entity
@Table(name = "tbl_itensDevolucao")

public class Itens_Devolucao_Model implements Serializable {
    
    @Id
    @Column(name = "numItem", nullable = false, columnDefinition = "INT")
    private int numItem;
    @Column(name = "valorItem", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valorItem;
    @Column(name = "quantidade", nullable = false, columnDefinition = "INT")
    private int quantidade;
    
    
    private Devolucao_Model devolucao; 
    
    
}
