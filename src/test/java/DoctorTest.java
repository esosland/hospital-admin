import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class DoctorTest {

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
  public void Doctor_instantiatesCorrectly_true() {
    Doctor myDoctor = new Doctor("Doctor Smith");
    assertEquals(true, myDoctor instanceof Doctor);
  }

  @Test
  public void getName_taskInstantiatesWithDescription_String() {
    Doctor myDoctor = new Doctor("Doctor Smith");
    assertEquals("Doctor Smith", myDoctor.getName());
  }
}
