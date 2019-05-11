package base.filterInterface;

import java.lang.reflect.Field;

public interface FieldNameFilter {
    default boolean isDoFilter(Field field){
        return true;
    }
}
