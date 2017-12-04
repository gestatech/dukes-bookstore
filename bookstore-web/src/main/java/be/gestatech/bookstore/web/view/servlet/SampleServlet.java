package be.gestatech.bookstore.web.view.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(value="/sample", name="hello-sample")
public class SampleServlet extends GenericServlet {

    private static final long serialVersionUID = 3320697984122270520L;

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Hello Sample!");
    }
}
