package io.github.mxudong.rs.packings.classes;

import io.github.mxudong.rs.exceptions.ReflectionException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * it is Sync ObjectFactory
 *
 * @author Dong
 * @since V 3.1.0
 *
 * @see ObjectFactory
 */

public class CurrentObjectFactory {

    private final static CurrentObjectFactory INSTANCE = new CurrentObjectFactory();

    private Map<String, ClassObject<?>> classObjectMap;

    private final static ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock(true);

    private CurrentObjectFactory(){
        classObjectMap = new ConcurrentHashMap<>();
    }

    public static CurrentObjectFactory getInstance() {
        return INSTANCE;
    }

    public ClassObject<?> getClassObject(Class<?> c){
        if(c == null){
            return null;
        }

        if(c.isAnnotation()){

        }

        READ_WRITE_LOCK.readLock().lock();
        try {
            if(classObjectMap.containsKey(c.getName())){
                return classObjectMap.get(c.getName());
            }
        }finally {
            READ_WRITE_LOCK.readLock().unlock();
        }

        READ_WRITE_LOCK.writeLock().lock();

        try {
            ClassObject<?> classObject = new ClassObject<>(c);
            classObjectMap.put(c.getName(), classObject);
            classObject.build();
            return classObject;
        }finally {
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }

    public AnnotationClass getAnnotationClass(Class<?> c){
        if(!c.isAnnotation()){
            try {
                throw new ReflectionException("ObjectFactory", "getAnnotationClass",
                        "the target class is not an annotation class, please use getClassObject(Class<?> c)");
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }

        READ_WRITE_LOCK.readLock().lock();
        try {
            if(classObjectMap.containsKey(c.getName())){
                return (AnnotationClass) classObjectMap.get(c.getName());
            }
        }finally {
            READ_WRITE_LOCK.readLock().unlock();
        }

        READ_WRITE_LOCK.writeLock().lock();
        try {
            AnnotationClass annotationClass = new AnnotationClass(c);
            classObjectMap.put(c.getName(), annotationClass);
            annotationClass.build();
            return annotationClass;
        }finally {
            READ_WRITE_LOCK.writeLock().unlock();
        }


    }
}
