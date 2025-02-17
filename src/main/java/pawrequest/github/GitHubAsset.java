package pawrequest.github;

import java.net.URL;

public class GitHubAsset {

    public String url;
    public long id;
    public String node_id;
    public String name;
    public String label;
    public String content_type;
    public String state;
    public long size;
    public int download_count;
    public String created_at;
    public String updated_at;
    public URL browser_download_url;

    public GitHubAsset(String url, long id, String node_id, String name, String label, String content_type, String state, long size, int download_count, String created_at, String updated_at, URL browser_download_url) {
        this.url = url;
        this.id = id;
        this.node_id = node_id;
        this.name = name;
        this.label = label;
        this.content_type = content_type;
        this.state = state;
        this.size = size;
        this.download_count = download_count;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.browser_download_url = browser_download_url;
    }

}
