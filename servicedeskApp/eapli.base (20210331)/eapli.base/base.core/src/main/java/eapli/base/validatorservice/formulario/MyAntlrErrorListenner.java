package eapli.base.validatorservice.formulario;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class MyAntlrErrorListenner extends BaseErrorListener implements ANTLRErrorListener {


    String errorMsg;
    List<String> errorsList ;

    MyAntlrErrorListenner(){
        this.errorMsg = "";
        this.errorsList = new ArrayList<>();
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
        int line = i;
        int charPositionInLine = i1;
        String msg = s;

        String sourceName = recognizer.getInputStream().getSourceName();

        if (!sourceName.isEmpty()) {
            sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
        }

        System.err.println(sourceName+"line "+line+":"+charPositionInLine+" "+msg);
        errorMsg = "\n" + sourceName+"line "+line+":"+charPositionInLine+" "+msg;
        errorsList.add(errorMsg);
        //System.err.println(sourceName+"line "+line+":"+charPositionInLine+" "+msg);

    }




    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String err : errorsList){
            stringBuilder.append(" " + err + "/n");
        }



        return stringBuilder.toString();
    }
}
