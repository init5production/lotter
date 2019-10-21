package init5.production.lotter.juggler.archive.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author Jakub Barski
 */
public class ArchiveConnector {

    private static final String ARCHIVE_FILE_URI = "http://www.mbnet.com.pl/dl.txt";
    private static final long TIMEOUT_IN_SECONDS = 3;

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveConnector.class);

    private HttpClient client;

    @PostConstruct
    public void init() {
        client = HttpClient.newBuilder().build();
    }

    public String getRawData() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(ARCHIVE_FILE_URI))
                    .timeout(Duration.of(TIMEOUT_IN_SECONDS, ChronoUnit.SECONDS))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            if(statusCode != 200) {
                LOGGER.error("Could not obtain archive file! Invalid response status code [{}].", statusCode);
                return "";
            }

            return response.body();
        } catch (Exception e) {
            LOGGER.error(
                    "Could not obtain archive file! Caught exception [{}] with message [{}].",
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
            return "";
        }
    }


}
