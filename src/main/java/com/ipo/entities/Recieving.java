package com.ipo.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



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
@Table(name = "RECEIVING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receiving.findAll", query = "SELECT r FROM Receiving r")})
public class Recieving implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "RCV_CODE")
    private BigDecimal rcvCode;
    @Basic(optional = false)
    @Column(name = "RCV_STATUS")
    private BigInteger rcvStatus;
    @Column(name = "RCV_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rcvCdate;
    @Column(name = "RCV_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rcvMdate;
    @Column(name = "RCV_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rcvDate;
    @JoinColumn(name = "RCV_INPUTTER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = false)
    private Users rcvInputter;
    @JoinColumn(name = "RCV_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne
    private Users rcvAuthoriser;
    @JoinColumn(name = "RCV_BAT_CODE", referencedColumnName = "BAT_CODE")
    @ManyToOne(optional = false)
    private Batch rcvBatCode;

    public Recieving() {
    }

    public Recieving(BigDecimal rcvCode) {
        this.rcvCode = rcvCode;
    }

    public Recieving(BigDecimal rcvCode, BigInteger rcvStatus) {
        this.rcvCode = rcvCode;
        this.rcvStatus = rcvStatus;
    }

    public BigDecimal getRcvCode() {
        return rcvCode;
    }

    public void setRcvCode(BigDecimal rcvCode) {
        this.rcvCode = rcvCode;
    }

    public BigInteger getRcvStatus() {
        return rcvStatus;
    }

    public void setRcvStatus(BigInteger rcvStatus) {
        this.rcvStatus = rcvStatus;
    }

    public Date getRcvCdate() {
        return rcvCdate;
    }

    public void setRcvCdate(Date rcvCdate) {
        this.rcvCdate = rcvCdate;
    }

    public Date getRcvMdate() {
        return rcvMdate;
    }

    public void setRcvMdate(Date rcvMdate) {
        this.rcvMdate = rcvMdate;
    }

    public Date getRcvDate() {
        return rcvDate;
    }

    public void setRcvDate(Date rcvDate) {
        this.rcvDate = rcvDate;
    }

    public Users getRcvInputter() {
        return rcvInputter;
    }

    public void setRcvInputter(Users rcvInputter) {
        this.rcvInputter = rcvInputter;
    }

    public Users getRcvAuthoriser() {
        return rcvAuthoriser;
    }

    public void setRcvAuthoriser(Users rcvAuthoriser) {
        this.rcvAuthoriser = rcvAuthoriser;
    }

    public Batch getRcvBatCode() {
        return rcvBatCode;
    }

    public void setRcvBatCode(Batch rcvBatCode) {
        this.rcvBatCode = rcvBatCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rcvCode != null ? rcvCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recieving)) {
            return false;
        }
        Recieving other = (Recieving) object;
        if ((this.rcvCode == null && other.rcvCode != null) || (this.rcvCode != null && !this.rcvCode.equals(other.rcvCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Receiving[ rcvCode=" + rcvCode + " ]";
    }
    
}
