package pawrequest.rs4ij.server;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.redhat.devtools.lsp4ij.server.OSProcessStreamConnectionProvider;
import org.jetbrains.annotations.NotNull;
import pawrequest.github.GitHubReleaseFetcherCache;
import pawrequest.rs4ij.settings.RedscriptSettings;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class RedscriptLanguageServer extends OSProcessStreamConnectionProvider {
    public RedscriptLanguageServer(@NotNull Project project) {
        try {
            Path cacheDir = Paths.get(System.getProperty("user.home"), ".redscript-ide");
            System.out.println("Cache Dir: " + cacheDir);
            //
            String platform_binary_name = RedscriptIDEGitHubRelease.platformBinaryName();
            System.out.println("Platform Binary Name: " + platform_binary_name);

//             GET LATEST RELEASE
//            RedscriptIDEGitHubRelease latestRelease = RedscriptIDEGitHubRelease.latestRelease();
//            System.out.println("Release Tag name: " + latestRelease.tag_name);
//            URI binary_uri = latestRelease.platform_binary_latest();
//            System.out.println("Latest Binary URI: " + binary_uri);
//
//            GET CURRENT RELEASE
            URI binary_uri = RedscriptIDEGitHubRelease.platform_binary_current();
            System.out.println("Binary URI: " + binary_uri);


//            check cache for binary else download
            GitHubReleaseFetcherCache fetcher = new GitHubReleaseFetcherCache(cacheDir, binary_uri);
            File binaryFile = fetcher.fetch_binary(binary_uri.toURL());

            GeneralCommandLine commandLine = new GeneralCommandLine(binaryFile.getAbsolutePath());
            super.setCommandLine(commandLine);

        } catch (IOException | URISyntaxException e) {

            throw new RuntimeException(e);
        }
    }


    @Override
    public Object getInitializationOptions(VirtualFile rootUri) {
        RedscriptSettings settings = RedscriptSettings.getInstance();

        Map<String, Object> options = new HashMap<>();
        options.put("ui.semanticTokens", true);
        options.put("game_dir", settings.getGameDir());
        return options;
    }

}


