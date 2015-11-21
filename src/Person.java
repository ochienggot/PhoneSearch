/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
/**
 *
 * @author ngot
 */
public class Person implements Comparable<Person> {

	private final List<String> address;
	private final String name;
	private final Set<String> phoneNumber;

	public Person(String name) {
		this.address = new ArrayList<String>();
		this.name = name;
		phoneNumber = new HashSet<String>();
	}

	public String getName() {
		return this.name;
	}

	public List<String> getAddress() {
		return this.address;
	}

	public void setAddress(String street, String city) {

		address.add(street);
		address.add(city);
	}

	public Set<String> getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.add(phoneNumber);
	}

	@Override 
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person other = (Person) obj;
			return this.name.equals(other.getName());
		}

		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = result + 31 * this.name.hashCode();
		return result;
	}

	@Override
	public int compareTo(Person p) {
		return name.compareTo(p.getName());
	}
	
}
