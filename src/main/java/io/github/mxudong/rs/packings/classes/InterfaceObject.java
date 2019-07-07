package io.github.mxudong.rs.packings.classes;

/**
 * class InterfaceObject packing the interface info, this class
 * provides some method to manage interface info
 *
 * @author Dong
 * @since 3.0
 */

public class InterfaceObject<T> {

    /**
     * the class of packing interface
     */
    private Class<T> interfaceClass;

    /**
     * the name of packing interface
     */
    private String interfaceName;

    /**
     * construction method
     *
     * @param interfaceObject packing interface
     */
    public InterfaceObject(Class<T> interfaceObject) {
        this.interfaceClass = interfaceObject;
        this.interfaceName = interfaceObject.getName();
    }

    /**
     * get the interface name
     *
     * @return interface name
     */
    public String getInterfaceName() {
        return interfaceName;
    }
}
