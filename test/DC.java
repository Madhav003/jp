public class DC {

    static int[] data = {1, 0, 1, 1};
    static int dataBits = 4;
    static int parityBits = 3;
    static int totalBits = dataBits + parityBits;

    public static void main(String[] args) {

        int[] encoded = encode(data);

        System.out.print("Encoded (Sender): ");
        printArray(encoded);

        int errorPos = 5;
        encoded[errorPos - 1] ^= 1;
        System.out.print("After error at bit " + errorPos + ": ");
        printArray(encoded);

        
        decode(encoded);
    }

    static int[] encode(int[] data) {
        int[] code = new int[totalBits];

        int j = 0;
        for (int i = 1; i <= totalBits; i++) {
            if (!isPowerOf2(i)) {
                code[i - 1] = data[j++];
            }
        }

        for (int i = 0; i < parityBits; i++) {
            int parityPos = (int) Math.pow(2, i);
            code[parityPos - 1] = calculateParity(code, parityPos);
        }

        return code;
    }

    static int calculateParity(int[] code, int parityPos) {
        int parity = 0;
        for (int i = parityPos; i <= totalBits; i += 2 * parityPos) {
            for (int j = i; j < i + parityPos && j <= totalBits; j++) {
                if (j != parityPos) {
                    parity ^= code[j - 1];
                }
            }
        }
        return parity;
    }

    static void decode(int[] code) {
        int errorBit = 0;

        for (int i = 0; i < parityBits; i++) {
            int parityPos = (int) Math.pow(2, i);
            int parity = 0;
            for (int j = parityPos; j <= totalBits; j += 2 * parityPos) {
                for (int k = j; k < j + parityPos && k <= totalBits; k++) {
                    parity ^= code[k - 1];
                }
            }
            if (parity != 0) {
                errorBit += parityPos;
            }
        }

        if (errorBit == 0) {
            System.out.println("No error detected.");
        } else {
            System.out.println("Error detected at bit position: " + errorBit);
            code[errorBit - 1] ^= 1; // correct the bit
            System.out.print("Corrected code: ");
            printArray(code);

            System.out.print("Recovered data: ");
            for (int i = 1; i <= totalBits; i++) {
                if (!isPowerOf2(i)) {
                    System.out.print(code[i - 1] + " ");
                }
            }
            System.out.println();
        }
    }

    static boolean isPowerOf2(int n) {
        return (n & (n - 1)) == 0;
    }

    static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }
}