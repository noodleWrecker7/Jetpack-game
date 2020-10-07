import java.util.Stack;

public class StateManager {

    static Stack<State> stateStack = new Stack<>();

    static public boolean changed = false; // this is because i cba to plan code so this is used to check when the state needs to be changed in the main loop, becase i cant change mid tick and screw up a state


//    public void Switch(State state){

    //  }

    static public State Pop() {
        changed = true;

        return stateStack.pop();
    }

    static public State Peek() {
        return stateStack.peek();
    }

    static public void Push(State state) {
        stateStack.push(state);
        changed = true;
    }


}
