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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smutua
 */
@Entity
@Table(name = "MESSAGES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m")})
public class Messages implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "MSG_CODE")
    private BigDecimal msgCode;
    @Column(name = "MSG_NAME")
    private String msgName;
    @Column(name = "MSG_CONTENT")
    private String msgContent;
    @Column(name = "MSG_STATUS")
    private BigInteger msgStatus;
    @Column(name = "MSG_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date msgCdate;
    @Column(name = "MSG_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date msgMdate;
    @Column(name = "MSG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date msgDate;
    @JoinColumn(name = "MSG_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne
    private Users msgInputter;
    @JoinColumn(name = "MSG_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne
    private Users msgAuthoriser;
    @JoinColumn(name = "MSG_MT_CODE", referencedColumnName = "MT_CODE")
    @ManyToOne
    private MessageTemplates msgMtCode;

    public Messages() {
    }

    public Messages(BigDecimal msgCode) {
        this.msgCode = msgCode;
    }

    public BigDecimal getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(BigDecimal msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgName() {
        return msgName;
    }

    public void setMsgName(String msgName) {
        this.msgName = msgName;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public BigInteger getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(BigInteger msgStatus) {
        this.msgStatus = msgStatus;
    }

    public Date getMsgCdate() {
        return msgCdate;
    }

    public void setMsgCdate(Date msgCdate) {
        this.msgCdate = msgCdate;
    }

    public Date getMsgMdate() {
        return msgMdate;
    }

    public void setMsgMdate(Date msgMdate) {
        this.msgMdate = msgMdate;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    public Users getMsgInputter() {
        return msgInputter;
    }

    public void setMsgInputter(Users msgInputter) {
        this.msgInputter = msgInputter;
    }

    public Users getMsgAuthoriser() {
        return msgAuthoriser;
    }

    public void setMsgAuthoriser(Users msgAuthoriser) {
        this.msgAuthoriser = msgAuthoriser;
    }

    public MessageTemplates getMsgMtCode() {
        return msgMtCode;
    }

    public void setMsgMtCode(MessageTemplates msgMtCode) {
        this.msgMtCode = msgMtCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (msgCode != null ? msgCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.msgCode == null && other.msgCode != null) || (this.msgCode != null && !this.msgCode.equals(other.msgCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Messages[ msgCode=" + msgCode + " ]";
    }
    
}
