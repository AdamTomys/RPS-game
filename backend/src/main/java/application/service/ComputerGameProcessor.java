package application.service;

import application.weapons.Weapons;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import static application.Constants.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComputerGameProcessor implements GameProcessor {

    final Logger logger = LoggerFactory.getLogger(ComputerGameProcessor.class);

    public String performGame(String playerWeaponString) {

        if (!isWeaponStringCorrect(playerWeaponString)) {
            return UNKNOWN_WEAPON;
        }
        Weapons playerWeapon = null;
        Weapons computerWeapon = null;

        try {
            playerWeapon = createWeapon(playerWeaponString);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        try {
            computerWeapon = drawComputerWeapon();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        if (playerWeapon != null && computerWeapon != null) {
            return compareWeaponsAndGetWinner(playerWeapon, computerWeapon);
        }

        return "Game occurred an error. Please contact with administrator.";
    }

    private Weapons drawComputerWeapon() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<String> weapons = WEAPON_LIST;
        Random generator = new Random();
        int weaponNumber = generator.nextInt(weapons.size());
        String generatedWeapon = weapons.get(weaponNumber);
        return (Weapons) Class.forName(
                WEAPON_PACKAGES + generatedWeapon,
                true,
                this.getClass().getClassLoader()).getDeclaredConstructor().newInstance();
    }

    private String compareWeaponsAndGetWinner(Weapons playerWeapon, Weapons computerWeapon) {

        if (playerWeapon.getOpponentLoosingWeapons().contains(computerWeapon.getName())) {
            return "Player chosen " + playerWeapon.getName() + " and computer choose " + computerWeapon.getName() + ". Player won!";
        } else if (playerWeapon.getOpponentWinningWeapons().contains(computerWeapon.getName())) {
            return "Player chosen " + playerWeapon.getName() + " and computer choose " + computerWeapon.getName() + ". Computer won!";
        }

        return DRAW;
    }
}
