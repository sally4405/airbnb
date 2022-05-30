package louie.dong.airbnb.banner;

import java.util.Collections;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BannerController {

    private static final String MAIN_IMAGE_URL = "https://user-images.githubusercontent.com/92966772/169965428-e12f898a-1f72-4510-8ddb-8086519366c0.PNG";

    @GetMapping("/main")
    public Map<String, String> getMainImage() {
        return Collections.singletonMap("imageUrl", MAIN_IMAGE_URL);
    }
}
