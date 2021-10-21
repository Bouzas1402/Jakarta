package es.carlosbouzas.holajee;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/SubeFichero")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // (5MB)
public class SubeFichero extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(HelloServlet.class);

    private String message;

    public void init() {
        message = "Hello World!";
    }

    private static final String UPLOAD_DIRECTORY = "ficheros_subidos";

    // Los ficheros se envían usando el método post y van en el cuerpo de la petición
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uploadPath = System.getProperty("jboss.server.deploy.dir") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        Part parteArchivo = request.getPart("archivo");   // Recibe el archivo en un objeto de tipo Part
        String nombreArchivo = uploadPath + File.separator + parteArchivo.getSubmittedFileName(); // Extrae el nombre original del archivo del objeto Part

        long tamanio = parteArchivo.getSize();
        String tipo = parteArchivo.getContentType();

        parteArchivo.write(nombreArchivo);  // Guarda en el disco el archivo con el nombre original

        // Creación del html de salida
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            log.debug("returning the message {}", message);
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SubeFichero</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Operación realizada con éxito</h1>");
            out.println("<table border=1px>");
            out.println("<tr><td>Archivo subido  </td><td><b>" + nombreArchivo + "</b></td></tr>");
            out.println("<tr><td>Tamaño del archivo </td><td>" + tamanio + " bytes</td></tr>");
            out.println("<tr><td>Tipo de archivo </td><td>" + tipo + "</td></tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
