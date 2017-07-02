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
public class Calculo {
    
    //VALORES REIAS
    static Double vProdFornecedor;
    static Double vProdNf;
    static Double diferencaAliquota;
    static Double vRateioEntrada;
    static Double vComplemento;
    
    //VALORES PERCENTUAIS
    static Double freteNf;
    static Double frete;
    static Double ipi;
    static Double icmsInterestadual;
    static Double icmsInterno;    
    static Double outrasDespesas;
    static Double freteCte;
    
    static Double percentual;
    
    
    //VARIAVEIS AUXILIARES
    static double parcial1;
    static double parcial2;
    static double parcial3;
    static double parcial4;
    
    ImpostoProduto impostoProduto;
    CalcularImposto calcularImposto;
    
    public static void main(String[] args) {
                       
        vProdFornecedor = 1574.40;
            vProdNf = 629.76;
        outrasDespesas = 0.0;
        ipi = 5.0 / 100;
        
        freteCte = 0.0;
        freteNf = 0.0;
        frete = 0.0;
        icmsInterestadual = 4.0 /100;
        icmsInterno = 18.0 / 100;        
        percentual = 0.0;
        
        //INICIO DO CALCULO DE DIFERENÇA DE ALIQUOTA OBS. OS CÓDIGO EX. B3, E3 ETC CORRESPONDEM A CELULAS DA 
        //PLANILHA DE CALCULO MANUAL
        
                    // B3                B3               E3              B3            H3
        parcial1 = ((vProdFornecedor + (vProdFornecedor * frete) + (vProdFornecedor * outrasDespesas))
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
        vRateioEntrada  = (vProdFornecedor - (vProdNf + (vProdNf * freteNf ) + 
                ((vProdNf + (vProdNf * freteNf)) * ipi ))) + diferencaAliquota;
        
        //CALCULO DA PORCENTAGEM DO PREÇO
        percentual = (vRateioEntrada / vProdNf) * 100; 
        
        //CALCULO COMPLEMENTO DE ALIQUOTA
        vComplemento = vProdFornecedor - (vProdNf + (vProdNf * freteNf) + 
                ((vProdNf + (vProdNf * freteNf)) * ipi));
        
        
        System.out.printf("DIFERENÇA DE ALIQUOTA = %.2f \n" , diferencaAliquota);        
        System.out.printf("RATEIO = %.2f \n" , vRateioEntrada);        
        System.out.printf("PERCENTUAL = %.3f \n" , percentual);
        System.out.printf("COMPLEMENTO = %.2f \n" , vComplemento);
        
    }
    
    
}
