public class Main2 {


        // method to convert binary to decimal
        int binaryToDecimal(long binary){

            // variable to store the converted
            // binary number
            int decimalNumber = 0, i = 0;

            // loop to extract the digits of the binary
            while (binary > 0) {

                // extracting the digits by getting
                // remainder on dividing by 10 and
                // multiplying by increasing integral
                // powers of 2
                decimalNumber
                        += Math.pow(2, i++) * (binary % 10);

                // updating the binary by eliminating
                // the last digit on division by 10
                binary /= 10;
            }

            // returning the decimal number
            return decimalNumber;
        }

        // method to convert decimal to hexadecimal
        String decimalToHex(long binary)
        {
            // variable to store the output of the
            // binaryToDecimal() method
            int decimalNumber = binaryToDecimal(binary);

            // converting the integer to the desired
            // hex string using toHexString() method
            String hexNumber
                    = Integer.toHexString(decimalNumber);

            // converting the string to uppercase
            // for uniformity
            hexNumber = hexNumber.toUpperCase();

            // returning the final hex string
            return hexNumber;
        }

        // Driver Code
        public static void main(String[] args)
        {

            // instantiating the class
            Main2 ob = new Main2();

            String num = "FF D8 FF E0 00 10 4A 46 49 46 00 01 01 00 00                 \n" +
                    " 01 00 01 00 00 FF DB 00 43 00 0B 08 08 0A 08                 \n" +
                    " 07 0B 0A 09 0A 0D 0C 0B 0D 11 1C 12 11 0F 0F                 \n" +
                    " 11 22 19 1A 14 1C 29 24 2B 2A 28 24 27 27 2D                 \n" +
                    " 32 40 37 2D 30 3D 30 27 27 38 4C 39 3D 43 45                 \n" +
                    " 48 49 48 2B 36 4F 55 4E 46 54 40 47 48 45 FF                 \n" +
                    " DB 00 43 01 0C 0D 0D 11 0F 11 21 12 12 21 45                 \n" +
                    " 2E 27 2E 45 45 45 45 45 45 45 45 45 45 45 45                 \n" +
                    " 45 45 45 45 45 45 45 45 45 45 45 45 45 45 45                 \n" +
                    " 45 45 45 45 45 45 45 45 45 45 45 45 45 45 45                 \n" +
                    " 45 45 45 45 45 45 45 45 FF C0 00 11 08 00 CD                 \n" +
                    " 00 CD 03 01 22 00 02 11 01 03 11 01 FF C4 00                 \n" +
                    " 1F 00 00 01 05 01 01 01 01 01 01 00 00 00 00                 \n" +
                    " 00 00 00 00 01 02 03 04 05 06 07 08 09 0A 0B                 \n" +
                    " FF C4 00 B5 10 00 02 01 03 03 02 04 03 05 05                 \n" +
                    " 04 04 00 00 01 7D 01 02 03 00 04 11 05 12 21                 \n" +
                    " 31 41 06 13 51 61 07 22 71 14 32 81 91 A1 08                 \n" +
                    " 23 42 B1 C1 15 52 D1 F0 24 33 62 72 82 09 0A                 \n" +
                    " 16 17 18 19 1A 25 26 27 28 29 2A 34 35 36 37                 \n" +
                    " 38 39 3A 43 44 45 46 47 48 49 4A 53 54 55 56                 \n" +
                    " 57 58 59 5A 63 64 65 66 67 68 69 6A 73 74 75                 \n" +
                    " 76 77 78 79 7A 83 84 85 86 87 88 89 8A 92 93                 \n" +
                    " 94 95 96 97 98 99 9A A2 A3 A4 A5 A6 A7 A8 A9                 \n" +
                    " AA B2 B3 B4 B5 B6 B7 B8 B9 BA C2 C3 C4 C5 C6                 \n" +
                    " C7 C8 C9 CA D2 D3 D4 D5 D6 D7 D8 D9 DA E1 E2                 \n" +
                    " E3 E4 E5 E6 E7 E8 E9 EA F1 F2 F3 F4 F5 F6 F7                 \n" +
                    " F8 F9 FA FF C4 00 1F 01 00 03 01 01 01 01 01                 \n" +
                    " 01 01 01 01 00 00 00 00 00 00 01 02 03 04 05                 \n" +
                    " 06 07 08 09 0A 0B FF C4 00 B5 11 00 02 01 02                 \n" +
                    " 04 04 03 04 07 05 04 04 00 01 02 77 00 01 02                 \n" +
                    " 03 11 04 05 21 31 06 12 41 51 07 61 71 13 22                 \n" +
                    " 32 81 08 14 42 91 A1 B1 C1 09 23 33 52 F0 15                 \n" +
                    " 62 72 D1 0A 16 24 34 E1 25 F1 17 18 19 1A 26                 \n" +
                    " 27 28 29 2A 35 36 37 38 39 3A 43 44 45 46 47                 \n" +
                    " 48 49 4A 53 54 55 56 57 58 59 5A 63 64 65 66                 \n" +
                    " 67 68 69 6A 73 74 75 76 77 78 79 7A 82 83 84                 \n" +
                    " 85 86 87 88 89 8A 92 93 94 95 96 97 98 99 9A                 \n" +
                    " A2 A3 A4 A5 A6 A7 A8 A9 AA B2 B3 B4 B5 B6 B7                 \n" +
                    " B8 B9 BA C2 C3 C4 C5 C6 C7 C8 C9 CA D2 D3 D4                 \n" +
                    " D5 D6 D7 D8 D9 DA E2 E3 E4 E5 E6 E7 E8 E9 EA                 \n" +
                    " F2 F3 F4 F5 F6 F7 F8 F9 FA FF DA 00 0C 03 01                 \n" +
                    " 00 02 11 03 11 00 3F 00 4C 52 E3 8A 43 4E AD                 \n" +
                    " 4C C4 C6 0D 07 AD 2D 26 28 01 40 A5 14 DC 52                 \n" +
                    " D0 31 4D 36 8C D3 49 C5 20 14 B0 51 92 70 00                 \n" +
                    " E4 D4 60 B4 A9 BD C1 54 C6 70 78 E3 D4 FA 55                 \n" +
                    " 0D 57 54 8A C9 44 64 17 9B 1B 84 78 20 7B 12                 \n" +
                    " 7D 3B E0 72 6B 99 B9 BE 9E F1 76 DC 4E F2 0E                 \n" +
                    " A5 4F 0B 9F A0 A5 71 D8 E9 AE 75 CB 2B 75 28                 \n" +
                    " 8E 66 2B FF 00 2C E2 07 1F F7 D6 31 F9 66 B2                 \n" +
                    " A6 F1 25 D3 B0 F2 11 2D F0 72 08 01 CF EA 31                 \n" +
                    " FA 56 30 23 BD 3C 08 D8 1C 87 07 1C 6D C1 C9                 \n" +
                    " FA 50 D8 EC 5B FB 6C CC AD FB E9 D7 71 2C C5                 \n" +
                    " 58 ED 39 EB F2 8E 3F 2A 61 48 64 5C 87 32 3B                 \n" +
                    " 1E 82 3E BF 9F 35 04 42 4C 12 A0 B0 EE 05 0C                 \n" +
                    " AA AF 9D FC 1E F8 C5 2B 88 72 AA E7 00 00 07                 \n" +
                    " 14 81 84 2F BA 33 B5 BD 57 8A 66 FD A7 20 8C                 \n" +
                    " FF 00 3A 6B B2 BF 2A 36 9F 40 68 19 7E 3D 4A                 \n" +
                    " E1 A6 89 A4 9B CC 08 78 32 22 B1 03 EB 8A D0                 \n" +
                    " 4F 11 CF E6 11 E4 AC 88 4F 05 88 56 03 F0 00                 \n" +
                    " 7E 95 CF 6E 20 73 4F 52 5B 1E 94 5C 2C 76 36                 \n" +
                    " 5A AC 77 63 24 04 3D 31 9C E2 AF A9 CD 70 71                 \n" +
                    " 4B B6 40 CB 23 23 03 C1 5A DB D3 B5 C1 1E D8                 \n" +
                    " AF 70 AA 4F 13 0C 9C 7D 40 FE 62 AA E2 3A 32                 \n" +
                    " 68 CF BD 46 1F 20 7B 8C 8C 1C 82 3D 69 DD 69                 \n" +
                    " 88 5C E6 82 4D 34 64 75 39 A5 34 08 70 62 28                 \n" +
                    " 2E 4F 52 78 F7 A6 8A 4A 06 3B 14 BD A9 0D 2E                 \n" +
                    " 28 10 51 46 68 34 00 01 9A 08 C5 26 69 0B 52                 \n" +
                    " 18 A7 92 00 EA 7A 62 B0 B5 8D 60 44 1A DA CE                 \n" +
                    " 50 67 0D 89 24 51 90 9E A0 7A 9F A7 4A 35 BD                 \n" +
                    " 59 22 8A 4B 4B 66 26 66 3B 64 75 3F 70 77 19                 \n" +
                    " F5 ED ED CF 7A E7 10 2A 8C 01 80 3A 01 4A E3                 \n" +
                    " B0 92 BB 48 C5 9D DD DC F5 67 62 49 FC 4D 33                 \n" +
                    " A7 26 95 A4 63 F2 EE 21 7D 29 33 F3 7A D2 18                 \n" +
                    " 03 80 71 41 E4 74 C1 A5 DA 50 E5 86 47 F3 A1                 \n" +
                    " 81 F4 21 4F DD CD 00 2A 92 A4 48 18 86 07 B7                 \n" +
                    " 06 97 E7 90 B3 82 0E 0E 5B 24 53 49 C3 00 38                 \n" +
                    " 07 AD 39 47 05 41 CE 3A 50 02 C8 41 00 F3 93                 \n" +
                    " ED 51 1E 9C D4 98 DD DB 07 D3 D6 9A E7 FC 9A                 \n" +
                    " 04 47 8C 9E 0F 14 EC E1 69 A0 63 9C D3 8B 7C                 \n" +
                    " A0 67 F4 A0 60 18 83 D2 9C AD F3 53 33 8E 94                 \n" +
                    " AB 8C FA D0 23 A3 D0 6F C6 16 CE 4F 73 11 F4                 \n" +
                    " EE 57 F9 91 5B EA 4E 2B 84 85 DA 29 12 45 F9                 \n" +
                    " 59 18 30 FC 0E 6B B4 B6 BA 4B AB 74 B8 84 7C                 \n" +
                    " 8F FC 27 F8 4F 71 F8 53 B8 13 83 CD 14 00 28                 \n" +
                    " CD 50 85 E8 29 33 45 1C 50 03 F3 4B 9A 4E B4                 \n" +
                    " 01 40 80 9A 3B 52 91 C5 25 03 13 15 97 AD DF                 \n" +
                    " 9B 2B 40 91 31 59 E6 C8 56 1D 54 77 6F E8 3F                 \n" +
                    " FA D5 A5 24 8B 1A B3 48 C1 51 41 66 63 D8 0E                 \n" +
                    " A6 B8 8D 42 F1 AF EE 9A 76 01 73 F2 A0 EB 85                 \n" +
                    " 1D 05 4B 1A 2B 17 0B D7 2C 7A E4 D3 4B E4 E4                 \n" +
                    " 1C 0F 4A 33 9E D9 A6 F7 C5 21 81 E4 D3 D5 7B                 \n" +
                    " 75 3E B4 81 33 CE 71 56 22 8F E7 01 B9 1D 8D                 \n" +
                    " 26 C1 22 22 19 94 67 38 EA 07 AD 29 50 53 18                 \n" +
                    " 0B C6 41 C7 7F 4A D2 87 4F F3 08 64 2A F1 B7                 \n" +
                    " 27 9C 15 FA 1E 86 9A D6 EC 81 7C BD CC A3 3D                 \n" +
                    " 57 24 7A 8C 8E B4 AF A0 EC FA 19 E1 36 ED 3F                 \n" +
                    " 78 11 90 41 A7 79 64 96 E0 82 3B 55 D9 A0 74                 \n" +
                    " D9 FB 97 56 3D 1B 04 6E 1D 2A 11 6F 23 12 14                 \n" +
                    " 9C 7B 8A 14 90 F9 59 14 51 BC AD B1 08 39 F5                 \n" +
                    " A8 E4 88 86 F9 86 6A FC 7A 7C B9 0C 62 C8 FA                 \n" +
                    " 56 B4 1A 0B CE AB 20 8D 4F B3 31 C7 F8 D4 CA                 \n" +
                    " A2 45 2A 6D 9C D1 B5 7F 24 4A 46 10 B6 D1 EE                 \n" +
                    " 7F CE 29 AD 13 2E 31 8C 1E E0 E6 BD 0E D7 43                 \n" +
                    " 48 6D 82 3A 64 91 8C E3 18 E7 3C 7E 35 85 AB                 \n" +
                    " 68 86 D6 E0 6E 21 A1 9B 76 D7 0B CA B0 19 00                 \n" +
                    " 8E F5 10 AC A4 EC 5C E8 B8 AB 9C B8 18 3C F2                 \n" +
                    " 68 03 27 D2 B4 24 89 55 55 19 39 8C F5 1C 6E                 \n" +
                    " 07 9C 7D 46 0D 41 E5 7E FA 43 10 CA 06 3B 40                 \n" +
                    " F4 EB DF DA B7 30 1A A7 6E 46 DF D6 B4 34 5B                 \n" +
                    " E3 6B 76 B1 33 7E E6 63 86 C9 E0 1E C7 FA 7F                 \n" +
                    " FA AB 3D 80 7D C4 71 CD 22 95 39 57 1C 11 C1                 \n" +
                    " 06 84 26 77 79 EC 7F 1A 5A A3 A5 DD 35 DD 92                 \n" +
                    " 34 87 F7 A9 FB B7 F7 C0 18 3F 88 C5 5E CD 58                 \n" +
                    " 82 93 9A 5A 42 3D E8 01 E7 AD 38 1A 69 F6 A2                 \n" +
                    " 80 1D EB 4D 34 B4 60 62 80 28 6A CA 5B 4A BB                 \n" +
                    " 55 1B 89 8C 9C 0E E0 72 7F 95 71 44 60 FB D7                 \n" +
                    " A1 10 19 97 7F DD CF 35 C0 CD 24 8D 2C 80 92                 \n" +
                    " 70 C4 7C C7 38 E7 A5 4B 19 09 39 ED 42 E4 1E                 \n" +
                    " 33 4A 08 62 30 30 6A EC 5A 74 D2 C7 BE 3D AC                 \n" +
                    " 47 25 33 83 8A 9B D8 A4 AE 57 05 A5 90 16 24                 \n" +
                    " B1 FC EB A6 B1 D2 DE 7B 45 92 24 DC 8C 7A B3                 \n" +
                    " E1 BA 73 C5 41 A7 F8 7A F6 59 03 34 4B 10 3C                 \n" +
                    " 06 76 07 6F BE 01 C9 3E D5 DA 59 69 E9 04 2A                 \n" +
                    " 88 E4 15 18 0D 8C D7 3D 4A BD 8E 9A 74 B4 BB                 \n" +
                    " 32 97 C3 97 12 C6 1B ED 10 40 BD 59 64 40 FE                 \n" +
                    " 67 B0 EF F8 F1 5A 1A 4F 81 2D 8C 11 DC 5F 5C                 \n" +
                    " 03 9F 98 41 0C 78 5F 6C 92 49 3F 86 2B 5A 0B                 \n" +
                    " 4D C4 F9 86 30 A7 86 58 E2 0A 5F EA 73 9C 7D                 \n" +
                    " 2B 5D 18 91 49 36 D6 A3 71 49 E8 60 BF 86 E2                 \n" +
                    " 53 95 96 6D BD E3 57 E0 FE 79 A8 D3 40 B1 89                 \n" +
                    " 99 56 36 4C F3 96 45 2C 3F 12 39 AE 88 83 DA                 \n" +
                    " 9B 82 2A 79 4A E6 31 A1 D1 EC 61 70 E9 6C D3                 \n" +
                    " C8 3A 49 36 32 0F A8 00 00 3F 01 56 85 AE E3                 \n" +
                    " 92 3A 55 E2 3D 69 37 0C E2 8E 5E E1 72 AF D9                 \n" +
                    " 97 1D 71 59 1A DD 90 96 C2 6D 9C 48 83 72 F1                 \n" +
                    " DC 73 5D 03 B0 C5 67 DC 00 43 73 D4 62 93 56                 \n" +
                    " D8 A8 B3 CA B5 06 DF 21 95 46 0E 78 20 F5 C7                 \n" +
                    " 5A A8 B3 14 5E 08 07 3B BE 95 D1 5F E9 07 17                 \n" +
                    " 88 AA 73 19 FD D7 B9 E5 8F F3 C5 73 53 83 E6                 \n" +
                    " 63 1C 00 05 74 42 7C C7 2C E1 CA C6 30 E7 20                 \n" +
                    " 8C 67 A8 A4 CF 18 C6 69 A0 63 B8 20 D3 94 82                 \n" +
                    " 70 7A D6 A8 C8 DD F0 E4 98 6B 84 03 39 0A D8                 \n" +
                    " F6 1C 7F 51 5D 00 E6 B9 7D 09 B6 EA 68 39 C3                 \n" +
                    " 23 03 81 ED 9F E9 5D 40 E6 A9 12 C7 76 A4 C5                 \n" +
                    " 2E 38 A3 14 C0 53 4A 38 EB 45 14 00 B4 87 A5                 \n" +
                    " 1C D1 48 06 9C 81 5C 46 A9 6E F6 DA 84 E8 E0                 \n" +
                    " 0C B9 75 C1 EA 1B 91 FC EB B8 39 ED 5C D7 89                 \n" +
                    " 2D 0A CD 1D C8 52 51 86 D7 6E C0 F6 FD 29 31                 \n" +
                    " 98 4B F7 81 00 71 5A DA 73 E6 4E 36 A9 90 E3                 \n" +
                    " 27 19 AC 81 C1 AE 8B 41 88 4B 7D 6F BB E6 54                 \n" +
                    " 52 D8 3E BF E4 56 35 1D 91 B5 25 79 1D 85 8C                 \n" +
                    " 41 22 40 46 71 EB 5A D1 2E 46 40 C5 52 89 70                 \n" +
                    " A3 8A BA 67 48 63 05 CE 33 C0 1D CD 71 A4 76                 \n" +
                    " B2 DC 7C 91 9A B4 9C 71 9A C4 3A C5 9C 04 1B                 \n" +
                    " AB 98 6D D3 9C 97 71 9E 3D 87 35 7E C3 53 B1                 \n" +
                    " BD 4C C1 77 03 10 32 46 F1 9A DD 5E C6 4D EA                 \n" +
                    " 5D 66 C5 26 FE 2A 77 84 AF 25 48 1E A4 62 A3                 \n" +
                    " 31 8C 53 62 22 2D 4C 66 A9 76 8C 60 9A 6B 22                 \n" +
                    " F4 F5 E0 52 02 06 90 62 A9 CC 43 55 D7 B5 7C                 \n" +
                    " 12 11 88 FA 56 6C D2 01 8F 46 E9 EF 59 C8 B4                 \n" +
                    " 51 BC 8F 72 1C 75 AE 07 5A B4 16 F7 A7 6A E0                 \n" +
                    " 48 37 E3 D0 E7 91 FD 7F 1A F4 26 70 72 47 23                 \n" +
                    " D4 56 16 A9 61 1D D4 C8 EE 33 B0 E4 82 78 61                 \n" +
                    " E9 4A 9C B9 5E A3 A9 0E 78 9C 23 0E 71 C8 A7                 \n" +
                    " C6 B9 C7 A7 73 56 2F A1 16 B7 B2 C4 83 01 5B                 \n" +
                    " 8C 8F C7 FA D5 5E A7 93 9A EE 4E E7 03 56 35                 \n" +
                    " B4 05 1F DA 67 9D D8 89 8E 7A 63 91 FE 35 D4                 \n" +
                    " 8E 95 CE F8 6D 0F 9B 72 F9 38 08 13 AF A9 CF                 \n" +
                    " 4F F8 0D 74 6B D0 55 A2 18 A2 90 D2 E7 14 87                 \n" +
                    " 9A 60 3A 81 4D A7 28 E2 80 03 46 28 23 9A 08                 \n" +
                    " A0 09 20 87 CE 72 A5 B6 2A A9 66 6F 40 2A 85                 \n" +
                    " ED BD 8E A1 63 79 3D A3 DC C9 1D B0 62 EC F1                 \n" +
                    " 91 1B 28 52 49 53 9E A0 E3 B5 69 5A C6 25 33                 \n" +
                    " C0 C3 22 58 59 47 BD 5A F0 E4 D8 D2 6C 03 20                 \n" +
                    " 75 11 B6 F8 D8 02 08 DC C3 07 EA 2B 8E AC 9F                 \n" +
                    " 3D 8E EA 50 5E CE E7 9A 5D DA FD 96 E1 A3 DC                 \n" +
                    " 1F 1D 18 74 35 D0 F8 4E D5 8B BD C3 1F 94 7C                 \n" +
                    " A0 0F 5F FF 00 55 52 D7 6C A0 B4 BD 75 B3 88                 \n" +
                    " C5 0B B1 64 85 9C 3F 97 EC 0F 5C 7D 6B A6 F0                 \n" +
                    " FD AB 41 A7 A2 B8 DA 49 CE D3 CD 15 25 EE 93                 \n" +
                    " 4E 36 99 76 F2 FB EC 50 E5 54 34 AC 76 A0 73                 \n" +
                    " 85 07 D4 FF 00 85 56 1A 6E A9 A8 C8 A6 59 92                 \n" +
                    " 18 CA FD E6 2C 33 ED 80 3F 91 AD 34 B3 FF 00                 \n" +
                    " 48 33 36 4E 06 17 9F BB F4 AA 5A 9D F5 D9 9A                 \n" +
                    " 3B 6B 54 DC 64 3B 48 32 79 65 89 EE 5F 1C 28                 \n" +
                    " EA 7B 9A CA 3A 1B 4B 52 1B 8F 0E DB 20 06 4D                 \n" +
                    " 44 C5 30 23 71 25 5B 38 ED CF 20 7B F5 AC 3B                 \n" +
                    " 8D 2D E1 25 54 DB DD 2F 42 D0 B0 C9 19 F4 38                 \n" +
                    " 3F 97 A5 69 F8 A2 D7 54 F0 F0 B5 85 F5 55 41                 \n" +
                    " 75 13 B3 25 AD B2 C6 85 94 AF CA 18 65 9B AE                 \n" +
                    " 49 63 E9 50 78 7E D3 54 D7 C5 FB C5 7C 2E 5E                 \n" +
                    " D5 23 61 15 CA E5 65 2D 9C A6 EE AA 78 E0 F4                 \n" +
                    " FE 75 BF BC 91 CF 78 B6 4D A6 8B DB 19 F1 2F                 \n" +
                    " 9E 91 B9 19 C1 27 1E FE DF 85 75 1A 6E A5 2C                 \n" +
                    " F1 C6 B2 12 5D B2 33 8A CD D3 91 CD B8 6D AE                 \n" +
                    " 83 19 29 27 0C A7 B8 39 F4 AD 8B 28 D7 70 60                 \n" +
                    " 00 3E B5 97 33 6C D7 96 C2 DF DC 3C 4B F2 9C                 \n" +
                    " 13 D0 FB D7 33 A9 EB 2E A4 07 6D AA 80 F0 A7                 \n" +
                    " A1 3E F5 D3 5F E0 A7 CC 06 05 72 D7 84 00 CC                 \n" +
                    " 3C A8 D4 72 D2 CA 32 A8 3B 93 EB F4 A3 9A CC                 \n" +
                    " 69 5D 1C D5 D6 A1 2D C9 21 AE A7 94 1E C5 D8                 \n" +
                    " E0 7E 07 9A B5 67 A7 6A 28 33 65 69 24 51 B7                 \n" +
                    " 2D BC AA 6E ED 90 3A E6 AE 6A 32 5E 69 49 6F                 \n" +
                    " 2A 69 F1 29 BA 47 92 39 6F A4 2D 2B A2 85 CB                 \n" +
                    " 79 7B 82 A0 F9 B8 18 2D F8 D3 34 7B CD 62 FD                 \n" +
                    " AE 56 DD 22 99 E1 89 65 68 E2 97 63 EC CE 32                 \n" +
                    " A3 94 27 9E 41 E7 F1 CD 69 2B DA F6 31 56 BE                 \n" +
                    " E2 FD AE F3 4E 23 ED 50 5C C0 C4 FC C6 E2 2E                 \n" +
                    " 0E 7D D7 8F CE AF 8B 98 AF 23 12 C4 C0 F6 20                 \n" +
                    " 1E 95 2D 8E A8 2F A2 30 C8 19 94 13 FE B2 3C                 \n" +
                    " 10 7B 86 5E C6 A0 92 C8 43 78 67 88 90 24 18                 \n" +
                    " 75 00 63 DA B0 95 99 D3 0B A3 93 D7 00 FE D3                 \n" +
                    " 94 8E E1 4F E8 07 F4 AC F0 33 D6 B5 B5 E5 6F                 \n" +
                    " B7 64 F4 D8 31 EF D6 A8 DB 44 5D 83 14 DC 83                 \n" +
                    " A9 65 DC A0 FA 1A EA 8B B4 51 C7 38 DE 6C D9                 \n" +
                    " F0 D9 90 45 70 9E 5E 23 2C 1B 71 3D F1 8A DD                 \n" +
                    " 1C 74 AC 9D 2A 47 12 BC 12 22 AB 21 C8 DA BB                 \n" +
                    " 41 1E 98 AD 6C F6 AD 61 2E 64 67 38 72 B1 7A                 \n" +
                    " D0 78 A5 02 90 8A B2 04 EF 4E C9 14 CA 70 39                 \n" +
                    " A0 07 73 DE 8C D2 66 96 80 26 B5 90 43 77 0C                 \n" +
                    " 8C 78 57 19 CF A6 79 AB 5A 4C 22 D2 D2 E9 5B                 \n" +
                    " 81 6F 23 46 3F EF B6 35 43 19 15 A1 14 86 5F                 \n" +
                    " B5 20 DB FE 99 18 75 DD D0 4A 9D 7F 3A E3 AE                 \n" +
                    " B5 4C EE C3 3B C5 A3 8F F1 16 C5 D5 54 ED 0C                 \n" +
                    " CE A2 42 0F 7E 48 FE 95 DB 43 08 46 D8 06 02                 \n" +
                    " 9C 57 23 E2 18 DE 7B 1B 7B BD 92 89 6D FF 00                 \n" +
                    " 75 2C 6C 57 08 09 24 11 F5 3D EB B1 B5 6F 31                 \n" +
                    " 43 81 80 E0 30 CF B8 CD 67 2D 52 2F 69 32 E0                 \n" +
                    " 18 50 07 53 4D 9F 4F 13 C2 54 83 83 D7 15 3C                 \n" +
                    " 31 0C E4 7D 79 AB 8A 31 4D 44 4D D8 E7 AF AC                 \n" +
                    " 0C F6 29 65 2C 1E 75 A2 B0 61 1C 8D BB 66 38                 \n" +
                    " CA 93 CA 9E BD 3D 6A 2B 38 6F AC ED 05 A5 BC                 \n" +
                    " B2 59 40 09 60 96 AC A0 96 CE 49 2D 82 4F E2                 \n" +
                    " 7F 3A E9 5C 2B 0E 82 A2 D8 BE 9C D5 6A 2F 77                 \n" +
                    " B1 9A 55 88 76 91 9D A4 73 92 58 E6 A7 B7 0C                 \n" +
                    " AA 31 D6 A5 74 04 D3 E2 51 9A CF A9 65 7B CC                 \n" +
                    " 98 C9 C5 61 DC 65 44 65 57 95 7C 96 0A 0E 3B                 \n" +
                    " 83 83 D7 9A DE BC 21 62 23 B9 AC A0 82 42 40                 \n" +
                    " EB E9 4A FA 94 B6 2A EB 62 5D 7E C6 28 AF A2                 \n" +
                    " 5B 89 21 25 A2 9E 35 F2 9D 73 D4 11 C8 20 E0                 \n" +
                    " 7A 52 E8 B6 E9 A3 D9 C9 0D 84 6C B2 5C 10 66                 \n" +
                    " 9E 53 97 6C 67 00 0E 8A 06 6B 62 DE 05 DA 01                 \n" +
                    " AB 42 DD 71 8A D7 9A 4C C7 92 2B 53 0D 2C D8                 \n" +
                    " CE D2 B1 2C CD D7 3C E6 91 E2 D9 E6 23 64 81                 \n" +
                    " CA 13 E9 5B CF 12 81 C0 E2 B3 2E 93 00 F1 C5                 \n" +
                    " 64 D1 AC 59 C2 F8 86 2D B3 44 E1 4F 20 AF F9                 \n" +
                    " FC EA DE 8D 66 6D B4 C9 A4 E3 CC 98 1E 83 9D                 \n" +
                    " 98 1C 7E 75 5B C4 B2 0D D1 27 52 09 6C 0A D8                 \n" +
                    " B0 26 28 27 DC 09 58 21 E4 03 9E 7A 01 F9 D5                 \n" +
                    " 49 BE 44 89 84 57 3B 64 51 C4 0E A5 7B 21 24                 \n" +
                    " 85 65 89 5B D4 A8 F9 BF 5A B2 06 0D 32 18 FC                 \n" +
                    " A8 82 75 39 2C C7 D5 8F 26 9F D2 BA E9 2B 44                 \n" +
                    " E5 AF 24 E7 A0 66 90 9A 05 15 A9 80 52 8A 4E                 \n" +
                    " 94 A0 E6 80 1D 49 4A 28 E9 40 0A 2A CD B3 6E                 \n" +
                    " 53 19 EA 18 3A 1F 43 55 AA 48 9F 64 A8 C7 90                 \n" +
                    " 08 C8 F5 15 95 58 F3 44 DA 8C F9 64 3F C5 36                 \n" +
                    " 16 EB A1 4D 75 12 32 CA A5 3C C1 BB E5 2B 9C                 \n" +
                    " 13 CF A6 6A 5D 02 53 2E 95 66 C7 8F DD 6D FF                 \n" +
                    " 00 BE 49 5F E9 5A 91 88 AF 7C A8 1D 92 58 AE                 \n" +
                    " E3 91 64 8F 3F C0 00 07 3E 9F 7B F4 AC ED 32                 \n" +
                    " C9 B4 D8 12 C5 9B 71 B7 DC 9B B1 D7 E6 27 3F                 \n" +
                    " AD 71 BB 58 EB FB 4D 9B F1 10 00 AB 19 C8 00                 \n" +
                    " F1 9A A5 11 F9 70 6A C2 31 07 9A B4 C4 C9 B1                 \n" +
                    " 8E 28 61 F2 F4 A3 69 38 C1 C0 A7 48 C1 50 D5                 \n" +
                    " 08 A5 21 E7 19 E6 9A 99 A8 A3 57 79 0B 36 71                 \n" +
                    " 56 61 42 5B 00 64 56 4B 72 F6 29 DF 1F 90 00                 \n" +
                    " 79 AA 08 BB 9F 18 AB DA 84 6C 84 67 EA 2A 8D                 \n" +
                    " AB 95 BA C3 74 61 52 D6 A5 FD 93 62 25 1B 06                 \n" +
                    " 2A 75 15 0C 5C 81 8A 55 9B E6 E7 A5 6C 8C 47                 \n" +
                    " C8 70 2B 26 F9 C0 43 CD 5D B9 98 F6 E2 B0 EF                 \n" +
                    " A7 CA 37 35 12 65 C5 1C AC CB F6 9D 6E 52 54                 \n" +
                    " 38 8B 6E D0 DD 33 C1 E7 F1 35 B3 0C 62 2B 78                 \n" +
                    " E1 05 98 93 E6 5C 3B 1F BC 7F 85 40 EC 33 51                 \n" +
                    " F8 76 25 FB 2D C5 E9 44 77 B8 90 94 66 E4 AA                 \n" +
                    " 82 47 1F 53 9A B0 AD F2 91 DC B1 24 FA D1 6E                 \n" +
                    " 69 28 82 97 24 1C 83 18 14 98 F5 A5 34 84 D7                 \n" +
                    " A0 8F 39 86 29 69 05 1C 9A 04 25 38 70 29 31                 \n" +
                    " 8A 51 40 05 2D 20 A7 62 80 01 8A 52 33 CD 18                 \n" +
                    " C0 A5 19 E2 81 91 CB 6E B3 01 97 96 32 3F 8A                 \n" +
                    " 19 0A 37 B8 C8 E6 B5 2C 4F 3C E4 9F 52 72 7F                 \n" +
                    " 3A A1 C8 C5 5C B1 39 63 5C B5 A2 92 BA 3A 28                 \n" +
                    " C9 BD 19 B1 19 CD 59 43 9E 3D 2A 9C 67 23 1E                 \n" +
                    " 95 34 6C 09 27 38 02 B1 4E C6 E5 E6 6D 80 60                 \n" +
                    " 54 52 B0 DA 7B E6 AB 99 89 06 42 40 55 EA 49                 \n" +
                    " C0 1F 8D 62 EA 5E 28 B2 B5 49 16 37 6B 99 D4                 \n" +
                    " 7D D8 94 ED 07 D3 76 30 7F 0C D5 88 BD 34 B7                 \n" +
                    " 10 CA 8D 1D CE C8 87 DF 88 A2 90 FF 00 89 19                 \n" +
                    " 07 E9 56 6D 75 14 31 B1 07 8E 84 8A E0 F5 2D                 \n" +
                    " 6A F6 FA EC C1 BB C8 45 38 28 AB 82 0E 39 C9                 \n" +
                    " EB 53 E9 F1 5C 46 D3 BF 96 03 3C 78 53 1A 06                 \n" +
                    " 3D 7B 91 D2 8E 5B 0E E9 E8 74 DA AE AB 13 FE                 \n" +
                    " EA DC AD CC CA 33 B1 5B 00 7D 5B A0 A8 2C DA                 \n" +
                    " 57 21 EE 92 28 DC 0F B9 13 16 03 F1 35 C5 B5                 \n" +
                    " CC 91 38 97 0A AC 1B 71 D9 C7 27 DA B7 74 CD                 \n" +
                    " 79 0B 05 BC 28 A7 03 E6 00 E0 9A 89 45 A7 71                 \n" +
                    " A9 26 AC 75 96 E7 9E B4 F9 18 02 73 F8 55 28                 \n" +
                    " 6E 15 90 3A 90 41 E8 45 4F 2C AA C8 0E 68 B8                 \n" +
                    " AC 55 BB 94 F0 07 E7 58 97 CC AC A5 5B 3B 4F                 \n" +
                    " 0D B7 39 C7 7E 95 A1 3C 85 DC 9E C2 A8 4A 7F                 \n" +
                    " 7D 1E 3A 83 9A 95 EF 4A C5 B7 CB 1B 89 0D ED                 \n" +
                    " BB B1 5B 08 1D 6D 56 01 1A 39 52 81 18 30 E8                 \n" +
                    " 0F 24 E3 20 E7 D7 39 34 71 8E 29 59 99 8E 58                 \n" +
                    " E4 FA 9A 69 AE CA 74 D4 0E 2A 95 1C 84 3E F4                 \n" +
                    " 94 B9 C8 A0 F0 2B 63 10 CF 1C 53 73 4A 29 0D                 \n" +
                    " 00 2D 2D 14 A2 80 16 94 53 69 7B 50 02 D2 E7                 \n" +
                    " 8C 53 41 E6 83 40 C7 71 56 AC DC 09 B1 9E B5                 \n" +
                    " 53 3C 53 91 B6 B0 23 A8 AC EA 2B C4 BA 6E D2                 \n" +
                    " 37 83 10 78 A2 37 F9 48 F7 AA D1 CD BD 32 2A                 \n" +
                    " 48 A4 DC D8 15 C2 76 D8 CE F1 0D 95 C5 EA 2C                 \n" +
                    " 70 46 65 8C A9 0E 9B 80 24 E4 11 8C 90 3F C6                 \n" +
                    " A8 DA 58 E9 96 BB 5B EC D7 5E 68 1D 25 97 E4                 \n" +
                    " 07 E8 06 2B A2 72 73 D7 34 9B B3 82 C0 67 AE                 \n" +
                    " 4F 73 54 9B D8 12 4F 73 05 6C 34 F3 78 27 2D                 \n" +
                    " 0B 06 18 68 5E 52 55 8F E7 C7 5A B2 DA 3E 92                 \n" +
                    " EC 92 8B 29 21 2A 41 D9 6D 70 C1 5B 1F 5F 5A                 \n" +
                    " D9 36 31 15 E0 A3 AF BA 80 73 EF 51 BE 93 68                 \n" +
                    " 14 92 8A 44 87 2C A1 98 60 8E 9D 0D 5D 99 69                 \n" +
                    " 44 E7 EE B4 BB 19 D0 A4 36 F2 47 83 D5 A7 2E                 \n" +
                    " 47 B0 24 56 5B F8 7D 37 0D D7 91 C6 C7 39 0F                 \n" +
                    " 11 00 8E DC 83 D7 DE BA AB BD 16 D4 20 66 86                 \n" +
                    " 24 5E 80 9E 4F EB 59 FF 00 61 B2 2E A0 C0 30                 \n" +
                    " 0F DC 04 80 7E A3 35 37 68 1C 22 CC 5B 03 A9                 \n" +
                    " D9 C9 8B 50 26 41 85 68 A2 75 C3 28 3C 0C 9E                 \n" +
                    " 01 15 D4 A4 AD E5 7C EB B5 BB 8C E7 14 B1 AC                 \n" +
                    " 70 A8 58 22 48 94 76 45 00 0A AD 3B ED 38 1D                 \n" +
                    " 49 CD 44 9D C5 15 61 24 7E C0 71 EB 55 01 DD                 \n" +
                    " 33 B0 39 0A 30 29 F3 CD B1 0E 2A 34 4D A8 17                 \n" +
                    " BF 53 5A 51 8D E5 73 2A F2 B4 6C 2F 6A 4E 29                 \n" +
                    " DC 62 9B DF 35 DC 70 88 7A D1 9E 69 4F 34 98                 \n" +
                    " A0 04 CF AD 21 E6 9D 8A 4C 50 02 F7 A5 14 DC                 \n" +
                    " F3 4F 18 A0 05 E9 49 D4 D1 8A 28 01 78 A3 3C                 \n" +
                    " 52 66 8C F1 40 C0 92 4E 00 C9 35 CF 6A FA B3                 \n" +
                    " CA 5A D2 C9 D8 29 E1 E4 43 CB 7B 0F 6F E7 F4                 \n" +
                    " EB 63 5F D4 1A DE DC 5B C4 E5 64 98 1D E4 75                 \n" +
                    " 09 FF 00 D7 FE 87 D6 B0 F4 B8 56 6D 4A D2 22                 \n" +
                    " 32 AD 2A 8F 4E 33 9F E4 2B 39 BD 0D 22 B5 48                 \n" +
                    " F4 62 85 77 34 63 3B 4E 19 7D 69 D0 C8 19 C3                 \n" +
                    " 29 E3 B1 A7 5B 12 1B 3E BC E6 8B A8 04 6D F6                 \n" +
                    " 88 46 07 F1 A8 E9 F5 AE 0B 1D CF 42 D9 04 F3                 \n" +
                    " 48 D0 79 8B 8C E0 FA D3 21 94 32 75 AB 22 40                 \n" +
                    " 1F 6F 15 69 5C 8B D8 A6 6D EF E3 6F 92 58 99                 \n" +
                    " 7F DA 53 FC C1 A6 6E D5 72 43 45 6C CA 7A 95                 \n" +
                    " 90 83 F9 62 B5 CA 8D BC 9A 8D 92 36 0C BC FD                 \n" +
                    " 45 56 A5 73 18 F3 5B DF CC F9 71 0A 2E 3A 86                 \n" +
                    " 24 FE 54 25 B8 8D 49 62 59 88 E3 1E B5 A6 C0                 \n" +
                    " 44 99 C9 3B 47 04 F2 6A 19 1C 32 03 EB 52 C3                 \n" +
                    " 98 A8 C3 0B CF E9 59 F3 48 37 B1 CF 03 D6 AC                 \n" +
                    " 5E 5C 84 5C 64 56 5C 84 B2 92 DD 3B 0F 5A 82                 \n" +
                    " 91 2C 6D E7 13 21 3F 28 38 03 FA D4 99 CD 57                 \n" +
                    " B4 95 24 B5 46 8D D5 86 48 38 39 C1 CF 20 D4                 \n" +
                    " C2 BB E9 C7 96 27 9F 52 4E 52 1F DA 9A 79 A0                 \n" +
                    " 9A 01 AD 0C C3 A0 A0 51 49 40 07 7A 5A 4C 52                 \n" +
                    " D0 02 77 A5 03 DE 93 AD 38 0A 00 05 29 E9 C5                 \n" +
                    " 00 50 68 01 BD E9 0F 3C 52 B3 04 19 3C 54 12                 \n" +
                    " 5C F9 76 F3 4F B5 73 1C 4E E0 7A ED 52 7F A5                 \n" +
                    " 4B 76 37 A7 49 CB 5E 87 27 AB DC FD AF 52 9E                 \n" +
                    " 45 3F 28 6D 8B 9E BB 47 03 FC 7F 1A B7 E1 9B                 \n" +
                    " 73 36 AE 92 60 ED 85 59 89 1E B8 C0 FE 7F A5                 \n" +
                    " 63 90 72 37 75 AE C3 C2 56 F8 B5 79 70 01 91                 \n" +
                    " F1 9F 61 FF 00 EB 35 8D 47 68 95 4D 73 4E E7                 \n" +
                    " 55 6E 9F 28 CD 58 20 11 83 49 0A E1 45 4A 54                 \n" +
                    " 76 AE 64 74 33 34 C6 F6 F2 1D 9C A9 E4 0A 95                 \n" +
                    " 6E 14 8C FF 00 10 EC 7A D4 CE B9 3C F5 14 9E                 \n" +
                    " 44 52 E3 CC 40 4F A8 38 34 5C 4D 0B F6 9D DC                 \n" +
                    " 13 4A 27 D8 A7 9C E6 AB C9 A5 13 CC 57 97 2A                 \n" +
                    " BF DC 3B 58 7F 20 6A 13 A7 5D 82 71 70 31 EF                 \n" +
                    " 1F FF 00 5E AA E2 B1 3C F7 59 18 15 4E E2 F9                 \n" +
                    " 63 8C 28 3F 37 A5 32 6B 39 13 99 2E 18 9F 45                 \n" +
                    " 40 BF E3 54 E4 45 5E 00 3E E4 F2 6A 1B 2D 21                 \n" +
                    " 98 69 5B 7B F4 A8 E7 39 FA D5 95 4F 93 8A 82                 \n" +
                    " 58 F2 C0 67 AD 25 A9 56 B2 39 7B 3D 41 EC 6F                 \n" +
                    " 9E 40 33 1B B1 12 26 7E F0 CF 5F A8 ED 5D 62                 \n" +
                    " 30 65 0C A7 2A C0 10 47 70 6B 85 72 77 1E 3F                 \n" +
                    " 2A E8 BC 3F 75 E6 5A B5 B9 1C C4 72 BF EE 9F                 \n" +
                    " FE BE 6B D0 4E C7 9E D5 CD A3 D2 81 48 06 7A                 \n" +
                    " F1 4A 3F 2A B3 30 3C 52 1A 52 69 33 4C 02 90                 \n" +
                    " E4 D3 A9 29 00 E1 4B C2 F2 78 F7 A8 F7 16 9E                 \n" +
                    " 28 57 00 CA DB 77 1E 71 F8 56 9C 76 B1 C3 71                 \n" +
                    " 74 BC B7 D9 19 15 9F F8 9C B1 1F 77 39 0A 39                 \n" +
                    " F4 27 DC 50 08 A9 1C 33 4E BB A0 82 59 57 38                 \n" +
                    " CA 29 23 3F 5E 95 62 6D 32 E2 D2 DD AE 2F 9A                 \n" +
                    " 3B 68 94 67 1B 84 8E DE C0 29 C7 7F 5A D3 D5                 \n" +
                    " 5E DF 47 D2 A6 BF FB 2A CE 62 52 58 48 E5 9A                 \n" +
                    " 4F 41 B9 B7 10 33 CE 05 63 EB 97 B3 4C F6 D0                 \n" +
                    " B3 7E ED 63 12 95 C7 DE 93 73 29 3F 4C 0E 07                 \n" +
                    " 6C 9E A7 9A 87 23 6A 54 F9 A4 93 29 65 E4 50                 \n" +
                    " ED 90 1B 85 04 7A 75 AA 9A 99 54 D2 AE C9 38                 \n" +
                    " CC 45 38 FF 00 68 85 FE B5 78 0F DC 03 E8 2B                 \n" +
                    " 23 5F 72 BA 5A A8 E9 24 EA 0F D0 06 3F E1 59                 \n" +
                    " EE CF 4A A2 51 A6 D2 39 B6 C6 06 2B B6 F0 91                 \n" +
                    " 0D A5 8C 1C 95 91 81 F6 E8 7F AD 71 4C 78 15                 \n" +
                    " D7 78 2C 91 1D CA 76 DC 1B FA 54 D5 D8 E1 A3                 \n" +
                    " BB 3B 38 C7 14 F2 0D 47 1F 6A 90 9E 2B 03 72                 \n" +
                    " 29 17 24 11 48 A3 1C 54 86 93 BD 20 1D 1B 6D                 \n" +
                    " 20 67 83 D8 D3 66 9B 68 24 0C 53 1D CA 91 DE                 \n" +
                    " AA DC C8 64 E0 8C 50 C1 22 09 C9 73 92 71 59                 \n" +
                    " EC 37 C9 81 57 A5 1F BB 27 D2 A2 82 30 4E 4F                 \n" +
                    " 5A 83 45 B0 AB 10 09 92 3A 55 49 87 25 B1 5A                 \n" +
                    " 12 F0 B5 42 7F BA 79 AA 40 F6 38 29 50 C7 33                 \n" +
                    " A8 FE 16 23 F5 AB BA 3C E6 0B F8 C0 C9 12 7C                 \n" +
                    " A4 7F 2F D6 99 3C 62 4D 51 E3 39 01 E4 23 E9                 \n" +
                    " CD 56 87 39 47 07 04 10 C2 BB 7A 1C 11 D2 47                 \n" +
                    " 70 1B 2A 0D 19 A6 44 73 1F B8 38 A7 F4 AD 16                 \n" +
                    " A8 CE 4A CE C0 69 69 3A D1 DE 99 20 28 27 9A                 \n" +
                    " 3A 51 40 8F FF D9 00 08 00 0F 00 30 30 30 30                 \n" +
                    " 30 32 33 30 36 35 39 30 31 38 33 08 00 04 00                 \n" +
                    " 31 2E 30 30 08 00 11 00 35 31 30 35 31 33 32                 \n" +
                    " 31 31 39 30 31 37 30 30 37 38                                \n";

            // calling and printing the output
            // of decimalToHex() method
            System.out.println("Inputted number : " +num);
            System.out.println(ob.decimalToHex(10011110));
        }




    }



