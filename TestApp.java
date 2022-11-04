import java.util.Scanner;

public class TestApp {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ExpressionTree eTree  = new ExpressionTree();
        while (true){
            System.out.println("Type your expression:");
            String userinput = scn.nextLine();
            Converter conv = new Converter(userinput);
            String postfix = conv.toPostFix();
            eTree = eTree.convert(postfix);
            eTree.prefix();
            eTree.infix();
            eTree.postfix();
        }
    }
}
