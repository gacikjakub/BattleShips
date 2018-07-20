package pl.krkteam.battleships.opponent.shot.response.domain;

import pl.krkteam.battleships.common.domain.player.Player;

import java.util.*;

public class ShotResultQueueHolder {

    private Map<Player, ShotResultQueue> playerShotResultQueueMap = new HashMap<>();

    public ShotResultQueueHolder() {}

    public ShotResultQueueHolder(Player player1, Player player2) {
        initializeQueueHolder(player1, player2);
    }

    private void initializeQueueHolder(Player player1, Player player2) {
        if (player1 == null || player2 == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        playerShotResultQueueMap.put(player1, new ShotResultQueue());
        playerShotResultQueueMap.put(player2, new ShotResultQueue());
    }

    public boolean addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (playerShotResultQueueMap.containsKey(player)) {
            return false;
        }
        playerShotResultQueueMap.put(player, new ShotResultQueue());
        return true;
    }

    public ShotResultQueue getShotResultQueue(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (!playerShotResultQueueMap.containsKey(player)) {
            throw new NoSuchElementException("Given player does not belong to this holder");
        }
        return playerShotResultQueueMap.get(player);
    }

    public Player getOpponent(Player player) {
        Set<Player> all = playerShotResultQueueMap.keySet();
        List<Player> allList = new ArrayList(all);

        allList.remove(player);

        return allList.get(0);

    }
}