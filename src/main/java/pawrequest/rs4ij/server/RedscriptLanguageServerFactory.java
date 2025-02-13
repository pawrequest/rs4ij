package pawrequest.rs4ij.server;

import com.redhat.devtools.lsp4ij.LanguageServerFactory;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class RedscriptLanguageServerFactory implements LanguageServerFactory {
    @Override
    public @NotNull RedscriptLanguageServer createConnectionProvider(@NotNull Project project) {
        return new RedscriptLanguageServer(project);
    }

}
