package ru.encrypting.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum LanguageInput
{
    RUSSIAN("Русский", "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"),
    ENGLISH("Английский", "abcdefghijklmnopqrstuvwxyz");

    String languageName;
    String languageAlphabet;

    @Override
    public String toString()
    {
        return languageName;
    }
}
