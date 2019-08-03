package test.com.simulator.app.testutils;

import java.lang.reflect.Method;

public class TestUtils {

    public static Method getMethod(Class<?> classObj, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        try {
            method = classObj.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return method;
    }

    public static Object invokeMethod(Object object, Method method, Object... params) {
        try {
            return method.invoke(object, params);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

}
