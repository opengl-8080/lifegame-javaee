package gl8080.lifegame.persistence;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;

@WebListener
public class DatabaseMigration implements ServletContextListener {
    
    @Inject
    private Logger logger;
    
    @Resource(lookup="java:app/lifegameDS")
    private DataSource ds;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("initialize database... {}", System.getProperty("user.dir"));
        
        Flyway flyway = new Flyway();
        flyway.setDataSource(this.ds);
        flyway.clean();
        flyway.migrate();
        
        logger.info("initialize database done.");
    }

    @Override public void contextDestroyed(ServletContextEvent sce) {}
}
