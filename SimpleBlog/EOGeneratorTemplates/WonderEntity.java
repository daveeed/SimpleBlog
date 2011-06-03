import org.apache.log4j.Logger;

import com.webobjects.eocontrol.EOEditingContext;

public class ${entity.classNameWithoutPackage} extends ${entity.prefixClassNameWithOptionalPackage} {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(${entity.classNameWithoutPackage}.class);

    public static final ${entity.classNameWithoutPackage}Clazz clazz = new ${entity.classNameWithoutPackage}Clazz();
    public static class ${entity.classNameWithoutPackage}Clazz extends ${entity.prefixClassNameWithOptionalPackage}.${entity.prefixClassNameWithoutPackage}Clazz {
        /* more clazz methods here */
    }

    /**
     * Initializes the EO. This is called when an EO is created, not when it is 
     * inserted into an EC.
     */
    public void init(EOEditingContext ec) {
        super.init(ec);
    }

}
