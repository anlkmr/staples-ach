package com.emagia.ach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Properties;

public class DBconfig {
    /**
     * Service to retrieve the properties stored in the DOMAIN_CONFIG table. This service can be autowired in any other service and its getProperty method can be invoked to get the property value.
     * The getProperty() method makes sure that all the properties from the database are loaded only once throughout the life time of the current application session.
     * @author Anil
     *
     */

    @Service("domainConfigService")
    public class DomainConfig {

       /* @Autowired
        @Qualifier("domainConfiguration")
         DatabaseConfiguration domainConfiguration;

        private Properties properties;

        public String getProperty(String key) {
            if (properties == null) {
                properties = ConfigurationConverter.getProperties(domainConfiguration);
            }
            return properties.getProperty(key);
        }*/
    }
//https://stackoverflow.com/questions/37113327/spring-boot-getting-scheduled-cron-value-from-database/49994397#49994397
    //https://stackoverflow.com/questions/37113327/spring-boot-getting-scheduled-cron-value-from-database/49994397#49994397
}
