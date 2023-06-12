/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mp4player;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.scene.layout.Pane;

public class MP4Player2 extends JFrame {

    private JFXPanel videoPanel;
    private MediaPlayer mediaPlayer;
    public File videoFile;
    public File selectedFile;

    public MP4Player2() {
        setTitle("Reproductor de Video");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        videoPanel = new JFXPanel();
        add(videoPanel, BorderLayout.CENTER);

        JButton selectButton = new JButton("Seleccionar Video");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos MP4", "mp4"));
                int result = fileChooser.showOpenDialog(MP4Player2.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    playVideo();
                }
            }
        });
        add(selectButton, BorderLayout.SOUTH);
    }

    private void playVideo() {
        stopVideo();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Media media = new Media(selectedFile.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                MediaView mediaView = new MediaView(mediaPlayer);
                
                Pane rootPane = new Pane();
                rootPane.getChildren().add(mediaView);
                videoPanel.setScene(new Scene(rootPane, videoPanel.getWidth(), videoPanel.getHeight()));
                
                mediaPlayer.setOnEndOfMedia(() -> {
                    stopVideo();
                    JOptionPane.showMessageDialog(null, "Video finalizado.");
                });

                mediaPlayer.play();
            }
        });
    }

    private void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // Inicializa la plataforma JavaFX
            Platform.runLater(() -> {
                MP4Player2 videoPlayer = new MP4Player2();
                videoPlayer.setVisible(true);
            });
        });
    }
}
