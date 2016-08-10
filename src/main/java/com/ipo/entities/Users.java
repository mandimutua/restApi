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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smutua
 */
@Entity
@Table(name = "USERS")
@XmlRootElement

public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    @Basic(optional = false)
    @Column(name = "USR_STATUS")
    private BigInteger usrStatus;
    @Basic(optional = false)
    @Column(name = "USR_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrCdate;
    @Column(name = "USR_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrMdate;
    @Basic(optional = false)
    @Column(name = "USR_INPUTTER")
    private BigInteger usrInputter;
    @Column(name = "USR_AUTHORISER")
    private BigInteger usrAuthoriser;
    @Column(name = "USR_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrDate;
    @Column(name = "USR_AUTH_SALT")
    private String usrAuthSalt;
    @Column(name = "SESSION_EXPIRY")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar sessionExpiry;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cusInputter")
    private Collection<Customers> customersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cusAuthoriser")
    private Collection<Customers> customersCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payInputter")
    private Collection<Payments> paymentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payAuthoriser")
    private Collection<Payments> paymentsCollection1;
    @OneToMany(mappedBy = "mtInputter")
    private Collection<MessageTemplates> messageTemplatesCollection;
    @OneToMany(mappedBy = "mtAuthoriser")
    private Collection<MessageTemplates> messageTemplatesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleInputter")
    private Collection<Roles> rolesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleAuthoriser")
    private Collection<Roles> rolesCollection1;
    @JoinColumn(name = "USR_BRK_CODE", referencedColumnName = "BRK_CODE")
    @ManyToOne
    private Brokers usrBrkCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "divInputter")
    private Collection<Dividends> dividendsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "divAuthoriser")
    private Collection<Dividends> dividendsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paramInputter")
    private Collection<SystemParameters> systemParametersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paramAuthoriser")
    private Collection<SystemParameters> systemParametersCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permInputter")
    private Collection<Permissions> permissionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permAuthoriser")
    private Collection<Permissions> permissionsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brkInputter")
    private Collection<Brokers> brokersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brkAuthoriser")
    private Collection<Brokers> brokersCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrAuthoriser")
    private Collection<Application> applicationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appInputter")
    private Collection<Application> applicationCollection1;
    @OneToMany(mappedBy = "msgInputter")
    private Collection<Messages> messagesCollection;
    @OneToMany(mappedBy = "msgAuthoriser")
    private Collection<Messages> messagesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usroleUsrCode")
    private Collection<UserRoles> userRolesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usroleInputter")
    private Collection<UserRoles> userRolesCollection1;
    @OneToMany(mappedBy = "usroleAuthoriser")
    private Collection<UserRoles> userRolesCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batInputter")
    private Collection<Batch> batchCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batAuthoriser")
    private Collection<Batch> batchCollection1;

    public Users() {
    }

    public Users(BigDecimal usrCode) {
        this.usrCode = usrCode;
    }

    public Users(BigDecimal usrCode, String usrName, String usrEmail, String usrPass, BigInteger usrStatus, Date usrCdate, BigInteger usrInputter) {
        this.usrCode = usrCode;
        this.usrName = usrName;
        this.usrEmail = usrEmail;
        this.usrPass = usrPass;
        this.usrStatus = usrStatus;
        this.usrCdate = usrCdate;
        this.usrInputter = usrInputter;
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

    @XmlTransient
    public Collection<Customers> getCustomersCollection() {
        return customersCollection;
    }

    public void setCustomersCollection(Collection<Customers> customersCollection) {
        this.customersCollection = customersCollection;
    }

    @XmlTransient
    public Collection<Customers> getCustomersCollection1() {
        return customersCollection1;
    }

    public void setCustomersCollection1(Collection<Customers> customersCollection1) {
        this.customersCollection1 = customersCollection1;
    }

    @XmlTransient
    public Collection<Payments> getPaymentsCollection() {
        return paymentsCollection;
    }

    public void setPaymentsCollection(Collection<Payments> paymentsCollection) {
        this.paymentsCollection = paymentsCollection;
    }

    @XmlTransient
    public Collection<Payments> getPaymentsCollection1() {
        return paymentsCollection1;
    }

    public void setPaymentsCollection1(Collection<Payments> paymentsCollection1) {
        this.paymentsCollection1 = paymentsCollection1;
    }

    @XmlTransient
    public Collection<MessageTemplates> getMessageTemplatesCollection() {
        return messageTemplatesCollection;
    }

    public void setMessageTemplatesCollection(Collection<MessageTemplates> messageTemplatesCollection) {
        this.messageTemplatesCollection = messageTemplatesCollection;
    }

    @XmlTransient
    public Collection<MessageTemplates> getMessageTemplatesCollection1() {
        return messageTemplatesCollection1;
    }

    public void setMessageTemplatesCollection1(Collection<MessageTemplates> messageTemplatesCollection1) {
        this.messageTemplatesCollection1 = messageTemplatesCollection1;
    }

    @XmlTransient
    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    @XmlTransient
    public Collection<Roles> getRolesCollection1() {
        return rolesCollection1;
    }

    public void setRolesCollection1(Collection<Roles> rolesCollection1) {
        this.rolesCollection1 = rolesCollection1;
    }

    public Brokers getUsrBrkCode() {
        return usrBrkCode;
    }

    public void setUsrBrkCode(Brokers usrBrkCode) {
        this.usrBrkCode = usrBrkCode;
    }

    @XmlTransient
    public Collection<Dividends> getDividendsCollection() {
        return dividendsCollection;
    }

    public void setDividendsCollection(Collection<Dividends> dividendsCollection) {
        this.dividendsCollection = dividendsCollection;
    }

    @XmlTransient
    public Collection<Dividends> getDividendsCollection1() {
        return dividendsCollection1;
    }

    public void setDividendsCollection1(Collection<Dividends> dividendsCollection1) {
        this.dividendsCollection1 = dividendsCollection1;
    }

    @XmlTransient
    public Collection<SystemParameters> getSystemParametersCollection() {
        return systemParametersCollection;
    }

    public void setSystemParametersCollection(Collection<SystemParameters> systemParametersCollection) {
        this.systemParametersCollection = systemParametersCollection;
    }

    @XmlTransient
    public Collection<SystemParameters> getSystemParametersCollection1() {
        return systemParametersCollection1;
    }

    public void setSystemParametersCollection1(Collection<SystemParameters> systemParametersCollection1) {
        this.systemParametersCollection1 = systemParametersCollection1;
    }

    @XmlTransient
    public Collection<Permissions> getPermissionsCollection() {
        return permissionsCollection;
    }

    public void setPermissionsCollection(Collection<Permissions> permissionsCollection) {
        this.permissionsCollection = permissionsCollection;
    }

    @XmlTransient
    public Collection<Permissions> getPermissionsCollection1() {
        return permissionsCollection1;
    }

    public void setPermissionsCollection1(Collection<Permissions> permissionsCollection1) {
        this.permissionsCollection1 = permissionsCollection1;
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

    @XmlTransient
    public Collection<Application> getApplicationCollection() {
        return applicationCollection;
    }

    public void setApplicationCollection(Collection<Application> applicationCollection) {
        this.applicationCollection = applicationCollection;
    }

    @XmlTransient
    public Collection<Application> getApplicationCollection1() {
        return applicationCollection1;
    }

    public void setApplicationCollection1(Collection<Application> applicationCollection1) {
        this.applicationCollection1 = applicationCollection1;
    }

    @XmlTransient
    public Collection<Messages> getMessagesCollection() {
        return messagesCollection;
    }

    public void setMessagesCollection(Collection<Messages> messagesCollection) {
        this.messagesCollection = messagesCollection;
    }

    @XmlTransient
    public Collection<Messages> getMessagesCollection1() {
        return messagesCollection1;
    }

    public void setMessagesCollection1(Collection<Messages> messagesCollection1) {
        this.messagesCollection1 = messagesCollection1;
    }

    @XmlTransient
    public Collection<UserRoles> getUserRolesCollection() {
        return userRolesCollection;
    }

    public void setUserRolesCollection(Collection<UserRoles> userRolesCollection) {
        this.userRolesCollection = userRolesCollection;
    }

    @XmlTransient
    public Collection<UserRoles> getUserRolesCollection1() {
        return userRolesCollection1;
    }

    public void setUserRolesCollection1(Collection<UserRoles> userRolesCollection1) {
        this.userRolesCollection1 = userRolesCollection1;
    }

    @XmlTransient
    public Collection<UserRoles> getUserRolesCollection2() {
        return userRolesCollection2;
    }

    public void setUserRolesCollection2(Collection<UserRoles> userRolesCollection2) {
        this.userRolesCollection2 = userRolesCollection2;
    }

    @XmlTransient
    public Collection<Batch> getBatchCollection() {
        return batchCollection;
    }

    public void setBatchCollection(Collection<Batch> batchCollection) {
        this.batchCollection = batchCollection;
    }

    @XmlTransient
    public Collection<Batch> getBatchCollection1() {
        return batchCollection1;
    }

    public void setBatchCollection1(Collection<Batch> batchCollection1) {
        this.batchCollection1 = batchCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrCode != null ? usrCode.hashCode() : 0);
        return hash;
    }

    @Override
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
    public String toString() {
        return "entities.Users[ usrCode=" + usrCode + " ]";
    }
    
}
