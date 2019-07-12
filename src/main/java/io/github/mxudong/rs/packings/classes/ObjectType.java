package io.github.mxudong.rs.packings.classes;


/**
 * @author Dong
 * @since 3.0
 */

public enum ObjectType {

    // class
    CLASS("class"),
    // enum
    ENUM("enum"),
    // interface
    INTERFACE("interface"),
    // annotation
    ANNOTATION("annotation");

    String type;

    ObjectType(String type) {
        this.type = type;
    }

    public String getType() {
        return type + " type";
    }
}
