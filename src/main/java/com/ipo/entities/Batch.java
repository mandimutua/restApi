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
import javax.persistence.OneToOne;
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
@Table(name = "BATCH")

//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="batCode")
public class Batch implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "BATCH_CODE_SEQUENCE", sequenceName = "BATCH_CODE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BATCH_CODE_SEQUENCE")
    @Column(name = "BAT_CODE")
    private BigDecimal batCode;
    @Basic(optional = false)
    
    @Column(name = "BAT_CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batCreateDate;
    
    @Column(name = "BAT_TOTAL_SHARES")
    private BigInteger batTotalShares;
    @Column(name = "BAT_STATUS")
    private BigInteger batStatus;
   
    @Column(name = "BAT_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batCdate;
   
    @Column(name = "BAT_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batMdate;
   
    @Column(name = "BAT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batDate;
    
    @Column(name = "BAT_NUMBER")
    private BigInteger batNumber;
    @Basic(optional = false)
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "appCusPalCode")
    @JsonBackReference(value="customer")
    private Application application;
    
    
   
	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appBatCode")
    //@JsonBackReference(value="batch")
    private Collection<Application> applicationCollection;
    
    @JoinColumn(name = "BAT_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users batInputter;
    
    @JoinColumn(name = "BAT_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = true)
    private Users batAuthoriser;
    
    @JoinColumn(name = "BAT_BRK_CODE", referencedColumnName = "BRK_CODE")
    @ManyToOne(optional = false)
   
    private Brokers batBrkCode;

    public Batch() {
    }

    public Batch(BigDecimal batCode) {
        this.batCode = batCode;
    }

    public Batch(BigDecimal batCode,BigInteger batNumber,Date batCreateDate, BigInteger batTotalShares, Date batCdate, Date batMdate, Date batDate) {
        this.batCode = batCode;
        this.batCreateDate = batCreateDate;
        this.batTotalShares = batTotalShares;
        this.batCdate = batCdate;
        this.batMdate = batMdate;
        this.batDate = batDate;
        this.batNumber = batNumber;
    }

    public BigDecimal getBatCode() {
        return batCode;
    }

    public void setBatCode(BigDecimal batCode) {
        this.batCode = batCode;
    }

    public Date getBatCreateDate() {
        return batCreateDate;
    }

    public void setBatCreateDate(Date batCreateDate) {
        this.batCreateDate = batCreateDate;
    }

    public BigInteger getBatTotalShares() {
        return batTotalShares;
    }

    public void setBatTotalShares(BigInteger batTotalShares) {
        this.batTotalShares = batTotalShares;
    }

    public BigInteger getBatStatus() {
        return batStatus;
    }

    public void setBatStatus(BigInteger batStatus) {
        this.batStatus = batStatus;
    }

    public Date getBatCdate() {
        return batCdate;
    }

    public void setBatCdate(Date batCdate) {
        this.batCdate = batCdate;
    }

    public Date getBatMdate() {
        return batMdate;
    }

    public void setBatMdate(Date batMdate) {
        this.batMdate = batMdate;
    }

    public Date getBatDate() {
        return batDate;
    }

    public void setBatDate(Date batDate) {
        this.batDate = batDate;
    }
    
    
    public BigInteger getBatNumber() {
		return batNumber;
	}

	public void setBatNumber(BigInteger batNumber) {
		this.batNumber = batNumber;
	}


    @XmlTransient
    public Collection<Application> getApplicationCollection() {
        return applicationCollection;
    }

    public void setApplicationCollection(Collection<Application> applicationCollection) {
        this.applicationCollection = applicationCollection;
    }

    public Users getBatInputter() {
        return batInputter;
    }

    public void setBatInputter(Users batInputter) {
        this.batInputter = batInputter;
    }

    public Users getBatAuthoriser() {
        return batAuthoriser;
    }

    public void setBatAuthoriser(Users batAuthoriser) {
        this.batAuthoriser = batAuthoriser;
    }

    public Brokers getBatBrkCode() {
        return batBrkCode;
    }

    public void setBatBrkCode(Brokers batBrkCode) {
        this.batBrkCode = batBrkCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (batCode != null ? batCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Batch)) {
            return false;
        }
        Batch other = (Batch) object;
        if ((this.batCode == null && other.batCode != null) || (this.batCode != null && !this.batCode.equals(other.batCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Batch[ batCode=" + batCode + " ]";
    }
    
}
