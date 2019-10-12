package text;

import config.TextConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TextService {

    private final TextConfiguration textConfiguration;
    @Setter
    private RestTemplate restTemplate = new RestTemplate();

    String readText(String textType, String textName) {
        final String url = textConfiguration.getUrl() + "/" + textType + "/" + textName;
        return restTemplate.getForObject(url, String.class);
    }

}
