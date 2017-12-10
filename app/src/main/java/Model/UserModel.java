package Model;

/**
 * Created by IamDeveloper on 10/10/2016.
 */
public class UserModel {
    String id;
    String name;
    String pass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Override
    public String toString() {
        return getName() + " " + getPass() + "\n";
    }
}
