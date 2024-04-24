package model;
public class Subtask extends Task{
    private Epic epic;
    public Subtask(String name, String description, Status status) {
        super(name, description, status);
    }

    public Subtask(String name, String description, Status status, Epic epic) {
        super(name, description, status);
        this.epic = epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }
}