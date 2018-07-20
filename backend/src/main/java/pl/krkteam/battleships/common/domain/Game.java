package pl.krkteam.battleships.common.domain;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.opponent.shot.response.domain.ShotResultQueueHolder;

@Component
public class Game {

    public GameBoardHolder gameBoardHolder = new GameBoardHolder();

    public ShotResultQueueHolder shotResultQueueHolder = new ShotResultQueueHolder();

}
