import java.io.*;
import java.util.*;

public class LZWCompression {

    public static void compress(String inputFilePath, String outputFilePath) throws IOException {
        // Read the input file
        String input = readFile(inputFilePath);

        // Build the dictionary
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }

        String current = "";
        List<Integer> compressedData = new ArrayList<>();
        for (char c : input.toCharArray()) {
            String next = current + c;
            if (dictionary.containsKey(next)) {
                current = next;
            } else {
                compressedData.add(dictionary.get(current));
                dictionary.put(next, dictionary.size());
                current = "" + c;
            }
        }
        compressedData.add(dictionary.get(current));

        // Write compressed data to the output file
        writeCompressedData(outputFilePath, compressedData);
    }

    public static void decompress(String compressedFilePath, String outputFilePath) throws IOException {
        List<Integer> compressedData = readCompressedData(compressedFilePath);

        // Build the dictionary
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char) i);
        }

        String current = "" + (char)(int)compressedData.remove(0);
        StringBuilder decompressedData = new StringBuilder(current);
        for (int code : compressedData) {
            String entry;
            if (dictionary.containsKey(code)) {
                entry = dictionary.get(code);
            } else if (code == dictionary.size()) {
                entry = current + current.charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid compressed data.");
            }
            decompressedData.append(entry);
            dictionary.put(dictionary.size(), current + entry.charAt(0));
            current = entry;
        }

        // Write decompressed data to the output file
        writeDecompressedData(outputFilePath, decompressedData.toString());
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }
    private static List<Integer> readCompressedData(String filePath) throws IOException {
        List<Integer> compressedData = new ArrayList<>();
        try (DataInputStream input = new DataInputStream(new FileInputStream(filePath))) {
            while (input.available() > 0) {
                compressedData.add(input.readInt());
            }
        }
        return compressedData;
    }
    private static void writeCompressedData(String filePath, List<Integer> compressedData) throws IOException {
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(filePath))) {
            for (int code : compressedData) {
                output.writeInt(code);
            }
        }
    }
    private static void writeDecompressedData(String filePath, String decompressedData) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(decompressedData);
        }
    }
    // private static String getUserInput(String prompt) {
    //     System.out.print(prompt);
    //     Scanner scanner = new Scanner(System.in);
    //     return scanner.nextLine();
    // }

    // public static void main(String[] args) {
    //     try {
    //         Scanner sc = new Scanner(System.in);
    //         System.out.println("Choose an option:");
    //         System.out.println("1. Compress");
    //         System.out.println("2. Decompress");
    //         int choice = Integer.parseInt(sc.nextLine());

    //         if (choice == 1) {
    //             String inputFilePath = getUserInput("Enter the path of the input file: ");
    //             String compressedFilePath = "compressed.lzw";

    //             // Compression
    //             compress(inputFilePath, compressedFilePath);
    //             System.out.println("Compression complete. Compressed file saved as " + compressedFilePath);
    //         } else if (choice == 2) {
    //             String compressedFilePath = getUserInput("Enter the path of the compressed file: ");
    //             String decompressedFilePath = "decompressed.txt";

    //             // Decompression
    //             decompress(compressedFilePath, decompressedFilePath);
    //             System.out.println("Decompression complete. Decompressed file saved as " + decompressedFilePath);
    //         } else {
    //             System.out.println("Invalid choice.");
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}



