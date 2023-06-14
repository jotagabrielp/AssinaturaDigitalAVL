import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o tipo de criptografia desejado");
        System.out.println("1 - Cifra de césar");
        System.out.println("2 - Autenticação digital");
        int operacao = sc.nextInt();
        if(operacao == 1) {
            Cesar.main();
        } else if (operacao == 2) {
            Autenticador.main();
        } else {
            System.out.println("Operação invalida");
        }
    }
}
