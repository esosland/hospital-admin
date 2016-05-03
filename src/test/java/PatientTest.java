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
      String sql = "DELETE FROM doctors *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Patient_instantiatesCorrectly_true() {
    Patient myPatient = new Patient("Person");
    assertEquals(true, myPatient instanceof Patient);
  }
}
