
/**
 * Polynomial Tester 
 * @author Eylul Badem
 * @version 2.0, 16.02.2021
*/ 
public class PolynomialTester
{
    public static void main(String[] args)
    {
        // Variables
        
        double[] coefficients1 = {-1, 1, 2};
        double[] coefficients2 = {-1, -1 };
        Polynomial pol = new Polynomial( coefficients1 );
        Polynomial pol2 = new Polynomial( coefficients2 );
        Polynomial sumPol = pol.add( pol2 );
        Polynomial subPol = pol.sub( pol2 );
        Polynomial mulPol = pol.mul( pol2 );
        Polynomial compPol = pol.compose( pol2 );
        Polynomial divPol = pol.div( pol2 );

        // Program Code

        System.out.println("First polynomial is");
        System.out.println( pol.toString() );
        System.out.println("\nSecond polynomial is");
        System.out.println( pol2.toString() );
        System.out.println("\nSum of polynomials is");
        System.out.println( sumPol.toString() );
        System.out.println("\nSub of polynomials is");
        System.out.println( subPol.toString() );
        System.out.println("\nMul of polynomials is");
        System.out.println( mulPol.toString() );
        System.out.println("\nComposition of polynomials is");
        System.out.println( compPol.toString() );
        System.out.println("\nDivision of polynomials is");
        System.out.println( divPol.toString() );
    }
}