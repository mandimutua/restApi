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
@Table(name = "MESSAGE_TEMPLATES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageTemplates.findAll", query = "SELECT m FROM MessageTemplates m")})
public class MessageTemplates implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "MT_CODE")
    private BigDecimal mtCode;
    @Column(name = "MT_TEMPLATE")
    private String mtTemplate;
    @Column(name = "MT_STATUS")
    private BigInteger mtStatus;
    @Column(name = "MT_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mtCdate;
    @Column(name = "MT_MDATE")
    private String mtMdate;
    @Column(name = "MT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mtDate;
    @JoinColumn(name = "MT_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne
    private Users mtInputter;
    @JoinColumn(name = "MT_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne
    private Users mtAuthoriser;
    @OneToMany(mappedBy = "msgMtCode")
    private Collection<Messages> messagesCollection;

    public MessageTemplates() {
    }

    public MessageTemplates(BigDecimal mtCode) {
        this.mtCode = mtCode;
    }

    public BigDecimal getMtCode() {
        return mtCode;
    }

    public void setMtCode(BigDecimal mtCode) {
        this.mtCode = mtCode;
    }

    public String getMtTemplate() {
        return mtTemplate;
    }

    public void setMtTemplate(String mtTemplate) {
        this.mtTemplate = mtTemplate;
    }

    public BigInteger getMtStatus() {
        return mtStatus;
    }

    public void setMtStatus(BigInteger mtStatus) {
        this.mtStatus = mtStatus;
    }

    public Date getMtCdate() {
        return mtCdate;
    }

    public void setMtCdate(Date mtCdate) {
        this.mtCdate = mtCdate;
    }

    public String getMtMdate() {
        return mtMdate;
    }

    public void setMtMdate(String mtMdate) {
        this.mtMdate = mtMdate;
    }

    public Date getMtDate() {
        return mtDate;
    }

    public void setMtDate(Date mtDate) {
        this.mtDate = mtDate;
    }

    public Users getMtInputter() {
        return mtInputter;
    }

    public void setMtInputter(Users mtInputter) {
        this.mtInputter = mtInputter;
    }

    public Users getMtAuthoriser() {
        return mtAuthoriser;
    }

    public void setMtAuthoriser(Users mtAuthoriser) {
        this.mtAuthoriser = mtAuthoriser;
    }

    @XmlTransient
    public Collection<Messages> getMessagesCollection() {
        return messagesCollection;
    }

    public void setMessagesCollection(Collection<Messages> messagesCollection) {
        this.messagesCollection = messagesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mtCode != null ? mtCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageTemplates)) {
            return false;
        }
        MessageTemplates other = (MessageTemplates) object;
        if ((this.mtCode == null && other.mtCode != null) || (this.mtCode != null && !this.mtCode.equals(other.mtCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MessageTemplates[ mtCode=" + mtCode + " ]";
    }
    
}
