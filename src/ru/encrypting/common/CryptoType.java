package ru.encrypting.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CryptoType
{
    ENCRYPT("Зашифровать"),
    DECRYPT("Расшифровать");

    String cryptoTitle;

    @Override
    public String toString()
    {
        return cryptoTitle;
    }
}
