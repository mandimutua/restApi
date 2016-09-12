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

/**
 *
 * @author smutua
 */
@Entity
@Table(name = "PERMISSIONS")
@XmlRootElement

public class Permissions implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PERMISSION_CODE_SEQ", sequenceName = "PERMISSION_CODE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSION_CODE_SEQ")
    @Basic(optional = false)
    @Column(name = "PERM_CODE")
    private BigDecimal permCode;
    @Column(name = "PERM_NAME")
    private String permName;
    @Column(name = "PERM_DESC")
    private String permDesc;
    @Column(name = "PERM_STATUS")
    private BigInteger permStatus;
    @Basic(optional = false)
    @Column(name = "PERM_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date permCdate;
    @Basic(optional = false)
    @Column(name = "PERM_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date permMdate;
    @Basic(optional = true)
    @Column(name = "PERM_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date permDate;
    @JoinColumn(name = "PERM_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users permInputter;
    @JoinColumn(name = "PERM_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = true)
    private Users permAuthoriser;
    @JoinColumn(name = "PERM_ROLE_CODE", referencedColumnName = "ROLE_CODE")
    @ManyToOne
    private Roles permRoleCode;

    public Permissions() {
    }

    public Permissions(BigDecimal permCode) {
        this.permCode = permCode;
    }

    public Permissions(BigDecimal permCode, Date permCdate, Date permMdate, Date permDate) {
        this.permCode = permCode;
        this.permCdate = permCdate;
        this.permMdate = permMdate;
        this.permDate = permDate;
    }

    public BigDecimal getPermCode() {
        return permCode;
    }

    public void setPermCode(BigDecimal permCode) {
        this.permCode = permCode;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermDesc() {
        return permDesc;
    }

    public void setPermDesc(String permDesc) {
        this.permDesc = permDesc;
    }

    public BigInteger getPermStatus() {
        return permStatus;
    }

    public void setPermStatus(BigInteger permStatus) {
        this.permStatus = permStatus;
    }

    public Date getPermCdate() {
        return permCdate;
    }

    public void setPermCdate(Date permCdate) {
        this.permCdate = permCdate;
    }

    public Date getPermMdate() {
        return permMdate;
    }

    public void setPermMdate(Date permMdate) {
        this.permMdate = permMdate;
    }

    public Date getPermDate() {
        return permDate;
    }

    public void setPermDate(Date permDate) {
        this.permDate = permDate;
    }

    public Users getPermInputter() {
        return permInputter;
    }

    public void setPermInputter(Users permInputter) {
        this.permInputter = permInputter;
    }

    public Users getPermAuthoriser() {
        return permAuthoriser;
    }

    public void setPermAuthoriser(Users permAuthoriser) {
        this.permAuthoriser = permAuthoriser;
    }

    public Roles getPermRoleCode() {
        return permRoleCode;
    }

    public void setPermRoleCode(Roles permRoleCode) {
        this.permRoleCode = permRoleCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permCode != null ? permCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissions)) {
            return false;
        }
        Permissions other = (Permissions) object;
        if ((this.permCode == null && other.permCode != null) || (this.permCode != null && !this.permCode.equals(other.permCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Permissions[ permCode=" + permCode + " ]";
    }
    
}
