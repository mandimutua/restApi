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
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author smutua
 */
@Entity
@Table(name = "USER_ROLES")
@XmlRootElement
public class UserRoles implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "USER_ROLE_CODE_SEQ", sequenceName = "USER_ROLE_CODE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ROLE_CODE_SEQ")
    @Basic(optional = false)
    @Column(name = "USROLE_CODE")
    private BigDecimal usroleCode;
    
    @Column(name = "USROLE_STATUS")
    private BigInteger usroleStatus;
    @Basic(optional = false)
    
    @Column(name = "USROLE_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usroleCdate;
    
    @Column(name = "USROLE_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usroleMdate;
    
    @Column(name = "USROLE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usroleDate;
    
    @JoinColumn(name = "USROLE_USR_CODE", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users usroleUsrCode;
    
    @JoinColumn(name = "USROLE_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users usroleInputter;
    
  
    
    @JoinColumn(name = "USROLE_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = true)
    private Users usroleAuthoriser;
    
    @JsonBackReference(value="roles")
    @JoinColumn(name = "USROLE_ROLE_CODE", referencedColumnName = "ROLE_CODE")
    @ManyToOne(optional = false)
    private Roles usroleRoleCode;

    public UserRoles() {
    }

    public UserRoles(BigDecimal usroleCode) {
        this.usroleCode = usroleCode;
    }

    public UserRoles(BigDecimal usroleCode, Date usroleCdate) {
        this.usroleCode = usroleCode;
        this.usroleCdate = usroleCdate;
    }

    public BigDecimal getUsroleCode() {
        return usroleCode;
    }

    public void setUsroleCode(BigDecimal usroleCode) {
        this.usroleCode = usroleCode;
    }

    public BigInteger getUsroleStatus() {
        return usroleStatus;
    }

    public void setUsroleStatus(BigInteger usroleStatus) {
        this.usroleStatus = usroleStatus;
    }

    public Date getUsroleCdate() {
        return usroleCdate;
    }

    public void setUsroleCdate(Date usroleCdate) {
        this.usroleCdate = usroleCdate;
    }

    public Date getUsroleMdate() {
        return usroleMdate;
    }

    public void setUsroleMdate(Date usroleMdate) {
        this.usroleMdate = usroleMdate;
    }

    public Date getUsroleDate() {
        return usroleDate;
    }

    public void setUsroleDate(Date usroleDate) {
        this.usroleDate = usroleDate;
    }

    public Users getUsroleUsrCode() {
        return usroleUsrCode;
    }

    public void setUsroleUsrCode(Users usroleUsrCode) {
        this.usroleUsrCode = usroleUsrCode;
    }

    public Users getUsroleInputter() {
        return usroleInputter;
    }

    public void setUsroleInputter(Users usroleInputter) {
        this.usroleInputter = usroleInputter;
    }

    public Users getUsroleAuthoriser() {
        return usroleAuthoriser;
    }

    public void setUsroleAuthoriser(Users usroleAuthoriser) {
        this.usroleAuthoriser = usroleAuthoriser;
    }

    public Roles getUsroleRoleCode() {
        return usroleRoleCode;
    }

    public void setUsroleRoleCode(Roles usroleRoleCode) {
        this.usroleRoleCode = usroleRoleCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usroleCode != null ? usroleCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRoles)) {
            return false;
        }
        UserRoles other = (UserRoles) object;
        if ((this.usroleCode == null && other.usroleCode != null) || (this.usroleCode != null && !this.usroleCode.equals(other.usroleCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserRoles[ usroleCode=" + usroleCode + " ]";
    }
    
}
