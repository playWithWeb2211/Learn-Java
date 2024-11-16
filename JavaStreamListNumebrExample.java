import java.util.*;
import java.util.stream.Collectors;

/**
1. Using Sequential or Parallel Stream
  -Sequential streams work just like for-loops, iterating over the elements one by one.
   Parallel streams can split their work into multiple chunks and process them independently, possibly using multiple threads, and then combine the results.
  -The stream created by `list.stream()` is sequential. If you want to create a parallel stream, you can use `list.parallelStream()` instead.
  -When you use a parallel stream with the reduce operation, Java will split the list into multiple sub-lists, each of which is processed in a separate thread, and then it combines the results.
  -This can potentially be much faster than a sequential stream if your list is large and your computer has multiple cores. 
   However, for small lists, the overhead of splitting the work and combining the results may make a parallel stream slower than a sequential stream.

   
**/
public class JavaStreamListNumebrExample
{
	public static void main(String args[]) {
    List<Integer> list = Arrays.asList(1,7,8,9,5,2,36,4,78,222,24,9);

    //--------------- 1. Sum of all number in the list ------------------

    // i) using sequential stream
    Optional<Integer> sum = list.stream().reduce((a,b)->a+b);
    System.out.println(sum.get());

    // ii) using parallel stream
    Optional<Integer>sumParallelStream = list.parallelStream().reduce((a,b)->a+b);
    System.out.println(sumParallelStream.get());

    // iii) using sequential stream here the resul returned is int and not Optional
    int sumVal = list.stream().reduce(0,Integer::sum);
    System.out.println(sumVal);

    //---------------- 2. Average of Numbers in the list --------------------

    /* 
    - mapToInt(a->a)`: This is an intermediate operation. It transforms the `Stream` of objects into an `IntStream` (a `Stream` of primitive `int` values). 
    The `a->a` is a lambda function that represents a function that takes an element and returns the same element (in this case, it assumes that the list contains integers or numbers).
    
    - mapToInt() - REQUIRED
    List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Jane", 25),
                new Person("Jack", 22));

        double averageAge = people.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);

    - mapToInt() - NOT REQUIRED
    int[] numbers = {1, 2, 3, 4, 5};

    double average = Arrays.stream(numbers)
            .average()
            .orElse(0);
     
    */
    
    OptionalDouble avg = list.stream().mapToInt(a->a).average();
    System.out.println(avg.getAsDouble());
    
    double avg2 = list.stream().mapToInt(a->a).average().orElse(0);
    System.out.println("average is: "+avg2);

    //-----------------3. Separating Even and Odd Number ----------------------

    List<Integer> evens = list.stream().filter(num->num%2==0).collect(Collectors.toList());

    List<Integer> odds = list.stream().filter(num -> num%2!=0).collect(Collectors.toList());

    // -----------------4. Get Number starting with 2 ---------------------------

    ist<Integer> numStartingWithTwo = list.stream().map(num->String.valueOf(num)).filter(num->num.startsWith("2")).map(Integer::valueOf).collect(Collectors.toList());
    System.out.println(numStartingWithTwo); 
    
    List<Integer> numStartingWithTwo1 = list.stream().map(String::valueOf).filter(num->num.startsWith("2")).map(Integer::valueOf).collect(Collectors.toList());
    System.out.println(numStartingWithTwo1);

    // O/P : 
    // [2, 222, 24]
    // [2, 222, 24]
    
    
  }
}
