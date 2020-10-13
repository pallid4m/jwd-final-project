import by.estore.dao.impl.DBResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.Base64;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CustomTest {
    private static final Logger logger = LogManager.getLogger(CustomTest.class);

    @Test
    public void codingTest() {
        String value = "123";
        byte[] encode = Base64.getEncoder().encode(value.getBytes());
        byte[] decode = Base64.getDecoder().decode(encode);
        logger.info(value);
        logger.info(new String(encode));
        logger.info(new String(decode));
    }

    @Test
    public void loadDBBundleTest() {
        String user = DBResourceManager.getInstance().getValue("db.user");
        assertEquals("root", user);
    }

    @Test
    public void uuidTest() {
        logger.info(UUID.randomUUID());
        logger.info(UUID.fromString("1-2-3-4-5"));
    }
}
