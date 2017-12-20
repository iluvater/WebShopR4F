package r4f.controller.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import r4f.controller.services.ImageService;
import r4f.model.Image;

/**
 * Servlet implementation class ImageServlet
 * @author Ture
 */
@WebServlet("/ImageServlet/*")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageId = request.getPathInfo().substring(1);
		ImageService imageService = new ImageService();
		Image image = imageService.getImage(Integer.parseInt(imageId));
		
		byte[] buffer = new byte[BUFFER_SIZE];
		response.setContentType(image.getType());
		
		try {
			InputStream inputStream = image.getImageBlob().getBinaryStream();
			
			int bytesRead = -1;
			
			
			
			while((bytesRead = inputStream.read(buffer)) != -1){
				response.getOutputStream().write(buffer, 0, bytesRead);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
