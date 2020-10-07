package by.estore.dao.impl;

import org.junit.Test;

import java.util.Base64;

public class SomeTest {

    @Test
    public void codingTest() {
        String password = "123";
        byte[] encode = Base64.getEncoder().encode(password.getBytes());
        byte[] decode = Base64.getDecoder().decode(encode);
        System.out.println(password);
        System.out.println(new String(encode));
        System.out.println(new String(decode));
    }
}
