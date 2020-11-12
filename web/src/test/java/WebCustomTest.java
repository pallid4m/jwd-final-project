import by.estore.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.*;

public class WebCustomTest {
    private static final Logger logger = LogManager.getLogger(WebCustomTest.class);

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
    public void uuidTest() {
        logger.info(UUID.randomUUID());
        logger.info(UUID.fromString("1-2-3-4-5"));
    }
}
