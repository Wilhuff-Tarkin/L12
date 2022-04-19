package configuration.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import configuration.driver.Browser;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class YamlModel {

    @Getter
    @Setter
    @JsonProperty
    private Browser testedBrowser;

    @Getter
    @Setter
    @JsonProperty
    private String testedDataSet;

    @Setter
    @JsonAnyGetter
    private HashMap<String, EnvironmentModel> testData;


    public YamlModel() {
    }

    public YamlModel(Browser testedBrowser, String testedDataSet, HashMap<String, EnvironmentModel> testData) {
        this.testedBrowser = testedBrowser;
        this.testedDataSet = testedDataSet;
        this.testData = testData;
    }

    public HashMap<String, EnvironmentModel> getAllTestData() {
        return testData;
    }

    public HashMap<String, Object> getSpecificTestData(String testSetName) {
        return testData.get(testSetName).getTestPropertiesMap();
    }

}
