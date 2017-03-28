package track.container ;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import track.container.config.*;
import track.container.beans.*;
import  track.container.*;


/**
 * TODO: Реализовать
 */
public class JsonConfigReader implements ConfigReader {

    @Override
    public List<Bean> parseBeans(File configFile) throws InvalidConfigurationException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Root root = mapper.readValue(configFile, Root.class);
            return root.getBeans();
        } catch (IOException e) {
            throw new InvalidConfigurationException(e.getMessage());
        }
    }

}
