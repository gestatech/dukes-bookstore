package be.gestatech.bookstore.service.dto;

import java.io.Serializable;

public class ProgrammingLanguage implements Serializable {

    private static final long serialVersionUID = 5438152462712901815L;

    private String name;
    private String inventor;
    private int yearOfBirth;

    public ProgrammingLanguage(String aName, String anInventor, int aYear) {
        this.name = aName;
        this.inventor = anInventor;
        this.yearOfBirth = aYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInventor() {
        return inventor;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

}
