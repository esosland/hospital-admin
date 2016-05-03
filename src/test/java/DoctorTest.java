import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class DoctorTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hospital_admin_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteDoctors = "DELETE FROM doctors *;";
      String deletePatients = "DELETE FROM patients *;";
      con.createQuery(deleteDoctors).executeUpdate();
      con.createQuery(deletePatients).executeUpdate();
    }
  }

  @Test
  public void Doctor_instantiatesCorrectly_true() {
    Doctor myDoctor = new Doctor("Doctor Smith");
    assertEquals(true, myDoctor instanceof Doctor);
  }

  @Test
  public void getName_doctorInstantiatesWithName_String() {
    Doctor myDoctor = new Doctor("Doctor Smith");
    assertEquals("Doctor Smith", myDoctor.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Doctor.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Doctor firstDoctor = new Doctor("Doctor Smith");
    Doctor secondDoctor = new Doctor("Doctor Smith");
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  // Don't forget .getKey() Query and true statement for sql to add id
  @Test
  public void save_savesIntoDatabase_true() {
    Doctor myDoctor = new Doctor("Doctor Smith");
    myDoctor.save();
    Doctor savedDoctor = Doctor.find(myDoctor.getId());
    assertTrue(myDoctor.equals(savedDoctor));
  }

  @Test
  public void getPatients_retrievesAllPatientsFromDatabase_patientsList() {
    Doctor myDoctor = new Doctor("Doctor Smith");
    myDoctor.save();
    Patient firstPatient = new Patient("Bub", myDoctor.getId());
    firstPatient.save();
    Patient secondPatient = new Patient("Bob", myDoctor.getId());
    secondPatient.save();
    Patient[] patients = new Patient[] { firstPatient, secondPatient };
    assertTrue(myDoctor.getPatients().containsAll(Arrays.asList(patients)));
  }
}
