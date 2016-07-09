package configs;

import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

@Resource.File("src\\test\\resources\\base.properties")
public interface TestConfig {

    @Property("baseTest.url")
    String getBaseUrl();
}
