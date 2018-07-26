package pl.krkteam.battleships.room.holder;

import org.springframework.stereotype.Component;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.dto.create.room.CreateResultDTO;
import pl.krkteam.battleships.room.holder.dto.create.room.CreateRoomOkDTO;
import pl.krkteam.battleships.room.holder.dto.create.room.CreateRoomWrongDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentAbsentDTO;
import pl.krkteam.battleships.wait.opponent.dto.WaiterResponseDTO;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoomHolder {
    private Map<String, Room> roomMap = new ConcurrentHashMap<>();

    public List<Room> getRoomList() {
        List<Room> roomList = new ArrayList<>(roomMap.values());
        return Collections.unmodifiableList(roomList);
    }

    public JoinResultDTO joinPlayer(String roomName, Player player) {
        final Room room = roomMap.get(roomName);
        return room.joinPlayer(player);
    }

    public WaiterResponseDTO isOpponentInRoom(String roomName, Player player) {
        final Room room = roomMap.get(roomName);
        if (!room.isPlayerBelongToRoom(player)) {
            return new OpponentAbsentDTO();
        }
        return room.areBothPlayers();
    }

    public Room getRoom(String roomName) {
        final Room room = roomMap.get(roomName);
        if (room == null) {
            throw new NoSuchElementException();
        }
        return room;
    }

    public CreateResultDTO createRoom(Player player, String roomName) {
        if (roomMap.containsKey(roomName)) {
            return new CreateRoomWrongDTO();
        }

        Room room = new Room(roomName);
        roomMap.put(roomName, room);

        room.joinPlayer(player);
        return new CreateRoomOkDTO();
    }
}
