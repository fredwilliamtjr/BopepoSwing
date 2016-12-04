/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.bopeposwing.util.bopepo;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;


/**
 *
 * @author Junior
 */
public class EmitirBoleto {

    private static void mostreBoletoNaTela(File arquivoBoleto) {

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

        try {
            desktop.open(arquivoBoleto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Bradeco(String nomeSacado, String documentoSacado, String logradouroSacado, String bairroSacado, 
            String cepSacado, String cidadeSacado, String ufSacado, Date data, String valor) {
        Cedente cedente = new Cedente("PROJETO JRimum", "00.000.208/0001-00");

        Sacado sacado = new Sacado(nomeSacado, documentoSacado);
        // Informando o endereço do sacado.
        Endereco enderecoSac = new Endereco();
        enderecoSac.setUF(UnidadeFederativa.valueOfSigla(ufSacado));
        enderecoSac.setLocalidade(cidadeSacado);
        enderecoSac.setCep(new CEP(cepSacado));
        enderecoSac.setBairro(bairroSacado);
        enderecoSac.setLogradouro(logradouroSacado);
        sacado.addEndereco(enderecoSac);

        // Informando dados sobre a conta bancária do título.
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setAgencia(new Agencia(1234, "1"));

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento("123456");
        titulo.setNossoNumero("99345678912");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(new BigDecimal(valor));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(data);
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setAceite(Aceite.A);
        titulo.setDesconto(new BigDecimal(0.05));
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.ZERO);

        Boleto boleto = new Boleto(titulo);
        boleto.setLocalPagamento("Pagável preferencialmente na Rede X ou em qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor cobrado não é o esperado, aproveite o DESCONTÃO!");
        boleto.setInstrucao1("PARA PAGAMENTO 1 até Hoje não cobrar nada!");
        boleto.setInstrucao2("PARA PAGAMENTO 2 até Amanhã Não cobre!");
        boleto.setInstrucao3("PARA PAGAMENTO 3 até Depois de amanhã, OK, não cobre.");
        boleto.setInstrucao4("PARA PAGAMENTO 4 até 04/xx/xxxx de 4 dias atrás COBRAR O VALOR DE: R$ 01,00");
        boleto.setInstrucao5("PARA PAGAMENTO 5 até 05/xx/xxxx COBRAR O VALOR DE: R$ 02,00");
        boleto.setInstrucao6("PARA PAGAMENTO 6 até 06/xx/xxxx COBRAR O VALOR DE: R$ 03,00");
        boleto.setInstrucao7("PARA PAGAMENTO 7 até xx/xx/xxxx COBRAR O VALOR QUE VOCÊ QUISER!");
        boleto.setInstrucao8("APÓS o Vencimento, Pagável Somente na Rede X.");

        BoletoViewer boletoViewer = new BoletoViewer(boleto);
        File arquivoPdf = boletoViewer.getPdfAsFile("Bradesco.pdf");
        mostreBoletoNaTela(arquivoPdf);
    }
    
    public void Itau(String nomeSacado, String documentoSacado, String logradouroSacado, String bairroSacado, 
            String cepSacado, String cidadeSacado, String ufSacado, Date data, String valor) {
        Cedente cedente = new Cedente("PROJETO JRimum", "00.000.208/0001-00");

        Sacado sacado = new Sacado(nomeSacado, documentoSacado);
        // Informando o endereço do sacado.
        Endereco enderecoSac = new Endereco();
        enderecoSac.setUF(UnidadeFederativa.valueOfSigla(ufSacado));
        enderecoSac.setLocalidade(cidadeSacado);
        enderecoSac.setCep(new CEP(cepSacado));
        enderecoSac.setBairro(bairroSacado);
        enderecoSac.setLogradouro(logradouroSacado);
        sacado.addEndereco(enderecoSac);

        // Informando dados sobre a conta bancária do título.
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_ITAU.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(21046, "9"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setAgencia(new Agencia(0041));

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento("123456");
        titulo.setNossoNumero("99345678");
        titulo.setDigitoDoNossoNumero("1");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(new BigDecimal(valor));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(data);
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setAceite(Aceite.A);
        titulo.setDesconto(new BigDecimal(0.05));
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.ZERO);

        Boleto boleto = new Boleto(titulo);
        boleto.setLocalPagamento("Pagável preferencialmente na Rede X ou em qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor cobrado não é o esperado, aproveite o DESCONTÃO!");
        boleto.setInstrucao1("PARA PAGAMENTO 1 até Hoje não cobrar nada!");
        boleto.setInstrucao2("PARA PAGAMENTO 2 até Amanhã Não cobre!");
        boleto.setInstrucao3("PARA PAGAMENTO 3 até Depois de amanhã, OK, não cobre.");
        boleto.setInstrucao4("PARA PAGAMENTO 4 até 04/xx/xxxx de 4 dias atrás COBRAR O VALOR DE: R$ 01,00");
        boleto.setInstrucao5("PARA PAGAMENTO 5 até 05/xx/xxxx COBRAR O VALOR DE: R$ 02,00");
        boleto.setInstrucao6("PARA PAGAMENTO 6 até 06/xx/xxxx COBRAR O VALOR DE: R$ 03,00");
        boleto.setInstrucao7("PARA PAGAMENTO 7 até xx/xx/xxxx COBRAR O VALOR QUE VOCÊ QUISER!");
        boleto.setInstrucao8("APÓS o Vencimento, Pagável Somente na Rede X.");

        BoletoViewer boletoViewer = new BoletoViewer(boleto);
        File arquivoPdf = boletoViewer.getPdfAsFile("Itau.pdf");
        mostreBoletoNaTela(arquivoPdf);
    }
    
    public void BancoDoBrasil(String nomeSacado, String documentoSacado, String logradouroSacado, String bairroSacado, 
            String cepSacado, String cidadeSacado, String ufSacado, Date data, String valor) {
        Cedente cedente = new Cedente("PROJETO JRimum", "00.000.208/0001-00");

        Sacado sacado = new Sacado(nomeSacado, documentoSacado);
        // Informando o endereço do sacado.
        Endereco enderecoSac = new Endereco();
        enderecoSac.setUF(UnidadeFederativa.valueOfSigla(ufSacado));
        enderecoSac.setLocalidade(cidadeSacado);
        enderecoSac.setCep(new CEP(cepSacado));
        enderecoSac.setBairro(bairroSacado);
        enderecoSac.setLogradouro(logradouroSacado);
        sacado.addEndereco(enderecoSac);

        // Informando dados sobre a conta bancária do título.
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_DO_BRASIL.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setAgencia(new Agencia(1234, "1"));

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento("123456");
        titulo.setNossoNumero("99345678912");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(new BigDecimal(valor));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(data);
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setAceite(Aceite.A);
        titulo.setDesconto(new BigDecimal(0.05));
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.ZERO);

        Boleto boleto = new Boleto(titulo);
        boleto.setLocalPagamento("Pagável preferencialmente na Rede X ou em qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor cobrado não é o esperado, aproveite o DESCONTÃO!");
        boleto.setInstrucao1("PARA PAGAMENTO 1 até Hoje não cobrar nada!");
        boleto.setInstrucao2("PARA PAGAMENTO 2 até Amanhã Não cobre!");
        boleto.setInstrucao3("PARA PAGAMENTO 3 até Depois de amanhã, OK, não cobre.");
        boleto.setInstrucao4("PARA PAGAMENTO 4 até 04/xx/xxxx de 4 dias atrás COBRAR O VALOR DE: R$ 01,00");
        boleto.setInstrucao5("PARA PAGAMENTO 5 até 05/xx/xxxx COBRAR O VALOR DE: R$ 02,00");
        boleto.setInstrucao6("PARA PAGAMENTO 6 até 06/xx/xxxx COBRAR O VALOR DE: R$ 03,00");
        boleto.setInstrucao7("PARA PAGAMENTO 7 até xx/xx/xxxx COBRAR O VALOR QUE VOCÊ QUISER!");
        boleto.setInstrucao8("APÓS o Vencimento, Pagável Somente na Rede X.");

        BoletoViewer boletoViewer = new BoletoViewer(boleto);
        File arquivoPdf = boletoViewer.getPdfAsFile("BancoDoBrasil.pdf");
        mostreBoletoNaTela(arquivoPdf);
    }

}
