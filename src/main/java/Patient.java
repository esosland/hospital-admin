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
      return this.getName().equals(newPatient.getName());
    }
  }
}
