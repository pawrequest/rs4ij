package pawrequest.github;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class GitHubReleaseFetcher {
    public final URI release_uri;

    protected GitHubReleaseFetcher(URI releaseUri) {
        this.release_uri = releaseUri;
    }

    public File fetch_binary(URL download_url) throws IOException, URISyntaxException {
        System.out.println("Downloading Binary from: " + download_url);
        File tempFile = getTemporaryFile(download_url);
        HttpURLConnection connection = getHttpURLConnection(download_url);
        copy_filestream(connection, tempFile);
        set_exe(tempFile);
        return tempFile;

    }

    private static void set_exe(File tempFile) throws IOException {
        if (!tempFile.setExecutable(true)) {
            throw new IOException("Failed to set executable permissions on the downloaded binary.");
        }
    }

    private static void copy_filestream(HttpURLConnection connection, File tempFile) throws IOException {
        try (InputStream inputStream = connection.getInputStream()) {
            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static @NotNull File getTemporaryFile(URL download_url) throws IOException {
        String url_tail = download_url.getPath().substring(download_url.getPath().lastIndexOf('/') + 1);
        File tempFile = File.createTempFile(url_tail, "");
        tempFile.deleteOnExit();
        return tempFile;
    }

    private static @NotNull HttpURLConnection getHttpURLConnection(URL connection_url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) connection_url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != 200) {
            throw new IOException("Failed to connect... " + connection.getResponseMessage());
        }
        return connection;
    }


}
