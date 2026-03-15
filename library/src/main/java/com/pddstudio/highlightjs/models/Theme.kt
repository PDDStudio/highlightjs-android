package com.pddstudio.highlightjs.models

/**
 * Sealed interface representing a highlight.js theme.
 *
 * Each built-in theme is a [data object] usable directly (e.g. `Theme.Dracula`).
 * [Custom] allows arbitrary theme names for extensibility.
 * [companion] `@JvmField` entries provide Java interop parity with the old enum API.
 */
@Suppress("SpellCheckingInspection")
sealed interface Theme {
    /** The highlight.js CSS theme name. */
    val themeName: String

    /** Returns [themeName] — kept for source compatibility with callers of the old enum API. */
    fun getName(): String = themeName

    data object _1CLight : Theme { override val themeName = "1c-light" }
    data object A11yDark : Theme { override val themeName = "a11y-dark" }
    data object A11yLight : Theme { override val themeName = "a11y-light" }
    data object Agate : Theme { override val themeName = "agate" }
    data object AnOldHope : Theme { override val themeName = "an-old-hope" }
    data object AndroidStudio : Theme { override val themeName = "androidstudio" }
    data object ArduinoLight : Theme { override val themeName = "arduino-light" }
    data object Arta : Theme { override val themeName = "arta" }
    data object Ascetic : Theme { override val themeName = "ascetic" }
    data object AtomOneDark : Theme { override val themeName = "atom-one-dark" }
    data object AtomOneDarkReasonable : Theme { override val themeName = "atom-one-dark-reasonable" }
    data object AtomOneLight : Theme { override val themeName = "atom-one-light" }
    data object BrownPaper : Theme { override val themeName = "brown-paper" }
    data object CodepenEmbed : Theme { override val themeName = "codepen-embed" }
    data object ColorBrewer : Theme { override val themeName = "color-brewer" }
    data object CybertopiaCherry : Theme { override val themeName = "cybertopia-cherry" }
    data object CybertopiaDimmer : Theme { override val themeName = "cybertopia-dimmer" }
    data object CybertopiaIcecap : Theme { override val themeName = "cybertopia-icecap" }
    data object CybertopiaSaturated : Theme { override val themeName = "cybertopia-saturated" }
    data object Dark : Theme { override val themeName = "dark" }
    data object Default : Theme { override val themeName = "default" }
    data object Devibeans : Theme { override val themeName = "devibeans" }
    data object Docco : Theme { override val themeName = "docco" }
    data object Far : Theme { override val themeName = "far" }
    data object Felipec : Theme { override val themeName = "felipec" }
    data object Foundation : Theme { override val themeName = "foundation" }
    data object Github : Theme { override val themeName = "github" }
    data object GithubDark : Theme { override val themeName = "github-dark" }
    data object GithubDarkDimmed : Theme { override val themeName = "github-dark-dimmed" }
    data object Gml : Theme { override val themeName = "gml" }
    data object Googlecode : Theme { override val themeName = "googlecode" }
    data object GradientDark : Theme { override val themeName = "gradient-dark" }
    data object GradientLight : Theme { override val themeName = "gradient-light" }
    data object Grayscale : Theme { override val themeName = "grayscale" }
    data object Hybrid : Theme { override val themeName = "hybrid" }
    data object Idea : Theme { override val themeName = "idea" }
    data object IntellijLight : Theme { override val themeName = "intellij-light" }
    data object IrBlack : Theme { override val themeName = "ir-black" }
    data object IsblEditorDark : Theme { override val themeName = "isbl-editor-dark" }
    data object IsblEditorLight : Theme { override val themeName = "isbl-editor-light" }
    data object KimbieDark : Theme { override val themeName = "kimbie-dark" }
    data object KimbieLight : Theme { override val themeName = "kimbie-light" }
    data object Lightfair : Theme { override val themeName = "lightfair" }
    data object Lioshi : Theme { override val themeName = "lioshi" }
    data object Magula : Theme { override val themeName = "magula" }
    data object MonoBlue : Theme { override val themeName = "mono-blue" }
    data object Monokai : Theme { override val themeName = "monokai" }
    data object MonokaiSublime : Theme { override val themeName = "monokai-sublime" }
    data object NightOwl : Theme { override val themeName = "night-owl" }
    data object NnfxDark : Theme { override val themeName = "nnfx-dark" }
    data object NnfxLight : Theme { override val themeName = "nnfx-light" }
    data object Nord : Theme { override val themeName = "nord" }
    data object Obsidian : Theme { override val themeName = "obsidian" }
    data object PandaSyntaxDark : Theme { override val themeName = "panda-syntax-dark" }
    data object PandaSyntaxLight : Theme { override val themeName = "panda-syntax-light" }
    data object ParaisoDark : Theme { override val themeName = "paraiso-dark" }
    data object ParaisoLight : Theme { override val themeName = "paraiso-light" }
    data object Pojoaque : Theme { override val themeName = "pojoaque" }
    data object PureBasic : Theme { override val themeName = "purebasic" }
    data object QtCreatorDark : Theme { override val themeName = "qtcreator-dark" }
    data object QtCreatorLight : Theme { override val themeName = "qtcreator-light" }
    data object Rainbow : Theme { override val themeName = "rainbow" }
    data object RosePine : Theme { override val themeName = "rose-pine" }
    data object RosePineDawn : Theme { override val themeName = "rose-pine-dawn" }
    data object RosePineMoon : Theme { override val themeName = "rose-pine-moon" }
    data object Routeros : Theme { override val themeName = "routeros" }
    data object SchoolBook : Theme { override val themeName = "school-book" }
    data object ShadesOfPurple : Theme { override val themeName = "shades-of-purple" }
    data object Srcery : Theme { override val themeName = "srcery" }
    data object StackoverflowDark : Theme { override val themeName = "stackoverflow-dark" }
    data object StackoverflowLight : Theme { override val themeName = "stackoverflow-light" }
    data object Sunburst : Theme { override val themeName = "sunburst" }
    data object TokyoNightDark : Theme { override val themeName = "tokyo-night-dark" }
    data object TokyoNightLight : Theme { override val themeName = "tokyo-night-light" }
    data object TomorrowNightBlue : Theme { override val themeName = "tomorrow-night-blue" }
    data object TomorrowNightBright : Theme { override val themeName = "tomorrow-night-bright" }
    data object Vs : Theme { override val themeName = "vs" }
    data object Vs2015 : Theme { override val themeName = "vs2015" }
    data object XCode : Theme { override val themeName = "xcode" }
    data object Xt256 : Theme { override val themeName = "xt256" }

    /** Extensibility: use any arbitrary highlight.js theme name. */
    data class Custom(override val themeName: String) : Theme

    companion object {
        // Java interop — mirror the old enum constant names as @JvmField properties
        @JvmField val _1C_LIGHT: Theme = _1CLight
        @JvmField val A11Y_DARK: Theme = A11yDark
        @JvmField val A11Y_LIGHT: Theme = A11yLight
        @JvmField val AGATE: Theme = Agate
        @JvmField val AND_OLD_HOPE: Theme = AnOldHope
        @JvmField val ANDROID_STUDIO: Theme = AndroidStudio
        @JvmField val ARDUINO_LIGHT: Theme = ArduinoLight
        @JvmField val ARTA: Theme = Arta
        @JvmField val ASCETIC: Theme = Ascetic
        @JvmField val ATOM_ONE_DARK: Theme = AtomOneDark
        @JvmField val ATOM_ONE_DARK_REASONABLE: Theme = AtomOneDarkReasonable
        @JvmField val ATOM_ONE_LIGHT: Theme = AtomOneLight
        @JvmField val BROWN_PAPER: Theme = BrownPaper
        @JvmField val CODEPEN_EMBED: Theme = CodepenEmbed
        @JvmField val COLOR_BREWER: Theme = ColorBrewer
        @JvmField val CYBERTOPIA_CHERRY: Theme = CybertopiaCherry
        @JvmField val CYBERTOPIA_DIMMER: Theme = CybertopiaDimmer
        @JvmField val CYBERTOPIA_ICECAP: Theme = CybertopiaIcecap
        @JvmField val CYBERTOPIA_SATURATED: Theme = CybertopiaSaturated
        @JvmField val DARK: Theme = Dark
        @JvmField val DEFAULT: Theme = Default
        @JvmField val DEVIBEANS: Theme = Devibeans
        @JvmField val DOCCO: Theme = Docco
        @JvmField val FAR: Theme = Far
        @JvmField val FELIPEC: Theme = Felipec
        @JvmField val FOUNDATION: Theme = Foundation
        @JvmField val GITHUB: Theme = Github
        @JvmField val GITHUB_DARK: Theme = GithubDark
        @JvmField val GITHUB_DARK_DIMMED: Theme = GithubDarkDimmed
        @JvmField val GML: Theme = Gml
        @JvmField val GOOGLECODE: Theme = Googlecode
        @JvmField val GRADIENT_DARK: Theme = GradientDark
        @JvmField val GRADIENT_LIGHT: Theme = GradientLight
        @JvmField val GRAYSCALE: Theme = Grayscale
        @JvmField val HYBRID: Theme = Hybrid
        @JvmField val IDEA: Theme = Idea
        @JvmField val INTELLIJ_LIGHT: Theme = IntellijLight
        @JvmField val IR_BLACK: Theme = IrBlack
        @JvmField val ISBL_EDITOR_DARK: Theme = IsblEditorDark
        @JvmField val ISBL_EDITOR_LIGHT: Theme = IsblEditorLight
        @JvmField val KIMBIE_DARK: Theme = KimbieDark
        @JvmField val KIMBIE_LIGHT: Theme = KimbieLight
        @JvmField val LIGHTFAIR: Theme = Lightfair
        @JvmField val LIOSHI: Theme = Lioshi
        @JvmField val MAGULA: Theme = Magula
        @JvmField val MONO_BLUE: Theme = MonoBlue
        @JvmField val MONOKAI: Theme = Monokai
        @JvmField val MONOKAI_SUBLIME: Theme = MonokaiSublime
        @JvmField val NIGHT_OWL: Theme = NightOwl
        @JvmField val NNFX_DARK: Theme = NnfxDark
        @JvmField val NNFX_LIGHT: Theme = NnfxLight
        @JvmField val NORD: Theme = Nord
        @JvmField val OBSIDIAN: Theme = Obsidian
        @JvmField val PANDA_SYNTAX_DARK: Theme = PandaSyntaxDark
        @JvmField val PANDA_SYNTAX_LIGHT: Theme = PandaSyntaxLight
        @JvmField val PARAISO_DARK: Theme = ParaisoDark
        @JvmField val PARAISO_LIGHT: Theme = ParaisoLight
        @JvmField val POJOAQUE: Theme = Pojoaque
        @JvmField val PURE_BASIC: Theme = PureBasic
        @JvmField val QT_CREATOR_DARK: Theme = QtCreatorDark
        @JvmField val QT_CREATOR_LIGHT: Theme = QtCreatorLight
        @JvmField val RAINBOW: Theme = Rainbow
        @JvmField val ROSE_PINE: Theme = RosePine
        @JvmField val ROSE_PINE_DAWN: Theme = RosePineDawn
        @JvmField val ROSE_PINE_MOON: Theme = RosePineMoon
        @JvmField val ROUTEROS: Theme = Routeros
        @JvmField val SCHOOL_BOOK: Theme = SchoolBook
        @JvmField val SHADES_OF_PURPLE: Theme = ShadesOfPurple
        @JvmField val SRCERY: Theme = Srcery
        @JvmField val STACKOVERFLOW_DARK: Theme = StackoverflowDark
        @JvmField val STACKOVERFLOW_LIGHT: Theme = StackoverflowLight
        @JvmField val SUNBURST: Theme = Sunburst
        @JvmField val TOKYO_NIGHT_DARK: Theme = TokyoNightDark
        @JvmField val TOKYO_NIGHT_LIGHT: Theme = TokyoNightLight
        @JvmField val TOMORROW_NIGHT_BLUE: Theme = TomorrowNightBlue
        @JvmField val TOMORROW_NIGHT_BRIGHT: Theme = TomorrowNightBright
        @JvmField val VS: Theme = Vs
        @JvmField val VS2015: Theme = Vs2015
        @JvmField val X_CODE: Theme = XCode
        @JvmField val XT256: Theme = Xt256
    }
}
