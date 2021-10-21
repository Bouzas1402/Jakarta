package es.carlosbouzas.holajee;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(HelloServlet.class);

    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        log.debug("returning the message {}", message);
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}