import tc.TC;
public class Palindromes{

    public static boolean estPalindrome(String mot){
        return estPalindrome(mot,0,mot.length()-1);
    }

    public static boolean estPalindrome(String mot, int g, int d){
		//__COMPLETER__
    	if(mot.equals("")){
    		return true;
    	}
    	if(g>d){
    		return false;
    	}else if(g==d){
    		return true;
    	}else if((d-g) == 1){
    		return mot.charAt(g) == mot.charAt(d);
    	}else{
    		if(mot.charAt(g)== mot.charAt(d)){
    			return estPalindrome(mot, g+1, d-1);
    		}else{
    			return false;
    		}

    	}
		
    }

    public static void testPalindrome(String mot){
        if(estPalindrome(mot)){
            TC.println(mot + " est un palindrome");
        }
        else{
            TC.println(mot + " n'est pas un palindrome");			
        }
    }
	
    public static void main(String[] args){
        testPalindrome("AB");
        testPalindrome("BAOBAB");
        testPalindrome("KAYAK");
        testPalindrome("ABBA");
        testPalindrome("BOBAR");
        testPalindrome("ESOPERESTEICIETSEREPOSE");
    }

}
