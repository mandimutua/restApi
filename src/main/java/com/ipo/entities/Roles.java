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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smutua
 */
@Entity
@Table(name = "ROLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")})
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ROLE_CODE")
    private BigDecimal roleCode;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Column(name = "ROLE_DESC")
    private String roleDesc;
    @Basic(optional = false)
    @Column(name = "ROLE_STATUS")
    private BigInteger roleStatus;
    @Basic(optional = false)
    @Column(name = "ROLE_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date roleCdate;
    @Basic(optional = false)
    @Column(name = "ROLE_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date roleMdate;
    @Basic(optional = false)
    @Column(name = "ROLE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date roleDate;
    @JoinColumn(name = "ROLE_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users roleInputter;
    @JoinColumn(name = "ROLE_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users roleAuthoriser;
    @OneToMany(mappedBy = "permRoleCode")
    private Collection<Permissions> permissionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usroleRoleCode")
    private Collection<UserRoles> userRolesCollection;

    public Roles() {
    }

    public Roles(BigDecimal roleCode) {
        this.roleCode = roleCode;
    }

    public Roles(BigDecimal roleCode, BigInteger roleStatus, Date roleCdate, Date roleMdate, Date roleDate) {
        this.roleCode = roleCode;
        this.roleStatus = roleStatus;
        this.roleCdate = roleCdate;
        this.roleMdate = roleMdate;
        this.roleDate = roleDate;
    }

    public BigDecimal getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(BigDecimal roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public BigInteger getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(BigInteger roleStatus) {
        this.roleStatus = roleStatus;
    }

    public Date getRoleCdate() {
        return roleCdate;
    }

    public void setRoleCdate(Date roleCdate) {
        this.roleCdate = roleCdate;
    }

    public Date getRoleMdate() {
        return roleMdate;
    }

    public void setRoleMdate(Date roleMdate) {
        this.roleMdate = roleMdate;
    }

    public Date getRoleDate() {
        return roleDate;
    }

    public void setRoleDate(Date roleDate) {
        this.roleDate = roleDate;
    }

    public Users getRoleInputter() {
        return roleInputter;
    }

    public void setRoleInputter(Users roleInputter) {
        this.roleInputter = roleInputter;
    }

    public Users getRoleAuthoriser() {
        return roleAuthoriser;
    }

    public void setRoleAuthoriser(Users roleAuthoriser) {
        this.roleAuthoriser = roleAuthoriser;
    }

    @XmlTransient
    public Collection<Permissions> getPermissionsCollection() {
        return permissionsCollection;
    }

    public void setPermissionsCollection(Collection<Permissions> permissionsCollection) {
        this.permissionsCollection = permissionsCollection;
    }

    @XmlTransient
    public Collection<UserRoles> getUserRolesCollection() {
        return userRolesCollection;
    }

    public void setUserRolesCollection(Collection<UserRoles> userRolesCollection) {
        this.userRolesCollection = userRolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleCode != null ? roleCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.roleCode == null && other.roleCode != null) || (this.roleCode != null && !this.roleCode.equals(other.roleCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Roles[ roleCode=" + roleCode + " ]";
    }
    
}
