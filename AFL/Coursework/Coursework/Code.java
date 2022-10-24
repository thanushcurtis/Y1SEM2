import java.sql.Array;

public class Code
{
    public static void main(String[] args) 
    {
        // DEMO code, using automaton of Figure 1

        FSA A0=generateFSA0();

        // Check and print the automaton A0
        checkPrintFSA(A0,"A0");

        // Check if A0 accepts some words
        Word w = new Word(new String[]{"0","0","1"});
        System.out.println("accepts "+w+": "+A0.isAccepted(w));
        w = new Word(new String[]{"1","1","2"});        
        System.out.println("accepts "+w+": "+A0.isAccepted(w));
        w = new Word(new String[]{"2","0","1"});        
        System.out.println("accepts "+w+": "+A0.isAccepted(w));
        w = new Word(new String[]{});        
        System.out.println("accepts "+w+": "+A0.isAccepted(w));

        // Check if A0 is deterministic -- the method is not implemented
        System.out.println("deterministic: "+isDeterministic(A0));
        // Check if A0 is complete -- the method is not implemented
        System.out.println("complete: "+isComplete(A0));


        // ---------
        // MAIN CODE
        // ---------
        // The code below tests the methods that you need to implement and prints
        // out related messages. Do not change this; instead, change the bodies 
        // of methods generateFSA1, etc. as specified in the coursework questions.

        FSA[] As = new FSA[]{generateFSA1(),generateFSA2()};
        String[] names = new String[]{"A1","A2"};
        
        // Question 13
        System.out.println("\nPrintout (Q13)");
        checkPrintFSA1(As,names);

        // Question 14
        System.out.println("\nPrintout (Q14)");
        checkPrintFSA2(As,names);

        // Question 15
        System.out.println("\nPrintout (Q15)");
        runFSAs(As,names);

        // Question 16 (isDeterministic/isComplete)
        // you can comment out the test for the method that you are not implementing
        System.out.println("\nPrintout (Q16)");
        testIsDeterministic();
        testIsComplete();

        // Question 17
        System.out.println("\nPrintout (Q17)");
        testComplement();
    }

    // Demo FSA, do not change this
    public static FSA generateFSA0() {
        String[] alphabet = new String[]{ "0", "1", "2" };
        Transition[] delta = new Transition[] { 
            new Transition(0,"0",0), 
            new Transition(0,"0",1),
            new Transition(0,"2",2),
            new Transition(1,"1",2),
            new Transition(2,"0",2),
            new Transition(2,"1",2)
        };
        int[] finals = new int[] { 2 };
        FSA A = new FSA(3,alphabet,delta,finals);
        return A;
    }

    // -----------------------------------
    // TODO CODE (REQUIRES IMPLEMENTATION)
    // -----------------------------------

    // TODO construct FSA of Question 13 and return it
    public static FSA generateFSA1()
    {
        String[] alphabet = new String[]{ "0", "1", "2" };
        Transition[] delta = new Transition[] {
                new Transition(0,"3",2),
                new Transition(0,"3",5),
                new Transition(1,"2",2),
                new Transition(2,"2",1),
                new Transition(2,"2",3),
                new Transition(3,"3",4),
                new Transition(4,"2",2),
                new Transition(5,"2",3),


        };
        int[] finals = new int[] { 3,4 };
        FSA A = new FSA(6,alphabet,delta,finals);
        return A;

    }

    // TODO construct FSA of Question 14 and return it
    public static FSA generateFSA2() {
        String[] alphabet = new String[]{ "0", "1", "2" };
        Transition[] delta = new Transition[] {
                new Transition(0,"3",2),
                new Transition(0,"5",2),
                new Transition(2,"3",2),
                new Transition(2,"1",1),
                new Transition(1,"1",1),
                new Transition(1,"5",0),



        };
        int[] finals = new int[] { 2};
        FSA A = new FSA(3,alphabet,delta,finals);
        return A;
    }

    // TODO implement this for Question 15
    public static void runFSA(FSA A, String name, Word input) {

        if(A.isAccepted(input))
        {
            System.out.println(name+" accepts: "+input+" [yes]");
        }
        else
        {
            System.out.println(name+" accepts: "+input+" [no]");
        }
    }
    
    // TODO return true if A is deterministic and false otherwise (Question 16)
    public static Boolean isDeterministic(FSA A) {
        return null; // TODO remove this
    }
    public static Boolean isComplete(FSA A) {
        int FSA_Length = A.delta.length;
        int alph_Length = A.alphabet.length;
        System.out.println(alph_Length+"test");
        Boolean success;
        boolean label_check_0=false;
        boolean label_check_1=false;
        boolean label_check_2=false;
        boolean q0_success=false;
        boolean q1_success=false;
        boolean q2_success=false;
        for(int k=0; k<FSA_Length;k++)
        {
            if(A.delta[k].from==0)
            {
                for(int j=0; j<FSA_Length; j++)
                {
                    if(A.delta[j].from==0&&A.delta[j].label.equals("0"))
                    {
                        label_check_0=true;
                    }
                    else if(A.delta[j].from==0&&A.delta[j].label.equals("1"))
                    {
                        label_check_1=true;
                    }
                    else if(A.delta[j].from==0&&A.delta[j].label.equals("2")) {
                        label_check_2 = true;
                    }
                }
                if(label_check_1&&label_check_0&&label_check_2)
                {
                    q0_success=true;
                }
                else
                {
                    q0_success=false;
                }
            }
            else if(A.delta[k].from==1)
            {
                for(int j=0; j<FSA_Length; j++)
                {
                    if(A.delta[j].from==1&&A.delta[j].label.equals("0"))
                    {
                        label_check_0=true;
                    }
                    else if(A.delta[j].from==1&&A.delta[j].label.equals("1"))
                    {
                        label_check_1=true;
                    }
                    else if(A.delta[j].from==1&&A.delta[j].label.equals("2")) {
                        label_check_2 = true;
                    }
                }
                if(label_check_1&&label_check_0&&label_check_2)
                {
                    q1_success=true;
                }
                else
                {
                    q1_success=false;
                }
            }

            else if(A.delta[k].from==2)
            {
                for(int j=0; j<FSA_Length; j++)
                {
                    if(A.delta[j].from==2&&A.delta[j].label.equals("0"))
                    {
                        label_check_0=true;
                    }
                    else if(A.delta[j].from==2&&A.delta[j].label.equals("1"))
                    {
                        label_check_1=true;
                    }
                    else if(A.delta[j].from==2&&A.delta[j].label.equals("2")) {
                        label_check_2 = true;
                    }
                }
                if(label_check_1&&label_check_0&&label_check_2)
                {
                    q2_success=true;
                }
                else
                {
                    q2_success=false;
                }
            }


        }

        if(q0_success&&q1_success&&q2_success)
        {
            success=true;
        }
        else
        {
            success=false;
        }


        return success; // TODO remove this
    }


    // TODO return a new FSA that is the complement of A (Question 17)
    // This method assumes that A is a complete deterministic FSA
    public static FSA complement(FSA A) {

        int alph_Length = A.alphabet.length;
        int FSA_Length = A.delta.length;
        int final_length = A.finalStates.length;
        int[] state_array = new int[A.numStates];
        if(A.numStates==2)
        {
            for(int k=0; k<A.numStates-1; k++)
            {
                A.finalStates[k]=A.delta[k].from;
            }
        }
        else
        {
            for(int j=0; j<A.numStates; j++)
            {
                for(int k=0; k<FSA_Length; k++) {
                    if (k != FSA_Length - 1) {
                        if (A.delta[k].from != A.delta[k + 1].from) {
                            state_array[j] = A.delta[k].from;
                        }
                    }
                }
            }
            System.out.println(state_array[0]);
            System.out.println(state_array[1]);
            System.out.println(state_array[2]);
            for(int i=0; i< A.numStates; i++)
            {

                if(state_array[i]!=A.finalStates[i])
                {
                    A.finalStates[i]=state_array[i];
                }
            }
        }


    	return A; // TODO remove this
    }

    // --------------------------------
    // DO NOT CHANGE THE REMAINING CODE    
    // --------------------------------

    // print FSA of Q13 after checking it is valid
    public static void checkPrintFSA1(FSA[] As, String[] names) {
        checkPrintFSA(As[0],names[0]);
    }    
    
    // print FSA of Q14 after checking it is valid
    public static void checkPrintFSA2(FSA[] As, String[] names) {
        checkPrintFSA(As[1],names[1]);
    }    

    // print FSA A, with given name, after checking it is valid
    public static void checkPrintFSA(FSA A, String name) {
	        if(A==null) return;
        	String s = A.check();
	        if(s!="") System.out.println("Error found in "+name+":\n"+s);
        	else System.out.println(name+" = "+A);
    }    

    // run FSAs, using runFSA method (Q15)
    public static void runFSAs(FSA[] As, String[] names) {
    	Word[][] inputs = new Word[][]{
    	    new Word[]{
    	        new Word(new String[]{}), 
    	        new Word(new String[]{"1","2"}), 
    	        new Word(new String[]{"3","2"}), 
    	        new Word(new String[]{"3","2","1","1","2"}), 
    	        new Word(new String[]{"3","2","2","2","3"})
    	    },
    	    new Word[]{
    	        new Word(new String[]{"3"}), 
    	        new Word(new String[]{"5","3"}), 
    	        new Word(new String[]{"5","3","1"}), 
    	        new Word(new String[]{"5","1","1","5","5"}),
    	        new Word(new String[]{"5","1","3","3","5"})
    	    }
    	};
        for(int i=0; i<As.length; i++)
            	for(Word input : inputs[i])
        	    runFSA(As[i],names[i],input);
    }

    // test the isDeterministic method on 10 FSAs (Q16)
    public static void testIsDeterministic() {
        System.out.println("Testing isDeterministic method");
        int j, marks = 0;
        Object[] X = FSA.getSamples();
        FSA[] tests = (FSA[])X[0];
        Boolean[] res = (Boolean[])X[1];
        for(int i=0; i<tests.length; i++) {
            System.out.println("Test "+(i+1)+": "+tests[i]);
            j = (res[i]==isDeterministic(tests[i])) ? 1 : 0;
            System.out.println("Result: "+j+" (was "+res[i]+")");
            marks += j;
        }
        System.out.println("Total: "+marks);
    }

    // test the isComplete method on 10 FSAs (Q16)
    public static void testIsComplete() {
        System.out.println("Testing isComplete method");
        int j, marks = 0;
        Object[] X = FSA.getSamples();
        FSA[] tests = (FSA[])X[0];
        Boolean[] res = (Boolean[])X[2];
        for(int i=0; i<tests.length; i++) {
            System.out.println("Test "+(i+1)+": "+tests[i]);
            j = (res[i]==isComplete(tests[i])) ? 1 : 0;
            System.out.println("Result: "+j+" (was "+res[i]+")");
            marks += j;
        }
        System.out.println("Total: "+marks);
    }
    
    // test the complement method on 10 complete DFAs (Q17)
    public static void testComplement() {
        System.out.println("Testing complement method");
        FSA[] As = FSA.getCompleteDFAs();
        for(int i=0; i<As.length; i++) {
            System.out.println("Test "+(i+1)+":     "+As[i]);
            System.out.println("Complement: "+complement(As[i]));
        }
    }
    
}
