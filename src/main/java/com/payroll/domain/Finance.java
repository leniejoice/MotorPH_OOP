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
public class Finance extends Person {
    
    private double empBasicSalary;
    private double empRice;
    private double empPhone;
    private double empClothing;
    private double empMonthlyRate;
    private double empHourlyRate;
    
    
    public Finance() {
        super(0, "", "", "", null, "", "", "", 0, 0, null, null, null); 
        // Initialize `Person` fields with default values
    }
    
    public Finance(int empID, String lastName, String firstName, String empAddress, Date empBirthday,
                   String empPhoneNumber, String empSSS, String empTIN, long empPhilHealth,
                   long empPagibig, Person empImmediateSupervisor, EmployeeStatus empStatus,
                   EmployeePosition empPosition, double empBasicSalary, double empRice,
                   double empPhone, double empClothing, double empMonthlyRate, double empHourlyRate) {
        // Call Parent (Person) Constructor
        super(empID, lastName, firstName, empAddress, empBirthday, empPhoneNumber, empSSS, 
              empTIN, empPhilHealth, empPagibig, empImmediateSupervisor, empStatus, empPosition);
        
        // Initialize Finance-specific payroll details
        this.empBasicSalary = empBasicSalary;
        this.empRice = empRice;
        this.empPhone = empPhone;
        this.empClothing = empClothing;
        this.empMonthlyRate = empMonthlyRate;
        this.empHourlyRate = empHourlyRate;
    }
    
    
    public double getEmpBasicSalary() {
        return empBasicSalary;
    }

    public double getEmpRice() {
        return empRice;
    }

    public double getEmpPhone() {
        return empPhone;
    }

    public double getEmpClothing() {
        return empClothing;
    }

    public double getEmpMonthlyRate() {
        return empMonthlyRate;
    }

    public double getEmpHourlyRate() {
        return empHourlyRate;
    }
    
    public void setEmpBasicSalary(double empBasicSalary) {
        this.empBasicSalary = empBasicSalary;
    }

    public void setEmpRice(double empRice) {
        this.empRice = empRice;
    }

    public void setEmpPhone(double empPhone) {
        this.empPhone = empPhone;
    }

    public void setEmpClothing(double empClothing) {
        this.empClothing = empClothing;
    }

    public void setEmpMonthlyRate(double empMonthlyRate) {
        this.empMonthlyRate = empMonthlyRate;
    }

    public void setEmpHourlyRate(double empHourlyRate) {
        this.empHourlyRate = empHourlyRate;
    }
   
}
