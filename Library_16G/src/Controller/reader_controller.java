package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.dlsc.pdfviewfx.PDFView;

import Model.book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class reader_controller implements Initializable 
{
	database_connection db = new database_connection();
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;
	int book_id;
	
	public reader_controller(int book_id) 
	{
		this.book_id = book_id;
	}

    public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
		System.out.println("log: book id set");
	}

	@FXML
    private Button btn_back;

    @FXML
    private AnchorPane result_pane;

    @FXML
    void click_back(ActionEvent event) throws IOException 
    {
    	Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		try {
			
			
			book b = id_to_get_obj(book_id);

			String pdf_path = b.getPdf_uri();

			File file = new File(pdf_path);

			PDFView view = new PDFView();
			
			view.load(file);
			
			view.prefWidthProperty().bind(result_pane.widthProperty());
			view.prefHeightProperty().bind(result_pane.heightProperty());
			
			result_pane.getChildren().clear();
			result_pane.getChildren().add(view);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	public book id_to_get_obj(int book_id) throws ClassNotFoundException, SQLException 
	{
		book_id = this.book_id;
		
	    con = db.getDB();
	    pstmt = con.prepareStatement("SELECT * FROM book WHERE book_id = ?");
	    pstmt.setInt(1, book_id);
	    rs = pstmt.executeQuery();

	    if(rs.next()) 
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
    		
    		return new book(bid, isbn, title, author_id, published_year, category_id, discription, copies, publisher_id, image, pdf);
    	}

	    return new book();
	}

}
