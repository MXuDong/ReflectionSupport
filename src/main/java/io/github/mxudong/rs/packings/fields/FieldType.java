package io.github.mxudong.rs.packings.fields;

/**
 * @author Dong
 * @since 3.0
 */
public enum FieldType {

    // common field
    COMMON_FIELD("Common"),
    // static field
    STATIC_FIELD("Static"),
    // final field
    FINAL_FIELD("Final");


    String value;

    FieldType(String value) {
        this.value = value;
    }

    String getValue() {
        return value + " Field";
    }
}
