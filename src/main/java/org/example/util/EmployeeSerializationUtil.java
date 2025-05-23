package org.example.util;
import org.example.data.employees.Employee;

import java.io.*;
import java.util.Set;

public class EmployeeSerializationUtil {
    public static void serializeEmployees(Set<Employee> employees, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(employees);
        }
    }

    @SuppressWarnings("unchecked")
    public static Set<Employee> deserializeEmployees(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Set<Employee>) ois.readObject();
        }
    }
}
