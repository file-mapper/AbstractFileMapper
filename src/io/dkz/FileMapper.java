package io.dkz;

public interface FileMapper<T> {

    T ToType(String fileLine) throws ReflectiveOperationException;
}
