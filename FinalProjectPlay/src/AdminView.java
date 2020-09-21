import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AdminView extends GUIsetup implements Serializable {

	private static final long serialVersionUID = 3603893027689158137L;
	private Scene sceneAdminView;
	private Button btnAddBook, btnRemoveBook, btnSelect, btnDelete, btnClear, btnSearch, btnMM;
	private User userAdmin, userDefault;
	private BorderPane allPic;

	private TextField tfTitle, tfAuthor, tfCount, tfCategory, tvfUserName, tvfPassWord, tvfFName, tvfLName, tvfAge,
			tvfAdress, tfSearch;
	private Text tvUserName, tvPassWord, tvFName, tvLName, tvAge, tvAdress, tvUserName2, tvPassWord2, tvFName2,
			tvLName2, tvAge2, tvAdress2, tvTitle, tvEdit, tvLate, tvAddb, tvSearchb, tvUserList;
	private Image img, img2;

	private ChoiceBox<Boolean> trueFalseBox;
	private ChoiceBox<String> nameBox;
	private TableView<Book> tableAdmin;
	private TableView<User> tableUser2;
	private TableView<Book> tableUser;
	private ObservableList<Book> viewBooksAdmin;
	private ObservableList<Book> viewBooksUser;
	private ObservableList<User> viewBooksUser2;
	private UserShelf us = new UserShelf(new User[] {});
	private BookShelf bsUser = new BookShelf(new Book[] {});
	private BookShelf bsAdmin = new BookShelf(new Book[] {});
	private BookShelf bsSearch = new BookShelf(new Book[] {});

	public AdminView() {
	}

	public Scene AdminViewScene() {
		TableColumn<Book, String> caTitle = new TableColumn<>("Title");
		caTitle.setMinWidth(85);
		caTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

		TableColumn<Book, String> caAuthor = new TableColumn<>("Author");
		caAuthor.setMinWidth(85);
		caAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));

		TableColumn<Book, String> caIsbn = new TableColumn<>("Isbn");
		caIsbn.setMinWidth(100);
		caIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

		TableColumn<Book, String> caCategory = new TableColumn<>("Category");
		caCategory.setMinWidth(85);
		caCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

		TableColumn<Book, Boolean> caAvailable = new TableColumn<>("Availability");
		caAvailable.setMinWidth(85);
		caAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));

		TableColumn<Book, Boolean> caDate = new TableColumn<>("Barrowed Date");
		caDate.setMinWidth(180);
		caDate.setCellValueFactory(new PropertyValueFactory<>("date"));

		TableColumn<Book, Boolean> caUser = new TableColumn<>("User");
		caUser.setMinWidth(100);
		caUser.setCellValueFactory(new PropertyValueFactory<>("user"));

		TableColumn<Book, String> cuTitle = new TableColumn<>("Title");
		cuTitle.setMinWidth(85);
		cuTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

		TableColumn<Book, String> cuAuthor = new TableColumn<>("Author");
		cuAuthor.setMinWidth(85);
		cuAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));

		TableColumn<Book, String> cuIsbn = new TableColumn<>("Isbn");
		cuIsbn.setMinWidth(100);
		cuIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

		TableColumn<Book, String> cuCategory = new TableColumn<>("Category");
		cuCategory.setMinWidth(85);
		cuCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

		TableColumn<Book, Boolean> cuDate = new TableColumn<>("Barrowed Date");
		cuDate.setMinWidth(180);
		cuDate.setCellValueFactory(new PropertyValueFactory<>("date"));

		TableColumn<Book, Boolean> cuReturnDate = new TableColumn<>("Return Date");
		cuReturnDate.setMinWidth(180);
		cuReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

		tableAdmin = new TableView<Book>();
		tableAdmin.getColumns().addAll(caTitle, caAuthor, caIsbn, caCategory, caAvailable, caDate, caUser);

		tableUser = new TableView<Book>();
		tableUser.getColumns().addAll(cuTitle, cuAuthor, cuIsbn, cuCategory, cuDate, cuReturnDate);

		TableColumn<User, String> cUserName = new TableColumn<>("Username");
		cUserName.setMinWidth(75);
		cUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));

		TableColumn<User, String> cPWord = new TableColumn<>("ID");
		cPWord.setMinWidth(75);
		cPWord.setCellValueFactory(new PropertyValueFactory<>("ID"));
		tableUser2 = new TableView<User>();
		tableUser2.getColumns().addAll(cUserName, cPWord);

		userAdmin = new User();
		userAdmin.setID("adminLib");
		userDefault = new User();
		userDefault.setID("tempLib");
		Library.readUserShelf("users.dat", us, tableUser2, viewBooksUser2);
		Library.readBookShelf("", userAdmin, bsAdmin, tableAdmin, viewBooksAdmin);

		tvUserName = new Text();
		tvPassWord = new Text();
		tvFName = new Text();
		tvLName = new Text();
		tvAge = new Text();
		tvAdress = new Text();
		tvUserName.setFont(new Font(15));
		tvUserName.setFill(Color.PURPLE);
		tvUserName.setStroke(Color.CYAN);
		tvPassWord.setFont(new Font(15));
		tvPassWord.setFill(Color.PURPLE);
		tvPassWord.setStroke(Color.CYAN);
		tvFName.setFont(new Font(15));
		tvFName.setFill(Color.PURPLE);
		tvFName.setStroke(Color.CYAN);
		tvLName.setFont(new Font(15));
		tvLName.setFill(Color.PURPLE);
		tvLName.setStroke(Color.CYAN);
		tvAge.setFont(new Font(15));
		tvAge.setFill(Color.PURPLE);
		tvAge.setStroke(Color.CYAN);
		tvAdress.setFont(new Font(15));
		tvAdress.setFill(Color.PURPLE);
		tvAdress.setStroke(Color.CYAN);

		tvUserName2 = new Text("Username: ");
		tvPassWord2 = new Text("Password: ");
		tvFName2 = new Text("First Name: ");
		tvLName2 = new Text("Last Name: ");
		tvAge2 = new Text("Age: ");
		tvAdress2 = new Text("Adress: ");
		tvUserName2.setFont(new Font(15));
		tvUserName2.setFill(Color.PURPLE);
		tvUserName2.setStroke(Color.CYAN);
		tvPassWord2.setFont(new Font(15));
		tvPassWord2.setFill(Color.PURPLE);
		tvPassWord2.setStroke(Color.CYAN);
		tvFName2.setFont(new Font(15));
		tvFName2.setFill(Color.PURPLE);
		tvFName2.setStroke(Color.CYAN);
		tvLName2.setFont(new Font(15));
		tvLName2.setFill(Color.PURPLE);
		tvLName2.setStroke(Color.CYAN);
		tvAge2.setFont(new Font(15));
		tvAge2.setFill(Color.PURPLE);
		tvAge2.setStroke(Color.CYAN);
		tvAdress2.setFont(new Font(15));
		tvAdress2.setFill(Color.PURPLE);
		tvAdress2.setStroke(Color.CYAN);

		tvEdit = new Text("Edit Profile");
		tvEdit.setFont(new Font(15));
		tvEdit.setFill(Color.PURPLE);
		tvEdit.setStroke(Color.CYAN);
		tvLate = new Text("Late Books");
		tvLate.setFont(new Font(15));
		tvLate.setFill(Color.PURPLE);
		tvLate.setStroke(Color.CYAN);
		tvAddb = new Text("Add Book");
		tvAddb.setFont(new Font(15));
		tvAddb.setFill(Color.PURPLE);
		tvAddb.setStroke(Color.CYAN);
		tvSearchb = new Text("Search Book");
		tvSearchb.setFont(new Font(15));
		tvSearchb.setFill(Color.PURPLE);
		tvSearchb.setStroke(Color.CYAN);

		tvTitle = new Text("USER INFO");
		tvTitle.setFont(new Font(15));
		tvTitle.setFill(Color.PURPLE);
		tvTitle.setStroke(Color.CYAN);
		tvUserList = new Text("User List");
		tvUserList.setFont(new Font(15));
		tvUserList.setFill(Color.PURPLE);
		tvUserList.setStroke(Color.CYAN);

		tvfUserName = new TextField("");
		tvfPassWord = new TextField("");
		tvfFName = new TextField("");
		tvfLName = new TextField("");
		tvfAge = new TextField("");
		tvfAdress = new TextField("");

		btnMM = GUIsetup.returnMainScreen();
		btnAddBook = new Button("Add Book");
		btnAddBook.setOnAction(e -> {

			String sTitle = tfTitle.getText();
			String sAuthor = tfAuthor.getText();
			String sIsbn = uniqueISBN();
			String sCategory = tfCategory.getText();

			Book bk = new Book(sTitle, sAuthor, sIsbn, sCategory);
			if (Integer.parseInt(tfCount.getText()) != 0)
				for (int i = 0; i < Integer.parseInt(tfCount.getText()); i++) {
					Book bkClone = (Book) bk.clone();
					bkClone.setIsbn(uniqueISBN());
					bkClone.setAvailable(true);
					bsAdmin.addBook(bkClone);
				}

			Library.writeBookShelf("", userAdmin, bsAdmin);
			Library.readBookShelf("", userAdmin, bsAdmin, tableAdmin, viewBooksAdmin);
		});
		btnRemoveBook = new Button("Remove Book");
		btnRemoveBook.setOnAction(e -> {

			ObservableList<Book> removedList = tableAdmin.getSelectionModel().getSelectedItems();
			for (Book b : removedList) {
				bsAdmin.removeBook(b);
			}

			Library.writeBookShelf("", userAdmin, bsAdmin);
			Library.readBookShelf("", userAdmin, bsAdmin, tableAdmin, viewBooksAdmin);
		});

		tableUser2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				bsUser.clear();
				User user = tableUser2.getSelectionModel().getSelectedItem();
				if (user != null)
					Library.readBookShelf("userInfo\\", user, bsUser, tableUser, viewBooksUser);
			}
		});

		btnSelect = new Button("Select User");
		btnSelect.setOnAction(e -> {
			ObservableList<User> selectedUser = tableUser2.getSelectionModel().getSelectedItems();
			Button btnEdit = new Button("Confirm Changes");
			for (User u : selectedUser) {
				for (User o : us.getAllUsers()) {
					if (o.equals(u)) {
						Library.readBookShelf("userInfo\\", o, bsUser, tableUser, viewBooksUser);
						tvUserName.setText(o.getUserName());
						tvPassWord.setText(o.getPassWord());
						tvFName.setText(o.getFirstName());
						tvLName.setText(o.getLastName());
						tvAge.setText(o.getAge());
						tvAdress.setText(o.getAddress());
						btnEdit.setOnAction(e1 -> {
							if (!(tvfUserName.getText().equals(""))) {
								o.setUserName(tvfUserName.getText());
							}
							if (!(tvfPassWord.getText().equals(""))) {
								o.setPassWord(tvfPassWord.getText());
							}
							if (!(tvfFName.getText().equals(""))) {
								o.setFirstName(tvfFName.getText());
							}
							if (!(tvfLName.getText().equals(""))) {
								o.setLastName(tvfLName.getText());
							}
							if (!(tvfAge.getText().equals(""))) {
								o.setAge(tvfAge.getText());
							}
							if (!(tvfAdress.getText().equals(""))) {
								o.setAddress(tvfAdress.getText());
							}
							tvUserName.setText(o.getUserName());
							tvPassWord.setText(o.getPassWord());
							tvFName.setText(o.getFirstName());
							tvLName.setText(o.getLastName());
							tvAge.setText(o.getAge());
							tvAdress.setText(o.getAddress());
							UserShelf.write(us);
							for (Book b : bsAdmin.getAllBooks()) {
								if (bsUser.getAllBooks().contains(b)) {
									b.setUser(o);
								}
							}
							Library.writeBookShelf("userInfo\\", o, bsUser);
							Library.readBookShelf("userInfo\\", o, bsUser, tableUser, viewBooksUser);
							Library.writeBookShelf("", userAdmin, bsAdmin);
							Library.readBookShelf("", userAdmin, bsAdmin, tableAdmin, viewBooksAdmin);
							Library.readUserShelf("users.dat", us, tableUser2, viewBooksUser2);
							tableUser2.refresh();
							tableAdmin.refresh();
						});
					}
				}
			}
			Button btnBack = new Button("Back");
			btnBack.setOnAction(e1 -> {
				sceneAdminView.setRoot(allPic);
			});
			BookShelf lateBooks = new BookShelf(new Book[] {});
			for (Book b : bsUser.getAllBooks()) {
				if (b.getDate().after(b.getReturnDate())) {
					lateBooks.addBook(b);
				}
			}
			TableColumn<Book, String> lTitle = new TableColumn<>("Title");
			lTitle.setMinWidth(85);
			lTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

			TableColumn<Book, String> lAuthor = new TableColumn<>("Author");
			lAuthor.setMinWidth(85);
			lAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));

			TableColumn<Book, String> lIsbn = new TableColumn<>("Isbn");
			lIsbn.setMaxWidth(100);
			lIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

			TableColumn<Book, String> lCategory = new TableColumn<>("Category");
			lCategory.setMinWidth(85);
			lCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
			TableView<Book> lates = new TableView<>();
			lates.getColumns().addAll(lTitle, lAuthor, lIsbn, lCategory);
			ObservableList<Book> viewLates = FXCollections.observableArrayList();
			viewLates.addAll(lateBooks.getAllBooks());
			lates.setItems(viewLates);

			ImageView imgV2 = new ImageView(img2);
			imgV2.setFitHeight(182);
			imgV2.setFitWidth(100);

			VBox vb = new VBox();
			vb.getChildren().addAll(tvUserName2, tvPassWord2, tvFName2, tvLName2, tvAge2, tvAdress2, imgV2);
			VBox.setMargin(imgV2, new Insets(75, 0, 0, 0));

			VBox vb1 = new VBox();
			vb1.getChildren().addAll(tvUserName, tvPassWord, tvFName, tvLName, tvAge, tvAdress);

			HBox btns = new HBox();
			btns.getChildren().addAll(btnBack, btnEdit);
			btns.setAlignment(Pos.BASELINE_RIGHT);
			btns.setPadding(new Insets(0, 0, 5, 0));

			VBox vb2 = new VBox();
			vb2.getChildren().addAll(tvEdit, tvfUserName, tvfPassWord, tvfFName, tvfLName, tvfAge, tvfAdress, btns,
					tvLate, lates);

			VBox.setMargin(tvUserName, new Insets(18, 10, 0, 0));
			VBox.setMargin(tvPassWord, new Insets(12, 10, 0, 0));
			VBox.setMargin(tvFName, new Insets(11, 10, 0, 0));
			VBox.setMargin(tvLName, new Insets(10, 10, 0, 0));
			VBox.setMargin(tvAge, new Insets(10, 10, 0, 0));
			VBox.setMargin(tvAdress, new Insets(10, 10, 0, 0));

			VBox.setMargin(tvEdit, new Insets(0, 0, 0, 20));
			VBox.setMargin(tvLate, new Insets(0, 0, 1, 20));
			HBox.setMargin(btnEdit, new Insets(0, 20, 0, 5));
			VBox.setMargin(lates, new Insets(0, 20, 5, 20));

			VBox.setMargin(tvfUserName, new Insets(0, 20, 5, 20));
			VBox.setMargin(tvfPassWord, new Insets(0, 20, 5, 20));
			VBox.setMargin(tvfFName, new Insets(0, 20, 5, 20));
			VBox.setMargin(tvfLName, new Insets(0, 20, 5, 20));
			VBox.setMargin(tvfAge, new Insets(0, 20, 5, 20));
			VBox.setMargin(tvfAdress, new Insets(0, 20, 5, 20));

			VBox.setMargin(tvUserName2, new Insets(18, 1, 0, 0));
			VBox.setMargin(tvPassWord2, new Insets(12, 1, 0, 0));
			VBox.setMargin(tvFName2, new Insets(11, 1, 0, 0));
			VBox.setMargin(tvLName2, new Insets(10, 1, 0, 0));
			VBox.setMargin(tvAge2, new Insets(10, 1, 0, 0));
			VBox.setMargin(tvAdress2, new Insets(10, 1, 0, 0));

			HBox hb = new HBox();
			hb.getChildren().addAll(vb, vb1, vb2, tableUser);

			hb.setAlignment(Pos.CENTER);
			FileInputStream input = null;
			try {
				input = new FileInputStream("bg.png");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			Image backgroundIMG = new Image(input);
			BackgroundImage backgroundimage = new BackgroundImage(backgroundIMG, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background background = new Background(backgroundimage);
			hb.setBackground(background);
			if (selectedUser.size() != 0)
				sceneAdminView.setRoot(hb);
		});

		btnDelete = new Button("Delete User");
		btnDelete.setOnAction(e -> {
			BookShelf tempBS = new BookShelf();
			ObservableList<User> removedUser = tableUser2.getSelectionModel().getSelectedItems();
			for (User u : removedUser) {
				Library.readBookShelf("userInfo\\", u, bsUser, tableUser, viewBooksUser);
				for (Book b : bsUser.getAllBooks()) {
					tempBS.addBook(b);
				}
				for (Book b : bsAdmin.getAllBooks()) {
					if (tempBS.getAllBooks().contains(b)) {
						b.setAvailable(true);
						b.setDate(null);
						b.setUser(null);
					}
				}
				Library.writeBookShelf("", userAdmin, bsAdmin);
				Library.readBookShelf("", userAdmin, bsAdmin, tableAdmin, viewBooksAdmin);
				try {
					Files.deleteIfExists(Paths.get("userInfo\\" + u.getID() + ".dat"));
				} catch (NoSuchFileException e1) {
					System.out.println("No such file/directory exists");
				} catch (DirectoryNotEmptyException e1) {
					System.out.println("Directory is not empty.");
				} catch (IOException e1) {
					System.out.println("Invalid permissions.");
				}
				us.removeUser(u);
			}
			UserShelf.write(us);
			Library.readUserShelf("users.dat", us, tableUser2, viewBooksUser2);
			tableAdmin.refresh();
		});

		btnClear = new Button("Clear BookShelf");
		btnClear.setOnAction(e -> {
			bsAdmin.clear();
			Library.writeBookShelf("", userAdmin, bsAdmin);
			Library.readBookShelf("", userAdmin, bsAdmin, tableAdmin, viewBooksAdmin);
			try {
				Files.deleteIfExists(Paths.get("isbn.dat"));
			} catch (NoSuchFileException e1) {
				System.out.println("No such file/directory exists");
			} catch (DirectoryNotEmptyException e1) {
				System.out.println("Directory is not empty.");
			} catch (IOException e1) {
				System.out.println("Invalid permissions.");
			}
			FileOutputStream newIsbn;
			try {
				newIsbn = new FileOutputStream("isbn.dat");
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
		});
		btnAddBook.setPrefWidth(100);
		btnRemoveBook.setPrefWidth(100);
		btnSelect.setPrefWidth(100);
		btnDelete.setPrefWidth(100);
		btnClear.setPrefWidth(100);
		btnMM.setPrefWidth(100);
		VBox.setMargin(btnAddBook, new Insets(20, 0, 20, 10));
		VBox.setMargin(btnRemoveBook, new Insets(0, 0, 15, 10));
		VBox.setMargin(btnSelect, new Insets(0, 0, 15, 10));
		VBox.setMargin(btnDelete, new Insets(0, 0, 15, 10));
		VBox.setMargin(btnClear, new Insets(0, 0, 15, 10));
		VBox.setMargin(btnMM, new Insets(0, 0, 15, 10));
		// open Book info
		tfTitle = new TextField("");
		tfAuthor = new TextField("");
		tfCount = new TextField("");
		tfCategory = new TextField("");
		Text tTitle = new Text("Title: ");
		Text tAuthor = new Text("Author: ");
		Text tCount = new Text("Count: ");
		Text tCategory = new Text("Category: ");
		tTitle.setFont(new Font(15));
		tTitle.setFill(Color.PURPLE);
		tTitle.setStroke(Color.CYAN);
		tAuthor.setFont(new Font(15));
		tAuthor.setFill(Color.PURPLE);
		tAuthor.setStroke(Color.CYAN);
		tCount.setFont(new Font(15));
		tCount.setFill(Color.PURPLE);
		tCount.setStroke(Color.CYAN);
		tCategory.setFont(new Font(15));
		tCategory.setFill(Color.PURPLE);
		tCategory.setStroke(Color.CYAN);

		tfSearch = new TextField("");

		trueFalseBox = new ChoiceBox<>();
		trueFalseBox.getItems().addAll(null, true, false);
		trueFalseBox.setValue(null);
		trueFalseBox.setTooltip(new Tooltip("Select the availability"));

		nameBox = new ChoiceBox<>();
		nameBox.getItems().addAll(null, "Title", "Author", "ISBN", "Category");
		nameBox.setValue("Title");
		nameBox.setTooltip(new Tooltip("Select the search type"));

		btnSearch = new Button("Search");
		btnSearch.setOnAction(e -> {
			if (trueFalseBox.getValue() == null) {
				bsSearch.clear();
				if (!(tfSearch.getText().equals(""))) {
					if (nameBox.getValue().equals("Title")) {
						for (Book b : bsAdmin.getBooksByTitle(tfSearch.getText()).getAllBooks()) {
							bsSearch.addBook(b);
						}
					}
					if (nameBox.getValue().equals("Author")) {
						for (Book b : bsAdmin.getBooksByAuthor(tfSearch.getText()).getAllBooks()) {
							bsSearch.addBook(b);
						}
					}
					if (nameBox.getValue().equals("ISBN")) {
						for (Book b : bsAdmin.getBooksByIsbn(tfSearch.getText()).getAllBooks()) {
							bsSearch.addBook(b);
						}
					}
					if (nameBox.getValue().equals("Category")) {
						for (Book b : bsAdmin.getBooksByCategory(tfSearch.getText()).getAllBooks()) {
							bsSearch.addBook(b);
						}
					}
					Library.writeBookShelf("", userDefault, bsSearch);
					Library.readBookShelf("", userDefault, bsSearch, tableAdmin, viewBooksAdmin);
					tableAdmin.refresh();
				}
			} else if (trueFalseBox.getValue() == true) {
				bsSearch.clear();
				if (!(tfSearch.getText().equals(""))) {
					if (nameBox.getValue().equals("Title")) {
						for (Book b : bsAdmin.getBooksByTitle(tfSearch.getText()).getAllBooks()) {
							if (b.isAvailable() == true)
								bsSearch.addBook(b);
						}
					}
					if (nameBox.getValue().equals("Author")) {
						for (Book b : bsAdmin.getBooksByAuthor(tfSearch.getText()).getAllBooks()) {
							if (b.isAvailable() == true)
								bsSearch.addBook(b);
						}
					}
					if (nameBox.getValue().equals("ISBN")) {
						for (Book b : bsAdmin.getBooksByIsbn(tfSearch.getText()).getAllBooks()) {
							if (b.isAvailable() == true)
								bsSearch.addBook(b);
						}
					}
					if (nameBox.getValue().equals("Category")) {
						for (Book b : bsAdmin.getBooksByCategory(tfSearch.getText()).getAllBooks()) {
							if (b.isAvailable() == true)
								bsSearch.addBook(b);
						}
					}
					Library.writeBookShelf("", userDefault, bsSearch);
					Library.readBookShelf("", userDefault, bsSearch, tableAdmin, viewBooksAdmin);
					tableAdmin.refresh();
				} else if (tfSearch.getText().equals("")) {
					for (Book b : bsAdmin.getAllBooks()) {
						if (b.isAvailable() == true)
							bsSearch.addBook(b);
					}
					Library.writeBookShelf("", userDefault, bsSearch);
					Library.readBookShelf("", userDefault, bsSearch, tableAdmin, viewBooksAdmin);
					tableAdmin.refresh();
				}
			} else if (trueFalseBox.getValue() == false) {
				bsSearch.clear();
				if (!(tfSearch.getText().equals(""))) {
					if (nameBox.getValue().equals("Title")) {
						for (Book b : bsAdmin.getBooksByTitle(tfSearch.getText()).getAllBooks()) {
							if (b.isAvailable() == false)
								bsSearch.addBook(b);
						}
					}
					if (nameBox.getValue().equals("Author")) {
						for (Book b : bsAdmin.getBooksByAuthor(tfSearch.getText()).getAllBooks()) {
							if (b.isAvailable() == false)
								bsSearch.addBook(b);
						}
					}
					if (nameBox.getValue().equals("ISBN")) {
						for (Book b : bsAdmin.getBooksByIsbn(tfSearch.getText()).getAllBooks()) {
							if (b.isAvailable() == false)
								bsSearch.addBook(b);
						}
					}
					if (nameBox.getValue().equals("Category")) {
						for (Book b : bsAdmin.getBooksByCategory(tfSearch.getText()).getAllBooks()) {
							if (b.isAvailable() == false)
								bsSearch.addBook(b);
						}
					}
					Library.writeBookShelf("", userDefault, bsSearch);
					Library.readBookShelf("", userDefault, bsSearch, tableAdmin, viewBooksAdmin);
					tableAdmin.refresh();
				} else if (tfSearch.getText().equals("")) {
					for (Book b : bsAdmin.getAllBooks()) {
						if (b.isAvailable() == false)
							bsSearch.addBook(b);
					}
					Library.writeBookShelf("", userDefault, bsSearch);
					Library.readBookShelf("", userDefault, bsSearch, tableAdmin, viewBooksAdmin);
					tableAdmin.refresh();
				}
			}
		});

		img = null;
		img2 = null;
		try {
			img = new Image(new FileInputStream("tree.png"));
			img2 = new Image(new FileInputStream("tree.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView imgV = new ImageView(img);
		imgV.setFitHeight(182);
		imgV.setFitWidth(100);

		VBox firstLine = new VBox();
		firstLine.getChildren().addAll(tTitle, tAuthor, tCategory, tCount);

		VBox secondLine = new VBox();
		secondLine.getChildren().addAll(tvAddb, tfTitle, tfAuthor, tfCategory, tfCount);
		VBox.setMargin(tvAddb, new Insets(0, 0, 1, 5));
		VBox.setMargin(tfTitle, new Insets(0, 0, 10, 5));
		VBox.setMargin(tfAuthor, new Insets(5, 0, 10, 5));
		VBox.setMargin(tfCategory, new Insets(5, 0, 10, 5));
		VBox.setMargin(tfCount, new Insets(5, 0, 10, 5));
		VBox.setMargin(tTitle, new Insets(24, 0, 18, 0));
		VBox.setMargin(tAuthor, new Insets(0, 0, 18, 0));
		VBox.setMargin(tCategory, new Insets(0, 0, 18, 0));
		VBox.setMargin(tCount, new Insets(0, 0, 18, 0));

		VBox searchVB = new VBox();
		searchVB.getChildren().addAll(tvSearchb, tfSearch, nameBox, trueFalseBox, btnSearch);

		HBox addLines = new HBox();
		addLines.getChildren().addAll(firstLine, secondLine);
		BorderPane bp = new BorderPane();
		bp.setRight(addLines);
		bp.setLeft(imgV);
		// close Book info

		VBox buttons = new VBox();
		buttons.getChildren().addAll(btnAddBook, btnRemoveBook, btnSelect, btnDelete, btnClear, btnMM);
		
		VBox userList = new VBox();
		userList.getChildren().addAll(tvUserList, tableUser2);

		HBox viewUsers = new HBox();
		viewUsers.getChildren().addAll(userList, buttons);

		VBox bookVB = new VBox();
		bookVB.setPadding(new Insets(25, 25, 25, 25));
		bookVB.getChildren().addAll(bp, viewUsers);
		HBox.setMargin(tableUser2, new Insets(5, 15, 5, 0));
		buttons.setAlignment(Pos.CENTER);

		VBox booksAdmin = new VBox();
		booksAdmin.getChildren().addAll(tableAdmin);

		VBox booksUser = new VBox();
		booksUser.getChildren().addAll(tableUser);

		HBox tables = new HBox();
		tables.getChildren().addAll(booksAdmin);
		tableAdmin.setPrefHeight(473);
		tableUser.setPrefHeight(473);

		
		
		allPic = new BorderPane();
		allPic.setLeft(bookVB);
		allPic.setCenter(searchVB);
		allPic.setRight(tables);
		searchVB.setPadding(new Insets(35, 10, 0, 0));
		VBox.setMargin(nameBox, new Insets(8, 0, 20, 95));
		VBox.setMargin(trueFalseBox, new Insets(0, 0, 12, 115));
		VBox.setMargin(btnSearch, new Insets(0, 0, 0, 115));
		HBox.setMargin(tableAdmin, new Insets(15, 10, 10, 10));
		FileInputStream input = null;
		try {
			input = new FileInputStream("bg.png");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Image backgroundIMG = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(backgroundIMG, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);
		allPic.setBackground(background);
		sceneAdminView = new Scene(allPic);
		return sceneAdminView;
	}

	public String uniqueISBN() {
		Random r = new Random();
		ArrayList<Integer> randomISBN = new ArrayList<>();

		try (ObjectInputStream intLocationIN = new ObjectInputStream(new FileInputStream("isbn.dat"));) {
			randomISBN = (ArrayList<Integer>) intLocationIN.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		int x = r.nextInt();
		if (x < 0) {
			x *= -1;
		}
		if (randomISBN.contains(x)) {
			x = r.nextInt();
		} else {
			randomISBN.add(x);
		}
		try (ObjectOutputStream bookLocation = new ObjectOutputStream(new FileOutputStream("isbn.dat"));) {
			bookLocation.writeObject(randomISBN);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return String.valueOf(x);
	}

}
