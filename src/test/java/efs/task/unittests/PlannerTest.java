package efs.task.unittests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class PlannerTest {
    Planner planner = new Planner();

    @BeforeEach
    public void reinit() {
        planner = new Planner();
    }

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void calculateDailyCaloriesDemand_shouldReturnCorrectValue_forEveryActivityLevel(ActivityLevel level) {
        //given
        User user = TestConstants.TEST_USER;
        Integer expected = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(level);

        //when
        Integer actual = planner.calculateDailyCaloriesDemand(user, level);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
     public void calculateDailyIntake_shouldReturnSameValueAsInTestConstants() {
        //given
        User user = TestConstants.TEST_USER;
        DailyIntake expected = TestConstants.TEST_USER_DAILY_INTAKE;

        //when
        DailyIntake actual = planner.calculateDailyIntake(user);

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getCalories(), actual.getCalories()),
                () -> Assertions.assertEquals(expected.getProtein(), actual.getProtein()),
                () -> Assertions.assertEquals(expected.getFat(), actual.getFat()),
                () -> Assertions.assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate())
        );
    }
}
