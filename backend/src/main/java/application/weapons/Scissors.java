package application.weapons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.List;

import static application.Constants.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Scissors implements Weapons {
    final String name = SCISSORS;
    final List<String> opponentWinningWeapons = Arrays.asList(ROCK);
    final List<String> opponentLoosingWeapons = Arrays.asList(PAPER);
}
