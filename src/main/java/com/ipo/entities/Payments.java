/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smutua
 */
@Entity
@Table(name = "PAYMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payments.findAll", query = "SELECT p FROM Payments p")})
public class Payments implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PAY_CODE")
    private BigDecimal payCode;
    @Basic(optional = false)
    @Column(name = "PAY_APP_CUS_PAL_CODE")
    private BigInteger payAppCusPalCode;
    @Column(name = "PAY_TYPE")
    private String payType;
    @Column(name = "PAY_CHEQUE_NO")
    private String payChequeNo;
    @Column(name = "PAY_BANK_CODE")
    private String payBankCode;
    @Column(name = "PAY_BRANCH")
    private String payBranch;
    @Basic(optional = false)
    @Column(name = "PAY_ACCOUNT_NO")
    private String payAccountNo;
    @Column(name = "PAY_TRANS_REF")
    private String payTransRef;
    @Column(name = "PAY_PHONE_NO")
    private String payPhoneNo;
    @Column(name = "PAY_TERMINAL_ID")
    private String payTerminalId;
    @Column(name = "PAY_ACCOUNT_NAME")
    private String payAccountName;
    @Basic(optional = false)
    @Column(name = "PAY_AMOUNT")
    private double payAmount;
    @Column(name = "PAY_STATUS")
    private BigInteger payStatus;
    @Basic(optional = false)
    @Column(name = "PAY_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payCdate;
    @Basic(optional = false)
    @Column(name = "PAY_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payMdate;
    @Basic(optional = false)
    @Column(name = "PAY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payDate;
    @JoinColumn(name = "PAY_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users payInputter;
    @JoinColumn(name = "PAY_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users payAuthoriser;
    @JoinColumn(name = "PAY_DIV_CODE", referencedColumnName = "DIV_CODE")
    @ManyToOne
    private Dividends payDivCode;

    public Payments() {
    }

    public Payments(BigDecimal payCode) {
        this.payCode = payCode;
    }

    public Payments(BigDecimal payCode, BigInteger payAppCusPalCode, String payAccountNo, double payAmount, Date payCdate, Date payMdate, Date payDate) {
        this.payCode = payCode;
        this.payAppCusPalCode = payAppCusPalCode;
        this.payAccountNo = payAccountNo;
        this.payAmount = payAmount;
        this.payCdate = payCdate;
        this.payMdate = payMdate;
        this.payDate = payDate;
    }

    public BigDecimal getPayCode() {
        return payCode;
    }

    public void setPayCode(BigDecimal payCode) {
        this.payCode = payCode;
    }

    public BigInteger getPayAppCusPalCode() {
        return payAppCusPalCode;
    }

    public void setPayAppCusPalCode(BigInteger payAppCusPalCode) {
        this.payAppCusPalCode = payAppCusPalCode;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayChequeNo() {
        return payChequeNo;
    }

    public void setPayChequeNo(String payChequeNo) {
        this.payChequeNo = payChequeNo;
    }

    public String getPayBankCode() {
        return payBankCode;
    }

    public void setPayBankCode(String payBankCode) {
        this.payBankCode = payBankCode;
    }

    public String getPayBranch() {
        return payBranch;
    }

    public void setPayBranch(String payBranch) {
        this.payBranch = payBranch;
    }

    public String getPayAccountNo() {
        return payAccountNo;
    }

    public void setPayAccountNo(String payAccountNo) {
        this.payAccountNo = payAccountNo;
    }

    public String getPayTransRef() {
        return payTransRef;
    }

    public void setPayTransRef(String payTransRef) {
        this.payTransRef = payTransRef;
    }

    public String getPayPhoneNo() {
        return payPhoneNo;
    }

    public void setPayPhoneNo(String payPhoneNo) {
        this.payPhoneNo = payPhoneNo;
    }

    public String getPayTerminalId() {
        return payTerminalId;
    }

    public void setPayTerminalId(String payTerminalId) {
        this.payTerminalId = payTerminalId;
    }

    public String getPayAccountName() {
        return payAccountName;
    }

    public void setPayAccountName(String payAccountName) {
        this.payAccountName = payAccountName;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public BigInteger getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(BigInteger payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPayCdate() {
        return payCdate;
    }

    public void setPayCdate(Date payCdate) {
        this.payCdate = payCdate;
    }

    public Date getPayMdate() {
        return payMdate;
    }

    public void setPayMdate(Date payMdate) {
        this.payMdate = payMdate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Users getPayInputter() {
        return payInputter;
    }

    public void setPayInputter(Users payInputter) {
        this.payInputter = payInputter;
    }

    public Users getPayAuthoriser() {
        return payAuthoriser;
    }

    public void setPayAuthoriser(Users payAuthoriser) {
        this.payAuthoriser = payAuthoriser;
    }

    public Dividends getPayDivCode() {
        return payDivCode;
    }

    public void setPayDivCode(Dividends payDivCode) {
        this.payDivCode = payDivCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payCode != null ? payCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payments)) {
            return false;
        }
        Payments other = (Payments) object;
        if ((this.payCode == null && other.payCode != null) || (this.payCode != null && !this.payCode.equals(other.payCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Payments[ payCode=" + payCode + " ]";
    }
    
}
