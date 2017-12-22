package be.gestatech.bookstore.web.view.view;

import org.omnifaces.config.WebXml;

import static org.omnifaces.util.Faces.getResource;
import static org.omnifaces.util.Faces.getViewId;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Named
@Dependent
public class Page {

    private static final Map<String, Page> PAGES = new ConcurrentHashMap<>();

    private Page current;

    private String path;

    private String name;

    private boolean home;

    public Page() {
        // Keep default constructor alive for CDI.
    }

    private Page(String path) {
        this.path = path;
        String uri = "/" + path;
        while (!uri.isEmpty()) {
            try {
                getResource(uri + ".xhtml").toString();
                break;
            } catch (Exception ignore) {
                uri = uri.substring(0, uri.lastIndexOf('/'));
            }
        }

        name = path.replaceFirst("WEB\\-INF/", "").replaceAll("\\W+", "_");
        home = WebXml.INSTANCE.getWelcomeFiles().contains(path);
        current = this;
    }

    @PostConstruct

    public void init() {
        current = get(getViewId().substring(1, getViewId().lastIndexOf('.')));
    }


    public Page get(String path) {
        return PAGES.computeIfAbsent(path, k -> new Page(path));
    }

    public boolean is(String path) {
        return path.equals(current.path);
    }

    public String getPath() {
        return current.path;
    }

    public String getName() {
        return current.name;
    }

    public boolean isHome() {
        return current.home;
    }

}