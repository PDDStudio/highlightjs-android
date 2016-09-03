package com.pddstudio.highlightjs.models;

/**
 * This Class was created by Patrick J
 * on 09.06.16. For more Details and Licensing
 * have a look at the README.md
 */

public enum Theme {
    AGATE("agate"),
    ANDROID_STUDIO("androidstudio"),
    ARDUINO_LIGHT("arduino-light"),
    ARTA("arta"),
    ASCETIC("ascetic"),
    ATELIER_CAVE_DARK("atelier-cave-dark"),
    ATELIER_CAVE_LIGHT("atelier-cave-light"),
    ATELIER_DUNE_DARK("atelier-dune-dark"),
    ATELIER_DUNE_LIGHT("atelier-dune-light"),
    ATELIER_ESTUARY_DARK("atelier-estuary-dark"),
    ATELIER_ESTUARY_LIGHT("atelier-estuary-light"),
    ATELIER_FOREST_DARK("atelier-forest-dark"),
    ATELIER_FOREST_LIGHT("atelier-forest-light"),
    ATELIER_HEATH_DARK("atelier-heath-dark"),
    ATELIER_HEATH_LIGHT("atelier-heath-light"),
    ATELIER_LAKESIDE_DARK("atelier-lakeside-dark"),
    ATELIER_LAKESIDE_LIGHT("atelier-lakeside-light"),
    ATELIER_PLATEAU_DARK("atelier-plateau-dark"),
    ATELIER_PLATEAU_LIGHT("atelier-plateau-light"),
    ATELIER_SAVANNA_DARK("atelier-savanna-dark"),
    ATELIER_SAVANNA_LIGHT("atelier-savanna-light"),
    ATELIER_SEASIDE_DARK("atelier-seaside-dark"),
    ATELIER_SEASIDE_LIGHT("atelier-seaside-light"),
    ATELIER_SULPHURPOOL_DARK("atelier-sulphurpool-dark"),
    ATELIER_SULPHURPOOL_LIGHT("atelier-sulphurpool-light"),
    ATOM_ONE_DARK("atom-one-dark"),
    ATOM_ONE_LIGHT("atom-one-light"),
    BROWN_PAPER("brown-paper"),
    CODEPEN_EMBED("codepen-embed"),
    COLOR_BREWER("color-brewer"),
    DARK("dark"),
    DARKULA("darkula"),
    DEFAULT("default"),
    DOCCO("docco"),
    DRAKULA("drakula"),
    FAR("far"),
    FOUNDATION("foundaiton"),
    GITHUB("github"),
    GITHUB_GIST("github-gist"),
    GOOGLECODE("googlecode"),
    GRAYSCALE("grayscale"),
    GRUVBOX_DARK("gruvbox-dark"),
    GRUVBOX_LIGHT("gruvbox-light"),
    HOPSCOTCH("hopscotch"),
    HYBRID("hybrid"),
    IDEA("idea"),
    IR_BLACK("ir-black"),
    KIMBIE_DARK("kimbie.dark"),
    KIMBIE_LIGHT("kimbie.light"),
    MAGULA("magula"),
    MONO_BLUE("mono-blue"),
    MONOKAI("monokai"),
    MONOKAI_SUBLIME("monokai-sublime"),
    OBSIDIAN("obsidian"),
    OCEAN("ocean"),
    PARAISO_DARK("paraiso-dark"),
    PARAISO_LIGHT("paraiso-light"),
    POJOAQUE("pojoaque"),
    PURE_BASIC("purebasic"),
    QT_CREATOR_DARK("qtcreator_dark"),
    QT_CREATOR_LIGHT("qtcreator_light"),
    RAILSCASTS("railscasts"),
    RAINBOX("rainbow"),
    SCHOOL_BOOK("school-book"),
    SOLARIZED_DARK("solarized-dark"),
    SOLARIZED_LIGHT("solarized-light"),
    SUNBURST("sunburst"),
    TOMORROW("tomorrow"),
    TOMORROW_NIGHT_BLUE("tomorrow-night-blue"),
    TOMORROW_NIGHT_BRIGHT("tomorrow-night-bright"),
    TOMORROW_NIGHT("tomorrow-night"),
    TOMORROW_NIGHT_EIGHTIES("tomorrow-night-eighties"),
    VS("vs"),
    X_CODE("xcode"),
    XT256("xt256"),
    ZENBURN("zenburn");

    private final String themeName;

    Theme(String themeName) {
        this.themeName = themeName;
    }

    public String getName() {
        return themeName;
    }

}
