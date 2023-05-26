package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenHeightIsZero() {
        //given
        double weight = 70.0;
        double height = 0.0;

        //then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ParameterizedTest(name = "{0} weight")
    @ValueSource(doubles = {85.3, 87.8, 90.0})
    void shouldReturnTrue_whenBMIIsCorrect(double weight) {
        //given
        double height = 1.74;

        //when
        boolean result = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(result);
    }

    @ParameterizedTest(name = "{0} weight, {1} height")
    @CsvSource({"60.0, 2.0", "24.5, 1.35", "50.3, 1.68"})
    void isBMICorrect_shouldReturnFalse_forCsvSourceValues(double weight, double height) {
        //when
        boolean result = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(result);
    }

    @ParameterizedTest(name = "{0} weight, {1} height")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void isBMICorrect_shouldReturnFalse_forDataFromFile(double weight, double height) {
        //when
        boolean result = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(result);
    }

    @Test
    void findUserWithTheWorstBMI_shouldReturnProperUser_forListFromTestConstants() {
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;

        //when
        User actualValues = FitCalculator.findUserWithTheWorstBMI(users);
        double expectedWeight = 97.3;
        double expectedHeight = 1.79;

        //then
        assertAll(
                "Should return user with the worst BMI",
                () -> assertEquals(expectedWeight, actualValues.getWeight()),
                () -> assertEquals(expectedHeight, actualValues.getHeight())
        );
    }

    @Test
    void findUserWithTheWorstBMI_shouldReturnNull_forEmptyList() {
        //when
        User result = FitCalculator.findUserWithTheWorstBMI(Collections.emptyList());

        //then
        assertNull(result);
    }

    @Test
    void calculateBMIScore_shouldReturnCorrectList_forListFromTestConstants() {
        //given
        double[] expectedBMI = TestConstants.TEST_USERS_BMI_SCORE;

        //when
        double[] calculatedBMI = FitCalculator.calculateBMIScore(TestConstants.TEST_USERS_LIST);

        //then
        assertArrayEquals(expectedBMI, calculatedBMI);
    }
}