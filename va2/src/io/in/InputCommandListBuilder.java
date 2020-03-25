package io.in;

public class InputCommandListBuilder {

    private InputCommandList icl;

    private InputCommandListBuilder(){
        icl = new InputCommandList();
    }

    public static InputCommandListBuilder initialize() {
        return new InputCommandListBuilder();
    }
    public InputCommandListBuilder addInputCommand(InputCommand ic) {
        icl.add(ic);
        return this;
    }
    public InputCommandList build(){
        return icl;
    }
}
