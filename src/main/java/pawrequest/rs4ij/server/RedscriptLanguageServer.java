package pawrequest.rs4ij.server;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.project.Project;
import com.redhat.devtools.lsp4ij.server.OSProcessStreamConnectionProvider;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


//public class RedscriptLanguageServer extends OSProcessStreamConnectionProvider {
//    public RedscriptLanguageServer(@NotNull Project project) {
//        String executablePath = Objects.requireNonNull(getClass().getResource("/server/redscript-ide.exe")).getPath();
//        GeneralCommandLine commandLine = new GeneralCommandLine(executablePath);
////        GeneralCommandLine commandLine = new GeneralCommandLine("resources/server/redscript-ide.exe");
//        super.setCommandLine(commandLine);
//    }
//}
//


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
            String gameDirJson = "{\"game_dir\": \"D:/GAMES/Cyberpunk 2077\"}";
            commandLine.addParameters("--init", gameDirJson);
            super.setCommandLine(commandLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}