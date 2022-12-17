import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println("Количество несовершеннолетних равно " + persons.stream().filter(a -> a.getAge() < 18).count() + " человек.");
        List<String> familiesArmy = persons.stream()
                .filter(a -> a.getSex().equals(Sex.MAN))
                .filter(a -> a.getAge() >= 18 && a.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(familiesArmy);
        List<Person> educatedPeople = persons.stream()
                .filter(a -> (a.getAge() >= 18))
                .filter(a -> (a.getSex() == Sex.MAN) ? a.getAge() <= 65 : a.getAge() <= 60)
                .filter(a -> a.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(educatedPeople);
    }
}
