import java.util.ArrayList;

public class Converter{

    private ArrayList<String> tokenslist;



    public Converter(String userinput){
        this.tokenslist = ParserHelper.parse(userinput.toCharArray());
    }

    public ArrayList<String> gettokenslist(){
        return this.tokenslist;
    }

    public String toPostFix (){
        StringBuilder output = new StringBuilder("");
        LinkedStack<String> operationStack = new LinkedStack<>();
        for (int i = 0; i < gettokenslist().size(); i++){
            if (tokenslist.get(i).equals( "(")){
                operationStack.push(tokenslist.get(i));
            }
            else if (tokenslist.get(i).equals(")")){
                String previous = operationStack.pop();
                while (!previous.equals( "(")){
                    output.append(previous);
                    output.append(" ");
                    previous = operationStack.pop();
                }
            }
            else if (tokenslist.get(i).equals("*" )|| tokenslist.get(i).equals( "/" )|| tokenslist.get(i).equals( "+") || tokenslist.get(i).equals("^") || tokenslist.get(i).equals( "-")){
                if (tokenslist.get(i) == "^"){
                    operationStack.push(tokenslist.get(i));
                }
                else if (tokenslist.get(i).equals( "/")||tokenslist.get(i).equals( "*")){
                    if (operationStack.size() == 0){
                        operationStack.push(tokenslist.get(i));
                    }
                    else{
                        while (operationStack.top().equals("^") || operationStack.top().equals("/") || operationStack.top().equals("*")){
                            output.append(operationStack.pop());
                            output.append(" ");
                            if (operationStack.size()==0|| operationStack.top().equals("(")){
                                break;
                            }
                        }
                        operationStack.push(tokenslist.get(i));
                    }

                }
                else{
                    if (operationStack.size() == 0){
                        operationStack.push(tokenslist.get(i));
                    }
                    else{
                        while (operationStack.top().equals("^")||operationStack.top().equals("/")||operationStack.top().equals( "*")){
                            output.append(operationStack.pop());
                            output.append(" ");
                            if (operationStack.size()==0 || operationStack.top().equals("(")){
                                break;
                            }
                        }
                        operationStack.push(tokenslist.get(i));
                    }
                }              
            }
            
            else{
                output.append(tokenslist.get(i));
                output.append(" ");
            }
            
        }
        while (operationStack.size()!= 0){
            output.append(operationStack.pop());
            output.append(" ");
        }
        return output.toString();
    }
}