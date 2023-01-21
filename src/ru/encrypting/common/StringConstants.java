package ru.encrypting.common;

public class StringConstants
{
    public static final String DOUBLE_ARROW = "↔";
    public static final String RIGHT_ARROW = " → ";
    public static final String ALPHABET = "Алфавит";
    public static final String OPERATION_TYPE = "Тип операции";
    public static final String SHIFT = "Сдвиг";
    public static final String LOAD_IMAGE_BUTTON_TEXT = "Загрузить картинку";
    public static final String DESCRIPTION = "Описание";

    public static final String LEGEND_TITLE = "Приложение для изучения алгоритмов шифрования";
    public static final String LEGEND_DESCRIPTION = "Данная программа позволит познакомиться и изучить популярные виды шифрования на существующих и своих примерах";
    public static final String LEGEND_SIMPLE_TEXT = "TEXT";
    public static final String LEGEND_CIPHERED_TEXT = "UFYU";
    public static final String LEGEND_AUTHOR = "<html><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>Разработчик: Снегов Дмитрий<br>Год: 2022</html>";

    public static final String ATBASH_TITLE = "Шифр Атбаша";
    public static final String ATBASH_DESCRIPTION = "   Атба́ш — простой шифр подстановки для алфавитного письма. Правило шифрования состоит в замене i-й буквы алфавита буквой с номером  n-i+1, где n — число букв в алфавите.\n" +
            "Происхождение слова «атбаш» объясняется принципом замены букв. Слово (точнее - аббревиатура на древнееврейском языке) \"אתבש\" составлено из букв «алеф», «тав», «бет» и «шин», то есть первой, последней, второй и предпоследней букв еврейского алфавита.\n" +
            "Впервые встречается в древнееврейском тексте Библии / Танаха.\n" +
            "Ниже даны примеры для английского, русского алфавитов:";

    public static final String CAESAR_TITLE = "Шифр Цезаря";
    public static final String CAESAR_DESCRIPTION = "   Шифр Цeзаря (шифр сдвига, код Цезаря) – такой простой вид шифрования текста, при котором все символы в тексте заменяются символами, сдвинутыми по алфавиту на правее или левее на постоянное количество позиций. Например, при сдвиге на 1 буква А заменяется на Б, Б на В и т.д. Вы можете как зашифровать текст данным способом, так и выполнить дешифровку.";
    public static final String CAESAR_SHIFT_ERROR = "Сдвиг не должен превышать длину алфавита";

    public static final String VIGENERE_TITLE = "Шифр Виженера";
    public static final String VIGENERE_DESCRIPTION = "   Шифр Виженера - это последовательность ранее описанных нами шифров Цезаря, но только с разными значениями сдвига.\n" +
            "Шифр Виженера считается намного безопасней и сложней, так как он является шифром подстановки. Это означает, что в данном шифре каждая буква введенного текста заменяется буквой уже не простого, а шифр-текста. Для расшифровки такого вида шифра используют частотный криптоанализ.";

    public static final String RSA_TITLE = "RSA шифрование";
    public static final String RSA_DESCRIPTION = "   RSA — криптографический алгоритм с открытым ключом, основывающийся на вычислительной сложности задачи факторизации больших целых чисел.\n" +
            "   Криптосистема RSA стала первой системой, пригодной и для шифрования, и для цифровой подписи. Алгоритм используется в большом числе криптографических приложений, включая PGP, S/MIME, TLS/SSL, IPSEC/IKE и других.\n" +
            "   При шифровании RSA сообщения шифруются с помощью кода, называемого открытый ключ, которыми можно поделиться открыто. Из-за некоторых четких математических свойств алгоритма RSA, если сообщение было зашифровано открытым ключом, оно может быть расшифровано только другим ключом, известным как закрытый ключ. У каждого пользователя RSA есть пара ключей, состоящая из их открытого и закрытого ключей. Как следует из названия, закрытый ключ должен храниться в секрете.\n" +
            "   Схемы шифрования с открытым ключом отличаются от шифрование с симметричным ключом, где и в процессе шифрования, и в дешифровании используется один и тот же закрытый ключ. Эти различия делают шифрование с открытым ключом, такое как RSA, полезным для связи в ситуациях, когда не было возможности безопасно распространять ключи заранее.";
    public static final String RSA_TEXT_HINT = "Текст";
    public static final String RSA_P = "p";
    public static final String RSA_Q = "q";
    public static final String RSA_N = "n";
    public static final String RSA_N_DESCRIPTION = "Вычисляем \"n = p * q\"";

    public static final String RSA_TOTN = "tot(n)";
    public static final String RSA_TOTN_DESCRIPTION = "Вычисляем тотиентную функцию Эйлера. tot(n) = φ(n) = (p - 1) * (q - 1)";

    public static final String RSA_E = "e";
    public static final String RSA_E_DESCRIPTION = "Выберем любое число \"e\", где \"1 < e < φ(n)\" и \"e\" и \"e\" нечетное число, которое не имеет общих делителей с φ(n)";

    public static final String RSA_D = "d";
    public static final String RSA_D_DESCRIPTION = "Вычислим \"d\", которое обратно \"e\" по модулю φ";

    public static final String RSA_BYTE_ARRAY = "Текст в байтах";
    public static final String RSA_ENCRYPTED_BYTE_ARRAY = "Зашифрованный текст в байтах";
    public static final String RSA_ENCRYPTED_BYTE_ARRAY_TEXT = "Зашифрованный текст";
    public static final String RSA_DECRYPTED_BYTE_ARRAY_TEXT = "Расшифрованный текст в байтах";
    public static final String RSA_DECRYPTED_TEXT = "Расшифрованный текст";
    public static final String RSA_FINAL_CALCULATE = "Вычислить";
}
