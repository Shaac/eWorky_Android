package omnicentre.eworky.API;

import java.util.List;

/**
 * This class will contain feature data. It should be the Java equivalent
 * of FeatureJson on JsonModels.cs
 *
 */
public class FeatureJson {
    
    public static final int WIFI = 55;
    public static final int FREEWIFI = 8;
    public static final int COFFEE = 12;
    public static final int RESTO = 11;
    public static final int PARKING = 9;
    public static final int HANDICAP = 10;

    private int featureId;
    private String featureDisplay;

    public FeatureJson() {
    }

    public static String toText(List<FeatureJson> list) {
        StringBuilder builder = new StringBuilder();
        for(FeatureJson feature : list)
            builder.append(" - ").append(feature.featureDisplay).append("\n");
        return builder.toString();
    }
    
    public static boolean hasWifi(List<FeatureJson> list) {
        return has(list, WIFI);
    }
    
    public static boolean hasFreeWifi(List<FeatureJson> list) {
        return has(list, FREEWIFI);
    }
    
    public static boolean hasCoffee(List<FeatureJson> list) {
        return has(list, COFFEE);
    }
    
    public static boolean hasResto(List<FeatureJson> list) {
        return has(list, RESTO);
    }
    
    public static boolean hasParking(List<FeatureJson> list) {
        return has(list, PARKING);
    }
    
    public static boolean hasHandicap(List<FeatureJson> list) {
        return has(list, HANDICAP);
    }
    
    public static boolean has(List<FeatureJson> list, int feature) {
        if (list == null)
            return false;
        for (FeatureJson f : list)
            if (f.featureId == feature)
                return true;
        return false;
    }
}
