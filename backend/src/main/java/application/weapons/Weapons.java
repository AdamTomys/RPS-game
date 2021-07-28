package application.weapons;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface Weapons {
    String getName();
    List<String> getOpponentWinningWeapons();
    List<String> getOpponentLoosingWeapons();
}
