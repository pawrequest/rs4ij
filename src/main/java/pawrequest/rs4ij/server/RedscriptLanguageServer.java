package pawrequest.rs4ij.server;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.redhat.devtools.lsp4ij.LanguageServerWrapper;
import com.redhat.devtools.lsp4ij.LanguageServersRegistry;
import com.redhat.devtools.lsp4ij.server.OSProcessStreamConnectionProvider;
import com.redhat.devtools.lsp4ij.server.definition.LanguageServerDefinition;
import org.eclipse.lsp4j.InitializeParams;
import org.jetbrains.annotations.NotNull;
import pawrequest.rs4ij.settings.RedscriptSettings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RedscriptLanguageServer extends OSProcessStreamConnectionProvider {
    public RedscriptLanguageServer(@NotNull Project project) {
        try {
            // Load and store redscript-ide.exe
            InputStream inputStream = getClass().getResourceAsStream("/language/redscript-ide.exe");
            if (inputStream == null) {
                throw new IOException("Executable not found in resources.");
            }
            File tempFile = File.createTempFile("redscript-ide", ".exe");
            tempFile.deleteOnExit();

            // Write the InputStream to the temporary file
            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // Start LSP
            GeneralCommandLine commandLine = new GeneralCommandLine(tempFile.getAbsolutePath());
            super.setCommandLine(commandLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object getInitializationOptions(VirtualFile rootUri) {
        RedscriptSettings settings = RedscriptSettings.getInstance();
        String gameDir = settings.getGameDir();

        Map<String, Object> options = new HashMap<>();
        options.put("ui.semanticTokens", true); // Existing option
//        options.put("game_dir", "D:/GAMES/Cyberpunk 2077"); // Add game_dir

        options.put("game_dir", gameDir); // Use user-configured game_dir
        return options;
    }




    public static void restartServer(@NotNull Project project) {
        // Restart the server directly
        LanguageServersRegistry.getInstance()
                .getServerDefinition("redscript") // Use the ID of your server definition
                .getLanguageServerWrappers(project) // Get the wrappers for the project
                .forEach(LanguageServerWrapper::restart); // Restart each wrapper
    }

//    public static void restartServer(@NotNull Project project) {
//        // Get the server definition for RedscriptLanguageServer
//        LanguageServerDefinition serverDefinition = LanguageServersRegistry.getInstance()
//                .getServerDefinition("redscript"); // Use the ID of your server definition
//
//        if (serverDefinition != null) {
//            // Find the LanguageServerWrapper for this server definition and project
//            List<LanguageServerWrapper> wrappers = LanguageServersRegistry.getInstance()
//                    .findLanguageServerWrappers(project, serverDefinition);
//
//            // Restart each wrapper (typically there should be only one)
//            for (LanguageServerWrapper wrapper : wrappers) {
//                wrapper.restart();
//            }
//        }
//    }

}