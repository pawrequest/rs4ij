package pawrequest.rs4ij.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ex.ProjectManagerEx;

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
        settings.setGameDir(newGameDir);

        if (!newGameDir.equals(oldGameDir)) {
            reloadProject();
        }
    }

    private void reloadProject() {
        for (Project project : ProjectManager.getInstance().getOpenProjects()) {
            ProjectManagerEx.getInstance().reloadProject(project);
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