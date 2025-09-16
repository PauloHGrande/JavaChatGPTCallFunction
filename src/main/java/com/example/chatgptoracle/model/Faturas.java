package com.example.chatgptoracle.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "DA_V_DA_CI_FATURA")
public class Faturas {

    @Id
    @Column(name = "ID_FATURA")
    private Long idFatura;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "TP_DOC")
    private String tpDoc;

    @Column(name = "COD_PROCESSO")
    private String codProcesso;

    @Column(name = "DT_EMISSAO_PROC")
    private LocalDate dtEmissaoProc;

    @Column(name = "DT_EMIS_PROC_YEAR")
    private String dtEmisProcYear;

    @Column(name = "DT_EMIS_PROC_QUARTER")
    private String dtEmisProcQuarter;

    @Column(name = "DT_EMIS_PROC_MONTH")
    private String dtEmisProcMonth;

    @Column(name = "DT_EMBARQUE")
    private LocalDate dtEmbarque;

    @Column(name = "DT_EMBARQUE_YEAR")
    private String dtEmbarqueYear;

    @Column(name = "DT_EMBARQUE_QUARTER")
    private String dtEmbarqueQuarter;

    @Column(name = "DT_EMBARQUE_MONTH")
    private String dtEmbarqueMonth;

    @Column(name = "VIA")
    private String via;

    @Column(name = "COD_FATURA")
    private String codFatura;

    @Column(name = "DT_LANCAMENTO")
    private LocalDate dtLancamento;

    @Column(name = "DT_LANCAMENTO_YEAR")
    private String dtLancamentoYear;

    @Column(name = "DT_LANCAMENTO_QUARTER")
    private String dtLancamentoQuarter;

    @Column(name = "DT_LANCAMENTO_MONTH")
    private String dtLancamentoMonth;

    @Column(name = "DT_EMISSAO_FAT")
    private LocalDate dtEmissaoFat;

    @Column(name = "DT_EMISSAO_FAT_YEAR")
    private String dtEmissaoFatYear;

    @Column(name = "DT_EMISSAO_FAT_QUARTER")
    private String dtEmissaoFatQuarter;

    @Column(name = "DT_EMISSAO_FAT_MONTH")
    private String dtEmissaoFatMonth;

    @Column(name = "TAXA_HISTORICA")
    private BigDecimal taxaHistorica;

    @Column(name = "TERM")
    private String term;

    @Column(name = "MASTER")
    private String master;

    @Column(name = "HOUSE")
    private String house;

    @Column(name = "LI")
    private String li;

    @Column(name = "DT_REGISTRO_DI")
    private LocalDate dtRegistroDI;

    @Column(name = "DT_REGISTRO_DI_YEAR")
    private String dtRegistroDIYear;

    @Column(name = "DT_REGISTRO_DI_QUARTER")
    private String dtRegistroDIQuarter;

    @Column(name = "DT_REGISTRO_DI_MONTH")
    private String dtRegistroDIMonth;

    @Column(name = "NUM_DI")
    private String numDI;

    @Column(name = "TAXA_DI")
    private BigDecimal taxaDI;

    @Column(name = "DT_DESEMB")
    private LocalDate dtDesemb;

    @Column(name = "DT_DESEMB_YEAR")
    private String dtDesembYear;

    @Column(name = "DT_DESEMB_QUARTER")
    private String dtDesembQuarter;

    @Column(name = "DT_DESEMB_MONTH")
    private String dtDesembMonth;

    @Column(name = "IMPORTADOR")
    private String importador;

    @Column(name = "UF")
    private String uf;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "CONDICAO")
    private String condicao;

    @Column(name = "MOEDA")
    private String moeda;

    @Column(name = "TAXA_ENTRADA")
    private BigDecimal taxaEntrada;

    @Column(name = "PAIS")
    private String pais;

    @Column(name = "EXPORTADOR")
    private String exportador;

    @Column(name = "NOME_EXPORTADOR")
    private String nomeExportador;

    @Column(name = "CLIENTE")
    private String cliente;

    @Column(name = "NOME_CLIENTE")
    private String nomeCliente;

    @Column(name = "AGENTE")
    private String agente;

    @Column(name = "NOME_AGENTE")
    private String nomeAgente;

    @Column(name = "DOC_FB60")
    private String docFb60;

    @Column(name = "DT_FB60")
    private LocalDate dtFb60;

    @Column(name = "DT_FB60_YEAR")
    private String dtFb60Year;

    @Column(name = "DT_FB60_QUARTER")
    private String dtFb60Quarter;

    @Column(name = "DT_FB60_MONTH")
    private String dtFb60Month;

    @Column(name = "MSG_ERRO_FB60")
    private String msgErroFb60;

    @Column(name = "DOC_MIRO")
    private String docMiro;

    @Column(name = "DT_MIRO")
    private LocalDate dtMiro;

    @Column(name = "DT_MIRO_YEAR")
    private String dtMiroYear;

    @Column(name = "DT_MIRO_QUARTER")
    private String dtMiroQuarter;

    @Column(name = "DT_MIRO_MONTH")
    private String dtMiroMonth;

    @Column(name = "MSG_ERRO_MIRO")
    private String msgErroMiro;

    public Long getIdFatura() {
        return idFatura;
    }

    public void setIdFatura(Long idFatura) {
        this.idFatura = idFatura;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTpDoc() {
        return tpDoc;
    }

    public void setTpDoc(String tpDoc) {
        this.tpDoc = tpDoc;
    }

    public String getCodProcesso() {
        return codProcesso;
    }

    public void setCodProcesso(String codProcesso) {
        this.codProcesso = codProcesso;
    }

    public LocalDate getDtEmissaoProc() {
        return dtEmissaoProc;
    }

    public void setDtEmissaoProc(LocalDate dtEmissaoProc) {
        this.dtEmissaoProc = dtEmissaoProc;
    }

    public String getDtEmisProcYear() {
        return dtEmisProcYear;
    }

    public void setDtEmisProcYear(String dtEmisProcYear) {
        this.dtEmisProcYear = dtEmisProcYear;
    }

    public String getDtEmisProcQuarter() {
        return dtEmisProcQuarter;
    }

    public void setDtEmisProcQuarter(String dtEmisProcQuarter) {
        this.dtEmisProcQuarter = dtEmisProcQuarter;
    }

    public String getDtEmisProcMonth() {
        return dtEmisProcMonth;
    }

    public void setDtEmisProcMonth(String dtEmisProcMonth) {
        this.dtEmisProcMonth = dtEmisProcMonth;
    }

    public LocalDate getDtEmbarque() {
        return dtEmbarque;
    }

    public void setDtEmbarque(LocalDate dtEmbarque) {
        this.dtEmbarque = dtEmbarque;
    }

    public String getDtEmbarqueYear() {
        return dtEmbarqueYear;
    }

    public void setDtEmbarqueYear(String dtEmbarqueYear) {
        this.dtEmbarqueYear = dtEmbarqueYear;
    }

    public String getDtEmbarqueQuarter() {
        return dtEmbarqueQuarter;
    }

    public void setDtEmbarqueQuarter(String dtEmbarqueQuarter) {
        this.dtEmbarqueQuarter = dtEmbarqueQuarter;
    }

    public String getDtEmbarqueMonth() {
        return dtEmbarqueMonth;
    }

    public void setDtEmbarqueMonth(String dtEmbarqueMonth) {
        this.dtEmbarqueMonth = dtEmbarqueMonth;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCodFatura() {
        return codFatura;
    }

    public void setCodFatura(String codFatura) {
        this.codFatura = codFatura;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public String getDtLancamentoYear() {
        return dtLancamentoYear;
    }

    public void setDtLancamentoYear(String dtLancamentoYear) {
        this.dtLancamentoYear = dtLancamentoYear;
    }

    public String getDtLancamentoQuarter() {
        return dtLancamentoQuarter;
    }

    public void setDtLancamentoQuarter(String dtLancamentoQuarter) {
        this.dtLancamentoQuarter = dtLancamentoQuarter;
    }

    public String getDtLancamentoMonth() {
        return dtLancamentoMonth;
    }

    public void setDtLancamentoMonth(String dtLancamentoMonth) {
        this.dtLancamentoMonth = dtLancamentoMonth;
    }

    public LocalDate getDtEmissaoFat() {
        return dtEmissaoFat;
    }

    public void setDtEmissaoFat(LocalDate dtEmissaoFat) {
        this.dtEmissaoFat = dtEmissaoFat;
    }

    public String getDtEmissaoFatYear() {
        return dtEmissaoFatYear;
    }

    public void setDtEmissaoFatYear(String dtEmissaoFatYear) {
        this.dtEmissaoFatYear = dtEmissaoFatYear;
    }

    public String getDtEmissaoFatQuarter() {
        return dtEmissaoFatQuarter;
    }

    public void setDtEmissaoFatQuarter(String dtEmissaoFatQuarter) {
        this.dtEmissaoFatQuarter = dtEmissaoFatQuarter;
    }

    public String getDtEmissaoFatMonth() {
        return dtEmissaoFatMonth;
    }

    public void setDtEmissaoFatMonth(String dtEmissaoFatMonth) {
        this.dtEmissaoFatMonth = dtEmissaoFatMonth;
    }

    public BigDecimal getTaxaHistorica() {
        return taxaHistorica;
    }

    public void setTaxaHistorica(BigDecimal taxaHistorica) {
        this.taxaHistorica = taxaHistorica;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getLi() {
        return li;
    }

    public void setLi(String li) {
        this.li = li;
    }

    public LocalDate getDtRegistroDI() {
        return dtRegistroDI;
    }

    public void setDtRegistroDI(LocalDate dtRegistroDI) {
        this.dtRegistroDI = dtRegistroDI;
    }

    public String getDtRegistroDIYear() {
        return dtRegistroDIYear;
    }

    public void setDtRegistroDIYear(String dtRegistroDIYear) {
        this.dtRegistroDIYear = dtRegistroDIYear;
    }

    public String getDtRegistroDIQuarter() {
        return dtRegistroDIQuarter;
    }

    public void setDtRegistroDIQuarter(String dtRegistroDIQuarter) {
        this.dtRegistroDIQuarter = dtRegistroDIQuarter;
    }

    public String getDtRegistroDIMonth() {
        return dtRegistroDIMonth;
    }

    public void setDtRegistroDIMonth(String dtRegistroDIMonth) {
        this.dtRegistroDIMonth = dtRegistroDIMonth;
    }

    public String getNumDI() {
        return numDI;
    }

    public void setNumDI(String numDI) {
        this.numDI = numDI;
    }

    public BigDecimal getTaxaDI() {
        return taxaDI;
    }

    public void setTaxaDI(BigDecimal taxaDI) {
        this.taxaDI = taxaDI;
    }

    public LocalDate getDtDesemb() {
        return dtDesemb;
    }

    public void setDtDesemb(LocalDate dtDesemb) {
        this.dtDesemb = dtDesemb;
    }

    public String getDtDesembYear() {
        return dtDesembYear;
    }

    public void setDtDesembYear(String dtDesembYear) {
        this.dtDesembYear = dtDesembYear;
    }

    public String getDtDesembQuarter() {
        return dtDesembQuarter;
    }

    public void setDtDesembQuarter(String dtDesembQuarter) {
        this.dtDesembQuarter = dtDesembQuarter;
    }

    public String getDtDesembMonth() {
        return dtDesembMonth;
    }

    public void setDtDesembMonth(String dtDesembMonth) {
        this.dtDesembMonth = dtDesembMonth;
    }

    public String getImportador() {
        return importador;
    }

    public void setImportador(String importador) {
        this.importador = importador;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public BigDecimal getTaxaEntrada() {
        return taxaEntrada;
    }

    public void setTaxaEntrada(BigDecimal taxaEntrada) {
        this.taxaEntrada = taxaEntrada;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getExportador() {
        return exportador;
    }

    public void setExportador(String exportador) {
        this.exportador = exportador;
    }

    public String getNomeExportador() {
        return nomeExportador;
    }

    public void setNomeExportador(String nomeExportador) {
        this.nomeExportador = nomeExportador;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getNomeAgente() {
        return nomeAgente;
    }

    public void setNomeAgente(String nomeAgente) {
        this.nomeAgente = nomeAgente;
    }

    public String getDocFb60() {
        return docFb60;
    }

    public void setDocFb60(String docFb60) {
        this.docFb60 = docFb60;
    }

    public LocalDate getDtFb60() {
        return dtFb60;
    }

    public void setDtFb60(LocalDate dtFb60) {
        this.dtFb60 = dtFb60;
    }

    public String getDtFb60Year() {
        return dtFb60Year;
    }

    public void setDtFb60Year(String dtFb60Year) {
        this.dtFb60Year = dtFb60Year;
    }

    public String getDtFb60Quarter() {
        return dtFb60Quarter;
    }

    public void setDtFb60Quarter(String dtFb60Quarter) {
        this.dtFb60Quarter = dtFb60Quarter;
    }

    public String getDtFb60Month() {
        return dtFb60Month;
    }

    public void setDtFb60Month(String dtFb60Month) {
        this.dtFb60Month = dtFb60Month;
    }

    public String getMsgErroFb60() {
        return msgErroFb60;
    }

    public void setMsgErroFb60(String msgErroFb60) {
        this.msgErroFb60 = msgErroFb60;
    }

    public String getDocMiro() {
        return docMiro;
    }

    public void setDocMiro(String docMiro) {
        this.docMiro = docMiro;
    }

    public LocalDate getDtMiro() {
        return dtMiro;
    }

    public void setDtMiro(LocalDate dtMiro) {
        this.dtMiro = dtMiro;
    }

    public String getDtMiroYear() {
        return dtMiroYear;
    }

    public void setDtMiroYear(String dtMiroYear) {
        this.dtMiroYear = dtMiroYear;
    }

    public String getDtMiroQuarter() {
        return dtMiroQuarter;
    }

    public void setDtMiroQuarter(String dtMiroQuarter) {
        this.dtMiroQuarter = dtMiroQuarter;
    }

    public String getDtMiroMonth() {
        return dtMiroMonth;
    }

    public void setDtMiroMonth(String dtMiroMonth) {
        this.dtMiroMonth = dtMiroMonth;
    }

    public String getMsgErroMiro() {
        return msgErroMiro;
    }

    public void setMsgErroMiro(String msgErroMiro) {
        this.msgErroMiro = msgErroMiro;
    }
}
