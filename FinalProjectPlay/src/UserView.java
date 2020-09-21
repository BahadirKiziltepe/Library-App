import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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

public class UserView extends GUIsetup implements Serializable {

	private static final long serialVersionUID = 1782402611277150420L;
	private Scene sceneUserView;
	private HBox allPic;
	private Button btnAddBook, btnRemoveBook, btnSettings, btnSearch, btnReset, btnMM;
	private User userAdmin, currentUser, userDefault;
	private TextField tvfUserName, tvfPassWord, tvfFName, tvfLName, tvfAge, tvfAdress, tfSearch;
	private Text tvUserName, tvPassWord, tvFName, tvLName, tvAge, tvAdress, tvUserName2, tvPassWord2, tvFName2,
			tvLName2, tvAge2, tvAdress2, tTitle1, tTitle2, tSearchb, tvLateb, tvSettings, tvSettings2;
	private Image img1, img2;
	private ImageView imgV1, imgV2;

	private ChoiceBox<Boolean> trueFalseBox;
	private ChoiceBox<String> nameBox;
	private TableView<Book> tableAdmin;
	private ObservableList<Book> viewBooksAdmin;
	private BookShelf bsAdmin = new BookShelf(new Book[] {});

	private TableView<Book> lates;;
	private TableView<Book> tableUser;
	private ObservableList<Book> viewBooksUser;
	private BookShelf bsUser = new BookShelf(new Book[] {});
	private BookShelf bsSearch = new BookShelf(new Book[] {});
	private BookShelf lateBooks = new BookShelf(new Book[] {});
	private UserShelf us = new UserShelf(new User[] {});

	public UserView() {
	}

	public Scene UserViewScene() {
		UserShelf.read(us);

		TableColumn<Book, String> caTitle = new TableColumn<>("Title");
		caTitle.setMinWidth(85);
		caTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

		TableColumn<Book, String> caAuthor = new TableColumn<>("Author");
		caAuthor.setMinWidth(85);
		caAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));

		TableColumn<Book, String> caIsbn = new TableColumn<>("Isbn");
		caIsbn.setMaxWidth(100);
		caIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

		TableColumn<Book, String> caCategory = new TableColumn<>("Category");
		caCategory.setMinWidth(85);
		caCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

		TableColumn<Book, Boolean> caAvailable = new TableColumn<>("Availability");
		caAvailable.setMinWidth(85);
		caAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));

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
		tableAdmin.getColumns().addAll(caTitle, caAuthor, caIsbn, caCategory, caAvailable);

		tableUser = new TableView<Book>();
		tableUser.getColumns().addAll(cuTitle, cuAuthor, cuIsbn, cuCategory, cuDate, cuReturnDate);

		userAdmin = new User();
		userAdmin.setID("adminLib");
		userDefault = new User();
		userDefault.setID("tempLib");
		Library.readBookShelf("", userAdmin, bsAdmin, tableAdmin, viewBooksAdmin);
		Library.readBookShelf("userInfo\\", currentUser, bsUser, tableUser, viewBooksUser);

		tvUserName = new Text();
		tvPassWord = new Text();
		tvFName = new Text();
		tvLName = new Text();
		tvAge = new Text();
		tvAdress = new Text();
		tvUserName.setFont(new Font(20));
		tvUserName.setFill(Color.PURPLE);
		tvUserName.setStroke(Color.CYAN);
		tvPassWord.setFont(new Font(20));
		tvPassWord.setFill(Color.PURPLE);
		tvPassWord.setStroke(Color.CYAN);
		tvFName.setFont(new Font(20));
		tvFName.setFill(Color.PURPLE);
		tvFName.setStroke(Color.CYAN);
		tvLName.setFont(new Font(20));
		tvLName.setFill(Color.PURPLE);
		tvLName.setStroke(Color.CYAN);
		tvAge.setFont(new Font(20));
		tvAge.setFill(Color.PURPLE);
		tvAge.setStroke(Color.CYAN);
		tvAdress.setFont(new Font(20));
		tvAdress.setFill(Color.PURPLE);
		tvAdress.setStroke(Color.CYAN);
		tvfUserName = new TextField("");
		tvfPassWord = new TextField("");
		tvfFName = new TextField("");
		tvfLName = new TextField("");
		tvfAge = new TextField("");
		tvfAdress = new TextField("");

		tvUserName2 = new Text("Username: ");
		tvPassWord2 = new Text("Password: ");
		tvFName2 = new Text("First Name: ");
		tvLName2 = new Text("Last Name: ");
		tvAge2 = new Text("Age: ");
		tvAdress2 = new Text("Adress: ");
		tvUserName2.setFont(new Font(20));
		tvUserName2.setFill(Color.PURPLE);
		tvUserName2.setStroke(Color.CYAN);
		tvPassWord2.setFont(new Font(20));
		tvPassWord2.setFill(Color.PURPLE);
		tvPassWord2.setStroke(Color.CYAN);
		tvFName2.setFont(new Font(20));
		tvFName2.setFill(Color.PURPLE);
		tvFName2.setStroke(Color.CYAN);
		tvLName2.setFont(new Font(20));
		tvLName2.setFill(Color.PURPLE);
		tvLName2.setStroke(Color.CYAN);
		tvAge2.setFont(new Font(20));
		tvAge2.setFill(Color.PURPLE);
		tvAge2.setStroke(Color.CYAN);
		tvAdress2.setFont(new Font(20));
		tvAdress2.setFill(Color.PURPLE);
		tvAdress2.setStroke(Color.CYAN);

		tvSettings = new Text("User Info");
		tvSettings.setFont(new Font(35));
		tvSettings.setFill(Color.PURPLE);
		tvSettings.setStroke(Color.CYAN);
		tvSettings2 = new Text("Edit Profile");
		tvSettings2.setFont(new Font(35));
		tvSettings2.setFill(Color.PURPLE);
		tvSettings2.setStroke(Color.CYAN);
		tvLateb = new Text("Late Books");
		tvLateb.setFont(new Font(15));
		tvLateb.setFill(Color.PURPLE);
		tvLateb.setStroke(Color.CYAN);

		try {
			img1 = new Image(new FileInputStream("reg1.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		imgV1 = new ImageView(img1);
		imgV1.setFitWidth(200);
		imgV1.setFitHeight(392);
//		imgV1.setX(650);
//		imgV1.setY(115);
		try {
			img2 = new Image(new FileInputStream("reg2.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		imgV2 = new ImageView(img2);
		imgV2.setFitWidth(250);
		imgV2.setFitHeight(390);
		// imgV2.setX(650);
//		imgV2.setY(115);

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
		lates = new TableView<>();
		lates.getColumns().addAll(lTitle, lAuthor, lIsbn, lCategory);
		for (Book b : bsUser.getAllBooks()) {
			if (b.getDate().after(b.getReturnDate())) {
				lateBooks.addBook(b);
			}
		}
		ObservableList<Book> viewLates = FXCollections.observableArrayList();
		viewLates.addAll(lateBooks.getAllBooks());
		lates.setItems(viewLates);

		btnMM = GUIsetup.returnMainScreen();
		btnAddBook = new Button("Barrow Book");
		btnAddBook.setOnAction(e -> {
			Calendar dueDate = Calendar.getInstance();
			Book tempB = new Book();
			ObservableList<Book> addList = tableAdmin.getSelectionModel().getSelectedItems();
			for (Book b : addList) {
				if (b.isAvailable()) {
					tempB = b;
					b.setAvailable(false);
					b.setDate(new Date());
					dueDate.setTime(b.getDate());
					dueDate.add(Calendar.DATE, 5);
					b.setReturnDate(dueDate.getTime());
					bsUser.addBook(b);
				}
			}
			Library.writeBookShelf("userInfo\\", currentUser, bsUser);
			Library.readBookShelf("userInfo\\", currentUser, bsUser, tableUser, viewBooksUser);

			for (Book b : bsAdmin.getAllBooks()) {
				if (tempB.equals(b)) {
					b.setAvailable(false);
					b.setUser(currentUser);
					Library.writeBookShelf("", userAdmin, bsAdmin);
					Library.readBookShelf("", userAdmin, bsAdmin, tableAdmin, viewBooksAdmin);
				}
			}
			tableAdmin.refresh();
		});
		btnRemoveBook = new Button("Return Book");
		btnRemoveBook.setOnAction(e -> {
			Book tempB = null;

			ObservableList<Book> removeList = tableUser.getSelectionModel().getSelectedItems();
			for (Book b : removeList) {
				tempB = b;
				b.setAvailable(true);
				bsUser.removeBook(b);
				lateBooks.removeBook(b);
				viewLates.removeAll(b);
			}
			Library.writeBookShelf("userInfo\\", currentUser, bsUser);
			Library.readBookShelf("userInfo\\", currentUser, bsUser, tableUser, viewBooksUser);

			for (Book b : bsAdmin.getAllBooks()) {
				if (tempB.equals(b)) {
					b.setAvailable(true);
					b.setDate(null);
					b.setReturnDate(null);
					b.setUser(null);
					Library.writeBookShelf("", userAdmin, bsAdmin);
					Library.readBookShelf("", userAdmin, bsAdmin, tableAdmin, viewBooksAdmin);
				}
			}
			lates.refresh();
			tableAdmin.refresh();
		});
		btnSettings = new Button("User Settings");
		btnSettings.setOnAction(e -> {
			Button btnBack = new Button("Back");
			Button btnEdit = new Button("Confirm");

			tvUserName.setText(currentUser.getUserName());
			tvPassWord.setText(currentUser.getPassWord());
			tvFName.setText(currentUser.getFirstName());
			tvLName.setText(currentUser.getLastName());
			tvAge.setText(currentUser.getAge());
			tvAdress.setText(currentUser.getAddress());

			btnBack.setOnAction(e1 -> {
				sceneUserView.setRoot(allPic);
			});

			btnEdit.setOnAction(e1 -> {
				if (!(tvfUserName.getText().equals(""))) {
					currentUser.setUserName(tvfUserName.getText());
				}
				if (!(tvfPassWord.getText().equals(""))) {
					currentUser.setPassWord(tvfPassWord.getText());
				}
				if (!(tvfFName.getText().equals(""))) {
					currentUser.setFirstName(tvfFName.getText());
				}
				if (!(tvfLName.getText().equals(""))) {
					currentUser.setLastName(tvfLName.getText());
				}
				if (!(tvfAge.getText().equals(""))) {
					currentUser.setAge(tvfAge.getText());
				}
				if (!(tvfAdress.getText().equals(""))) {
					currentUser.setAddress(tvfAdress.getText());
				}
				tvUserName.setText(currentUser.getUserName());
				tvPassWord.setText(currentUser.getPassWord());
				tvFName.setText(currentUser.getFirstName());
				tvLName.setText(currentUser.getLastName());
				tvAge.setText(currentUser.getAge());
				tvAdress.setText(currentUser.getAddress());
				for (User u : us.getAllUsers()) {
					if (u.getID().equals(currentUser.getID())) {
						us.getAllUsers().set(us.getAllUsers().indexOf(u), currentUser);
					}
				}
				UserShelf.write(us);
				UserShelf.read(us);
				for (Book b : bsAdmin.getAllBooks()) {
					if (bsUser.getAllBooks().contains(b)) {
						b.setUser(currentUser);
					}
				}
				Library.writeBookShelf("userInfo\\", currentUser, bsUser);
				Library.readBookShelf("userInfo\\", currentUser, bsUser, tableUser, viewBooksUser);
				Library.writeBookShelf("", userAdmin, bsAdmin);
				Library.readBookShelf("", userAdmin, bsAdmin, tableAdmin, viewBooksAdmin);
				tableUser.refresh();
				tableAdmin.refresh();
			});
			VBox vb = new VBox();
			vb.getChildren().addAll(tvUserName2, tvPassWord2, tvFName2, tvLName2, tvAge2, tvAdress2);
			VBox.setMargin(tvUserName2, new Insets(0, 0, 2, 1));
			VBox.setMargin(tvPassWord2, new Insets(0, 0, 2, 1));
			VBox.setMargin(tvFName2, new Insets(0, 0, 2, 1));
			VBox.setMargin(tvLName2, new Insets(0, 0, 2, 1));
			VBox.setMargin(tvAge2, new Insets(0, 0, 2, 1));
			VBox.setMargin(tvAdress2, new Insets(0, 2, 0, 1));

			VBox vb1 = new VBox();
			vb1.getChildren().addAll(tvUserName, tvPassWord, tvFName, tvLName, tvAge, tvAdress);
			VBox.setMargin(tvUserName, new Insets(0, 0, 2, 0));
			VBox.setMargin(tvPassWord, new Insets(0, 0, 2, 0));
			VBox.setMargin(tvFName, new Insets(0, 0, 2, 0));
			VBox.setMargin(tvLName, new Insets(0, 0, 2, 0));
			VBox.setMargin(tvAge, new Insets(0, 0, 2, 0));
			VBox.setMargin(tvAdress, new Insets(0, 0, 2, 0));

			HBox btnsSettings = new HBox();
			btnsSettings.getChildren().addAll(btnBack, btnEdit);
			HBox.setMargin(btnBack, new Insets(5, 10, 0, 100));
			HBox.setMargin(btnEdit, new Insets(5, 0, 0, 0));

			VBox vb2 = new VBox();
			vb2.getChildren().addAll(tvfUserName, tvfPassWord, tvfFName, tvfLName, tvfAge, tvfAdress, btnsSettings);
			VBox.setMargin(tvfUserName, new Insets(0, 0, 5, 5));
			VBox.setMargin(tvfPassWord, new Insets(0, 0, 5, 5));
			VBox.setMargin(tvfFName, new Insets(0, 0, 5, 5));
			VBox.setMargin(tvfLName, new Insets(0, 0, 5, 5));
			VBox.setMargin(tvfAge, new Insets(0, 0, 5, 5));
			VBox.setMargin(tvfAdress, new Insets(0, 0, 0, 5));
			VBox.setMargin(tvSettings, new Insets(100, 0, 0, 0));
			VBox.setMargin(tvSettings2, new Insets(100, 0, 0, 0));
			
			VBox tfEdit = new VBox();
			tfEdit.getChildren().addAll(tvSettings2, vb2);

			HBox hb2 = new HBox();
			hb2.getChildren().addAll(vb, vb1);

			VBox vbEdit = new VBox();
			vbEdit.getChildren().addAll(tvSettings, hb2);

			HBox hb = new HBox();
			hb.getChildren().addAll(imgV1, vbEdit, tfEdit, imgV2);

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
			sceneUserView.setRoot(hb);
		});

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

		btnReset = new Button("Reset");
		btnReset.setOnAction(e -> {
			trueFalseBox.setValue(null);
			tfSearch.setText("");
			Library.readBookShelf("", userAdmin, bsAdmin, tableAdmin, viewBooksAdmin);
			tableAdmin.refresh();
		});

		tSearchb = new Text("Search Book");
		tSearchb.setFont(new Font(15));
		tSearchb.setFill(Color.PURPLE);
		tSearchb.setStroke(Color.CYAN);
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(tSearchb, btnSettings, btnMM);
		hb1.setAlignment(Pos.BASELINE_RIGHT);
		HBox.setMargin(btnMM, new Insets(5, 10, 0, 5));
		HBox.setMargin(tSearchb, new Insets(0, 200, 0, 0));
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(nameBox, trueFalseBox, btnSearch, btnReset, btnAddBook, btnRemoveBook);
		hb2.setPadding(new Insets(10));
		HBox.setMargin(nameBox, new Insets(0, 5, 0, 0));
		HBox.setMargin(trueFalseBox, new Insets(0, 5, 0, 0));
		HBox.setMargin(btnSearch, new Insets(0, 5, 0, 0));
		HBox.setMargin(btnReset, new Insets(0, 5, 0, 0));
		HBox.setMargin(btnAddBook, new Insets(0, 5, 0, 0));
		VBox buttons = new VBox();
		buttons.getChildren().addAll(hb1, tfSearch, hb2, tvLateb, lates);
		VBox.setMargin(tfSearch, new Insets(5, 10, 0, 10));
		VBox.setMargin(tvLateb, new Insets(0, 0, 0, 10));
		VBox.setMargin(lates, new Insets(1, 10, 10, 10));
		tTitle1 = new Text("Main Library");
		tTitle1.setFont(new Font(15));
		tTitle1.setFill(Color.PURPLE);
		tTitle1.setStroke(Color.CYAN);
		tTitle2 = new Text("User Library");
		tTitle2.setFont(new Font(15));
		tTitle2.setFill(Color.PURPLE);
		tTitle2.setStroke(Color.CYAN);
		HBox titles = new HBox();
		titles.getChildren().addAll(tTitle1, tTitle2);
		HBox.setMargin(tTitle1, new Insets(0, 0, 0, 20));
		HBox.setMargin(tTitle2, new Insets(0, 0, 0, 350));
		BorderPane bp = new BorderPane();
		bp.setLeft(buttons);
		HBox hbTable = new HBox();
		hbTable.getChildren().addAll(tableAdmin, tableUser);
		tableUser.setPrefWidth(400);
		tableUser.setPrefHeight(500);
		VBox combineTables = new VBox();
		combineTables.getChildren().addAll(titles, hbTable);
		bp.setRight(combineTables);
		allPic = new HBox();
		allPic.getChildren().addAll(bp);
		HBox.setMargin(tableAdmin, new Insets(1, 0, 10, 20));
		HBox.setMargin(tableUser, new Insets(1, 10, 10, 10));
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
		sceneUserView = new Scene(allPic, 1240, 580);
//		GUIsetup.mainSTG.setHeight(580);
		return sceneUserView;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
