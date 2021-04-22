 
package models;

 
public class PatientVisit {
    private int id;
     private String patient, department;
     private String visitor, phone , visitdate;

    public PatientVisit() {
    }

    public PatientVisit(int id, String patient, String department, String visitor, String phone, String visitdate) {
        this.id = id;
        this.patient = patient;
        this.department = department;
        this.visitor = visitor;
        this.phone = phone;
        this.visitdate = visitdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(String visitdate) {
        this.visitdate = visitdate;
    }

    @Override
    public String toString() {
        return "PatientVisit{" + "id=" + id + ", patient=" + patient + ", department=" + department + ", visitor=" + visitor + ", phone=" + phone + ", visitdate=" + visitdate + '}';
    }
     
     
}
