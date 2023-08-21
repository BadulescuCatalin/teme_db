import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * clasa StudentRegister
 * fileName = fisierul in care scriu
 * students = lista de studenti
 * studentsRegister instanta unica de StudentRegister
 * os, is = outputStream si inputStream
 */

public class StudentsRegister {
    private static final String fileName = "students.data";
    private static StudentsRegister studentsRegister;
    private static List<Student> students;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    private StudentsRegister() {
        students = new ArrayList<>();
    }

    public static StudentsRegister getInstance() {
        if (studentsRegister == null) {
            studentsRegister = new StudentsRegister();
        }
        return studentsRegister;
    }

    // functie care imi adauga un student in lista
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * functia care imi sterge un student din lista
     *
     * @param index = indexul de la care vreau sa sterg
     *              daca lista e goala, imi arunca o EmptyListException si imi adauga un student default in lista
     *              daca index nu este in intervalul [0, students.size()] imi arunca o CustomIndexOutOfBoundException
     */
    public void removeStudent(int index) {
        try {
            if (students.size() == 0) {
                throw new EmptyListException();
            }
            if (index < 0 || index > students.size() - 1) {
                throw new CustomIndexOutOfBoundException(students.size(), index);
            }
            students.remove(index);
        } catch (CustomIndexOutOfBoundException e) {
            System.out.println();
            System.out.println("Lista este: " + students);
            System.out.println(e.getMessage());
        } catch (EmptyListException e) {
            addStudent(new Student("Catalin", 9.32));
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println("Pentru ca lista este goala, am adaugat un student. Noua lista este: " + students);
        }
    }

    // functia care scrie in fisier studentii din lista
    public void writeToFile() {
        try {
            os = new ObjectOutputStream(new FileOutputStream(fileName));
            for (Student student : students) {
                os.writeObject(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeOutputFile();
        }
    }

    // functia care citeste din fisier studentii
    public void loadFromFile() {
        students.clear();
        try {
            is = new ObjectInputStream(new FileInputStream(fileName));
            Student student;
            while (true) {
                try {
                    student = (Student) is.readObject();
                    students.add(student);
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeInputFile();
        }
    }

    // functie care imi inchide fisierul de scris
    public void closeOutputFile() {
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // functie care imi inchide fisierul de citit
    public void closeInputFile() {
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // functie care printeaza lista de studenti
    public void printStudents() {
        System.out.println(students);
    }

}
