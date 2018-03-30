package org.bahmni.mart;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class CommonTestHelper {

    public static void setValuesForMemberFields(Object classInstance, String fieldName, Object valueForMemberField)
            throws NoSuchFieldException, IllegalAccessException {
        setField(classInstance, valueForMemberField, classInstance.getClass().getDeclaredField(fieldName));
    }

    public static void setValuesForSuperClassMemberFields(Object classInstance, String fieldName,
                  Object valueForMemberField) throws NoSuchFieldException, IllegalAccessException {
        Field field = classInstance.getClass().getSuperclass().getDeclaredField(fieldName);
        setField(classInstance, valueForMemberField, field);
    }

    private static void setField(Object classInstance, Object valueForMemberField, Field field)
            throws IllegalAccessException {
        field.setAccessible(true);
        field.set(classInstance, valueForMemberField);
    }

    public static void setValueForFinalStaticField(Class classInstance, String fieldName, Object valueForMemberField)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = classInstance.getDeclaredField(fieldName);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        setField(null, valueForMemberField, field);
    }
}
