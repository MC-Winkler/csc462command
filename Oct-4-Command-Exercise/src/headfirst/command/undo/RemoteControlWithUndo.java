package headfirst.command.undo;

import java.util.*;

//
// This is the invoker
//
public class RemoteControlWithUndo {
  private Command[] onCommands;
  private Command[] offCommands;
  private Stack<Command> undoCommands;

  public RemoteControlWithUndo() {
    onCommands = new Command[7];
    offCommands = new Command[7];

    Command noCommand = new NoCommand();
    for (int i = 0; i < 7; i++) {
      onCommands[i] = noCommand;
      offCommands[i] = noCommand;
    }
    undoCommands = new Stack<Command>();
  }

  public void setCommand(int slot, Command onCommand, Command offCommand) {
    onCommands[slot] = onCommand;
    offCommands[slot] = offCommand;
  }

  public void onButtonWasPushed(int slot) {
    onCommands[slot].execute();
    undoCommands.push(onCommands[slot]);
  }

  public void offButtonWasPushed(int slot) {
    offCommands[slot].execute();
    undoCommands.push(offCommands[slot]);
  }

  public void undoButtonWasPushed() {
    if (!undoCommands.empty()){
    	undoCommands.pop().undo();
    } else {
    	System.out.println("There's nothing else to undo");
    }
  }

  public String toString() {
    StringBuffer stringBuff = new StringBuffer();
    stringBuff.append("\n------ Remote Control -------\n");
    for (int i = 0; i < onCommands.length; i++) {
      stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getName() + "    "
                        + offCommands[i].getClass().getName() + "\n");
    }
    Stack<Command> undoStackCopy = (Stack<Command>) undoCommands.clone();
    while (!undoStackCopy.empty()) {
    	stringBuff.append("[undo] " + undoStackCopy.pop().getClass().getName() + "\n");
    }
    return stringBuff.toString();
  }
}
