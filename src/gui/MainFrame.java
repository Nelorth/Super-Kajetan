package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Klasse des Hauptfensters
 */
public class MainFrame extends JFrame {

    private final int WIDTH = 1024, HEIGHT = 768;
    private AbstractView currentView;

    /**
     * Die öffentliche statische Leere namens "main"
     * @param args Ein Feld von Fäden namens "aarrrgghs"
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MainFrame();
    }

    public MainFrame() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width / 2 - WIDTH / 2, d.height / 2 - HEIGHT / 2);
        currentView = new MenuView(this);
        add(currentView);
        setVisible(true);
    }
}
