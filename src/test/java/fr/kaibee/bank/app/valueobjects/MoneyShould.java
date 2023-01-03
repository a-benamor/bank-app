package fr.kaibee.bank.app.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MoneyShould {
    @Test
    void have_the_right_amount_for_add_operations(){
        Money first = new Money(100.8723);
        Money second = new Money(222.7512);
        Money third = new Money(300);

        Money result = first.add(second).add(third);

        Assertions.assertEquals(new Money(623.62), result);
    }

    @Test
    void have_the_right_amount_for_subtract_operations(){
        Money first = new Money(500.8523);
        Money second = new Money(200.3333);
        Money third = new Money(100);

        Money result = first.subtract(second).subtract(third);

        Assertions.assertEquals(new Money(200.52), result);

    }

    @Test
    void have_the_right_amount_for_mixed_operations(){
        Money first = new Money(500.8523);
        Money second = new Money(200.3333);
        Money third = new Money(100);

        Money result = first.add(second).subtract(third);

        Assertions.assertEquals(new Money(601.18), result);
    }

    @Test
    void have_the_right_result_for_equal_method(){
        Money first = new Money(500);
        Money second = new Money(500.00);
        Money third = new Money(500.10);

        Assertions.assertEquals(first, second);
        Assertions.assertNotEquals(first, third);
    }

    @Test
    void have_the_right_result_for_is_less_than_zero_method(){
        Money first = new Money(-10);
        Money second = new Money(0.00);
        Money third = new Money(0);

        Assertions.assertTrue(first.isLessOrEqualToZero());
        Assertions.assertTrue(second.isLessOrEqualToZero());
        Assertions.assertTrue(third.isLessOrEqualToZero());
    }

    @Test
    void have_the_right_result_for_is_greater_than_method(){
        Money first = new Money(100.9);
        Money second = new Money(100.6);
        Money third = new Money(-10);

        Assertions.assertTrue(first.isGreaterThan(second));
        Assertions.assertTrue(first.isGreaterThan(third));
    }
}
