package pawrequest.rs4ij.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "RedscriptSettings",
        storages = {@Storage("redscript.xml")}
)
public class RedscriptSettings implements PersistentStateComponent<RedscriptSettings.State> {

    private State myState = new State();

    public static class State {
        public String gameDir = "D:/GAMES/Cyberpunk 2077";
    }

    @Nullable
    @Override
    public State getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull State state) {
        myState = state;
    }

    public String getGameDir() {
        return myState.gameDir;
    }

    public void setGameDir(String gameDir) {
        myState.gameDir = gameDir;
    }

    public static RedscriptSettings getInstance() {
        return ServiceManager.getService(RedscriptSettings.class);
    }
}