/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mp3player;

import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;

public class MP3Player5 extends JFrame {
    private AdvancedPlayer player;
    private Thread playerThread;

    private JButton playButton;
    private JButton stopButton;
    private JButton selectButton;
    private JLabel currentSongLabel;
    private File selectedFile;
    
private void playSelectedSong() {
    
        stopCurrentSong();

        try {
            FileInputStream fis = new FileInputStream(selectedFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new AdvancedPlayer(bis);

            playerThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        player.play();
                        if (playerThread.isInterrupted()) {
                            playerThread.join();
                        }
                        currentSongLabel.setText("Canción actual: " + selectedFile.getName());
                    } catch (Exception ex) {
                        System.out.println("Error al reproducir la canción: " + ex.getMessage());
                    }
                }
            });

            playerThread.start();
        } catch (Exception ex) {
            System.out.println("Error al cargar la canción: " + ex.getMessage());
        }
    }
    public MP3Player5() {
        setTitle("Reproductor MP3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (player == null || !playerThread.isAlive()) {
                    playSelectedSong();
                } else {
                    try {
                        player.play();
                    } catch (JavaLayerException ex) {
                        Logger.getLogger(MP3Player5.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

           
        });
        add(playButton);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (player != null && playerThread.isAlive()) {
                    player.close();
                }
            }
        });
        add(stopButton);

        selectButton = new JButton("Select Song");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("MP3 Files", "mp3"));
                int result = fileChooser.showOpenDialog(MP3Player5.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    playSelectedSong();
                }
            }
        });
        add(selectButton);

        currentSongLabel = new JLabel();
        add(currentSongLabel);
    }

    

    private void stopCurrentSong() {
        if (player != null && playerThread.isAlive()) {
            player.close();
            playerThread.interrupt();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MP3Player5 mp3Player = new MP3Player5();
                mp3Player.setVisible(true);
            }
        });
    }
}
