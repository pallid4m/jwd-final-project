package by.estore.dao.impl;

import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.assertEquals;

public class SomeTest {

    @Test
    public void codingTest() {
        String value = "123";
        byte[] encode = Base64.getEncoder().encode(value.getBytes());
        byte[] decode = Base64.getDecoder().decode(encode);
        System.out.println(value);
        System.out.println(new String(encode));
        System.out.println(new String(decode));
    }

    @Test
    public void loadDBBundleTest() {
        String user = DBResourceManager.getInstance().getValue("db.user");
        assertEquals("root", user);
    }
}
