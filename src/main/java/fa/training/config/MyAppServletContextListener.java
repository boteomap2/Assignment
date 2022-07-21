package fa.training.config;

import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.PooledDataSource;
import java.sql.SQLException;
import java.util.Set;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyAppServletContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        HibernateConfig.getSessionFactory().close();

        Set<PooledDataSource> pooledDataSourceSet = (Set<PooledDataSource>) C3P0Registry.getPooledDataSources();

        for (PooledDataSource dataSource : pooledDataSourceSet) {
            try {
                dataSource.hardReset();
                dataSource.close();
            } catch (SQLException e) {
                // note - do not use log4j since it may have been unloaded by this point
                System.out.println("Unable to hard reset and close data source." + e);
            }
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("ServletContextListener started");
        HibernateConfig.getSessionFactory();
    }
}
