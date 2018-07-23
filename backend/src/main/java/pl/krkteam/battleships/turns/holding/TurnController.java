package pl.krkteam.battleships.turns.holding;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.turns.holding.dto.TurnResult;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TurnController {

    private TurnHolder turnHolder;

    public TurnController(TurnHolder turnHolder) {
        this.turnHolder = turnHolder;
    }

    @GetMapping(value = "/game/player/isTurn")
    public String isTurnOfPlayer(@RequestParam String playerName) {
        Player player = new Player(playerName);
        boolean result = turnHolder.isTurnOfPlayer(player);
        Gson gson = new Gson();
        return gson.toJson(new TurnResult(result));
    }

}