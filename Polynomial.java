
/**
 * A Program To Evaluate and Do Operations With Polynomials  
 * @author Eylul Badem
 * @version 2.0, 16.02.2021
*/ 
public class Polynomial
{
    // Properties
    
    private double[] coefficients;
    private double[] sum;
    private double[] sub;
    private double[] mul;
    private double[] comp;
    private double[] div;
    private double[] t;

    // Constructors
    
    public Polynomial ( double[] coefficients )
    { 
        this.coefficients = coefficients;
    }
    
    public Polynomial ( int c, int d )
    {
        coefficients = new double[d+1];
        coefficients[d] = c;
    }
    
    public Polynomial ()
    {
        coefficients = new double[0];
    }
    
    // Methods
    
    /**
     * This method takes degree and returns the coefficient of the term with that degree
     * @param letter guessed letter by the player 
     * @return number of occurences of given letter in the secret word
     */
    public double getterMethod ( int degree ) {
        if ( degree >= 0 && degree > 0 && coefficients[degree] != 0 )
        {
            return coefficients[degree];
        }
        else 
        {
            return 0;
        }
    }
    
    /**
     * This method evaluates the polynomial at x and returns the result
     * @param x the number to use instead of the unknown when evaluating the polynomial
     * @return the result of evalutaion
     */
    public double eval(double x) {
        double sum = 0;
        
        for ( int i = 0; i <= coefficients.length - 1; i++ )
        {
            sum = sum + (coefficients[i] * Math.pow(x , i));
        }
        return sum;
    }
    
    /**
     * This method returns the degree of the polynomial
     * 
     * @return highest degree of this polynomial
     */
    public int getDegree()
    {
        for ( int i = coefficients.length - 1; i >= 0; i-- )
        {
            if ( coefficients[i] != 0 )
                return i;
        }
        return 0;
    }
    
    /**
     * This method returns the coefficients array for this polynomial
     * 
     * @return coefficients
     */
    public double[] getCoef()
    {
        return this.coefficients;
    }
    
    /**
     * This method evaluates the polynomial using Horner’s method
     * @param x the number to use instead of the unknown when evaluating the polynomial
     * @return the result of evalutaion
     */
    public double eval2(double x) {
        double sum = 0;
        
        for ( int i = coefficients.length - 1; i >= 0; i-- )
        {
            sum = ( sum * x ) + coefficients[i];
        }
        return sum;
    }
    
    /**
     * This method returns String representation of this polynomial
     * 
     * @return coefficients written again in polynomial form 
     */
    public String toString()
    {
        String polynomial = "";
        
        if ( coefficients.length == 0 )
            return polynomial;
            
        for ( int i = 0; i < coefficients.length; i++)
        {
            if ( i == 0 && coefficients[i] != 0 )
                polynomial += coefficients[i];
            else if ( coefficients[i] == 0 )    
                polynomial += "";
            else if ( coefficients[i] > 0 )
                polynomial += " + (" + coefficients[i] + ")x^" + i;
            else if ( coefficients[i] < 0 )
                polynomial += " - (" + Math.abs(coefficients[i]) + ")x^" + i;
        }
        
        return polynomial + " ";
    }
  
    /**
     * This method sums that polynomial and this polynomial
     * @param that that polynomial 
     * @return the result polynomial 
     */ 
    public Polynomial add( Polynomial that ) { 
        double[] coef2 = that.getCoef();
        int size = Math.max(coefficients.length, coef2.length ); 
        sum = new double[size];
  
        for (int i = 0; i < coefficients.length; i++) { 
            sum[i] = coefficients[i]; 
        } 
        
        for (int i = 0; i < coef2.length; i++) { 
            sum[i] += coef2[i]; 
        } 
         
        Polynomial sumPol = new Polynomial( sum );
        return sumPol; 
    } 
    
    /**
     * This method subtracts that polynomial from this polynomial
     * @param that that polynomial 
     * @return the result polynomial of substraction
     */
    public Polynomial sub( Polynomial that ) { 
        
        Polynomial pol = new Polynomial( coefficients );
        int size = coefficients.length; 
        sub = new double[ size ];
        Polynomial subPol = new Polynomial( sub );
        Polynomial minusOne = new Polynomial( -1, 0 );
        Polynomial randomPol = that.mul( minusOne );
        subPol = pol.add( randomPol );
    
        return subPol; 
    } 
    
    /**
     * This method multiplies this polynomial with that polynomial
     * @param that that polynomial 
     * @return the result polynomial of multiplication
     */
    public Polynomial mul( Polynomial that ) { 
        double[] coef2 = that.getCoef();
        int size = ( coefficients.length + coef2.length ); 
        mul = new double[ size ];
  
        for (int i = 0; i < coefficients.length; i++) { 
            for (int j = 0; j < coef2.length; j++) {
                mul[i+j] += ( coefficients[i] * coef2[j] );
            }
        } 
        Polynomial mulPol = new Polynomial( mul );
        return mulPol; 
    } 
    
    /**
     * This method composes this polynomial with that polynomial
     * @param that that polynomial (inner polynomial)
     * @return the result polynomial of composition
     */
    public Polynomial compose( Polynomial that ) {
        comp = new double[ coefficients.length ];
        Polynomial compPol = new Polynomial();
        Polynomial randomPol;
        
        for (int i = coefficients.length - 1; i >= 0; i--) {
            comp[0] = coefficients[i];
            Polynomial term = new Polynomial( comp );
            randomPol = that.mul( compPol );
            compPol = term.add(randomPol);
        }
        return compPol; 
    } 
    
    /**
     * This method divides this polynomial with that polynomial
     * @param thatPol that polynomial (divisor)
     * @return the quotient polynomial
     */
    public Polynomial div( Polynomial thatPol ) {
        Polynomial pol = new Polynomial( coefficients );
        int size = coefficients.length; 
        div = new double[ size ];
        Polynomial divPol = new Polynomial( div );
        
        while ( pol.getDegree() >= thatPol.getDegree() )
        {
            double polMax = pol.getterMethod( pol.getDegree() );
            double thatMax = thatPol.getterMethod( thatPol.getDegree() );
            t = new double[ size ];
            t[ pol.getDegree() - thatPol.getDegree() ] = polMax / thatMax ;
            Polynomial T = new Polynomial( t );
            pol = pol.sub( thatPol.mul( T ) );
            divPol = divPol.add( T );
        }
        
        return divPol; 
    } 
}