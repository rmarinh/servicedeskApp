package eapli.base.validatorservice.script;

import java.util.HashMap;
import java.util.Map;

public class Ambito {
/* Ambito da declaracao de comandos */
    private Ambito parent;
    private Map<String, Valor> variables;
    private boolean isFunction;

    Ambito() {
        this(null, false);
    }

    Ambito(Ambito p, boolean function) {
        parent = p;
        variables = new HashMap<>();
        isFunction = function;
    }

    public void assignParam(String var, Valor value) {
    	variables.put(var, value);
    }
    
    public void declarar(String var, Valor value) {
            variables.put(var, value);

    }
    public void replace(String var, String textToFind, String textToReplace) {

        Valor temp = variables.get(var);
        if (temp != null){
            if (temp.isString()){
                String valueReplaced = temp.asString();
               valueReplaced= valueReplaced.replaceAll(textToFind.replaceAll("\"",""), textToReplace.replaceAll("\"",""));
                variables.put(var, new Valor(valueReplaced));
            }
        }


    }


    private boolean isAmbitoGlobal() {
        return parent == null;
    }

    public Ambito parent() {
        return parent;
    }


    public Valor resolve(String var) {
        return resolve(var, true);
    }

    private Valor resolve(String var, boolean checkParent) {
        Valor value = variables.get(var);
        if(value != null) {
            return value;
        }
        else if(checkParent && !isAmbitoGlobal()) {
            return parent.resolve(var, !parent.isFunction);
        }
        else {
            // Unknown variable
            return null;
        }
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for(Map.Entry<String, Valor> var: variables.entrySet()) {
    		sb.append(var.getKey()).append("->").append(var.getValue()).append(",");
    	}
    	return sb.toString();
    }
}
