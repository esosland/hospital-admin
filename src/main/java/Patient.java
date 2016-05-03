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
}
