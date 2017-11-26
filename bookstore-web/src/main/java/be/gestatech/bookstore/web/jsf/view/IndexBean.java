package be.gestatech.bookstore.web.jsf.view;

import java.io.IOException;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexBean {

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("pages/index.jsf");
    }
}
