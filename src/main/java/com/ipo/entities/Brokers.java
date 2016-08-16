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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;




/**
 *
 * @author smutua
 */
@Entity
@Table(name = "BROKERS")
public class Brokers implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "BROKER_CODE_SEQ", sequenceName = "BROKER_CODE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BROKER_CODE_SEQ")
    @Basic(optional = false)
    @Column(name = "BRK_CODE")
    private BigDecimal brkCode;
    @Basic(optional = false)
    
    @Column(name = "BRK_NAME")
    private String brkName;
    
    @Column(name = "BRK_ADDRESS")
    private String brkAddress;
    
    @Column(name = "BRK_TOWN")
    private String brkTown;
    
    @Column(name = "BRK_PHONE")
    private String brkPhone;
    
    @Column(name = "BRK_EMAIL")
    private String brkEmail;
    
    @Column(name = "BRK_STATUS")
    private BigInteger brkStatus;
    @Basic(optional = false)
    
    @Column(name = "BRK_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date brkCdate;
    @Basic(optional = false)
    
    @Column(name = "BRK_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date brkMdate;
    @Basic(optional = true)
    
    @Column(name = "BRK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date brkDate;
    
    
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batBrkCode")
    private Collection<Batch> batchCollection;
    @OneToMany(mappedBy = "usrBrkCode")
    
    @JsonIgnore
    private Collection<Users> usersCollection;
    
   @JsonIgnore
   @JoinColumn(name = "BRK_AUTHORISER", referencedColumnName = "USR_CODE")
   @ManyToOne(optional = true)
  // @JsonBackReference
    private Users brkAuthoriser;
    
    
    @JoinColumn(name = "BRK_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    @JsonBackReference
    private Users brkInputter;

    public Brokers() {
    }

    public Brokers(BigDecimal brkCode) {
        this.brkCode = brkCode;
    }

    public Brokers(BigDecimal brkCode, String brkName, Date brkCdate, Date brkMdate, Date brkDate) {
        this.brkCode = brkCode;
        this.brkName = brkName;
        this.brkCdate = brkCdate;
        this.brkMdate = brkMdate;
        this.brkDate = brkDate;
    }

    public BigDecimal getBrkCode() {
        return brkCode;
    }

    public void setBrkCode(BigDecimal brkCode) {
        this.brkCode = brkCode;
    }

    public String getBrkName() {
        return brkName;
    }

    public void setBrkName(String brkName) {
        this.brkName = brkName;
    }

    public String getBrkAddress() {
        return brkAddress;
    }

    public void setBrkAddress(String brkAddress) {
        this.brkAddress = brkAddress;
    }

    public String getBrkTown() {
        return brkTown;
    }

    public void setBrkTown(String brkTown) {
        this.brkTown = brkTown;
    }

    public String getBrkPhone() {
        return brkPhone;
    }

    public void setBrkPhone(String brkPhone) {
        this.brkPhone = brkPhone;
    }

    public String getBrkEmail() {
        return brkEmail;
    }

    public void setBrkEmail(String brkEmail) {
        this.brkEmail = brkEmail;
    }

    public BigInteger getBrkStatus() {
        return brkStatus;
    }

    public void setBrkStatus(BigInteger brkStatus) {
        this.brkStatus = brkStatus;
    }

    public Date getBrkCdate() {
        return brkCdate;
    }

    public void setBrkCdate(Date brkCdate) {
        this.brkCdate = brkCdate;
    }

    public Date getBrkMdate() {
        return brkMdate;
    }

    public void setBrkMdate(Date brkMdate) {
        this.brkMdate = brkMdate;
    }

    public Date getBrkDate() {
        return brkDate;
    }

    public void setBrkDate(Date brkDate) {
        this.brkDate = brkDate;
    }

    @XmlTransient
    public Collection<Batch> getBatchCollection() {
        return batchCollection;
    }

    public void setBatchCollection(Collection<Batch> batchCollection) {
        this.batchCollection = batchCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    public Users getBrkAuthoriser() {
        return brkAuthoriser;
    }

    public void setBrkAuthoriser(Users brkAuthoriser) {
        this.brkAuthoriser = brkAuthoriser;
    }

    public Users getBrkInputter() {
        return brkInputter;
    }

   
    public void setBrkInputter(Users brkInputter) {
        this.brkInputter = brkInputter;
    }

    @Override
    @JsonIgnore
    public int hashCode() {
        int hash = 0;
        hash += (brkCode != null ? brkCode.hashCode() : 0);
        return hash;
    }

    @Override
    @JsonIgnore
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Brokers)) {
            return false;
        }
        Brokers other = (Brokers) object;
        if ((this.brkCode == null && other.brkCode != null) || (this.brkCode != null && !this.brkCode.equals(other.brkCode))) {
            return false;
        }
        return true;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return "entities.Brokers[ brkCode=" + brkCode + " ]";
    }
    
}
