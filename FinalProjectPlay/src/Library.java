import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Library implements Serializable {

	private static final long serialVersionUID = -9133051276989144521L;

	public static BookShelf readBookShelf(String path, User user, BookShelf bs, TableView<Book> table,
			ObservableList<Book> viewBooks) {
		try (ObjectInputStream bookLocation = new ObjectInputStream(
				new FileInputStream(path + user.getID() + ".dat"));) {
			Book[] books = (Book[]) bookLocation.readObject();
			for (int i = 0; i < books.length; i++) {
				bs.addBook(books[i]);
			}
		} catch (EOFException e1) {

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		viewBooks = FXCollections.observableArrayList();
		viewBooks.addAll(bs.getAllBooks());
		table.setItems(viewBooks);
		return bs;
	}

	public static void writeBookShelf(String path, User user, BookShelf bs) {
		Book[] books = new Book[bs.getAllBooks().size()];
		File fileBooks = new File(path + user.getID() + ".dat");

		for (int i = 0; i < books.length; i++) {
			books[i] = bs.getAllBooks().get(i);
		}

		try (ObjectOutputStream bookLocation = new ObjectOutputStream(new FileOutputStream(fileBooks));) {
			bookLocation.writeObject(books);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static UserShelf readUserShelf(String path, UserShelf us, TableView<User> table,
			ObservableList<User> viewBooks) {
		try (ObjectInputStream userLocation = new ObjectInputStream(new FileInputStream(path))) {
			User[] users = (User[]) userLocation.readObject();
			for (int i = 0; i < users.length; i++) {
				us.addUser(users[i]);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		viewBooks = FXCollections.observableArrayList();
		viewBooks.addAll(us.getAllUsers());
		table.setItems(viewBooks);
		return us;
	}

	public static void writeUserShelf(String path, UserShelf us) {
		User[] users = new User[us.getAllUsers().size()];
		File fileUsers = new File(path);

		for (int i = 0; i < users.length; i++) {
			users[i] = us.getAllUsers().get(i);
		}

		try (ObjectOutputStream userLocation = new ObjectOutputStream(new FileOutputStream(fileUsers));) {
			userLocation.writeObject(users);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
