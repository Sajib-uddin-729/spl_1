import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a compression algorithm:");
        System.out.println("1. Huffman Compression");
        System.out.println("2. LZW Compression");
        int algorithmChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.println("Choose an operation:");
        System.out.println("1. Compress");
        System.out.println("2. Decompress");
        int operationChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (algorithmChoice == 1) {
            if (operationChoice == 1) {
                // Huffman Compression - Compress
                System.out.print("Enter the path of the source file to compress: ");
                String srcPath = scanner.nextLine();
                System.out.print("Enter the path of the compressed output file: ");
                String dstPath = scanner.nextLine();
                HuffCompression.compress(srcPath, dstPath);
                System.out.println("Huffman Compression - Compression successful.");
            } else if (operationChoice == 2) {
                // Huffman Compression - Decompress
                System.out.print("Enter the path of the compressed file to decompress: ");
                String srcPath = scanner.nextLine();
                System.out.print("Enter the path of the decompressed output file: ");
                String dstPath = scanner.nextLine();
                HuffCompression.decompress(srcPath, dstPath);
                System.out.println("Huffman Compression - Decompression successful.");
            }
        } else if (algorithmChoice == 2) {
            if (operationChoice == 1) {
                // LZW Compression - Compress
                System.out.print("Enter the path of the source file to compress: ");
                String srcPath = scanner.nextLine();
                System.out.print("Enter the path of the compressed output file: ");
                String dstPath = scanner.nextLine();
                try {
                    LZWCompression.compress(srcPath, dstPath);
                    System.out.println("LZW Compression - Compression successful.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (operationChoice == 2) {
                // LZW Compression - Decompress
                System.out.print("Enter the path of the compressed file to decompress: ");
                String srcPath = scanner.nextLine();
                System.out.print("Enter the path of the decompressed output file: ");
                String dstPath = scanner.nextLine();
                try {
                    LZWCompression.decompress(srcPath, dstPath);
                    System.out.println("LZW Compression - Decompression successful.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
