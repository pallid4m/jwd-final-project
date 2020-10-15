import by.estore.dao.impl.DBResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

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

    @Test
    public void bCryptTest() {
        String password = "123";

        String salt = BCrypt.gensalt();
        String hashed = BCrypt.hashpw(password, salt);

        String pw = salt + hashed + "$" + salt.length();
        int index = pw.lastIndexOf('$');
        int len = Integer.parseInt(pw.substring(index + 1));
        String salt1 = pw.substring(0, len);
        String hashed1 = pw.substring(len, index);

        logger.debug(salt);
        logger.debug(hashed);
        logger.debug(pw);
        logger.debug(BCrypt.checkpw(password, hashed));
        logger.debug(salt1);
        logger.debug(hashed1);
    }
}
