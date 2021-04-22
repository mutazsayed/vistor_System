package models;

public class Visit {

    private int id,patientId;
    private String name, phone;

    public Visit() {
    }

    public Visit(int id, int patientId, String name, String phone ) {
        this.id = id;
        this.patientId = patientId;
        this.name = name;
        this.phone  = phone;
 
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone () {
        return phone ;
    }

    public void setPhone  (String phone  ) {
        this.phone = phone  ;
    }

   

}
