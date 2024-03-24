package com.alamin.stream_api;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Root {
    public static List<Student> createStudents = List.of(
            new Student(1L, "Al Amin", "SCIENCE", 14),
            new Student(2L, "Rony", "SCIENCE", 15),
            new Student(3L, "Messi", "SCIENCE", 16),
            new Student(4L, "Messi", "SCIENCE", 16),
            new Student(5L, "Jil", "ARTS", 20),
            new Student(6L, "Paul", "COMMERCE", 22),
            new Student(7L, "Sara", "COMMERCE", 24),
            new Student(8L, "Mahadi", "ARTS", 25),
            new Student(9L, "Mahadi", "ARTS", 25)
    );
    public static void main(String[] args) {
        // reduce takes the collections
        System.out.println(
                createStudents.stream()
                        .map(Student::getAge)
//                        .reduce(0, (total, age)-> total+age)
                        .reduce(0, Integer::sum)
        );

        System.out.println("----------Reduce of ArrayList-----------");
        System.out.println(
                createStudents.stream()
                        .filter(s-> s.getAge() > 20)
                        .map(Student::getName)
                        .map(String::toUpperCase)
                        .reduce(
                                new ArrayList<String>(),
                                (names, name) -> {
                                    names.add(name);
//                                    System.out.println("Names: "+names);
                                    return names;
                                },
                                (names1, names2) -> {
//                                    System.out.println(names1+"::"+names2);
                                    names1.addAll(names2);
                                    return names1;
                                }
                        )
        );
        System.out.println("----------Reduce of toList-----------");
        System.out.println(
                createStudents.stream()
                        .filter(s-> s.getAge() > 20)
                        .map(Student::getName)
                        .map(String::toUpperCase)
//                        .reduce(
//                                new ArrayList<String>(),
//                                (names, name) -> {
//                                    names.add(name);
////                                    System.out.println("Names: "+names);
//                                    return names;
//                                },
//                                (names1, names2) -> {
////                                    System.out.println(names1+"::"+names2);
//                                    names1.addAll(names2);
//                                    return names1;
//                                }
//                        )
                        .collect(Collectors.toList())
        );
        System.out.println("----------Reduce of HashSet-----------");
        System.out.println(
                createStudents.stream()
                        .filter(s-> s.getAge() > 15)
                        .map(Student::getName)
                        .map(String::toUpperCase)
                        .reduce(
                                new HashSet<String>(),
                                (names, name) -> {
                                    names.add(name);
//                                    System.out.println("Names: "+names);
                                    return names;
                                },
                                (names1, names2) -> {
//                                    System.out.println(names1+"::"+names2);
                                    names1.addAll(names2);
                                    return names1;
                                }
                        )
        );
        System.out.println("----------Reduce of toSet-----------");
        System.out.println(
                createStudents.stream()
                        .filter(s-> s.getAge() > 15)
                        .map(Student::getName)
                        .map(String::toUpperCase)
//                        .reduce(
//                                new HashSet<String>(),
//                                (names, name) -> {
//                                    names.add(name);
////                                    System.out.println("Names: "+names);
//                                    return names;
//                                },
//                                (names1, names2) -> {
////                                    System.out.println(names1+"::"+names2);
//                                    names1.addAll(names2);
//                                    return names1;
//                                }
//                        )
                        .collect(Collectors.toSet())
        );



        System.out.println("----------Reduce of parallelStream Using ArrayList Data Consistency-----------");
        System.out.println(
                createStudents.parallelStream()
                        .filter(s-> s.getAge() > 15)
                        .map(Student::getName)
                        .map(String::toUpperCase)
                        .reduce(
                                new ArrayList<String>(),
                                (names, name) -> {
                                    names.add(name);
//                                    System.out.println("Names: "+names);
                                    return names;
                                },
                                (names1, names2) -> {
//                                    System.out.println(names1+"::"+names2);
                                    names1.addAll(names2);
                                    return names1;
                                }
                        )
        );
        System.out.println("----------parallelStream of toList-----------");
        System.out.println(
                createStudents.parallelStream()
                        .filter(s-> s.getAge() > 15)
                        .map(Student::getName)
                        .map(String::toUpperCase)
                        .collect(Collectors.toList())
        );
        // Map names as key and age as value
        System.out.println("----------Stream Api Map-----------");
        System.out.println(
                createStudents.stream()
                        // .collect(Collectors.toMap(keyFunction, valueFunction))
                        .collect(Collectors.toMap(Student::getId, Student::getName))
        );
        System.out.println("----------Stream Api of Modifiable List-----------");
        List<Integer> ages = createStudents.stream()
                .map(Student::getAge)
                .collect(Collectors.toList());
        ages.add(88);
        System.out.println(ages);
        System.out.println(ages.getClass());
        System.out.println("----------Stream Api of Unmodifiable List-----------");

        List<Integer> agesUnmodifiable = createStudents.stream()
                .map(Student::getAge)
                .collect(Collectors.toUnmodifiableList());
        //agesUnmodifiable.add(88); //Exception this Runtime
        System.out.println(agesUnmodifiable);
        System.out.println(agesUnmodifiable.getClass());


        // Create coma separated the names in upper-cases of student older than 15
        System.out.println("----------Stream Api of Joining-----------");
        System.out.println(
                createStudents.stream()
                        .filter(s->s.getAge()>15)
                        .map(Student::getName)
                        .map(String::toUpperCase)
                        // Collector<T,A,R>
                        .collect(Collectors.joining(", "))
        );
        System.out.println("----------Stream Api of Event for ID-----------");
        System.out.println(
                createStudents.stream()
                        .filter(s->s.getId() % 2 == 0)
                        .collect(Collectors.toList())
        );
        System.out.println("----------Stream Api of Odd for ID-----------");
        System.out.println(
                createStudents.stream()
                        .filter(s->s.getId() % 2 != 0)
                        .collect(Collectors.toList())
        );
        System.out.println("----------Stream Api of partitioningBy used two different group for ID-----------");
        System.out.println(
                createStudents.stream()
                        .collect(Collectors.partitioningBy(student -> student.getId() % 2 == 0))
        );
        System.out.println("----------Stream Api of partitioningBy used two different group for ID-----------");
        Map<Boolean, List<Student>> groupOfTwo = createStudents.stream()
                .collect(Collectors.partitioningBy(student -> student.getId() % 2 == 0));
        System.out.println(groupOfTwo);
        System.out.println("----------Stream Api of groupingBy used multiple groupBy(Function<T,R>)==>Collector-----------");
        //groupBy(Function<T,R>)==>Collector
        Map<String, List<Student>> studentOfGroupBy = createStudents.stream()
                .collect(Collectors.groupingBy(Student::getDepartment));
        System.out.println(studentOfGroupBy);

        System.out.println("----------Stream Api of groupingBy % mapping-----------");
        //groupBy(Function<T,R>, Collector)
        var studentOfGroupByAge = createStudents.stream()
                .collect(Collectors.groupingBy(Student::getDepartment, Collectors.mapping(Student::getAge, Collectors.toList())));
        //Collector(Function, Collector(Function, Collector) :: This is used to 3 collectors
        System.out.println(studentOfGroupByAge);

        System.out.println("----------Stream Api of groupingBy countByName-----------");
        //groupBy(Function<T,R>, Collector)
        //Map<String, Integer> this counting() Error
        //groupBy and mapping (Function, Collector) ==> Map<String, Long>
        //collectingAndThen(Collector, Function) ==> Map<String, Integer>
        Map<String, Long> countDefaultLongByName = createStudents.stream()
                .collect(Collectors.groupingBy(Student::getDepartment, Collectors.counting()));
        System.out.println(countDefaultLongByName);
        Map<String, Integer> countDownOfIntegerByName = createStudents.stream()
                .collect(Collectors.groupingBy(Student::getDepartment, Collectors.collectingAndThen(Collectors.counting(), value-> value.intValue())));
        System.out.println(countDownOfIntegerByName);
        System.out.println("------------Optional------------------");
        System.out.println(
                createStudents.stream()
                        .mapToInt(Student::getAge)
                        .sum()
        );
        System.out.println(
                createStudents.stream()
                        .mapToInt(Student::getAge)
                        .max()
        );

        System.out.println(
                createStudents.stream()
                        .collect(Collectors.maxBy(Comparator.comparing(Student::getAge)))
        );

        System.out.println(
                createStudents.stream()
                        .collect(Collectors.minBy(Comparator.comparing(Student::getAge)))
        );

        var result = createStudents.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.maxBy(
                                        Comparator.comparing(Student::getAge)),
                                s->s.map(Student::getName).orElse("")));

        System.out.println(result);



        // map vs mapping
        System.out.println(
                createStudents.stream()
                        .collect(Collectors.groupingBy(Student::getAge, Collectors.mapping(Student::getName, Collectors.toList())))
        );
        // filter vs filtering
        System.out.println(
                createStudents.stream()
                        .collect(Collectors.groupingBy(Student::getAge, Collectors.mapping(Student::getName, Collectors.filtering(name->name.length() > 4, Collectors.toList()))))
        );

        // grouping mapping (Function, Collector)
        // collectingAndThen(Collector, Function)
        // teeing(Collector, Collector, Operation)


        List<Integer> numbers = List.of(1,2,3);
        //one-to-one function
        System.out.println("one-to-one function");
        System.out.println(
                numbers.stream()
                        .map(e-> e*2)
                        .collect(Collectors.toList())
        );
        //Stream<T>.map(f11) ==> Stream<R>
        //one-to-many function
        System.out.println("one-to-many function");
        System.out.println(
                numbers.stream()
                        .map(e-> List.of(e-1, e+1))
                        .collect(Collectors.toList())
        );
        //Stream<T>.map(f1n) ==> Stream<List<R>>

        System.out.println("one-to-many function");
        System.out.println(
                numbers.stream()
                        .flatMap(e-> List.of(e-1, e+1).stream())
                        .collect(Collectors.toList())
        );
        //Stream<T>.???(f1n) ==> Stream<R>
        //Stream<T>.map(Function<T,R>) ==> Stream<R>
        //Stream<T>.flatMap(Function<T, ???<R>>) ==> Stream<R>
        //Stream<T>.flatMap(Function<T, Iterator<R>>) ==> Stream<R>
        //Stream<T>.flatMap(Function<T, Stream<R>>) ==> Stream<R>
        //flatMap:: Internal works first map than flatten....



        //Stream<T>.flatMap(f1n) ==> Stream<R>
        //If you have a one-to-one function, use map to go from
        // Stream<T> to Stream<R>

        //If you have a one-to-many function, use map to go from
        // Stream<T> to Stream<Collection<R>>

        //If you have a one-to-many function, use flatMap to go from
        // Stream<T> to Stream<R>

        System.out.println(
                createStudents.stream()
                        .collect(Collectors.groupingBy(Student::getAge, Collectors.mapping(student -> student.getName().toUpperCase(),
                                Collectors.flatMapping(name-> Stream.of(name.split("")), Collectors.toSet()))))
        );

        createStudents.stream()
                .sorted(Comparator.comparing(Student::getAge).thenComparing(Student::getName))
                .forEach(System.out::println);
        // TODO: 23/03/2024 Guideline Of Exploring Collectors
        //reduce - sum, max, min, reduce, collect
        //collect(Collector)
        //Collectors
        //toList, toSet, toMap
        //toUnmodifiableList, toUnmodifiableSet, toUnmodifiableMap
        //partitioningBy
        //groupingBy
        //groupingBy(Function<T,R>)
        //groupingBy(Function, Collector)
        //mapping(Function, Collector)
        //collectingAndThen(Collector, Function)
        //teeing(Collector, Collector, Operator)

    }
}
