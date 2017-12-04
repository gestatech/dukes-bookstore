package be.gestatech.bookstore.service.config;

import org.omnifaces.persistence.datasource.PropertiesFileLoader;

import java.util.Map;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;
import static org.omnifaces.utils.properties.PropertiesUtils.loadPropertiesFromClasspath;
import static org.omnifaces.utils.properties.PropertiesUtils.loadXMLPropertiesStagedFromClassPath;


/**
 * Adaptor for the switchable datasource as defined in web.xml to be able to read properties from the
 * <p>
 * right file.
 */
public class DataSourceStagedPropertiesFileLoader implements PropertiesFileLoader {

    private static final Logger logger = getLogger(DataSourceStagedPropertiesFileLoader.class.getName());

    @Override
    public Map<String, String> loadFromFile(String fileName) {
        // Make sure we use the same names as the application settings are using
        Map<String, String> omniSettings = loadPropertiesFromClasspath("META-INF/omnifaces-settings");
        Map<String, String> dataSourceProperties = loadXMLPropertiesStagedFromClassPath(
            fileName,
            omniSettings.getOrDefault("stageSystemPropertyName", "omni.stage"),
            omniSettings.get("defaultStage")
        );

        logger.info("\n\nAbout to install DataSource. \n" +
            "Classname: " + dataSourceProperties.get("className") + "\n" +
            "URL: " + dataSourceProperties.getOrDefault("url", dataSourceProperties.get("URL") + "\n" +
            "See META-INF/conf/" + fileName + " for details. \n" + "\n\n"
            )
        );
        return dataSourceProperties;
    }
}
