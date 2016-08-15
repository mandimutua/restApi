/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



/**
 *
 * @author munenedk-pc
 */
@Entity
@Table(name = "USERS")

public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "USER_ROLE_CODE_SEQ", sequenceName = "USER_ROLE_CODE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ROLE_CODE_SEQ")
    @Basic(optional = false)
    
  
    @Column(name = "USR_CODE")
    private BigDecimal usrCode;
    @Basic(optional = false)
    
    @Column(name = "USR_NAME")
    private String usrName;
    @Basic(optional = false)
    
    @Column(name = "USR_EMAIL")
    private String usrEmail;
    @Basic(optional = false)
    
    @Column(name = "USR_PASS")
    private String usrPass;
    
    @Column(name = "USR_LAST_LOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrLastLogin;
    
    @Column(name = "USR_LAST_PASS_CHANGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrLastPassChange;
    
    @Column(name = "USR_STATUS")
    private BigInteger usrStatus;
    @Basic(optional = false)
    
    @Column(name = "USR_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrCdate;
    @Basic(optional = false)
    
    @Column(name = "USR_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrMdate;
    @Basic(optional = false)
    
    @Column(name = "USR_INPUTTER")
    private BigInteger usrInputter;
    @Basic(optional = false)
    
    @Column(name = "USR_AUTHORISER")
    private BigInteger usrAuthoriser;
    @Basic(optional = false)
    
    @Column(name = "USR_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrDate;
    
    @Column(name = "USR_AUTH_SALT")
    private String usrAuthSalt;
    @Basic(optional = false)
    
    @Column(name = "SESSION_EXPIRY")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar sessionExpiry;
    

    
    
    
    @JoinColumn(name = "USR_BRK_CODE", referencedColumnName = "BRK_CODE")
    @ManyToOne( fetch = FetchType.EAGER)
    @JsonManagedReference
    private Brokers usrBrkCode;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brkAuthoriser")
  //  @JsonManagedReference
    @JsonIgnore
    private Collection<Brokers> brokersCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brkInputter")
    @JsonIgnore
    private Collection<Brokers> brokersCollection1;

    public Users() {
    }

    public Users(BigDecimal usrCode) {
        this.usrCode = usrCode;
    }

    public Users(BigDecimal usrCode, String usrName, String usrEmail, String usrPass, Date usrCdate, Date usrMdate, BigInteger usrInputter, BigInteger usrAuthoriser, Date usrDate, Calendar sessionExpiry) {
        this.usrCode = usrCode;
        this.usrName = usrName;
        this.usrEmail = usrEmail;
        this.usrPass = usrPass;
        this.usrCdate = usrCdate;
        this.usrMdate = usrMdate;
        this.usrInputter = usrInputter;
        this.usrAuthoriser = usrAuthoriser;
        this.usrDate = usrDate;
        this.sessionExpiry = sessionExpiry;
    }

    public BigDecimal getUsrCode() {
        return usrCode;
    }

    public void setUsrCode(BigDecimal usrCode) {
        this.usrCode = usrCode;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrPass() {
        return usrPass;
    }

    public void setUsrPass(String usrPass) {
        this.usrPass = usrPass;
    }

    public Date getUsrLastLogin() {
        return usrLastLogin;
    }

    public void setUsrLastLogin(Date usrLastLogin) {
        this.usrLastLogin = usrLastLogin;
    }

    public Date getUsrLastPassChange() {
        return usrLastPassChange;
    }

    public void setUsrLastPassChange(Date usrLastPassChange) {
        this.usrLastPassChange = usrLastPassChange;
    }

    public BigInteger getUsrStatus() {
        return usrStatus;
    }

    public void setUsrStatus(BigInteger usrStatus) {
        this.usrStatus = usrStatus;
    }

    public Date getUsrCdate() {
        return usrCdate;
    }

    public void setUsrCdate(Date usrCdate) {
        this.usrCdate = usrCdate;
    }

    public Date getUsrMdate() {
        return usrMdate;
    }

    public void setUsrMdate(Date usrMdate) {
        this.usrMdate = usrMdate;
    }

    public BigInteger getUsrInputter() {
        return usrInputter;
    }

    public void setUsrInputter(BigInteger usrInputter) {
        this.usrInputter = usrInputter;
    }

    public BigInteger getUsrAuthoriser() {
        return usrAuthoriser;
    }

    public void setUsrAuthoriser(BigInteger usrAuthoriser) {
        this.usrAuthoriser = usrAuthoriser;
    }

    public Date getUsrDate() {
        return usrDate;
    }

    public void setUsrDate(Date usrDate) {
        this.usrDate = usrDate;
    }

    public String getUsrAuthSalt() {
        return usrAuthSalt;
    }

    public void setUsrAuthSalt(String usrAuthSalt) {
        this.usrAuthSalt = usrAuthSalt;
    }

    public Calendar getSessionExpiry() {
        return sessionExpiry;
    }

    public void setSessionExpiry(Calendar sessionExpiry) {
        this.sessionExpiry = sessionExpiry;
    }

    public Brokers getUsrBrkCode() {
        return usrBrkCode;
    }

    public void setUsrBrkCode(Brokers usrBrkCode) {
        this.usrBrkCode = usrBrkCode;
    }

    @XmlTransient
    public Collection<Brokers> getBrokersCollection() {
        return brokersCollection;
    }

    public void setBrokersCollection(Collection<Brokers> brokersCollection) {
        this.brokersCollection = brokersCollection;
    }

    @XmlTransient
    public Collection<Brokers> getBrokersCollection1() {
        return brokersCollection1;
    }

    public void setBrokersCollection1(Collection<Brokers> brokersCollection1) {
        this.brokersCollection1 = brokersCollection1;
    }

    @Override
    @JsonIgnore
    public int hashCode() {
        int hash = 0;
        hash += (usrCode != null ? usrCode.hashCode() : 0);
        return hash;
    }

    @Override
    @JsonIgnore
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.usrCode == null && other.usrCode != null) || (this.usrCode != null && !this.usrCode.equals(other.usrCode))) {
            return false;
        }
        return true;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return "entities.Users[ usrCode=" + usrCode + " ]";
    }
    
}
