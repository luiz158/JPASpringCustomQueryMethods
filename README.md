## Query Methods - allow you to create custom queries

### What are Query Methods?

A quick way to retrieve records from your database without using SQL. Query methods are intuitive. They simply join keywords (from the table shown below) with field names to return a customized list of your data. If a query method returns a scalar (a one row and one column table containing a single value) then the query method return value  is the datatype of the scalar, ie... Integer, String, etc....

Why do I care?

Because you're too lazy to use SQL and (or) you just want something completed quickly (and correctly).

### Where do I put them?

In the repository as shown below:

```
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    long countByState(String state);
    Iterable<Customer> findAllByLastNameContainingIgnoreCase(String lastName);
    Iterable<Customer> findAllByCityAndState(String city, String state);
}
```
### How do I call a query method?

From the controller

```
@RequestMapping("/")
public String index(Model model){
    //we can populate the arraylist from the customerRepository
    //Since the repository returns an iterable (a more generic class) we need
    //to cast it to an arrayList of type customer: (ArrayList<Customer>)

    //the % symbol is an optional wildcard for 0 or more characters
    String lastName = "J%";
    ArrayList<Customer> results =(ArrayList<Customer>)
            customerRepository.findAllByLastNameContainingIgnoreCase(lastName);

    // The state could be selected from a user form and submitted back to the controller
    String state = "CA";
    long total = customerRepository.countByState(state);

    //display our results on the index page...
    model.addAttribute("state",state);
    model.addAttribute("total",total);
    model.addAttribute("results", results);
    return "index";
}
```
### How do I display the results of the query method?

Using Thymeleaf as shown below:

```
<body>
<h2>Results</h2>
<p>There are [[${total}]] customers in [[${state}]]</p>
<div th:each="result:${results}">
    <p th:text="${result.lastName}"/>

</div>
</body>
```
This is cool! What other keywords can I use (with an example, please!)?

Keyword             | Sample
--------            |-------
And	                |findByLastnameAndFirstname
Or	                |findByLastnameOrFirstname
Between	            |findByStartDateBetween
LessThan	        |findByAgeLessThan
LessThanEqual	    |findByAgeLessThanEqual
GreaterThan	        |findByAgeGreaterThan
GreaterThanEqual	|findByAgeGreaterThanEqual
After	            |findByStartDateAfter
Before	            |findByStartDateBefore
IsNull	            |findByAgeIsNull
IsNotNull,NotNull	|findByAge(Is)NotNull
Like	            |findByFirstnameLike
NotLike	            |findByFirstnameNotLike
StartingWith	    |findByFirstnameStartingWith
EndingWith	        |findByFirstnameEndingWith
Containing	        |findByFirstnameContaining
OrderBy	            |findByAgeOrderByLastnameDesc
Not	                |findByLastnameNot
In	                |findByAgeIn(Collection<Age> ages)
NotIn	            |findByAgeNotIn(Collection<Age> age)
TRUE	            |findByActiveTrue()
FALSE	            |findByActiveFalse()
IgnoreCase	        |findByFirstnameIgnoreCase
