package data;

import org.testng.annotations.DataProvider;
import utils.Randomization;

public class StaticProvider {

    @DataProvider(name = "dataForLimitTest")
    public static Object[][] dataForLimitTest() {
        return new Object[][]{
                {Randomization.getRandomString(5), Randomization.getRandomString(2), "was created successfully"},
                {Randomization.getRandomString(5), Randomization.getRandomString(3), "was created successfully"},
                {Randomization.getRandomString(5), Randomization.getRandomString(9), "was created successfully"},
                {Randomization.getRandomString(5), Randomization.getRandomString(10), "was created successfully"},
                {Randomization.getRandomString(5), Randomization.getRandomString(11), "was created successfully"},
                {Randomization.getRandomString(5), Randomization.getRandomString(1), "The code must be at least 2 characters."},
        };
    }
}


