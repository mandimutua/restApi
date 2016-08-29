package com.ipo.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author smutua
 */
@Entity
@Table(name = "REFUNDS")

public class Refunds implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "REFUND_CODE_SEQ", sequenceName = "REFUND_CODE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REFUND_CODE_SEQ")
    @Basic(optional = false)
    @Column(name = "RFD_CODE")
    private BigDecimal rfdCode;
    @Basic(optional = false)
    @Column(name = "RFD_AMOUNT")
    private double rfdAmount;
    @Column(name = "RFD_MODE")
    private String rfdMode;
    @Column(name = "RFD_CHEQUE_NO")
    private String rfdChequeNo;
    @Column(name = "RFD_BANK_CODE")
    private String rfdBankCode;
    @Column(name = "RFD_BRANCH")
    private String rfdBranch;
    @Column(name = "RFD_ACCOUNT_NO")
    private String rfdAccountNo;
    @Column(name = "RFD_TRANS_REF")
    private String rfdTransRef;
    @Column(name = "RFD_PHONE_NO")
    private String rfdPhoneNo;
    @Basic(optional = false)
    @Column(name = "RFD_STATUS")
    private BigInteger rfdStatus;
    @Basic(optional = false)
    @Column(name = "RFD_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rfdCdate;
    @Column(name = "RFD_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rfdMdate;
    @Basic(optional = false)
    @Column(name = "RFD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rfdDate;
    @JoinColumn(name = "RFD_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users rfdInputter;
    @JoinColumn(name = "RFD_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne
    private Users rfdAuthoriser;
    @JoinColumn(name = "RFD_PAY_CODE", referencedColumnName = "PAY_CODE")
    @ManyToOne(optional = false)
    private Payments rfdPayCode;
    @JoinColumn(name = "RFD_APP_CODE", referencedColumnName = "APP_CODE")
    @ManyToOne(optional = false)
    private Application rfdAppCode;

    public Refunds() {
    }

    public Refunds(BigDecimal rfdCode) {
        this.rfdCode = rfdCode;
    }

    public Refunds(BigDecimal rfdCode, double rfdAmount, BigInteger rfdStatus, Date rfdCdate, Date rfdDate) {
        this.rfdCode = rfdCode;
        this.rfdAmount = rfdAmount;
        this.rfdStatus = rfdStatus;
        this.rfdCdate = rfdCdate;
        this.rfdDate = rfdDate;
    }

    public BigDecimal getRfdCode() {
        return rfdCode;
    }

    public void setRfdCode(BigDecimal rfdCode) {
        this.rfdCode = rfdCode;
    }

    public double getRfdAmount() {
        return rfdAmount;
    }

    public void setRfdAmount(double rfdAmount) {
        this.rfdAmount = rfdAmount;
    }

    public String getRfdMode() {
        return rfdMode;
    }

    public void setRfdMode(String rfdMode) {
        this.rfdMode = rfdMode;
    }

    public String getRfdChequeNo() {
        return rfdChequeNo;
    }

    public void setRfdChequeNo(String rfdChequeNo) {
        this.rfdChequeNo = rfdChequeNo;
    }

    public String getRfdBankCode() {
        return rfdBankCode;
    }

    public void setRfdBankCode(String rfdBankCode) {
        this.rfdBankCode = rfdBankCode;
    }

    public String getRfdBranch() {
        return rfdBranch;
    }

    public void setRfdBranch(String rfdBranch) {
        this.rfdBranch = rfdBranch;
    }

    public String getRfdAccountNo() {
        return rfdAccountNo;
    }

    public void setRfdAccountNo(String rfdAccountNo) {
        this.rfdAccountNo = rfdAccountNo;
    }

    public String getRfdTransRef() {
        return rfdTransRef;
    }

    public void setRfdTransRef(String rfdTransRef) {
        this.rfdTransRef = rfdTransRef;
    }

    public String getRfdPhoneNo() {
        return rfdPhoneNo;
    }

    public void setRfdPhoneNo(String rfdPhoneNo) {
        this.rfdPhoneNo = rfdPhoneNo;
    }

    public BigInteger getRfdStatus() {
        return rfdStatus;
    }

    public void setRfdStatus(BigInteger rfdStatus) {
        this.rfdStatus = rfdStatus;
    }

    public Date getRfdCdate() {
        return rfdCdate;
    }

    public void setRfdCdate(Date rfdCdate) {
        this.rfdCdate = rfdCdate;
    }

    public Date getRfdMdate() {
        return rfdMdate;
    }

    public void setRfdMdate(Date rfdMdate) {
        this.rfdMdate = rfdMdate;
    }

    public Date getRfdDate() {
        return rfdDate;
    }

    public void setRfdDate(Date rfdDate) {
        this.rfdDate = rfdDate;
    }

    public Users getRfdInputter() {
        return rfdInputter;
    }

    public void setRfdInputter(Users rfdInputter) {
        this.rfdInputter = rfdInputter;
    }

    public Users getRfdAuthoriser() {
        return rfdAuthoriser;
    }

    public void setRfdAuthoriser(Users rfdAuthoriser) {
        this.rfdAuthoriser = rfdAuthoriser;
    }

    public Payments getRfdPayCode() {
        return rfdPayCode;
    }

    public void setRfdPayCode(Payments rfdPayCode) {
        this.rfdPayCode = rfdPayCode;
    }

    public Application getRfdAppCode() {
        return rfdAppCode;
    }

    public void setRfdAppCode(Application rfdAppCode) {
        this.rfdAppCode = rfdAppCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rfdCode != null ? rfdCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Refunds)) {
            return false;
        }
        Refunds other = (Refunds) object;
        if ((this.rfdCode == null && other.rfdCode != null) || (this.rfdCode != null && !this.rfdCode.equals(other.rfdCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Refunds[ rfdCode=" + rfdCode + " ]";
    }
    
}
