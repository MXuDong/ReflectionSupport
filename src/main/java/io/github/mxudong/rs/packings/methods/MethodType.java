package io.github.mxudong.rs.packings.methods;

/**
 * @author Dong
 * @since 3.0
 */
public enum MethodType {

    /**
     * ConstructionMethod : the construction method
     * CommonMethod : the common method
     * StaticMethod : the static method
     * GetterMethod : the getter method
     * SetterMethod : the setter method
     */
    ConstructionMethod("Construction"),
    CommonMethod("Common"),
    StaticMethod("Static"),
    GetterMethod("Getter"),
    SetterMethod("Setter");

    String type;

    MethodType(String type) {
        this.type = type;
    }

    public String getType() {
        return type + " Method";
    }
}
