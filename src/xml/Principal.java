/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jdom2.Element;

/**
 *
 * @author Administrador
 */
public class Principal {
    
    //OBJETO DA CLASSE QUE MANIPULA O ARQUIVO XML
    Elementos elementos;
    String caminhoArquivo;  
    Calculo calculo;
       
    List<Element> listaElementosNFe; //LISTA QUE RECEBERÁ OS ELEMENTOS DO ARQUIVO XML
    List <Produto> listaProdutosObj; //LISTA QUE GUARDARÁ OS PRODUTOS RETIRADOS DO ARQUIVO XML, JÁ EM FORMA DE OBJETOS
    List <ImpostoProduto> listaTotalProdutosIpi;
    
    /*MAPA QUE RECEBE COMO CHAVE A ALIQUOTA DE IPI (CONVERTIDA PRA STRING) E COMO VALOR PRA CADA CHAVE
            A LISTA DE PRODUTOS COM IPI IGUAIS*/
    Map <String,List<Produto>> mapaProdutoIpi;
    
    SepararPorImposto separar = new SepararPorImposto();
    
    //CONSTRUTOR QUE RECEBE O CAMINHO DO ARQUIVO OBTIDO DA TELA PRINCIPAL

    public Principal(String caminhoArquivoRecebido) {
        this.caminhoArquivo = caminhoArquivoRecebido;
    }    
    
    public void main() {
        
        //caminhoArquivo = "C:\\Users\\Administrador\\Desktop\\xml\\teste.xml";
        
        elementos = new Elementos();
        calculo = new Calculo();
        
        //LISTA QUE RECEBERÁ OS ELEMENTOS DO ARQUIVO XML
        listaElementosNFe = new ArrayList<>();
        //PREECHENDO A LISTA DE ELEMENTOS, PASSA O CAMINHO DO ARQUIVO QUE SERÁ LIDO
        listaElementosNFe = elementos.separarElementos(caminhoArquivo);
        
        //AQUI É PASSADA A LISTA DE ELEMENTOS DA XML PARA O MÉTODO QUE PERCORRE ESSES ELEMENTOS E EXTRAI OS PRODUTOS
        listaProdutosObj = elementos.montarListaProdutos(listaElementosNFe);
        
        //SEPARA OS OS PRODUTOS POR IPI E GUARDA EM UM MAPA CHAVE (ALIQUOTA) VALOR (LISTA DE PRODUTOS)
        mapaProdutoIpi = separar.separarProdutosIpi(listaProdutosObj);
        
        //PERCORRE O MAPA E RETORNA A LISTA DE ALIQUOTAS COM O VALOR TOTAL DE PRODUTOS DE CADA ALIQUOTA
        listaTotalProdutosIpi = separar.buscarTotalProdutosIpi(mapaProdutoIpi);
        
        //REALIZA O CALCULO DOS TRIBUTOS
        calculo.calcular(listaTotalProdutosIpi);
        
        
        
    }    
    
}
