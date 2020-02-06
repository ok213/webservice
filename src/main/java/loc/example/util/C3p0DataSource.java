package loc.example.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3p0DataSource {

    private static final String sqlUser = "***";
    private static final String sqlUserPassword = "***";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/task_webservice";

    private static ComboPooledDataSource cpds = new ComboPooledDataSource();

    static {
        try {
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
            cpds.setJdbcUrl(jdbcUrl);
            cpds.setUser(sqlUser);
            cpds.setPassword(sqlUserPassword);
            cpds.setFactoryClassLocation("org.apache.naming.factory.BeanFactory");
            cpds.setMaxPoolSize(50);
            cpds.setMinPoolSize(0);
            cpds.setMaxConnectionAge(100);
            cpds.setAcquireRetryAttempts(60);
            cpds.setAcquireIncrement(1);
            cpds.setPreferredTestQuery("SELECT 1");
            cpds.setTestConnectionOnCheckout(true);
        } catch (PropertyVetoException e) {
            // handle the exception
        }
    }

    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }

    private C3p0DataSource(){}
}
