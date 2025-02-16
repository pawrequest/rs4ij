package pawrequest.rs4ij.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.Nullable;
import pawrequest.rs4ij.server.RedscriptLanguageServer;

import javax.swing.*;

public class RedscriptConfigurable implements Configurable {

    private RedscriptSettingsComponent mySettingsComponent;

    @Override
    public String getDisplayName() {
        return "Redscript";
    }

    @Override
    public JComponent createComponent() {
        mySettingsComponent = new RedscriptSettingsComponent();
        return mySettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        RedscriptSettings settings = RedscriptSettings.getInstance();
        return !mySettingsComponent.getGameDir().equals(settings.getGameDir());
    }


    @Override
    public void apply() {
        RedscriptSettings settings = RedscriptSettings.getInstance();
        String oldGameDir = settings.getGameDir();
        String newGameDir = mySettingsComponent.getGameDir();

        // Save the new game directory
        settings.setGameDir(newGameDir);

        // Restart the LSP server if the game directory has changed
        if (!newGameDir.equals(oldGameDir)) {
            restartLspServer();
        }
    }



//    @Override
//    public void apply() {
//        RedscriptSettings settings = RedscriptSettings.getInstance();
//        settings.setGameDir(mySettingsComponent.getGameDir());
//    }

    private void restartLspServer() {
        // Iterate over all open projects and restart the LSP server for each one
        for (Project project : ProjectManager.getInstance().getOpenProjects()) {
            RedscriptLanguageServer.restartServer(project);
        }
    }

    @Override
    public void reset() {
        RedscriptSettings settings = RedscriptSettings.getInstance();
        mySettingsComponent.setGameDir(settings.getGameDir());
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }
}