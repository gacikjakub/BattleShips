package pl.krkteam.battleships.shooting.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.domain.GameBoardHolder;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.CoordinateDTO;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;
import pl.krkteam.battleships.shooting.dto.ShotDTO;
import pl.krkteam.battleships.shooting.dto.result.ShotResultDTO;
import pl.krkteam.battleships.shooting.services.ShotResultCheckerService;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ShootingController {


    private final ShotResultCheckerService shotResultCheckerService;
    private final Game game;

    public ShootingController(ShotResultCheckerService shotResultCheckerService, Game game) {
        this.shotResultCheckerService = shotResultCheckerService;
        this.game = game;
    }

    @PostMapping(value = "/game/player/shot")
    public String validateShot(@RequestBody String shotJson, @RequestParam String playerName) {
        final Player shootingPlayer = new Player(playerName);
        final Player opponentPlayer = getOpponentPlayer(shootingPlayer);
        ShotDTO shotDTO = convertToShotDTO(shotJson);

        final ShotResultDTO shotResultDTO = getShotResult(opponentPlayer, shotDTO);
        setResponseToOpponentPlayer(shotResultDTO, shotDTO, opponentPlayer);

        Gson gson = new Gson();
        return gson.toJson(shotResultDTO);
    }


    private ShotDTO convertToShotDTO(String shotJson) {
        Gson gson = new Gson();
        return gson.fromJson(shotJson, ShotDTO.class);
    }

    private Player getOpponentPlayer(Player shootingPlayer) {
        final GameBoardHolder gameBoardHolder = game.getGameBoardHolder();
        return gameBoardHolder.getOpponentPlayer(shootingPlayer);
    }

    private ShotResultDTO getShotResult(Player opponentPlayer, ShotDTO shotDTO) {
        final GameBoardHolder gameBoardHolder = game.getGameBoardHolder();
        final GameBoard opponentGameBoard = gameBoardHolder.getGameBoard(opponentPlayer);
        return shotResultCheckerService.checkShotResult(shotDTO, opponentGameBoard);
    }

    private void setResponseToOpponentPlayer(ShotResultDTO shotResultDTO, ShotDTO shotDTO, Player opponentPlayer) {
        CoordinateDTO shotCoordinates = shotDTO.getShotCoordinate();

        OpponentShotResult opponentShotResult = shotResultDTO
                .getOpponentShotResult(shotCoordinates.getY(),
                        shotCoordinates.getX());

        game.getShotResultQueueHolder().getShotResultQueue(opponentPlayer).addShotResult(opponentShotResult);
    }
}
