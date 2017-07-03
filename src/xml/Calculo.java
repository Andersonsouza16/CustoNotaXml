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
public class Calculo {
    
    //VALORES REIAS
    static Double multiplicadorValorFornec; //multiplicador para aplicar no valor da nf para chegar no preço do fornecedor ex. x2
    static Double vProdNf;
    static Double diferencaAliquota;
    static Double vRateioEntrada;
    static Double vComplemento;
    static double valorForcedor;
    
    //VALORES PERCENTUAIS
    static double freteNf;
    static double frete;
    static double ipi;
    static double icmsInterestadual;
    static double icmsInterno;    
    static double outrasDespesas;
    static double freteCte;
    
    static double percentual;
    
    
    //VARIAVEIS AUXILIARES
    static double parcial1;
    static double parcial2;
    static double parcial3;
    static double parcial4;
    
    ImpostoProduto impostoProduto;
    CalcularImposto calcularImposto;
    
    public void calcular (List <ImpostoProduto> listaTotalProdutosIpi) {
        
        //VALORES FIXOS  
        icmsInterno = 18.0 / 100;   
        freteCte = 0.0;
        freteNf = 0.0;
        frete = 0.0;
        outrasDespesas = 0.0;
        
        
        icmsInterestadual = 4.0 /100;        
        
        multiplicadorValorFornec = 2.0;       
        
        
        for(ImpostoProduto ip: listaTotalProdutosIpi){
            
            ipi = Double.parseDouble(ip.getAliqIpi()) / 100;
            vProdNf = ip.getValorTotalProdutos();
            valorForcedor = vProdNf * multiplicadorValorFornec;
            
            parcial1 = 0;
            parcial2 = 0;
            parcial3 = 0;
            parcial4 = 0;
            diferencaAliquota = 0.0;
            vRateioEntrada = 0.0;
            vComplemento = 0.0;
            
            percentual = 0.0;
            
        
            //INICIO DO CALCULO DE DIFERENÇA DE ALIQUOTA OBS. OS CÓDIGO EX. B3, E3 ETC CORRESPONDEM A CELULAS DA 
            //PLANILHA DE CALCULO MANUAL

                        // B3                B3               E3              B3            H3
            parcial1 = ((valorForcedor + (valorForcedor * frete) + (valorForcedor * outrasDespesas))
                        //C3        D3         C3        F3
                    + (vProdNf * freteNf) + (vProdNf * freteCte));

            //             C3        C3       D3          G3  
            parcial2 = ((vProdNf +(vProdNf * freteNf)) * ipi );

            parcial3 = (parcial1 + parcial2) * icmsInterno;  

            //             C3          C3         D3          C3        F3 
            parcial4 = ((vProdNf) + (vProdNf * freteNf) + (vProdNf * freteCte )) *icmsInterestadual;

            //FIM DO CALCULO DE DIFERENÃ DE ALIQUOTA
            diferencaAliquota = (parcial3 - parcial4) ;

            //CALCULO DO RATEIO
            vRateioEntrada  = (valorForcedor - (vProdNf + (vProdNf * freteNf ) + 
                    ((vProdNf + (vProdNf * freteNf)) * ipi ))) + diferencaAliquota;

            //CALCULO DA PORCENTAGEM DO PREÇO
            percentual = (vRateioEntrada / vProdNf) * 100; 

            //CALCULO COMPLEMENTO DE ALIQUOTA
            vComplemento = valorForcedor - (vProdNf + (vProdNf * freteNf) + 
                    ((vProdNf + (vProdNf * freteNf)) * ipi));

            System.out.println("AlIQUOTA = " + ip.getAliqIpi());
            System.out.println("VALOR DOS PRODUTOS NA NF = " + ip.getValorTotalProdutos());
            System.out.printf("DIFERENÇA DE ALIQUOTA = %.2f \n" , diferencaAliquota);        
            System.out.printf("RATEIO = %.2f \n" , vRateioEntrada);        
            System.out.printf("PERCENTUAL = %.3f \n" , percentual);
            System.out.printf("COMPLEMENTO = %.2f \n" , vComplemento);
        }       
        
    }
    
    
}
