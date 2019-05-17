package base.reflectionSupport;

import base.ReflectionSupport;
import base.entity.People;
import base.filters.MethodNameFilter;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author Dong
 * <p>
 * Class Name : TestGetMethodList
 * Create Time : 20:38
 * Create Date : 2019/5/11
 * Project : ReflectionSupport
 */

public class TestGetMethodList {

    @Test
    public void getObjectList() {
        List<String> list = ReflectionSupport.getMethodList(new Object(), null, false);
        System.out.println(list);
    }

    @Test
    public void getPeopleMethodList() {
        List<String> list = ReflectionSupport.getMethodList(new People(), null, false);
        System.out.println(list);
    }

    @Test
    public void getPeopleMethodListWithGetterAndSetter() {
        MethodNameFilter methodNameFilter = new MethodNameFilter() {
            @Override
            public boolean isDoFilter(Method methodName) {
                return methodName.getName().startsWith("set") || methodName.getName().startsWith("get");
            }
        };

        List<String> list = ReflectionSupport.getMethodList(new People(), methodNameFilter, true);
        System.out.println(list);
    }

    @Test
    public void getPeopleMethodListWithPublic() {
        MethodNameFilter methodNameFilter = new MethodNameFilter() {
            @Override
            public boolean isDoFilter(Method method) {
                return Modifier.isPublic(method.getModifiers());
            }
        };

        List<String> list = ReflectionSupport.getMethodList(new People(), methodNameFilter, true);
        System.out.println(list);
    }
}
