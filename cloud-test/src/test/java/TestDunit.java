import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.unitils.UnitilsBlockJUnit4ClassRunner;
import org.unitils.dbunit.annotation.ExpectedDataSet;

/**
 * @author Administrator
 * @description TestDunit
 * @date 2020/3/16 11:26
 */
@RunWith(UnitilsBlockJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class TestDunit {
    @Test
//    @DataSest("date/")
    public void testLogin() {

    }

    @Test
    @ExpectedDataSet("/result_data/reg-user.xml")
    public void resultExpect(){

    }
}
