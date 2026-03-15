package com.pddstudio.highlightjs.utils

/**
 * This Class was created by Patrick J
 * on 09.06.16. For more Details and Licensing
 * have a look at the README.md
 */

object SourceUtils {

    @JvmStatic
    fun generateContent(source: String, style: String, language: String?, supportZoom: Boolean, showLineNumbers: Boolean): String {
        return getStylePageHeader(supportZoom) +
                getSourceForStyle(style) +
                (if (showLineNumbers) getLineNumberStyling() else "") +
                getScriptPageHeader(showLineNumbers) +
                getSourceForLanguage(source, language) +
                getTemplateFooter()
    }

    private fun getStylePageHeader(enableZoom: Boolean): String {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                (if (enableZoom) "" else "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\">\n") +
                "    <style type=\"text/css\">\n" +
                "       html, body {\n" +
                "           width:100%;\n" +
                "           height: 100%;\n" +
                "           margin: 0px;\n" +
                "           padding: 0px;\n" +
                "       }\n" +
                "       pre {\n" +
                "           margin: 0;\n" +
                "       }\n" +
                "   </style>\n"
    }

    private fun getScriptPageHeader(showLineNumbers: Boolean): String {
        return "    <script src=\"./highlight.pack.js\"></script>\n" +
                (if (showLineNumbers) "<script src=\"./highlightjs-line-numbers.min.js\"></script>\n" else "") +
                "    <script>hljs.initHighlightingOnLoad();</script>\n" +
                (if (showLineNumbers) "<script>hljs.initLineNumbersOnLoad();</script>\n" else "") +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0\" class=\"hljs\">\n"
    }

    private fun getLineNumberStyling(): String {
        return "<style type=\"text/css\">\n" +
                ".hljs-line-numbers {\n" +
                "\ttext-align: right;\n" +
                "\tborder-right: 1px solid #ccc;\n" +
                "\tcolor: #999;\n" +
                "\t-webkit-touch-callout: none;\n" +
                "\t-webkit-user-select: none;\n" +
                "\t-khtml-user-select: none;\n" +
                "\t-moz-user-select: none;\n" +
                "\t-ms-user-select: none;\n" +
                "\tuser-select: none;\n" +
                "}\n" +
                "</style>\n"
    }

    private fun getTemplateFooter(): String {
        return "</body>\n</html>\n"
    }

    private fun formatCode(code: String): String {
        return code.replace("<", "&lt;").replace(">", "&gt;")
    }

    private fun getSourceForStyle(style: String): String {
        return String.format("<link rel=\"stylesheet\" href=\"./styles/%s.css\">\n", style)
    }

    private fun getSourceForLanguage(source: String, language: String?): String {
        return if (language != null) {
            String.format("<pre><code class=\"%s\">%s</code></pre>\n", language, formatCode(source))
        } else {
            String.format("<pre><code>%s</code></pre>\n", formatCode(source))
        }
    }
}
