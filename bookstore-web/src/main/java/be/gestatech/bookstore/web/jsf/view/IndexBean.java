package be.gestatech.bookstore.web.jsf.view;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import java.io.IOException;

@Model
public class IndexBean {

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(getContextRoot() + "/pages/index.jsf");
    }

    private String getContextRoot() throws IOException {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }
}
