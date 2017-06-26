package gui;

import model.Entity;
import model.Level;
import model.Obstacle;
import model.Player;
import physics.GameConstants;
import util.Point;
import util.list.List;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class LobbyView extends AbstractView {
    private static LobbyView instance;

    private LobbyView() {
        super();
        JButton temp = new JButton("temp");
        temp.addActionListener(actionEvent -> {
            LevelView levelView = new LevelView(createLevel1());
            MainFrame.getInstance().changeTo(levelView);
            levelView.setFocusable(true);
            levelView.requestFocusInWindow();
        });
        add(temp);
    }

    public void update() {

    }

    public static LobbyView getInstance() {
        if (instance == null)
            instance = new LobbyView();
        return instance;
    }

    /**
     * Hässliche Hardcoded-Level-Methode
     */
    private Level createLevel1() {
        List<Obstacle> obstacles = new List<>();
        List<Entity> entities = new List<>();
        entities.add(new Player(new Point(getWidth() / 2, GameConstants.GROUND_LEVEL)));
        Level level1 = new Level("images/backgrounds/background.png", obstacles, entities, 1000);
        return level1;
    }
}
