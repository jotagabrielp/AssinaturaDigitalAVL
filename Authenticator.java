import java.io.*;
import java.util.Scanner;

public class Authenticator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVLTree avlTree = new AVLTree();
        System.out.println("Selecione o tipo de operação");
        int tipoOperacao = sc.nextInt();

        if (tipoOperacao == 1) {
            System.out.println("Escreva o nome do documento:");
            String document = sc.next();
            String assinaturaDigital = generateAuthenticationCode(avlTree, document);
            System.out.println("Assinatura digital: " + assinaturaDigital);
        } else if (tipoOperacao == 2) {
            // Verify document authenticity

            System.out.println("Escreva o nome do documento:");
            String document = sc.next();
            System.out.println("Insira o código de autenticação:");
            String authenticationCode = sc.next();
            boolean isAuthentic = verifyDocumentAuthenticity(avlTree, document, authenticationCode);
            if (isAuthentic) {
                System.out.println("Document is authentic.");
            } else {
                System.out.println("Document is not authentic.");
            }
        } else {
            System.out.println("Invalid operation type.");
        }
    }

    private static String generateAuthenticationCode(AVLTree avlTree, String document) {
        try (BufferedReader br = new BufferedReader(new FileReader(document))) {
            String line;
            while ((line = br.readLine()) != null) {
                avlTree.insert(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return avlTree.getRootHash();
    }

    private static boolean verifyDocumentAuthenticity(AVLTree avlTree, String document, String authenticationCode) {
        String calculatedHash = generateAuthenticationCode(avlTree, document);
        return authenticationCode.equals(calculatedHash);
    }
}