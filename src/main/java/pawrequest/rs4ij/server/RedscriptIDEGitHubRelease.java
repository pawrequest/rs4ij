package pawrequest.rs4ij.server;

import pawrequest.github.GitHubAsset;
import pawrequest.github.GitHubRelease;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class RedscriptIDEGitHubRelease extends GitHubRelease {
    public static URI REPO_URI = URI.create("https://github.com/jac3km4/redscript-ide/");
    public static URI RELEASES = REPO_URI.resolve("releases/");
    public static URI API_URI = URI.create("https://api.github.com/repos/jac3km4/redscript-ide/");
    public static URI RELEASES_API = API_URI.resolve("releases/");
    public static URI LATEST_RELEASE_API = RELEASES_API.resolve("latest");
    public static String CURRENT_VERSION = "v0.1.46";

    public RedscriptIDEGitHubRelease(String tagName, List<GitHubAsset> assets, URL url, URL assets_url, URL html_url, URL upload_url, Integer id, String node_id, Date created_at, Date published_at) {
        super(tagName, assets, url, assets_url, html_url, upload_url, id, node_id, created_at, published_at);
        System.out.println("repo uri: " + REPO_URI);
        System.out.println("releases: " + RELEASES);
        System.out.println("releases api: " + RELEASES_API);
        System.out.println("Latest Release API URI: " + LATEST_RELEASE_API);
        System.out.println("Current Version: " + CURRENT_VERSION);
    }


    public URI platform_binary_latest() {
        String binaryPathStr = "download/" + this.tag_name + "/" + platformBinaryName();
        URI res = RELEASES.resolve(binaryPathStr);
        System.out.println("Latest Platform Binary URI: " + res);
        return res;
    }

    public static URI platform_binary_current() {
        String binaryPathStr = "download/" + CURRENT_VERSION + "/" + platformBinaryName();
        URI res = RELEASES.resolve(binaryPathStr);
        System.out.println("Current Platform Binary URI: " + res);
        return res;
    }

    public static RedscriptIDEGitHubRelease latestRelease()
            throws URISyntaxException, IOException {
        return (RedscriptIDEGitHubRelease) RedscriptIDEGitHubRelease.fromURL(LATEST_RELEASE_API.toURL());
    }


    protected static String platformBinaryName() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")) {
            return "redscript-ide.exe";
        } else if (osName.contains("mac")) {
            return "redscript-ide-x86_64-apple-darwin";
        } else if (osName.contains("linux")) {
            return "redscript-ide-x86_64-unknown-linux-gnu";
        } else {
            throw new UnsupportedOperationException("Unsupported platform: " + osName);
        }
    }

}
