import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PatientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hospital_admin_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM patients *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Patient_instantiatesCorrectly_true() {
    Patient myPatient = new Patient("Person");
    assertEquals(true, myPatient instanceof Patient);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Patient.all().size(), 0);
  }

  @Test
  public void getName_patientInstantiatesWithName_String() {
    Patient myPatient = new Patient("Person");
    assertEquals("Person", myPatient.getName());
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Patient firstPatient = new Patient("Bub");
    Patient secondPatient = new Patient("Bub");
    assertTrue(firstPatient.equals(secondPatient));
  }

  @Test
  public void save_assignsIdToObject() {
    Patient myPatient = new Patient("Bub");
    myPatient.save();
    Patient savedPatient = Patient.all().get(0);
    assertEquals(myPatient.getId(), savedPatient.getId());
  }
}
