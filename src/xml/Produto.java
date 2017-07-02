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
public class Produto {
    
    private String codItem;
    private String descricao;        
    private String codBar;
    private double valorTotalItem;
    
    private double percIcms;
    private double valorIcms;
    private double ipiAliq;
    private double ipiValor;
    

    public double getValorTotalItem() {
        return valorTotalItem;
    }

    public void setValorTotalItem(double valorTotalItem) {
        this.valorTotalItem = valorTotalItem;
    }

    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getIpiAliq() {
        return ipiAliq;
    }

    public void setIpiAliq(double ipiAliq) {
        this.ipiAliq = ipiAliq;
    }

    public String getCodBar() {
        return codBar;
    }

    public void setCodBar(String codBar) {
        this.codBar = codBar;
    }

    public double getPercIcms() {
        return percIcms;
    }

    public void setPercIcms(double percIcms) {
        this.percIcms = percIcms;
    }

    public double getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(double valorIcms) {
        this.valorIcms = valorIcms;
    }

    public double getIpiValor() {
        return ipiValor;
    }

    public void setIpiValor(double ipiValor) {
        this.ipiValor = ipiValor;
    }
    
    
    
}
