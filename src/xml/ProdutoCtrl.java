/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.util.List;

/**
 *
 * @author Administrador
 */
public class ProdutoCtrl {
    
    List<Produto> listaProdutos;
    Produto produto;
    Elementos elementos;
    
    public List<Produto> montarObjProduto(){
        
        elementos = new Elementos();
        
        //preenche uma lista com os produtos em formato de objeto já montado
        listaProdutos = elementos.obterProdutos();
        
        return listaProdutos;
    }
    
}
