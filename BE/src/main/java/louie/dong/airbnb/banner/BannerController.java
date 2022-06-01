package louie.dong.airbnb.banner;

import java.util.Collections;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BannerController {

    private static final String MAIN_IMAGE_URL = "https://user-images.githubusercontent.com/73376468/171333357-01d5146b-bdf2-45a0-a49d-234ee9f133b7.jpeg";

    @GetMapping("/banners/main")
    public Map<String, String> getMainImage() {
        return Collections.singletonMap("imageUrl", MAIN_IMAGE_URL);
    }
}
