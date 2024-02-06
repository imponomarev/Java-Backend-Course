package edu.hw10.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task1Test {

    @Test
    void testNextObjectWithNonRecord() {


        RandomObjectGenerator generator = new RandomObjectGenerator();

        MyClass result = generator.nextObject(MyClass.class);


        assertNotNull(result);
        assertNotNull(result.getName());
        assertTrue(result.getName().length() > 0);
        assertTrue(result.getAge() >= 1);
        assertTrue(result.getSalary() <= 100);

    }



    @Test
    void testNextObjectWithNonRecordWithFactoryMethod() {

        RandomObjectGenerator generator = new RandomObjectGenerator();

        MyClass result = generator.nextObject(MyClass.class, "createInstance");


        assertNotNull(result);
        assertNotNull(result.getName());
        assertTrue(result.getName().length() > 0);
        assertTrue(result.getAge() >= 0);
        assertTrue(result.getSalary() <= 45000);

    }


    @Test
    void testNextObjectWithRecord() {

        RandomObjectGenerator generator = new RandomObjectGenerator();

        MyClazz result = generator.nextObject(MyClazz.class);

        assertNotNull(result);
        assertNotNull(result.name());
        assertTrue(result.name().length() > 0);
        assertTrue(result.age() >= 1);
        assertTrue(result.salary() <= 50000);

    }

    @Test
    void testNextObjectWithRecordWithFactoryMethod() {

        RandomObjectGenerator generator = new RandomObjectGenerator();

        MyClazz result = generator.nextObject(MyClazz.class, "create");

        assertNotNull(result);
        assertNotNull(result.name());
        assertTrue(result.name().length() > 0);
        assertTrue(result.age() >= 1);
        assertTrue(result.salary() <= 50000);

    }
}
