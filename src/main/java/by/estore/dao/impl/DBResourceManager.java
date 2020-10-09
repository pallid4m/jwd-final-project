package by.estore.dao.impl;

import java.util.ResourceBundle;

public final class DBResourceManager {
    private static final DBResourceManager instance = new DBResourceManager();

    private final ResourceBundle bundle = ResourceBundle.getBundle("db");

    private DBResourceManager() {}

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
