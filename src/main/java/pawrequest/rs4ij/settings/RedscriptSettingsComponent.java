package pawrequest.rs4ij.settings;

import javax.swing.*;

public class RedscriptSettingsComponent {

    private JPanel myPanel;
    private JTextField gameDirField;

    public RedscriptSettingsComponent() {
        myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Game Directory:");
        gameDirField = new JTextField(50);

        myPanel.add(label);
        myPanel.add(gameDirField);
    }

    public JPanel getPanel() {
        return myPanel;
    }

    public String getGameDir() {
        return gameDirField.getText();
    }

    public void setGameDir(String gameDir) {
        gameDirField.setText(gameDir);
    }
}