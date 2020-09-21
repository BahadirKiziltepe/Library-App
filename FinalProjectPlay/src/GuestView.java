import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

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

public class GuestView extends GUIsetup implements Serializable {

	private static final long serialVersionUID = -4070360701132823670L;
	private User userAdmin, userDefault;
	private Scene sceneGuestView;
	private Button btnSearch, btnReset;
	private ChoiceBox<Boolean> trueFalseBox;
	private ChoiceBox<String> nameBox;
	private TextField tfSearch;
	private Text tSearchb;

	private TableView<Book> table;
	private ObservableList<Book> viewBooks;
	private BookShelf bsAdmin = new BookShelf(new Book[] {});
	private BookShelf bsSearch = new BookShelf(new Book[] {});

	public GuestView() {
	}

	public Scene GuestViewScene() {
		TableColumn<Book, String> cTitle = new TableColumn<>("Title");
		cTitle.setMinWidth(50);
		cTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

		TableColumn<Book, String> cAuthor = new TableColumn<>("Author");
		cAuthor.setMinWidth(50);
		cAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));

		TableColumn<Book, String> cIsbn = new TableColumn<>("Isbn");
		cIsbn.setMinWidth(50);
		cIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

		TableColumn<Book, String> cCategory = new TableColumn<>("Category");
		cCategory.setMinWidth(50);
		cCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

		TableColumn<Book, String> cAvailable = new TableColumn<>("Availability");
		cAvailable.setMinWidth(85);
		cAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));

		table = new TableView<Book>();
		table.getColumns().addAll(cTitle, cAuthor, cIsbn, cCategory, cAvailable);

		userAdmin = new User();
		userAdmin.setID("adminLib");
		userDefault = new User();
		userDefault.setID("tempLib");
		viewBooks = FXCollections.observableArrayList();
		Library.readBookShelf("", userAdmin, bsAdmin, table, viewBooks);

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
					Library.readBookShelf("", userDefault, bsSearch, table, viewBooks);
					table.refresh();
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
					Library.readBookShelf("", userDefault, bsSearch, table, viewBooks);
					table.refresh();
				} else if (tfSearch.getText().equals("")) {
					for (Book b : bsAdmin.getAllBooks()) {
						if (b.isAvailable() == true)
							bsSearch.addBook(b);
					}
					Library.writeBookShelf("", userDefault, bsSearch);
					Library.readBookShelf("", userDefault, bsSearch, table, viewBooks);
					table.refresh();
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
					Library.readBookShelf("", userDefault, bsSearch, table, viewBooks);
					table.refresh();
				} else if (tfSearch.getText().equals("")) {
					for (Book b : bsAdmin.getAllBooks()) {
						if (b.isAvailable() == false)
							bsSearch.addBook(b);
					}
					Library.writeBookShelf("", userDefault, bsSearch);
					Library.readBookShelf("", userDefault, bsSearch, table, viewBooks);
					table.refresh();
				}
			}
		});

		btnReset = new Button("Reset");
		btnReset.setOnAction(e -> {
			trueFalseBox.setValue(null);
			tfSearch.setText("");
			Library.readBookShelf("", userAdmin, bsAdmin, table, viewBooks);
			table.refresh();
		});
		tSearchb = new Text("Search Book");
		tSearchb.setFont(new Font(75));
		tSearchb.setFill(Color.PURPLE);
		tSearchb.setStroke(Color.CYAN);
		HBox allPic = new HBox();
		FileInputStream input = null;
		Image img1 = null;
		try {
			input = new FileInputStream("bg.png");
			img1 = new Image(new FileInputStream("guest.png"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		ImageView imgV1 = new ImageView(img1);

		HBox hb = new HBox();
		hb.getChildren().addAll(tfSearch, nameBox, trueFalseBox, btnSearch, btnReset,
				GUIsetup.returnMainScreen());
		hb.setPadding(new Insets(40, 0, 0, 0));
		
		VBox main = new VBox();
		main.getChildren().addAll(tSearchb, hb, imgV1);
		main.setAlignment(Pos.CENTER);
		VBox.setMargin(imgV1, new Insets(40, 0, 0, 80));

		imgV1.setFitHeight(img1.getHeight() / 1.3);
		imgV1.setFitWidth(img1.getWidth() / 1.3);
//		imgV1.setX(800);
//		imgV1.setY(800);
		BorderPane bp = new BorderPane();
		bp.setLeft(table);
		bp.setRight(main);
		Image backgroundIMG = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(backgroundIMG, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);
		allPic.getChildren().addAll(bp);
		allPic.setAlignment(Pos.CENTER);
		allPic.setBackground(background);
		sceneGuestView = new Scene(allPic);
		return sceneGuestView;
	}

}
