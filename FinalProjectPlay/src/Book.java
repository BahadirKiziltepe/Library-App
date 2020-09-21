import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Book implements Comparable<Book>, Cloneable, Serializable {

	private static final long serialVersionUID = -4292701653964196041L;
	private String title;
    private String author;
    private String isbn;
    private String category;
    private boolean available;
    private Date date, returnDate;
    private User user;

    public Book() {
        this("", "", "", "");

    }

    public Book(String title, String author, String isbn, String category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(category, book.category);
    }

    @Override
    public String toString() {
        return title+", "+author+", "+isbn+", "+category;
    }

    @Override
    protected Object clone(){
    	Book book = null;
		try {
			book = (Book)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
    	book.title = new String(this.title);
    	book.author = new String(this.author);
    	book.isbn = new String(this.isbn);
    	book.category = new String(this.category);
    	return (Object)book;
    }

    @Override
    public int compareTo(Book otherBook) {
        if (!(otherBook instanceof Book))
            throw new IllegalArgumentException("Cannot compare a book object with a non book object.");

        Book other = (Book) otherBook;
        return isbn.compareTo(other.isbn);
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}
	
	public String isUser() {
		return user.getUserName();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
