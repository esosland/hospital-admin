import java.util.List;
import org.sql2o.*;
import java.time.LocalDateTime;

public class Patient {
  private int id;
  private String name;
  private LocalDateTime birthdate;
  private int doctor_id;

  public Patient(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Patient> all() {
    String sql = "SELECT id, name FROM patients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }

  @Override
  public boolean equals(Object otherPatient) {
    if (!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return this.getName().equals(newPatient.getName()) &&
              this.getId() == newPatient.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients (name, doctor_id) VALUES (:name, :doctor_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("doctor_id", this.doctor_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static Patient find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM patients WHERE id=:id";
    Patient patient = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Patient.class);
    return patient;
  }
}
}
