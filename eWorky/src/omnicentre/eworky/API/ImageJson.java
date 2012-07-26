package omnicentre.eworky.API;

import java.util.List;

public class ImageJson {
    
    private String url;
    private String thumbnail_url;
    
    public ImageJson() {
    }
    
    public static String getURL(List<ImageJson> list) {
        if (list == null || list.size() == 0)
            return "http://www.eworky.fr/Content/images/no_image.png";
        else
            return list.get(0).url;
    }
    
    public static String getThumbURL(List<ImageJson> list) {
        if (list == null || list.size() == 0)
            return "http://www.eworky.fr/Content/images/no_image.png";
        else
            return list.get(0).thumbnail_url;
    }
}
