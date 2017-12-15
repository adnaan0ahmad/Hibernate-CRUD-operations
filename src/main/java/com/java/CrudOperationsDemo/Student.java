package com.java.CrudOperationsDemo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author Adnaan
 *
 *
 */


@Entity
@Table(name = "StudentRecords")
public class Student implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SNo")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studId;
	
	@Column(name = "Name")
	private String studName;
	
	@Column (name = "Roll_Number")
	private int studRoll;
	
	@Column(name = "Address")
	private String studAddress;
	
	public Student(int studId, String studName, int studRoll, String studAddress) {
		super();
		this.studId = studId;
		this.studName = studName;
		this.studRoll = studRoll;
		this.studAddress = studAddress;
	}

	public Student() {
		super();
	}

	public int getStudId() {
		return studId;
	}

	public void setStudId(int studId) {
		this.studId = studId;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public int getStudRoll() {
		return studRoll;
	}

	public void setStudRoll(int studRoll) {
		this.studRoll = studRoll;
	}

	public String getStudAddress() {
		return studAddress;
	}

	public void setStudAddress(String studAddress) {
		this.studAddress = studAddress;
	}

	@Override
	public String toString() {
		return "Student [studId=" + studId + ", studName=" + studName + ", studRoll=" + studRoll + ", studAddress="
				+ studAddress + "]";
	}
	
	

}
