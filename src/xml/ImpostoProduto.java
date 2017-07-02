/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

/**
 *
 * @author Administrador
 */
public class ImpostoProduto {
    
    private Double valorTotalProdutos;
    private String aliqIpi;

    public ImpostoProduto( String aliqIpi, Double valorTotalProdutos) {
        this.aliqIpi = aliqIpi;
        this.valorTotalProdutos = valorTotalProdutos;       
    }    
    

    public Double getValorTotalProdutos() {
        return valorTotalProdutos;
    }

    public void setValorTotalProdutos(Double valorTotalProdutos) {
        this.valorTotalProdutos = valorTotalProdutos;
    }

    public String getAliqIpi() {
        return aliqIpi;
    }

    public void setAliqIpi(String aliqIpi) {
        this.aliqIpi = aliqIpi;
    }
    
    
    
}
