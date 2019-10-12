package text;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TextControllerTest {

    private static final String TYPE = "type";
    private static final String NAME = "name";

    private MockMvc mockMvc;
    private TextService mockTextService;

    @Before
    public void setUp() {
        mockTextService = mock(TextService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new TextController(mockTextService)).build();
    }

    @Test
    public void testCharFrequency() throws Exception {
        String resultString = "A random assortment of characters";
        when(mockTextService.readText(TYPE, NAME)).thenReturn(resultString);

        mockMvc.perform(get(String.format("/text/%s/%s/frequency", TYPE, NAME)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a", is(5)))
                .andExpect(jsonPath("$.s", is(3)))
                .andExpect(jsonPath("$.h", is(1)));
    }

}