import by.estore.dao.impl.pool.DBResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataCustomTest {
    private static final Logger logger = LogManager.getLogger(DataCustomTest.class);

    @Test
    public void loadDBBundleTest() {
        String user = DBResourceManager.getInstance().getValue("db.user");
        assertEquals("root", user);
    }
}
