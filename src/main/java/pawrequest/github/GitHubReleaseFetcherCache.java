package pawrequest.github;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class GitHubReleaseFetcherCache extends GitHubReleaseFetcher {
    public final Path cache_dir;

    public GitHubReleaseFetcherCache(Path cache_dir, URI releaseURI) {
        super(releaseURI);
        this.cache_dir = cache_dir;

    }

    public File cachedBinary(String assetName) {
        return Paths.get(String.valueOf(cache_dir), assetName).toFile();
    }

    @Override
    public File fetch_binary(URL binary_url) throws IOException, URISyntaxException {

        String asset_name = List.of(binary_url.getPath().split("/")).getLast();
        System.out.println("Asset Name: " + asset_name);

        File cachedBinary = cachedBinary(asset_name);
        if (cachedBinary.exists()) {
            System.out.println("Cached Binary Exists: " + cachedBinary);
            return cachedBinary;
        }
        System.out.println("Cached Binary Does Not Exist: " + cachedBinary);
        File Binary = super.fetch_binary(binary_url);
        System.out.println("Caching Binary to: " + cachedBinary);
        Files.createDirectories(cache_dir);
        Files.copy(Binary.toPath(), cachedBinary.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return cachedBinary;
    }

}