package text;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TextController {

    private final TextService textService;

    @GetMapping("/text/{type}/{textName}/frequency")
    public Map<Character, Long> charFrequency(
            @PathVariable("type") String type,
            @PathVariable("textName") String textName
    ) {
        try {
            String text = textService.readText(type, textName);
            return text.chars()
                    .mapToObj(t -> (char) t)
                    .map(Character::toLowerCase)
                    .collect(Collectors.groupingBy(Function.identity(), counting()));
        } catch (Exception e) {
            throw new RuntimeException("Text not found. Ensure the type and name are correct.");
        }
    }

}
