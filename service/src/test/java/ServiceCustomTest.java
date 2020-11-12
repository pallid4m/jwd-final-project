import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class ServiceCustomTest {
    private static final Logger logger = LogManager.getLogger(ServiceCustomTest.class);

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