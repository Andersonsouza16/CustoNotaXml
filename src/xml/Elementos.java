/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Administrador
 */
public class Elementos {
    
    Produto produto;
    List <Produto> listaProdutosObj;
    
    //OBJETOS DE IMPOSTO
    Element impostoElement = null;
    Element icmsElement = null;
    Element filhoDet = null;
    Element produtoElement = null;
    
    List <Element> icmsCstElement= null;
    List <Element> itensIcmsStElementlIST = null;
    Element ipiElement = null;
    List <Element> ipiTrib = null;      
    
    //FAZ A SEPARAÇÃO DAS TAGS E ELEMENTOS DO XML
    public List<Element> separarElementos (String cainhoArquiho) {
                        
        Document doc = null;
        SAXBuilder builder = new SAXBuilder();
        try {//arquivo XML
            doc = builder.build(cainhoArquiho);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Element nfeProc_root = doc.getRootElement();
        
        Element nfeElement = null;
        Element infNFeElement = null;
        Element detElement = null;
        Element prodElement = null;
        
        
        List <Element> listaDet = new ArrayList();
        List <Element> listaProd = new ArrayList<>();
        
        
        //PEGANDO ELEMENTO NFe
        for(Element e : nfeProc_root.getChildren()){
            System.out.println("filho de nfeProc -> "+e.getName());
            if(e.getName().equals("NFe")){
                nfeElement = e;
            }             
        }
        
        //PEGANDO ELEMENTO infNFe
        for(Element e : nfeElement.getChildren()){
            System.out.println("filho de NFe -> "+e.getName());
            if(e.getName().equals("infNFe")){
                infNFeElement = e;
            }             
        }
        
        //PEGANDO ELEMENTO det
        for(Element e : infNFeElement.getChildren()){
            if(e.getName().equals("det")){
                detElement = e;
                listaDet.add(e);
            }             
        }
              
        
        //PREECHENDO LISTA COM A TAG PROD   
        
        listaProd = new ArrayList<>();
        int cont = 1;
            for(Element e : listaDet){

                //for(Element t : e.getChildren()){
                    filhoDet = e;
                    listaProd.add(filhoDet);                    
                    

           // }           
            
        }      
          //LISTA DE "det", CONTENDO AS TAGS "prod" E "imposto"    
          return listaProd;
    }
    
    
    // MONTAR OS OBJETOS COM OS VALORES EXTRAIDOS DA NF E COLOCAR NUMA LISTA
    public List<Produto> montarListaProdutos(List <Element> listaDetProd){
        
        listaProdutosObj = new ArrayList<>();
                        
        //IRA PERCORRER UMA LISTA COM TODOS OS ITENS DA NOTA       
        for(Element e : listaDetProd){            
                        
            produto = new Produto();
            for (Element d: e.getChildren()){
                
                 //PEGAR OS ITENS NA TAG "PROD"
                if(d.getName().equals("prod")){
                    
                    for(Element filhoProd: d.getChildren()){
                                              
                        if(filhoProd.getName().equals("cProd")){                        
                            produto.setCodItem(filhoProd.getText());
                        }

                        if(filhoProd.getName().equals("cEAN")){
                            produto.setCodBar(filhoProd.getText());
                        }

                        //descrição
                        if(filhoProd.getName().equals("xProd")){
                            produto.setDescricao(filhoProd.getText());
                        }     

                        if(filhoProd.getName().equals("vProd")){
                            produto.setValorTotalItem(Double.parseDouble(filhoProd.getText()));
                        }                     
                    }                 
                   
                }                
                //ENTRAR NA ARVORE DE IMPOSTO E PEGAR OS VALORES
                if(d.getName().equals("imposto")){
                    
                    for(Element filhoProdImposto: d.getChildren()){
                      
                        if(filhoProdImposto.getName().equals("ICMS")){
                            
                            icmsElement = filhoProdImposto;
                            icmsCstElement = filhoProdImposto.getChildren();

                            //PEGAR OS VALORES GUARDADOS NAS TAGS DENTRO DE CADA TIPO DE ICMS, OU SEJA AS TAGS NETAS DE ICMS
                            for(Element icmsCst: icmsCstElement){
                                itensIcmsStElementlIST = icmsCst.getChildren();
                            }

                            //PERCORRER O NO COM OS VALORES DE ICMS
                            for(Element valorTagIcms: itensIcmsStElementlIST){                               

                                if(valorTagIcms.getName().equals("pICMS")){                                
                                    produto.setPercIcms(Double.parseDouble(valorTagIcms.getText()));                                
                                }     

                                if(valorTagIcms.getName().equals("vICMS")){                                
                                    produto.setValorIcms(Double.parseDouble(valorTagIcms.getText()));                                
                                }    

                            }
                        }

                        //ENTRAR NO NÓ IPI
                        if(filhoProdImposto.getName().equals("IPI")){

                            ipiElement = filhoProdImposto;
                                                       
                            for(Element filhoIPI: ipiElement.getChildren()){
                                
                                if(filhoIPI.getName().equals("IPITrib")){
                                    ipiTrib =  filhoIPI.getChildren();
                                    for(Element ipiTribFilho: ipiTrib){
                                        if(ipiTribFilho.getName().equals("pIPI")){
                                            produto.setIpiAliq(Double.parseDouble(ipiTribFilho.getText()));    
                                        }
                                        if(ipiTribFilho.getName().equals("vIPI")){
                                            produto.setIpiValor(Double.parseDouble(ipiTribFilho.getText()));
                                        }
                                    }
                                    
                                }
                            }
                        }
                    
                    
                    }                            

                }
                
            }
            
            //RETORNA A LISTA DE PRODUTOS JÁ INSTACIADOS
            listaProdutosObj.add(produto);  
            
        }
        
        return listaProdutosObj;
    }

    
}
