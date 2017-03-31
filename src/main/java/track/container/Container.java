package track.container;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;
import track.container.config.Bean;
import track.container.config.Property;
import track.container.config.ValueType;


/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class  Container {

    private List<Bean> beans;
    private Map<String, Object> objByName;
    private Map<String, Object> objByClassName;
    // Реализуйте этот конструктор, используется в тестах!

    public Container(List<Bean> beans) {
        this.beans = beans;
        objByName = new HashMap<String, Object>();
        objByClassName = new HashMap<String, Object>();
    }

    private Object getType(String value, Type type) {
        if (type == Integer.TYPE) {
            return Integer.parseInt(value);
        }
        if (type == Double.TYPE) {
            return Double.parseDouble(value);
        }
        if (type == Boolean.TYPE) {
            return Boolean.parseBoolean(value);
        }
        if (type == Long.TYPE) {
            return Long.parseLong(value);
        }
        if (type == Short.TYPE) {
            return Short.parseShort(value);
        }
        if (type == Byte.TYPE) {
            return Byte.parseByte(value);
        }
        return value;
    }

    private Object getObject(Bean bean) throws ReflectiveOperationException {
        Class<?> clazz = Class.forName(bean.getClassName());
        Object obj = clazz.newInstance();
        objByName.put(bean.getId(), obj);
        objByClassName.put(bean.getClassName(), obj);
        for (Map.Entry<String, Property> entry : bean.getProperties().entrySet()) {
            Class<?> type;
            Object value;
            if (entry.getValue().getType().equals(ValueType.VAL)) {
                type = clazz.getDeclaredField(entry.getValue().getName()).getType();
                value = getType(entry.getValue().getValue(), type);
            } else {
                value = getById(entry.getValue().getValue());
                type = value.getClass();
            }
            Method method = clazz.getMethod("set" +
                    entry.getKey().substring(0, 1).toUpperCase() +
                    entry.getKey().substring(1), type);
            method.invoke(obj, value);
        }
        return obj;
    }


    /**
     *  Вернуть объект по имени бина из конфига
     *  Например, Car car = (Car) container.getById("carBean")
     */

    public Object getById(String id) throws ReflectiveOperationException {
        Object object = objByName.get(id);
        if (object == null) {
            for (Bean bean : beans) {
                if (bean.getId().equals(id)) {
                    object = getObject(bean);
                }
            }
        }
        return object;
    }

    /**
     * Вернуть объект по имени класса
     * Например, Car car = (Car) container.getByClass("track.container.beans.Car")
     */

    public Object getByClass(String className) throws ReflectiveOperationException {
        Object object = objByClassName.get(className);
        if (object == null) {
            for (Bean bean : beans) {
                if (bean.getClassName().equals(className)) {
                    object = getObject(bean);
                }
            }
        }
        return object;
    }
}
