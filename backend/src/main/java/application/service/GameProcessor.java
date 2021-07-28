package application.service;

import application.weapons.Weapons;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static application.Constants.*;

public interface GameProcessor {

    default Boolean isWeaponStringCorrect(String playerWeapon) {
        return playerWeapon.equals(ROCK) || playerWeapon.equals(PAPER) || playerWeapon.equals(SCISSORS);
    }

    default Weapons createWeapon(String weapon) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List<String> weapons = WEAPON_LIST;
        for(String each : weapons) {
            if (each.equals(weapon)) {
                return (Weapons) Class.forName(
                        "application.weapons." + each,
                        true,
                        this.getClass().getClassLoader()).getDeclaredConstructor().newInstance();
            }
        }
        return null;
    }
}
