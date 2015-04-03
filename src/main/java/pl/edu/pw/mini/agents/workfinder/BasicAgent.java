package pl.edu.pw.mini.agents.workfinder;


import jade.core.Agent;

public class BasicAgent extends Agent {
    @Override
    protected void setup() {
        System.out.printf("Hello! I'm " + getAID().getName());
    }
}
