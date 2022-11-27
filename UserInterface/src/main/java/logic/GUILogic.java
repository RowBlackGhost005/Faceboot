package logic;

import com.masa.businesslogic.BusinessLogic;
import com.masa.businesslogic.IBusinessLogic;

/**
 * Class that wraps a single object of BusinessLogic to give acces to only one
 * for every controller to use.
 *
 * @author Luis Marin
 */
public class GUILogic {

    //Business Logic to use as single tone
    private static IBusinessLogic businessLogic;

    private GUILogic() {
    }

    /**
     * Returns a IBusinessLogic object.
     * @return IBusinessLogic.
     */
    public static IBusinessLogic getLogic() {

        if (businessLogic == null) {
            businessLogic = BusinessLogic.createBusinessLogic();
        }

        return businessLogic;
    }
}
