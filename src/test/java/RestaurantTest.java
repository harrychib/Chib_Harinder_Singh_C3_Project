import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setUp() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Harry's Restrau", "Dubai", openingTime, closingTime);
        restaurant.addDefaultMenuItems();
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        // Arrange
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime validTime = LocalTime.parse("12:05:00");

        // Act
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(validTime);
        boolean isRestaurantOpen = spyRestaurant.isRestaurantOpen();

        // Assert
        assertTrue(isRestaurantOpen);

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        // Arrange
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime invalidTime = LocalTime.parse("23:00:00");

        // Act
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(invalidTime);
        boolean isRestaurantOpen = spyRestaurant.isRestaurantOpen();

        // Assert
        assertFalse(isRestaurantOpen);


    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        // Arrange
        int initialMenuSize = restaurant.getMenu().size();

        // Act
        restaurant.addToMenu("Sizzling brownie", 319);

        // Assert
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        // Arrange
        int initialMenuSize = restaurant.getMenu().size();

        // Act
        restaurant.removeFromMenu("Vegetable lasagne");

        // Assert
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void getting_order_cost_should_return_sum_of_selected_menu_items_price() {
        // Arrange
        List<String> selectedMenuItems = Arrays.asList("Sweet Corn Soup", "Sizzling Brownie");

        // Act
        int orderCost = restaurant.getOrderCost(selectedMenuItems);

        // Assert
        assertEquals(438, orderCost);
    }
}
