
import java.lang.reflect.*;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;

public class CopyUtils {

    public static <T> T deepCopy(T obj) throws Exception{
        Map<Object, Object> clonedObjects = new IdentityHashMap<>();
        return deepCopy(obj, clonedObjects);
    }
    public static <T> T deepCopy(T obj, Map<Object, Object> clonedObjects) throws Exception {
        if(obj == null || isPrimitiveOrWrapper(obj.getClass())){
            return obj;
        }
        if(clonedObjects.containsKey(obj)){
            return (T) clonedObjects.get(obj);
        }
        Class<?> clazz = obj.getClass();
        T clone;
        if (clazz.isArray()) {
            int length = Array.getLength(obj);
            clone = (T) Array.newInstance(clazz.getComponentType(), length);
            clonedObjects.put(obj, clone);
            for (int i = 0; i < length; i++) {
                Array.set(clone, i, deepCopy(Array.get(obj, i), clonedObjects));
            }
        } else if (Collection.class.isAssignableFrom(clazz)) {
            clone = (T) ((Collection<?>) obj).getClass().getDeclaredConstructor().newInstance();
            clonedObjects.put(obj, clone);
            for (Object item : (Collection<?>) obj) {
                ((Collection<Object>) clone).add(deepCopy(item, clonedObjects));
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            clone = (T) ((Map<?, ?>) obj).getClass().getDeclaredConstructor().newInstance();
            clonedObjects.put(obj, clone);
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) obj).entrySet()) {
                ((Map<Object, Object>) clone).put(deepCopy(entry.getKey(), clonedObjects), deepCopy(entry.getValue(), clonedObjects));
            }
        } else {
            clone = (T) createNewInstance(obj);
            clonedObjects.put(obj, clone);
            for (Field field : clazz.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    field.set(clone, deepCopy(field.get(obj), clonedObjects));
                }
            }
        }

        return clone;
    }

    private static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return clazz.isPrimitive() ||
                clazz == String.class ||
                clazz == Boolean.class ||
                clazz == Integer.class ||
                clazz == Character.class ||
                clazz == Byte.class ||
                clazz == Short.class ||
                clazz == Double.class ||
                clazz == Long.class ||
                clazz == Float.class;
    }

    private static <T> T createNewInstance(T obj) {
        if(hasDefoultConstructor(obj)){
            try {
                return (T)obj.getClass().getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.out.println("Exception on create instance");
            }
        } else {
            Constructor<?> constructor = obj.getClass().getConstructors()[0];
            Object[] parameters = new Object[constructor.getParameterCount()];
            int pi = 0;
            for(Class<?> cl : constructor.getParameterTypes()){
                parameters[pi] = defoultValue(cl);
                pi++;
            }
            try {
                return  (T)constructor.newInstance(parameters);
            } catch (Exception e) {
                System.out.println("Exception on create instance");
            }
        }
        return null;
    }

    private static Object defoultValue(Class<?> cl) {
        if (isPrimitiveOrWrapper(cl)) {
            switch (cl.getName()) {
                case "java.lang.String":
                    return "";
                case "int":
                case "short":
                case "byte":
                case "java.lang.Integer":
                case "java.lang.Short":
                case "java.lang.Byte":
                    return 0;
                case "long":
                case "java.lang.Long":
                    return 0L;
                case "float":
                case "java.lang.Float":
                    return 0.0f;
                case "java.lang.Double":
                case "double":
                    return 0.0d;
                case "boolean":
                case "java.lang.Boolean":
                    return false;
            }
        }
        return null;
    }


    private static <T> boolean hasDefoultConstructor(T obj) {
        boolean hasDefaultConstructor = false;
        Constructor<?>[] constructors = obj.getClass().getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                hasDefaultConstructor = true;
                break;
            }
        }
        return hasDefaultConstructor;
    }

}
