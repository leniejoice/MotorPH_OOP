/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.payroll.domain;

import java.util.Date;

/**
 *
 * @author leniejoice
 */
public class IT extends Person{
    private int accountID;
    private String empUserName; 
    private String empPassword;
    private Person empDetails;
    private Finance payrollDetails;
    private UserRole userRole;
    private HR leaveDetail;

    // ✅ No-Args Constructor (Fixes NullPointerException for payrollDetails)
    public IT() {
        super(0, "", "", "", null, "", "", "", 0, 0, null, null, null);
        //this.payrollDetails = new Finance();
 
    }

    // ✅ Full Constructor
    public IT(int empID, String lastName, String firstName, String empAddress, Date empBirthday,
              String empPhoneNumber, String empSSS, String empTIN, long empPhilHealth,
              long empPagibig, Person empImmediateSupervisor, EmployeeStatus empStatus,
              EmployeePosition empPosition, int accountID, String empUserName, String empPassword,
              Person empDetails, Finance payrollDetails, UserRole userRole, HR leaveDetail) {
        super(empID, lastName, firstName, empAddress, empBirthday, empPhoneNumber, empSSS, 
              empTIN, empPhilHealth, empPagibig, empImmediateSupervisor, empStatus, empPosition);

        this.accountID = accountID;
        this.empUserName = empUserName;
        this.empPassword = empPassword;
        this.empDetails = empDetails;
        this.payrollDetails = payrollDetails; 
        this.userRole = userRole;
        this.leaveDetail = leaveDetail; // ✅ Prevent null issues
    }

    

    // ✅ Other Getters & Setters
    public int getAccountID() { 
        return accountID; }
    
    public void setAccountID(int accountID) { 
        this.accountID = accountID; }

    public String getEmpUserName() { 
        return empUserName; }
    
    public void setEmpUserName(String empUserName) { 
        this.empUserName = empUserName; }

    public String getEmpPassword() { 
        return empPassword; }
    
    public void setEmpPassword(String empPassword) { 
        this.empPassword = empPassword; }

    public Person getEmpDetails() { 
        return empDetails; }
    
    public void setEmpDetails(Person empDetails) { 
        this.empDetails = empDetails; }
    
    public Finance getPayrollDetails() {
    if (payrollDetails == null) {
        payrollDetails = new Finance(); // Prevent NullPointerException
    }
    return payrollDetails;
    }
    
    public void setPayrollDetails(Finance payrollDetails) {
        this.payrollDetails = (payrollDetails != null) ? payrollDetails : new Finance();
    }

    public UserRole getUserRole() { 
        return userRole; }
    
    public void setUserRole(UserRole userRole) { 
        this.userRole = userRole; }

    public HR getLeaveDetail() { 
        return leaveDetail; }
    
    public void setLeaveDetail(HR leaveDetail) { 
        this.leaveDetail = leaveDetail; }

}