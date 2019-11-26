package bashShell.ast;

public abstract class AST {
    //tree initialization
    public String treeAst = "";
    //visit method
    protected abstract String visit(int i);
    //Method for indenting each line
    public String Indent(int len) {
        StringBuffer outputBuffer = new StringBuffer(len);
        for (int i = 0; i < len; i++) {
            outputBuffer.append(" ");
        }
        return outputBuffer.toString();
    }
}