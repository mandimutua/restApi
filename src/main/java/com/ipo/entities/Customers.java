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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smutua
 */
@Entity
@Table(name = "CUSTOMERS")
@XmlRootElement

public class Customers implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   
    @Id
    @SequenceGenerator(name = "CUSTOMER_CODE_SEQ", sequenceName = "CUSTOMER_CODE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_CODE_SEQ")
    @Column(name = "CUS_PAL_CODE")
    private BigDecimal cusPalCode;
    @Basic(optional = false)
    
    @Column(name = "CUS_SHARES_AC_NO")
    private String cusSharesAcNo;
    @Basic(optional = false)
    @Column(name = "CUS_NAME")
    private String cusName;
    @Column(name = "CUS_ADDRESS")
    private String cusAddress;
    @Column(name = "CUS_TOWN")
    private String cusTown;
    @Column(name = "CUS_COUNTRY")
    private String cusCountry;
    @Column(name = "CUS_SHAREHOLDING")
    private BigInteger cusShareholding;
    @Column(name = "CUS_PHONE")
    private String cusPhone;
    @Column(name = "CUS_EMAIL")
    private String cusEmail;
    @Column(name = "CUS_STATUS")
    private BigInteger cusStatus;
    @Basic(optional = false)
    @Column(name = "CUS_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cusCdate;
    @Basic(optional = false)
    @Column(name = "CUS_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cusMdate;
    @Basic(optional = false)
    @Column(name = "CUS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cusDate;
    @JoinColumn(name = "CUS_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users cusInputter;
    @JoinColumn(name = "CUS_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users cusAuthoriser;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "appCusPalCode")
    private Application application;

    public Customers() {
    }

    public Customers(BigDecimal cusPalCode) {
        this.cusPalCode = cusPalCode;
    }

    public Customers(BigDecimal cusPalCode, String cusSharesAcNo, String cusName, Date cusCdate, Date cusMdate, Date cusDate) {
        this.cusPalCode = cusPalCode;
        this.cusSharesAcNo = cusSharesAcNo;
        this.cusName = cusName;
        this.cusCdate = cusCdate;
        this.cusMdate = cusMdate;
        this.cusDate = cusDate;
    }

    public BigDecimal getCusPalCode() {
        return cusPalCode;
    }

    public void setCusPalCode(BigDecimal cusPalCode) {
        this.cusPalCode = cusPalCode;
    }

    public String getCusSharesAcNo() {
        return cusSharesAcNo;
    }

    public void setCusSharesAcNo(String cusSharesAcNo) {
        this.cusSharesAcNo = cusSharesAcNo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusTown() {
        return cusTown;
    }

    public void setCusTown(String cusTown) {
        this.cusTown = cusTown;
    }

    public String getCusCountry() {
        return cusCountry;
    }

    public void setCusCountry(String cusCountry) {
        this.cusCountry = cusCountry;
    }

    public BigInteger getCusShareholding() {
        return cusShareholding;
    }

    public void setCusShareholding(BigInteger cusShareholding) {
        this.cusShareholding = cusShareholding;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public BigInteger getCusStatus() {
        return cusStatus;
    }

    public void setCusStatus(BigInteger cusStatus) {
        this.cusStatus = cusStatus;
    }

    public Date getCusCdate() {
        return cusCdate;
    }

    public void setCusCdate(Date cusCdate) {
        this.cusCdate = cusCdate;
    }

    public Date getCusMdate() {
        return cusMdate;
    }

    public void setCusMdate(Date cusMdate) {
        this.cusMdate = cusMdate;
    }

    public Date getCusDate() {
        return cusDate;
    }

    public void setCusDate(Date cusDate) {
        this.cusDate = cusDate;
    }

    public Users getCusInputter() {
        return cusInputter;
    }

    public void setCusInputter(Users cusInputter) {
        this.cusInputter = cusInputter;
    }

    public Users getCusAuthoriser() {
        return cusAuthoriser;
    }

    public void setCusAuthoriser(Users cusAuthoriser) {
        this.cusAuthoriser = cusAuthoriser;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cusPalCode != null ? cusPalCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        if ((this.cusPalCode == null && other.cusPalCode != null) || (this.cusPalCode != null && !this.cusPalCode.equals(other.cusPalCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Customers[ cusPalCode=" + cusPalCode + " ]";
    }
    
}
