package be.gestatech.bookstore.web.view.jsf;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import java.io.IOException;

@Model
public class IndexBean {

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(getContextRoot() + "/views/login.jsf");
    }

    private String getContextRoot() throws IOException {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }
}
