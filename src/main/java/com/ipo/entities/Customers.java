/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *
 * @author smutua
 */
@Entity
@Table(name = "CUSTOMERS")
@XmlRootElement

public class Customers implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CUSTOMER_CODE_SEQ", sequenceName = "CUSTOMER_CODE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_CODE_SEQ")
    @Basic(optional = false)
    @Column(name = "CUS_PAL_CODE")
    private BigDecimal cusPalCode;
    @Basic(optional = false)
    
    @Column(name = "CUS_CDSC_AC_NO")
    private String cusCdscAcNo;
    @Column(name = "CUS_SURNAME")
    private String cusSurname;
    @Column(name = "CUS_ADDRESS")
    private String cusAddress;
    @Column(name = "CUS_TOWN")
    private String cusTown;
    @Basic(optional = false)
    @Column(name = "CUS_CITIZENSHIP")
    private String cusCitizenship;
   
    @Column(name = "CUS_MOBILE_PHONE")
    private String cusMobilePhone;
    @Column(name = "CUS_EMAIL")
    private String cusEmail;
    @Basic(optional = false)
    @Column(name = "CUS_STATUS")
    private BigInteger cusStatus;
    @Basic(optional = false)
    @Column(name = "CUS_CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cusCdate;
    @Column(name = "CUS_MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cusMdate;
    @Basic(optional = false)
    @Column(name = "CUS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cusDate;
    @Basic(optional = false)
    @Column(name = "CUS_FORM_SERIAL_NO")
    private BigInteger cusFormSerialNo;
    @Basic(optional = false)
    @Column(name = "CUS_APPLICANT_CATEGORY")
    private String cusApplicantCategory;
    @Basic(optional = false)
    @Column(name = "CUS_APPLICANT_RESIDENCE")
    private String cusApplicantResidence;
    @Column(name = "CUS_TAX_EXMPT")
    private String cusTaxExmpt;
    @Column(name = "CUS_OTHER_NAMES")
    private String cusOtherNames;
    @Column(name = "CUS_ID_NO")
    private String cusIdNo;
    @Column(name = "CUS_JOINT_SURNAME")
    private String cusJointSurname;
    @Column(name = "CUS_JOINT_OTHERNAMES")
    private String cusJointOthernames;
    @Column(name = "CUS_JOINT_APPLICANT_ID_NO")
    private String cusJointApplicantIdNo;
    @Column(name = "CUS_COMPANY_NAME")
    private String cusCompanyName;
    @Column(name = "CUS_COMPANY_REG_NO")
    private String cusCompanyRegNo;
    @Column(name = "CUS_COMPANY_DATE_OF_INC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cusCompanyDateOfInc;
    @Column(name = "CUS_NOMINEE_NAME")
    private String cusNomineeName;
    @Column(name = "CUS_NOMINEE_AC")
    private BigInteger cusNomineeAc;
    @Column(name = "CUS_POSTAL_CODE")
    private String cusPostalCode;
    @Column(name = "CUS_STREET")
    private String cusStreet;
    @Column(name = "CUS_TEL_NO")
    private String cusTelNo;
    @Column(name = "CUS_FAX_NO")
    private String cusFaxNo;
    @Basic(optional = false)
    @Column(name = "CUS_APPLICANT_TYPE")
    private String cusApplicantType;
    
    @Column(name = "CUS_COUNTRY")
    private String cusCountry;
    
	

	@JoinColumn(name = "CUS_INPUTTER", referencedColumnName = "USR_CODE")
   // @JsonManagedReference
    @ManyToOne(optional = false)
    private Users cusInputter;
    
    @JsonIgnore
    @JoinColumn(name = "CUS_AUTHORISER", referencedColumnName = "USR_CODE")
    @ManyToOne(optional = true)
    private Users cusAuthoriser;
    
    @JsonBackReference(value="customer")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "appCusPalCode")
    private Application application;

	
    @JoinColumn(name = "CUS_BRK_CODE", referencedColumnName = "BRK_CODE")
    @ManyToOne(optional = false)
    private Brokers cusBrkCode;

    public Customers() {
    }

    public Customers(BigDecimal cusPalCode) {
        this.cusPalCode = cusPalCode;
    }

    public Customers(BigDecimal cusPalCode, String cusCdscAcNo, String cusCitizenship, BigInteger cusStatus, Date cusCdate, Date cusDate, BigInteger cusFormSerialNo, String cusApplicantCategory, String cusApplicantResidence, String cusApplicantType, String cusCountry) {
        this.cusPalCode = cusPalCode;
        this.cusCdscAcNo = cusCdscAcNo;
        this.cusCitizenship = cusCitizenship;
        this.cusStatus = cusStatus;
        this.cusCdate = cusCdate;
        this.cusDate = cusDate;
        this.cusFormSerialNo = cusFormSerialNo;
        this.cusApplicantCategory = cusApplicantCategory;
        this.cusApplicantResidence = cusApplicantResidence;
        this.cusApplicantType = cusApplicantType;
        this.cusCountry = cusCountry;
    }

    public BigDecimal getCusPalCode() {
        return cusPalCode;
    }

    public void setCusPalCode(BigDecimal cusPalCode) {
        this.cusPalCode = cusPalCode;
    }

    public String getCusCdscAcNo() {
        return cusCdscAcNo;
    }

    public void setCusCdscAcNo(String cusCdscAcNo) {
        this.cusCdscAcNo = cusCdscAcNo;
    }

    public String getCusSurname() {
        return cusSurname;
    }

    public void setCusSurname(String cusSurname) {
        this.cusSurname = cusSurname;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusTown() {
        return cusTown;
    }

    public void setCusTown(String cusTown) {
        this.cusTown = cusTown;
    }

    public String getCusCitizenship() {
        return cusCitizenship;
    }

    public void setCusCitizenship(String cusCitizenship) {
        this.cusCitizenship = cusCitizenship;
    }



    public String getCusMobilePhone() {
        return cusMobilePhone;
    }

    public void setCusMobilePhone(String cusMobilePhone) {
        this.cusMobilePhone = cusMobilePhone;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public BigInteger getCusStatus() {
        return cusStatus;
    }

    public void setCusStatus(BigInteger cusStatus) {
        this.cusStatus = cusStatus;
    }

    public Date getCusCdate() {
        return cusCdate;
    }

    public void setCusCdate(Date cusCdate) {
        this.cusCdate = cusCdate;
    }

    public Date getCusMdate() {
        return cusMdate;
    }

    public void setCusMdate(Date cusMdate) {
        this.cusMdate = cusMdate;
    }

    public Date getCusDate() {
        return cusDate;
    }

    public void setCusDate(Date cusDate) {
        this.cusDate = cusDate;
    }

    public BigInteger getcusFormSerialNo() {
        return cusFormSerialNo;
    }

    public void setcusFormSerialNo(BigInteger cusSerialNo) {
        this.cusFormSerialNo = cusSerialNo;
    }

    public String getCusApplicantCategory() {
        return cusApplicantCategory;
    }

    public void setCusApplicantCategory(String cusApplicantCategory) {
        this.cusApplicantCategory = cusApplicantCategory;
    }

    public String getCusApplicantResidence() {
        return cusApplicantResidence;
    }

    public void setCusApplicantResidence(String cusApplicantResidence) {
        this.cusApplicantResidence = cusApplicantResidence;
    }

    public String getCusTaxExmpt() {
        return cusTaxExmpt;
    }

    public void setCusTaxExmpt(String cusTaxExmpt) {
        this.cusTaxExmpt = cusTaxExmpt;
    }

    public String getCusOtherNames() {
        return cusOtherNames;
    }

    public void setCusOtherNames(String cusOtherNames) {
        this.cusOtherNames = cusOtherNames;
    }

    public String getCusIdNo() {
        return cusIdNo;
    }

    public void setCusIdNo(String cusIdNo) {
        this.cusIdNo = cusIdNo;
    }

    public String getCusJointSurname() {
        return cusJointSurname;
    }

    public void setCusJointSurname(String cusJointSurname) {
        this.cusJointSurname = cusJointSurname;
    }

    public String getCusJointOthernames() {
        return cusJointOthernames;
    }

    public void setCusJointOthernames(String cusJointOthernames) {
        this.cusJointOthernames = cusJointOthernames;
    }

    public String getCusJointApplicantIdNo() {
        return cusJointApplicantIdNo;
    }

    public void setCusJointApplicantIdNo(String cusJointApplicantIdNo) {
        this.cusJointApplicantIdNo = cusJointApplicantIdNo;
    }

    public String getCusCompanyName() {
        return cusCompanyName;
    }

    public void setCusCompanyName(String cusCompanyName) {
        this.cusCompanyName = cusCompanyName;
    }

    public String getCusCompanyRegNo() {
        return cusCompanyRegNo;
    }

    public void setCusCompanyRegNo(String cusCompanyRegNo) {
        this.cusCompanyRegNo = cusCompanyRegNo;
    }

    public Date getCusCompanyDateOfInc() {
        return cusCompanyDateOfInc;
    }

    public void setCusCompanyDateOfInc(Date cusCompanyDateOfInc) {
        this.cusCompanyDateOfInc = cusCompanyDateOfInc;
    }

    public String getCusNomineeName() {
        return cusNomineeName;
    }

    public void setCusNomineeName(String cusNomineeName) {
        this.cusNomineeName = cusNomineeName;
    }

    public BigInteger getCusNomineeAc() {
        return cusNomineeAc;
    }

    public void setCusNomineeAc(BigInteger cusNomineeAc) {
        this.cusNomineeAc = cusNomineeAc;
    }

    public String getCusPostalCode() {
        return cusPostalCode;
    }

    public void setCusPostalCode(String cusPostalCode) {
        this.cusPostalCode = cusPostalCode;
    }

    public String getCusStreet() {
        return cusStreet;
    }

    public void setCusStreet(String cusStreet) {
        this.cusStreet = cusStreet;
    }

    public String getCusTelNo() {
        return cusTelNo;
    }

    public void setCusTelNo(String cusTelNo) {
        this.cusTelNo = cusTelNo;
    }

    public String getCusFaxNo() {
        return cusFaxNo;
    }

    public void setCusFaxNo(String cusFaxNo) {
        this.cusFaxNo = cusFaxNo;
    }

    public String getCusApplicantType() {
        return cusApplicantType;
    }

    public void setCusApplicantType(String cusApplicantType) {
        this.cusApplicantType = cusApplicantType;
    }

    public Users getCusInputter() {
        return cusInputter;
    }

    public void setCusInputter(Users cusInputter) {
        this.cusInputter = cusInputter;
    }

    public Users getCusAuthoriser() {
        return cusAuthoriser;
    }

    public void setCusAuthoriser(Users cusAuthoriser) {
        this.cusAuthoriser = cusAuthoriser;
    }

    public Brokers getCusBrkCode() {
        return cusBrkCode;
    }

    public void setCusBrkCode(Brokers cusBrkCode) {
        this.cusBrkCode = cusBrkCode;
    }
    
    public String getCusCountry() {
		return cusCountry;
	}

	public void setCusCountry(String cusCountry) {
		this.cusCountry = cusCountry;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cusPalCode != null ? cusPalCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        if ((this.cusPalCode == null && other.cusPalCode != null) || (this.cusPalCode != null && !this.cusPalCode.equals(other.cusPalCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Customers[ cusPalCode=" + cusPalCode + " ]";
    }
    
}
