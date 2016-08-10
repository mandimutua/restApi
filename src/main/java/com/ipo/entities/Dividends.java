/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smutua
 */
@Entity
@Table(name = "DIVIDENDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dividends.findAll", query = "SELECT d FROM Dividends d")})
public class Dividends implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "DIV_CODE")
    private BigDecimal divCode;
    @Column(name = "DIV_CUS_PAL_NO")
    private BigInteger divCusPalNo;
    @Column(name = "DIV_GROSS_DIVIDENDS")
    private Double divGrossDividends;
    @Column(name = "DIV_TAX")
    private Double divTax;
    @Column(name = "DIV_NET_DIVIDEND")
    private Double divNetDividend;
    @Column(name = "DIV_AMOUNT_APPLIED")
    private Double divAmountApplied;
    @Column(name = "DIV_SCRIPT_SHARES")
    private Double divScriptShares;
    @Column(name = "DIV_SCRIPT_DIVIDENDS")
    private Double divScriptDividends;
    @Column(name = "DIV_MODE")
    private String divMode;
    @Column(name = "DIV_STATUS")
    private BigInteger divStatus;
    @Basic(optional = false)
    @Column(name = "DIV_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date divCdate;
    @Basic(optional = false)
    @Column(name = "DIV_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date divMdate;
    @Basic(optional = false)
    @Column(name = "DIV_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date divDate;
    @OneToMany(mappedBy = "payDivCode")
    private Collection<Payments> paymentsCollection;
    @JoinColumn(name = "DIV_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users divInputter;
    @JoinColumn(name = "DIV_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users divAuthoriser;

    public Dividends() {
    }

    public Dividends(BigDecimal divCode) {
        this.divCode = divCode;
    }

    public Dividends(BigDecimal divCode, Date divCdate, Date divMdate, Date divDate) {
        this.divCode = divCode;
        this.divCdate = divCdate;
        this.divMdate = divMdate;
        this.divDate = divDate;
    }

    public BigDecimal getDivCode() {
        return divCode;
    }

    public void setDivCode(BigDecimal divCode) {
        this.divCode = divCode;
    }

    public BigInteger getDivCusPalNo() {
        return divCusPalNo;
    }

    public void setDivCusPalNo(BigInteger divCusPalNo) {
        this.divCusPalNo = divCusPalNo;
    }

    public Double getDivGrossDividends() {
        return divGrossDividends;
    }

    public void setDivGrossDividends(Double divGrossDividends) {
        this.divGrossDividends = divGrossDividends;
    }

    public Double getDivTax() {
        return divTax;
    }

    public void setDivTax(Double divTax) {
        this.divTax = divTax;
    }

    public Double getDivNetDividend() {
        return divNetDividend;
    }

    public void setDivNetDividend(Double divNetDividend) {
        this.divNetDividend = divNetDividend;
    }

    public Double getDivAmountApplied() {
        return divAmountApplied;
    }

    public void setDivAmountApplied(Double divAmountApplied) {
        this.divAmountApplied = divAmountApplied;
    }

    public Double getDivScriptShares() {
        return divScriptShares;
    }

    public void setDivScriptShares(Double divScriptShares) {
        this.divScriptShares = divScriptShares;
    }

    public Double getDivScriptDividends() {
        return divScriptDividends;
    }

    public void setDivScriptDividends(Double divScriptDividends) {
        this.divScriptDividends = divScriptDividends;
    }

    public String getDivMode() {
        return divMode;
    }

    public void setDivMode(String divMode) {
        this.divMode = divMode;
    }

    public BigInteger getDivStatus() {
        return divStatus;
    }

    public void setDivStatus(BigInteger divStatus) {
        this.divStatus = divStatus;
    }

    public Date getDivCdate() {
        return divCdate;
    }

    public void setDivCdate(Date divCdate) {
        this.divCdate = divCdate;
    }

    public Date getDivMdate() {
        return divMdate;
    }

    public void setDivMdate(Date divMdate) {
        this.divMdate = divMdate;
    }

    public Date getDivDate() {
        return divDate;
    }

    public void setDivDate(Date divDate) {
        this.divDate = divDate;
    }

    @XmlTransient
    public Collection<Payments> getPaymentsCollection() {
        return paymentsCollection;
    }

    public void setPaymentsCollection(Collection<Payments> paymentsCollection) {
        this.paymentsCollection = paymentsCollection;
    }

    public Users getDivInputter() {
        return divInputter;
    }

    public void setDivInputter(Users divInputter) {
        this.divInputter = divInputter;
    }

    public Users getDivAuthoriser() {
        return divAuthoriser;
    }

    public void setDivAuthoriser(Users divAuthoriser) {
        this.divAuthoriser = divAuthoriser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (divCode != null ? divCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dividends)) {
            return false;
        }
        Dividends other = (Dividends) object;
        if ((this.divCode == null && other.divCode != null) || (this.divCode != null && !this.divCode.equals(other.divCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dividends[ divCode=" + divCode + " ]";
    }
    
}
