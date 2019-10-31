package io.dkz;

import javax.swing.text.MaskFormatter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Arrays;

public abstract class AbstractFileMapper<T> implements FileMapper<T> {

    private final Class<T> typeClass;

    protected AbstractFileMapper() {
        final Type actualTypeArgument = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.typeClass = (Class<T>) actualTypeArgument;
    }

    @Override
    public T ToType(String fileLine) throws ReflectiveOperationException {
        T obj = typeClass.getDeclaredConstructor().newInstance();
        Arrays.stream(obj.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(FieldMap.class))
                .forEach(field -> {
                    int start = field.getDeclaredAnnotation(FieldMap.class).offset();
                    int size = field.getDeclaredAnnotation(FieldMap.class).size();
                    field.setAccessible(Boolean.TRUE);

                    try {
                        final String substring = fileLine.substring(start, (start + size));
                        if (!field.getDeclaredAnnotation(FieldMap.class).mask().isBlank() && !field.getDeclaredAnnotation(FieldMap.class).mask().isEmpty()) {
                            MaskFormatter mask = new MaskFormatter(field.getDeclaredAnnotation(FieldMap.class).mask());
                            mask.setValueContainsLiteralCharacters(Boolean.FALSE);
                            field.set(obj, checkType(field.getType(), mask.valueToString(substring)));
                        } else {
                            field.set(obj, checkType(field.getType(), substring));
                        }
                    } catch (ParseException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        return obj;
    }


    private Object checkType(Class<?> clazz, String value) {
        if (clazz.isAssignableFrom(Double.class)) {
            return Double.parseDouble(value);
        }
        if (clazz.isAssignableFrom(Float.class)) {
            return Float.parseFloat(value);
        }
        if (clazz.isAssignableFrom(Long.class)) {
            return Long.parseLong(value);
        }
        if (clazz.isAssignableFrom(Integer.class)) {
            return Integer.parseInt(value);
        }
        if (clazz.isAssignableFrom(Short.class)) {
            return Short.parseShort(value);
        }
        if (clazz.isAssignableFrom(Byte.class)) {
            return Byte.parseByte(value);
        }
        return value;
    }

}