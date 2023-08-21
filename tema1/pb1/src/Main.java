public class Main {
    public static void main(String[] args) {
        StudentsRegister studentsRegister1 = StudentsRegister.getInstance();
        StudentsRegister studentsRegister2 = StudentsRegister.getInstance();

        // verific ca am o singura instanta de StudentRegister
        assert (studentsRegister1 == studentsRegister2);

        // verific exceptia in care sterg cand am lista goala
        studentsRegister1.removeStudent(3);
        studentsRegister1.addStudent(new Student("Ion", 10.0));

        // afisez lista dpa adaugarea unui student noi pe langa cel adaugat cand am prins exceptia
        System.out.println();
        System.out.println("Lista dupa adaugarea unui student: ");
        studentsRegister1.printStudents();

        // verific exceptiile atunci cand sterg de la index invalid, apoi adaug alti studenti
        studentsRegister1.removeStudent(3);
        studentsRegister1.removeStudent(3);
        studentsRegister1.removeStudent(-3);
        studentsRegister1.addStudent(new Student("Vlad", 10.00));
        studentsRegister1.addStudent(new Student("Andrei", 9.50));
        studentsRegister1.addStudent(new Student("David", 8.90));

        // afisez lista dupa modificari
        System.out.println();
        System.out.println("Lista dupa adaugarea mai multor studenti: ");
        studentsRegister1.printStudents();

        // salvez lista in fisier, apoi fac modificari
        studentsRegister1.writeToFile();
        studentsRegister1.removeStudent(1);
        studentsRegister1.removeStudent(0);

        // afisez lista dupa modificari
        System.out.println();
        System.out.println("Lista de studenti dupa modificare");
        studentsRegister1.printStudents();

        // afisez lista salvata inaintea modificarilor
        System.out.println();
        studentsRegister1.loadFromFile();
        System.out.println("Lista incarcata din fisier, inainte de modificare");
        studentsRegister1.printStudents();

        // salvez lista inainte si dupa o modificare, apoi afisez lista dupa
        // modificare pentru a arata ca este functia writeToFile este persistenta
        System.out.println();
        studentsRegister1.writeToFile();
        studentsRegister1.removeStudent(4);
        System.out.println("Lista corecta: ");
        studentsRegister1.printStudents();
        studentsRegister1.writeToFile();
        studentsRegister1.loadFromFile();
        System.out.println("Lista actuala: ");
        studentsRegister1.printStudents();
    }
}
