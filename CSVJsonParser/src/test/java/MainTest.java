import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

    @Test
    public void testListToJsonOneEmployee() {
        // given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Ivan", "Ivanov", "Russia", 30));

        // when
        String json = Main.listToJson(employees);

        // then
        Assertions.assertTrue(json.contains("Ivan"));
        Assertions.assertTrue(json.contains("Russia"));
    }
}
