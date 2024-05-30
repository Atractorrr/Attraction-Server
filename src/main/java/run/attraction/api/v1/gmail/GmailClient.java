package run.attraction.api.v1.gmail;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Filter;
import com.google.api.services.gmail.model.FilterAction;
import com.google.api.services.gmail.model.FilterCriteria;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.LabelColor;
import com.google.api.services.gmail.model.ListLabelsResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import run.attraction.AttractionApplication;

@Slf4j
@Component
public class GmailClient {
  private static final String APPLICATION_NAME = "attraction";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
  private static final GoogleClientSecrets clientSecrets;
  static {
    try (
        final InputStream in = AttractionApplication.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        final InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(in))
    ) {
      clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);
    } catch (IOException e) {
      throw new GoogleClientSecretsNotLoadedException(e);
    }
  }

  public void applyLabelAndFilterForNewsletterEmail(String newsletterEmail, String googleRefreshTokenByUser) {
    final NetHttpTransport httpTransport = getHttpTransport();
    final GoogleTokenResponse googleTokenResponse = getGoogleTokenResponse(googleRefreshTokenByUser, httpTransport);

    Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod())
        .setAccessToken(googleTokenResponse.getAccessToken());

    final Gmail service = new Gmail.Builder(httpTransport, JSON_FACTORY, credential)
        .setApplicationName(APPLICATION_NAME)
        .build();

    executeNewsletterFiltering(newsletterEmail, executeNewsletterLabeling(service), service);
  }

  private NetHttpTransport getHttpTransport() {
    try {
      return GoogleNetHttpTransport.newTrustedTransport();
    } catch (GeneralSecurityException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private GoogleTokenResponse getGoogleTokenResponse(
      String userGoogleRefreshToken,
      NetHttpTransport HTTP_TRANSPORT
  ) {
    final Details details = clientSecrets.getDetails();

    try {
      return new GoogleRefreshTokenRequest(
          HTTP_TRANSPORT,
          JSON_FACTORY,
          userGoogleRefreshToken,
          details.getClientId(),
          details.getClientSecret()
      ).execute();
    } catch (IOException e) {
      throw new GmailApiAccessException(e);
    }
  }

  private Label executeNewsletterLabeling(Gmail service) {
    try {
      final ListLabelsResponse me = service.users().labels().list("me").execute();
      return me.getLabels().stream()
          .filter(label -> APPLICATION_NAME.equals(label.getName()))
          .findAny()
          .orElse(createLabel(service));

    } catch (IOException e) {
      throw new GmailApiAccessException(e);
    }
  }

  private Label createLabel(Gmail service) {
    LabelColor labelColor = new LabelColor().setTextColor("#ffffff").setBackgroundColor("#16a765");
    Label createLabel = new Label()
        .setColor(labelColor)
        .setName(APPLICATION_NAME)
        .setType("user")
        .setLabelListVisibility("labelShow")
        .setMessageListVisibility("show");

    try {
      return service.users().labels().create("me", createLabel).execute();
    } catch (IOException e) {
      throw new GmailApiAccessException(e);
    }
  }

  private void executeNewsletterFiltering(String newsletterEmail, Label label, Gmail service) {
    Filter filter = new Filter()
        .setCriteria(new FilterCriteria()
            .setFrom(newsletterEmail))
        .setAction(new FilterAction()
            .setAddLabelIds(Collections.singletonList(label.getId()))
            .setRemoveLabelIds(Collections.singletonList("INBOX")));

    try {
      service.users().settings().filters().create("me", filter).execute();
    } catch (IOException e) {
      throw new GmailApiAccessException(e);
    }
  }
}
