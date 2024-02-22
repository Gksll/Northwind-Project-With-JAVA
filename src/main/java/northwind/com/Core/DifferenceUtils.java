package northwind.com.Core;

import northwind.com.Core.exceptions.BusinessException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Component
public class DifferenceUtils {

    public static <T> List<String> getDifferences(T oldEntity, T newEntity) {
        List<String> differences = new ArrayList<>();

        // Check for same class
        if (!oldEntity.getClass().equals(newEntity.getClass())) {
            throw new IllegalArgumentException("Objects must have the same class");
        }

        // Iterate over declared fields
        for (Field field : oldEntity.getClass().getDeclaredFields()) {
            try {
                // Make field accessible
                field.setAccessible(true);

                // Get values
                Object oldFieldValue = field.get(oldEntity);
                Object newFieldValue = field.get(newEntity);

                // Skip null fields
                if (oldFieldValue == null && newFieldValue == null) {
                    continue;
                }

                // Check for differences
                if (!Objects.equals(oldFieldValue, newFieldValue)) {
                    String formattedDifference = formatDifference(field.getName(), oldFieldValue, newFieldValue);
                    differences.add(formattedDifference);
                }
            } catch (IllegalAccessException e) {
                // Log or handle exception appropriately
                System.err.println("Error accessing field: " + field.getName() + ": " + e.getMessage());
            }
        }

        return differences;
    }

    private static String formatDifference(String fieldName, Object oldFieldValue, Object newFieldValue) {
        return String.format("- %s: Old value: %s, New value: %s",
                fieldName, oldFieldValue == null ? "null" : oldFieldValue.toString(), newFieldValue == null ? "null" : newFieldValue.toString());
    }
    public static <T> T getAndCheckNull(T value) {
        if (Objects.isNull(value)) {
            throw new BusinessException("There is NO PRODUCT!");
        }
        return value;
    }
}
