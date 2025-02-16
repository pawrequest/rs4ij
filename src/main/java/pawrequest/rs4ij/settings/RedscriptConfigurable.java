package pawrequest.rs4ij.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

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
        settings.setGameDir(mySettingsComponent.getGameDir());
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