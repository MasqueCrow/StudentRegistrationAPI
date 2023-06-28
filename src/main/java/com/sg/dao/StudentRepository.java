package com.sg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sg.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	public List<Student> findByName(String name);

	//@Query is for defining custom query and @Modifying is for telling 
	//spring-data-jpa that this query is an update operation 
	//and it requires executeUpdate() not executeQuery().
	//ref: https://stackoverflow.com/questions/11881479/how-do-i-update-an-entity-using-spring-data-jpa
	@Transactional
	@Modifying
	@Query("UPDATE Student s SET s.name = ?1, s.age = ?2, s.mobileNo= ?3, s.city = ?4 where s.id = ?5")
	abstract public int setStudent(String name, int age, int mobileNo, String city, int id);
	
	/*
	 UserJPARepository extends the JpaRepository of the below default methods
	 
	 findById  : Retrieves one object based on the ID you pass it
	 findAll   : Retrieves a list of all the objects of the type
	 save      : Used for both creating and updating an object; returns the object that was created or updated as it now exists in the database
	 saveAll   : Saves a collection of entities
	 deleteById: Deletes the object with the passed ID from the database
	 deleteAll : Deletes all entities
	 count     : Retrieves a count of all objects of a type in the database
	 existsById: Checks if an object with the passed ID exists in the database; returns true or false
	 */
	
	/*
	 * JPA uses query methods to handle anything past simple CRUD operations. 
	 * The idea is that you create interface method signatures
	 * in a specific format that the system can interpret and 
 	 * convert to SQL queries behind the scenes.
 	 * 
 	 * These method names always start with findBy, followed by the filters we want to apply 
 	 * to the query
 	 *  eg List<Store> findByManager(String manager); internally is converted to
     * SELECT * FROM store WHERE manager = ? 
 	 * The question mark will be filled in by the parameter of the method.
 	 * 
 	 * We can include more than one field in the filter as
 	 * Store findByManagerAndLocation(String manager, String location);
     * separate the two field names with "And." This also works for "Or" and a number of other SQL keywords
	 * */
}
