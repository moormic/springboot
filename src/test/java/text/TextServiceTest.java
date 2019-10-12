package text;

import config.TextConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TextServiceTest {

    private static final String TYPE = "type";
    private static final String NAME = "name";
    private static final String URL = "url";

    private TextService textService;
    @Mock
    private RestTemplate mockRestTemplate;

    @Before
    public void setUp() {
        TextConfiguration mockConfiguration = new TextConfiguration();
        mockConfiguration.setUrl(URL);

        textService = new TextService(mockConfiguration);
        textService.setRestTemplate(mockRestTemplate);
    }

    @Test
    public void testReadText() {
        String expectedText = "A random string of characters";
        when(mockRestTemplate.getForObject(String.format("%s/%s/%s", URL, TYPE, NAME), String.class)).thenReturn(expectedText);
        assertThat(textService.readText(TYPE, NAME), is(expectedText));
    }

}
