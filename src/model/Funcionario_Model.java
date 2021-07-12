/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Claudio Xavier
 */
@Entity
@Table(name = "tbl_Funcionario")
public class Funcionario_Model extends Pessoa {

    public Funcionario_Model() {
    }
}
