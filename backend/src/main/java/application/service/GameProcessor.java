package application.service;

import application.weapons.Weapons;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static application.Constants.*;

public interface GameProcessor {

    List<String> weapons = WEAPON_LIST;

    default Boolean isWeaponStringCorrect(String playerWeapon) {
        return weapons.contains(playerWeapon);
    }

    default Weapons createWeapon(String weapon) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        for(String each : weapons) {
            if (each.equals(weapon)) {
                return (Weapons) Class.forName(
                        WEAPON_PACKAGES + each,
                        true,
                        this.getClass().getClassLoader()).getDeclaredConstructor().newInstance();
            }
        }
        return null;
    }
}
