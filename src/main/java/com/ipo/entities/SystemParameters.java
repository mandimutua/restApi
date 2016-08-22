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
 * @author
 */
@Entity
@Table(name = "SYSTEM_PARAMETERS")
public class SystemParameters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "PARAM_NAME")
    private String paramName;
    @Basic(optional = false)
    
    @Column(name = "PARAM_VALUE_1")
    private String paramValue1;
    @Basic(optional = false)
    
    @Column(name = "PARAM_VALUE_2")
    private String paramValue2;
    
    @Column(name = "PARAM_APPL")
    private String paramAppl;
    
    @Column(name = "PARAM_EXEMPT")
    private String paramExempt;
    
    @Column(name = "PARAM_STATUS")
    private BigInteger paramStatus;
    @Basic(optional = false)
    
    @Column(name = "PARAM_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paramCdate;
    @Basic(optional = false)
    
    @Column(name = "PARAM_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paramMdate;
    @Basic(optional = true)
    
    @Column(name = "PARAM_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paramDate;
    @Basic(optional = false)
    
    
    @Id
    @SequenceGenerator(name = "SYSTEM_PARAMS_CODE_SEQ", sequenceName = "SYSTEM_PARAMS_CODE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYSTEM_PARAMS_CODE_SEQ")
    @Column(name = "PARAM_CODE")
    private BigDecimal paramCode;
    @Basic(optional = false)
    
    @JoinColumn(name = "PARAM_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = true)
    private Users paramAuthoriser;
    @JoinColumn(name = "PARAM_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users paramInputter;

    public SystemParameters() {
    }

    public SystemParameters(BigDecimal paramCode) {
        this.paramCode = paramCode;
    }

    public SystemParameters(BigDecimal paramCode, Date paramCdate, Date paramMdate, Date paramDate) {
        this.paramCode = paramCode;
        this.paramCdate = paramCdate;
        this.paramMdate = paramMdate;
        this.paramDate = paramDate;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue1() {
        return paramValue1;
    }

    public void setParamValue1(String paramValue1) {
        this.paramValue1 = paramValue1;
    }

    public String getParamValue2() {
        return paramValue2;
    }

    public void setParamValue2(String paramValue2) {
        this.paramValue2 = paramValue2;
    }

    public String getParamAppl() {
        return paramAppl;
    }

    public void setParamAppl(String paramAppl) {
        this.paramAppl = paramAppl;
    }

    public String getParamExempt() {
        return paramExempt;
    }

    public void setParamExempt(String paramExempt) {
        this.paramExempt = paramExempt;
    }

    public BigInteger getParamStatus() {
        return paramStatus;
    }

    public void setParamStatus(BigInteger paramStatus) {
        this.paramStatus = paramStatus;
    }

    public Date getParamCdate() {
        return paramCdate;
    }

    public void setParamCdate(Date paramCdate) {
        this.paramCdate = paramCdate;
    }

    public Date getParamMdate() {
        return paramMdate;
    }

    public void setParamMdate(Date paramMdate) {
        this.paramMdate = paramMdate;
    }

    public Date getParamDate() {
        return paramDate;
    }

    public void setParamDate(Date paramDate) {
        this.paramDate = paramDate;
    }

    public BigDecimal getParamCode() {
        return paramCode;
    }

    public void setParamCode(BigDecimal paramCode) {
        this.paramCode = paramCode;
    }

    public Users getParamAuthoriser() {
        return paramAuthoriser;
    }

    public void setParamAuthoriser(Users paramAuthoriser) {
        this.paramAuthoriser = paramAuthoriser;
    }

    public Users getParamInputter() {
        return paramInputter;
    }

    public void setParamInputter(Users paramInputter) {
        this.paramInputter = paramInputter;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paramCode != null ? paramCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemParameters)) {
            return false;
        }
        SystemParameters other = (SystemParameters) object;
        if ((this.paramCode == null && other.paramCode != null) || (this.paramCode != null && !this.paramCode.equals(other.paramCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SystemParameters[ paramCode=" + paramCode + " ]";
    }
    
}
