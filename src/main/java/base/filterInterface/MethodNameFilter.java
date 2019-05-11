package base.filterInterface;

/**
 * @author Dong
 * @since 1.0
 * @version 1.0
 *
 * createTime :
 */
public interface MethodNameFilter {
    default boolean isDoFilter(String methodName){
        return true;
    }
}
