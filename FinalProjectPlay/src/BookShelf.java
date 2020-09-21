import java.io.*;
import java.util.ArrayList;

public class BookShelf implements Cloneable, Serializable {

	private static final long serialVersionUID = 8750047099768274766L;
	ArrayList<Book> bookList;

	public BookShelf() {
		bookList = new ArrayList<>();
	}

	public BookShelf(Book[] books) {
		this();
		for (Book book : books) {
			bookList.add(book);
		}
	}

	public void addBook(Book newBook) {
		for (Book book : bookList) {
			if (book.getIsbn().equals(newBook.getIsbn())) {
				return;
			}
		}
		bookList.add(newBook);
	}

	public void removeBook(Book newBook) {
		for (Book book : bookList) {
			if (book.getIsbn().equals(newBook.getIsbn())) {
				bookList.remove(book);
				return;
			}
		}
	}

	public ArrayList<Book> getAllBooks() {
		return bookList;
	}

	public BookShelf getBooksByTitle(String title) {
		BookShelf bs = new BookShelf();

		for (Book book : bookList) {
			if (book.getTitle().equals(title))
				bs.addBook(book);
		}

		return bs;
	}

	public BookShelf getBooksByAuthor(String author) {
		BookShelf bs = new BookShelf();

		for (Book book : bookList) {
			if (book.getAuthor().equals(author))
				bs.addBook(book);
		}

		return bs;
	}

	public BookShelf getBooksByIsbn(String isbn) {
		BookShelf bs = new BookShelf();

		for (Book book : bookList) {
			if (book.getIsbn().equals(isbn))
				bs.addBook(book);
		}

		return bs;
	}

	public BookShelf getBooksByCategory(String category) {
		BookShelf bs = new BookShelf();

		for (Book book : bookList) {
			if (book.getCategory().equals(category))
				bs.addBook(book);
		}

		return bs;
	}

	public BookShelf getBooksByUser(String user) {
		BookShelf bs = new BookShelf();

		for (Book book : bookList) {
			if (book.getUser().toString().equals(user))
				bs.addBook(book);
		}

		return bs;
	}

	public BookShelf findBook(Book newBook) {
		BookShelf bs = new BookShelf();

		for (Book book : bookList) {
			if (book.getIsbn().equals(newBook.getIsbn()))
				bs.addBook(book);
		}

		return bs;
	}

	public boolean isEmpty() {
		return bookList.isEmpty();
	}

	public void clear() {
		bookList.clear();
	}

	@Override
	public String toString() {
		return "The bookshelf has " + bookList.size() + " books.";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		BookShelf copy = (BookShelf) super.clone();
		copy.bookList = new ArrayList<>();
		for (Book book : bookList)
			copy.addBook((Book) book.clone());

		return copy;
	}

}
