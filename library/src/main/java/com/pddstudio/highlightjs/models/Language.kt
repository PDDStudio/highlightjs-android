package com.pddstudio.highlightjs.models

/**
 * Sealed interface representing a highlight.js language.
 *
 * Each built-in language is a [data object] usable directly (e.g. `Language.Kotlin`).
 * [Custom] allows arbitrary language class names for extensibility.
 * [companion] `@JvmField` entries provide Java interop parity with the old enum API.
 */
@Suppress("SpellCheckingInspection")
sealed interface Language {
    /** The highlight.js class name, or `null` for auto-detection. */
    val className: String?

    /** Returns [className] — kept for source compatibility with callers of the old enum API. */
    fun getName(): String? = className

    // ── Auto / Disable ────────────────────────────────────────────────────────
    data object AutoDetect : Language { override val className: String? = null }
    data object DisableHighlight : Language { override val className = "nohighlight" }

    // ── A ─────────────────────────────────────────────────────────────────────
    data object _1C : Language { override val className = "1c" }
    data object Abnf : Language { override val className = "abnf" }
    data object AccessLogs : Language { override val className = "accesslog" }
    data object Ada : Language { override val className = "ada" }
    data object ArmAssembler : Language { override val className = "arm" }
    data object AvrAssembler : Language { override val className = "avrasm" }
    data object ActionScript : Language { override val className = "actionscript" }
    data object AngleScript : Language { override val className = "anglescript" }
    data object Apache : Language { override val className = "apache" }
    data object AppleScript : Language { override val className = "applescript" }
    data object AsciiDoc : Language { override val className = "asciidoc" }
    data object AspectJ : Language { override val className = "aspectj" }
    data object AutoHotkey : Language { override val className = "autohotkey" }
    data object AutoIt : Language { override val className = "autoit" }
    data object Axapta : Language { override val className = "axapta" }
    data object Awk : Language { override val className = "awk" }

    // ── B ─────────────────────────────────────────────────────────────────────
    data object Bash : Language { override val className = "bash" }
    data object Shell : Language { override val className = "sh" }
    data object Zsh : Language { override val className = "zsh" }
    data object Basic : Language { override val className = "basic" }
    data object Bnf : Language { override val className = "bnf" }
    data object Brainfuck : Language { override val className = "brainfuck" }

    // ── C ─────────────────────────────────────────────────────────────────────
    data object C : Language { override val className = "c" }
    data object CSharp : Language { override val className = "csharp" }
    data object CPlusPlus : Language { override val className = "cpp" }
    data object CAl : Language { override val className = "cal" }
    data object CacheObjectScript : Language { override val className = "cos" }
    data object CMake : Language { override val className = "cmake" }
    data object Coq : Language { override val className = "coq" }
    data object Csp : Language { override val className = "csp" }
    data object Css : Language { override val className = "css" }
    data object CaptainProto : Language { override val className = "capnproto" }
    data object Chaos : Language { override val className = "chaos" }
    data object CiscoCli : Language { override val className = "cisco" }
    data object Clean : Language { override val className = "clean" }
    data object Clojure : Language { override val className = "clojure" }
    data object CoffeeScript : Language { override val className = "coffeescript" }
    data object CpcdoscPlus : Language { override val className = "cpc" }
    data object Crmsh : Language { override val className = "crmsh" }
    data object Crystal : Language { override val className = "crystal" }
    data object CypherNeo4j : Language { override val className = "cypher" }

    // ── D ─────────────────────────────────────────────────────────────────────
    data object D : Language { override val className = "d" }
    data object DnsZoneFile : Language { override val className = "dns" }
    data object Dos : Language { override val className = "dos" }
    data object Batch : Language { override val className = "bat" }
    data object Dart : Language { override val className = "dart" }
    data object Delphi : Language { override val className = "delphi" }
    data object Diff : Language { override val className = "diff" }
    data object Django : Language { override val className = "django" }
    data object Dockerfile : Language { override val className = "dockerfile" }
    data object Dsconfig : Language { override val className = "dsconfig" }
    data object Dts : Language { override val className = "dts" }
    data object Dust : Language { override val className = "dust" }
    data object Dylan : Language { override val className = "dylan" }

    // ── E ─────────────────────────────────────────────────────────────────────
    data object Ebnf : Language { override val className = "ebnf" }
    data object Elixir : Language { override val className = "elixir" }
    data object Elm : Language { override val className = "elm" }
    data object Erlang : Language { override val className = "erlang" }
    data object Excel : Language { override val className = "excel" }

    // ── F ─────────────────────────────────────────────────────────────────────
    data object FSharp : Language { override val className = "fsharp" }
    data object Fix : Language { override val className = "fix" }
    data object Flix : Language { override val className = "flix" }
    data object Fortran : Language { override val className = "fortran" }

    // ── G ─────────────────────────────────────────────────────────────────────
    data object GCode : Language { override val className = "gcode" }
    data object Gams : Language { override val className = "gams" }
    data object Gauss : Language { override val className = "gauss" }
    data object Gdscript : Language { override val className = "godot" }
    data object Gherkin : Language { override val className = "gherkin" }
    data object GnForNinja : Language { override val className = "gn" }
    data object Go : Language { override val className = "go" }
    data object GrammaticalFramework : Language { override val className = "gf" }
    data object Golo : Language { override val className = "golo" }
    data object Gradle : Language { override val className = "gradle" }
    data object Groovy : Language { override val className = "groovy" }

    // ── H ─────────────────────────────────────────────────────────────────────
    data object Html : Language { override val className = "html" }
    data object Xml : Language { override val className = "xml" }
    data object Http : Language { override val className = "http" }
    data object Haml : Language { override val className = "haml" }
    data object Handlebars : Language { override val className = "hbs" }
    data object Haskell : Language { override val className = "hs" }
    data object Haxe : Language { override val className = "hx" }
    data object Hy : Language { override val className = "hy" }

    // ── I ─────────────────────────────────────────────────────────────────────
    data object Ini : Language { override val className = "ini" }
    data object Inform7 : Language { override val className = "i7" }
    data object Irpf90 : Language { override val className = "irpf90" }

    // ── J ─────────────────────────────────────────────────────────────────────
    data object Json : Language { override val className = "json" }
    data object Java : Language { override val className = "java" }
    data object JavaScript : Language { override val className = "javascript" }
    data object Jolie : Language { override val className = "ol" }

    // ── K ─────────────────────────────────────────────────────────────────────
    data object Kotlin : Language { override val className = "kt" }

    // ── L ─────────────────────────────────────────────────────────────────────
    data object Latex : Language { override val className = "tex" }
    data object Lasso : Language { override val className = "lasso" }
    data object Leaf : Language { override val className = "leaf" }
    data object Lean : Language { override val className = "lean" }
    data object Less : Language { override val className = "less" }
    data object Ldif : Language { override val className = "ldif" }
    data object Lisp : Language { override val className = "lisp" }
    data object LiveCodeServer : Language { override val className = "livecodeserver" }
    data object LiveScript : Language { override val className = "livescript" }
    data object Llvm : Language { override val className = "llvm" }
    data object Lua : Language { override val className = "lua" }

    // ── M ─────────────────────────────────────────────────────────────────────
    data object Makefile : Language { override val className = "makefile" }
    data object Markdown : Language { override val className = "md" }
    data object Mathematica : Language { override val className = "mma" }
    data object Matlab : Language { override val className = "matlab" }
    data object Maxima : Language { override val className = "maxima" }
    data object MayaEmbeddedLanguage : Language { override val className = "mel" }
    data object Mercury : Language { override val className = "mercury" }
    data object MircScriptingLanguage : Language { override val className = "mrc" }
    data object Mizar : Language { override val className = "mizar" }
    data object Mojolicious : Language { override val className = "mojolicious" }
    data object Monkey : Language { override val className = "monkey" }
    data object Moonscript : Language { override val className = "moonscript" }

    // ── N ─────────────────────────────────────────────────────────────────────
    data object N1Ql : Language { override val className = "n1ql" }
    data object Nsis : Language { override val className = "nsis" }
    data object Nginx : Language { override val className = "nginx" }
    data object Nimrod : Language { override val className = "nimrod" }
    data object Nix : Language { override val className = "nix" }

    // ── O ─────────────────────────────────────────────────────────────────────
    data object ObjectiveConstraintLanguage : Language { override val className = "ocl" }
    data object OCaml : Language { override val className = "ocaml" }
    data object ObjectiveC : Language { override val className = "objectivec" }
    data object OpenGlShadingLanguage : Language { override val className = "glsl" }
    data object OpenScad : Language { override val className = "scad" }
    data object OracleRulesLanguage : Language { override val className = "ruleslanguage" }
    data object Oxygene : Language { override val className = "oxygene" }

    // ── P ─────────────────────────────────────────────────────────────────────
    data object Pf : Language { override val className = "pf" }
    data object Php : Language { override val className = "php" }
    data object Parser3 : Language { override val className = "parser3" }
    data object Perl : Language { override val className = "perl" }
    data object PlainText : Language { override val className = "txt" }
    data object Pony : Language { override val className = "pony" }
    data object PostgreSql : Language { override val className = "pgsql" }
    data object PowerShell : Language { override val className = "ps" }
    data object Processing : Language { override val className = "processing" }
    data object Prolog : Language { override val className = "prolog" }
    data object Properties : Language { override val className = "properties" }
    data object ProtocolBuffers : Language { override val className = "protobuf" }
    data object Puppet : Language { override val className = "pp" }
    data object Python : Language { override val className = "python" }
    data object PythonProfilerResults : Language { override val className = "profile" }
    data object PythonRepl : Language { override val className = "pycon" }

    // ── Q ─────────────────────────────────────────────────────────────────────
    data object Q : Language { override val className = "k" }
    data object Qml : Language { override val className = "qml" }

    // ── R ─────────────────────────────────────────────────────────────────────
    data object R : Language { override val className = "r" }
    data object RazorCshtml : Language { override val className = "razor" }
    data object ReasonMl : Language { override val className = "re" }
    data object RenderManRib : Language { override val className = "rib" }
    data object RenderManRsl : Language { override val className = "rsl" }
    data object Roboconf : Language { override val className = "roboconf" }
    data object Ruby : Language { override val className = "ruby" }
    data object Rust : Language { override val className = "rust" }

    // ── S ─────────────────────────────────────────────────────────────────────
    data object Sas : Language { override val className = "sas" }
    data object Scss : Language { override val className = "scss" }
    data object Sql : Language { override val className = "sql" }
    data object StepPart21 : Language { override val className = "p21" }
    data object Scala : Language { override val className = "scala" }
    data object Scheme : Language { override val className = "scheme" }
    data object Scilab : Language { override val className = "sci" }
    data object Smali : Language { override val className = "smali" }
    data object Smalltalk : Language { override val className = "smalltalk" }
    data object Stan : Language { override val className = "stan" }
    data object Stata : Language { override val className = "stata" }
    data object Stylus : Language { override val className = "stylus" }
    data object SubUnit : Language { override val className = "subunit" }
    data object Swift : Language { override val className = "swift" }

    // ── T ─────────────────────────────────────────────────────────────────────
    data object TestAnythingProtocol : Language { override val className = "tap" }
    data object Tcl : Language { override val className = "tcl" }
    data object Tex : Language { override val className = "tex" }
    data object Thrift : Language { override val className = "thrift" }
    data object Tp : Language { override val className = "tp" }
    data object Twig : Language { override val className = "twig" }
    data object TypeScript : Language { override val className = "typescript" }

    // ── V ─────────────────────────────────────────────────────────────────────
    data object VbNet : Language { override val className = "vbnet" }
    data object VbScript : Language { override val className = "vbscript" }
    data object Vhdl : Language { override val className = "vhdl" }
    data object Vala : Language { override val className = "vala" }
    data object Verilog : Language { override val className = "v" }
    data object Vim : Language { override val className = "vim" }

    // ── X-Z ───────────────────────────────────────────────────────────────────
    data object X86Assembly : Language { override val className = "x86asm" }
    data object Xl : Language { override val className = "xl" }
    data object XQuery : Language { override val className = "xq" }
    data object Zephir : Language { override val className = "zep" }

    /** Extensibility: use any arbitrary highlight.js language class name. */
    data class Custom(override val className: String) : Language

    companion object {
        // Java interop — mirror the old enum constant names as @JvmField properties
        @JvmField val AUTO_DETECT: Language = AutoDetect
        @JvmField val DISABLE_HIGHLIGHT: Language = DisableHighlight
        @JvmField val ABNF: Language = Abnf
        @JvmField val ACCESS_LOGS: Language = AccessLogs
        @JvmField val ADA: Language = Ada
        @JvmField val ARM_ASSEMBLER: Language = ArmAssembler
        @JvmField val AVR_ASSEMBLER: Language = AvrAssembler
        @JvmField val ACTION_SCRIPT: Language = ActionScript
        @JvmField val ANGLE_SCRIPT: Language = AngleScript
        @JvmField val APACHE: Language = Apache
        @JvmField val APPLE_SCRIPT: Language = AppleScript
        @JvmField val ASCII_DOC: Language = AsciiDoc
        @JvmField val ASPECT_J: Language = AspectJ
        @JvmField val AUTO_HOTKEY: Language = AutoHotkey
        @JvmField val AUTO_IT: Language = AutoIt
        @JvmField val AXAPTA: Language = Axapta
        @JvmField val AWK: Language = Awk
        @JvmField val BASH: Language = Bash
        @JvmField val SHELL: Language = Shell
        @JvmField val ZSH: Language = Zsh
        @JvmField val BASIC: Language = Basic
        @JvmField val BNF: Language = Bnf
        @JvmField val BRAINFUCK: Language = Brainfuck
        @JvmField val C_SHARP: Language = CSharp
        @JvmField val C_PLUS_PLUS: Language = CPlusPlus
        @JvmField val C_AL: Language = CAl
        @JvmField val CACHE_OBJECT_SCRIPT: Language = CacheObjectScript
        @JvmField val C_MAKE: Language = CMake
        @JvmField val COQ: Language = Coq
        @JvmField val CSP: Language = Csp
        @JvmField val CSS: Language = Css
        @JvmField val CAPTAIN_PROTO: Language = CaptainProto
        @JvmField val CHAOS: Language = Chaos
        @JvmField val CISCO_CLI: Language = CiscoCli
        @JvmField val CLEAN: Language = Clean
        @JvmField val CLOJURE: Language = Clojure
        @JvmField val COFFEE_SCRIPT: Language = CoffeeScript
        @JvmField val CPCDOSC_PLUS: Language = CpcdoscPlus
        @JvmField val CRMSH: Language = Crmsh
        @JvmField val CRYSTAL: Language = Crystal
        @JvmField val CYPHER_NEO4J: Language = CypherNeo4j
        @JvmField val DNS_ZONE_FILE: Language = DnsZoneFile
        @JvmField val DOS: Language = Dos
        @JvmField val BATCH: Language = Batch
        @JvmField val DART: Language = Dart
        @JvmField val DELPHI: Language = Delphi
        @JvmField val DIFF: Language = Diff
        @JvmField val DJANGO: Language = Django
        @JvmField val DOCKER_FILE: Language = Dockerfile
        @JvmField val DSCONFIG: Language = Dsconfig
        @JvmField val DTS: Language = Dts
        @JvmField val DUST: Language = Dust
        @JvmField val DYLAN: Language = Dylan
        @JvmField val EBNF: Language = Ebnf
        @JvmField val ELIXIR: Language = Elixir
        @JvmField val ELM: Language = Elm
        @JvmField val ERLANG: Language = Erlang
        @JvmField val EXCEL: Language = Excel
        @JvmField val F_SHARP: Language = FSharp
        @JvmField val FIX: Language = Fix
        @JvmField val FLIX: Language = Flix
        @JvmField val FORTRAN: Language = Fortran
        @JvmField val G_CODE: Language = GCode
        @JvmField val GAMS: Language = Gams
        @JvmField val GAUSS: Language = Gauss
        @JvmField val GDSCRIPT: Language = Gdscript
        @JvmField val GHERKIN: Language = Gherkin
        @JvmField val GN_FOR_NINJA: Language = GnForNinja
        @JvmField val GO: Language = Go
        @JvmField val GRAMMATICAL_FRAMEWORK: Language = GrammaticalFramework
        @JvmField val GOLO: Language = Golo
        @JvmField val GRADLE: Language = Gradle
        @JvmField val GROOVY: Language = Groovy
        @JvmField val HTML: Language = Html
        @JvmField val XML: Language = Xml
        @JvmField val HTTP: Language = Http
        @JvmField val HAML: Language = Haml
        @JvmField val HANDLEBARS: Language = Handlebars
        @JvmField val HASKELL: Language = Haskell
        @JvmField val HAXE: Language = Haxe
        @JvmField val HY: Language = Hy
        @JvmField val INI: Language = Ini
        @JvmField val INFORM7: Language = Inform7
        @JvmField val IRPF90: Language = Irpf90
        @JvmField val JSON: Language = Json
        @JvmField val JAVA: Language = Java
        @JvmField val JAVA_SCRIPT: Language = JavaScript
        @JvmField val JOLIE: Language = Jolie
        @JvmField val KOTLIN: Language = Kotlin
        @JvmField val LATEX: Language = Latex
        @JvmField val LASSO: Language = Lasso
        @JvmField val LEAF: Language = Leaf
        @JvmField val LEAN: Language = Lean
        @JvmField val LESS: Language = Less
        @JvmField val LDIF: Language = Ldif
        @JvmField val LISP: Language = Lisp
        @JvmField val LIVE_CODE_SERVER: Language = LiveCodeServer
        @JvmField val LIVE_SCRIPT: Language = LiveScript
        @JvmField val LLVM: Language = Llvm
        @JvmField val LUA: Language = Lua
        @JvmField val MAKEFILE: Language = Makefile
        @JvmField val MARKDOWN: Language = Markdown
        @JvmField val MATHEMATICA: Language = Mathematica
        @JvmField val MATLAB: Language = Matlab
        @JvmField val MAXIMA: Language = Maxima
        @JvmField val MAYA_EMBEDDED_LANGUAGE: Language = MayaEmbeddedLanguage
        @JvmField val MERCURY: Language = Mercury
        @JvmField val MIRC_SCRIPTING_LANGUAGE: Language = MircScriptingLanguage
        @JvmField val MIZAR: Language = Mizar
        @JvmField val MOJOLICIOUS: Language = Mojolicious
        @JvmField val MONKEY: Language = Monkey
        @JvmField val MOONSCRIPT: Language = Moonscript
        @JvmField val N1QL: Language = N1Ql
        @JvmField val NSIS: Language = Nsis
        @JvmField val NGINX: Language = Nginx
        @JvmField val NIMROD: Language = Nimrod
        @JvmField val NIX: Language = Nix
        @JvmField val OBJECTIVE_CONSTRAINT_LANGUAGE: Language = ObjectiveConstraintLanguage
        @JvmField val O_CAML: Language = OCaml
        @JvmField val OBJECTIVE_C: Language = ObjectiveC
        @JvmField val OPENGL_SHADING_LANGUAGE: Language = OpenGlShadingLanguage
        @JvmField val OPEN_SCAD: Language = OpenScad
        @JvmField val ORACLE_RULES_LANGUAGE: Language = OracleRulesLanguage
        @JvmField val OXYGENE: Language = Oxygene
        @JvmField val PF: Language = Pf
        @JvmField val PHP: Language = Php
        @JvmField val PARSER3: Language = Parser3
        @JvmField val PERL: Language = Perl
        @JvmField val PLAIN_TEXT: Language = PlainText
        @JvmField val PONY: Language = Pony
        @JvmField val POSTGRE_SQL: Language = PostgreSql
        @JvmField val POWER_SHELL: Language = PowerShell
        @JvmField val PROCESSING: Language = Processing
        @JvmField val PROLOG: Language = Prolog
        @JvmField val PROPERTIES: Language = Properties
        @JvmField val PROTOCOL_BUFFERS: Language = ProtocolBuffers
        @JvmField val PUPPET: Language = Puppet
        @JvmField val PYTHON: Language = Python
        @JvmField val PYTHON_PROFILER_RESULTS: Language = PythonProfilerResults
        @JvmField val PYTHON_REPL: Language = PythonRepl
        @JvmField val QML: Language = Qml
        @JvmField val RAZOR_CSHTML: Language = RazorCshtml
        @JvmField val REASON_ML: Language = ReasonMl
        @JvmField val RENDER_MAN_RIB: Language = RenderManRib
        @JvmField val RENDER_MAN_RSL: Language = RenderManRsl
        @JvmField val ROBOCONF: Language = Roboconf
        @JvmField val RUBY: Language = Ruby
        @JvmField val RUST: Language = Rust
        @JvmField val SAS: Language = Sas
        @JvmField val SCSS: Language = Scss
        @JvmField val SQL: Language = Sql
        @JvmField val STEP_PART_21: Language = StepPart21
        @JvmField val SCALA: Language = Scala
        @JvmField val SCHEME: Language = Scheme
        @JvmField val SCILAB: Language = Scilab
        @JvmField val SMALI: Language = Smali
        @JvmField val SMALLTALK: Language = Smalltalk
        @JvmField val STAN: Language = Stan
        @JvmField val STATA: Language = Stata
        @JvmField val STYLUS: Language = Stylus
        @JvmField val SUB_UNIT: Language = SubUnit
        @JvmField val SWIFT: Language = Swift
        @JvmField val TEST_ANYTHING_PROTOCOL: Language = TestAnythingProtocol
        @JvmField val TCL: Language = Tcl
        @JvmField val TEX: Language = Tex
        @JvmField val THRIFT: Language = Thrift
        @JvmField val TP: Language = Tp
        @JvmField val TWIG: Language = Twig
        @JvmField val TYPE_SCRIPT: Language = TypeScript
        @JvmField val VB_NET: Language = VbNet
        @JvmField val VB_SCRIPT: Language = VbScript
        @JvmField val VHDL: Language = Vhdl
        @JvmField val VALA: Language = Vala
        @JvmField val VERILOG: Language = Verilog
        @JvmField val VIM: Language = Vim
        @JvmField val X86_ASSEMBLY: Language = X86Assembly
        @JvmField val XL: Language = Xl
        @JvmField val X_QUERY: Language = XQuery
        @JvmField val ZEPHIR: Language = Zephir

        /**
         * Returns all predefined [Language] instances (excludes [Custom]).
         * Provided for source-compatibility with the old enum [Language.values()] call.
         */
        @JvmStatic fun values(): Array<Language> = arrayOf(
            AUTO_DETECT, DISABLE_HIGHLIGHT, _1C, ABNF, ACCESS_LOGS, ADA,
            ARM_ASSEMBLER, AVR_ASSEMBLER, ACTION_SCRIPT, ANGLE_SCRIPT, APACHE,
            APPLE_SCRIPT, ASCII_DOC, ASPECT_J, AUTO_HOTKEY, AUTO_IT, AXAPTA, AWK,
            BASH, SHELL, ZSH, BASIC, BNF, BRAINFUCK,
            C, C_SHARP, C_PLUS_PLUS, C_AL, CACHE_OBJECT_SCRIPT, C_MAKE, COQ, CSP, CSS,
            CAPTAIN_PROTO, CHAOS, CISCO_CLI, CLEAN, CLOJURE, COFFEE_SCRIPT,
            CPCDOSC_PLUS, CRMSH, CRYSTAL, CYPHER_NEO4J,
            DNS_ZONE_FILE, DOS, BATCH, DART, DELPHI, DIFF, DJANGO, DOCKER_FILE,
            DSCONFIG, DTS, DUST, DYLAN,
            EBNF, ELIXIR, ELM, ERLANG, EXCEL,
            F_SHARP, FIX, FLIX, FORTRAN,
            G_CODE, GAMS, GAUSS, GDSCRIPT, GHERKIN, GN_FOR_NINJA, GO,
            GRAMMATICAL_FRAMEWORK, GOLO, GRADLE, GROOVY,
            HTML, XML, HTTP, HAML, HANDLEBARS, HASKELL, HAXE, HY,
            INI, INFORM7, IRPF90,
            JSON, JAVA, JAVA_SCRIPT, JOLIE,
            KOTLIN,
            LATEX, LASSO, LEAF, LEAN, LESS, LDIF, LISP, LIVE_CODE_SERVER, LIVE_SCRIPT, LLVM, LUA,
            MAKEFILE, MARKDOWN, MATHEMATICA, MATLAB, MAXIMA, MAYA_EMBEDDED_LANGUAGE,
            MERCURY, MIRC_SCRIPTING_LANGUAGE, MIZAR, MOJOLICIOUS, MONKEY, MOONSCRIPT,
            N1QL, NSIS, NGINX, NIMROD, NIX,
            OBJECTIVE_CONSTRAINT_LANGUAGE, O_CAML, OBJECTIVE_C, OPENGL_SHADING_LANGUAGE,
            OPEN_SCAD, ORACLE_RULES_LANGUAGE, OXYGENE,
            PF, PHP, PARSER3, PERL, PLAIN_TEXT, PONY, POSTGRE_SQL, POWER_SHELL,
            PROCESSING, PROLOG, PROPERTIES, PROTOCOL_BUFFERS, PUPPET,
            PYTHON, PYTHON_PROFILER_RESULTS, PYTHON_REPL,
            Q, QML,
            R, RAZOR_CSHTML, REASON_ML, RENDER_MAN_RIB, RENDER_MAN_RSL, ROBOCONF, RUBY, RUST,
            SAS, SCSS, SQL, STEP_PART_21, SCALA, SCHEME, SCILAB, SMALI, SMALLTALK,
            STAN, STATA, STYLUS, SUB_UNIT, SWIFT,
            TEST_ANYTHING_PROTOCOL, TCL, TEX, THRIFT, TP, TWIG, TYPE_SCRIPT,
            VB_NET, VB_SCRIPT, VHDL, VALA, VERILOG, VIM,
            X86_ASSEMBLY, XL, X_QUERY, ZEPHIR
        )

        // ── Convenience aliases for single-letter / underscore-prefixed languages ──
        @JvmField val _1C: Language = Language._1C
        @JvmField val C: Language = Language.C
        @JvmField val Q: Language = Language.Q
        @JvmField val R: Language = Language.R
    }
}
