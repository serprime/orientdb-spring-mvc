package example.domain;

public class Entity {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * ensure to use getters in the toString or the attributes will
     * not be resolved by orientdb and you get nulls after reading from db.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Entity{" +
                "value='" + getValue() + '\'' +
                '}';
    }
}
