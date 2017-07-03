/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Jadson
 */
public class Main {

    static ProdutoCtrl produtoCtrl;
    static List<Produto> listaProdutos;
    static List <ImpostoProduto> listaTotalProdutosIpi;
    static Calculo calculo;
    
    static Map <String,List<Produto>> mapaProdutoIpi;
        
    public static void main(String[] args) {
       
        produtoCtrl = new ProdutoCtrl();
        CalcularImposto ci = new CalcularImposto(); //ojeto da classe calcular imposto
        calculo = new Calculo();
        
        listaProdutos = produtoCtrl.montarObjProduto();
        //ci.listarIpi(listaProdutos);
        
        ci.separarProdutosIpi(listaProdutos);
        
        mapaProdutoIpi = ci.separarProdutosIpi(listaProdutos);
        
        ci.imprimirProdutosMapaIpi(mapaProdutoIpi);
        
        listaTotalProdutosIpi = ci.buscarTotalProdutosIpi(mapaProdutoIpi);
        
        calculo.calcular(listaTotalProdutosIpi);
        
        /*
        for(ImpostoProduto ip: listaTotalProdutosIpi ){
            System.out.println("ALIQUOTA: " + ip.getAliqIpi() + "TOTAL = " + ip.getValorTotalProdutos());
        }
        
        
        double valorTotaProdutos = 0;
        double valorTotalIcms = 0;
        double ValorTotalIpi = 0;
        
        for(Produto p: listaProdutos){
                                    
            valorTotaProdutos += p.getValorTotalItem();
            valorTotalIcms += p.getValorIcms();
            ValorTotalIpi += p.getIpiValor();
        }      
        
        System.out.printf("valor total dos produtos = %.2f \n", valorTotaProdutos);
        System.out.printf("valor total do ICMS = %.2f \n" , valorTotalIcms);
        System.out.printf("valor total do IPI = %.2f \n" , ValorTotalIpi);
        
        System.out.printf("Valor total NFe = %.2f \n" ,(valorTotaProdutos + ValorTotalIpi) );
        */
    }

}
