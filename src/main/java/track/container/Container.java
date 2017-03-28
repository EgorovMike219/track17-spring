package track.container;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import track.container.config.Bean;
import track.container.config.Property;

import static track.container.config.ValueType.REF;
import static track.container.config.ValueType.VAL;


/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class  Container {

    private Map<String, Bean> beanByName;
    private Map<String, Bean> beanByClassName;
    private Map<String, Object> objByName;
    private Map<String, Object> objByClassName;
    // Реализуйте этот конструктор, используется в тестах!

    public Container(List<Bean> beans) {
        beanByName = new HashMap<String, Bean>(beans.size());
        beanByClassName = new HashMap<String, Bean>(beans.size());
        objByName = new HashMap<String, Object>(beans.size());
        objByClassName = new HashMap<String, Object>(beans.size());
        for (Bean bean: beans) {
            beanByName.put(bean.getId(), bean);
            beanByClassName.put(bean.getClassName(), bean);
        }
    }

    /**
     *  Вернуть объект по имени бина из конфига
     *  Например, Car car = (Car) container.getById("carBean")
     */
    public Object getById(String id) throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {
        Bean bean = beanByName.get(id);
        Class myClass = Class.forName(bean.getClassName());
        Object obj = myClass.newInstance();
        Method[] methods = myClass.getMethods();
        for (Map.Entry<String, Property> entry : bean.getProperties().entrySet()) {
            if (entry.getValue().getType() == VAL) {
                Method method = myClass.getMethod("set" +
                        entry.getValue().getName().substring(0,1).toUpperCase() +
                        entry.getValue().getName().substring(1));
                method.invoke(obj, entry.getValue().getValue());
            } else if (entry.getValue().getType() == REF) {
                parseRef(bean, entry, cls, object);
            }
        }
        objByName.put(id, obj);
        objByClassName.put(bean.getClassName(), obj);
        return obj;
    }

    /**
     * Вернуть объект по имени класса
     * Например, Car car = (Car) container.getByClass("track.container.beans.Car")
     */
    public Object getByClass(String className) {
        return objByClassName.get(className);
    }
}
