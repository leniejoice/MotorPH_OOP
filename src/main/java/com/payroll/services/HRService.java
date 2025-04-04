/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.payroll.services;
import com.payroll.domain.Person;
import com.payroll.domain.Employee;
import com.payroll.domain.IT;
import com.payroll.subdomain.EmployeePosition;
import com.payroll.subdomain.EmployeeStatus;
import com.payroll.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author leniejoice
 */
public class HRService {
    
    private Connection connection;
    private DatabaseConnection dbConnection;
    
    public HRService(DatabaseConnection dbConnection){
        this.connection = dbConnection.connect();   
    }

    public HRService() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Person getByEmpID(int empID){
        return getByEmpID(empID,true);
    } 
    
    public Person getByEmpID(int empID, boolean fetchSupervisor){
        Person employeeDetails = null ;
        if (connection != null) {
            String Query = "SELECT * FROM employee where employee_id = ?";
            ResultSet resultSet = null;
            PreparedStatement preparedStatement =null;
            try {
                preparedStatement = connection.prepareStatement(Query);
                preparedStatement.setInt(1,empID);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    employeeDetails = toEmployeeDetails(resultSet, fetchSupervisor);
                    
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }   
        }                          
        return employeeDetails;
    }
    
    public EmployeePosition getPositionById(int id){
        EmployeePosition empPosition = null ;
        if (connection != null) {
            String Query = "SELECT * FROM public.employee_position where id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Query);
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                empPosition = new EmployeePosition();
                while (resultSet.next()) {
                    empPosition.setId(resultSet.getInt("id"));
                    empPosition.setPosition(resultSet.getString("position"));
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }        
        }                          
        return empPosition;
    }
    
    public void updateEmployeeCredentials(IT empAccount){
        if(connection !=null){
            String Query = "UPDATE public.employee_account SET username = ?, password = ? WHERE employee_id = ?";
        
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(Query);
                preparedStatement.setString(1,empAccount.getEmpUserName());
                preparedStatement.setString(2,empAccount.getEmpPassword());
                preparedStatement.setInt(3,empAccount.getEmpID());
                

                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }                                   
        }
    }
    
    public EmployeeStatus getStatusById(int id){
        EmployeeStatus empStatus = null ;
        if (connection != null) {
            String Query = "SELECT * FROM public.employee_status where id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Query);
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                empStatus = new EmployeeStatus();
                while (resultSet.next()) {
                    empStatus.setId(resultSet.getInt("id"));
                    empStatus.setStatus(resultSet.getString("status"));
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }           
        }                          
        return empStatus;
    }
    
    public EmployeePosition loadPositionData(JComboBox<String> positionComboBox){
        return null;
     
    }
    
    public Person updateEmployeeDetails(Person empDetails){
        java.sql.Date birthDate = empDetails.getEmpBirthday()!=null? new java.sql.Date(empDetails.getEmpBirthday().getTime()):null;
        Integer superVisorId = empDetails.getEmpImmediateSupervisor() != null ? empDetails.getEmpImmediateSupervisor().getEmpID() : null;
        Integer positionId = empDetails.getEmpPosition() != null ? empDetails.getEmpPosition().getId() : null;
        Integer statusId = empDetails.getEmpStatus() != null ? empDetails.getEmpStatus().getId() : null;

            if (connection != null) {
            String Query = "UPDATE public.employee \n"
                    + "SET \n"
                    + "    lastname = ?,\n"
                    + "    firstname = ?,\n"
                    + "    birthday = ?,\n"
                    + "    address = ?,\n"
                    + "    phone_number = ?,\n"
                    + "    sss = ?,\n"
                    + "    philhealth = ?,\n"
                    + "    tin = ?,\n"
                    + "    pag_ibig = ?,\n"
                    + "    status = ?,\n"
                    + "    position = ?,\n"
                    + "    immediate_supervisor = ?,\n"
                    + "    basic_salary = ?,\n"
                    + "    rice_subsidy = ?,\n"
                    + "    phone_allowance = ?,\n"
                    + "    clothing_allowance = ?,\n"
                    + "    gross_semi_monthly_rate = ?,\n"
                    + "    hourly_rate = ?\n"
                    + "WHERE employee_id = ?";
            
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Query);
                preparedStatement.setString(1,empDetails.getLastName());
                preparedStatement.setString(2,empDetails.getFirstName());
                preparedStatement.setDate(3,birthDate); 
                preparedStatement.setString(4,empDetails.getEmpAddress());
                preparedStatement.setString(5,empDetails.getEmpPhoneNumber());
                preparedStatement.setString(6,empDetails.getEmpSSS());
                preparedStatement.setLong(7,empDetails.getEmpPhilHealth());
                preparedStatement.setString(8,empDetails.getEmpTIN());
                preparedStatement.setLong(9,empDetails.getEmpPagibig());
                preparedStatement.setInt(10, statusId);
                preparedStatement.setInt(11, positionId);
                preparedStatement.setInt(12, superVisorId);
                preparedStatement.setDouble(13,empDetails.getEmpBasicSalary());
                preparedStatement.setDouble(14,empDetails.getEmpRice());
                preparedStatement.setDouble(15,empDetails.getEmpPhone());
                preparedStatement.setDouble(16,empDetails.getEmpClothing());
                preparedStatement.setDouble(17,empDetails.getEmpMonthlyRate());
                preparedStatement.setDouble(18,empDetails.getEmpHourlyRate());
                preparedStatement.setInt(19, empDetails.getEmpID());
                
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }         
        }                          
        return empDetails;
    }
    
    public boolean isDuplicateEmployee(Person empDetails) {
        String query = """
            SELECT employee_id FROM public.employee
            WHERE lastname = ? AND firstname = ? AND birthday = ? AND address = ? AND phone_number = ?
            AND sss = ? AND philhealth = ? AND tin = ? AND pag_ibig = ? AND status = ? AND position = ? AND immediate_supervisor = ?
        """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, empDetails.getLastName());
            preparedStatement.setString(2, empDetails.getFirstName());
            preparedStatement.setDate(3, new java.sql.Date(empDetails.getEmpBirthday().getTime()));
            preparedStatement.setString(4, empDetails.getEmpAddress());
            preparedStatement.setString(5, empDetails.getEmpPhoneNumber());
            preparedStatement.setString(6, empDetails.getEmpSSS());
            preparedStatement.setLong(7, empDetails.getEmpPhilHealth());
            preparedStatement.setString(8, empDetails.getEmpTIN());
            preparedStatement.setLong(9, empDetails.getEmpPagibig());
            preparedStatement.setObject(10, empDetails.getEmpStatus().getId(), Types.INTEGER);
            preparedStatement.setObject(11, empDetails.getEmpPosition().getId(), Types.INTEGER);
            preparedStatement.setObject(12, empDetails.getEmpImmediateSupervisor().getEmpID(), Types.INTEGER);

            try (ResultSet resultset = preparedStatement.executeQuery()) {
                return resultset.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Integer getEmployeeIdIfExists(Person empDetails) {
        String query = """
            SELECT employee_id FROM public.employee
            WHERE lastname = ? AND firstname = ? AND birthday = ? AND address = ? AND phone_number = ?
            AND sss = ? AND philhealth = ? AND tin = ? AND pag_ibig = ? AND status = ? AND position = ? AND immediate_supervisor = ?
        """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, empDetails.getLastName());
            preparedStatement.setString(2, empDetails.getFirstName());
            preparedStatement.setDate(3, new java.sql.Date(empDetails.getEmpBirthday().getTime()));
            preparedStatement.setString(4, empDetails.getEmpAddress());
            preparedStatement.setString(5, empDetails.getEmpPhoneNumber());
            preparedStatement.setString(6, empDetails.getEmpSSS());
            preparedStatement.setLong(7, empDetails.getEmpPhilHealth());
            preparedStatement.setString(8, empDetails.getEmpTIN());
            preparedStatement.setLong(9, empDetails.getEmpPagibig());
            preparedStatement.setObject(10, empDetails.getEmpStatus().getId(), Types.INTEGER);
            preparedStatement.setObject(11, empDetails.getEmpPosition().getId(), Types.INTEGER);
            preparedStatement.setObject(12, empDetails.getEmpImmediateSupervisor().getEmpID(), Types.INTEGER);

            try (ResultSet resultset = preparedStatement.executeQuery()) {
                if (resultset.next()) {
                    return resultset.getInt("employee_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }    
    
    public Person addEmployeeDetails(Person empDetails){
            if (connection == null)return null; 
            
            /*if (isDuplicateEmployee(empDetails)) {
                JOptionPane.showMessageDialog(null, "Duplicate employee found. No insertion performed.", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                return null;
            }*/
            String Query = "INSERT into public.employee (lastname, firstname,birthday,address,phone_number,sss,philhealth,tin,pag_ibig,status,position,immediate_supervisor,basic_salary, rice_subsidy, phone_allowance, clothing_allowance, gross_semi_monthly_rate, hourly_rate)"
                    + "values(?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Query,Statement.RETURN_GENERATED_KEYS);
                java.sql.Date birthDate = empDetails.getEmpBirthday()!=null? new java.sql.Date(empDetails.getEmpBirthday().getTime()):null;
                Integer superVisorId = empDetails.getEmpImmediateSupervisor() != null ? empDetails.getEmpImmediateSupervisor().getEmpID() : null;
                Integer positionId = empDetails.getEmpPosition() != null ? empDetails.getEmpPosition().getId() : null;
                Integer statusId = empDetails.getEmpStatus() != null ? empDetails.getEmpStatus().getId() : null;
                preparedStatement.setString(1,empDetails.getLastName());
                preparedStatement.setString(2,empDetails.getFirstName());
                preparedStatement.setDate(3,birthDate);
                preparedStatement.setString(4,empDetails.getEmpAddress());
                preparedStatement.setString(5,empDetails.getEmpPhoneNumber());
                preparedStatement.setString(6,empDetails.getEmpSSS());
                preparedStatement.setLong(7,empDetails.getEmpPhilHealth());
                preparedStatement.setString(8,empDetails.getEmpTIN());
                preparedStatement.setLong(9,empDetails.getEmpPagibig());
                preparedStatement.setObject(10, statusId, Types.INTEGER);
                preparedStatement.setObject(11, positionId,Types.INTEGER);
                preparedStatement.setObject(12, superVisorId,Types.INTEGER);
                preparedStatement.setDouble(13, empDetails.getEmpBasicSalary());
                preparedStatement.setDouble(14, empDetails.getEmpRice());
                preparedStatement.setDouble(15, empDetails.getEmpPhone());
                preparedStatement.setDouble(16, empDetails.getEmpClothing());
                preparedStatement.setDouble(17, empDetails.getEmpMonthlyRate());
                preparedStatement.setDouble(18, empDetails.getEmpHourlyRate());
  
                int affectedrows = preparedStatement.executeUpdate();
                if(affectedrows > 0){
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            empDetails.setEmpID(generatedKeys.getInt(1));
                        } else {
                            throw new SQLException("Updating user failed, no ID obtained.");
                         }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }                              
        return empDetails;
    }
    
    public boolean deleteEmployeeDetails(int empID) {
        boolean isDeleted = false;

        if (connection != null) {
            String query = "DELETE FROM public.employee WHERE employee_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, empID);

                int affectedRows = preparedStatement.executeUpdate();
                
                if (affectedRows > 0) {
                    isDeleted = true;
                }
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }  
        }
        return isDeleted;
    }
    
    public List<Person> getAllEmployee(){
        List<Person> allEmployee = new ArrayList<>();
            if (connection != null) {
            String Query = "SELECT * FROM employee order by employee_id ASC";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Person employeeDetails = toEmployeeDetails(resultSet);
                    allEmployee.add(employeeDetails);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }         
        }                          
        return allEmployee;
 
    }
    
    public List<EmployeePosition> getAllPosition(){
        List<EmployeePosition> positions = new ArrayList<>();
        if (connection != null) {
        String Query = "SELECT * FROM employee_position";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    EmployeePosition position = new EmployeePosition();
                    position.setId(resultSet.getInt("id"));
                    position.setPosition(resultSet.getString("position"));
                    positions.add(position);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }         
        }                          
        return positions;
    }
    
     public List<EmployeeStatus> getAllStatuses(){
        List<EmployeeStatus> statuses = new ArrayList<>();
        if (connection != null) {
        String Query = "SELECT * FROM employee_status";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    EmployeeStatus status = new EmployeeStatus();
                    status.setId(resultSet.getInt("id"));
                    status.setStatus(resultSet.getString("status"));
                    statuses.add(status);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }        
        }                          
        return statuses;
    }
    
    
    public List<Employee> getEmpHoursByEmpID(int empID) {
        List<Employee> empHours = new ArrayList<>();

        if (connection == null) {
            System.err.println("Database connection is null. Cannot fetch employee hours.");
            return empHours;
        }

        String query = "SELECT * FROM employee_hours WHERE employee_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, empID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Employee e = new Employee(); 
                    e.setEmpID(resultSet.getInt("employee_id"));
                    e.setDate(resultSet.getDate("date"));
                    e.setAttendanceId(resultSet.getInt("id"));
                    e.setTimeIn(resultSet.getObject("time_in", LocalTime.class));
                    e.setTimeOut(resultSet.getObject("time_out", LocalTime.class));
                    empHours.add(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empHours;
    }
    
    private Person toEmployeeDetails(ResultSet resultSet, boolean fetchSupervisor) 
        throws SQLException {
        Person employeeDetails = new Employee();
            employeeDetails.setEmpID(resultSet.getInt("employee_id"));
            employeeDetails.setFirstName(resultSet.getString("firstname"));
            employeeDetails.setLastName(resultSet.getString("lastname"));
            employeeDetails.setEmpBirthday(resultSet.getDate("birthday"));
            employeeDetails.setEmpAddress(resultSet.getString("address"));
            employeeDetails.setEmpPhoneNumber(resultSet.getString("phone_number"));
            employeeDetails.setEmpSSS(resultSet.getString("sss"));
            employeeDetails.setEmpPhilHealth(resultSet.getLong("philhealth"));
            employeeDetails.setEmpTIN(resultSet.getString("tin"));
            employeeDetails.setEmpPagibig(resultSet.getLong("pag_ibig"));
            employeeDetails.setEmpBasicSalary(resultSet.getDouble("basic_salary"));
            employeeDetails.setEmpRice(resultSet.getDouble("rice_subsidy"));
            employeeDetails.setEmpPhone(resultSet.getDouble("phone_allowance"));
            employeeDetails.setEmpClothing(resultSet.getDouble("clothing_allowance"));
            employeeDetails.setEmpMonthlyRate(resultSet.getDouble("gross_semi_monthly_rate"));
            employeeDetails.setEmpHourlyRate(resultSet.getDouble("hourly_rate"));
            int superVisorId  = resultSet.getInt("immediate_supervisor");
            if (superVisorId > 0 && fetchSupervisor){
                Person superVisor = getByEmpID(superVisorId, false);
                employeeDetails.setEmpImmediateSupervisor(superVisor);
            }       
            int positionId  = resultSet.getInt("position");
            if (positionId > 0){
                EmployeePosition position = getPositionById(positionId);
                employeeDetails.setEmpPosition(position);
            }    
            int statusId  = resultSet.getInt("status");
            if (statusId > 0){
                EmployeeStatus status = getStatusById(statusId);
                employeeDetails.setEmpStatus(status);
            }
        return employeeDetails;
    }
    private Person toEmployeeDetails(ResultSet resultSet) 
        throws SQLException {
        return toEmployeeDetails(resultSet, true);
    }
        
}