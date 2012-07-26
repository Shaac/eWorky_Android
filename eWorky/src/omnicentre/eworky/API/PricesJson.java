package omnicentre.eworky.API;

import java.util.List;

public class PricesJson {
    private int offerType;
    private String price;
    
    public PricesJson() {   
    }
    
    public static String getOfficePrice(List<PricesJson> list) {
        if (list == null)
            return "";
        for (PricesJson price : list)
            if (price.offerType == 2 || price.offerType == 3)
                return price.price;
        return "";
    }
    
    public static String getMeetingPrice(List<PricesJson> list) {
        if (list == null)
            return "";
        for (PricesJson price : list)
            if (price.offerType == 4 || price.offerType == 5)
                return price.price;
        return "";
    }


}
