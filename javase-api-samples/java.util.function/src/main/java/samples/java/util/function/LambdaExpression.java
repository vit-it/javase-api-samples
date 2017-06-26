package samples.java.util.function;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 Синтаксис лямда
 (p1, p2, ..., pn) -> ...
 тип данных параметров можно не указывать
 не обязательно указывать скобки если на вход всего один параметр
 затем стрелка ->
 затем блок выражения, если код не укладывается в одно выражение, можно использовать фигурные скобки
 p -> p.getAge() >= 18, p -> { return p.getAge() >= 18; }

 См. функциональные интерфейсы в пакете java.util.function
 Predicate, Function, Consumer, Supplier, UnaryOperator

 Ссылки на методы (Method References)
 Person::compareByAge то же самое, что и (a, b) -> Person.compareByAge(a, b)
 4 вида ссылок на методы:
 ContainingClass::staticMethodName - Reference to a static method
 containingObject::instanceMethodName - Reference to an instance method of a particular object
 ContainingType::methodName - Reference to an instance method of an arbitrary object of a particular type
 ClassName::new - Reference to a constructor
 */
public class LambdaExpression {

    public static void main(String[] args) {

        LambdaExpression le = new LambdaExpression();
        le.printByPredicate(p -> p != null && p.getGender() == Sex.MALE);

    }

    private List<Person> personList = new ArrayList<>();

    private void fill() {
        personList.add(new Person("Daniel", LocalDate.of(1990, 4, 26),
                Sex.MALE, "daniel@post.com"));
        personList.add(new Person("Arina", LocalDate.of(1994, 8, 5),
                Sex.FEMALE, "Arina@post.com"));
    }

    public LambdaExpression() {
        fill();
    }

    private void printByPredicate(Predicate<Person> predicate) {
        getPersonList().stream().filter(predicate).forEach(this::printPerson);
    }

    private void printPerson(Person person) {
        out(person.toString());
    }

    private void out(Object o) {
        System.out.println(o);
    }

    private List<Person> getPersonList() {
        return personList;
    }

    public class Person {
        private String name;
        private LocalDate birthday;
        private Sex gender;
        private String emailAddress;

        public Person() {

        }

        public Person(String name, LocalDate birthday, Sex gender, String emailAddress) {
            this.name = name;
            this.birthday = birthday;
            this.gender = gender;
            this.emailAddress = emailAddress;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday = birthday;
        }

        public Sex getGender() {
            return gender;
        }

        public void setGender(Sex gender) {
            this.gender = gender;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", birthday=" + birthday +
                    ", gender=" + gender +
                    ", emailAddress='" + emailAddress + '\'' +
                    '}';
        }
    }

    public enum Sex {
        MALE, FEMALE
    }
}
