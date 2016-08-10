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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smutua
 */
@Entity
@Table(name = "APPLICATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a")})
public class Application implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "APP_CODE")
    private BigDecimal appCode;
    @Column(name = "APP_CUST_MOBILE_NO")
    private String appCustMobileNo;
    @Basic(optional = false)
    @Column(name = "APP_SHARES_APPLIED")
    private BigInteger appSharesApplied;
    @Basic(optional = false)
    @Column(name = "APP_PAYMENT_MODE")
    private String appPaymentMode;
    @Column(name = "APP_STATUS")
    private BigInteger appStatus;
    @Basic(optional = false)
    @Column(name = "APP_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appCdate;
    @Basic(optional = false)
    @Column(name = "APP_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appMdate;
    @Basic(optional = false)
    @Column(name = "APP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appDate;
    @JoinColumn(name = "USR_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users usrAuthoriser;
    @JoinColumn(name = "APP_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users appInputter;
    @JoinColumn(name = "APP_CUS_PAL_CODE", referencedColumnName = "CUS_PAL_CODE")
    @OneToOne(optional = false)
    private Customers appCusPalCode;
    @JoinColumn(name = "APP_BAT_CODE", referencedColumnName = "BAT_CODE")
    @ManyToOne(optional = false)
    private Batch appBatCode;

    public Application() {
    }

    public Application(BigDecimal appCode) {
        this.appCode = appCode;
    }

    public Application(BigDecimal appCode, BigInteger appSharesApplied, String appPaymentMode, Date appCdate, Date appMdate, Date appDate) {
        this.appCode = appCode;
        this.appSharesApplied = appSharesApplied;
        this.appPaymentMode = appPaymentMode;
        this.appCdate = appCdate;
        this.appMdate = appMdate;
        this.appDate = appDate;
    }

    public BigDecimal getAppCode() {
        return appCode;
    }

    public void setAppCode(BigDecimal appCode) {
        this.appCode = appCode;
    }

    public String getAppCustMobileNo() {
        return appCustMobileNo;
    }

    public void setAppCustMobileNo(String appCustMobileNo) {
        this.appCustMobileNo = appCustMobileNo;
    }

    public BigInteger getAppSharesApplied() {
        return appSharesApplied;
    }

    public void setAppSharesApplied(BigInteger appSharesApplied) {
        this.appSharesApplied = appSharesApplied;
    }

    public String getAppPaymentMode() {
        return appPaymentMode;
    }

    public void setAppPaymentMode(String appPaymentMode) {
        this.appPaymentMode = appPaymentMode;
    }

    public BigInteger getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(BigInteger appStatus) {
        this.appStatus = appStatus;
    }

    public Date getAppCdate() {
        return appCdate;
    }

    public void setAppCdate(Date appCdate) {
        this.appCdate = appCdate;
    }

    public Date getAppMdate() {
        return appMdate;
    }

    public void setAppMdate(Date appMdate) {
        this.appMdate = appMdate;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public Users getUsrAuthoriser() {
        return usrAuthoriser;
    }

    public void setUsrAuthoriser(Users usrAuthoriser) {
        this.usrAuthoriser = usrAuthoriser;
    }

    public Users getAppInputter() {
        return appInputter;
    }

    public void setAppInputter(Users appInputter) {
        this.appInputter = appInputter;
    }

    public Customers getAppCusPalCode() {
        return appCusPalCode;
    }

    public void setAppCusPalCode(Customers appCusPalCode) {
        this.appCusPalCode = appCusPalCode;
    }

    public Batch getAppBatCode() {
        return appBatCode;
    }

    public void setAppBatCode(Batch appBatCode) {
        this.appBatCode = appBatCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appCode != null ? appCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.appCode == null && other.appCode != null) || (this.appCode != null && !this.appCode.equals(other.appCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Application[ appCode=" + appCode + " ]";
    }
    
}
