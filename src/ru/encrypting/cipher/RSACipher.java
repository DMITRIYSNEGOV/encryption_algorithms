package ru.encrypting.cipher;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import java.math.BigInteger;
import java.util.Random;

public class RSACipher
{
    @Getter
    @ToString
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class RSA
    {
        BigInteger p;
        BigInteger q;
        BigInteger n;
        BigInteger totN;
        BigInteger e;
        BigInteger d;

        String text;
        BigInteger cipherText;
        BigInteger encrypted;
        String encryptedBytes;
        BigInteger decrypted;
        String restoredText;

        public RSA(String text)
        {
            this.text = text;

            this.p = largePrime(512);
            this.q = largePrime(512);
            this.n = p.multiply(q);
            this.totN = getTotN(p, q);
            this.e = genE(totN);
            this.d = extEuclid(e, totN)[1];

            this.cipherText = new BigInteger(text.getBytes());
            this.encrypted = encrypt(cipherText, e, n);
            this.decrypted = decrypt(encrypted, d, n);
            this.restoredText = new String(decrypted.toByteArray());
            this.encryptedBytes = new String(encrypted.toByteArray());
        }

        public static BigInteger getTotN(BigInteger p, BigInteger q)
        {
            BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
            return phi;
        }

        public static BigInteger largePrime(int bits)
        {
            Random randomInteger = new Random();
            BigInteger largePrime = BigInteger.probablePrime(bits, randomInteger);
            return largePrime;
        }

        public static BigInteger gcd(BigInteger a, BigInteger b)
        {
            if (b.equals(BigInteger.ZERO))
            {
                return a;
            }
            else
            {
                return gcd(b, a.mod(b));
            }
        }

        public static BigInteger[] extEuclid(BigInteger a, BigInteger b)
        {
            if (b.equals(BigInteger.ZERO)) return new BigInteger[]{
                    a, BigInteger.ONE, BigInteger.ZERO
            }; // { a, 1, 0 }
            BigInteger[] vals = extEuclid(b, a.mod(b));
            BigInteger d = vals[0];
            BigInteger p = vals[2];
            BigInteger q = vals[1].subtract(a.divide(b).multiply(vals[2]));
            return new BigInteger[]{
                    d, p, q
            };
        }

        public static BigInteger genE(BigInteger phi)
        {
            Random rand = new Random();
            BigInteger e = new BigInteger(1024, rand);
            do
            {
                e = new BigInteger(1024, rand);
                while (e.min(phi).equals(phi))
                { // пока phi is меньше чем e, ищем нужное значение для e
                    e = new BigInteger(1024, rand);
                }
            }
            while (!gcd(e, phi).equals(BigInteger.ONE)); // если gcd(e,phi) не 1 тогда остаемся в цикле
            return e;
        }

        public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n)
        {
            return message.modPow(e, n);
        }

        public static BigInteger decrypt(BigInteger message, BigInteger d, BigInteger n)
        {
            return message.modPow(d, n);
        }

    }
}
