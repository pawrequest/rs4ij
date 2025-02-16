package pawrequest.rs4ij.settings;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedscriptSettingsComponent {

    private JPanel myPanel;
    private JTextField gameDirField;
    private JButton browseButton;

    public RedscriptSettingsComponent() {
        myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

        // Label and text field for game directory
        JLabel label = new JLabel("Game Directory:");
        gameDirField = new JTextField(50);

        // Browse button
        browseButton = new JButton("Browse...");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a folder selection dialog
                FileChooserDescriptor descriptor = new FileChooserDescriptor(false, true, false, false, false, false);
                VirtualFile selectedDir = FileChooser.chooseFile(descriptor, null, null);
                if (selectedDir != null) {
                    // Update the text field with the selected directory path
                    gameDirField.setText(selectedDir.getPath());
                }
            }
        });

        // Add components to the panel
        myPanel.add(label);
        myPanel.add(gameDirField);
        myPanel.add(browseButton);
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

//package pawrequest.rs4ij.settings;
//
//import javax.swing.*;
//
//public class RedscriptSettingsComponent {
//
//    private JPanel myPanel;
//    private JTextField gameDirField;
//
//    public RedscriptSettingsComponent() {
//        myPanel = new JPanel();
//        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
//
//        JLabel label = new JLabel("Game Directory:");
//        gameDirField = new JTextField(50);
//
//        myPanel.add(label);
//        myPanel.add(gameDirField);
//    }
//
//    public JPanel getPanel() {
//        return myPanel;
//    }
//
//    public String getGameDir() {
//        return gameDirField.getText();
//    }
//
//    public void setGameDir(String gameDir) {
//        gameDirField.setText(gameDir);
//    }
//}