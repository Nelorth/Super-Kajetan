package gui;

import logic.Behavior;
import model.*;
import util.Constants;
import util.List;
import util.Logger;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

class LobbyView extends AbstractView {
    private static LobbyView instance;

    private LobbyView() {
        super();
        setLayout(new BorderLayout());
        setBackground(Constants.MENU_BACKGROUND_COLOR);

        //ButtonPanel, im Prinzip nur der Zurück-Knopf
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setOpaque(false);

        //Zurück-Button
        WoodenButton backButton = new WoodenButton("Zurück");
        backButton.setFont(Constants.DEFAULT_FONT);
        backButton.setPreferredSize(Constants.DEFAULT_BUTTON_SIZE_2);
        backButton.addActionListener(a -> MainFrame.getInstance().changeTo(MainMenuView.getInstance()));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.PAGE_END);


        //Level-Buttons-Panel, hardcoded weil LvL auch hardcoded sind
        JPanel levelButtonPanel = new JPanel();
        levelButtonPanel.setLayout(new GridBagLayout());
        levelButtonPanel.setOpaque(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 0, 5, 0);

        JScrollPane scrollPane = new JScrollPane(levelButtonPanel) {
            @Override
            public void setBorder(Border border) {
                // Nein, Böse! Wieder mal!
            }
        };
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);


        Font buttonFont = Constants.DEFAULT_FONT.deriveFont(24F);
        //Buttons für einzelne Lvl
        //Level 1
        WoodenButton lvl1 = new WoodenButton("Level 1");
        lvl1.setPreferredSize(Constants.DEFAULT_BUTTON_SIZE);
        lvl1.setFont(buttonFont);
        lvl1.addActionListener(a -> {
            LevelView levelView = new LevelView(createLevel1());
            MainFrame.getInstance().changeTo(levelView);
            levelView.setFocusable(true);
            levelView.requestFocusInWindow();
            new Thread(levelView).start();
        });
        levelButtonPanel.add(lvl1, constraints);

        //Level 2
        WoodenButton lvl2 = new WoodenButton("Level 2");
        lvl2.setPreferredSize(Constants.DEFAULT_BUTTON_SIZE);
        lvl2.setFont(buttonFont);
        lvl2.addActionListener(a -> {
            LevelView levelView = new LevelView(createLevel2());
            MainFrame.getInstance().changeTo(levelView);
            new Thread(levelView).start();
        });
        levelButtonPanel.add(lvl2, constraints);
    }

    public void refresh() {
        System.out.println("active threads: " + Thread.activeCount());
    }

    public static LobbyView getInstance() {
        if (instance == null)
            instance = new LobbyView();
        return instance;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Praktisch der Teil der für das Hintergrundbild sorgt. Man muss natürlich auch die ganzen Panels auf nicht opaque setzen
        //-> setOpaque(false)
        try {
            g.drawImage(util.ImageUtil.getImage(Constants.MENU_BACKGROUND_2), 0, 0, getWidth(), getHeight(), null);
            g.setColor(new Color(0, 0, 0, 0.7f));
            g.fillRect(0, 0, getWidth(), getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Hässliche Hardcoded-Level-Methode
     */
    private Level createLevel1() {
        List<Enemy> enemies = new List<>();
        enemies.add(new Knight(2200, 680, Behavior.PATROL, Direction.LEFT));
        List<Obstacle> obstacles = new List<>();
        obstacles.add(new Crate(1750, 640));
        obstacles.add(new Crate(1000, 300));
        List<Ground> grounds = new List<>();
        grounds.add(new Ground(600, 1200, 20, Ground.Type.SOIL));
        grounds.add(new Ground(1400, 400, 60, Ground.Type.SOIL));
        grounds.add(new Ground(1750, 300, 100, Ground.Type.GRASS));
        grounds.add(new Ground(2000, 200, 80, Ground.Type.ROCK));
        grounds.add(new Ground(2200, 200, 60, Ground.Type.GRASS));
        grounds.add(new Ground(2400, 200, 40, Ground.Type.SOIL));
        grounds.add(new Ground(2600, 200, 20, Ground.Type.ROCK));
        grounds.add(new Ground(2800, 200, 1, Ground.Type.GRASS));
        Logger.log("Level erstellt", Logger.INFO);
        return new Level(enemies, obstacles, grounds, "images/backgrounds/background.png");
    }

    private Level createLevel2() {
        List<Enemy> enemies = new List<>();
        List<Obstacle> obstacles = new List<>();
        List<Ground> grounds = new List<>();
        grounds.add(new Ground(600, 1200, 20, Ground.Type.SOIL));
        grounds.add(new Ground(1400, 400, 60, Ground.Type.SOIL));
        return new Level(enemies, obstacles, grounds, "images/backgrounds/background2.jpg");
    }
}
