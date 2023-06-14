import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cesar {
    public static Map<Character, Character> criarTabelaHash(int chave) {
        Map<Character, Character> tabelaHash = new HashMap<>();
        char primeiro = ' ';
        char ultimo = '~';

        for (char caractere = primeiro; caractere <= ultimo; caractere++) {
            char caractereCriptografado = (char) ((caractere - primeiro + chave) % (ultimo - primeiro + 1) + primeiro);
            tabelaHash.put(caractere, caractereCriptografado);
        }

        return tabelaHash;
    }

    public static String criptografar(String frase, Map<Character, Character> tabelaHash) {
        StringBuilder fraseCriptografada = new StringBuilder();

        for (char caractere : frase.toCharArray()) {
            char caractereCriptografado = tabelaHash.get(caractere);
            fraseCriptografada.append(caractereCriptografado);
        }

        return fraseCriptografada.toString();
    }
    public static String descriptografar(String fraseCriptografada, Map<Character, Character> tabelaHash) {
        StringBuilder fraseOriginal = new StringBuilder();

        for (char caractere : fraseCriptografada.toCharArray()) {
            for (Map.Entry<Character, Character> entry : tabelaHash.entrySet()) {
                if (entry.getValue() == caractere) {
                    fraseOriginal.append(entry.getKey());
                    break;
                }
            }
        }

        return fraseOriginal.toString();
    }
    public static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o número da operação");
        System.out.println("1 - criptografar");
        System.out.println("2 - descriptografar");

        int operacao = sc.nextInt();

        System.out.print("Digite a chave de criptografia: ");
        int chave = sc.nextInt();
        Map<Character, Character> tabelaHash = Cesar.criarTabelaHash(chave);
        if(operacao == 1) {
            System.out.print("Digite a frase a ser criptografada: ");
            sc.nextLine();
            String frase = sc.nextLine();
            String fraseCriptografada = Cesar.criptografar(frase, tabelaHash);
            System.out.println("Frase criptografada: " + fraseCriptografada);

        } else if(operacao == 2) {
            System.out.print("Digite a frase a ser descriptografada: ");
            sc.nextLine();
            String frase = sc.nextLine();
            String fraseDescriptografada = Cesar.descriptografar(frase, tabelaHash);
            System.out.println("Frase descriptografada: " + fraseDescriptografada);
        }

    }
}
