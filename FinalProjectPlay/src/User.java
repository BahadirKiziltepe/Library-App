import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class User implements Serializable {

	private static final long serialVersionUID = -7020619477594468968L;
	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	private String age;
	private String address;
	private String ID;
	private BookShelf bs = new BookShelf(new Book[] {});

	public User() {
		this("", "", "", "", "", "");
	}

	public User(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}

	public User(String userName, String passWord, String firstName, String lastName, String age, String address) {
		this.userName = userName;
		this.passWord = passWord;
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAge(age);
		this.setAddress(address);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BookShelf userBookShelf() {
		return bs;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void addBookUser(Book newBook) {
		bs.addBook(newBook);
	}

	protected Object clone() throws CloneNotSupportedException {
		User user = (User) super.clone();
		user.userName = new String(this.userName);
		user.passWord = new String(this.passWord);
		return (Object) user;
	}
	
	@Override
	public String toString() {
		return userName;
	}

	public static String uniqueID() {
		Random r = new Random();
		ArrayList<Integer> randomID = new ArrayList<>();

		try (ObjectInputStream idLocationIN = new ObjectInputStream(new FileInputStream("id.dat"));) {
			randomID = (ArrayList<Integer>) idLocationIN.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		int x = r.nextInt(1001);
		if (x < 0) {
			x *= -1;
		}
		if (randomID.contains(x)) {
			x = r.nextInt(1001);
		} else {
			randomID.add(x);
		}
		try (ObjectOutputStream bookLocation = new ObjectOutputStream(new FileOutputStream("id.dat"));) {
			bookLocation.writeObject(randomID);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return String.valueOf(x);
	}

}
