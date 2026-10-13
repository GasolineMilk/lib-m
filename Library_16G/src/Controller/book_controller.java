package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class book_controller implements Initializable {
	database_connection db = new database_connection();
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	ObservableList<String> alist = FXCollections.observableArrayList();
	ObservableList<String> plist = FXCollections.observableArrayList();
	ObservableList<String> clist = FXCollections.observableArrayList();
	ObservableList<book> blist = FXCollections.observableArrayList();
	book b;

	
	@FXML
	private Button btn_pdf;
	
	@FXML
	private Button btn_delete;

	@FXML
    private Button btn_update;
	
	@FXML
	private Button btn_clear;

	@FXML
	private Button btn_save;

	@FXML
	private Button btn_upload;
	
	@FXML
	private Button btn_read;


	@FXML
	private TableColumn<?, ?> col_book_id;

	@FXML
	private TableColumn<?, ?> col_coipes;

	@FXML
	private TableColumn<?, ?> col_description;

	@FXML
	private TableColumn<?, ?> col_img;

	@FXML
	private TableColumn<?, ?> col_isbn;

	@FXML
	private TableColumn<?, ?> col_publish_year;

	@FXML
	private TableColumn<?, ?> col_title;
	
	@FXML
    private TableColumn<?, ?> col_pdf;

	@FXML
	private ComboBox<String> combo_author;

	@FXML
	private ComboBox<String> combo_publish;

	@FXML
	private ComboBox<String> combo_cate;

	@FXML
	private ImageView image_view;

	@FXML
	private TableView<book> tbl_book;

	@FXML
	private TextField txt_copies;

	@FXML
	private TextArea txt_des;

	@FXML
	private TextField txt_publish_year;

	@FXML
	private TextField txt_title;

	@FXML
	private TextField txtisbn;
	
	@FXML
    private TextField txt_arrival_copies;
	
	@FXML
    private TextField txt_pdf;

	 @FXML
	 private Label lbl_arrival_coipes;
	
	@FXML
    private ComboBox<String> combo_select_Category;

    @FXML
    private ComboBox<String> combo_select_author;

    @FXML
    private ComboBox<String> combo_select_publisher;
    
    @FXML
    void click_select_Category(ActionEvent event) throws ClassNotFoundException, SQLException 
    {
    	blist.clear();

    	int cate_id = name_to_id_category(combo_select_Category.getSelectionModel().getSelectedItem());
    	
    	con = db.getDB();
		pstmt = con.prepareStatement("SELECT * FROM book WHERE category_id = ?");
    	pstmt.setInt(1, cate_id);
    	
    	rs = pstmt.executeQuery();
    	
    	while(rs.next()) 
    	{
    		int bid = rs.getInt(1);
    		String isbn = rs.getString(2);
    		String title = rs.getString(3);
    		int author_id = rs.getInt(4);
    		int published_year = rs.getInt(5);
    		int category_id = rs.getInt(6);
    		String discription = rs.getString(7);
    		int copies = rs.getInt(8);
    		int publisher_id = rs.getInt(9);
    		String image = rs.getString(10);
    		String pdf = rs.getString(11);
    		
    		blist.add(new book(bid, isbn, title, author_id, published_year, category_id, discription, copies, publisher_id, image, pdf));
    	}
    	tbl_book.setItems(blist);
		con.close();
    }

    @FXML
    void click_select_author(ActionEvent event) throws ClassNotFoundException, SQLException 
    {
		blist.clear();

    	int aut_id = name_to_id_author(combo_select_author.getSelectionModel().getSelectedItem());
    	
    	con = db.getDB();
		pstmt = con.prepareStatement("SELECT * FROM book WHERE author_id = ?");
    	pstmt.setInt(1, aut_id);
    	
    	rs = pstmt.executeQuery();
    	
    	while(rs.next()) 
    	{
    		int bid = rs.getInt(1);
    		String isbn = rs.getString(2);
    		String title = rs.getString(3);
    		int author_id = rs.getInt(4);
    		int published_year = rs.getInt(5);
    		int category_id = rs.getInt(6);
    		String discription = rs.getString(7);
    		int copies = rs.getInt(8);
    		int publisher_id = rs.getInt(9);
    		String image = rs.getString(10);
    		String pdf = rs.getString(11);
    		
    		blist.add(new book(bid, isbn, title, author_id, published_year, category_id, discription, copies, publisher_id, image, pdf));
    	}
    	tbl_book.setItems(blist);
		con.close();
    }

    @FXML
    void click_select_publisher(ActionEvent event) throws ClassNotFoundException, SQLException 
    {
    	blist.clear();

    	int pub_id = name_to_id_publisher(combo_select_publisher.getSelectionModel().getSelectedItem());
    	
    	con = db.getDB();
		pstmt = con.prepareStatement("SELECT * FROM book WHERE publisher_id = ?");
    	pstmt.setInt(1, pub_id);
    	
    	rs = pstmt.executeQuery();
    	
    	while(rs.next()) 
    	{
    		int bid = rs.getInt(1);
    		String isbn = rs.getString(2);
    		String title = rs.getString(3);
    		int author_id = rs.getInt(4);
    		int published_year = rs.getInt(5);
    		int category_id = rs.getInt(6);
    		String discription = rs.getString(7);
    		int copies = rs.getInt(8);
    		int publisher_id = rs.getInt(9);
    		String image = rs.getString(10);
    		String pdf = rs.getString(11);
    		
    		blist.add(new book(bid, isbn, title, author_id, published_year, category_id, discription, copies, publisher_id, image, pdf));
    	}
    	tbl_book.setItems(blist);
		con.close();
    }
    @FXML
    void click_select(ActionEvent event) throws ClassNotFoundException, SQLException 
    {
    	ComboBox cb = (ComboBox) event.getSource();
    	if (cb.getValue() == null) {
    	    return;
    	}

    	String value = cb.getValue().toString();
    	
    	if(value.equals(combo_select_author.getValue())) 
    	{
    		combo_select_Category.setValue(null);
    		combo_select_publisher.setValue(null);
    		
    		 blist.clear();
    	     getbook("author", "author_id", value);
    	}
    	else if(value.equals(combo_select_publisher.getValue())) 
    	{
    		combo_select_author.setValue(null);
    		combo_select_Category.setValue(null);
    		
    		blist.clear();
            getbook("publisher", "publisher_id", value);
    	}
    	else if(value.equals(combo_select_Category.getValue())) 
    	{
    		combo_select_author.setValue(null);
    		combo_select_publisher.setValue(null);
    		
    		blist.clear();
            getbook("category", "category_id", value);
    	}
    	tbl_book.setItems(blist);
    	
    }

	@FXML
	void click_clear(ActionEvent event) 
	{
		txtisbn.clear();
	    txt_title.clear();
	    txt_publish_year.clear();
	    txt_copies.clear();
	    txt_arrival_copies.clear();
	    txt_pdf.clear();

	    txt_des.clear();

	    combo_author.getSelectionModel().clearSelection();
	    combo_publish.getSelectionModel().clearSelection();
	    combo_cate.getSelectionModel().clearSelection();

	    combo_select_Category.getSelectionModel().clearSelection();
	    combo_select_author.getSelectionModel().clearSelection();
	    combo_select_publisher.getSelectionModel().clearSelection();
	    

	    image_view.setImage(null);
	}

	
	
	@FXML
	void click_save(ActionEvent event) throws ClassNotFoundException, SQLException 
	{
		Button btn = (Button)event.getSource();
		con = db.getDB();
		
		if (btn.getText().equals("save")) 
		{
			
			String t = txt_title.getText();
			String isbn = txtisbn.getText();
			String a = combo_author.getSelectionModel().getSelectedItem();
			int y = Integer.parseInt(txt_publish_year.getText());
			String c = combo_cate.getSelectionModel().getSelectedItem();
			String d = txt_des.getText();
			int cpoies = Integer.parseInt(txt_copies.getText());
			String p = combo_publish.getSelectionModel().getSelectedItem();
			
			String img_f = "";
			if (image_view.getImage() != null) 
			{
	    	    img_f = image_view.getImage().getUrl();
	    	}
			String pdf_path = "";

			if (txt_pdf.getText() != null && !txt_pdf.getText().isBlank()) 
			{
			    pdf_path = txt_pdf.getText();
			}
			
			
			try {
				int aid = name_to_id_author(a);
				
				int cid = name_to_id_category(c);
				
				int pid = name_to_id_publisher(p);
				
				int bid = 0;
				
				con = db.getDB();
				pstmt = con.prepareStatement("SELECT publisher_id FROM publisher WHERE name = ?");
				pstmt.setString(1,p);
				rs = pstmt.executeQuery();
				if(rs.next())
					pid = rs.getInt(1);
				
				pstmt = con.prepareStatement("INSERT INTO book (title, isbn, author_id, published_year, category_id, discription, Copies, publisher_id, image, pdf_uri) VALUES (?,?,?,?,?,?,?,?,?,?)");
				
				pstmt.setString(1, t);
				pstmt.setString(2, isbn);
				pstmt.setInt(3, aid);
				pstmt.setInt(4, y);
				pstmt.setInt(5, cid);
				pstmt.setString(6, d);
				pstmt.setInt(7, cpoies);
				pstmt.setInt(8, pid);
				pstmt.setString(9, img_f);
				pstmt.setString(10, pdf_path);
				
				result = pstmt.executeUpdate();
				if(result > 0)
					System.out.println("Insert book done!");	
				
				con.close();
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getbook();
		}
		else if (btn.getText().equals("Edit")) {

		    try {
		        String title = txt_title.getText();
		        String isbn = txtisbn.getText();
		        String description = txt_des.getText();

		        if (title.isEmpty() || isbn.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Title and ISBN cannot be empty!");
		            return;
		        }

		        int year = Integer.parseInt(txt_publish_year.getText());
		        
		        int arrivalCopies = Integer.parseInt(txt_arrival_copies.getText());

		        if (arrivalCopies <= 0) {
		            JOptionPane.showMessageDialog(null, "Arrival copies must be greater than 0!");
		            return;
		        }


		        String author = combo_author.getValue();
		        String category = combo_cate.getValue();
		        String publisher = combo_publish.getValue();

		        if (author == null || category == null || publisher == null) 
		        {
		            JOptionPane.showMessageDialog(null, "Please select author, category and publisher!");
		            return;
		        }

		        int authorId = name_to_id_author(author);
		        int categoryId = name_to_id_category(category);
		        int publisherId = name_to_id_publisher(publisher);


		        String imgPath = b.getImage();

		        if (image_view.getImage() != null) 
		        {
		            imgPath = image_view.getImage().getUrl();
		        }
		        
		        String pdf_path = "";

				if (txt_pdf.getText() != null && !txt_pdf.getText().isBlank()) 
				{
				    pdf_path = txt_pdf.getText();
				}


		        int totalCopies = b.getCopies() + arrivalCopies;

		      
		        pstmt = con.prepareStatement("UPDATE book SET title=?, isbn=?, author_id=?, published_year=?, category_id=?, discription=?, copies=?, publisher_id=?, image=? , pdf_uri=? WHERE book_id=?");

		        pstmt.setString(1, title);
		        pstmt.setString(2, isbn);
		        pstmt.setInt(3, authorId);
		        pstmt.setInt(4, year);
		        pstmt.setInt(5, categoryId);
		        pstmt.setString(6, description);
		        pstmt.setInt(7, totalCopies);
		        pstmt.setInt(8, publisherId);
		        pstmt.setString(9, imgPath);
		        pstmt.setString(10, pdf_path);
		        pstmt.setInt(11, b.getBook_id());

		        int updated = pstmt.executeUpdate();


		        if (updated > 0) 
		        {
		            pstmt = con.prepareStatement("UPDATE book_copies SET copies=? WHERE book_id=?");

		            pstmt.setInt(1, totalCopies);
		            pstmt.setInt(2, b.getBook_id());
		            pstmt.executeUpdate();

		            JOptionPane.showMessageDialog(null,"Book updated successfully!");
		        }

		    } 
		    catch (NumberFormatException e) 
		    {
		        JOptionPane.showMessageDialog(null, "Please enter a valid year and arrival copies!");
		        return;

		    } 
		    catch (SQLException e) 
		    {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Database update failed!");
		        return;
		    }

		    getbook();
		}
		blist.clear();
    	getbook();
    	tbl_book.setItems(blist);
    	con.close();
	}

	@FXML
    void click_pdf(ActionEvent event) 
	{
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose PDF File");

		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

		Stage stage = (Stage) ((Node) event.getSource())
		        .getScene().getWindow();

		File file = chooser.showOpenDialog(stage);

		if (file != null) 
		{
			txt_pdf.setText(file.getAbsolutePath());
		}
	    
    }
	
	@FXML
	void click_upload(ActionEvent event) 
	{
		FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Image");

        chooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		File file = chooser.showOpenDialog(stage);
		
		if (file != null) 
		{
			Image image;
			try {
				image = new Image(file.toURI().toString());
				image_view.setImage(image);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	@FXML
    void click_delete(ActionEvent event) 
	{
		b = tbl_book.getSelectionModel().getSelectedItem();

	    if (b == null) {
	        JOptionPane.showMessageDialog(null, "Please select a book!");
	        return;
	    }

	    int confirm = JOptionPane.showConfirmDialog(
	        null,
	        "Are you sure you want to delete \"" + b.getTitle() + "\"?",
	        "Confirm Delete",
	        JOptionPane.YES_NO_OPTION
	    );

	    if (confirm != JOptionPane.YES_OPTION) {
	        return;
	    }

	    try {
	        con = db.getDB();

	        pstmt = con.prepareStatement("DELETE FROM book_copies WHERE book_id = ?");
	        pstmt.setInt(1, b.getBook_id());
	        pstmt.executeUpdate();

	        pstmt = con.prepareStatement("DELETE FROM book WHERE book_id = ?");
	        pstmt.setInt(1, b.getBook_id());

	        int result = pstmt.executeUpdate();

	        if (result > 0) 
	        {
	            JOptionPane.showMessageDialog(null, "Book deleted successfully!");
	        }

	        con.close();

	        getbook();

	        click_clear(null);

	    } 
	    catch (ClassNotFoundException | SQLException e) 
	    {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Failed to delete book!");
	    }
    }
	
   @FXML
    void click_read(ActionEvent event) throws IOException 
   	{
	   b = tbl_book.getSelectionModel().getSelectedItem();
	   
	   if(b == null) 
	   {
		   JOptionPane.showMessageDialog(null, "No book selected!");
		   return;
	   }
		   
	   
	   FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/reader.fxml"));

	   loader.setControllerFactory(controllerClass ->new reader_controller(b.getBook_id()));

	   Parent root = loader.load();

	   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

	   stage.setScene(new Scene(root));
	   stage.show();
    }
	
	@FXML
    void click_update(ActionEvent event) throws ClassNotFoundException, SQLException 
	{
		image_view.setImage(null);
		txt_arrival_copies.setText("");
		
		int author_id = 0;
		int category_id = 0;
		int publisher_id = 0;
		lbl_arrival_coipes.setVisible(true);
		
		txt_arrival_copies.setVisible(true);
		
		btn_save.setText("Edit");
		
		b = tbl_book.getSelectionModel().getSelectedItem();
		if(b != null) 
		{
			txt_title.setText(b.getTitle());
			txt_copies.setText(String.valueOf(b.getCopies()));
			txt_des.setText(String.valueOf(b.getDiscription()));
			txtisbn.setText(b.getIsbn());
			txt_publish_year.setText(String.valueOf(b.getPublished_year()));
			String imgPath = b.getImage();

			if (imgPath != null && !imgPath.isBlank()) {
			    File file = new File(imgPath);

			    if (file.exists()) {
			        image_view.setImage(new Image(file.toURI().toString()));
			    } else {
			        image_view.setImage(new Image(b.getImage()));
			        System.out.println("Image not found: " + imgPath);
			    }
			} else {
			    image_view.setImage(new Image(b.getImage()));
			}
			
			String pdfPath = b.getPdf_uri();

			if (pdfPath != null && !pdfPath.isBlank()) {
			    File file = new File(pdfPath);

			    if (file.exists()) {
			        txt_pdf.setText(pdfPath);
			    } else {
			        txt_pdf.clear();
			        System.out.println("PDF not found: " + pdfPath);
			    }
			} else {
			    txt_pdf.clear();
			}
			
			con = db.getDB();
			pstmt = con.prepareStatement("SELECT author_id, category_id, publisher_id from book where book_id  = ?");
			pstmt.setInt(1, b.getBook_id());
			rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				author_id = rs.getInt(1);
				category_id = rs.getInt(2);
				publisher_id = rs.getInt(3);
			}
			
			try {
				combo_author.setValue(id_to_name_author(author_id));
				combo_cate.setValue(id_to_name_category(category_id));
				combo_publish.setValue(id_to_name_publisher(publisher_id));
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Please select a Book!");
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
		lbl_arrival_coipes.setVisible(false);
		
		txt_arrival_copies.setVisible(false);
		
		col_book_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));

		col_coipes.setCellValueFactory(new PropertyValueFactory<>("Copies"));

		col_description.setCellValueFactory(new PropertyValueFactory<>("discription"));

		col_img.setCellValueFactory(new PropertyValueFactory<>("image"));

		col_isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

		col_publish_year.setCellValueFactory(new PropertyValueFactory<>("published_year"));

		col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		col_pdf.setCellValueFactory(new PropertyValueFactory<>("pdf_uri"));
		
		
		
		try {
			con = db.getDB();
			pstmt = con.prepareStatement("SELECT name FROM author");
			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				String name = rs.getString(1);
				alist.add(name);
			}
			combo_author.setItems(alist);
			combo_select_author.setItems(alist);
			con.close();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = db.getDB();
			pstmt = con.prepareStatement("SELECT name FROM category");
			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				String name = rs.getString(1);
				clist.add(name);
			}
			combo_cate.setItems(clist);
			combo_select_Category.setItems(clist);
			con.close();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	try

	{
		con = db.getDB();
		pstmt = con.prepareStatement("SELECT name FROM publisher");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String name = rs.getString(1);
			plist.add(name);
		}
		combo_publish.setItems(plist);
		combo_select_publisher.setItems(plist);
		con.close();

	}catch(ClassNotFoundException|
	SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	blist.clear();
	try
	{
		con = db.getDB();
		pstmt = con
				.prepareStatement("SELECT book_id, title, isbn, Copies, published_year, discription, image, pdf_uri FROM book");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int bid = rs.getInt(1);
			String t = rs.getString(2);
			String isbn = rs.getString(3);
			int c = rs.getInt(4);
			int y = rs.getInt(5);
			String d = rs.getString(6);
			String img = rs.getString(7);
			String pdf = rs.getString(8);

			book b = new book(bid, isbn, t, y, d, c, img, pdf);

			blist.add(b);
		}
		tbl_book.setItems(blist);
		con.close();
	}catch(ClassNotFoundException|
	SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	getbook();
	
}
public ObservableList<book> getbook() 
{
	blist.clear();
	try
	{
		con = db.getDB();
		pstmt = con
				.prepareStatement("SELECT book_id, title, isbn, Copies, published_year, discription, image, pdf_uri FROM book");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int bid = rs.getInt(1);
			String t = rs.getString(2);
			String isbn = rs.getString(3);
			int c = rs.getInt(4);
			int y = rs.getInt(5);
			String d = rs.getString(6);
			String img = rs.getString(7);
			String pdf = rs.getString(8);

			book b = new book(bid, isbn, t, y, d, c, img, pdf);

			blist.add(b);
			
		}
		tbl_book.setItems(blist);
		con.close();
	}catch(ClassNotFoundException|
	SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return blist;
}
public ObservableList<book> getbook_by_author() 
{
	blist.clear();
	try
	{
		con = db.getDB();
		pstmt = con
				.prepareStatement("SELECT book_id, title, isbn, Copies, published_year, discription, image, pdf_uri FROM book");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int bid = rs.getInt(1);
			String t = rs.getString(2);
			String isbn = rs.getString(3);
			int c = rs.getInt(4);
			int y = rs.getInt(5);
			String d = rs.getString(6);
			String img = rs.getString(7);
			String pdf = rs.getString(8);

			book b = new book(bid, isbn, t, y, d, c, img, pdf);

			blist.add(b);
			
		}
		tbl_book.setItems(blist);
		con.close();
	}catch(ClassNotFoundException|
	SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return blist;
}
public int name_to_id_author(String name) throws ClassNotFoundException, SQLException 
{
	con = db.getDB();
	pstmt = con.prepareStatement("SELECT author_id FROM author WHERE name = ?");
	pstmt.setString(1,name);
	rs = pstmt.executeQuery();
	int aid = 0;
	if(rs.next())
		aid = rs.getInt(1);
	

	return aid;
}
public int name_to_id_category(String name) throws ClassNotFoundException, SQLException 
{
	con = db.getDB();
	pstmt = con.prepareStatement("SELECT category_id FROM category WHERE name = ?");
	pstmt.setString(1,name);
	rs = pstmt.executeQuery();
	int cid = 0;
	if(rs.next())
		cid = rs.getInt(1);
	

	return cid;
}
public int name_to_id_publisher(String name) throws ClassNotFoundException, SQLException 
{
	con = db.getDB();
	pstmt = con.prepareStatement("SELECT publisher_id FROM publisher WHERE name = ?");
	pstmt.setString(1,name);
	rs = pstmt.executeQuery();
	int pid = 0;
	if(rs.next())
		pid = rs.getInt(1);
	
	
	return pid;
}

public String id_to_name_author(int id) throws ClassNotFoundException, SQLException 
{
    con = db.getDB();
    pstmt = con.prepareStatement("SELECT name FROM author WHERE author_id = ?");
    pstmt.setInt(1, id);
    rs = pstmt.executeQuery();

    String name = null;

    if (rs.next())
        name = rs.getString(1);

    con.close();
    return name;
}

public String id_to_name_category(int id) throws ClassNotFoundException, SQLException 
{
    con = db.getDB();
    pstmt = con.prepareStatement("SELECT name FROM category WHERE category_id = ?");
    pstmt.setInt(1, id);
    rs = pstmt.executeQuery();

    String name = null;

    if (rs.next())
        name = rs.getString(1);

    con.close();
    return name;
}

public String id_to_name_publisher(int id) throws ClassNotFoundException, SQLException 
{
    con = db.getDB();
    pstmt = con.prepareStatement("SELECT name FROM publisher WHERE publisher_id = ?");
    pstmt.setInt(1, id);
    rs = pstmt.executeQuery();

    String name = null;

    if (rs.next())
        name = rs.getString(1);

    con.close();
    return name;
}
public void select_chose(ActionEvent event)
{
	event.getTarget().addEventFilter(null, null);
}
	
public ObservableList<book> getbook(String tbl, String col, String v) throws ClassNotFoundException, SQLException
{
	con = db.getDB();
	pstmt = con.prepareStatement("SELECT * FROM "+tbl+" WHERE name = ?");
	pstmt.setString(1, v);
	rs = pstmt.executeQuery();
	int s_id = 0;
	String n = "";
	if(rs.next()) 
	{
		s_id = rs.getInt(1);
		n = rs.getString(2);
	}
	pstmt = con.prepareStatement("SELECT * from book where "+col+"= ?");
	pstmt.setInt(1, s_id);
	rs = pstmt.executeQuery();
	while(rs.next()) 
	{
		int bid = rs.getInt(1);
		String isbn = rs.getString(2);
		String title = rs.getString(3);
		int author_id = rs.getInt(4);
		int published_year = rs.getInt(5);
		int category_id = rs.getInt(6);
		String discription = rs.getString(7);
		int copies = rs.getInt(8);
		int publisher_id = rs.getInt(9);
		String image = rs.getString(10);
		String pdf = rs.getString(11);
		
		blist.add(new book(bid, isbn, title, author_id, published_year, category_id, discription, copies, publisher_id, image, pdf));
	}
	tbl_book.setItems(blist);
	con.close();
	return blist;
}
}
