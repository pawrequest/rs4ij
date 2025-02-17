package pawrequest.github;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class GitHubRelease {
    public String tag_name;
    public List<GitHubAsset> assets;
    public URL url;
    public URL assets_url;
    public URL html_url;
    public URL upload_url;
    public Integer id;
    public String node_id;
    public Date created_at;
    public Date published_at;

    public GitHubRelease(
            String tagName,
            List<GitHubAsset> assets,
            URL url, URL assets_url, URL html_url, URL upload_url,
            Integer id,
            String node_id,
            Date created_at, Date published_at
    ) {
        this.tag_name = tagName;
        this.assets = assets;
        this.url = url;
        this.assets_url = assets_url;
        this.html_url = html_url;
        this.id = id;
        this.node_id = node_id;
        this.upload_url = upload_url;
        this.created_at = created_at;
        this.published_at = published_at;

    }


    public static GitHubRelease fromURL(URL release_url) throws IOException {
        System.out.println("Fetching Release from: " + release_url);
        HttpURLConnection connection = (HttpURLConnection) release_url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200) {
            String retry = connection.getHeaderField("retry-after");
            if (retry != null) {
                System.out.println("Rate Limited. Retry after: " + retry);
            }
            String x = "Failed to fetch latest release from " + release_url + " msg =" + connection.getResponseMessage();
            System.out.println(x);
            throw new IOException(x);

        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return new Gson().fromJson(response.toString(), GitHubRelease.class);
        }
    }

}
