package com.alura.literalura.model;

public enum CodigoIdioma {
    ENGLISH("[en]", "inglés"),
    GERMAN("[de]", "alemán"),
    FRENCH("[fr]", "francés"),
    SPANISH("[es]", "español"),
    ITALIAN("[it]", "italiano"),
    RUSSIAN("[ru]", "ruso"),
    CHINESE("[zh]", "chino"),
    PORTUGUES("[pt]", "portugués");

    private String codigoGutendex;
    private String codigoEspanol;

    CodigoIdioma(String codigoGutendex, String codigoEspanol) {
        this.codigoGutendex = codigoGutendex;
        this.codigoEspanol = codigoEspanol;
    }

    //Metodo cuyo propósito es convertir un valor de tipo String a un objeto de tipo CodigoIdioma.
    public static CodigoIdioma getStringCode(String text) {
        for (CodigoIdioma idioma : CodigoIdioma.values()) {
            if (idioma.codigoGutendex.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("No se encontró la opción en este idioma: " + text);
    }

    public static CodigoIdioma getSpanolCode (String text) {
        for (CodigoIdioma idioma : CodigoIdioma.values()) {
            if (idioma.codigoEspanol.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("No se encontró la opción en este idioma: " + text);
    }

}
