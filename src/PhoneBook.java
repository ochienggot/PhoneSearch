/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Collections;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ngot
 */
public class PhoneBook {

	private List<Person> people;
	private Scanner reader;
	private int searchIndex;

	public PhoneBook() {
		people = new ArrayList<Person>();
		reader = new Scanner(System.in);
		this.searchIndex = 0;
	}

	public void addPhoneNumber(String name, String phoneNumber) {

		// initialize addressses not present
		if (!people.contains(new Person(name))) {
			people.add(new Person(name));
		}

		for (Person p : people) {
			if (p.equals(new Person(name))) {
				p.setPhoneNumber(phoneNumber);
				return;
			}
		}
	}

	public Set<String> searchByPerson(String name) {

		// Functional style: no change of state, avoid mutability
		for (Person p : people) {
			if (p.equals(new Person(name))) {
				return p.getPhoneNumber();
			}
		}

		return null;
	}

	public String nameSearchByPhoneNumber(String phoneNumber) {

		for (Person s : people) {
			Set<String> temp = s.getPhoneNumber();
			if (temp.contains(phoneNumber)) {
				return s.getName();
			}
		}

		return null;
	}

	public String searchByAddress(String keyword) {

		for (Person s : people) {
			List<String> temp = s.getAddress();
			if (temp.contains(keyword) || temp.contains(keyword)) {
				return s.getName();
			}
		}

		System.out.println("  not found");
		return null;
	}

	public boolean inAddress(Person p) {
		
		return people.contains(p);
	}

	public void addAddress() {
		System.out.print("whose address: ");
		String name = reader.nextLine();

		System.out.print("street: ");
		String street = reader.nextLine();

		System.out.print("city: ");
		String city = reader.nextLine();

		// 0 - st, 1 - city
		if (!people.contains(new Person(name))) {
			// not in list
			people.add(new Person(name));
		} 
		// should at least be initialized
		for (Person p : people) {
			if (p.equals(new Person(name))) {
				p.setAddress(street, city);
			}
		}
	}

	public void searchInfo(Person person) {

		if (!people.contains(person)) {
			System.out.println("  not found");
			return;
		}

		// search for address
		for (Person p : people) {
			if (p.equals(person)) {

				List<String> temp = p.getAddress();
				if (temp != null && !temp.isEmpty()) {
					System.out.println("  address: " + temp.get(0) + " " + temp.get(1));
				} else {
					System.out.println("  address unknown");
			}
		}
		}

		// search for phoneNumber
		Set<String> results = searchByPerson(person.getName());
		displayNumbers(results);

	}

	public void displayNumbers(Set<String> results) {

		if (results == null || results.isEmpty()) {
			System.out.println("  phone number not found");
			return;
		}

		else if (results.size() > 1) {
			System.out.println("  phone numbers: ");

		} else {
			System.out.println("  phone number: ");
		}

		for (String s : results) {
			System.out.println("   " + s);
			}
	}

	public void removeInfo() {
		System.out.print("whose information: ");
		String name = reader.nextLine();

		if (inAddress(new Person(name))) {
			people.remove(new Person(name));
		}
	}

	public void filterInfo() {
		// keyword - name or address; sorted in alphabetical order
		Collections.sort(people);

		System.out.print("keyword (if empty, all listed): ");
		String keyword = reader.nextLine();

		if (keyword.isEmpty()) {
			for (Person person : people) {
				// list all
				searchInfo(person);
			}

		} else if (people.isEmpty()) {
			System.out.println("  keyword not found");
		} 
		else {
			for (Person p : people) {
				if (p.getName().contains(keyword)) {
					System.out.println(p.getName());
					searchInfo(p);
					searchIndex++;
				}

				else if (p.getAddress() != null) {
					if (p.getAddress().contains(keyword) || p.getAddress().contains(keyword)) {
					System.out.println(p.getName());
					String temp = searchByAddress(keyword);
					searchInfo( new Person(temp));
					searchIndex++;

				} 

				else if (p == people.get(people.size() - 1) && searchIndex == 0) {
				
					System.out.println(" keyword not found");
					}
				}

			}

		}
	}

	public void start() {

		System.out.println("phone search");
		System.out.println("available operations: ");
		System.out.println("1 add a number");
		System.out.println("2 search for a number");
		System.out.println("3 search for a person by phone number");
		System.out.println("4 add an address");
		System.out.println("5 search for personal information");
		System.out.println("6 delete personal information");
		System.out.println("7 filtered listing");
		System.out.println("x quit");
		System.out.println("");

		System.out.print("Command: ");
		String command = reader.nextLine();

		while (!command.equals("x")) {
			if (command.equals("1")) {
				System.out.print("whose number: ");
				String name = reader.nextLine();
				System.out.print("number: ");
				String number = reader.nextLine();
				
				addPhoneNumber(name, number);

			} else if (command.equals("2")) {
				System.out.print("whose number: ");
				String name = reader.nextLine();

				Set<String> temp = (searchByPerson(name));
				if (temp == null || temp.isEmpty()) {
					System.out.println("  not found");
				} else {
					for (String s : temp) {
						System.out.println(" " + s);
					}
				}


			} else if (command.equals("3")) {
				System.out.print("number: ");
				String number = reader.nextLine();

				String result = nameSearchByPhoneNumber(number);

				if (result == null) {
					System.out.println("  not found");
				} else {
					System.out.println("  " + result);
				}

			} else if (command.equals("4")) {

				addAddress();

	 		} else if (command.equals("5")) {
				// personal info search - search for address + phone num
				System.out.print("whose information: ");
				String name = reader.nextLine();
				searchInfo(new Person(name));

			} else if (command.equals("6")) {
				removeInfo();

			} else if (command.equals("7")) {
				filterInfo();
			}


		System.out.println("");
		System.out.print("Command: ");
		command = reader.nextLine();
		}
	}
}
