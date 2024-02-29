import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard=new Scanner(System.in);
        Tree<String> familyTree = new Tree<>();
        System.out.println(" Enter filename ");
        String filename = keyboard.nextLine();
        String a="C:\\Users\\asus\\IdeaProjects\\assignment3\\src\\family\\"+filename;
        familyTree.createFamily(a);
        Tree.Node root = familyTree.getRoot();



        int input = 0;
        while (input != 6) {


            System.out.println("Enter operation code: ");
            input = keyboard.nextInt();
        switch (input) {

            case 1:
                System.out.println("Enter ID: ");
                int p = keyboard.nextInt();
                familyTree.printAllDescendants(p);
                System.out.println();
                break;

            case 2:
                System.out.println("Enter IDs: ");
                int num1 = keyboard.nextInt();
                int num2 = keyboard.nextInt();
                boolean ancestor = familyTree.CheckAncestor(num1, num2);
                System.out.println(ancestor);
                break;

            case 3:
                System.out.println("Enter IDs: ");
                int num3 = keyboard.nextInt();
                int num4 = keyboard.nextInt();
                boolean descendant = familyTree.CheckDescendant(num3, num4);
                System.out.println(descendant);
                break;

            case 4:
                System.out.println("Enter IDs: ");
                int num5 = keyboard.nextInt();
                int num6 = keyboard.nextInt();
                boolean siblings = familyTree.CheckSiblings(num5, num6);
                System.out.println(siblings);
                break;

            case 5:
                System.out.println("Enter IDs: ");
                int num7 = keyboard.nextInt();
                int num8 = keyboard.nextInt();
                familyTree.findFirstOldestCommonRelative(num7, num8);
                break;
            case 6:
                System.out.println("Stopped!");
                familyTree.Quit();
                break;


        }



        }
    }
}