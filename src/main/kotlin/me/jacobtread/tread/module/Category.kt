package me.jacobtread.tread.module

import org.apache.commons.lang3.text.WordUtils

enum class Category {

    COMBAT,
    MOVEMENT,
    PLAYER,
    RENDER,
    WORLD,
    MISC;

    fun prettyName(): String {
        return WordUtils.capitalizeFully(name) ?: name
    }

}