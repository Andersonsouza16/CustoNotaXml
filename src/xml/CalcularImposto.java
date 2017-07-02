/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrador
 * 
 * 
 */
public class CalcularImposto {
    
    static ProdutoCtrl produtoCtrl;
    
    List <String> aliqIpiPresentes;
    List <Produto> listaProdutoPorIpi;
    String ipiAliquota;
    Produto produto;
    
       
    
    public void listarIpi(List<Produto> listaProdutos){
               
        aliqIpiPresentes = new ArrayList();
        
        int numero = 1;
        String num;
        
        for(Produto p: listaProdutos){
            num = Double.toString(p.getIpiAliq());
            if(!aliqIpiPresentes.contains(num)){
                aliqIpiPresentes.add(num);
            }
        }
        
        for(Object p: aliqIpiPresentes){            
                System.out.println("aliquotas da nota " + p);            
        }
        
    }
    
    public Map <String,List<Produto>> separarProdutosIpi(List<Produto> listaProdutos){       
        
        Map <String,List<Produto>> mapaProdutoIpi = new HashMap<>();
                        
        listaProdutoPorIpi = new ArrayList<>();
        
        //PERCORRER LISTA COMPLETA COM TODOS OS PRODUTOS COM IPI DIVERSOS
        for(Produto p: listaProdutos){
            //PEGAR A ALIQUOTA DE CADA PRODUTO
            String aliq  = Double.toString(p.getIpiAliq());
            //SE O MAPA ESTIVER VAZIO INSERE O PRIMEIRO ITEM NA LISTA E ADICIONA NO MAPA, PASSANDO A ALIQUOTA COMO CHAVE
            if(mapaProdutoIpi.isEmpty()){                 
                listaProdutoPorIpi.add(p);
                mapaProdutoIpi.put(aliq, listaProdutoPorIpi);
            }else{//SE O MAPA DE PRODUTOS SEPARADOS POR IPI JÁ CONTÉM ITEM 
                //TESTA SE O MAPA JÁ CONTÉM ITENS COM A CHAVE QUE É A ALIQUOTA DO PRODUTO PASSADO NESTE MOMENTO
                if(mapaProdutoIpi.containsKey(aliq)){
                   //SE JA POSSUI CHAVE COM ESTA ALIQUOTA, ADICIONA NO MAPA O PRODUTO À LISTA COM ITENS IGUAIS (MESMO IPI) 
                   mapaProdutoIpi.get(aliq).add(p);
                }
                else{//SE NÃO HOUVER A CHAVE, OU SEJA, NÃO TEM NO MAPA TEM PRODUTO COM A ALIQUOTA EM QUESTÃO
                    //INSTANCIA UMA NOVA LISTA QUE RECEBERA ESTE PRODUTO
                    listaProdutoPorIpi = new ArrayList<>();
                    //ADICIONA ESTE PRODUTO NA NOVA LISTA
                    listaProdutoPorIpi.add(p);
                    //E ADICIONA A NOVA LISTA AO MAPA
                    mapaProdutoIpi.put(aliq, listaProdutoPorIpi);
                }
            }            

        }
        
        return mapaProdutoIpi;
        
    }
    
    public void imprimirProdutosMapaIpi(Map <String,List<Produto>> mapaProdIpi){
        
        //LISTA QUE VAI GUARDAR OS VALOR DE CADA LISTA PRODUTOS COM A CHAVE IPI PRESENTES NO MAPA
        List <Produto> listaValorMapa;
        
        //PERCORRER O MAPA PEGANDO AS CHAVES, QUE NESTE MAPA É A ALIQUOTA DE IPI
        for(String chave: mapaProdIpi.keySet()){
                       
            System.out.println("chave " + chave);
            //AQUI A LISTA É CRIADA E RECEBE OS VALORES DAS LISTAS CONTENDO OS PRODUTOS DE CADA IPI
            listaValorMapa = mapaProdIpi.get(chave); 
            //ZERA PRA CALCULAR O VALOR TOTAL DOS PRODUTOS DE CADA IPI
            
            //PERCORRE A LISTA DE CADA IPI
            for(Produto p: listaValorMapa){
                System.out.println("Produto = " + p.getDescricao() + " Aliquota = " + p.getIpiAliq());              
            }        
        }
    }
    
    
    public List<ImpostoProduto> buscarTotalProdutosIpi(Map <String,List<Produto>> mapaProdIpi){
         //OBJETO QUE GUARDA A ALIQ. DE IPI E O VALOR TOTAL DOS PRODUTOS COM ESTA ALIQUOTA
        ImpostoProduto impostoProduto;
        //GUARDA O VALOR TOTAL DOS PRODUTOS CALCULADOS POR IPI
        Double totalProdutoIpi;
        
        //LISTA QUE SERA RETORNADA COM A ALIQUOTA DE IPI E VALOR TOTAL DOS PRODUTOS DE CADA ALIQ.
        List<ImpostoProduto> listaTotalProdutosIpi = new ArrayList<>(); 
        
        //LISTA QUE VAI GUARDAR OS VALOR DE CADA LISTA PRODUTOS COM A CHAVE IPI PRESENTES NO MAPA
        List <Produto> listaValorMapa;
        
        //PERCORRER O MAPA PEGANDO AS CHAVES, QUE NESTE MAPA É A ALIQUOTA DE IPI
        for(String chave: mapaProdIpi.keySet()){                       
            
            //AQUI A LISTA É CRIADA E RECEBE OS VALORES DAS LISTAS CONTENDO OS PRODUTOS DE CADA IPI
            listaValorMapa = mapaProdIpi.get(chave); 
            //ZERA PRA CALCULAR O VALOR TOTAL DOS PRODUTOS DE CADA IPI
            totalProdutoIpi = 0.0;
            //PERCORRE A LISTA DE CADA IPI
            for(Produto p: listaValorMapa){
                //System.out.println("Produto = " + p.getDescricao() + " Aliquota = " + p.getIpiAliq());                
                totalProdutoIpi += p.getValorTotalItem();                
            }
            //CRIA UM OBJETO CONTENDO O A ALIQ. DE IPI E VALOR TOTAL DOS PRODUTOS DESTE IPI
            impostoProduto = new ImpostoProduto(chave, totalProdutoIpi);
            //GUARDANDO O OBJETO CRIADO NA LISTA
            listaTotalProdutosIpi.add(impostoProduto); 
        }
        
        return listaTotalProdutosIpi;
    }
    
    
}
